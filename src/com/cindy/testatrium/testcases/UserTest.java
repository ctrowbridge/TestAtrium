package com.cindy.testatrium.testcases;

import org.testng.annotations.Test;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.Topic;
import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.data.UserSettings.DigestGroupingPref;
import com.cindy.testatrium.data.UserSettings.EmailPref;
import com.cindy.testatrium.pageobjects.AddUserPage;
import com.cindy.testatrium.pageobjects.DashboardPage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.pageobjects.ProfileAccountPage;
import com.cindy.testatrium.pageobjects.ProfileUserInfoPage;
import com.cindy.testatrium.pageobjects.SettingsPage;

import org.testng.annotations.BeforeClass;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains various tests for user-related functionality.
 * 
 * @author Cindy
 */
public class UserTest extends AtriumBaseTestCase {

	private HomePage homePage;
	private DashboardPage dashboardPage;
	private SettingsPage settingsPage;
	private ProfileUserInfoPage userInfoPage;
	private ProfileAccountPage profileAccountPage;

	private final String settingsTitle = "Notification Settings";
	private final String profileAccountTitle = "Account Info";
	private final String profileUserTitle = "User Info";
	private final String displayErrorMessage = "Display Name field is required.";
	private final String userErrorMessage = "Username field is required.";
	private final String emailErrorMessage = "E-mail address field is required.";

	private static final Logger logger = LogManager.getLogger("UserTest");

	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	/**
	 * Test the User Dashboard page
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void testUserDashboard() throws InterruptedException {

		openLoginPage();
		login();

		openDashboard();
		checkDashboard();
		checkTasks();
		checkTopics();
		checkReplyTopics();

		logout();
	}

	/**
	 * Test the Settings page
	 * 
	 * @throws InterruptedException
	 */
	//@Test
	public void testSettings() throws InterruptedException {

		openLoginPage();
		login();

		openSettings();
		checkSettings();
		logout();
	}

	/**
	 * Tests the User Profile Account Info page and the User Profile User Info
	 * page.
	 * 
	 * @throws InterruptedException
	 */
	//@Test
	public void testProfile() throws InterruptedException {

		openLoginPage();
		login();

		openProfile();
		checkProfile();
		
		logout();
	}
	
	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(mainUser);
	}

	private void logout() throws InterruptedException {
		logger.info("Log out of site ...");
		loginPage = loginPage.logout();
	}

	private void openDashboard() throws InterruptedException {

		logger.info("Open user dashboard ...");
		dashboardPage = homePage.selectUserDashboard();
		logger.info(" dashboardPage = " + dashboardPage);
	}

	private void checkDashboard() throws InterruptedException {

		String title = dashboardPage.getTitle();
		logger.info(" title = " + title);
		Assert.assertEquals(title, userName + " | " + siteTitle);

		String header = dashboardPage.getHeader();
		logger.info(" header = " + header);
		Assert.assertEquals(header, userName);
	}

	private void checkTasks() {
		logger.info("Check tasks ...");
		List<Task> taskList = dashboardPage.getTasks();

		logger.info(" taskList size = " + taskList.size());
		Assert.assertEquals(taskList.size(), 0);
	}

	private void checkTopics() {
		logger.info("Check topics ...");
		List<Topic> topicList = dashboardPage.getTopics();

		logger.info(" topicList size = " + topicList.size());
		Assert.assertEquals(topicList.size(), 0);
	}

	private void checkReplyTopics() {
		logger.info("\nCheck topics ...");
		List<Topic> topicList = dashboardPage.getReplyTopics();

		logger.info(" replyTopicList size = " + topicList.size());
		Assert.assertEquals(topicList.size(), 0);
	}

	private void openSettings() throws InterruptedException {

		logger.info("Open user settings page ...");
		settingsPage = homePage.selectUserSettings();
		logger.info(" userSettings = " + settingsPage);
		logger.info(" title = " + settingsPage.getTitle());

		String pageTitle = settingsPage.getPageTitle();
		logger.info(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, settingsTitle);
	}

	private void checkSettings() throws InterruptedException {
		
		logger.info("Check delivery preferences ...");
		UserSettings expectedSettings = new UserSettings(EmailPref.HTML, DigestGroupingPref.ONE_DIGEST);
		UserSettings userSettings = settingsPage.getUserSettings();
		logger.info(" userSettings = " + userSettings);
		Assert.assertEquals(userSettings, expectedSettings);

		logger.info("Check update of settings ...");
		UserSettings newSettings = new UserSettings(EmailPref.PLAIN_TEXT, DigestGroupingPref.COMBINED_DIGEST);
		settingsPage = settingsPage.updateSettings(newSettings);

		UserSettings updatedSettings = settingsPage.getUserSettings();
		logger.info(" updatedSettings = " + updatedSettings);
		Assert.assertEquals(updatedSettings, newSettings);

		logger.info("Reset settings ...");
		settingsPage = settingsPage.updateSettings(expectedSettings);
		UserSettings finalSettings = settingsPage.getUserSettings();
		logger.info(" finalSettings = " + finalSettings);
	}

	private void openProfile() throws InterruptedException {

		logger.info("Open Profile ...");
		profileAccountPage = homePage.selectUserProfile();
		logger.info(" profileAccountPage = " + profileAccountPage);
		logger.info(" title              = " + profileAccountPage.getTitle());

		String pageTitle = profileAccountPage.getPageTitle();
		logger.info(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileAccountTitle);

		logger.info("Switch from Account Info to User Info ...");
		userInfoPage = profileAccountPage.switchToUserInfo();
		logger.info(" userInfoPage = " + userInfoPage);
		logger.info(" title = " + userInfoPage.getTitle());

		pageTitle = userInfoPage.getPageTitle();
		logger.info(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileUserTitle);
	}

	private void checkProfile() throws InterruptedException {

		// Make sure error message is initially blank
		//
		logger.info("Check error message ...");
		String errorMessage = userInfoPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertEquals(errorMessage, "");

		// Make sure Display Name is required
		//
		logger.info("Check user info required fields ...");
		userInfoPage.setDisplayName("");
		userInfoPage.selectSaveButton();
		errorMessage = userInfoPage.getErrorMessage();

		logger.info(" errorMessage = " + errorMessage);
		Assert.assertEquals(errorMessage, displayErrorMessage);
		userInfoPage.closeErrorMessage();

		logger.info("Check account info required fields ...");
		profileAccountPage = userInfoPage.switchToAccountInfo();

		// Make sure Username field is required
		//
		profileAccountPage.setUserName("");
		profileAccountPage.selectSaveButton();
		errorMessage = profileAccountPage.getErrorMessage();
		
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(userErrorMessage));
		profileAccountPage.closeErrorMessage();

		// Make sure Display Name field is required
		//
		profileAccountPage.setDisplayName("");
		profileAccountPage.selectSaveButton();
		errorMessage = profileAccountPage.getErrorMessage();
		
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(displayErrorMessage));
		profileAccountPage.closeErrorMessage();

		// Make sure Email field is required
		//
		profileAccountPage.setEmail("");
		profileAccountPage.selectSaveButton();
		errorMessage = profileAccountPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);

		Assert.assertTrue(errorMessage.contains(emailErrorMessage));
		profileAccountPage.closeErrorMessage();
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...\n");
		driver.close();
	}

}
