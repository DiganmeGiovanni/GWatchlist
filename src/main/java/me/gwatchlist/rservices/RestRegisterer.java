package me.gwatchlist.rservices;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Created by giovanni on 19/08/16.
 */
public class RestRegisterer extends Application {

    @Override
    public Set<Class<?>> getClasses() {

        Set<Class<?>> classes = new HashSet<Class<?>>();

        // Register REST Services
        classes.add(HelloService.class);
        classes.add(UserRService.class);

        return classes;
    }
}
