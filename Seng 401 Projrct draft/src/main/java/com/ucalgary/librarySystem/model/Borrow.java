package com.ucalgary.librarySystem.model;



public class Borrow {
    private int UserID;
    private int BookID;
    private String sd ;
    private String ed;
    private String BookName;
    private String sDate;
    private String eDate;

    public Borrow()
    {}

    public Borrow(int Book_ID, String BookName, String Start_date, String End_date){
        this.BookID=Book_ID;
        this.BookName = BookName;
        this.sDate=Start_date;
        this.eDate=End_date;
    }

    public int getUserID()
    {
        return this.UserID;
    }

    public int getBookID()
    {
        return this.BookID;
    }

    public String getStartDate()
    {
        return this.sd;
    }

    public String getEndDate()
    {
        return this.ed;
    }

    public String getBookName()
    {
        return this.BookName;
    }

    public String getsDate()
    {
        return this.sDate;
    }

    public String geteDate()
    {
        return this.eDate;
    }
}
