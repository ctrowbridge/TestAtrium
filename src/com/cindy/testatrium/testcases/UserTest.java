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
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("\nCreate driver ...\n");
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
		System.out.println("\nLogin in to site ...");
		homePage = loginPage.login(userName, "admin");
	}
	
	private void logout() throws InterruptedException {
		System.out.println("\nLog out of site ...");
		loginPage = loginPage.logout();
	}
	
	private void openDashboard() throws InterruptedException {
		
		System.out.println("\nOpen user dashboard ...");
		dashboardPage = homePage.selectUserDashboard();
		System.out.println(" dashboardPage = " + dashboardPage);
	}
	
	private void checkDashboard() throws InterruptedException {
				
		String title = dashboardPage.getTitle();
		System.out.println(" title = " + title);
		Assert.assertEquals(title, userName + " | " + siteTitle);
		
		String header = dashboardPage.getHeader();
		System.out.println(" header = " + header);
		Assert.assertEquals(header, userName);
	}
	
	private void checkTasks() {
		System.out.println("\nCheck tasks ...");
		List<Task> taskList = dashboardPage.getTasks();
		
		System.out.println(" taskList size = " + taskList.size());
		Assert.assertEquals(taskList.size(), 0);
	}
	
	private void checkTopics() {
		System.out.println("\nCheck topics ...");
		List<Topic> topicList = dashboardPage.getTopics();
		
		System.out.println(" topicList size = " + topicList.size());
		Assert.assertEquals(topicList.size(), 0);
	}
	
	private void checkReplyTopics() {
		System.out.println("\nCheck topics ...");
		List<Topic> topicList = dashboardPage.getReplyTopics();
		
		System.out.println(" replyTopicList size = " + topicList.size());
		Assert.assertEquals(topicList.size(), 0);
	}
	
	private void openSettings() throws InterruptedException {

		System.out.println("\nOpen user settings page ...");
		settingsPage = homePage.selectUserSettings();
		System.out.println(" userSettings = " + settingsPage);
		System.out.println(" title = " + settingsPage.getTitle());
		
		String pageTitle = settingsPage.getPageTitle();
		System.out.println(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, settingsTitle);
	}
	
	private void checkSettings() throws InterruptedException {
		System.out.println("\nCheck delivery preferences ...");
		UserSettings expectedSettings = new UserSettings(EmailPref.HTML, DigestGroupingPref.ONE_DIGEST);
		UserSettings userSettings = settingsPage.getUserSettings();
		System.out.println(" userSettings = " + userSettings);
		Assert.assertEquals(userSettings, expectedSettings);
		
		System.out.println("\nCheck update of settings ...");
		UserSettings newSettings = new UserSettings(EmailPref.PLAIN_TEXT, DigestGroupingPref.COMBINED_DIGEST);
		settingsPage = settingsPage.updateSettings(newSettings);
		
		UserSettings updatedSettings = settingsPage.getUserSettings();
		System.out.println(" updatedSettings = " + updatedSettings);
		Assert.assertEquals(updatedSettings, newSettings);
		
		System.out.println("\nReset settings ...");
		settingsPage = settingsPage.updateSettings(expectedSettings);
		UserSettings finalSettings = settingsPage.getUserSettings();
		System.out.println(" finalSettings = " + finalSettings);
	}
	
	private void openProfile() throws InterruptedException {
		
		System.out.println("\nOpen Profile ...");
		ProfileAccountPage profileAccountPage = homePage.selectUserProfile();
		System.out.println(" profileAccountPage = " + profileAccountPage);
		System.out.println(" title = " + profileAccountPage.getTitle());
		
		String pageTitle = profileAccountPage.getPageTitle();
		System.out.println(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileAccountTitle);
		
		System.out.println("\nSwitch from Account Info to User Info ...");
		ProfileUserInfoPage userInfoPage = profileAccountPage.switchToUserInfo();
		System.out.println(" userInfoPage = " + userInfoPage);
		System.out.println(" title = " + userInfoPage.getTitle());
		
		pageTitle = userInfoPage.getPageTitle();
		System.out.println(" pageTitle = " + pageTitle);
		Assert.assertEquals(pageTitle, profileUserTitle);
	}
	
	@AfterClass
	public void afterClass() {

		System.out.println("\nClose driver ...\n");
		driver.close();
	}

}
