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

    final String linkRegex = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
	    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
    final String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

    public DocumentDetails(List<String> allContents) {
	this.allContents = allContents;
    }

    public void storeDetails() {
	for (int index = 0; index < allContents.size(); index++) {
	    String details = allContents.get(index);

	    // cleaning: removing next line
	    // if one next line: "\n"
	    // if more than one next line: "\r"
	    String nextLineRemoved = details.replace("\n", " ").replace("\r", " ");

	    this.appDocList.add(new ApplicantDocument(index, nextLineRemoved));
	}
    }

    public List<ApplicantDocument> getAppDocList() {
	return appDocList;
    }

    public void main() {
	storeDetails();
	applicantInfo();

	for (Applicant app : applicant) {
	    logger.info(app.toString());
	}
	// i.e. it prints
	// [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant
	// [name=null, address=null, email=[], phoneNumber=null]
	// [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant
	// [name=null, address=null, email=[mepnd@nd.edu], phoneNumber=null]
	// [main] INFO com.cv.parser.applicant.DocumentDetails - Applicant
	// [name=null, address=null, email=[alexs.dbk@gmail.com, leo@gmail.com],
	// phoneNumber=null]
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
	List<String> emailList = new ArrayList<String>(); // since you can have one
							  // or more email in a resume

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
}
