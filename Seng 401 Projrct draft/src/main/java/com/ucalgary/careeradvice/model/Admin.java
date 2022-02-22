package com.ucalgary.careeradvice.model;

public class Admin {
    private String ID;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String DateOfBirth;
    private String Email;
    private String Password;
    private String Library_ID;

    public Admin() {
    }

    public Admin(String id, String name, String address, String phone, String dateOfBirth, String email, String password, String library_ID) {
        this.ID = id;
        this.Name=name;
        this.Address=address;
        this.PhoneNumber=phone;
        this.DateOfBirth=dateOfBirth;
        this.Email = email;
        this.Password = password;
        this.Library_ID=library_ID;
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
    
    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void setLibraryID(String libraryID) {
        this.Library_ID = libraryID;
    }
}
