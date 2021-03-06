package tech.donau;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import io.smallrye.reactive.messaging.annotations.Channel;

@Path("/prices")
public class PriceResource {
    
    @Inject
    @Channel("converted-prices")
    Publisher<Double> publisher;
    
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    public Publisher<Double> getPrice() {
        return publisher;
    }
    
}
