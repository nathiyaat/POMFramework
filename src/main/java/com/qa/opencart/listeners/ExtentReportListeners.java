package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.observer.entity.MediaEntity.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.DriverFactory;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListeners implements ITestListener{

	private static final String OUTPUT_FOLDER="./reports/";
	
	private static final String FILE_NAME="TestExecution.html";
	
	private static ExtentReports extent=init();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ExtentReports extentReports;

	private static ExtentReports init() {
		
		Path path = Paths.get(OUTPUT_FOLDER);
		
		//if file exists?
		if(!Files.exists(path)) {
			try {
			Files.createDirectories(path);
			}
			catch(IOException e) {
				//fail to cerate directory
				e.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter= new ExtentSparkReporter(OUTPUT_FOLDER+FILE_NAME);
		reporter.config().setReportName("opencart automation test Results");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("System", "Windows");
		extentReports.setSystemInfo("Author", "nathiya");
		extentReports.setSystemInfo("Build", "1.1");

		extentReports.setSystemInfo("Team", "Opencartautomation");
		extentReports.setSystemInfo("EnvName", System.getProperty("env"));	
		
		return extentReports;
	}
	
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started......");
	}
	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println("Test suite is ending ......");
		extent.flush();
		test.remove();
	}
	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String qualifiedName =result.getMethod().getQualifiedName();
		int last=qualifiedName.lastIndexOf(".");
		int mid=qualifiedName.substring(0, last).lastIndexOf(".");
		
		String className=qualifiedName.substring(mid+1, last);
		System.out.println(methodName +" Started");
		ExtentTest extentTest=extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
		
	}
	public synchronized void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName +" passed");
		test.get().pass("Test passed");
		test.get().pass(result.getThrowable(),com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());

		test.get().getModel().setEndTime(getTime(result.getEndMillis()));	
		
	}
	public synchronized void onTestFailure(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName +" Failed");
		test.get().fail("Test Failed");
		test.get().fail(result.getThrowable(),com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
		
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));	
		
	}
	public synchronized void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		System.out.println(methodName +" Skipped");
		test.get().skip("Test Skipped");
		test.get().skip(result.getThrowable(),com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());

		test.get().getModel().setEndTime(getTime(result.getEndMillis()));	
		
	}
	private Date getTime(long millis) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTimeInMillis(millis);		
		return calendar.getTime();
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	System.out.println("onTestFailedButWithinSuccessPercentage  for "+result.getMethod().getMethodName());		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
