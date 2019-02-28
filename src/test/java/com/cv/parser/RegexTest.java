package com.cv.parser;

import com.cv.parser.applicant.ParserHelper;
import com.github.javafaker.Faker;
import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest extends TestCase {
    private final static Logger LOG = LogManager.getLogger();

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
        LOG.info(line);
        ParserHelper helper = new ParserHelper();
        RegEx regEx = RegEx.OBJECTIVE;
        int objIndex = helper.getIndexOfThisSection(regEx, line);
        int indexOfFollowingSection = helper.getSectionIndexesExcludeOne(regEx, line).get(0);
        LOG.info("object index {}", objIndex);
        LOG.info("index of following section {}", indexOfFollowingSection);
        String objective = line.substring(objIndex, indexOfFollowingSection);
        LOG.info(objective);
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