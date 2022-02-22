package com.ucalgary.careeradvice.model;

public class User {
    private String ID;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String DateOfBirth;
    private String Email;
    private String Password;
    private String Balance;
    
    public User(){
        
    }

    public User(String ID, String Name, String Address, String PhoneNumber, String birth, String Email, String Password, String Balance) {
        this.ID=ID;
        this.Name=Name;
        this.Address=Address;
        this.PhoneNumber=PhoneNumber;
        this.DateOfBirth=birth;
        this.Email=Email;
        this.Password=Password;
        this.Balance=Balance;
    }
    
    public void setID(String id) {
        this.ID = id;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public void setBirth(String birth) {
        this.DateOfBirth = birth;
    }

    public void setPhone(String phone) {
        this.PhoneNumber = phone;
    }
    public void setBalance(String balance) {
        this.Balance = balance;
    }

    public void setUsername(String username) {
        this.Email = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
