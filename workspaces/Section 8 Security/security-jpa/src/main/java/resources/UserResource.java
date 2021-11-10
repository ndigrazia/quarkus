package resources;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import data.User;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.security.jpa.Roles;

@Path("/api")
public class UserResource {

    @Transactional
    public void onStart(@Observes StartupEvent event) {
        User admin = new User("admin", "admin", "admin");
        admin.persist();

        User user = new User("user", "user", "user");
        user.persist();
    }
    
    @GET
    @PermitAll
    @Path("/public")
    public String getInfoPublic() {
        return "public";
    }

    @GET
    @Path("/secured")
    @RolesAllowed("admin")
    public String getInfo(@Context SecurityContext context) {
        //User u = User.find("username", context.getUserPrincipal().getName());
        return "Hello," +context.getUserPrincipal().getName();
    }

}
