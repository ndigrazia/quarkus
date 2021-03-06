package metrics;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/")
public class NumberResource {
    
        private long highestPrimeNumberSoFar = 2;
           
        @GET
        @Timed(description = "How long random numbers return", unit = MetricUnits.MILLISECONDS, name = "numberTimed")
        @Path("/random")
        @Counted(description = "How many random numbers return", name = "numberCounted")
        @Produces(MediaType.TEXT_PLAIN)
        public String getNumbers() throws InterruptedException {
            Random r = new Random();
            int i = r.nextInt(100);
            Thread.sleep(i*100);
            return i + "";
        }

        @GET
        @Path("/{number}")
        @Produces(MediaType.TEXT_PLAIN)
        @Counted(name = "performedChecks", description = "How many primality checks have been performed.")
        @Timed(name = "checksTimer", description = "A measure of how long it takes to perform the primality test.", unit = MetricUnits.MILLISECONDS)
        public String checkIfPrime(@PathParam(value = "number") long number) {
            if (number < 1) {
                return "Only natural numbers can be prime numbers.";
            }
            if (number == 1) {
                return "1 is not prime.";
            }
            if (number == 2) {
                return "2 is prime.";
            }
            if (number % 2 == 0) {
                return number + " is not prime, it is divisible by 2.";
            }
            for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
                if (number % i == 0) {
                    return number + " is not prime, is divisible by " + i + ".";
                }
            }
            if (number > highestPrimeNumberSoFar) {
                highestPrimeNumberSoFar = number;
            }
            return number + " is prime.";
        }
    
        @Gauge(name = "highestPrimeNumberSoFar", unit = MetricUnits.NONE, description = "Highest prime number so far.")
        public Long highestPrimeNumberSoFar() {
            return highestPrimeNumberSoFar;
        }
    
}