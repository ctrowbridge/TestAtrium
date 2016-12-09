package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends AtriumBasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	String url = "http://127.0.0.1/openatrium/";

	public LoginPage open() throws InterruptedException {

		driver.get(url);

		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));

		return this;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public SiteMapPage selectSiteMap() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.linkText("Recent Spaces"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement siteMapButton = driver.findElement(By.linkText("Site map"));
		siteMapButton.click();

		waitForElement(By.tagName("h2"));

		return new SiteMapPage(driver);
	}

	public HomePage login(String user, String password) {

		driver.findElement(By.id("edit-name")).clear();
		driver.findElement(By.id("edit-name")).sendKeys(user);
		
		driver.findElement(By.id("edit-pass")).clear();
		driver.findElement(By.id("edit-pass")).sendKeys(password);
		driver.findElement(By.id("edit-submit")).click();

		return new HomePage(driver);
	}
}
