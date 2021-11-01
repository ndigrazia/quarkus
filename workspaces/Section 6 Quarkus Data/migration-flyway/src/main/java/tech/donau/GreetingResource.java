package tech.donau;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.flywaydb.core.Flyway;

@Path("/info")
public class GreetingResource {
    
    @Inject
    Flyway flyway;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return flyway.info().current().getDescription();
    }
}