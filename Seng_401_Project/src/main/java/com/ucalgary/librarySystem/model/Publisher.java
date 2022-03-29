package com.ucalgary.librarySystem.model;

public class Publisher {
    private String Name;
    private String Address;
    private String PhoneNumber;
    
    public Publisher(String name, String address, String phoneNumber){
        this.Name=name;
        this.Address=address;
        this.PhoneNumber=phoneNumber;
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
