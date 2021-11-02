package org.acme.vertx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.data.Fruit;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;

@Path("/fruits")
public class FruitResource {
    
    @Inject
    MySQLPool pool;

    public void onStart(@Observes StartupEvent e) {
        pool.query("DROP TABLE IF EXISTS fruits").execute()
            .flatMap(it -> pool.query("CREATE TABLE fruits (name text, calories int   )").execute())
            .flatMap(it -> pool.query("INSERT INTO fruits VALUES ('Orange', 200)").execute())
            .flatMap(r -> pool.query("INSERT INTO fruits VALUES ('Pear', 100)").execute())
            .await().indefinitely();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Fruit>> hello() {
        return pool.query("SELECT *FROM fruits").execute()
            .map(rows ->  {
                final List<Fruit> l = new ArrayList<Fruit>();
                for(Row row: rows) 
                    l.add(Fruit.from(row));
                return l;
            });
    }
}