package com.cv.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import junit.framework.TestCase;

public class RegexTest extends TestCase {
    Logger logger = LoggerFactory.getLogger(RegexTest.class);

    public void testEmail() {
	String email = "hello@world.com";
	Pattern pattern = Pattern.compile(RegEx.EMAIL.toString(), Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(email);
	List<String> emailList = new ArrayList<String>();
	while (matcher.find()) {
	    emailList.add(matcher.group());
	}
	assertEquals(email, emailList.get(0));
    }

    public void testObjective() {
	// goal is to extract ONLY the objective of the applicant
	Pattern pattern = Pattern.compile(RegEx.OBJECTIVE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(goodResumeFormat());
	List<String> objective = new ArrayList<String>();
	List<Integer> indexOfObjective = new ArrayList<Integer>();
	while (matcher.find()) {
	    objective.add(matcher.group(0));
	    indexOfObjective.add(matcher.start());
	}
	
	logger.info(goodResumeFormat());
	// you can basically use substring to parse objective text
	// [main] INFO com.cv.parser.RegexTest - OBJECTIVE: 0
	// [main] INFO com.cv.parser.RegexTest - EDUCATION: 188
	// [main] INFO com.cv.parser.RegexTest - EXPERIENCE: 415
	// [main] INFO com.cv.parser.RegexTest - [0, 188, 415]
	getIndexesOfSection(goodResumeFormat());
    }
       
    public void getIndexesOfSection(String line) {
	RegEx[] sectionRegex = {RegEx.OBJECTIVE, RegEx.EDUCATION, RegEx.EXPERIENCE};
	List<Integer> indexesOfSection = new ArrayList<Integer>();
	for (RegEx r : sectionRegex) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
		    logger.info(matcher.group() + ": " + matcher.start() + "");
		    indexesOfSection.add(matcher.start()); 
		}
	}
	// it prints in order of the array sectionRegex
	logger.info(indexesOfSection.toString());
    }
    
    /**
     * Resume has a good format if section headings i.e. objective, education
     * and experience appear only once before the start of a paragraph or
     * section.
     * 
     * @return
     */
    private String goodResumeFormat() {
	Faker faker = new Faker();
	List<String> lorems = faker.lorem().paragraphs(3);
	String fakeObjective = "OBJECTIVE ".concat(lorems.get(0));
	String fakeEducation = " EDUCATION ".concat(lorems.get(1));
	String fakeExperience = " EXPERIENCE ".concat(lorems.get(2));
	return fakeObjective.concat(fakeEducation).concat(fakeExperience);
    }
    
    private String badResumeFormat() {
	Faker faker = new Faker();
	List<String> lorems = faker.lorem().paragraphs(3);
	String fakeObjective = "abcde OBJECTIVE abcde education abcde objective abcde ".concat(lorems.get(0));
	String fakeEducation = " Education abcde objective abcde".concat(lorems.get(1));
	String fakeExperience = " Experience ".concat(lorems.get(2));
	return fakeObjective.concat(fakeEducation).concat(fakeExperience);
    }
}