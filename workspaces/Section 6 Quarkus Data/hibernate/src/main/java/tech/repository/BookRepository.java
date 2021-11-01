package tech.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import tech.donau.data.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book>{
    
    public List<Book> findAllBook() {
        return findAll().list();
    }

    public List<Book> findAllBookByName(String name) {
        return find("name", name).list();
    }
}
