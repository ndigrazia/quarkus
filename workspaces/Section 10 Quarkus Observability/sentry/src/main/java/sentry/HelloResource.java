package sentry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/hello")
public class HelloResource {
    
    private static final Logger LOGGER = Logger.getLogger(HelloResource.class);

    @GET
    @Produces(value = MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Executing hello method");
        LOGGER.warn("Executing hello method with warning");

        return "Hello";
    }

}
