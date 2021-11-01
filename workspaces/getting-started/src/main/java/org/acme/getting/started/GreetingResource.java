package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    public static final String TOKEN = "dev";

    @Inject
    private GreetingService greetingService;

    @GET
    @Path("/html/service/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloService(@PathParam("name") String name) {
        return greetingService.sayHello(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greetingService.sayHello();
    }

    @GET
    @Path("/html/{id}/details")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("id") String id) {
        return "hello "+ id;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addHello(@HeaderParam("token") String hName, @QueryParam("token") String aName) {
        String token = hName != null ? hName : aName;
        if (!TOKEN.equals(token)) {
            throw new RuntimeException("Unauthorized");
        }
        return "<b>Text</b>";
    }

}