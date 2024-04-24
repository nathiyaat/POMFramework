package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegistrationPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstname=By.id("input-firstname");
	private By lastname=By.id("input-lastname");
	private By email=By.id("input-email");
	private By telephone=By.id("input-telephone");
	private By password=By.id("input-password");
	private By confirmPassword=By.id("input-confirm");
	private By subscribeYes=By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscribeNo=By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	private By agreeChkBox=By.name("agree");
	private By continueBtn=By.xpath("//input[@type='submit' and @value='Continue']");
	private By successMsg=By.cssSelector("div#content h1");
	private By logoutLink=By.linkText("Logout");
	private By registerLink=By.linkText("Register");



	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);

	}

	@Step("user register with {0} , {1} ,{2}, {3}")
	public boolean userRegister(String fname,String lname,String email,String telPhone,String pwd,String subscribe) {
		eleUtil.waitForeleVisible(firstname, 10).sendKeys(fname);
		eleUtil.dosendKeys(lastname, lname);
		eleUtil.dosendKeys(this.email, email);

		eleUtil.dosendKeys(telephone, telPhone);
		eleUtil.dosendKeys(password, pwd);
		eleUtil.dosendKeys(confirmPassword, pwd);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		}else
		{
			eleUtil.doClick(subscribeNo);

		}
		eleUtil.doClick(agreeChkBox);
		eleUtil.doClick(continueBtn);
		
		eleUtil.waitForeleVisible(successMsg, 10);
		
		if(successMsg.equals(AppConstants.USER_REG_SUCCS_MSG)) {
			//eleUtil.scrollPageDown();
			//eleUtil.scrollIntoView(logoutLink);
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);

			
			
			return true;
			
		}
		return false;	
		
	}
	
	public void logoutLink() {
		eleUtil.doClick(logoutLink);
	}
	public void registerLink() {
		eleUtil.doClick(registerLink);
	}
	
}
