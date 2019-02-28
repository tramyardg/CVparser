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
         * Algorithm: copy texts starting from experience section index to the
         * following section index experience index is LESS THAN the following
         * section index, therefore
         *
         * Example: section indexes [24, 355, 534, 669] index of experience
         * section = 355 therefore, the following section index would be 534 we
         * can get the texts that encompasses experience section by substring =>
         * (indexOfExperience, beginIndexOfFollowingSection)
         *
         */
        int indexOfExperience = parser.getIndexOfThisSection(RegEx.EXPERIENCE, line);
        if (indexOfExperience != -1) {
            int nextSectionIndex = 0; // index that follows experience section
            List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
            String experiencesText = line.replaceFirst(RegEx.EXPERIENCE.toString(), "");
            return helper.getSectionContent(indexOfExperience, listOfSectionIndexes, experiencesText, nextSectionIndex);
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
