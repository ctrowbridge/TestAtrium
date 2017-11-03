package com.cindy.testatrium.testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.SeleniumCommon.BaseUtils;
import com.cindy.SeleniumCommon.BaseTestCase.DriverType;

/**
 * Performs various tests on the Login page.
 * 
 * <pre>
 * Feature:  Check Atrium login page
 * Scenario: Check various elements on Atrium login page
 * 
 * Given: User navigates to Atrium Login page
 * Then: User is not logged in yet
 * Then: Title is correct
 * Then: Getting Start URL is correct
 * Then: Search text field is present
 * Then: Search button is present
 * </pre>
 * 
 * @author Cindy
 */
public class CheckLoginPage extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("CheckHomePage");

	@BeforeClass
	public void beforeClass() {

		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	/**
	 * Perform various checks on the Login page.
	 * 
	 * @throws InterruptedException when page times out.
	 * @throws IOException
	 */
	@Test
	public void testLoginPage() throws InterruptedException, IOException {

		openLoginPage();

		// Make sure user isn't logged in
		//
		Assert.assertFalse(loginPage.isLoggedIn());

		// Check title
		//
		logger.info("Check title ...");
		String title = loginPage.getTitle();
		logger.info(" title = " + title);
		Assert.assertEquals(title, siteTitle);

		checkDocumentationUrl();

		// Check menu
		//
		boolean menuExists = loginPage.menuExists();
		logger.info(" menuExists = " + menuExists);
		Assert.assertTrue(menuExists);

		// Check for search elements
		//
		boolean searchTextExists = loginPage.searchFieldExists();
		logger.info(" searchTextExists = " + searchTextExists);
		Assert.assertTrue(searchTextExists);
		boolean searchButtonExists = loginPage.searchButtonExists();
		logger.info(" searchButtonExists = " + searchButtonExists);
		Assert.assertTrue(searchTextExists);
	}

	private void checkDocumentationUrl() {

		logger.info("Check documentation URL ...");
		String url = loginPage.getDocumentationURL();
		logger.info(" url = " + url);
		boolean urlOk = BaseUtils.checkURL(url);
		logger.info(" urlOK = " + urlOk);
		Assert.assertTrue(urlOk);
	}

	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		shutdown();
	}
}
