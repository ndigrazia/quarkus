package org.acme.opentracing;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.opentracing.Traced;

import org.jboss.logging.Logger;

@Traced
@ApplicationScoped
public class FrancophoneService {

    private static final Logger LOGGER = Logger.getLogger(FrancophoneService.class);

    public String bonjour() {
        LOGGER.info("bonjour");
        return "bonjour";
    }
}