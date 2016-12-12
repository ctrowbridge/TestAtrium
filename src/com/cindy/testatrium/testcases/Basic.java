package com.cindy.testatrium.testcases;

import com.cindy.testatrium.pageobjects.SearchResultsPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;
import com.cindy.SeleniumCommon.BaseUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

/**
 * Contains some basic tests for Atrium that can be run without logging
 * in.  Assumes user is logged out.
 * 
 * @author Cindy
 */
public class Basic extends AtriumBaseTestCase {

	private final String siteTitle = "Cindy's Site";
	private final String siteHeader = "SITE MAP FOR CINDY'S SITE";
	private static final Logger logger = LogManager.getLogger("Basic");
	
	SiteMapPage siteMapPage;
	SearchResultsPage searchResultsPage;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	/**
	 * Checks login and site map
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void basicTest() throws InterruptedException {
		
		logger.info("Start basicTest");
		
		openLoginPage();
		checkLoginPage();
		openSiteMapPage();
	}

	/**
	 * Opens the Site Map page
	 * 
	 * @throws InterruptedException
	 */
	private void openSiteMapPage() throws InterruptedException {
		
		logger.info("Open Site Map ...");
		siteMapPage = loginPage.selectSiteMap();
		logger.info(" siteMapPage = " + siteMapPage);
		String siteMapTitle = siteMapPage.getTitle();
		String header = siteMapPage.getHeader();
		
		logger.info(" title  = " + siteMapTitle);
		logger.info(" header = " + header);
		
		Assert.assertEquals(siteMapTitle, siteTitle);
		Assert.assertEquals(header, siteHeader);
		loginPage = siteMapPage.selectHome();
		
	}
	
	private void checkLoginPage() throws InterruptedException {
		
		// Make sure user isn't logged in
		//
		Assert.assertFalse(loginPage.isLoggedIn());
		
		// Check documentation url
		//
		logger.info("Check documentation URL ...");
		String url = loginPage.getDocumentationURL();
		logger.info(" url = " + url);
		boolean urlOk = BaseUtils.checkURL(url);
		logger.info(" urlOK = " + urlOk);
		Assert.assertTrue(urlOk);
		
		// Try searching
		//
		logger.info("Check search ...");
		loginPage.enterSearchString("hello");
		searchResultsPage = loginPage.selectSearch();
		logger.info(" searchResultsPage = " + searchResultsPage);
		
		String title = searchResultsPage.getTitle(); 
		logger.info(" title = " + title);
		Assert.assertEquals(title, "Search | " + siteTitle);
		logger.info(" results found = " + searchResultsPage.noResultsFound());
		Assert.assertTrue(searchResultsPage.noResultsFound());
		
		loginPage = searchResultsPage.selectHome();
	}	
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}

}
