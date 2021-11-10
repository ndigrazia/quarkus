package org.acme.security.jwt;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/secured")
@RequestScoped
public class TokenSecuredResource {

    @Inject
    JsonWebToken jwt; 

    @Inject
    TokenService tokenService;

    /*@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }*/ 

    
    @GET()
    @Path("permit-all")
    @PermitAll 
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@Context SecurityContext ctx) {
        return getResponseString(ctx); 
    }

    @POST
    @PermitAll 
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String,String> generateToken(@QueryParam("username") String username, 
        @QueryParam("email") String email, @QueryParam("birthdate") String birthdate) {

        Map<String,String> map = new HashMap<String,String>();
        map.put("token", tokenService.generateToken(username, email, birthdate));

        return  map;
    }

    private String getResponseString(SecurityContext ctx) {
        String name;
        if (ctx.getUserPrincipal() == null) { 
            name = "anonymous";
        } else if (!ctx.getUserPrincipal().getName().equals(jwt.getName())) { 
            throw new InternalServerErrorException("Principal and JsonWebToken names do not match");
        } else {
            name = ctx.getUserPrincipal().getName(); 
        }
        return String.format("hello + %s,"
            + " isHttps: %s,"
            + " authScheme: %s,"
            + " hasJWT: %s",
            name, ctx.isSecure(), ctx.getAuthenticationScheme(), hasJwt()); 
    }

    private boolean hasJwt() {
	return jwt.getClaimNames() != null;
    }

    @GET
    @Path("roles-allowed") 
    @RolesAllowed({ "User", "Admin" }) 
    @Produces(MediaType.APPLICATION_JSON)
    public String helloRolesAllowed(@Context SecurityContext ctx) {
        return getResponseString(ctx) + ", birthdate: " + jwt.getClaim("birthdate").toString(); 
    }
        
}