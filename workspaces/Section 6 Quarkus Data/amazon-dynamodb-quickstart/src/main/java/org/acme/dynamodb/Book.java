package org.acme.dynamodb;

import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public class Book {

    private String title;
    private Integer pages;

    public static Book from(Map<String, AttributeValue> attributes) {
        Book b = new Book(attributes.get(BookService.TITLE_COL).s(), 
            Integer.valueOf(attributes.get(BookService.PAGES_COL).n()));

        return b;
    }

    public Book() {}

    public Book(String title, Integer pages) {
        this.title = title;
        this.pages = pages;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return this.pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }


}


