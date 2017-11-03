package com.cindy.testatrium.testusecases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.SeleniumCommon.BaseTestCase.DriverType;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.data.UserInfo.Status;
import com.cindy.testatrium.pageobjects.AddUserPage;
import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.pageobjects.PeoplePage;
import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Contains test to run Scenario 2
 * 
 * @author Cindy
 * 
 * @deprecated Replaced by add_user feature
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
 *      
 */
@Deprecated
public class AddUserTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("AddUserTest");
	
	HomePage homePage;
	AdminPage adminPage;
	PeoplePage peoplePage;
	AddUserPage addUserPage;
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}
	
	@Test
	public void testAddUser() throws InterruptedException, IOException {
		openLoginPage();
		login();
		openAdminPage();
		selectAddNewUser();
		addNewUser();
		checkNewUser();
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
	}
	
	private void selectAddNewUser() throws InterruptedException {
		
		logger.info("Select Add New User ...");
		peoplePage = adminPage.selectPeoplePage();
		logger.info(" peoplePage = " + peoplePage);
		addUserPage = peoplePage.selectAddUser();
		logger.info(" addUserPage = " + addUserPage);
		
	}
	
	private void addNewUser() throws IOException {
		
		logger.info("Add new user ...");
		
		UserInfo newUser = new UserInfo();
		int userCounter = getUserCounter();
		updateUserCounter();
		String userName = String.format("user%03d", userCounter);
		logger.info(" username = " + userName);
		newUser.setUsername(userName);
		newUser.setEmail(userName + "@dummy.com");
		newUser.setStatus(Status.ACTIVE);
		newUser.setPassword("password");
		newUser.setDisplayName(userName);
		
		addUserPage = addUserPage.addUser(newUser);
		String errorMessage = addUserPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
	}
	
	private void checkNewUser() {
		
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}
}
