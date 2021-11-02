package tech.donau;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.runtime.StartupEvent;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.vertx.axle.mysqlclient.MySQLPool;
import io.vertx.axle.sqlclient.Row;

import tech.donau.data.Book;

@Path("/books")
public class BookResource {

    @Inject
    MySQLPool pool;

    public void onStart(@Observes StartupEvent e) {
        pool.query("DROP TABLE IF EXISTS Books")
            .thenCompose(it-> pool.query("CREATE TABLE Books(title text, pages int)"))
            .thenCompose(it-> pool.query("INSERT INTO Books VALUES('text',96)"))
            .toCompletableFuture()
            .join();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<List<Book>> hello() {
        return pool.query("select * from Books")
            .thenApply(rows ->  {
                final List<Book> l = new ArrayList<Book>();
                for(Row row: rows) 
                    l.add(Book.from(row));
                return l;
            })
            .thenApply(books -> {
                Book o = new Book();

                o.pages = 67;
                o.title = "other";

                books.add(o);

                return books;
            });
    }
}