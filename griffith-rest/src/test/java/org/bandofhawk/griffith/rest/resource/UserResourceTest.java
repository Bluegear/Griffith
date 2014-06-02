package org.bandofhawk.griffith.rest.resource;

import org.bandofhawk.griffith.dao.CommonDAO;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class UserResourceTest extends JerseyTest {

    private CommonDAO dao = CommonDAO.getInstance();

    @Before
    public void setUp() throws Exception {
        super.setUp();
        dao.getEntityManager().getTransaction().begin();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        dao.getEntityManager().getTransaction().rollback();
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(UserResource.class);
    }

    @Test
    public void testRegister() throws Exception {

        Form form = new Form();
        form.param("email", "me@localhost.com");
        form.param("password", "abc123");

        Response response = target("user/register").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDuplicateRegister() throws Exception {

        Form form = new Form();
        form.param("email", "duplicate@localhost.com");
        form.param("password", "abc123");

        Response response = target("user/register").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertEquals(200, response.getStatus());

        response = target("user/register").request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE));
        assertEquals(409, response.getStatus());
    }
}