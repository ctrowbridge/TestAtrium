package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.AccountInfo;

/**
 * Page Object representing the Account / Profile page.
 * 
 * @author Cindy
 */
public class ProfileAccountPage extends AtriumBasePage {

	private static By userInfoLocator = By.linkText("User Info");
	private static By adminRoleInfolcator = By.id("edit-roles-3");
	private static By authRoleInfolcator = By.id("edit-roles-2");
	private static By editorRoleInfolcator = By.id("edit-roles-4");
	private static By editNameLocator = By.id("edit-name");
	private static By displayNameLocator = By.id("edit-field-user-display-name-und-0-value");
	private static By emailLocator = By.id("edit-mail--2");
	private static By submitButtonLocator = By.id("edit-submit");
	
	public ProfileAccountPage(WebDriver driver) {
		super(driver);
	}

	public ProfileUserInfoPage switchToUserInfo() throws InterruptedException {
		
		WebElement userInfoButton = driver.findElement(userInfoLocator);
		userInfoButton.click();
		
		waitForElement(toolBarLocator);
		
		return new ProfileUserInfoPage(driver);
	}
	
	public ProfileAccountPage setRole(AccountInfo.Role role) {
		
		WebElement roleBox = null;
		switch (role) {
		case ADMINISTRATOR:
			roleBox = driver.findElement(adminRoleInfolcator);
			break;
		case AUTHENTICATED_USER:
			roleBox = driver.findElement(authRoleInfolcator);
			break;
		case EDITOR:
			roleBox = driver.findElement(editorRoleInfolcator);
			break;
		}
		roleBox.click();
		return this;
	}
	
	public ProfileAccountPage setUserName(String name) {
		
		WebElement nameField = driver.findElement(editNameLocator);
		nameField.clear();
		nameField.sendKeys(name);
		return this;
	}
	
	public ProfileAccountPage setDisplayName(String name) {
		WebElement nameField = driver.findElement(displayNameLocator);
		nameField.clear();
		nameField.sendKeys(name);
		return this;
	}
	
	public ProfileAccountPage setEmail(String email) {
		WebElement nameField = driver.findElement(emailLocator);
		nameField.clear();
		nameField.sendKeys(email);
		return this;
	}
	
	public ProfileAccountPage selectSaveButton() {
		WebElement saveButton = driver.findElement(submitButtonLocator);
		saveButton.click();
		return this;
	}
}
