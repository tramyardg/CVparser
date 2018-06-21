package com.cv.parser.builder;

public interface ResumeBuilder {

    public ResumeBuilder buildApplicant();
    public ResumeBuilder buildApplicantExperiences();
    public ResumeBuilder buildApplicantEducation();
    public ResumeBuilder buildApplicantSkills();
    public Resume build();
    
}
