package com.ucalgary.librarySystem.model;

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

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }

    public String getName(){
        return this.Name;
    }

    public void setAddress(String Address){
        this.Address = Address;
    }

    public String getAddress(){
        return this.Address;
    }

    public void setPhoneNumber(String num){
        this.PhoneNumber = num;
    }

    public String getPhoneNumber(){
        return this.PhoneNumber;
    }
}
