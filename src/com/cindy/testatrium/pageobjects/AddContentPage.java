package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the Add Content page.
 * 
 * @author Cindy
 */
public class AddContentPage extends AtriumBasePage {

	private static String url = "http://localhost/openatrium/node/add";
	
	public static final By addContentLocator = By.xpath("//h1[contains(text(),'Add content')]");
	
	private static final By contentLocator = By.className("content");
	private static final By linkLocator = By.tagName("a");
	private static final By spaceLocator = By.linkText("Space");
	private static final By spacePageLocator = By.id("edit-path-alias");
	
	public AddContentPage(WebDriver driver) {
		super(driver);
	}

	public boolean isAddContentPage() {
		
		return isElementPresent(addContentLocator);
	}
	
	public List<String> getContentOptions() {
		List<String> contentOptions = new ArrayList<String>();
		
		WebElement content = driver.findElement(contentLocator);
		List<WebElement> links = content.findElements(linkLocator);
		
		for (WebElement link : links) {
			contentOptions.add(link.getText());
		}
		return contentOptions;
	}
	
	public CreateDefaultSpacePage selectSpace() throws InterruptedException {
		
		WebElement spaceLink = driver.findElement(spaceLocator);
		spaceLink.click();
		
		waitForElement(spacePageLocator);
		
		return new CreateDefaultSpacePage(driver);
	}
	
	public CreateGroupPage selectGroup() throws InterruptedException {
		
		WebElement groupButton = driver.findElement(By.linkText("Group"));
		groupButton.click();
		
		waitForElement(CreateGroupPage.createGroupLocator);
		
		return new CreateGroupPage(driver);
	}
}
