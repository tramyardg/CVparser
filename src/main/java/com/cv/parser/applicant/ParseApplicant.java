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
import com.cv.parser.helper.Address;
import com.cv.parser.helper.ParserHelper;

/**
 * This is for storing data in {@link} Applicant model;
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class ParseApplicant {
    Logger logger = LoggerFactory.getLogger(ParseApplicant.class);

    List<ApplicantDocument> appDocList = new ArrayList<ApplicantDocument>();
    List<Applicant> applicants = new ArrayList<Applicant>();

    public ParseApplicant(List<ApplicantDocument> appDocList) {
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
    private String findLinks(String line) {
	List<String> links = new ArrayList<String>();
	Pattern pattern = Pattern.compile(RegEx.LINK.toString(),
		Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(line);
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
    private String findName(String line) {
	String[] tokens = line.split(" ");
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
    private String findPhoneNumber(String line) {
	List<String> phoneNumbers = new ArrayList<String>();
	Pattern pattern = Pattern.compile(RegEx.PHONE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(line);
	while (matcher.find()) {
	    phoneNumbers.add(matcher.group());
	}
	return phoneNumbers.toString();
    }

    private String findObjective(String line) {
	RegEx obj = RegEx.OBJECTIVE;
	ParserHelper helper = new ParserHelper();
	int beginIndex = helper.getIndexOfThisSection(obj, line);
	String objective = line.replaceFirst(RegEx.OBJECTIVE.toString(), ""); // section heading is removed
	int endIndex = helper.getIndexesOfSection(obj, objective).get(0);
	return objective.substring(beginIndex, endIndex);
    }

    private String findAddress(String line) {
	return null;
    }
    
    public void applicantInfo() {
	for (ApplicantDocument ad : appDocList) {
	    Applicant applicant = new Applicant();
	    applicant.setName(findName(ad.getDetails()));
	    applicant.setPhoneNumber(findPhoneNumber(ad.getDetails()));
	    applicant.setAddress(new Address());
	    applicant.setEmail(findEmail(ad.getDetails()));
	    applicant.setLinks(findLinks(ad.getDetails()));
	    
	    // test if objective section exists in the first place
	    if (new ParserHelper().getIndexOfThisSection(RegEx.OBJECTIVE, ad.getDetails())  != -1) {
		applicant.setObjective(findObjective(ad.getDetails()));
	    } else {
		applicant.setObjective(null);
	    }
	    
	    this.applicants.add(applicant);
	}
    }

    public List<Applicant> getApplicants() {
	return applicants;
    }
}
