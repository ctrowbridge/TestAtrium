package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.Space;

/**
 * Page object representing the Create Default Space page. The
 * Create Default Space page allows you to select various content
 * types to create.
 * 
 * @author Cindy
 */
public class CreateDefaultSpacePage extends AtriumBasePage {

	private static String url = "http://localhost/openatrium/node/add/oa-space/1";
	
	private static By titleLocator = By.id("edit-title");
	private static By descriptionLocator = By.id("tinymce");
	private static By publicLocator = By.id("edit-group-access-und-0");
	private static By privateLocator = By.id("edit-group-access-und-1");
	
	private static String iframeID = "edit-body-und-0-value_ifr";
	
	public CreateDefaultSpacePage(WebDriver driver) {
		super(driver);
	}

	public CreateDefaultSpacePage createSpace(Space spaceData) {
		
		WebElement title = driver.findElement(titleLocator);
		title.clear();
		title.sendKeys(spaceData.getTitle());
		
		driver.switchTo().frame(iframeID);
		WebElement description = driver.findElement(descriptionLocator);
		description.clear();
		description.sendKeys(spaceData.getDescription());
		
		WebElement groupVisibility = null;
		switch (spaceData.getGroupVisibilty())
		{
		case PUBLIC:
			groupVisibility = driver.findElement(publicLocator);
			break;
		case PRIVATE:
			groupVisibility = driver.findElement(privateLocator);
			break;
		}
		groupVisibility.click();
		
		driver.switchTo().defaultContent();
		
		return this;
	}
}
