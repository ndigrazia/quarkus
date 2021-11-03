package tech.donau;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.reactivex.Flowable;

@ApplicationScoped
public class PriceProducer {
    
    Random random = new Random();

    @Outgoing("price")
    public Flowable<Integer> producePrice() {
        return Flowable.interval(5, TimeUnit.SECONDS)
            .map(it->  random.nextInt(4000));
    }

}
