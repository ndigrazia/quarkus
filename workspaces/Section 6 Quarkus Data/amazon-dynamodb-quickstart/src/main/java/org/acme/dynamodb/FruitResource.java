package org.acme.dynamodb;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.inject.Inject;

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
public class FruitResource {

    @Inject
    BookService service;

    @POST
    public Book add(Book book) {
      service.add(book);
      return book;
    }       

    @GET
    public List<Book> get() {
        return service.findAll();
    }
}