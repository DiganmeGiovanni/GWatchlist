package me.gwatchlist.rservices;

import me.gwatchlist.entities.User;
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

        System.out.println("Receiving login request");
        return userService.login(email, name);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("preferences")
    public User updatePreferences(@FormParam("email") String email,
                                  @FormParam("notifyOnListShared") boolean notifyOnListShared,
                                  @FormParam("notifyOnMovieAdded") boolean notifyOnMovieAdded,
                                  @FormParam("theme") String theme) {
        return userService.updatePreferences(
                email,
                notifyOnListShared,
                notifyOnMovieAdded,
                theme
        );
    }
}











