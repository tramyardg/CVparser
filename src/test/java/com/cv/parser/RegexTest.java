package com.cv.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
