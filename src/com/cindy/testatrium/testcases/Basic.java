package com.cindy.testatrium.testcases;

import org.testng.annotations.Test;

import com.cindy.testatrium.pageobjects.LoginPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;


import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Basic extends AtriumBaseTestCase {

	private final String siteTitle = "Cindy's Site";
	private final String siteHeader = "SITE MAP FOR CINDY'S SITE";
	

	SiteMapPage siteMapPage;
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("\nCreate driver ...\n");
		createDriver(DriverType.CHROME, 15);
	}

	@Test
	public void f() throws InterruptedException {
		
		openLoginPage();
		openSiteMapPage();
	}

	private void openSiteMapPage() throws InterruptedException {
		System.out.println("\nOpen Site Map ...");
		siteMapPage = loginPage.selectSiteMap();
		System.out.println(" siteMapPage = " + siteMapPage);
		String siteMapTitle = siteMapPage.getTitle();
		String header = siteMapPage.getHeader();
		
		System.out.println(" title  = " + siteMapTitle);
		System.out.println(" header = " + header);
		
		Assert.assertEquals(siteMapTitle, siteTitle);
		Assert.assertEquals(header, siteHeader);
		loginPage = siteMapPage.selectHome();
	}
	
	@AfterClass
	public void afterClass() {

		System.out.println("\nClose driver ...\n");
		driver.close();
	}

}
