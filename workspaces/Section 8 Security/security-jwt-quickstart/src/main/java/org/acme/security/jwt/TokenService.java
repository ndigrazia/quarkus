package org.acme.security.jwt;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

import javax.enterprise.context.RequestScoped;

import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import io.smallrye.jwt.build.Jwt;

@RequestScoped
public class TokenService {

    public final static Logger LOGGER = Logger.getLogger(TokenService.class);
    
    public String generateToken(String username, String email, String birthdate) {
        String token =
        Jwt.issuer("DonauTech") 
          .upn(email) 
          .subject(email)
          .audience("using-jwt")
          .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
          .claim(Claims.birthdate.name(), "2001-07-13") 
          .claim(Claims.preferred_username.name(), username)
          .claim(Claims.upn.name(), email)
          .expiresIn(Duration.ofMinutes(10))
        .sign();

        LOGGER.info("TOKEN generated:" + token);

        return token;       
    }
    
}
