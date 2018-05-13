package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.model.ApplicantDocument;

public class DocumentDetails {
    Logger logger = LoggerFactory.getLogger(DocumentDetails.class);

    List<String> allContents = new ArrayList<String>();
    List<ApplicantDocument> applicantList = new ArrayList<ApplicantDocument>();
    
    public DocumentDetails(List<String> allContents) {
	this.allContents = allContents;
    }

    public void storeDetails() {
	for (int index = 0; index < allContents.size(); index++) {	    
	    this.applicantList.add(new ApplicantDocument(index, allContents.get(index)));
	}
    }

    public List<ApplicantDocument> getApplicantList() {
        return applicantList;
    }
}

