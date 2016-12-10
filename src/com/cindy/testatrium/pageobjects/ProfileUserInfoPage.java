package com.cindy.testatrium.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the User Info / Profile page.
 * 
 * @author Cindy
 */
public class ProfileUserInfoPage extends AtriumBasePage {

	private static final Logger logger = LogManager.getLogger("ProfileUserInfoPage");

	public ProfileUserInfoPage(WebDriver driver) {
		super(driver);
	}

	public ProfileAccountPage switchToAccountInfo() throws InterruptedException {
		WebElement userAccountInfoButton = driver.findElement(By.linkText("Account"));
		userAccountInfoButton.click();

		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));

		return new ProfileAccountPage(driver);
	}

	public ProfileUserInfoPage setDisplayName(String name) {
		WebElement nameField = driver.findElement(By.id("edit-field-user-display-name-und-0-value"));
		nameField.clear();
		nameField.sendKeys(name);

		return this;
	}

	public ProfileUserInfoPage selectSaveButton() {
		WebElement saveButton = driver.findElement(By.id("edit-submit"));
		saveButton.click();
		return this;
	}

}
