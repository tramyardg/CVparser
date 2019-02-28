package com.cv.parser.entity;

public class ApplicantSkills {

    public ApplicantSkills() {

    }

    private int id;
    private String skills;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return skills;
    }

}
