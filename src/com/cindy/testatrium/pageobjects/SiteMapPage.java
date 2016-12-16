package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the Site Map page.
 * 
 * @author Cindy
 */
public class SiteMapPage extends AtriumBasePage {

	private By newSpaceLocator = By.className("oa-subspace-icon");
	private By newSpaceTitleLocator = By.id("modal-title");
	private By headerLocator = By.tagName("h2");
	
	public SiteMapPage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(headerLocator);
		return header.getText();
	}
	
	public AddNewSpacePage selectNewSpace() throws InterruptedException {
		WebElement newSpaceButton = driver.findElement(newSpaceLocator);
		newSpaceButton.click();
		
		waitForElement(newSpaceTitleLocator);
		return new AddNewSpacePage(driver);
	}

}
