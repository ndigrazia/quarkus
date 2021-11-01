package tech.donau;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import io.quarkus.infinispan.client.Remote;
import tech.donau.data.Book;

@Path("/books")
public class BookResource {

    @Inject
    @Remote("books")//Create the books cache on infinispan
    RemoteCache<String, Book> remoteCache;

    @Inject
    RemoteCacheManager manager;

    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Set<String> caches() {
        System.out.println("------------------> manager.getCacheNames()");
        //return remoteCache.get("hello");
        return manager.getCacheNames();
    }*/
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Book getBook(@QueryParam("id") String id) {
        return remoteCache.get(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addBook(Book b) {
       remoteCache.put(b.getId(), b);
       return b;
    }
}