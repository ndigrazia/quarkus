package tech.donau.data;

import java.util.Objects;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

public class Book {
    
    private String id;
    private String title;
    private String author;

    public Book(){
    }

    @ProtoFactory
    public Book(String id, String title, String author) {
        this.id = Objects.requireNonNull(id);
        this.title = Objects.requireNonNull(title);
        this.author = Objects.requireNonNull(author);
    }

    @ProtoField(number = 1)
    public String getId() {
        return this.id;
    }

    @ProtoField(number = 2)
    public String getTitle() {
        return this.title;
    }

    @ProtoField(number = 3)
    public String getAuthor() {
        return this.author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
