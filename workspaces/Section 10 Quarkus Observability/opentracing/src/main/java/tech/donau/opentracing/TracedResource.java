package tech.donau.opentracing;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/hello")
public class TracedResource {

    private static final Logger LOGGER = Logger.getLogger(TracedResource.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Hello");
        LOGGER.info("How");
        LOGGER.info("are");
        LOGGER.info("you");
        return "hello";
    }
}