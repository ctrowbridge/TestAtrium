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

	public ProfileAccountPage(WebDriver driver) {
		super(driver);
	}

	public ProfileUserInfoPage switchToUserInfo() throws InterruptedException {
		WebElement userInfoButton = driver.findElement(By.linkText("User Info"));
		userInfoButton.click();
		
		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));
		
		return new ProfileUserInfoPage(driver);
	}
	
	public ProfileAccountPage setRole(AccountInfo.Role role) {
		
		WebElement roleBox = null;
		switch (role) {
		case ADMINISTRATOR:
			roleBox = driver.findElement(By.id("edit-roles-3"));
			break;

		case AUTHENTICATED_USER:
			roleBox = driver.findElement(By.id("edit-roles-2"));
			break;
			
		case EDITOR:
			roleBox = driver.findElement(By.id("edit-roles-4"));
			break;
		}
		roleBox.click();
		
		return this;
	}
	
	public ProfileAccountPage setUserName(String name) {
		WebElement nameField = driver.findElement(By.id("edit-name"));
		nameField.clear();
		nameField.sendKeys(name);
		
		return this;
	}
	
	public ProfileAccountPage setDisplayName(String name) {
		WebElement nameField = driver.findElement(By.id("edit-field-user-display-name-und-0-value"));
		nameField.clear();
		nameField.sendKeys(name);
		
		return this;
	}
	
	public ProfileAccountPage setEmail(String email) {
		WebElement nameField = driver.findElement(By.id("edit-mail--2"));
		nameField.clear();
		nameField.sendKeys(email);
		
		return this;
	}
	
	public ProfileAccountPage selectSaveButton() {
		WebElement saveButton = driver.findElement(By.id("edit-submit"));
		saveButton.click();
		return this;
	}
}
