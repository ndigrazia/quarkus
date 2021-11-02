package tech.donau;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tech.donau.data.Book;
import tech.donau.services.BookService;

@Path("/books")
public class BookResource {


    @Inject
    BookService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> hello() {
        return service.get();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book hello(Book book) {
        service.add(book);
        return book;
    }

}