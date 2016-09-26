package me.gwatchlist.rservices;

import me.gwatchlist.beans.ListsNames;
import me.gwatchlist.entities.Movie;
import me.gwatchlist.entities.MoviesList;
import me.gwatchlist.services.MovieListService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * Created by giovanni on 24/09/16.
 */
@Path("movies")
public class MovieListRService {

    private MovieListService moviesListService = new MovieListService();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("list")
    public Response getList(@QueryParam("owner_email") String ownerEmail,
                            @QueryParam("list_id") Long listId) {

        MoviesList list = moviesListService.findUserList(listId, ownerEmail);
        if (list == null) {

            // Movie list not exists
            return Response.status(404).build();
        }

        return Response.status(200).entity(list).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("list")
    public Response createList(@FormParam("owner_email") String ownerEmail,
                                 @FormParam("name") String name) {

        MoviesList list = moviesListService.createList(name, ownerEmail);
        if (list == null) {

            // Movie list not created (Cause yet exists)
            return Response.status(409).build();
        }

        return Response.status(201).entity(list).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("lists")
    public ListsNames getListsNames(@QueryParam("owner_email") String ownerEmail) {

        return moviesListService.getListsNames(ownerEmail);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("list/personal")
    public Response getPersonalList(@QueryParam("owner_email") String ownerEMail) {

        MoviesList list = moviesListService.getPersonalList(ownerEMail);
        if (list == null) {

            // Movies list not exists
            return Response.status(404).build();
        }

        return Response.status(200).entity(list).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("list/share")
    public Response shareList(@FormParam("list_id") Long listId,
                              @FormParam("email") String email) {

        int responseCode = moviesListService.shareList(listId, email);
        return Response.status(responseCode).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    @Path("list/{list_id}/movie")
    public Response addMovie(@PathParam("list_id") Long listId, Movie movie) {

        int responseCode = moviesListService.addMovie(listId, movie);
        return Response.status(responseCode).build();
    }
}
