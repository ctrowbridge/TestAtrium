package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object representing the Home page.
 * 
 * @author Cindy
 */
public class HomePage extends AtriumBasePage {

	private By createContentLocator = By.linkText("Create Content");
	private By newSpaceLocator = By.className("oa-subspace oa-new-subspace");
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(By.tagName("h2"));
		return header.getText();
	}
	
	public SiteMapPage selectCreateContent() throws InterruptedException {
		WebElement createContentButton = driver.findElement(createContentLocator);
		createContentButton.click();
		
		waitForElement(newSpaceLocator);
		
		return new SiteMapPage(driver);
	}
}
