package healthcheck;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class LivenessCheck implements HealthCheck {
    
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder()
            .name("Quarkus serve")
            .up() //.down()
            .withData("mavenPlugin", "1.2.6")
            .withData("systemTime", Long.toString(new Date().getTime()))
            .build();
    }

}
