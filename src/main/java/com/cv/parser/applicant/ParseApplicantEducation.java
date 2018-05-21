package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantEducation;

/**
 * This is for storing data in {@link} ApplicantEducation object|entity;
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class ParseApplicantEducation {


    Logger logger = LoggerFactory.getLogger(ParseApplicantEducation.class);
    
    List<ApplicantDocument> appDocList = new ArrayList<>();

    public ParseApplicantEducation(List<ApplicantDocument> appDocList) {
	this.appDocList = appDocList;
    }

    public void setApplicantEducation() {
	
    }

    public List<ApplicantEducation> getApplicantEducation() {
	return null;
    }    
}
