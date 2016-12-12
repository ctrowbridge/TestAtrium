package com.cindy.testatrium.testusecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.SeleniumCommon.BaseTestCase.DriverType;
import com.cindy.testatrium.pageobjects.AtriumBasePage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.pageobjects.SiteMapPage;
import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Contains tests to run Scenario 1
 * 
 * @author Cindy
 */
/*-
 * Scenario 1:  Create a new Space
 * 		given  A new instance of OpenAtrium
 *  	and    OpenAtrium is running
 *  	then   Open Login page
 * 		and    Log in as user
 *  	and    Create a default space
 *      and    Ensure default space
 *      and    Logoff
 */
public class CreateSpaceTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("CreateSpaceTest");
	HomePage homePage;
	SiteMapPage siteMapPage;

	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	@Test
	public void basicTest() throws InterruptedException {
		
		logger.info("Start CreateSpaceTest");
		
		openLoginPage();
		login();
		createDefaultSpace();
			
		logout();
	}

	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(userName, "admin");
	}

	private void createDefaultSpace() throws InterruptedException {
		logger.info("Click on Create Content ...");
		siteMapPage = homePage.selectCreateContent();
		logger.info(" siteMapPage = " + siteMapPage);
		
	}
	
	private void logout() throws InterruptedException {
		logger.info("Log out of site ...");
		loginPage = loginPage.logout();
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}
}
