package org.bandofhawk.griffith.rest.main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bandofhawk.griffith.dao.model.User;

/**
 * Created by Bluegear on 5/31/14.
 */

@Path("mymodelresource")
public class MyModelResource {
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {

        User user = new User();
        user.setId(Long.valueOf(1));
        return user.getId() + "";
    }
}
