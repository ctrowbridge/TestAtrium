package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
}
