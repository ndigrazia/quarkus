package healthcheck;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.health.Readiness;

import io.quarkus.runtime.StartupEvent;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class ReadinessCheck implements HealthCheck {
    
    public static boolean READY = false;

    public void onStart(@Observes StartupEvent event)  {

        new Thread(()-> {
            try {
                Thread.sleep(15000);
                READY=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).run();
        
    }

    public HealthCheckResponse call() {
        if(READY) 
            return HealthCheckResponse.up("ReadinessCheck");
        return HealthCheckResponse.down("ReadinessCheck");
    }

}
