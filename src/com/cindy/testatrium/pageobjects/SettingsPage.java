package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.data.UserSettings.DigestGroupingPref;
import com.cindy.testatrium.data.UserSettings.EmailPref;

/**
 * Page Object representing the Settings page.  Fields on the Settings
 * page are represented by the UserSettings class.
 * 
 * @author Cindy
 */
public class SettingsPage extends AtriumBasePage {

	private static By emailPlainTextLocator = By.id("edit-oa-messages-delivery-email-type-0");
	private static By emailHtmlLocator = By.id("edit-oa-messages-delivery-email-type-1");
	private static By groupOneDigestLocator = By.id("edit-oa-messages-delivery-digest-grouping-group");
	private static By groupCombinedDigestLocator = By.id("edit-oa-messages-delivery-digest-grouping-global");
	private static By submitButtonLocator = By.id("edit-submit-top");
	
	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Retrieves the current settings for this page
	 * 
	 * @return Current setting values
	 */
	public UserSettings getUserSettings() {
		
		UserSettings userSettings = new UserSettings();
		
		WebElement email0 = driver.findElement(emailPlainTextLocator);
		//WebElement email1 = driver.findElement(emailHtmlLocator);
		
		if (email0.isSelected()) {
			userSettings.setEmailPref(EmailPref.PLAIN_TEXT);
		} else {
			userSettings.setEmailPref(EmailPref.HTML);
		}
		
		WebElement digest0 = driver.findElement(groupOneDigestLocator);
		//WebElement digest1 = driver.findElement(groupCombinedDigestLocator);
		
		if (digest0.isSelected()) {
			userSettings.setDigestPref(DigestGroupingPref.ONE_DIGEST);
		} else {

			userSettings.setDigestPref(DigestGroupingPref.COMBINED_DIGEST);
		}
			
		return userSettings;
	}
	
	/**
	 * Updates and save the user settings.
	 * 
	 * @param toSettings Setting to use for the update.
	 * @return Updated Settings page
	 * @throws InterruptedException
	 */
	public SettingsPage updateSettings(UserSettings toSettings) throws InterruptedException {
		
		// Update settings
		//
		if (toSettings.getEmailPref() == EmailPref.HTML) {
			WebElement email1 = driver.findElement(emailHtmlLocator);
			email1.click();
		} else {
			WebElement email0 = driver.findElement(emailPlainTextLocator);
			email0.click();
		}
		
		if (toSettings.getDigestPref() == DigestGroupingPref.COMBINED_DIGEST) {

			WebElement digest1 = driver.findElement(groupCombinedDigestLocator);
			digest1.click();
		} else {
			WebElement digest0 = driver.findElement(submitButtonLocator);
			digest0.click();
		}
		
		// Save changes
		//
		WebElement saveButton = driver.findElement(By.id("edit-submit-top"));
		saveButton.click();
		
		// Wait for changes to take effect
		// TODO Fix the first locator
		//
		waitForElement(By.xpath("html/body/div[2]/div/div[3]/section/div/div[1]/div/div[1]/div/div"));
		WebElement closeButton = driver.findElement(closeButtonLocator);
		closeButton.click();
		
		return this;
	}
}
