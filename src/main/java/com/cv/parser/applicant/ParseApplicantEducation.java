package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantEducation;

/**
 * This is for storing data in {@link} ApplicantEducation object|entity;
 * The result would be a list of ApplicantEducation. For instance,
 * if you have more than one applicants. You'll get:
 * 
 * ApplicantEducationObject = {
 *  ApplicantEducation [id=1, education=...],
 *  ApplicantEducation [id=2, education=...],
 *  ApplicantEducation [id=3, education=...]
 * }
 * 
 * @author tramyardg
 *
 */
public class ParseApplicantEducation {

    Logger logger = LoggerFactory.getLogger(ParseApplicantEducation.class);
    
    List<ApplicantDocument> applicantDocument = new ArrayList<>();
    List<ApplicantEducation> applicantEducationList = new ArrayList<>();

    public ParseApplicantEducation(List<ApplicantDocument> applicantDocument) {
	this.applicantDocument = applicantDocument;
    }

    public void setApplicantEducations() {
	for (ApplicantDocument ad : applicantDocument) {
	    ApplicantEducation applicantEducation = new ApplicantEducation();
	    applicantEducation.setId(ad.getId());
	    applicantEducation.setEducation(findEducations(ad.getLine()));
	    this.applicantEducationList.add(applicantEducation);
	}
    }
    
    public List<ApplicantEducation> getApplicantEducation() {
	return applicantEducationList;
    } 

    private String findEducations(String line) {
	ParserHelper parser = new ParserHelper();
	// just like findWorkExperiences
	int indexOfEducation = parser.getIndexOfThisSection(RegEx.EDUCATION, line);
	if (indexOfEducation != -1) {
	    int nextSectionIndex = 0;
	    List<Integer> listOfSectionIndexes = parser.getIndexesOfSection(line);
	    String educationsText = line.replaceFirst(RegEx.EDUCATION.toString(), "");
	    for (int index = 0; index < listOfSectionIndexes.size(); index++) {
		if (listOfSectionIndexes.get(index) == indexOfEducation) {
		    // if education is the last section, then there is no
		    // nextSectionIndex
		    if (index == listOfSectionIndexes.size() - 1) {
			return educationsText.substring(indexOfEducation);
		    } else {
			nextSectionIndex = listOfSectionIndexes.get(index + 1);
			break;
		    }
		}
	    }
	    return educationsText.substring(indexOfEducation, nextSectionIndex);
	}
	return null;
    }   
}
