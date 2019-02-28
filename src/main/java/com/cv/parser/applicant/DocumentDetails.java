package com.cv.parser.applicant;

import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.saveas.CandidateBean;
import com.cv.parser.saveas.WriteToFile;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentDetails {
    private static final Logger logger = LoggerFactory.getLogger(DocumentDetails.class);

    // toString(): ApplicantDocument [id={number}, details={resume
    // details......}]
    private List<ApplicantDocument> applicantDocumentList = new ArrayList<>();

    private List<String> superList;
    private Button btnSaveInJSON;
    private Button btnSaveInCSV;

    public DocumentDetails(Button btnSaveDocumentsToDb, Button btnSaveInCsv, List<String> superList) {
        this.btnSaveInJSON = btnSaveDocumentsToDb;
        this.btnSaveInCSV = btnSaveInCsv;
        this.superList = superList;
    }

    private void storeDocumentAsString() {
        for (int index = 0; index < superList.size(); index++) {
            String details = superList.get(index);
            String normalize = StringUtils.normalizeSpace(details);
            this.applicantDocumentList.add(new ApplicantDocument((index + 1), normalize));
        }
    }

    private List<CandidateBean> getProcessedCandidates() {
        ParseApplicant applicant = new ParseApplicant(applicantDocumentList);
        applicant.setApplicantInfo();

        ParseApplicantExperience experiences = new ParseApplicantExperience(applicantDocumentList);
        experiences.setApplicantExperiences();

        ParseApplicantEducation education = new ParseApplicantEducation(applicantDocumentList);
        education.setApplicantEducations();

        ParseApplicantSkill skills = new ParseApplicantSkill(applicantDocumentList);
        skills.setApplicantSkills();

        List<CandidateBean> candidates = new ArrayList<>();
        for (int i = 0; i < applicantDocumentList.size(); i++) {
            CandidateBean bean = new CandidateBean();
            bean.setProfile(applicant.getApplicants().get(i).toString());
            bean.setEducation(education.getApplicantEducation().get(i).toString());
            bean.setExperiences(experiences.getApplicantExperience().get(i).toString());
            bean.setSkills(skills.getApplicantSkillList().get(i).toString());
            candidates.add(i, bean);
        }
        return candidates;
    }

    public void handleButtonSaveInJSON() {
        btnSaveInJSON.addListener(SWT.Selection, arg0 -> {
            storeDocumentAsString();

            // For debugging print processed output
            // System.out.println("***" + getProcessedCandidates().toString());

            Map<String, List<CandidateBean>> map = new HashMap<>();
            map.put("candidates", getProcessedCandidates());

            WriteToFile writeToFile = new WriteToFile();
            writeToFile.writeToJSON((new JSONObject(map)).toString());
            if (writeToFile.isHasUpdated()) {
                btnSaveInJSON.setEnabled(false);
                logger.debug("Write to JSON file success!");
            }
        });
    }

    public void handleButtonSaveInCSV() {
        btnSaveInCSV.addListener(SWT.Selection, arg0 -> {
            storeDocumentAsString();
            WriteToFile writeToFile = new WriteToFile();
            writeToFile.writeToCSV(getProcessedCandidates());
            if (writeToFile.isHasUpdated()) {
                btnSaveInCSV.setEnabled(false);
                logger.info("Write to CSV file success!");
            }
        });
    }
}
