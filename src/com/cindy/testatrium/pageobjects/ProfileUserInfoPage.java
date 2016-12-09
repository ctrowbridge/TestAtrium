package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the User Info / Profile page.
 * 
 * @author Cindy
 */
public class ProfileUserInfoPage extends AtriumBasePage {

	public ProfileUserInfoPage(WebDriver driver) {
		super(driver);
	}

	public ProfileAccountPage switchToAccountInfo() throws InterruptedException {
		WebElement userAccountInfoButton = driver.findElement(By.linkText("Account"));
		userAccountInfoButton.click();
		
		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));
		
		return new ProfileAccountPage(driver);
	}
	
}
