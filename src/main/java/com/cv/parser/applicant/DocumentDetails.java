package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.builder.ResumeBuilder;
import com.cv.parser.builder.ResumeDirector;
import com.cv.parser.builder.ResumeViewer;
import com.cv.parser.entity.ApplicantDocument;

public class DocumentDetails {
    Logger logger = LoggerFactory.getLogger(DocumentDetails.class);

    // toString(): ApplicantDocument [id={number}, details={resume
    // details......}]
    List<ApplicantDocument> applicantDocumentList = new ArrayList<>();

    List<String> superList = new ArrayList<>();
    Button btnSaveDocumentsToDb;

    public DocumentDetails(Button btnSaveDocumentsToDb, List<String> superList) {
	this.btnSaveDocumentsToDb = btnSaveDocumentsToDb;
	this.superList = superList;
    }

    public void handleButtonClick() {
	btnSaveDocumentsToDb.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {

		storeDocumentAsString();

		ParseApplicant applicant = new ParseApplicant(applicantDocumentList);
		applicant.setApplicantInfo();

		ParseApplicantExperience experiences = new ParseApplicantExperience(applicantDocumentList);
		experiences.setApplicantExperiences();

		ParseApplicantEducation education = new ParseApplicantEducation(applicantDocumentList);
		education.setApplicantEducations();

		ParseApplicantSkill skills = new ParseApplicantSkill(applicantDocumentList);
		skills.setApplicantSkills();

		ResumeBuilder builder = new ResumeViewer(applicant.getApplicants(),
			experiences.getApplicantExperience(), education.getApplicantEducation(),
			skills.getApplicantSkillList());
		ResumeDirector director = new ResumeDirector(builder);
		
		logger.info(director.construct().getApplicant().toString());
		logger.info(director.construct().getExperiences().toString());
		logger.info(director.construct().getEducation().toString());
		// returns List of ApplicantSkill (is a List, get(index) can be used)
		logger.info(director.construct().getSkills().toString());

	    }
	});

    }

    private void storeDocumentAsString() {
	for (int index = 0; index < superList.size(); index++) {
	    String details = superList.get(index);
	    String normalize = StringUtils.normalizeSpace(details);
	    this.applicantDocumentList.add(new ApplicantDocument((index + 1), normalize));
	}
    }

}
