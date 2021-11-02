package org.acme.mongodb.panache;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;

@MongoEntity(collection = "fruits")
public class Fruit extends PanacheMongoEntityBase {

    @BsonId
    public String name;

    @BsonProperty(value = "calories")
    public int calories;

    public Fruit() {}

    /*public static Book findByTitle(String title) {
        return find(query, params)
    }*/

}
