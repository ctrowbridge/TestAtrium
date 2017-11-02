package com.cindy.testatrium.steps;

import org.testng.Assert;

import com.cindy.testatrium.data.UserSettings;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Defines Cucumber steps for User features.
 * 
 * @author Cindy
 */
public class UserStepDefs extends BaseStepDefs {

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
		} else if (emailPref.equals("HTML")){
			settingsPage.setEmailPreference(UserSettings.EmailPref.HTML);
		} else {
			throw new Exception("Unknown email pref " + emailPref);
		}
	}

	@When("^I set digest preference to \"([^\"]*)\"$")
	public void i_set_digest_preference_to(String digestPref) throws Throwable {
	   
		if (digestPref.equals("Combined digest")) {
			settingsPage.setDigestPreference(UserSettings.DigestGroupingPref.COMBINED_DIGEST);
		} else if (digestPref.equals("One digest")){
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
}
