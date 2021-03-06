package me.gwatchlist.rservices;

import me.gwatchlist.beans.ListWrapper;
import me.gwatchlist.entities.Movie;
import me.gwatchlist.entities.MoviesList;
import me.gwatchlist.services.MovieListService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * Created by giovanni on 24/09/16.
 */
@Path("movies")
public class MovieListRService {

    private MovieListService moviesListService = new MovieListService();

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("list/{list_id}")
    public Response getList(@PathParam("list_id") Long listId) {
        MoviesList list = moviesListService.findUserList(listId);
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

    @DELETE
    @Path("list/{list_id}")
    public Response deleteList(@PathParam("list_id") Long listId) {
        int responseCode = moviesListService.deleteList(listId);
        return Response.status(responseCode).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("lists")
    public List<ListWrapper> getLists(@QueryParam("owner_email") String ownerEmail) {
        return moviesListService.getLists(ownerEmail);
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
    @Path("list/{list_id}/share")
    public Response shareList(@PathParam("list_id") Long listId,
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

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    @Path("list/{list_id}/movie")
    public Response updateMovie(@PathParam("list_id") Long listId, Movie movie) {
        int responseCode = moviesListService.updateMovie(listId, movie);
        return Response.status(responseCode).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @DELETE
    @Path("list/{list_id}/movie")
    public Response deleteMovie(@PathParam("list_id") Long listId, Movie movie) {
        int responseCode = moviesListService.deleteMovie(listId, movie);
        return Response.status(responseCode).build();
    }
}
