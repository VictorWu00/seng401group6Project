package com.ucalgary.librarySystem.model;

public class Review {
    private String Description;
    private String Rating;
    private String Date;

    public Review(String Description, String Rating, String Date)
{
    this.Description = Description;
    this.Rating = Rating;
    this.Date = Date;
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
}

