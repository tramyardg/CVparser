package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.RegEx;
import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;

/**
 * This is for storing data in Applicant object;
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class FetchApplicant {
    Logger logger = LoggerFactory.getLogger(FetchApplicant.class);

    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();
    List<Applicant> applicants = new ArrayList<Applicant>();

    public FetchApplicant(List<ApplicantDocument> appDocList) {
	this.appDocList = appDocList;
    }

    private String findEmail(String details) {
	List<String> emailList = new ArrayList<String>();
	Pattern pattern = Pattern.compile(RegEx.EMAIL.toString(), Pattern.MULTILINE);
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
	Pattern pattern = Pattern.compile(RegEx.LINK.toString(),
		Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(details);
	while (matcher.find()) {
	    links.add(matcher.group());
	}
	return links.toString();
    }

    /**
     * Usually the first few lines contains the name of the applicant
     * 
     * @param details
     *            line to parse
     * @return assumed names
     */
    private String findName(String details) {
	String[] tokens = details.split(" ");
	// assuming the first 3 elements of each document has the name of
	// the applicant
	String[] possibleName = new String[3];
	List<String> nameList;
	for (int index = 0; index < 3; index++) {
	    possibleName[index] = tokens[index];
	}
	nameList = Arrays.asList(possibleName);
	nameList.toString();
	return String.join(" ", nameList);
    }

    /**
     * http://www.harshbaid.in/2013/08/03/regular-expression-for-us-and-canada-phone-number/
     * Standard format: (123) 456-7890
     * 
     * @param details
     * @return
     */
    private String findPhoneNumber(String details) {
	List<String> phoneNumbers = new ArrayList<String>();
	Pattern pattern = Pattern.compile(RegEx.PHONE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(details);
	while (matcher.find()) {
	    phoneNumbers.add(matcher.group());
	}
	return phoneNumbers.toString();
    }

    private String findObjective(String details) {
	// will match
	// objective
	// objectives
	// OBJECTIVE
	// objectif
	return null;
    }

    private String findAddress(String details) {
	return null;
    }

    public void applicantInfo() {
	for (ApplicantDocument ad : appDocList) {
	    Applicant applicant = new Applicant();
	    applicant.setName(findName(ad.getDetails()));
	    applicant.setPhoneNumber(findPhoneNumber(ad.getDetails()));
	    applicant.setAddress(findAddress(ad.getDetails()));
	    applicant.setEmail(findEmail(ad.getDetails()));
	    applicant.setLinks(findLinks(ad.getDetails()));
	    applicant.setObjective(findObjective(ad.getDetails()));
	    this.applicants.add(applicant);
	}
    }

    public List<Applicant> getApplicants() {
	return applicants;
    }
}
