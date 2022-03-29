package com.ucalgary.librarySystem.model;

public class Review {
    private String Description;
    private String Rating;
    private String Date;
    private int bookID;
    private String bookName;

    public Review(String Description, String Rating, String Date)
{
    this.Description = Description;
    this.Rating = Rating;
    this.Date = Date;
}

    public Review(int bookID, String bookName, String Description, String Rating, String Date)
    {
        this.bookID = bookID;
        this.bookName = bookName;
        this.Description = Description;
        this.Rating = Rating;
        this.Date  =Date;
    }

    public String getDescription()
    {
        return this.Description;
    }

    public String getRating()
    {
        return this.Rating;
    }

    public String getDate()
    {
        return this.Date;
    }

    public int getBookID()
    {
        return this.bookID;
    }

    public String getBookName()
    {
        return this.bookName;
    }
}

