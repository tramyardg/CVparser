package com.cv.parser.builder;

public class ResumeDirector {

    private ResumeBuilder builder;

    public ResumeDirector(final ResumeBuilder builder) {
	this.builder = builder;
    }

    public Resume construct() {
	return builder.buildApplicant().buildApplicantExperiences().buildApplicantEducation().buildApplicantSkills()
		.build();
    }

}
