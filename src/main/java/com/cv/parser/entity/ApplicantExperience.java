package com.cv.parser.entity;

import java.util.Arrays;

import com.cv.parser.helper.WorkExperienceHelper;

public class ApplicantExperience {

    public ApplicantExperience() {
	
    }
    
    private int id;
    private WorkExperienceHelper[] experience; // an applicant may have more than one experience
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkExperienceHelper[] getExperience() {
        return experience;
    }

    public void setExperience(WorkExperienceHelper[] experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
	return "ApplicantExperience [id=" + id + ", experience=" + Arrays.toString(experience) + "]";
    }
}
