package com.cindy.testatrium.testcases;

import org.testng.annotations.Test;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.Topic;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.data.AccountInfo.Status;
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
 * Contains tests for the Add New User page
 * 
 * @author Cindy
 */
public class AddUserTest extends AtriumBaseTestCase {

	private HomePage homePage;
	private AddUserPage addUserPage;

	private final String displayErrorMessage = "Display Name field is required.";
	private final String userErrorMessage = "Username field is required.";
	private final String emailErrorMessage = "E-mail address field is required.";
	private final String passwordErrorMessage = "Password field is required.";
	private final String passwordMismatch = "Passwords match: no";
	private final String passwordMismatchErrorMessage = "The specified passwords do not match.";
	private final String invalidCharacterMessage = "The username contains an illegal character.";
	private final String validUserName = "user.name-valid_test'ca se";
	private final String successMessage = "Created a new user account for user.test. No e-mail has been sent.";

	private final String userNameTest = "user.test";
	private final String emailTest = "user@dummy.com";
	private final String passwordTest = "password";
	private final UserInfo.Status statusTest = UserInfo.Status.ACTIVE;
	private final String displayNameTest = "User Test";

	private static final Logger logger = LogManager.getLogger("UserTest");

	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	@Test
	public void testNewUser() throws InterruptedException {

		openLoginPage();
		login();

		openPage();
		checkRequiredFields();
		checkPasswordMismatch();
		checkPasswordMatch();
		checkInvalidUserName();
		checkValidUserName();
		checkStatus();
		checkRoles();

		checkAdd();

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

	private void openPage() {
		addUserPage = new AddUserPage(driver);
		logger.info(" addUserPage = " + addUserPage);
		addUserPage = addUserPage.open();
	}

	/**
	 * Checks for required fields. Each required field will give an error
	 * message when you submit the page.
	 */
	private void checkRequiredFields() {

		logger.info("Check required fields for adding a new User ...");

		// Add user with no data
		//
		addUserPage = addUserPage.selectCreateNewAccount();

		// Check required fields
		//
		String errorMessage = addUserPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(userErrorMessage));
		Assert.assertTrue(errorMessage.contains(emailErrorMessage));
		Assert.assertTrue(errorMessage.contains(passwordErrorMessage));
		Assert.assertTrue(errorMessage.contains(displayErrorMessage));
	}

	/**
	 * Deliberately enters the wrong passwords. This should give you an error
	 * message next to the second password field immediately after entering a
	 * mismatched password. This should also given you an error message if you
	 * submit the page.
	 */
	private void checkPasswordMismatch() {

		logger.info("Check password mismatch");

		addUserPage = addUserPage.setPasswords("pass1", "pass2");
		String passwordConfirm = addUserPage.getPasswordConfirm();
		logger.info(" passwordConfirm = " + passwordConfirm);
		Assert.assertTrue(passwordConfirm.contains(passwordMismatch));

		addUserPage = addUserPage.selectCreateNewAccount();
		String errorMessage = addUserPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(passwordMismatchErrorMessage));
	}

	private void checkPasswordMatch() {

		logger.info("Check password match");

		addUserPage = addUserPage.setPasswords("password", "password");
		String passwordConfirm = addUserPage.getPasswordConfirm();
		logger.info(" passwordConfirm = " + passwordConfirm);
		Assert.assertTrue(!passwordConfirm.contains(passwordMismatch));
	}

	// Spaces are allowed; punctuation is not allowed except for periods,
	// hyphens, apostrophes, and underscores.

	private void checkInvalidUserName() {

		logger.info("Check invalid user name");

		String defaultUserName = addUserPage.getUsername();
		logger.info(" defaultUserName = " + defaultUserName);
		
		addUserPage = addUserPage.setUsername("user$$$");
		addUserPage = addUserPage.selectCreateNewAccount();
		String errorMessage = addUserPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(invalidCharacterMessage));
	}

	private void checkValidUserName() {

		logger.info("Check valid user name ...");

		addUserPage = addUserPage.setUsername(validUserName);
		addUserPage = addUserPage.selectCreateNewAccount();
		String errorMessage = addUserPage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(!errorMessage.contains(invalidCharacterMessage));
		
		String userName = addUserPage.getUsername();
		logger.info(" userName = " + userName);
		Assert.assertEquals(userName, validUserName);
	}

	private void checkStatus() {

		logger.info("Check status ...");
		UserInfo.Status defaultStatus = addUserPage.getStatus();
		logger.info(" defaultStatus = " + defaultStatus);
		Assert.assertEquals(defaultStatus, UserInfo.Status.ACTIVE);

		addUserPage.setStatus(UserInfo.Status.BLOCKED);
		UserInfo.Status blockedStatus = addUserPage.getStatus();
		logger.info(" blockedStatus = " + blockedStatus);
		Assert.assertEquals(blockedStatus, UserInfo.Status.BLOCKED);

		addUserPage.setStatus(UserInfo.Status.ACTIVE);
		UserInfo.Status activeStatus = addUserPage.getStatus();
		logger.info(" activeStatus = " + activeStatus);
		Assert.assertEquals(activeStatus, UserInfo.Status.ACTIVE);
	}

	private void checkRoles() {

		logger.info("Check roles ...");

		boolean defaultUser = addUserPage.getRole(UserInfo.Roles.AUTHENTICATED_USER);
		logger.info(" defaultUser = " + defaultUser);
		Assert.assertEquals(defaultUser, true);

		boolean defaultAdmin = addUserPage.getRole(UserInfo.Roles.ADMINSTRATOR);
		logger.info(" defaultAdmin = " + defaultAdmin);
		Assert.assertEquals(defaultAdmin, false);

		boolean defaultEditor = addUserPage.getRole(UserInfo.Roles.EDITOR);
		logger.info(" defaultEditor = " + defaultEditor);
		Assert.assertEquals(defaultEditor, false);
		
		addUserPage.setRole(UserInfo.Roles.ADMINSTRATOR);
		addUserPage.setRole(UserInfo.Roles.EDITOR);
		
		boolean admin = addUserPage.getRole(UserInfo.Roles.ADMINSTRATOR);
		logger.info(" admin = " + admin);
		Assert.assertEquals(admin, true);
		
		boolean editor = addUserPage.getRole(UserInfo.Roles.EDITOR);
		logger.info(" editor = " + editor);
		Assert.assertEquals(editor, true);
		
		addUserPage = addUserPage.clearRoles();
		
		admin = addUserPage.getRole(UserInfo.Roles.ADMINSTRATOR);
		logger.info(" admin = " + admin);
		Assert.assertEquals(admin, false);
		
		editor = addUserPage.getRole(UserInfo.Roles.EDITOR);
		logger.info(" editor = " + editor);
		Assert.assertEquals(editor, false);
	}

	private void checkAdd() {

		logger.info("Check add ...");

		addUserPage = addUserPage.setUsername(userNameTest);
		addUserPage = addUserPage.setEmail(emailTest);
		addUserPage = addUserPage.setPasswords(passwordTest, passwordTest);
		addUserPage = addUserPage.setStatus(statusTest);
		addUserPage = addUserPage.clearRoles();
		addUserPage = addUserPage.setDisplayName(displayNameTest);

		String name = addUserPage.getUsername();
		logger.info(" name - " + name);
		Assert.assertEquals(name, userNameTest);

		String email = addUserPage.getEmail();
		logger.info(" email = " + email);
		Assert.assertEquals(email, emailTest);
		
		String display = addUserPage.getDisplayName();
		logger.info(" display = " + display);
		Assert.assertEquals(display, displayNameTest);
		
		addUserPage = addUserPage.selectCreateNewAccount();
		String message = addUserPage.getErrorMessage();
		logger.info(" message = " + message);
		Assert.assertEquals(message, successMessage);
	}

	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...\n");
		driver.close();
	}

}
