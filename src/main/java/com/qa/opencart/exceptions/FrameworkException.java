package com.qa.opencart.exceptions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class FrameworkException extends RuntimeException{

	public FrameworkException(String msg) {
		super(msg);
		
	}
	
	
}
