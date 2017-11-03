package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Create Group page.  From the page, the user
 * can create a group, with Title, Description, Visibility, Parent groups,
 * etc.
 * 
 * @author Cindy
 */
public class CreateGroupPage extends AtriumBasePage {

	public static final By createGroupLocator = By.xpath("//h1[contains(text(),'Create Group')]");
	
	public CreateGroupPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isCreateGroupPage() {
		
		return isElementPresent(createGroupLocator);
	}
}
