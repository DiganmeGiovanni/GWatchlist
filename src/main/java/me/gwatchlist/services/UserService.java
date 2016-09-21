package me.gwatchlist.services;

import com.googlecode.objectify.ObjectifyService;
import me.gwatchlist.entities.User;

import java.util.Date;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 *
 * Created by giovanni on 11/09/16.
 */
public class UserService {

    static {
        ObjectifyService.register(User.class);
    }

    public User login(String email, String name) {

        // Create user if does not exists
        User user = this.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setCreatedAt(new Date());
        }

        // Updates name and last login date
        user.setName(name);
        user.setLastLoginAt(new Date());
        user.setLoginCount(user.getLoginCount() + 1);

        ofy().save().entity(user);
        return user;
    }

    private User findByEmail(String email) {
        return ofy()
                .load()
                .type(User.class)
                .filter("email", email)
                .first()
                .now();
    }
}
