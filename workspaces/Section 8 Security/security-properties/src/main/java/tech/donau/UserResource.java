package tech.donau;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class UserResource {

    @GET
    @Path("/public")
    @PermitAll
    @Produces(MediaType.TEXT_PLAIN)
    public String getInfo() {
        return "public";
    }

    @GET
    @Path("/secured")
    @RolesAllowed("Admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String adminInfo() {
        return "admin";
    }

    @GET
    @Path("/user")
    @RolesAllowed("User")
    @Produces(MediaType.TEXT_PLAIN)
    public String userInfo() {
        return "user";
    }
}