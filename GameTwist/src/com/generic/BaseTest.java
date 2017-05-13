package com.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.generic.Generic;

public class BaseTest extends Generic
{
	public WebDriver driver;
	
	@BeforeMethod
	public void preCondition()
	{
		String browser = Generic.getConfigValue("BROWSER");
		if(browser.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver","./exe/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equals("GC"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			System.setProperty("webdriver.chrome.driver", "./exe/chromedriver.exe");
			driver = new ChromeDriver(options);
		}
		else
		{
			//driver = new InternetExplorerDriver();
		}
		String time = Generic.getConfigValue("TIME");
		int Time = Integer.parseInt(time);
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		String URL =  Generic.getConfigValue("URL");
		driver.get(URL);
		driver.findElement(By.xpath("//button[@class = 'btn btn--secondary js-cookie-accept-btn']")).click();
	}
	@AfterMethod
	public void postCondition()
	{
		
		driver.quit();
	}
}