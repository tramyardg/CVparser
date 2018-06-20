package com.cv.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.applicant.ParserHelper;
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
	// this means the next section would be getIndexesOfSection(RegEx.OBJECTIVE, line).get(0);
	String line = goodResumeFormat();
	logger.info(line);
	ParserHelper helper = new ParserHelper();
	RegEx regEx = RegEx.OBJECTIVE;
	int objIndex = helper.getIndexOfThisSection(regEx, line);
	int indexOfFollowingSection = helper.getIndexesOfSection(regEx, line).get(0);
	logger.info("object index {}", objIndex);
	logger.info("index of following section {}", indexOfFollowingSection);
	String objective = line.substring(objIndex, indexOfFollowingSection);
	logger.info(objective);
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
	String fakeExperience = " EXPERIENCE ".concat(lorems.get(1));
	String fakeEducation = " EDUCATION ".concat(lorems.get(2));
	return fakeObjective.concat(fakeExperience).concat(fakeEducation);
    }
    
    private String badResumeFormat() {
	Faker faker = new Faker();
	List<String> lorems = faker.lorem().paragraphs(3);
	String fakeObjective = "abcde OBJECTIVE abcde education abcde objective abcde ".concat(lorems.get(0));
	String fakeEducation = "Education abcde objective abcde".concat(lorems.get(1));
	String fakeExperience = "Experience ".concat(lorems.get(2));
	return fakeObjective.concat(fakeEducation).concat(fakeExperience);
    }
}