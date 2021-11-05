package security;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.cache.NoCache;

import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;

@Path("/api")
//@Authenticated
public class UserResource {
 
    @Inject
    SecurityIdentity identity;

    @GET
    @Produces(MediaType.APPLICATION_JSON)     
    @RolesAllowed("user")
    //@RolesAllowed({"user", "admin"})
    //@PermitAll
    //@DenyAll
    @NoCache
    @Path("/users")
    public SecurityIdentity getUserInfo() {
        return identity;
    }


    @GET
    @Path("/admin")
    @Produces(MediaType.APPLICATION_JSON)     
    //@RolesAllowed("admin")
    @NoCache
    public String getAdminInfo() {
        return "ADMIN is a Good";
    }
}
