package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantExperience;
import com.cv.parser.helper.ParserHelper;
import com.cv.parser.helper.WorkExperienceHelper;

/**
 * Fetches data to be stored in {@link ApplicantExperience} 
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class ParseApplicantExperience {

    Logger logger = LoggerFactory.getLogger(ParseApplicantExperience.class);

    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();
    List<ApplicantExperience> applicantExperience = new ArrayList<ApplicantExperience>();

    public ParseApplicantExperience(List<ApplicantDocument> appDocList) {
	this.appDocList = appDocList;
    }

    public void setApplicantExperience() {
	for (ApplicantDocument ad : appDocList) {
	    ApplicantExperience ae = new ApplicantExperience();
	    ae.setId(ad.getId());
	    ae.setExperience(findWorkExperience(ad.getLine()));

	    this.applicantExperience.add(ae);
	}
    }

    public List<ApplicantExperience> getApplicantExperience() {
	return applicantExperience;
    }

    private WorkExperienceHelper[] findWorkExperience(String line) {
	WorkExperienceHelper[] experiences = null;
	ParserHelper parser = new ParserHelper();
	logger.info(parser.getIndexOfThisSection(RegEx.EXPERIENCE, line)+"");
	return experiences;
    }
    
}
