package tech.donau;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

public class PriceConsumer {
    
    /* @Incoming("prices")
     @Outgoing("converted-prices")
     @Broadcast
     public double convert(long price) {
        double value = price * 0.5d;
        System.out.println("PriceConsumer-------------> " + value);
         return value;
     }*/

     @Incoming("prices")
     @Outgoing("converted-prices")
     @Broadcast
     public Price convert(Price price) {
         price.setPrice(price.getPrice() * 0.5d);
        System.out.println("PriceConsumer-------------> " + price.getPrice());
         return price;
     }
}
