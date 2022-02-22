package com.ucalgary.careeradvice.model;

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
}
