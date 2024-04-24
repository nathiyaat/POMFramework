package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1, private By locatoirs	
	
	private By logoutLink = By.linkText("Logout");
	private By myAccLink = By.linkText("My Account");
	private By searchBar=By.name("search");
	private By searchIcpon = By.cssSelector("div#search button");

	
	private By headersLink=By.cssSelector("div #content h2");
	
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);	
		
	}
	public String getAccPageTitle() {
		
		String title =eleUtil.waitForTitleIs(AppConstants.ACCOUNT_PAGE_TITLE, 5);		
		System.out.println("Account page title is "+title);
		return title;
		
	}
	public String getAccPageURL() {		
		String url=eleUtil.waitForURLContains(AppConstants.Account_PAGE_URL_FRACTION, 5);		
		System.out.println("Account URL  is "+url);
		return url;
		
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.isEleDisplayed(logoutLink);
	}
	public boolean isMyAccLinkExist() {
		return eleUtil.isEleDisplayed(myAccLink);
	}
	
	public List<String> getAccountsPageHeaderList() {
		List<WebElement> heasdesList =eleUtil.getElements(headersLink);
		
		List<String> headerList = new ArrayList<String>();
		
		for(WebElement e: heasdesList) {
			
			String headerTxt = e.getText();
			headerList.add(headerTxt);
		}
		return headerList;		
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		System.out.println("searching for : "+searchKey);
		eleUtil.dosendKeys(searchBar, searchKey);
		eleUtil.doClick(searchIcpon);
		
		return new SearchResultsPage(driver);
		
	}
	
	
	
	
	
	
	
	
}
