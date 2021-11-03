package tech.donau;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

public class PriceConsumer {
    
     @Incoming("prices")
     @Outgoing("converted-prices")
     @Broadcast
     public double convert(int price) {
        double value = price * 0.5d;
         return value;
     }

}
