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

    private String findWorkExperience(int id, String line) {
	ParserHelper parser = new ParserHelper();	
	/* 
	 * copy texts starting from experience section index to the following section index
	 * experience index is LESS THAN the following section index, therefore
	 * 
	 * Example:
	 *  section indexes [24, 355, 534, 669]
	 *  index of experience section = 355
	 *  therefore, the following section index would be 534
	 *  we can get the texts that encompasses experience section
	 *  by substring => (indexOfExperience, beginIndexOfFollowingSection)
	 *  
	 */
	int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
	int nextSectionIndex = 0; // index that follows experience section
	for (int index = 0; index < parser.getIndexesOfSection(line).size(); index++) {
	    if (parser.getIndexesOfSection(line).get(index) == indexOfExperience) {
		nextSectionIndex = parser.getIndexesOfSection(line).get(index + 1);
		break;
	    }
	}	
	// logger.info(line.substring(indexOfExperience, nextSectionIndex));
	String experienceText = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
	// TODO call WorkExperienceHelper and parse the line
	return experienceText.substring(indexOfExperience, nextSectionIndex);
    }

}
