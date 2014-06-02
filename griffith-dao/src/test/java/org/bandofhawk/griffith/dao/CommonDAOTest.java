package org.bandofhawk.griffith.dao;

import org.bandofhawk.griffith.dao.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommonDAOTest {

    CommonDAO dao;

    @Before
    public void setUp() throws Exception {
        dao = CommonDAO.getInstance();
        dao.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        dao.getEntityManager().getTransaction().rollback();
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setEmail("somename");
        dao.save(user);
        User user2 = dao.getEntityManager().find(User.class, (long) 1);
        assertEquals(user2.getEmail(), "somename");
    }

    @Test
    public void testDuplicate() throws Exception {
        User user = new User();
        user.setEmail("aaa");
        dao.save(user);
        dao.getEntityManager().flush();

        User user2 = new User();
        user2.setEmail("aaa");

        try {
            dao.save(user2);
            dao.getEntityManager().flush();
        } catch (Exception e) {
            assertTrue(e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException);
            return;
        }

        assertTrue(false);
    }
}