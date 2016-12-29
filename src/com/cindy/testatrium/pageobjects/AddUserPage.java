package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.UserInfo;

/**
 * Page Object representing the Add New User page.
 * 
 * @author Cindy
 */
public class AddUserPage  extends AtriumBasePage {

	private final String url = "http://127.0.0.1/openatrium/admin/people/create";
	
	private final By editNameLocator = By.id("edit-name");
	private final By createAccountLocator = By.id("edit-submit");
	
	
	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	public AddUserPage open() {
		driver.get(url);
		waitForPageLoaded();
		return this;
	}
	
	public AddUserPage addUser(UserInfo newUser) {
		
		setUserName(newUser.getUsername());
		
		return new AddUserPage(driver);
	}
	
	public AddUserPage setUserName(String userName) {
		
		WebElement userNameEdit = driver.findElement(editNameLocator);
		userNameEdit.clear();
		userNameEdit.sendKeys(userName);
		
		return this;
	}
	
	public AddUserPage selectCreateNewAccount() {
		
		WebElement createAccountButton = driver.findElement(createAccountLocator);
		createAccountButton.click();
		
		return new AddUserPage(driver);
	}
}
