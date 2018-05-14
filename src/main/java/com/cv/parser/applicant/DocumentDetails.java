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

    String linkRegex = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" 
	    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
	    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
    String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    public DocumentDetails(List<String> allContents) {
	this.allContents = allContents;
    }
    
    public List<ApplicantDocument> getAppDocList() {
	return appDocList;
    }
    
    public void main() {
	storeDocumentAsString();
	applicantInfo();

	for (Applicant app : applicant) {
	    logger.info(app.toString());
	}
    }

    public void storeDocumentAsString() {
	for (int index = 0; index < allContents.size(); index++) {
	    String details = allContents.get(index);

	    // cleaning: removing next line
	    // if one next line: "\n"
	    // if more than one next line: "\r"
	    String nextLineRemoved = details.replace("\n", " ").replace("\r", " ");

	    this.appDocList.add(new ApplicantDocument(index, nextLineRemoved));
	}
    }

    private void applicantInfo() {
	for (ApplicantDocument ad : appDocList) {
	    Applicant a = new Applicant();
	    a.setName(null);
	    a.setPhoneNumber(null);
	    a.setAddress(null);
	    a.setEmail(findEmail(ad.getDetails()));
	    a.setLinks(findLinks(ad.getDetails()));
	    this.applicant.add(a);
	}
    }

    private String findEmail(String details) {
	List<String> emailList = new ArrayList<String>();
	Pattern pattern = Pattern.compile(emailRegex, Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(details);
	while (matcher.find()) {
	    emailList.add(matcher.group());
	}
	return emailList.toString(); 
    }

    /**
     * a link without http://|https://|www is not considered a link i.e. google.com (invalid)
     * 
     * @return
     */
    private String findLinks(String details) {
	List<String> links = new ArrayList<String>();
	Pattern pattern = Pattern.compile(linkRegex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(details);
	while (matcher.find()) {
	    links.add(matcher.group());
	}
	return links.toString();
    }
    
    private String findName(String details) {
	// TODO
	return null;
    }
    
    private String findObjective(String details) {
	// TODO
	return null;
    }
    
    private String findPhoneNumber(String details) {
	// TODO
	return null;
    }
    
    private String findAddress(String details) {
	// TODO
	return null;
    }
}
