package me.gwatchlist.config;

import com.googlecode.objectify.ObjectifyService;
import me.gwatchlist.entities.MoviesList;
import me.gwatchlist.entities.User;
import me.gwatchlist.rservices.MovieListRService;
import me.gwatchlist.rservices.UserRService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by giovanni on 19/08/16.
 */
public class RestRegisterer extends Application {

    static {
        ObjectifyService.register(User.class);
        ObjectifyService.register(MoviesList.class);
    }

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register REST Services
        classes.add(UserRService.class);
        classes.add(MovieListRService.class);

        return classes;
    }
}
