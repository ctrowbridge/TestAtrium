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
	
	public SiteMapPage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(By.tagName("h2"));
		return header.getText();
	}
	
	public AddNewSpacePage selectNewSpace() {
		WebElement newSpaceButton = driver.findElement(newSpaceLocator);
		newSpaceButton.click();
		
		waitForElementnewSpaceTitleLocator();
		return new AddNewSpacePage(driver);
	}

	private void waitForElementnewSpaceTitleLocator() {
		// TODO Auto-generated method stub
		
	}
}
