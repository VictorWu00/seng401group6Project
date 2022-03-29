package com.ucalgary.librarySystem.model;

public class Section {
    private String Name;
    private String AisleNumber;
    private int Library_ID;

    public Section(){}

    public Section(String name, String aNumber, int lID){
        this.Name=name;
        this.AisleNumber=aNumber;
        this.Library_ID=lID;
    }

    public String getName(){
        return Name;
    }

    public void setNmae(String Name){
        this.Name = Name;
    }

    public String getAisleNumber(){
        return AisleNumber;
    }

    public void setAisleNumber(String num){
        this.AisleNumber = num;
    }

    public int getLibraryID(){
        return Library_ID;
    }

    public void setLibraryID(int id){
        this.Library_ID = id;
    }
}
