package com.cv.parser.applicant;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantExperiences;

import java.util.ArrayList;
import java.util.List;

/**
 * Fetches data to be stored in {@link ApplicantExperiences} The result would be
 * a list of ApplicantExperiences. For instance, if you have more than one
 * applicants. You'll get:
 * <p>
 * ApplicantExperiencesObject = { ApplicantExperiences [id=1, experience=...],
 * ApplicantExperiences [id=2, experience=...], ApplicantExperiences [id=3,
 * experience=...] }
 *
 * @author tramyardg
 */
class ParseApplicantExperience {

    private List<ApplicantDocument> applicantDocument;
    private List<ApplicantExperiences> applicantExperienceList = new ArrayList<>();
    private ParserHelper helper = new ParserHelper();

    ParseApplicantExperience(List<ApplicantDocument> applicantDocument) {
        this.applicantDocument = applicantDocument;
    }

    List<ApplicantExperiences> getApplicantExperience() {
        return applicantExperienceList;
    }

    private String findWorkExperiences(String line) {
        ParserHelper parser = new ParserHelper();
        /*
         * Algorithm: copy texts from starting index of desired section to the index
         * of the section that follows.
         *
         * Example: section indexes [24, 355, 534, 669] index of experience
         * section is 355 therefore, the work experience we get starts from 355
         * and ends with 534. This is where substring becomes useful.
         *
         */
        int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
        if (indexOfExperience != -1) {
            List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
            String texts = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
            return helper.getSectionContent(indexOfExperience, listOfSectionIndexes, texts);
        }
        return null;
    }

    void setApplicantExperiences() {
        for (ApplicantDocument ad : applicantDocument) {
            ApplicantExperiences applicantExperience = new ApplicantExperiences();
            applicantExperience.setId(ad.getId());
            applicantExperience.setExperience(findWorkExperiences(ad.getLine()));
            this.applicantExperienceList.add(applicantExperience);
        }
    }
}
