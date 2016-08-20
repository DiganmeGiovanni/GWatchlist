package me.gwatchlist.rservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * Created by giovanni on 19/08/16.
 */

@Path("test")
public class HelloService {

    @Produces("text/plain")
    @GET()
    @Path("hello")
    public String getHello() {
        return "Hello world";
    }

}