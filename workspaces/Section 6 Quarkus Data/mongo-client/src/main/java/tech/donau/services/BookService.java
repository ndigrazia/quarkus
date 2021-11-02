package tech.donau.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import tech.donau.data.Book;

@ApplicationScoped
public class BookService {
    
    @Inject
    MongoClient client;

    public void add(Book book) {
        MongoCollection<Document> collection= getCollection();
        Document d = new Document();
        d.put("title", book.getTitle());
        d.put("pages", book.getPages());
        collection.insertOne(d);
    }

    public List<Book> get() {
         FindIterable<Document> documents = getCollection().find();

         List<Book> books= new ArrayList<Book>();

         for(Document d: documents) {
                Book b = new Book();
                b.setPages(d.get("pages",Integer.class));
                b.setTitle(d.get("title", String.class));

                books.add(b);
         }
         
         return books;
    }

    private MongoCollection<Document> getCollection(){
        MongoCollection<Document> collection = client.getDatabase("books").getCollection("books");
        return collection;
    }
}
