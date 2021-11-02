package tech.donau;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.neo4j.driver.async.AsyncSession;

import io.vertx.ext.web.Session;

@Path("/books")
public class BookResource {

    @Inject
    Driver driver;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> hello() {
        AsyncSession session = driver.asyncSession();
        return session.runAsync("MATCH (b:Book) RETURN b ORDER BY b.title")
            .thenCompose(cursor-> cursor.listAsync(record-> Book.from(record.get("b").asNode())))
            .thenCompose(books-> session.closeAsync().thenApply(it->books))
            .thenApply(Response::ok)
            .thenApply(Response.ResponseBuilder::build);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CompletionStage<Response> hello(Book book) {
        AsyncSession session = driver.asyncSession();
        return session.writeTransactionAsync(trx-> 
            trx.runAsync("CREATE (b:Book {title: $title, pages: $pages}) RETURN b", 
                Values.parameters("title",book.getTitle(), "pages", book.getPages()))
            .thenCompose(fn-> fn.singleAsync()) 
        ).thenApply(record-> Book.from(record.get("b").asNode()))
        .thenCompose(createBook-> session.closeAsync().thenApply(it->createBook))
        .thenApply(Response::ok)
        .thenApply(Response.ResponseBuilder::build);
    }


}