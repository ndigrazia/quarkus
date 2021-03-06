package org.acme.jms;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/prices")
public class PriceResource {

    @Inject
    PriceConsumer consumer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return consumer.getLastPrice();
    }
}