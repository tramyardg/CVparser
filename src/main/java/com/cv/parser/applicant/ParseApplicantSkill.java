package com.cv.parser.applicant;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantSkills;

import java.util.ArrayList;
import java.util.List;

/**
 * Fetches data to be stored in {@link ApplicantSkills}
 * The result would be a list of ApplicantSkills. For instance,
 * if you have more than one applicants. You'll get:
 * <p>
 * ApplicantExperiencesObject = {
 * ApplicantSkills [id=1, skills=...],
 * ApplicantSkills [id=2, skills=...],
 * ApplicantSkills [id=3, skills=...]
 * }
 *
 * @author tramyardg
 */
class ParseApplicantSkill {
    private List<ApplicantDocument> applicantDocumentList;
    private List<ApplicantSkills> applicantSkillList = new ArrayList<>();
    private ParserHelper helper = new ParserHelper();

    ParseApplicantSkill(List<ApplicantDocument> applicantDocumentList) {
        this.applicantDocumentList = applicantDocumentList;
    }

    List<ApplicantSkills> getApplicantSkillList() {
        return applicantSkillList;
    }

    private String findApplicantSkills(String line) {
        ParserHelper parser = new ParserHelper();
        int indexOfSkillsSection = parser.getIndexOfThisSection(RegEx.SKILLS, line);
        if (indexOfSkillsSection != -1) {
            List<Integer> sectionIndexes = parser.getAllSectionIndexes(line);
            String texts = line.replaceFirst(RegEx.SKILLS.toString(), "");
            return helper.getSectionContent(indexOfSkillsSection, sectionIndexes, texts);
        }
        return null;
    }


    void setApplicantSkills() {
        for (ApplicantDocument applicantDocument : applicantDocumentList) {
            ApplicantSkills applicantSkill = new ApplicantSkills();
            applicantSkill.setId(applicantDocument.getId());
            applicantSkill.setSkills(findApplicantSkills(applicantDocument.getLine()));
            this.applicantSkillList.add(applicantSkill);
        }
    }

}
