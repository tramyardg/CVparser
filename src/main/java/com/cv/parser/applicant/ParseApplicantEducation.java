package com.cv.parser.applicant;

import com.cv.parser.RegEx;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantEducation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This is for storing data in {@link} ApplicantEducation object|entity;
 * The result would be a list of ApplicantEducation. For instance,
 * if you have more than one applicants. You'll get:
 * <p>
 * ApplicantEducationObject = {
 * ApplicantEducation [id=1, education=...],
 * ApplicantEducation [id=2, education=...],
 * ApplicantEducation [id=3, education=...]
 * }
 *
 * @author tramyardg
 */
class ParseApplicantEducation {
    private final static Logger LOG = LogManager.getLogger();

    private List<ApplicantDocument> applicantDocument;
    private List<ApplicantEducation> applicantEducationList = new ArrayList<>();
    private ParserHelper helper = new ParserHelper();

    ParseApplicantEducation(List<ApplicantDocument> applicantDocument) {
        this.applicantDocument = applicantDocument;
    }

    List<ApplicantEducation> getApplicantEducation() {
        return applicantEducationList;
    }

    private String findEducations(String line) {
        ParserHelper parser = new ParserHelper();
        int indexOfEducation = parser.getIndexOfThisSection(RegEx.EDUCATION, line);
        if (indexOfEducation != -1) {
            List<Integer> listOfSectionIndexes = parser.getAllSectionIndexes(line);
            LOG.info("SECTION INDEXES: {}", listOfSectionIndexes);
            String texts = line.replaceFirst(RegEx.EDUCATION.toString(), "");
            return helper.getSectionContent(indexOfEducation, listOfSectionIndexes, texts);
        }
        return null;
    }

    void setApplicantEducations() {
        for (ApplicantDocument ad : applicantDocument) {
            ApplicantEducation applicantEducation = new ApplicantEducation();
            applicantEducation.setId(ad.getId());
            applicantEducation.setEducation(findEducations(ad.getLine()));
            this.applicantEducationList.add(applicantEducation);
        }
    }
}
