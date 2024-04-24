package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//1, private By locatoirs	
	
	private By productHeader = By.tagName("h1");
	private By images=By.cssSelector("ul.thumbnails img");
	private By qty=By.name("quantity");
	private By Addtocart=By.cssSelector("button#button-cart");
	
	private By shoppingcartLink = By.linkText("shopping cart");
	
	//private Map<String,String> prodMap=new HashMap<String,String>();
	
	private Map<String,String> prodMap=new LinkedHashMap<String,String>();


	
	private By prodMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By prodPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);	
		
	}
	public String getProductHeader() {
		String header =eleUtil.dogetText(productHeader);
		System.out.println("Searching for product : "+header);
		return header;
	}
	
	public int getProdImagesCount() {
		int prodImagesCount =eleUtil.waitForelementsVisible(images, 10).size();
		System.out.println("Image count for  "+getProductHeader() +":"+prodImagesCount);
		return prodImagesCount;
		
	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	
	
	public void getProdMetaData() {
		List<WebElement> metaDataList =eleUtil.getElements(prodMetaData);
		
		for(WebElement e: metaDataList) {
			String text=e.getText();
			String metaKey =text.split(":")[0].trim();
			String metaValue =text.split(":")[1].trim();

			prodMap.put(metaKey, metaValue);
		}		
		
	}
	
//	$2,000.00
//	Ex Tax: $2,000.00
	public void getProductPriceData() {
		List<WebElement> priceList =eleUtil.getElements(prodPriceData);

		String price =priceList.get(0).getText();
		String exTaxPrice =priceList.get(1).getText().split(":")[1].trim();
		
		prodMap.put("prodPrice", price);
		prodMap.put("exTaxPrice", exTaxPrice);
	}
	public Map<String, String> getProductDetailsMap() {
		prodMap.put("header", getProductHeader());
		getProdMetaData();
		getProductPriceData();
		prodMap.put("prodImages", String.valueOf(getProdImagesCount()));
		
		return prodMap;
		
	}
	
	public ShoppingcartPage enterQty() {
		eleUtil.dosendKeys(qty, "1");
		eleUtil.doClick(Addtocart);
		
		String successTxt =eleUtil.dogetText(By.xpath("//div[text()='Success: You have added ']"));
		System.out.println("success text on the page "+successTxt);
		
		eleUtil.doClick(shoppingcartLink);
		
		return new ShoppingcartPage(driver);
		
	}
	
	
}
