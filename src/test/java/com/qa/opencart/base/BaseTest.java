package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingcartPage;

import io.qameta.allure.Step;

public class BaseTest {
	
	DriverFactory df;
	protected Properties prop;
	WebDriver driver;
	protected LoginPage loginpage;
	protected  AccountsPage accntPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingcartPage shoppingcartPage;
	protected RegistrationPage registrationPage;

	
	protected SoftAssert  softAssert;
	
	@Step("setup: launching browser {0} & init the properties")
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();	
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		
		driver =df.initDriver(prop);	
		loginpage=new LoginPage(driver);
		softAssert=new SoftAssert();
	}
	
	@Step("closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
