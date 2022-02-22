package com.ucalgary.careeradvice.model;

public class Library {
    private int ID;
    private String Name; 
    private String Address;
    private String PhoneNumber;

    public Library(){}

    public Library(int id, String name, String address, String phoneNumber){
        this.ID=id;
        this.Name=name;
        this.Address=address;
        this.PhoneNumber=phoneNumber;
    }
}
