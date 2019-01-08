package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.saveas.CandidateBean;
import com.cv.parser.saveas.CandidateCSV;
import com.cv.parser.saveas.CandidateJSON;

public class DocumentDetails {
    private static final Logger logger = LoggerFactory.getLogger(DocumentDetails.class);

    // toString(): ApplicantDocument [id={number}, details={resume
    // details......}]
    List<ApplicantDocument> applicantDocumentList = new ArrayList<>();

    List<String> superList = new ArrayList<>();
    Button btnSaveInJSONfile;
    Button btnSaveInCSVfile;

    public DocumentDetails(Button btnSaveDocumentsToDb, Button btnSaveInCsv, List<String> superList) {
	this.btnSaveInJSONfile = btnSaveDocumentsToDb;
	this.btnSaveInCSVfile = btnSaveInCsv;
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

    public void handleButtonSaveInJSONfile() {
	btnSaveInJSONfile.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {

		storeDocumentAsString();

		CandidateJSON cJSON = new CandidateJSON();
		Map<String, List<CandidateBean>> map = new HashMap<>();
		map.put("candidates", getProcessedCandidates());
		cJSON.setMap(map);
		cJSON.writeToJSONfile((new JSONObject(map)).toString());
		logger.debug("Write to JSON file success!");

	    }
	});
    }

    public void handleButtonSaveInCSVfile() {
	btnSaveInCSVfile.addListener(SWT.Selection, new Listener() {

	    @Override
	    public void handleEvent(Event arg0) {
		storeDocumentAsString();
		CandidateCSV csv = new CandidateCSV();
		csv.saveDataInCSVfile(getProcessedCandidates());
		logger.info("Write to CSV file success!");
	    }
	});
    }

}
