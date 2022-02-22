package com.ucalgary.careeradvice.model;

public class Author {
    private String Name;
    private String Address;
    private String PhoneNumber;

    public Author(){

    }

    public Author(String name, String address, String phone){
        this.Name=name;
        this.Address=address;
        this.PhoneNumber=phone;
    }
}
