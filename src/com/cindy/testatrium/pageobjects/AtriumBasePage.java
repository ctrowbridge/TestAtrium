package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.cindy.SeleniumCommon.BasePage;

/**
 * Implements a Base Page Object for Attrium tests.
 * 
 * @author Cindy
 */
public class AtriumBasePage extends BasePage {

	public AtriumBasePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public AtriumBasePage back() {
		driver.navigate().back();
		return new AtriumBasePage(driver);
	}
	
	public LoginPage selectHome() throws InterruptedException {
		driver.findElement(By.cssSelector("img.oa-site-banner-img")).click();
		waitForElement(By.cssSelector("h1"));

		return new LoginPage(driver);
	}
	
	public DashboardPage selectUserDashboard() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Dashboard"));
		dashboardButton.click();

		waitForElement(By.id("page-title"));

		return new DashboardPage(driver);
	}
	
	public SettingsPage selectUserSettings() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Settings"));
		dashboardButton.click();

		waitForElement(By.id("page-title"));

		return new SettingsPage(driver);
	}
	
	public String getPageTitle() {
		if (isElementPresent(By.id("page-title"))) {
			WebElement pageTitle = driver.findElement(By.id("page-title"));
			return pageTitle.getText();
		}
		return "Unknown";
	}
}
