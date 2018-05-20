package com.cv.parser.entity;

public class ApplicantExperience {

    public ApplicantExperience() {
	
    }
    
    private int id;
    //private WorkExperienceHelper[] experience; // an applicant may have more than one experience
    private String experience; // store it as string for now
    // change to List<WorkExperienceHelper> only if managed to parse and store in WorkExperienceHelper
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experienceData) {
	// WorkExperienceHelper[] experienceArr;
	// parse experience from experienceData
	// store them in 
        this.experience = experienceData;
    }

    @Override
    public String toString() {
	return "ApplicantExperience [id=" + id + ", experience=" + experience + "]";
    }
}
