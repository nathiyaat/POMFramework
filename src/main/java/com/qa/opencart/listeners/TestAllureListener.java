package com.qa.opencart.listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Attachment;

public class TestAllureListener implements ITestListener{
	
	private static String getTestMethodName(ITestResult iTestResult) {		
		return iTestResult.getMethod().getConstructorOrMethod().getName();		
	}
	
	//Text attachments for allure
	@Attachment(value="Page Screenshot" , type="image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	//Text attachments for allure
	@Attachment(value="{0}" , type="text/plain")
	public static String saveTextLog(String message) {
		return message;
	}
	//Html attachments for allure
	@Attachment(value="{0}" , type="text/html")
	public static String attachHtml(String html) {
		return html;
	}
	
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onstart method ......" +iTestContext.getName());
	}
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method ......" +iTestContext.getName());
	}
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method ......" + getTestMethodName(iTestResult)+ "start");
	}
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method ......" + getTestMethodName(iTestResult)+ "succeed");
	}
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method ......" + getTestMethodName(iTestResult)+ "failed");
		
		Object testClass=iTestResult.getInstance();
		//Allure ScreenshotRobot and save TextLog
		
		if(DriverFactory.getDriver() instanceof WebDriver) {
			System.out.println("Screenshot capture for test case "+ getTestMethodName(iTestResult));
			saveScreenshotPNG(DriverFactory.getDriver());
		}
		//save a log on allure
		saveTextLog(getTestMethodName(iTestResult) +"failed and screenshot taken");		
	}
	
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method ......" + getTestMethodName(iTestResult)+ "skipped");
	}
	public void onTestFailedButwithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is defined in success ratio ......" + getTestMethodName(iTestResult));
	}
	
	
	
	
	
	
	
	
}
