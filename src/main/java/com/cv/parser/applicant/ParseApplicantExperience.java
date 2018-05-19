package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	    ae.setExperience(findWorkExperience(ad.getId(), ad.getLine()));
	    this.applicantExperience.add(ae);
	}
    }

    public List<ApplicantExperience> getApplicantExperience() {
	return applicantExperience;
    }

    private WorkExperienceHelper[] findWorkExperience(int id, String line) {
	ParserHelper parser = new ParserHelper();
	logger.info(id + ": section indexes of this resume => "+parser.getIndexesOfSection(line).toString());
	logger.info(id + ": index of experience section in this =>"+parser.getIndexOfThisSection(RegEx.EXPERIENCE, line));
	// copy texts starting from experience section index to the following section index
	// experience index < following section index
	return null;
    }

}
