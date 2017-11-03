package com.cindy.testatrium.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the Content page. The Content page displays
 * available content. It also allows the user to add content.
 * 
 * @author Cindy
 */
public class ContentPage extends AdminBasePage {

	private static final Logger logger = LogManager.getLogger("ContentPage");

	public static final By contentLocator = By.xpath("//h1[contains(text(),'Content')]");
	private static final By contentTabLocator = By.partialLinkText("Content");
	private static final By commentsTabLocator = By.partialLinkText("Comments");
	private static final By filesTabLocator = By.partialLinkText("Files");
	private static final By messagesTabLocator = By.partialLinkText("Messages");
	private static final By addContentButtonLocator = By.linkText("Add content");

	public ContentPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isContentPage() {
		
		return isElementPresent(contentLocator);
	}
	
	public boolean tabExists(String tabName) {
		
		if (tabName.equals("Content")) {
			return isElementPresent(contentTabLocator);
		}
		if (tabName.equals("Comments")) {
			return isElementPresent(commentsTabLocator);
		}
		if (tabName.equals("Files")) {
			return isElementPresent(filesTabLocator);
		}
		if (tabName.equals("Messages")) {
			return isElementPresent(messagesTabLocator);
		}
		return false;
	}
	
	public AddContentPage selectAddContent() {
		
		WebElement addContentButton = driver.findElement(addContentButtonLocator);
		addContentButton.click();
		
		return new AddContentPage(driver);
	}
}
