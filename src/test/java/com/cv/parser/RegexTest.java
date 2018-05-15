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
	Pattern pattern = Pattern.compile(Regex.EMAIL.toString(), Pattern.MULTILINE);
	Matcher matcher = pattern.matcher(email);
	List<String> emailList = new ArrayList<String>();
	while (matcher.find()) {
	    emailList.add(matcher.group());
	}
	assertEquals(email, emailList.get(0));
    }
    
    public void testObjective() {
	Faker faker = new Faker();
	List<String> lorems = faker.lorem().paragraphs(3);
	String fakeObjective = "ads OBJECTIVE lkajdlkajd education slkajsd objective asdsad ".concat(lorems.get(0));
	String fakeEducation = " Education ".concat(lorems.get(1));
	String fakeExperience = " Experience ".concat(lorems.get(2));
	
	String combined = fakeObjective.concat(fakeEducation).concat(fakeExperience);
	
	Pattern pattern = Pattern.compile(Regex.OBJECTIVE.toString(), Pattern.MULTILINE | Pattern.DOTALL);
	Matcher matcher = pattern.matcher(combined);
	List<String> objective = new ArrayList<String>();
	List<Integer> indexOfObjective = new ArrayList<Integer>();
	while (matcher.find()) {
	    objective.add(matcher.group(0));
	    indexOfObjective.add(matcher.start());
	}
	
	int firstOccurenceOfObjective = indexOfObjective.get(0);
	List<Integer> startIndexOfFollowingSections = testGetFollowingSections(Regex.OBJECTIVE, combined);
	
	logger.info(combined);
	logger.info(indexOfObjective.get(0).toString());
	logger.info(startIndexOfFollowingSections.toString());
	
	String assumedObjective = null;
	
	// hopefully the words "education" or "experience" does not appear in objective section
	// if they appear take the index of last occurrence of these words
	if (startIndexOfFollowingSections.size() == 1) {
	    assumedObjective = combined.substring(indexOfObjective.get(0), startIndexOfFollowingSections.get(0));    
	} else {
	    int lastIndex = startIndexOfFollowingSections.size() - 1;
	    // these ignores "education" or "experience" words inside objective section
	    assumedObjective = combined.substring(indexOfObjective.get(0), startIndexOfFollowingSections.get(lastIndex));   
	}
	
	logger.info(assumedObjective);
    }
    
    
    public List<Integer> testGetFollowingSections(Regex currentRegexName, String line) {
	Regex[] sectionRegex = {Regex.OBJECTIVE, Regex.EDUCATION, Regex.EXPERIENCE};
	List<Integer> startIndexOfFollowingSections = new ArrayList<Integer>();
	
	for (Regex r : sectionRegex) {
	    if (!r.name().equals(currentRegexName.name())) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
		    // store starting index of sections that follows i.e. education, experience 
		    logger.info(matcher.group());
		    startIndexOfFollowingSections.add(matcher.start()); 
		}
	    }
	}
	return startIndexOfFollowingSections;
    }
    
    public void getStartIndexesOfSection(String line) {
	Regex[] sectionRegex = {Regex.OBJECTIVE, Regex.EDUCATION, Regex.EXPERIENCE};
	List<Integer> startIndexOfSections = new ArrayList<Integer>();
	
	for (Regex r : sectionRegex) {
		Pattern pattern = Pattern.compile(r.toString(), Pattern.MULTILINE | Pattern.DOTALL);
		Matcher matcher = pattern.matcher(line);
		while(matcher.find()) {
		    // store starting index of sections that follows i.e. education, experience 
		    logger.info(matcher.group());
		    startIndexOfSections.add(matcher.start()); 
		}
	}
	logger.info(startIndexOfSections.toString());
    }
}














