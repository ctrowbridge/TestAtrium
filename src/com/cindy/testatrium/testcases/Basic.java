package com.cindy.testatrium.testcases;

import com.cindy.testatrium.pageobjects.AdminPage;
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
 * in.
 * 
 * @author Cindy
 */
public class Basic extends AtriumBaseTestCase {

	/**
	 * Title for site - this is set when the Bitnami Atrium installer is run.
	 * TODO Read site title from a configuration file.
	 */
	private final String siteTitle = "Cindy's Site";
	private final String siteHeader = "SITE MAP FOR " + siteTitle.toUpperCase();
	private final String adminHeader = "Access Denied";
	
	private static final Logger logger = LogManager.getLogger("Basic");
	
	SiteMapPage siteMapPage;
	SearchResultsPage searchResultsPage;
	AdminPage adminPage;
	
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
		openAdminPage();
	}
	
	private void checkLoginPage() throws InterruptedException {
		
		// Make sure user isn't logged in
		//
		Assert.assertFalse(loginPage.isLoggedIn());
		
		// Check documentation URL
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
	
	/**
	 * Opens the Admin page. Note that an "Access Denied" page should appear
	 * since the user isn't logged in.
	 * 
	 * @throws InterruptedException
	 */
	private void openAdminPage() throws InterruptedException {
		logger.info("Open Admin Page ...");
		adminPage = new AdminPage(driver);
		adminPage.openNotAuthorized();
		String header = adminPage.getHeader();
		logger.info(" header = " + header);
		Assert.assertEquals(header, adminHeader);
		
		loginPage = adminPage.selectHome();
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}

}
