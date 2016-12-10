package com.cindy.testatrium.testcases;

import org.testng.annotations.Test;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.Topic;
import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.data.UserSettings.DigestGroupingPref;
import com.cindy.testatrium.data.UserSettings.EmailPref;
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
 * Run tests for user.  Assumes new user.
 * 
 * @author Cindy
 */
public class UserTest extends AtriumBaseTestCase {
	
	private HomePage homePage;
	private DashboardPage dashboardPage;
	private SettingsPage settingsPage;
	
	private final String settingsTitle = "Notification Settings";
	private final String profileAccountTitle = "Account Info";
	private final String profileUserTitle = "User Info";
	
	private static final Logger logger = LogManager.getLogger("UserTest");
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	//@Test
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
	
	//@Test
	public void testSettings() throws InterruptedException {
		
		openLoginPage();
		login();

		openSettings();
		checkSettings();
		logout();
	}
	
	@Test
	public void testProfile() throws InterruptedException {
		
		openLoginPage();
		login();
		
		openProfile();
		logout();
	}

	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(userName, "admin");
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
		ProfileAccountPage profileAccountPage = homePage.selectUserProfile();
		logger.info(" profileAccountPage = " + profileAccountPage);
		logger.info(" title = " + profileAccountPage.getTitle());
		
		String pageTitle = profileAccountPage.getPageTitle();
		logger.info(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileAccountTitle);
		
		logger.info("Switch from Account Info to User Info ...");
		ProfileUserInfoPage userInfoPage = profileAccountPage.switchToUserInfo();
		logger.info(" userInfoPage = " + userInfoPage);
		logger.info(" title = " + userInfoPage.getTitle());
		
		pageTitle = userInfoPage.getPageTitle();
		logger.info(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileUserTitle);
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...\n");
		driver.close();
	}

}
