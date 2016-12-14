package com.cindy.testatrium.testcases;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.HomePage;

/**
 * Contains various tests for admin functionality.
 * 
 * @author Cindy
 */
public class AdminTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("AdminTest");
	private static final String expectedHeader = "Administration";

	HomePage homePage;
	AdminPage adminPage;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	@Test
	public void testAdminPage() throws InterruptedException {
		openLoginPage();
		login();
		openAdminPage();
		checkTasks();
		
	}

	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(userName, "admin");
		logger.info(" homePage = " + homePage);
	}
	
	private void openAdminPage() throws InterruptedException {

		logger.info("Open Admin page ...");
		adminPage = new AdminPage(driver);
		adminPage = adminPage.open();
		logger.info(" adminPage = " + adminPage);
		String title = adminPage.getTitle();
		logger.info(" title = " + title);
		String header = adminPage.getHeader();
		logger.info(" header = " + header);
		Assert.assertEquals(header, expectedHeader);
	}
	
	private void checkTasks() {
		logger.info("Get list of tasks ...");
		List<String> tasks = adminPage.getTasks();
		for (String task : tasks) {
			logger.info(" task = " + task);
		}
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...\n");
		driver.close();
	}
}
