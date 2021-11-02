package tech.donau.data;

import io.vertx.axle.sqlclient.Row;

public class Book {
    
    public String title;
    public int pages;

    public Book() {}

    public static Book from(Row r) {
        Book b= new Book();

        String t = r.getString("title");
        System.out.println("------------------------->" + t);
        b.title = t;

        Integer i =  r.getInteger("pages");
        System.out.println("------------------------->" + i);
        b.pages = i;

        return b;
    }

}
