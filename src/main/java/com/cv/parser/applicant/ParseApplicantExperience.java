package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantExperience;
import com.cv.parser.helper.ParserHelper;

/**
 * Fetches data to be stored in {@link ApplicantExperience}
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class ParseApplicantExperience {

    Logger logger = LoggerFactory.getLogger(ParseApplicantExperience.class);

    List<ApplicantDocument> applicantDocument = new ArrayList<>();
    List<ApplicantExperience> applicantExperienceList = new ArrayList<>();

    public ParseApplicantExperience(List<ApplicantDocument> applicantDocument) {
	this.applicantDocument = applicantDocument;
    }

    public void setApplicantExperience() {
	for (ApplicantDocument ad : applicantDocument) {
	    ApplicantExperience applicantExperience = new ApplicantExperience();
	    applicantExperience.setId(ad.getId());
	    applicantExperience.setExperience(findWorkExperiences(ad.getId(), ad.getLine()));
	    this.applicantExperienceList.add(applicantExperience);
	}
    }

    public List<ApplicantExperience> getApplicantExperience() {
	return applicantExperienceList;
    }	

    private String findWorkExperiences(int id, String line) {
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
	String experiencesText = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
	return experiencesText.substring(indexOfExperience, nextSectionIndex);
    }
}
