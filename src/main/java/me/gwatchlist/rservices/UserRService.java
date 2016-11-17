package me.gwatchlist.rservices;

import me.gwatchlist.entities.User;
import me.gwatchlist.entities.UserPreferences;
import me.gwatchlist.services.UserService;

import javax.ws.rs.*;
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

        return userService.login(email, name);
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("{userId}/preferences")
    public User updatePreferences(@PathParam("userId") Long userId, UserPreferences userPreferences) {
        return userService.updatePreferences(userId, userPreferences);
    }
}











