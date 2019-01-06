package com.cv.parser.entity;

public class ApplicantEducation {

    public ApplicantEducation() {
	
    }
    
    private int id;
    // store educations as String for now
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

    public String oldToString() {
	return "ApplicantEducation [id=" + id + ", education=" + education + "]";
    }
    
    @Override
    public String toString() {
	return education;
    }
}
