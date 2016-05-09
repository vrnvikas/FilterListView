package com.onthespot.vikaskumar.filterlistview;

/**
 * Created by Vikas Kumar on 04-05-2016.
 */
public class BookCollection {
    private String name;
    private String author;
    private String publisher;
    private int image;

    public BookCollection(String name, String author, String publisher,int image) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.image = image;
    }

    public int getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPublisher() {
        return this.publisher;
    }
}
