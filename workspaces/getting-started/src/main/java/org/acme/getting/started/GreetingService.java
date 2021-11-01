package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class GreetingService {
    
    @ConfigProperty(name = "greeting.name")
    String name;

    @ConfigProperty(name = "greeting.suffix", defaultValue = "!")
    String suffix;

    @ConfigProperty(name = "greeting.preffix")
    Optional<String> preffix;

    public String sayHello() {
        return preffix.orElse("_") + name + suffix;
    }

    public String sayHello(String name) {
        return String.format("Hello %s, your id is %s",
                name,
                UUID.randomUUID().toString()
        );
    }
}
