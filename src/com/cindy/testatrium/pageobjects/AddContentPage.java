package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AddContentPage extends AtriumBasePage {

	private static String url = "http://localhost/openatrium/node/add";
	
	private static By contentLocator = By.className("content");
	private static By linkLocator = By.tagName("a");
	private static By spaceLocator = By.linkText("Space");
	private static By spacePageLocator = By.id("edit-path-alias");
	
	public AddContentPage(WebDriver driver) {
		super(driver);
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
	
}
