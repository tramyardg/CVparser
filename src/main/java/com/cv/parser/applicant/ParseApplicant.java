package com.cv.parser.applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cv.parser.RegEx;
import com.cv.parser.entity.Applicant;
import com.cv.parser.entity.ApplicantDocument;

/**
 * This is for storing data in {@link} Applicant model; The result would be a
 * list of Applicant. For instance, if you have more than one applicants. You'll
 * get:
 * 
 * ApplicantObject = { Applicant [id=1, ...], Applicant [id=2, ...], Applicant
 * [id=3, ...] }
 * 
 * @author tramyardg
 *
 */
public class ParseApplicant {
    List<ApplicantDocument> appDocList = new ArrayList<>();
    List<Applicant> applicants = new ArrayList<>();

    public ParseApplicant(List<ApplicantDocument> appDocList) {
	this.appDocList = appDocList;
    }

    private String findEmail(String details) {
	List<String> emailList = new ArrayList<>();
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
	List<String> links = new ArrayList<>();
	Pattern pattern = Pattern.compile(RegEx.LINK.toString(),
		Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(line);
	while (matcher.find()) {
	    links.add(matcher.group());
	}
	return links.toString();
    }

    /**
     * The introduction of an applicant may contains the name and the address
     * 
     * @return the profile containing name (most likely) and address (possibly)
     */
    private String findProfile(String line) {
	// copies everything up to the next section
	ParserHelper helper = new ParserHelper();
	List<Integer> indexes = helper.getAllSectionIndexes(line);
	int beginIndex = 0;
	int endIndex = indexes.get(0);
	return line.substring(beginIndex, endIndex);
    }

    /**
     * Find phone numbers in the resume
     * 
     * @param line
     *            to search for
     * @return phone numbers found from resume
     */
    private String findPhoneNumber(String line) {
	List<String> phoneNumbers = new ArrayList<>();
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
	String objective = line.replaceFirst(RegEx.OBJECTIVE.toString(), "");
	// get(0) which ensures objective heading is excluded
	int endIndex = helper.getSectionIndexesExcludeOne(obj, objective).get(0);
	return objective.substring(beginIndex, endIndex);
    }

    /**
     * Gathers basic applicant info such as phone number, email, links, profile
     * (introduction), and objective. These information are saved in a list of Applicant object.
     */
    public void setApplicantInfo() {
	for (ApplicantDocument ad : appDocList) {
	    Applicant applicant = new Applicant();
	    applicant.setId(ad.getId());
	    applicant.setPhoneNumber(findPhoneNumber(ad.getLine()));
	    applicant.setEmail(findEmail(ad.getLine()));
	    applicant.setLinks(findLinks(ad.getLine()));
	    applicant.setProfile(findProfile(ad.getLine()));
	    // test if objective section exists in the first place
	    if (new ParserHelper().getIndexOfThisSection(RegEx.OBJECTIVE, ad.getLine()) != -1) {
		applicant.setObjective(findObjective(ad.getLine()));
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
