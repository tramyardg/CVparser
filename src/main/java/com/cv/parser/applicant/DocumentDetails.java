package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;

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
		
		FetchApplicant application = new FetchApplicant(appDocList);
		application.applicantInfo();
		for (Applicant a : application.getApplicants()) {
		    logger.info(a.toString());
		}
		
		//FetchApplicantEducation applicationEducation = new FetchApplicantEducation(appDocList);
		//FetchApplicantExperience applicationExperience = new FetchApplicantExperience(appDocList);
		//FetchApplicantSkill applicantSkill = new FetchApplicantSkill(appDocList);
		
		// insert application + applicationEducation + applicationExperience + applicantSkill in the database
	    }
	});
	
    }
    
    private void storeDocumentAsString() {
	for (int index = 0; index < superList.size(); index++) {
	    String details = superList.get(index);
	    // cleaning: removing next line
	    // if one next line: "\n"
	    // if more than one next line: "\r"
	    String nextLineRemoved = details.replace("\n", " ").replace("\r", " ");
	    this.appDocList.add(new ApplicantDocument(index, nextLineRemoved));
	}
    }

}
