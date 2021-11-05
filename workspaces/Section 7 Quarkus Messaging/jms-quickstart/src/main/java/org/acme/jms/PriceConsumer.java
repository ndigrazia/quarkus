package org.acme.jms;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;


@ApplicationScoped
public class PriceConsumer implements Runnable {
    
    @Inject
    ConnectionFactory factory;

    private String lastPrice;

    public String getLastPrice() {
        return lastPrice;
    }

    ExecutorService schedule = Executors.newSingleThreadExecutor();

    public void onStart(@Observes StartupEvent  event) {
        System.out.println("PriceConsumer-------------------------------->>>>>>>>>>>>> onStart");
        schedule.execute(this);
    }

    public void onStop(@Observes ShutdownEvent event) {
        schedule.shutdownNow();
    }

    public void run() {
        try {

            JMSContext context = factory.createContext(Session.AUTO_ACKNOWLEDGE);
            JMSConsumer consumer = context.createConsumer(context.createQueue("prices"));
            
            while(true) {
                Message m = consumer.receive();
                if(m==null) return;
                
                    String body = m.getBody(String.class);
                    lastPrice = body;
                    System.out.println("PriceConsumer-------------------------------->>>>>>>>>>>>>" + lastPrice);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
