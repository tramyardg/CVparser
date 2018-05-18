package com.cv.parser.util;

import java.util.HashMap;
import java.util.Map;

public class Message {
	public Message() {
	}

	/**
	 * [ key - String/variable]
	 * [ value - text/value ]
	 * 
	 * Instead of hard coding the message every time. Create a utility to store
	 * all the messages needed for the forms. This tool is DRY meaning you can
	 * reuse keys whenever you want you don't have to type every time, just find 
	 * message or create one if you need.
	 * Can be used for
	 *  - debugging
	 *  - tooltip text
	 *  - etc.
	 * @param key
	 * @return the value of the key
	 */
	public String msg(String key) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("keyNotExists", "Message key invalid. Please see Message util.");
		
		map.put("mainTitle", "Canadian Citizenship Quiz - (c) 2017,  by Raymart de Guzman & Leo Sudarma");
		map.put("help", "Help");
		map.put("aboutTitle", "About");
		map.put("aboutProgram", "About this program");
		
		map.put("pwdNotMatch", 				"Password not match!");
		map.put("pwdErrorTitle", 			"Password error!");
		
		//*****************************************************//
		map.put("userExists", 				"This user already exist.");
		map.put("userExistsTitle", 			"User already exist!");
		
		map.put("userNotExists", 			"This user does not exist!");
		map.put("userNotExistsTitle", 		"User not exist");
		
		//*****************************************************//
		map.put("regionExists", 			"This region already exist.");
		map.put("regionExistsTitle", 		"Region already exist!");
		
		map.put("regionNotExists", 			"This region does NOT exist.");
		map.put("regionNotExistsTitle", 	"Region NOT exist!");
		//*****************************************************//
		
		map.put("saveConfirmation", 		"Do you want to save it?");
		map.put("saveConfirmationTitle", 	"Save confirmation");
		
		map.put("exitProgram", "Exit From The Application");
		map.put("exitProgramTitle", "Exit Program");
		
		map.put("userFormTitle", "User form");
		map.put("handlingUserTbl", "Handling User Table");
		
		map.put("regionFormTitle", "Region form");
		map.put("handlingRegionTbl", "Handling Region Table");
		
		//*****************************************************//
		map.put("emptyLoginIdTxt", "Login Id text field cannot be empty!");
		map.put("emptyLoginIdTitle", "LoginId field is empty");
		
		map.put("emptyUsernameTxt", "Username text field cannot be empty!");
		map.put("emptyUsernameTitle", "Username field is empty");
		//*****************************************************//
		
		//*****************************************************//
		map.put("emptyRegionCode", "Region code field cannot be empty.");
		map.put("emptyRegionCodeTitle", "Region code empty");
		
		map.put("emptyRegionName", "Region name field cannot be empty.");
		map.put("emptyRegionNameTitle", "Region name empty");
		//*****************************************************//
		
		map.put("emptyPwdTxt", "Password text field cannot be empty!");
		map.put("emptyPwdTitle", "Password field is empty");
		
		map.put("emptyFields", "Empty field(s) encountered in the form.");
		map.put("emptyFieldsTitle", "Empty field(s) encountered.");
		
		map.put("searchUserTitle", "Search for a user");
		map.put("searchRegionTitle", "Search for a region");
		
		map.put("deleteRecord", "Do you want to delete this record?");
		map.put("deleteRecordTitle", "Deleting record");
		
		map.put("noDataFound", "You have no data to display.");
		map.put("noDataFoundTitle", "No data found");
		
		map.put("makeAquizTitle", "Make a quiz");
		
		
		//*****************************************************//
		map.put("emptyQuizQuestion", "Quiz Question must not empty.");
		map.put("emptyQuizQuestionTitle", "Quiz question empty");
		//*****************************************************//
		
		
		// the value of keyNotExists will show up in the GUI if one provided a wrong key
		// one must make sure that key being entered exists when you're using the Message utility
		if(!isKeyExists(key, map))
			return map.get("keyNotExists");
		else 
			return map.get(key);

	}
	
	
	
	public boolean isKeyExists(String key, Map<String, String> map) {
		return map.containsKey(key);
	}

}
