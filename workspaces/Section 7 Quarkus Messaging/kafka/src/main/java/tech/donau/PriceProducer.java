package tech.donau;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;

@ApplicationScoped
public class PriceProducer {
    
    Random random = new Random();

   /* @Outgoing("price")
    public Flowable<Long> producePrice() {
        return Flowable.interval(5, TimeUnit.SECONDS)
            .map(it->  {
                long value = (long) random.nextInt(4000);
                System.out.println("PriceProducer-------------> " + value);
                return value;
            });
    }*/

    @Outgoing("price")
    public Flowable<Price> producePrice() {
        return Flowable.interval(5, TimeUnit.SECONDS)
            .map(it->  {
                long value = (long) random.nextInt(4000);
                System.out.println("PriceProducer-------------> " + value);
                Price p = new Price();
                p.setName("BTC");
                p.setPrice(value);
                return p;
            });
    }
}
