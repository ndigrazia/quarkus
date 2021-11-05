package tech.donau.greeting;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.Message;

@ApplicationScoped
public class GreetingService {
    
    @ConsumeEvent(value = "greeting")
    public String onMessage(String name) {
        return "hello, " + name; 
    }

    /*@ConsumeEvent(value = "greeting")
    public Uni<String> onMessageAsyc(Message<String> name) {
        return Uni.createFrom().item("hello, " + name); 
    }*/
}
