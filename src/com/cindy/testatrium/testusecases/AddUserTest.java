package com.cindy.testatrium.testusecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.SeleniumCommon.BaseTestCase.DriverType;
import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Contains test to run Scenario 2
 * 
 * @author Cindy
 */
/*-
 * Scenario 2: Add User to Site
 *  	given  A new instance of OpenAtrium
 *  	and    OpenAtrium is running
 *  	then   Open Login page
 * 		and    Log in as user
 *  	and    Select option to add new user
 *      and    Add new user
 *      and    Ensure new user was added
 *      and    Logoff
 */
public class AddUserTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("AddUserTest");
	
	HomePage homePage;
	AdminPage adminPage;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}
	
	@Test
	public void testAddUser() throws InterruptedException {
		openLoginPage();
		login();
	}
	
	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(userName, "admin");
	}
	
	private void openAdminPage() throws InterruptedException {

		logger.info("Open Admin page ...");
		adminPage = new AdminPage(driver);
		adminPage = adminPage.open();
		logger.info(" adminPage = " + adminPage);
		String title = adminPage.getTitle();
		logger.info(" title = " + title);
		String header = adminPage.getHeader();
		
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}
}
