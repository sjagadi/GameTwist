package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	@FindBy(name="login-nickname")
	private WebElement unTextBox;
	
	@FindBy(id = "login-password")
	private WebElement pwdTextBox;
	
	@FindBy(xpath = "//span[contains(text(), 'LOG IN')]")
	private WebElement loginBtn;
	
		public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void Login(String username, String password) throws InterruptedException
	{
		unTextBox.sendKeys(username);
		pwdTextBox.sendKeys(password);
		loginBtn.click();
		Thread.sleep(5000);
	}
}

