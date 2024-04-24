package com.qa.opencart.utils;

public class StringUtils {

	public static String getRandomEmailId() {
		String emailId= "testauto"+System.currentTimeMillis()+"@opencart.com";
		System.out.println("email ID is "+emailId);
		return emailId;
	}
	
	
}
