package tech.donau.data;

public class Book {
    
    public String title;

    public int pages;

    public Book(){}


    public  Book( String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
  
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }



}
