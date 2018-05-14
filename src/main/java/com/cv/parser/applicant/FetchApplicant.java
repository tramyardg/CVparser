package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;

/**
 * This is for storing data in Applicant object;
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class FetchApplicant {
    
    String linkRegex = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)" 
	    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
	    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";
    
    String emailRegex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    
    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();
    List<Applicant> applicants = new ArrayList<Applicant>();
    
    public FetchApplicant(List<ApplicantDocument> appDocList) {
	this.appDocList = appDocList;
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
     * a link without http://|https://|www is not considered a link i.e.
     * google.com (invalid)
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
	return null;
    }

    private String findObjective(String details) {
	return null;
    }

    private String findPhoneNumber(String details) {
	return null;
    }

    private String findAddress(String details) {
	return null;
    }
    
    public void applicantInfo() {
	for (ApplicantDocument ad : appDocList) {

	    Applicant a = new Applicant();
	    a.setName(findName(ad.getDetails()));
	    a.setPhoneNumber(findPhoneNumber(ad.getDetails()));
	    a.setAddress(findAddress(ad.getDetails()));
	    a.setEmail(findEmail(ad.getDetails()));
	    a.setLinks(findLinks(ad.getDetails()));
	    a.setObjective(findObjective(ad.getDetails()));

	    this.applicants.add(a);
	}
    }
    
    public List<Applicant> getApplicants() {
        return applicants;
    }
}
