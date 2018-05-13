package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoreDetails {
    Logger logger = LoggerFactory.getLogger(StoreDetails.class);

    List<String> allContents = new ArrayList<String>();
    List<JobApplicant> jobApplicantList = new ArrayList<JobApplicant>();
    
    public StoreDetails(List<String> allContents) {
	this.allContents = allContents;
    }

    public void storeDetails() {
	for (int index = 0; index < allContents.size(); index++) {	    
	    this.jobApplicantList.add(new JobApplicant(index, allContents.get(index)));
	}
    }

    public List<JobApplicant> getJobApplicantList() {
        return jobApplicantList;
    }

    public void setJobApplicantList(List<JobApplicant> jobApplicantList) {
        this.jobApplicantList = jobApplicantList;
    }
}

