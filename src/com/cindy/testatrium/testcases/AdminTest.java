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
import com.cindy.testatrium.pageobjects.PeoplePage;
import com.cindy.testatrium.pageobjects.ReportsPage;
import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.UserInfo;


/**
 * Contains various tests for admin functionality.
 * 
 * @deprecated Replaced by "admin.feature"
 * @author Cindy
 */
@Deprecated
public class AdminTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("AdminTest");
	private static final String expectedHeader = "Administration";
	private static final String peopleExpectedHeader = "People";
	private static final String noOp = "No operation selected. Please select an operation to perform.";
	private static final String noItem = "Please select at least one item.";

	HomePage homePage;
	AdminPage adminPage;
	ReportsPage reportsPage;
	PeoplePage peoplePage;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	/**
	 * Tests various capabilities of the Admin page.
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testAdminPage() throws InterruptedException {
		
		openLoginPage();
		login();
		openAdminPage();
		checkTasks();
		
		openReportsPage();
		reportsPage.back();
		
		openPeoplePage();
		checkPeoplePage();
		adminPage = adminPage.open();
	}

	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(mainUser);
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
		String breadcrumbs = adminPage.getBreadcrumbs();
		logger.info(" breadcrumbs = " + breadcrumbs);
	}
	
	private void checkTasks() {
		logger.info("Get list of tasks ...");
		List<Task> tasks = adminPage.getTasks();
		for (Task task : tasks) {
			logger.info(" task = " + task);
		}
	}
	
	private void openReportsPage() throws InterruptedException {
		
		logger.info("Open Reports page ...");
		reportsPage = adminPage.selectReportsPage();
		logger.info(" reportsPage = " + reportsPage);
		logger.info(" breadcrumbs = " + reportsPage.getBreadcrumbs());
	}
	
	private void openPeoplePage() throws InterruptedException {
		
		logger.info("Open People page ...");
		peoplePage = adminPage.selectPeoplePage();
		logger.info(" peoplePage = " + peoplePage);
		logger.info(" breadcrumbs = " + peoplePage.getBreadcrumbs());
		String title = peoplePage.getTitle();
		logger.info(" title = " + title);
	}
	
	private void checkPeoplePage() throws InterruptedException {
		
		String header = peoplePage.getHeader();
		Assert.assertEquals(header, peopleExpectedHeader);
		logger.info(" header = " + header);
		
		logger.info("Get people ...");
		List<UserInfo> people = peoplePage.getPeople();
		logger.info(" people size = " + people.size());
		for (UserInfo person : people) {
			logger.info("  name   = " + person.getUsername());
			logger.info("  email  = " + person.getEmail());
			logger.info("  status = " + person.getStatus());
		}
		
		logger.info("Apply filter ...");
		peoplePage.setFilterUsername("XXXXXXX");
		peoplePage.selectApplyFilter();
		people = peoplePage.getPeople();
		logger.info(" people size = " + people.size());		
		Assert.assertEquals(people.size(), 0);

		logger.info("Click Execute ...");
		peoplePage = peoplePage.selectExecute();
		String errorMessage = peoplePage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(noOp));
		Assert.assertTrue(errorMessage.contains(noItem));
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...\n");
		driver.close();
	}
}
