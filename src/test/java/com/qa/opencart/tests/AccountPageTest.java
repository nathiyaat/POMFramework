package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetup() {
		
		accntPage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actTitle =accntPage.getAccPageTitle();
		Assert.assertEquals(actTitle, AppConstants.ACCOUNT_PAGE_TITLE);
		
	}
	@Test
	public void accPageURLTest() {
		String actURLTitle =accntPage.getAccPageURL();
		Assert.assertTrue(actURLTitle.contains(AppConstants.Account_PAGE_URL_FRACTION));		
	}
	
	@Test
	public void isLogoutlinkExistTest() {
		Assert.assertTrue(accntPage.isLogoutLinkExist());
		
	}
	@Test
	public void isMyAcclinkExistTest() {
		Assert.assertTrue(accntPage.isMyAccLinkExist());
		
	}
	
	@Test
	public void accsPageHeaderTest() {
		
		List<String> acctPageheaders =accntPage.getAccountsPageHeaderList();
		System.out.println("Account page heder List"+acctPageheaders);
		
	}
	@Test
	public void searchTest() {
		accntPage.doSearch("macbook");
	}
	
	
}
