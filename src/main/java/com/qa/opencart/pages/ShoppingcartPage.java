package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class ShoppingcartPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1, private By locatoirs	
	
	private By shopCartTitle = By.tagName("h1");
	private By myAccLink = By.linkText("My Account");
	private By searchBar=By.name("search");
	private By searchIcpon = By.cssSelector("div#search button");

	
	private By headersLink=By.cssSelector("div #content h2");
	
	
	public ShoppingcartPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);	
		
	}
	
	
	public void getshoppingCartTitle() {
		
		eleUtil.waitForeleVisible(shopCartTitle, 10);
	}
	
}
