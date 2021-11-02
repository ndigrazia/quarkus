package org.acme.mongodb.panache;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;

@MongoEntity(collection = "other_books")
public class Book extends PanacheMongoEntity {

    public String title;

    @BsonProperty(value = "pages")
    public int pages;

    public Book() {}

    /*public static Book findByTitle(String title) {
        return find(query, params)
    }*/

}
