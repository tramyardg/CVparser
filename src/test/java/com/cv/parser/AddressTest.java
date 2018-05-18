package com.cv.parser;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cv.parser.helper.AddressHelper;
import com.cv.parser.helper.ParserHelper;
import com.github.javafaker.Faker;

import junit.framework.TestCase;

public class AddressTest extends TestCase {
    Logger logger = LoggerFactory.getLogger(RegexTest.class);

    public void test() {
	Faker faker = new Faker();
	AddressHelper address = new AddressHelper();
	
	String usAddress = faker.address().fullAddress();
	logger.info(usAddress);
	
	String canadianAddress = "Surrey, BC";
	logger.info(canadianAddress);
	
	ParserHelper parserHelper = new ParserHelper();
	
	Map<String, String> us = parserHelper.getUSstatesMap();
	for (Map.Entry<String, String> usKV: us.entrySet()) {
	    if (usAddress.indexOf(usKV.getKey()) != -1) {
		logger.info(usKV.getValue());
	    }
	}
	
	Map<String, String> can = parserHelper.getCanadianProvincesMap();
	for (Map.Entry<String, String> canKV: can.entrySet()) {
	    if (canadianAddress.indexOf(canKV.getKey()) != -1) {
		logger.info(canKV.getValue());
	    }
	}	
    }
}
