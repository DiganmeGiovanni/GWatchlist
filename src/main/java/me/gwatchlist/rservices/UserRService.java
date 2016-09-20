package me.gwatchlist.rservices;

import me.gwatchlist.entities.User;
import me.gwatchlist.services.UserService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * Created by giovanni on 8/09/16.
 */
@Path("user")
public class UserRService {
    private UserService userService = new UserService();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("login")
    public User login(@QueryParam("email") String email,
            @QueryParam("name") String name) {

        User user = userService.login(email, name);
        return user;
    }
}
