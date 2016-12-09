package com.cindy.testatrium.testcases;

import org.testng.Assert;

import com.cindy.SeleniumCommon.BaseTestCase;
import com.cindy.testatrium.pageobjects.LoginPage;

public class AtriumBaseTestCase extends BaseTestCase {

	LoginPage loginPage;
	
	protected final String userName = "user";
	protected final String siteTitle = "Cindy's Site";
	
	protected void openLoginPage() throws InterruptedException {
		System.out.println("\nOpen Login page ...");
		loginPage = new LoginPage(driver);
		loginPage = loginPage.open();
		System.out.println(" loginPage = " + loginPage);
		String loginPageTitle = loginPage.getTitle();
		
		System.out.println(" title = " + loginPageTitle);
		Assert.assertEquals(siteTitle, siteTitle);
	}

}
