package com.qa.opencart.utils;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.ElementException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private JavascriptExecutor js;
	private JavaScriptUtil jsUtil;
	
	private final String DEFAULT_ELE_TIMEOUT_MSG ="no element... timeout..";
	private final String DEFAULT_ALERT_TIMEOUT_MSG ="no alert... timeout..";

	
	public ElementUtil(WebDriver driver) {
		this.driver=driver;
		jsUtil=new JavaScriptUtil(driver, js);
	}
	
	private void nullBlankcheck(String value) {
		if(value == null || value.length()==0) {
			throw new RuntimeException(value +"value cant be null");
		}
	}
	
	
	private void checkHighlight(WebElement elemnt) {
		if(Boolean.parseBoolean(DriverFactory.highlight)){
			jsUtil.flash(elemnt);
		}
		
	}
	
	@Step("getting web element using locator {0}")
	public WebElement getelemnet(By locator) {
		WebElement elemnt =null;
		try {
			elemnt = driver.findElement(locator);
			checkHighlight(elemnt);			
		 
		}
		catch(NoSuchElementException e) {
			System.out.println("Web elemnet is not present on the page");
			e.printStackTrace();
			return null;
			
		}
		return elemnt;
	}
	public WebElement getelemnet(String locatorType,String locatorValue) {
		WebElement ele = driver.findElement(getBy(locatorType, locatorValue));
		checkHighlight(ele);
		return ele;
	}
	
	@Step("entering value in {1} with locator : {0}")
	public void dosendKeys(By locator,String value) {
		nullBlankcheck(value);
		getelemnet(locator).clear();
		getelemnet(locator).sendKeys(value);
	}
	
	public void dosendKeys(By locator,String value,int timeout) {
		nullBlankcheck(value);		
		waitForeleVisible(locator, timeout).sendKeys(value);
	}
	
	public void dosendKeys(String locatorType,String locatorValue,String value) {
		getelemnet(locatorType, locatorValue).sendKeys(value);
	}
	
	public void eleClick(By locator) {
		getelemnet(locator).click();
	}
	
	public By getBy(String locatorType,String locatorValue) {
		By locator=null;
		
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			locator= By.id(locatorValue);
			break;
		case "name":
			locator= By.name(locatorValue);
			break;
		case "classname":
			locator= By.className(locatorValue);
			break;
		case "xpath":
			locator= By.xpath(locatorValue);
			break;
		case "linktext":
			locator= By.linkText(locatorValue);
			break;
		case "partiallinktext":
			locator= By.partialLinkText(locatorValue);
			break;
		case "tagname":
			locator= By.tagName(locatorValue);
			break;

		default:
			break;
		}
		return locator;
	}
	
	@Step("clicking on element : {0}")
	public void doClick(By locator) {
		getelemnet(locator).click();
	}
	public void doClick(By locator, int timeout) {
		waitForeleVisible(locator, timeout).click();
	}
	
	public String dogetText(By locator) {
		return getelemnet(locator).getText();
	}
	
	public  String doeleGetAttribut(By locator ,String attrName) {
		return getelemnet(locator).getAttribute(attrName);
	}
	
	public  List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	@Step("checking element {0} is displayed ....")
	public  boolean isEleDisplayed(By locator) {
		return getelemnet(locator).isDisplayed();
	}
	
	public  boolean isEleExist(By locator) {
		if(getElements(locator).size()==1) {
			return true;
		}
		return false;
		
	}
	public  boolean multipleEleExist(By locator) {
		if(getElements(locator).size()>0) {
			return true;
		}
		return false;
		
	}

	public  int getelemnetsCount(By locator) {
		return getElements(locator).size();
	}
	public  ArrayList<String> getElemnetsListText(By locator) {
		
		List<WebElement> eleList = getElements(locator);
		ArrayList<String> listText = new ArrayList<String>();
		
		for(WebElement e: eleList) {
			String listTexts =e.getText();
			if(listTexts.length()!=0) {
				listText.add(listTexts);
			}
			
			
		}
		return listText;
	}
	
	public  ArrayList<String> getElemsAttributeList(By locator,String attrName) {
		
		List<WebElement> attrList =getElements(locator);
		ArrayList<String> AttrText = new ArrayList<String>();

		
		
		for(WebElement e : attrList) {
			String attrValue = e.getAttribute(attrName);
			if(attrValue.length()!=0) {
				AttrText.add(attrValue);
			}
		}
		return AttrText;
		
		
		
	}
	public  void doSearch(By searchText,By suggestion,String searchKey,String searchVal) throws InterruptedException {
		
		
		dosendKeys(searchText, searchKey);
				
				Thread.sleep(3000);
				List<WebElement> search_list =getElements(suggestion);
				System.out.println(search_list.size());
				
				for(WebElement e: search_list) {
					
					String text =e.getText();
					System.out.println(text);
					if(text.contains(searchVal)) {
						e.click();
						break;
					}
					
					
				}
			}
	
	//************************** Actions Utils *********************************
	
	public  void handleMouseHover4Menu(By level1menu, By level2menu,By level3menu,By level4menu) throws InterruptedException {
		
		doClick(level1menu);
		Thread.sleep(2000);		
		Actions act = new Actions(driver);
		act.moveToElement(getelemnet(level2menu)).perform();
		Thread.sleep(2000);
		act.moveToElement(getelemnet(level3menu)).perform();
		Thread.sleep(2000);		
		
		doClick(level4menu);
	}
	
	public  void doActionsClick(By locator) {		
		Actions act = new Actions(driver);
		act.click(getelemnet(locator)).perform();
	}

	public  void doActionsSendKeys(By locator , String value) {		
		Actions act = new Actions(driver);
		act.sendKeys(getelemnet(locator), value).perform();
	}
	
	
	//*************Wait Utils*****************************************
	/**
	   * An expectation for checking that an element is present on the DOM of a page. This does not
	   * necessarily mean that the element is visible.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located
	   */
	public  WebElement waitForElePresence(By locator,int timeouts) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		WebElement ele= 	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		checkHighlight(ele);
		return ele;
		
	}
	  /**
	   * An expectation for checking that an element is present on the DOM of a page and visible.
	   * Visibility means that the element is not only displayed but also has a height and width that is
	   * greater than 0.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and visible
	   */
	
	@Step("waiting for element using loactor : {0} within timeout {1}")
	public  WebElement waitForeleVisible(By locator,int timeouts) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		return 	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
		
	}
	
	public  String waitForTitleContains(String titleFraction,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		if(wait.until(ExpectedConditions.titleContains(titleFraction))){
			return driver.getTitle();
		}
		}
		catch(Exception e) {
			System.out.println("title is not found within : "+timeout);
		}
		return null;
		
	}
	
	@Step("waiting for expected title")
	public  String waitForTitleIs(String title,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		if(wait.until(ExpectedConditions.titleIs(title))){
			return driver.getTitle();
		}
		}
		catch(Exception e) {
			System.out.println("title is not found within : "+timeout);
		}
		return null;
		
	}
	@Step("waiting for expected URL")
	public  String waitForURLContains(String urlFraction,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		if(wait.until(ExpectedConditions.urlContains(urlFraction))){
			return driver.getCurrentUrl();
		}
		}
		catch(Exception e) {
			System.out.println("URL fraction is not found within : "+timeout);
		}
		return null;
		
	}
	public  String waitForURLIs(String urlFull,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		try {
		if(wait.until(ExpectedConditions.urlToBe(urlFull))){
			return driver.getCurrentUrl();
		}
		}
		catch(Exception e) {
			System.out.println("URL is not found within : "+timeout);
		}
		return null;
		
	}
	public  Alert waitForJSAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	public  Alert waitForJSAlertwithFluentWait(int timeout , int pollingtime) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingtime))
				.ignoring(NoAlertPresentException.class)
				.withMessage(DEFAULT_ALERT_TIMEOUT_MSG);		
		
		return wait.until(ExpectedConditions.alertIsPresent());
		
	}
	
	public  String getAlertTxt(int timeout) {
		return waitForJSAlert(timeout).getText();
	}
	public  void acceptAlertTxt(int timeout) {
		 waitForJSAlert(timeout).accept();
	}
	public  void dismissAlertTxt(int timeout) {
		 waitForJSAlert(timeout).dismiss();
	}
	public  void alerSendkeys(int timeout,String value) {
		 waitForJSAlert(timeout).sendKeys(value);
	}
	
	public boolean waitForWindow(int timeout, int numberofWindowtobe) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));		
		return wait.until(ExpectedConditions.numberOfWindowsToBe(numberofWindowtobe));
	}
	  /**
	   * An expectation for checking an element is visible and enabled such that you can click it.
	   *
	   * @param locator used to find the element
	   * @return the WebElement once it is located and clickable (visible and enabled)
	   */
	public  void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));		
		 wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		
	}
	 /**
	   * An expectation for checking that there is at least one element present on a web page.
	   *
	   * @param locator used to find the element
	   * @return the list of WebElements once they are located
	   */
	public List<WebElement> waitForelementsPresence(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));		
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
	}
	public List<WebElement> waitForelementsPresencewithFluentWait(By locator,int timeout , int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class)
				.withMessage(DEFAULT_ELE_TIMEOUT_MSG);		
		
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		
	}
	
	
	
	
	  /**
	   * An expectation for checking that all elements present on the web page that match the locator
	   * are visible. Visibility means that the elements are not only displayed but also have a height
	   * and width that is greater than 0.
	   *
	   * @param elements list of WebElements
	   * @return the list of WebElements once they are located
	   */
	public List<WebElement> waitForelementsVisible(By locator,int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));		
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
	public List<WebElement> waitForelementsVisible(By locator,int timeout , int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout),Duration.ofSeconds(intervalTime));		
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	  /**
	   * An expectation for checking whether the given frame is available to switch to.
	   *
	   * <p>If the frame is available it switches the given driver to the specified frame.
	   *
	   * @param locator used to find the frame
	   * @return WebDriver instance after frame has been switched
	   */
	public  void waitforFrameandSwitchtoIt(By frmaeLocator,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frmaeLocator));
		
	}
	public  void waitforFrameandSwitchtoIt(int frameindex,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameindex));
		
	}
	public  void waitforFrameandSwitchtoIt(WebElement frmaeElement,int timeout) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frmaeElement));
		
	}
	
public  WebElement retryingElement(By locator, int timeout) {
		
		WebElement ele=null;
		int attempts =0;
		
		while(attempts<timeout) {
			
			try {
				ele =getelemnet(locator);	
				System.out.println("element is found ..."+locator+"in attempt "+attempts);
			}
			catch(NoSuchElementException e) {
				System.out.println("no element is found ..."+ "in attempts "+attempts);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				
			}
			attempts++;
		}
		
		if(ele == null) {
			System.out.println("ele is not found for " +timeout +"times " +"in the interval of "+500 +" millseconds");
			throw new ElementException("No Such Element");
		}
		return ele;
		
		
	}
	
	
public  WebElement retryingElement(By locator, int timeout, int intervaltime) {
		
		WebElement ele=null;
		int attempts =0;
		
		while(attempts<timeout) {
			
			try {
				ele =getelemnet(locator);	
				System.out.println("element is found ..."+locator+"in attempt "+attempts);
			}
			catch(NoSuchElementException e) {
				System.out.println("no element is found ..."+ "in attempts "+attempts);
				try {
					Thread.sleep(intervaltime*1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				
			}
			attempts++;
		}
		
		if(ele == null) {
			System.out.println("ele is not found for " +timeout +"times " +"in the interval of "+intervaltime +" millseconds");
			throw new ElementException("No Such Element");
		}
		return ele;
		
		
	}

public void scrollIntoView(WebElement elmnt) {
	
	js.executeScript("arguments[0].scrollIntoView(true);", elmnt);
}
	
public void scrollIntoView(By elmnt) {
	
	js.executeScript("arguments[0].scrollIntoView(true);", elmnt);
}
public void scrollPageDown() {
	js.executeScript("window.scrollTo(0,document.body.ScrollHeight)");
}
public void cliclEleByJS(By ele) {
	js.executeScript("arguments[0].click();", ele);
}	
	
}
