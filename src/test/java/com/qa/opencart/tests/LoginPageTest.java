package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


//@Listeners(ExtentReports.class)

@Epic("EP 100: Design open cart login page")
@Story("US 101: Design login page feature for open cart appln")
@Feature("Feature 201: Adding Login  feature")


public class LoginPageTest extends BaseTest{

@Description("checking login page title ......")
@Severity(SeverityLevel.MINOR)
@Test (priority=1)
public void loginPageTitleTest() {
	
	String actTitle =loginpage.loginPageTitle();
	Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
}

@Description("checking login page URL ......")
@Severity(SeverityLevel.MINOR)
@Test(priority=2)
public void loginPageURLTest() {
	
	String acturlTitle =loginpage.loginPageURL();
	Assert.assertTrue(acturlTitle.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);
}

@Description("checking forgot password link on login page ......")
@Severity(SeverityLevel.CRITICAL)
@Test(priority=3)
public void forgotPwdlinkExistTest() {
	
	Assert.assertTrue(loginpage.isForgorPwdLinkExist());
	
}
@Description("checking user is able to login ......")
@Severity(SeverityLevel.BLOCKER)
@Test(priority=4)
public void logintest() {
	accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	Assert.assertEquals(accntPage.getAccPageTitle(),AppConstants.ACCOUNT_PAGE_TITLE);
	
}


}
