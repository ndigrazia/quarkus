package org.acme.jms;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Session;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class PriceProducer implements Runnable {

    Random random = new Random();
    ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();

    @Inject
    ConnectionFactory factory;

    public void onStart(@Observes StartupEvent  event) {
        System.out.println("PriceProducer-------------------------------->>>>>>>>>>>>> onStart");
        schedule.scheduleWithFixedDelay(this, 0L, 5L, TimeUnit.SECONDS);
    }

    public void onStop(@Observes ShutdownEvent event) {
        schedule.shutdownNow();
    }

    public void run() {
        try {
            JMSContext context = factory.createContext(Session.AUTO_ACKNOWLEDGE);
            String p = Integer.toString(random.nextInt(4000));
            context.createProducer().send(context.createQueue("prices"), p);
            System.out.println("PriceProducer-------------------------------->>>>>>>>>>>>>" + p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
