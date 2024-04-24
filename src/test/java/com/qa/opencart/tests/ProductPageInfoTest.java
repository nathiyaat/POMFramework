package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void prodInfoSetup() {
		accntPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
	@DataProvider
	public Object[][] getProductSearchData() {
		
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
		}	
	
	@Test(dataProvider="getProductSearchData")
	public void prodHeaderTest(String searchKey, String prodName) {
		searchResultsPage =accntPage.doSearch(searchKey);
		productInfoPage =searchResultsPage.selectProduct(prodName);		
		Assert.assertEquals(productInfoPage.getProductHeader(), prodName);
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"samsung","Samsung Galaxy Tab 10.1",7}
		};
		}
	
	@DataProvider
	public Object[][] getProductImagesDataFromExcel() {
		
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
		}
	
	
	@Test(dataProvider="getProductImagesDataFromExcel")
	public void prodImagesCountTest(String searchKey,String prodName,String imageCount) {
		searchResultsPage =accntPage.doSearch(searchKey);
		productInfoPage =searchResultsPage.selectProduct(prodName);		
		Assert.assertEquals(productInfoPage.getProdImagesCount(), Integer.parseInt(imageCount));
	}
	
	@Test
	public void prodDetailsTest() {
		searchResultsPage =accntPage.doSearch("macbook");
		productInfoPage =searchResultsPage.selectProduct("MacBook Pro");		
		Map<String,String>  prodActdetailsMap= productInfoPage.getProductDetailsMap();
		
		//Assert.assertEquals(prodActdetailsMap.get("Brand"), "Apple");
		
		softAssert.assertEquals(prodActdetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(prodActdetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(prodActdetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(prodActdetailsMap.get("prodPrice"), "$2,000.00");
		softAssert.assertEquals(prodActdetailsMap.get("exTaxPrice"), "$2,000.00");
		
		System.out.println("product map details : "+prodActdetailsMap);
		softAssert.assertAll();

	}
	
	
}
