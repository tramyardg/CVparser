package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;

public class DocumentDetails {
    Logger logger = LoggerFactory.getLogger(DocumentDetails.class);

    List<String> allContents = new ArrayList<String>();
    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();

    List<Applicant> applicant = new ArrayList<Applicant>();

    public DocumentDetails(List<String> allContents) {
	this.allContents = allContents;
    }

    public void storeDetails() {
	for (int index = 0; index < allContents.size(); index++) {
	    this.appDocList.add(new ApplicantDocument(index, allContents.get(index)));
	}
    }

    public List<ApplicantDocument> getAppDocList() {
	return appDocList;
    }

    public void main() {
	storeDetails();
	applicantInfo();
	
	for(Applicant app : applicant) {
	    logger.info(app.toString());
	}
	// i.e. it prints
        // [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant [name=null, address=null, email=[], phoneNumber=null]
        // [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant [name=null, address=null, email=[mepnd@nd.edu], phoneNumber=null]
        // [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant [name=null, address=null, email=[alexs.dbk@gmail.com, leo@gmail.com], phoneNumber=null]
    }

    private void applicantInfo() {
	for (ApplicantDocument ad : appDocList) {
	    Applicant a = new Applicant();
	    a.setName(null);
	    a.setPhoneNumber(null);
	    a.setAddress(null);
	    a.setEmail(findEmail(ad.getDetails()));
	    this.applicant.add(a);
	}
    }

    private String findEmail(String details) {
	Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(details);
	List<String> emailList = new ArrayList<String>(); // since you can one or more than one email in a resume
	while (m.find()) {
	    emailList.add(m.group());
	}
	return emailList.toString(); // return as i.e. [email1@gmail.com, email2@gmail.com] if more than one
    }
    
    // TODO find name, phone number, and address
}
