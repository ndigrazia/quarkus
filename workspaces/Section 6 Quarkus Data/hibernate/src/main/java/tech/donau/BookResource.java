package tech.donau;

import java.util.List;
import java.util.Optional;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.resource.spi.ConfigProperty;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.search.mapper.orm.Search;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.StartupEvent;
import tech.donau.data.Author;
import tech.donau.data.Book;
import tech.repository.BookRepository;

@Path("/weather")
public class BookResource {

    @Inject
    EntityManager entityManager;

    @Inject
    BookRepository repository;

    @Inject
    TransactionManager manager;

    @Inject
    UserTransaction user;


    @Transactional
    public void onStart(@Observes StartupEvent se) throws InterruptedException  {
        Search.session(entityManager).massIndexer().startAndWait();
    }

    @POST
    @Path("/author")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void addAuthor(Author a) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        user.begin();
        Author.persist(a);
        user.commit();
        //user.rollback();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public List<Book> hello() {
        try {
            manager.setRollbackOnly();
        } catch (IllegalStateException | SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       // List<Book> books = entityManager.createQuery("select b from Book b", Book.class).getResultList();

       /*PanacheQuery<Book> query = Book.find("name", "sample");
       //query.page(Page.of(2, 50));
       query.page(Page.ofSize(50));
       List<Book> books = query.list();
       query.nextPage();
       books = query.list();*/

       //Book.listAll(Sort.by("name").ascending());

       //List<Book> books = Book.findAllBook();
       
       List<Book> books = repository.findAllBook();

        return books;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Book hello(Book b) {
        //Book.findById("123", LockModeType.PESSIMISTIC_WRITE);
        //Book.find("name", "sample").withLock(LockModeType.WRITE);
        
        Book.persist(b);
        //entityManager.persist(b);
        return b;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Path("/{id}")
    public long hello(@PathParam("id") long id) {
       // return Book.delete("id=?1", id);
      return repository.delete("id=?1", id);
    }

    @Path("/alter/search")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Author> getAuthors(@QueryParam("pattern") String pattern) { //, @QueryParam("size") Optional<Integer> size) {
       return  Search.session(entityManager).search(Author.class)
            .predicate(predicate->
                pattern ==null || pattern.trim().isEmpty() 
                    ? predicate.matchAll()
                    : predicate.simpleQueryString().fields("firstName", "lastName", "books.title").matching(pattern)
                )
            .sort(sort->sort.field("firstName_sort").then().field("lastName_sort"))
            //.fetchHits(size);
            .fetchAll().getHits();

    }

}