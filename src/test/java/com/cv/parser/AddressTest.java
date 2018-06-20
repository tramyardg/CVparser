package com.cv.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import junit.framework.TestCase;

public class AddressTest extends TestCase {
    Logger logger = LoggerFactory.getLogger(AddressTest.class);

    public void test() {
	Faker faker = new Faker();

	String usAddress = faker.address().fullAddress();
	logger.info(usAddress);

	String canadianAddress = "Surrey, BC";
	logger.info(canadianAddress);

    }
}
