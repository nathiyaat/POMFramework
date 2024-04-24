package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage{

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1, private By locatoirs	
	private By emailId=By.id("input-email");
	private By password=By.id("input-password");
	private By login=By.xpath("//input[@value='Login']");	
	private By forgotPwd = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");


	//3.page class comstructor
	public LoginPage(WebDriver driver ) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	
	}
	
	
	
	@Step("getting login page title")
	public String loginPageTitle() {
		
		String title =eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);		
		//System.out.println("title is "+title);
		Log.info("title is "+title);
		return title;
		
	}
	@Step("getting login page URL")

	public String loginPageURL() {		
		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);		
		System.out.println("URL  is "+url);
		return url;
		
	}
	@Step("getting the state of forgot password link")

	public boolean isForgorPwdLinkExist() {
		return eleUtil.isEleDisplayed(forgotPwd);
	}
	
	@Step("login with username: {0} and password: {1}")
	public AccountsPage doLogin(String username,String pwd) {
		System.out.println("Login creds are "+username + " "+pwd);

		eleUtil.waitForeleVisible(emailId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.doClick(login);	
		//return eleUtil.waitForTitleIs("My Account", 10);		
		return new AccountsPage(driver);
		
	}
	@Step("navigating to registartion page .....")
	public RegistrationPage navigateToRegisterPage() {
		
		eleUtil.waitForeleVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		return new RegistrationPage(driver);
		
	}
	
	
	
	
	
	
	//2 page actions or methods
	
}
