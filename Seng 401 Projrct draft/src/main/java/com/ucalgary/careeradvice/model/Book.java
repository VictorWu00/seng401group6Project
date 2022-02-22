package com.ucalgary.careeradvice.model;

import java.util.function.BiConsumer;

public class Book {
    private int BookID;
    private int ISBN;
    private String Name;
    private String Description;
    private String Category;
    private int Year;
    private String Author;
    private String Publisher;
    private String SectionName;
    private int location;

    public Book(){

    }

    public Book(int bID, int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        this.BookID=bID;
        this.ISBN=isbn;
        this.Name=name;
        this.Description=description;
        this.Category=category;
        this.Year=year;
        this.Author=author;
        this.Publisher=publisher;
        this.SectionName=section;
        this.location=location;
    }

    
}
