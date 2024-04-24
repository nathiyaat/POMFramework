package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JavaScriptUtil(WebDriver driver , JavascriptExecutor js) {
		this.driver=driver;
		this.js = js;

	}
	
	public  String getTitlebyJS() {
		return js.executeScript("return document.title").toString();
		
	}
	
	public void generateAlert(String message) {
		js.executeScript("alert ('"+message+"')");
	}
	
	public void generateConfirmPopup(String message) {
		js.executeScript("confirm ('"+message+"')");
	}
	
	public String getPageInnerText() {
		
		return js.executeScript("document.documentElement.innerHTML").toString();
		
	}
	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0,document.body.ScrollHeight)");
	}
	
	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.ScrollHeight,0)");
	}
	
	public void scrollPageDown(String height) {
		js.executeScript("window.scroll(0,'"+height+"')");
	}
	
	public void scrollPageDownMiddlePAge() {
		js.executeScript("window.scroll(0,document.body,scrollHeight/2)");
	}
	public void scrollIntoView(WebElement elmnt) {
		
		js.executeScript("arguments[0].scrollIntoView(true);", elmnt);
	}
	
	public void zoomChromeEdge(String percentage) {
		
		String zoom= "document.body.style.zoom ='"+percentage+"%'";
		js.executeScript(zoom);
	}
	public void drawBorder(WebElement ele) {
		js.executeScript("arguments[0].style.border='3px solid red'", ele);
	}
	
	public void flash(WebElement ele) {
		
		String bgcolor=ele.getCssValue("backgroundColor"); //red		
		for(int i=0;i<5;i++) {
			changeColor("rgb(0,200,0)", ele); //green
			changeColor(bgcolor, ele); //red

		}
		
	}
public void changeColor(String color, WebElement ele) {
		
	js.executeScript("arguments[0].style.backgroundColor='"+color+"'", ele)	;
try {
	Thread.sleep(20);
}
catch(InterruptedException e) {
	
}

}

public void cliclEleByJS(WebElement ele) {
	js.executeScript("arguments[0].click();", ele);
}
public void sendKeysUsinId(String id,String val) {
	
	js.executeScript("document.getElementById('"+id+"').value='"+val+"'");
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
