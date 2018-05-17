package com.cv.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.helper.Address;
import com.github.javafaker.Faker;

import junit.framework.TestCase;

public class AddressTest extends TestCase {
    Logger logger = LoggerFactory.getLogger(RegexTest.class);
    
    public void test() {
	Faker faker = new Faker();
	Address address = new Address();
	logger.info(faker.address().fullAddress());
    }
}
