package com.cv.parser.entity;

public class ApplicantEducation {

    public ApplicantEducation() {

    }

    private int id;
    private String education;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return education;
    }
}
