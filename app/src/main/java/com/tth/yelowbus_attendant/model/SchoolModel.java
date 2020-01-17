package com.tth.yelowbus_attendant.model;

public class SchoolModel {

    int id;
    String schoolName;

    public SchoolModel(int id, String schoolName) {
        this.id = id;
        this.schoolName = schoolName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
