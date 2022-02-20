package com.ucalgary.careeradvice.model;

public class User {
    private final int id;
    private final String username;
    private final String fname;
    private final String lname;
    private final String phone_no;
    private final String email;
    private final String address;
    private final String sin;
    private final String applicationName;
    private final String applicationStatus;


    public User(int id, String username, String fname, String lname, String phone_no, String email, String address, String sin, String applicationName, String applicationStatus) {
        this.id = id;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.phone_no = phone_no;
        this.email = email;
        this.address = address;
        this.sin = sin;
        this.applicationName = applicationName;
        this.applicationStatus = applicationStatus;
    }

    // Note: must have getters for local variables or you'll get a 406 error
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getSin() {
        return sin;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }
}
