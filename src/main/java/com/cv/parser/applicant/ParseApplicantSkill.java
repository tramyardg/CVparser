package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantSkills;

/**
 * Fetches data to be stored in {@link ApplicantSkills}
 * The result would be a list of ApplicantSkills. For instance,
 * if you have more than one applicants. You'll get:
 * 
 * ApplicantExperiencesObject = {
 *  ApplicantSkills [id=1, skills=...],
 *  ApplicantSkills [id=2, skills=...],
 *  ApplicantSkills [id=3, skills=...]
 * }
 * 
 * @author tramyardg
 *
 */
public class ParseApplicantSkill {
    List<ApplicantDocument> applicantDocumentList = new ArrayList<>();
    List<ApplicantSkills> applicantSkillList = new ArrayList<>();

    public ParseApplicantSkill(List<ApplicantDocument> applicantDocumentList) {
	this.applicantDocumentList = applicantDocumentList;
    }

    public List<ApplicantSkills> getApplicantSkillList() {
	return applicantSkillList;
    }

    private String findApplicantSkills(String line) {
	ParserHelper parser = new ParserHelper();
	int indexOfSkillsSection = parser.getIndexOfThisSection(RegEx.SKILLS, line);

	if (indexOfSkillsSection != -1) {
	    List<Integer> sectionIndexes = parser.getAllSectionIndexes(line);
	    String skillsText = line.replaceFirst(RegEx.SKILLS.toString(), "");
	    int nextSectionIndex = 0;
	    for (int index = 0; index < sectionIndexes.size(); index++) {
		if (sectionIndexes.get(index) == indexOfSkillsSection) {
		    if (index == sectionIndexes.size() - 1) {
			return skillsText.substring(indexOfSkillsSection);
		    } else {
			nextSectionIndex = sectionIndexes.get(index + 1);
			break;
		    }
		}
	    }
	    return skillsText.substring(indexOfSkillsSection, nextSectionIndex);
	}
	return null;
    }
    
    public void setApplicantSkills() {
	for (ApplicantDocument applicantDocument : applicantDocumentList) {
	    ApplicantSkills applicantSkill = new ApplicantSkills();
	    applicantSkill.setId(applicantDocument.getId());
	    applicantSkill.setSkills(findApplicantSkills(applicantDocument.getLine()));
	    this.applicantSkillList.add(applicantSkill);
	}
    }

}
