package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
WebDriver driver;

Properties prop;	
OptionsManager optionsManager;

public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); 

public static String highlight;

	public WebDriver initDriver(Properties prop) {
		
		String browserName =prop.getProperty("browser");
		
		System.out.println("Browser name is "+browserName);
		
		Log.info("Browser name is "+browserName);
		
		highlight=prop.getProperty("highlight");
		
		optionsManager=new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//remote grid execution
				init_remoteDriver("chrome");
			}
			else {
				//run on local execution
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			
			
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			//tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			
			break;
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//remote grid execution
				init_remoteDriver("firefox");
			}
			else {
				//run on local execution
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			//tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			break;
			
		case "edge":
			if(Boolean.parseBoolean(prop.getProperty("remote"))) {
				//remote grid execution
				init_remoteDriver("edge");
			}
			else {
				//run on local execution
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			
			//driver=new EdgeDriver(optionsManager.getEdgeOptions());
			//tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;
		case "safari":
			driver=new SafariDriver();
			break;
		default:
			Log.error("Plz pass the right browser "+browserName);
			//System.out.println("Plz pass the right browser");
			throw new BrowserException("No BROWSER FOUND"+browserName);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();	
		
	}
	/**
	 * 
	 * run tests on remote execution
	 * @param browserName
	 */
	
	private void init_remoteDriver(String browserName) {
		System.out.println("Running tests on GRID --- REMOTE on browser "+browserName);
		
		try {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":		
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));						
			break;
		case "firefox":				
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));						
			break;
		case "edge":				
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));						
			break;
		default:
			System.out.println("Plz pass the right supported browser on GRID ....");
			break;
		}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static WebDriver getDriver() {
		
		return tlDriver.get();
		
	}
	
	
	
	public Properties initProp() {
		//env name : qa,uat,dev,stage
		//maven clean install -Denv="qa"
		FileInputStream ip = null;
		prop=new Properties();

		
		String envName = System.getProperty("env");
		System.out.println("Running tests on Env "+envName);
		
		try {
		if(envName==null) {
			System.out.println("No env name is given.... hence running it on QA env");
			 ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

		}
		else {
		
		switch (envName.toLowerCase().trim()) {
		case "qa":
			 ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			break;
		case "dev":
			 ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
			break;
		case "uat":
			 ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
			break;
		case "stage":
			 ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
			break;
		case "prod":
			 ip = new FileInputStream("./src/test/resources/config/config.properties");
			break;
		default:
			System.out.println("plz pass right env name..."+envName);
			throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : "+envName);
		}
		
		}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * take screenshot
	 * 
	 */
	public static String getScreenshot(String methodName) {
		
		File srcFile= ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+"/screenshot/" + methodName+ "_" + System.currentTimeMillis() + ".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
		
	}
	
	
	
	
	
	
	
	
	
}
