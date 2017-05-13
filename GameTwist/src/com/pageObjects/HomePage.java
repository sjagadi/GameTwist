package com.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.generic.Generic;

public class HomePage 
{
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='WOF-close-x']")
	public List<WebElement> checkPopup;
	
	@FindBy(xpath="//span[@class='WOF-close-x']")
	public WebElement closePopup;
	
	@FindBy(xpath="(//i[@class='icon-arrow-down'])[5]")
	public WebElement LanguageMenu;
	
	@FindBy(xpath="(//span[contains(text(), 'English')])[2]")
	public WebElement SelectEnglish;
	
	@FindBy(xpath="//*[@id='navigation-main']/ul/li[1]/a")
	public WebElement SlotTab;
	
	@FindBy(xpath="//*[@id='navigation-main']/ul/li[2]/a")
	public WebElement BingoTab;
	
	@FindBy(xpath="//*[@id='navigation-main']/ul/li[3]/a")
	public WebElement CasinoTab;
	
	@FindBy(xpath="//*[@id='navigation-main']/ul/li[4]/a")
	public WebElement PokerTab;
	
	@FindBy(id="ctl00_cphNavAndSearch_ctl01_gameSearch")
	public WebElement SearchBox;
	
	@FindBy(xpath="//li[@class='game-search__item grid__item one-whole js-game-item']")
	public List<WebElement> SearchResults;
	
	@FindBy(xpath="//button[contains(@class, 'js-game-search-paging-num')]")
	public List<WebElement> PageNumbers;
	
	@FindBy(xpath="(//li[@class='game-search__item grid__item one-whole js-game-item'])[2]")
	private WebElement selectgame;
	
	@FindBy(xpath="(//span[contains(text(), 'Deutsch')])[2]")
	public WebElement SelectGerman;
	
	@FindBy(xpath="//span[@class='nickname']")
	public WebElement LogoutMenu;
	
	@FindBy(xpath="//button[@class='btn--link js-logout']")
	public WebElement Logout;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void VerifySuccessfulLogin()
	{
		if(driver.getTitle().toLowerCase().contains("gametwist"))
		{
			Reporter.log("The user has successfully logged in to the GameTwist application");
		}
	}
	
	public void CheckAndClosePopup() throws InterruptedException
	{
		int popupCount = checkPopup.size();		
		if(popupCount>0)
		 {
			closePopup.click();
			Thread.sleep(3000);
			Reporter.log("Closed the popup");
		 }
	}
	
	public void ChangeLanguageToEnglish() throws InterruptedException
	{
		
		Actions actions = new Actions(driver);
		actions.moveToElement(LanguageMenu).perform();
		SelectEnglish.click();
		Thread.sleep(3000);
	}
	
	public void VerifyTabTitle(String aTitle, String eTitle)
	{
		if(aTitle.contains(eTitle))
		{
			Reporter.log("Navigated to "+driver.getTitle()+" page");
		}
		else
		{
			Reporter.log("The user couldn't navigate to the desired page");
		}
	}
	
	public void ClickTabsAndVerify()
	{
		SlotTab.click();
		VerifyTabTitle(driver.getTitle(), "Slot");
		
		BingoTab.click();
		VerifyTabTitle(driver.getTitle(), "Bingo");
		
		CasinoTab.click();	
		VerifyTabTitle(driver.getTitle(), "Casino");
		
		PokerTab.click();	
		VerifyTabTitle(driver.getTitle(), "Poker");
	}
	
	String searchText = Generic.getConfigValue("SearchText");
	public void SearchText() throws InterruptedException
	{
		SearchBox.sendKeys(searchText);
		Thread.sleep(3000);
		Reporter.log("Entered search text "+searchText+" in the search box");
	}
	public void CountGamesAndClickAnyGame() throws InterruptedException
	{
		List<WebElement> list = SearchResults;
        int count = list.size();
        Reporter.log("Number of pages displayed in the search results: "+count);
        
        List<WebElement> buttons = PageNumbers;
        
        int page = buttons.size();
        
        for(int j = 2; j<=page; j++)
        {
        	driver.findElement(By.xpath("(//button[contains(@class, 'js-game-search-paging-num')])["+j+"]")).click();
        	List<WebElement> items = SearchResults;
        	int itemsCount = items.size();
        	count = count+itemsCount;
        	Thread.sleep(500);
        }
        Reporter.log("Total number of games: "+count);
        if(count>1)
        {
        	driver.navigate().refresh();
        	SearchBox.sendKeys(searchText);
        	Thread.sleep(2000);
        	
        	String gameName = selectgame.getAttribute("data-name");
        	selectgame.click();
        	Reporter.log("Clicked on "+gameName+" game link");
        	driver.getTitle();
        	if(driver.getTitle().contains(gameName))
        	{
        		Reporter.log("The user navigated to "+driver.getTitle()+" page");
        	}
        	Thread.sleep(10000);
        }
	}
	
	public void ClickAnyGame(String searchText) throws InterruptedException
	{
		SearchBox.sendKeys(searchText);
		if(selectgame.isDisplayed())
		{
			selectgame.click();
			Thread.sleep(5000);
		}
		else
		{
			Reporter.log("Only one game displayed in the search result.");
		}
	}
	
	public void ChangeLanguageToGerman() throws InterruptedException
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(LanguageMenu).perform();
		SelectGerman.click();
		Thread.sleep(10000);
		Reporter.log("Changed the language from English to German");
	}
	
	public void LogoutFromTheWebsite() throws InterruptedException
	{
		
		Actions actions = new Actions(driver);
		actions.moveToElement(LogoutMenu).perform();
		Logout.click();
		Thread.sleep(2000);
		Reporter.log("Successfully logged out from the GameTwist application");
	}
}

