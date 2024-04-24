package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1, private By locatoirs	
	
	private By prodsList = By.cssSelector("div .product-thumb");

	
	private By headersLink=By.cssSelector("div #content h2");
	
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);	
		
	}
	
	public int getSearchProdscount() {
		
		return eleUtil.waitForelementsVisible(prodsList, 10).size();		
		
	}
	
	public ProductInfoPage selectProduct(String prodName) {		
		eleUtil.waitForeleVisible(By.linkText(prodName), 10).click();
		return new ProductInfoPage(driver);		
		
	}
	
	

}
