package me.gwatchlist.services;

import com.googlecode.objectify.ObjectifyService;
import me.gwatchlist.beans.ListsNames;
import me.gwatchlist.entities.Movie;
import me.gwatchlist.entities.MoviesList;
import me.gwatchlist.entities.User;

import java.util.Date;
import java.util.List;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 *
 * Created by giovanni on 24/09/16.
 */
public class MovieListService {

    public MoviesList createList(String name, String ownerEmail) {

        // Check if user yet has a list with same name
        MoviesList list = this.findUserList(name, ownerEmail);
        if (list != null) {
            return null;
        }

        list = new MoviesList();
        list.setName(name);
        list.setOwnerEmail(ownerEmail);
        list.setCreatedAt(new Date());
        list.setPersonalList(false);
        ofy().save().entity(list);
        return list;
    }

    public MoviesList findUserList(String name, String ownerEmail) {

        return ofy().load()
                .type(MoviesList.class)
                .filter("name", name)
                .filter("ownerEmail", ownerEmail)
                .first()
                .now();
    }

    public MoviesList findUserList(Long id, String ownerEmail) {

        MoviesList moviesList = ofy().load()
                .type(MoviesList.class)
                .id(id)
                .now();

        if (moviesList != null) {
            if (moviesList.getOwnerEmail().compareTo(ownerEmail) == 0) {
                return moviesList;
            }
        }

        return null;
    }

    public ListsNames getListsNames(String ownerEmail) {

        List<MoviesList> moviesLists = ofy().load()
                .type(MoviesList.class)
                .filter("ownerEmail", ownerEmail)
                .list();

        List<MoviesList> moviesListsShared = ofy().load()
                .type(MoviesList.class)
                .filter("sharedWith", ownerEmail)
                .list();

        ListsNames listsNames = new ListsNames(ownerEmail);
        listsNames.addMoviesLists(moviesLists);
        listsNames.addMoviesLists(moviesListsShared);
        return listsNames;
    }

    public MoviesList getPersonalList(String ownerEmail) {

        MoviesList moviesList = ofy().load()
                .type(MoviesList.class)
                .filter("isPersonalList", true)
                .filter("ownerEmail", ownerEmail)
                .first()
                .now();

        if (moviesList == null) {

            // Create user personal list
            moviesList = new MoviesList();
            moviesList.setName("Personal list");
            moviesList.setOwnerEmail(ownerEmail);
            moviesList.setPersonalList(true);
            moviesList.setCreatedAt(new Date());
            ofy().save().entity(moviesList).now();
        }

        return moviesList;
    }

    /**
     * Shares a list with a given user
     * @param listId The id of list to share
     * @param email The email to add to list's members
     * @return Http code:
     *          404 List not found | 409 List is personal | 201 Share successful
     */
    public int shareList(Long listId, String email) {

        // Retrieve movie list from data store
        MoviesList moviesList = ofy().load()
                .type(MoviesList.class)
                .id(listId)
                .now();

        if (moviesList != null) {
            if (moviesList.isPersonalList() || moviesList.isSharedWith(email)) {
                return 409;
            }
            else {
                moviesList.getSharedWith().add(email);
                ofy().save().entity(moviesList);
                return 201;
            }
        }
        else {
            return 404;
        }
    }

    public int addMovie(Long listId, Movie movie) {

        // Retrieve movie list from data store
        MoviesList moviesList = ofy().load()
                .type(MoviesList.class)
                .id(listId)
                .now();

        if (moviesList != null) {
            moviesList.getMovies().add(movie);
            ofy().save().entity(moviesList);
            return 201;
        }

        return 404;
    }
}
