package com.cv.parser.applicant;

import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.saveas.CandidateBean;
import com.cv.parser.saveas.WriteToFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentDetails {
    private final static Logger LOG = LogManager.getLogger();

    // toString(): ApplicantDocument [id={number}, details={resume
    // details......}]
    private List<ApplicantDocument> applicantDocumentList = new ArrayList<>();

    private List<String> superList;
    private MenuItem menuItemJSON;
    private MenuItem menuItemCSV;

    public DocumentDetails(MenuItem menuItemJSON, MenuItem menuItemCSV, List<String> superList) {
        this.menuItemJSON = menuItemJSON;
        this.menuItemCSV = menuItemCSV;
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
        menuItemJSON.addListener(SWT.Selection, arg0 -> {
            storeDocumentAsString();

            // For debugging print processed output
            // System.out.println("***" + getProcessedCandidates().toString());

            Map<String, List<CandidateBean>> map = new HashMap<>();
            map.put("candidates", getProcessedCandidates());

            WriteToFile writeToFile = new WriteToFile();
            writeToFile.writeToJSON((new JSONObject(map)).toString());
            if (writeToFile.isHasUpdated()) {
        	menuItemJSON.setEnabled(false);
                LOG.info("Write to JSON file success!");
            }
            this.applicantDocumentList.clear();
        });
    }

    public void handleButtonSaveInCSV() {
        menuItemCSV.addListener(SWT.Selection, arg0 -> {
            storeDocumentAsString();
            WriteToFile writeToFile = new WriteToFile();
            writeToFile.writeToCSV(getProcessedCandidates());
            if (writeToFile.isHasUpdated()) {
        	menuItemCSV.setEnabled(false);
                LOG.info("Write to CSV file success!");
            }
            this.applicantDocumentList.clear();
        });
    }
}
