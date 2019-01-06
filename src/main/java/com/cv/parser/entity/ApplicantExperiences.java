package com.cv.parser.entity;

public class ApplicantExperiences {

    public ApplicantExperiences() {
	
    }
    
    private int id;
    private String experiences;
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperience() {
        return experiences;
    }

    public void setExperience(String experienceData) {
        this.experiences = experienceData;
    }

    public String oldToString() {
	return "ApplicantExperiences [id=" + id + ", experiences=" + experiences + "]";
    }
    
    @Override
    public String toString() {
	return experiences;
    }
}
