package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtils;

import io.qameta.allure.Step;

public class RegistrationPageTest extends BaseTest{

	
	@BeforeClass
	public void regPageSetup() {
		registrationPage = loginpage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getUserRegTestData() {
		
		return new Object[][] {
			{"shilpa","gupta","8978767876","sg@123","yes"},
			{"kevin","test","8978767877","kt@123","no"},
			{"venila","test","8978767899","ven@123","no"}
		};
		
	}
	
	@DataProvider
	public Object[][] getUserRegTestDataFromExcel() {
		
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		
	}
	@DataProvider
	public Object[][] getUserRegTestDataFromCSV() {
		
		return CSVUtil.csvData(AppConstants.REGISTER_SHEET_NAME);
		
	}
	
	@Step("checking user Registration")
	@Test(dataProvider="getUserRegTestData")
	public void userRegTest(String fname,String lname,String telPhone,String pwd,String subscribe) {
		Assert.assertTrue(registrationPage.userRegister(fname, lname, StringUtils.getRandomEmailId(),
				telPhone, pwd, subscribe));
	}
	
	
	
}
