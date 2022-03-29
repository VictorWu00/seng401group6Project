package com.ucalgary.librarySystem.model;

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

    public String getName()
    {
        return this.Name;
    }

    public String getAddress()
    {
        return this.Address;

    }

    public String getPhoneNumber()
    {
        return PhoneNumber;
    }
}
