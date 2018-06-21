package com.cv.parser.builder;

import java.util.List;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantEducation;
import com.cv.parser.entity.ApplicantExperiences;
import com.cv.parser.entity.ApplicantSkills;

public class Resume {

    public Resume() { }

    private List<Applicant> applicant;
    private List<ApplicantExperiences> experiences;
    private List<ApplicantEducation> education;
    private List<ApplicantSkills> skills;
    
    public List<Applicant> getApplicant() {
        return applicant;
    }

    public void setApplicant(List<Applicant> applicant) {
        this.applicant = applicant;
    }

    public List<ApplicantExperiences> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ApplicantExperiences> experiences) {
        this.experiences = experiences;
    }

    public List<ApplicantEducation> getEducation() {
        return education;
    }

    public void setEducation(List<ApplicantEducation> education) {
        this.education = education;
    }

    public List<ApplicantSkills> getSkills() {
        return skills;
    }

    public void setSkills(List<ApplicantSkills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
	return "Resume [applicant=" + applicant + ", experiences=" + experiences + ", education=" + education
		+ ", skills=" + skills + "]";
    }
}
