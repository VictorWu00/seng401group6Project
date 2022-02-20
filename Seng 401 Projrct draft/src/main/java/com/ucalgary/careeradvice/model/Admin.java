package com.ucalgary.careeradvice.model;

public class Admin {
    private int id;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
