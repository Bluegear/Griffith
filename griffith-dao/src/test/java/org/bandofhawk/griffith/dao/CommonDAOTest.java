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
        dao = new CommonDAO();
        dao.em.getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        dao.em.getTransaction().rollback();
    }

    @Test
    public void testSave() throws Exception {
        User user = new User();
        user.setEmail("somename");
        dao.save(user);
        User user2 = dao.em.find(User.class, Long.valueOf(1));
        assertEquals(user2.getEmail(), "somename");
    }

    @Test
    public void testDuplicate() throws Exception {
        User user = new User();
        user.setEmail("aaa");
        dao.save(user);
        dao.em.flush();

        User user2 = new User();
        user2.setEmail("aaa");

        try {
            dao.save(user2);
            dao.em.flush();
        } catch (Exception e) {
            assertTrue(e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException);
            return;
        }

        assertTrue(false);
    }
}