package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;
import com.cv.parser.entity.ApplicantEducation;
import com.cv.parser.entity.ApplicantExperience;
import com.cv.parser.entity.ApplicantSkill;

public class DocumentDetails {
    Logger logger = LoggerFactory.getLogger(DocumentDetails.class);
    
    // toString(): ApplicantDocument [id={number}, details={resume details......}]
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
		
		ParseApplicant application = new ParseApplicant(applicantDocumentList);
		application.setApplicantInfo();
		for (Applicant a : application.getApplicants()) {
		    logger.info(a.toString());
		}

		////////////////////////////////////
		
		ParseApplicantExperience parseApplicantExperience = new ParseApplicantExperience(applicantDocumentList);
		parseApplicantExperience.setApplicantExperiences();
		for (ApplicantExperience applicantExperience : parseApplicantExperience.getApplicantExperience()) {
		    logger.info(applicantExperience.toString());
		}
		
		ParseApplicantEducation parseApplicantEducation = new ParseApplicantEducation(applicantDocumentList);
		parseApplicantEducation.setApplicantEducations();
		for (ApplicantEducation applicantEducation : parseApplicantEducation.getApplicantEducation()) {
		    logger.info(applicantEducation.toString());
		}
		
		ParseApplicantSkill parseApplicantSkill = new ParseApplicantSkill(applicantDocumentList);
		parseApplicantSkill.setApplicantSkills();
		for (ApplicantSkill applicantSkill : parseApplicantSkill.getApplicantSkillList()) {
		    logger.info(applicantSkill.toString());
		}
		
	    }
	});
	
    }
    
    private void storeDocumentAsString() {
	for (int index = 0; index < superList.size(); index++) {
	    String details = superList.get(index);
	    String normalize = StringUtils.normalizeSpace(details); // i.e. hello     world -> hello world
	    this.applicantDocumentList.add(new ApplicantDocument((index + 1), normalize));
	}
    }

}
