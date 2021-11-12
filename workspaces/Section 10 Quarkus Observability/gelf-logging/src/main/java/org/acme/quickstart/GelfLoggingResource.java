package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/gelf-logging")
public class GelfLoggingResource {

    private static final Logger LOGGER = Logger.getLogger(GelfLoggingResource.class);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("hello method executed!!!!!");
        return "Hello RESTEasy";
    }
}