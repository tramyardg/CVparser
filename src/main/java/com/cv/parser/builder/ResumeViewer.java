package com.cv.parser.builder;

import java.util.List;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantEducation;
import com.cv.parser.entity.ApplicantExperiences;
import com.cv.parser.entity.ApplicantSkills;

public class ResumeViewer implements ResumeBuilder {

    private Resume resume;
   
    private List<Applicant> applicant;
    private List<ApplicantExperiences> experiences;
    private List<ApplicantEducation> education;
    private List<ApplicantSkills> skills;
    
    public ResumeViewer(List<Applicant> applicant, List<ApplicantExperiences> experiences,
	    List<ApplicantEducation> education, List<ApplicantSkills> skills) {
	this.applicant = applicant;
	this.experiences = experiences;
	this.education = education;
	this.skills = skills;
	
	resume = new Resume();
    }

    @Override
    public ResumeBuilder buildApplicant() {
	resume.setApplicant(applicant);
	return this;
    }

    @Override
    public ResumeBuilder buildApplicantExperiences() {
	resume.setExperiences(experiences);
	return this;
    }

    @Override
    public ResumeBuilder buildApplicantEducation() {
	resume.setEducation(education);
	return this;
    }

    @Override
    public ResumeBuilder buildApplicantSkills() {
	resume.setSkills(skills);
	return this;
    }

    @Override
    public Resume build() {
	return resume;
    }
}
