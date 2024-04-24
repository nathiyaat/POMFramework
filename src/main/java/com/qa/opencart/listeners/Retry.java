package com.qa.opencart.listeners;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
public class Retry implements IRetryAnalyzer{
	
	private int count=0;
	private static int maxTry=3;

	@Override
	public boolean retry(ITestResult iTestresult) {

		if(!iTestresult.isSuccess()) {  //check if test not succedd
			
			if(count<maxTry)  {//check if maxTry count is reached
				count++; //increase the count by 1
				iTestresult.setStatus(ITestResult.FAILURE);//mark test as failed
				return true; //tells TestNG to re run the test
				}
			else {
				iTestresult.setStatus(ITestResult.FAILURE);//maxcount has reached, test marked as failed
			}
		}
		else {
			iTestresult.setStatus(ITestResult.SUCCESS);//if test passes TestNG mark test as passed
		}
		
		
		
		return false;//tells TestNG no need to re run the test
	}
}
