package com.cv.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.helper.WorkExperienceHelper;
import com.github.javafaker.Faker;

import junit.framework.TestCase;

public class ApplicantTest extends TestCase {
    Logger logger = LoggerFactory.getLogger(ApplicantTest.class);
    
    public void testWorkExperienceHelper() {
	Faker faker = new Faker();
	WorkExperienceHelper weh = new WorkExperienceHelper();
	weh.setPosition(faker.company().profession());
	weh.setCompany(faker.company().name());
	weh.setAddress(faker.address().fullAddress());
	weh.setDateFrom(faker.date().birthday(18, 50).toString());
	weh.setDateTo(faker.date().birthday(18, 50).toString());
	weh.setDescription(faker.lorem().sentence());

	WorkExperienceHelper weh2 = new WorkExperienceHelper();
	weh2.setPosition(faker.company().profession());
	weh2.setCompany(faker.company().name());
	weh2.setAddress(faker.address().fullAddress());
	weh2.setDateFrom(faker.date().birthday(18, 50).toString());
	weh2.setDateTo(faker.date().birthday(18, 50).toString());
	weh2.setDescription(faker.lorem().sentence());
	
	
	List<WorkExperienceHelper> weh3 = new ArrayList<WorkExperienceHelper>();
	weh3.add(weh);
	weh3.add(weh2);
	
	logger.info(weh3.toString());
	
    }

}
