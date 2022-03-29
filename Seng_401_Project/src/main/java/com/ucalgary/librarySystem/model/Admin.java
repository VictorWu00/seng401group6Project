package com.ucalgary.librarySystem.model;

public class Admin {
    private int ID;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private String DateOfBirth;
    private String Email;
    private String Password;
    private int Library_ID;

    public Admin() {
    }

    public Admin(int id, String name, String address, String phone, String dateOfBirth, String email, String password, int library_ID) {
        this.ID = id;
        this.Name=name;
        this.Address=address;
        this.PhoneNumber=phone;
        this.DateOfBirth=dateOfBirth;
        this.Email = email;
        this.Password = password;
        this.Library_ID=library_ID;
    }

    public void setID(int id) {
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

    public void setLibraryID(int libraryID) {
        this.Library_ID = libraryID;
    }

    public String getEmail(){
        return this.Email;
    }

    public String getPassword(){
        return this.Password;
    }

    public int getAdminID(){
        return this.ID;
    }

    public String getAdminName(){
        return this.Name;
    }

    public String getAdminAddress(){
        return this.Address;
    }

    public String getAdminPhone(){
        return this.PhoneNumber;
    }

    public String getAdminBirth(){
        return this.DateOfBirth;
    }

    public String getAdminEmail(){
        return this.Email;
    }

    public int getLibraryID(){
        return this.Library_ID;
    }
}
