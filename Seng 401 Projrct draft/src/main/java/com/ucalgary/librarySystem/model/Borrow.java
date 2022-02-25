package com.ucalgary.librarySystem.model;

import javax.management.loading.PrivateClassLoader;

public class Borrow {
    private int User_ID;
    private int Book_ID;
    private String Start_date;
    private String End_date;

    public Borrow(){}

    public Borrow(int user_id, int book_id, String sDate, String eDate){
        this.User_ID=user_id;
        this.Book_ID=book_id;
        this.Start_date=sDate;
        this.End_date=eDate;
    }
}
