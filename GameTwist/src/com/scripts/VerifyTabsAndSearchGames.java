package com.scripts;

import org.testng.annotations.Test;

import com.generic.BaseTest;
import com.generic.Generic;
import com.pageObjects.HomePage;
import com.pageObjects.LoginPage;

public class VerifyTabsAndSearchGames extends BaseTest
{
	@Test
	public void TabsAndSearchGamesTest() throws InterruptedException
	{
		String un = Generic.getConfigValue("UserName");
		String pwd = Generic.getConfigValue("Password");
		// 1. Successful Login to the https://www.gametwist.com/en/
		new LoginPage(driver).Login(un, pwd);
		
		HomePage homePage = new HomePage(driver);
		
		// 2. Close any popups IF they appear.
		homePage.CheckAndClosePopup();
		
		homePage.VerifySuccessfulLogin();
		homePage.ChangeLanguageToEnglish();
		
		// 3. Navigate through the pages Slots, Bingo, Casino & Poker and check if you are on the correct page after each navigation.
		homePage.ClickTabsAndVerify();
		
		// 4. Search for 'Slot’ on the website in the search games section.		
		homePage.SearchText();
		
		// 5. Count a number of shown games and select one of them (but not the first or last one).
		// 6. Confirm that you are on the correct game page.
		homePage.CountGamesAndClickAnyGame();
		
		// 7. Change the language from English to German.
		homePage.ChangeLanguageToGerman(); 
		
		// 8. Successfully logout.
		homePage.LogoutFromTheWebsite();
	}
}

