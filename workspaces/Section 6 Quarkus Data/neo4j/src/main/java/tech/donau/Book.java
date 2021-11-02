package tech.donau;

import org.neo4j.driver.types.Node;

public class Book {
    
    public String title;
    public int pages;

    public Book(){}

    public Book(String title, int pages){
        this.title=title;
        this.pages=pages;
    }

    public static Book from(Node n) {
        Book b = new Book();

        b.setTitle(n.get("title").asString());
        b.setPages(n.get("pages").asInt());

        return b;

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
