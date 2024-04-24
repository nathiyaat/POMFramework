package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;

public class SearchResultsTest extends BaseTest{
	
	@BeforeClass
	public void searchReultSetup() {		
		accntPage =loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductCountData() {
		
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2}
		};
		}	
	
	@Test(dataProvider="getProductCountData")
	public void searchResultsTest(String searchKey,int prodCount) {
		searchResultsPage =accntPage.doSearch(searchKey);
		Assert.assertEquals(searchResultsPage.getSearchProdscount(), prodCount);
	}

	
	
}
