package com.cindy.testatrium.steps;

import java.util.List;

import org.testng.Assert;

import com.cindy.SeleniumCommon.BaseUtils;
import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.pageobjects.PeoplePage.Operation;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Contains step definitions for Cucumber steps for User features.
 * 
 * @author Cindy
 */
public class UserStepDefs extends BaseStepDefs {

	private static String nonAdminDisplayName = "User No Admin";
	private int peopleCountBeforeDelete = 0;
	private int peopleCountAfterDelete = 0;

	@When("^I select Settings from the user menu$")
	public void i_select_Settings_from_the_user_menu() throws Throwable {

		logger.info("Open user settings page ...");
		settingsPage = homePage.selectUserSettings();
		logger.info(" settingsPage = " + settingsPage);
		Assert.assertNotNull(settingsPage);

		logger.debug(" settingsPage title       = " + settingsPage.getTitle());
		logger.debug(" settingsPage breadcrumbs = " + settingsPage.getBreadcrumbs());
		UserSettings settings = settingsPage.getUserSettings();
		logger.debug(" settingsPage settings    = " + settings);
	}

	@Then("^I see the Settings page$")
	public void i_see_the_Settings_page() throws Throwable {

		Assert.assertNotNull(settingsPage);
		Assert.assertTrue(settingsPage.isSettingsPage());
	}

	@When("^I set mail preference to \"([^\"]*)\"$")
	public void i_set_mail_preference_to(String emailPref) throws Throwable {

		if (emailPref.equals("Plain text")) {
			settingsPage.setEmailPreference(UserSettings.EmailPref.PLAIN_TEXT);
		} else if (emailPref.equals("HTML")) {
			settingsPage.setEmailPreference(UserSettings.EmailPref.HTML);
		} else {
			throw new Exception("Unknown email pref " + emailPref);
		}
	}

	@When("^I set digest preference to \"([^\"]*)\"$")
	public void i_set_digest_preference_to(String digestPref) throws Throwable {

		if (digestPref.equals("Combined digest")) {
			settingsPage.setDigestPreference(UserSettings.DigestGroupingPref.COMBINED_DIGEST);
		} else if (digestPref.equals("One digest")) {
			settingsPage.setDigestPreference(UserSettings.DigestGroupingPref.ONE_DIGEST);
		} else {
			throw new Exception("Unknown digest pref " + digestPref);
		}
	}

	@When("^I save my configuration from the Settings page$")
	public void i_save_my_configuration_from_the_Settings_page() throws Throwable {

		logger.info("Save configuration ...");
		settingsPage.selectSave();
	}

	@When("^I logoff Settings page$")
	public void i_logoff() throws Throwable {

		logger.info("Logout ...");
		loginPage = settingsPage.logout();
	}

	@Then("^the email preference is \"([^\"]*)\"$")
	public void the_emsil_preference_is(String emailPref) throws Throwable {

		Assert.assertNotNull(settingsPage);
		UserSettings userSettings = settingsPage.getUserSettings();
		Assert.assertEquals(userSettings.emailPrefToString(), emailPref);
	}

	@Then("^the digest preference is \"([^\"]*)\"$")
	public void the_digest_preference_is(String digestPref) throws Throwable {

		Assert.assertNotNull(settingsPage);
		UserSettings userSettings = settingsPage.getUserSettings();
		Assert.assertEquals(userSettings.digestPrefToString(), digestPref);
	}

	@When("^I select Dashboard from the user menu$")
	public void i_select_Dashboard_from_the_user_menu() throws Throwable {

		logger.info("Open user dashboard ...");
		dashboardPage = homePage.selectUserDashboard();
		logger.info(" dashboardPage = " + dashboardPage);

		Assert.assertNotNull(dashboardPage);

		logger.debug(" dashboardPage title       = " + dashboardPage.getTitle());
		logger.debug(" dashboardPage breadcrumbs = " + dashboardPage.getBreadcrumbs());
		List<Task> tasks = dashboardPage.getTasks();
		for (Task task : tasks) {
			logger.debug(" dashboard task = " + task);
		}
	}

	@Then("^I see the Dashboard page$")
	public void i_see_the_Dashboard_page() throws Throwable {

		Assert.assertNotNull(dashboardPage);
		Assert.assertTrue(dashboardPage.isDashboardPage());
	}

	@When("^I select Add User$")
	public void i_select_Add_User() throws Throwable {

		logger.info("Select Add user ...");
		Assert.assertNotNull(peoplePage);
		addUserPage = peoplePage.selectAddUser();
		logger.info(" addUserPage = " + addUserPage);

		logger.debug(" addUserPage title       = " + addUserPage.getTitle());
		logger.debug(" addUserPage breadcrumbs = " + addUserPage.getBreadcrumbs());
	}

	@Then("^I see the Add New User page$")
	public void i_see_the_Add_New_User_page() throws Throwable {

		Assert.assertNotNull(addUserPage);
		Assert.assertTrue(addUserPage.isAddUserPage());
	}

	@When("^I enter a new non-admin user$")
	public void i_enter_a_new_non_admin_user() throws Throwable {

		logger.info("Set new user fields ...");
		addUserPage = addUserPage.setUsername("usernoadmin");
		addUserPage = addUserPage.setEmail("usernoadmin@dummy.com");
		addUserPage = addUserPage.setPasswords("password", "password");
		addUserPage = addUserPage.setDisplayName(nonAdminDisplayName);
	}

	@When("^I select to Create new Account$")
	public void i_select_to_Create_new_Account() throws Throwable {

		logger.info("Create new account ...");
		addUserPage.selectCreateNewAccount();
	}

	@Then("^new non-admin user is created$")
	public void new_non_admin_user_is_created() throws Throwable {

		String message = addUserPage.getErrorMessage();
		logger.info(" error message = " + message);
		Assert.assertTrue(message.contains("Created a new account") || message.contains("No e-mail has been sent"));
	}

	@When("^I select the non-admin user$")
	public void i_select_the_non_admin_user() throws Throwable {

		logger.info("Select non admin user");
		UserInfo userInfo = new UserInfo();
		userInfo.setDisplayName(nonAdminDisplayName);

		Assert.assertTrue(peoplePage.ifUserExists(userInfo));
		peoplePage = peoplePage.selectUser(userInfo);
	}

	@When("^I delete the non-admin user$")
	public void i_delete_the_non_admin_user() throws Throwable {

		logger.info("Cancel non-admin user ...");

		UserInfo userInfo = new UserInfo();
		userInfo.setDisplayName(nonAdminDisplayName);

		List<UserInfo> people = peoplePage.getPeople();
		peopleCountBeforeDelete = people.size();
		logger.info(" peopleCountBeforeDelete = " + peopleCountBeforeDelete);

		peoplePage = peoplePage.deleteUser(userInfo);

		people = peoplePage.getPeople();
		peopleCountAfterDelete = people.size();
		logger.info(" peopleCountAfterDelete = " + peopleCountAfterDelete);

		String message = peoplePage.getErrorMessage();
		logger.info(" message = " + message);
	}

	@Then("^The non-admin user is deleted$")
	public void the_non_admin_user_is_deleted() throws Throwable {

		int diff = peopleCountBeforeDelete - peopleCountAfterDelete;
		Assert.assertTrue(diff == 1);
	}

	@Then("^the title is \"([^\"]*)\"$")
	public void the_title_is(String title) throws Throwable {

		logger.info("check title (" + title + "}");
		Assert.assertNotNull(loginPage);
		String titleStr = loginPage.getTitle();

		logger.info(" titleStr = " + titleStr);
		Assert.assertTrue(titleStr.equals(title));
	}

	@Then("^the document URL on the Login page is a valid link$")
	public void the_document_URL_on_the_Login_page_is_a_valid_link() throws Throwable {

		logger.info("Check documentation URL ...");
		Assert.assertNotNull(loginPage);
		String url = loginPage.getDocumentationURL();
		logger.info(" url = " + url);
		boolean urlOk = BaseUtils.checkURL(url);
		logger.info(" urlOK = " + urlOk);
		Assert.assertTrue(urlOk);
	}

	@When("^I select the Log In button from the Login Page$")
	public void i_select_the_Log_In_button_from_the_Login_Page() throws Throwable {

		logger.info("Select Log In (with missing data)");
		Assert.assertNotNull(loginPage);
		loginPage = loginPage.selectLoginError();
	}

	@Then("^I see the error message \"([^\"]*)\"$")
	public void i_see_the_error_message(String expectedErrorMessage) throws Throwable {

		logger.info("Check for error message " + expectedErrorMessage + " ...");
		String errorMessage = loginPage.getErrorMessage();
		logger.info(" errorMessage = \"" + errorMessage + "\"");
		Assert.assertTrue(errorMessage.contains(expectedErrorMessage));
	}

	@When("^I set the Username in the login screen to \"([^\"]*)\"$")
	public void i_set_the_Username_in_the_login_screen_to(String userName) throws Throwable {

		logger.info("Set user name to " + userName);
		Assert.assertNotNull(loginPage);
		loginPage.setUsername(userName);
	}

	@When("^I set the password to \"([^\"]*)\"$")
	public void i_set_the_password_to(String password) throws Throwable {

		logger.info("Set password to " + password);
		Assert.assertNotNull(loginPage);
		loginPage.setPassword(password);
	}

	@Then("^the menu is present on the Login page$")
	public void the_menu_is_present_on_the_Login_page() throws Throwable {

		Assert.assertNotNull(loginPage);
		logger.info("Check that menu exists ...");
		boolean menuExists = loginPage.menuExists();
		logger.info(" menuExists = " + menuExists);
		Assert.assertTrue(menuExists);

	}
}
