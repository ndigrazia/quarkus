package org.acme.opentracing;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/hello")
public class TracedResource {

    private static final Logger LOGGER = Logger.getLogger(TracedResource.class);

    @Inject
    FrancophoneService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        LOGGER.info("Hello");
        LOGGER.info("How");
        
        LOGGER.info(service.bonjour());

        LOGGER.info("are");
        LOGGER.info("you");
        
        return "hello";
    }
}