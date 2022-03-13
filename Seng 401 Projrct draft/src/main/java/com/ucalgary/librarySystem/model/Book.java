package com.ucalgary.librarySystem.model;

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

    public Book(int bookID, int isbn, String name, String description, String category, int year, String author, String publisher, String section, int location){
        this.BookID=bookID;
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

    public String getName(){
        return this.Name;
    }

    public String getAuthor(){
        return this.Author;
    }

    public String getDescription(){
        return this.Description;
    }

    public int getYear(){
        return this.Year;
    }

    public String getSectionName(){
        return this.SectionName;
    }

    public String getPublisher()
    {
        return this.Publisher;
    }

    public int getLocation()
    {
        return this.location;
    }

    public String getCategory()
    {
        return this.Category;
    }

    public int getBookID()
    {
        return this.BookID;
    }

    public static class Builder {
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

        public Builder(Book book) {
            this.BookID=book.BookID;
        this.ISBN=book.ISBN;
        this.Name=book.Name;
        this.Description=book.Description;
        this.Category=book.Category;
        this.Year=book.Year;
        this.Author=book.Author;
        this.Publisher=book.Publisher;
        this.SectionName=book.SectionName;
        this.location=book.location;
        }

       

        public Book build() {
            return new Book(BookID, ISBN, Name, Description, Category, Year,  Author, Publisher, SectionName, location);
        }
    }



    
}
