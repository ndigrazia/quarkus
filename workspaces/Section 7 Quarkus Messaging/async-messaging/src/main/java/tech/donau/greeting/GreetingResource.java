package tech.donau.greeting;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;

@Path("/greeting")
public class GreetingResource {

    @Inject
    EventBus eventBus;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello(@QueryParam("name") String name ) {
        return eventBus.<String>request("greeting", name).onItem().apply(it->it.body());
    }
}       