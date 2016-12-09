package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.UserSettings;
import com.cindy.testatrium.data.UserSettings.DigestGroupingPref;
import com.cindy.testatrium.data.UserSettings.EmailPref;

public class SettingsPage extends AtriumBasePage {

	public SettingsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public UserSettings getUserSettings() {
		
		UserSettings userSettings = new UserSettings();
		
		WebElement email0 = driver.findElement(By.id("edit-oa-messages-delivery-email-type-0"));
		//WebElement email1 = driver.findElement(By.id("edit-oa-messages-delivery-email-type-1"));
		
		if (email0.isSelected()) {
			userSettings.setEmailPref(EmailPref.PLAIN_TEXT);
		} else {
			userSettings.setEmailPref(EmailPref.HTML);
		}
		
		WebElement digest0 = driver.findElement(By.id("edit-oa-messages-delivery-digest-grouping-group"));
		//WebElement digest1 = driver.findElement(By.id("edit-oa-messages-delivery-digest-grouping-global"));
		
		if (digest0.isSelected()) {
			userSettings.setDigestPref(DigestGroupingPref.ONE_DIGEST);
		} else {

			userSettings.setDigestPref(DigestGroupingPref.COMBINED_DIGEST);
		}
			
		return userSettings;
	}
	
	public SettingsPage updateSettings(UserSettings toSettings) throws InterruptedException {
		
		if (toSettings.getEmailPref() == EmailPref.HTML) {
			WebElement email1 = driver.findElement(By.id("edit-oa-messages-delivery-email-type-1"));
			email1.click();
		} else {
			WebElement email0 = driver.findElement(By.id("edit-oa-messages-delivery-email-type-0"));
			email0.click();
		}
		
		if (toSettings.getDigestPref() == DigestGroupingPref.COMBINED_DIGEST) {

			WebElement digest1 = driver.findElement(By.id("edit-oa-messages-delivery-digest-grouping-global"));
			digest1.click();
		} else {
			WebElement digest0 = driver.findElement(By.id("edit-oa-messages-delivery-digest-grouping-group"));
			digest0.click();
		}
		WebElement saveButton = driver.findElement(By.id("edit-submit-top"));
		saveButton.click();
		
		waitForElement(By.xpath("html/body/div[2]/div/div[3]/section/div/div[1]/div/div[1]/div/div"));
		WebElement closeButton = driver.findElement(By.className("close"));
		closeButton.click();
		
		return this;
	}
}
