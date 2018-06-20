package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantSkill;

public class ParseApplicantSkill {

    Logger logger = LoggerFactory.getLogger(ParseApplicantSkill.class);

    List<ApplicantDocument> applicantDocumentList = new ArrayList<>();
    List<ApplicantSkill> applicantSkillList = new ArrayList<>();

    public ParseApplicantSkill(List<ApplicantDocument> applicantDocumentList) {
	this.applicantDocumentList = applicantDocumentList;
    }

    public List<ApplicantSkill> getApplicantSkillList() {
	return applicantSkillList;
    }

    public void setApplicantSkills() {
	for (ApplicantDocument applicantDocument : applicantDocumentList) {
	    ApplicantSkill applicantSkill = new ApplicantSkill();
	    applicantSkill.setId(applicantDocument.getId());
	    applicantSkill.setSkills(findApplicantSkills(applicantDocument.getLine()));
	    this.applicantSkillList.add(applicantSkill);
	}
    }

    private String findApplicantSkills(String line) {
	ParserHelper parser = new ParserHelper();
	int indexOfSkillsSection = parser.getIndexOfThisSection(RegEx.SKILLS, line);

	if (indexOfSkillsSection != -1) {
	    List<Integer> sectionIndexes = parser.getIndexesOfSection(line);
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

}
