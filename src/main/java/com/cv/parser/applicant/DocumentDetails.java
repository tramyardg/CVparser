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
import com.cv.parser.entity.ApplicantExperience;

public class DocumentDetails {
    Logger logger = LoggerFactory.getLogger(DocumentDetails.class);
    
    // toString(): ApplicantDocument [id={number}, details={resume details......}]
    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();
    
    List<String> superList = new ArrayList<String>();
    Button btnSaveDocumentsToDb;
    
    public DocumentDetails(Button btnSaveDocumentsToDb, List<String> superList) {
	this.btnSaveDocumentsToDb = btnSaveDocumentsToDb;
	this.superList = superList;
    }
    
    public void handleButtonClick() {
	btnSaveDocumentsToDb.addListener(SWT.Selection, new Listener() {
	    public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
		
		storeDocumentAsString();
		
		ParseApplicant application = new ParseApplicant(appDocList);
		application.setApplicantInfo();
		for (Applicant a : application.getApplicants()) {
		    logger.info(a.toString());
		}

		////////////////////////////////////
		
		ParseApplicantExperience applicantExperience = new ParseApplicantExperience(appDocList);
		applicantExperience.setApplicantExperience();
		for (ApplicantExperience ae : applicantExperience.getApplicantExperience()) {
		    logger.info(ae.toString());
		}
		
		
		//FetchApplicantExperience applicationExperience = new FetchApplicantExperience(appDocList);
		//FetchApplicantSkill applicantSkill = new FetchApplicantSkill(appDocList);
		
		// insert application + applicationEducation + applicationExperience + applicantSkill in the database
		
	    }
	});
	
    }
    
    private void storeDocumentAsString() {
	for (int index = 0; index < superList.size(); index++) {
	    String details = superList.get(index);
	    String normalize = StringUtils.normalizeSpace(details); // i.e. hello     world -> hello world
	    this.appDocList.add(new ApplicantDocument((index + 1), normalize));
	}
    }

}
