package com.onthespot.vikaskumar.filterlistview;

/**
 * Created by Vikas Kumar on 04-05-2016.
 */
public class BookCollection {
    private String name;
    private String author;
    private String publisher;
    private int image;


    public BookCollection(String rank, String country, String population,int image) {
        this.name = rank;
        this.author = country;
        this.publisher = population;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
