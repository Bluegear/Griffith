package org.bandofhawk.griffith.rest.resource;

import org.bandofhawk.griffith.dao.CommonDAO;
import org.bandofhawk.griffith.dao.model.User;

import javax.inject.Singleton;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Griffith
 * Created by Bluegear on 6/2/14.
 */

@Path("user")
public class UserResource {

    @POST
    @Path("register")
    @Consumes("application/x-www-form-urlencoded")
    public Response register(@FormParam("email") String email, @FormParam("password") String password) throws Exception {

        User user = new User();
        user.setEmail(email);
        user.setCredential(password);
        try {
            CommonDAO.getInstance().save(user);
            CommonDAO.getInstance().getEntityManager().flush();

            return Response.ok().build();
        }catch(PersistenceException e){
            if(e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException){
                return Response.status(Response.Status.CONFLICT).build();
            }else{
                throw new Exception();
            }
        }
    }

}
