package com.cindy.testatrium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.UserInfo;

/**
 * Page Object representing the Add New User page. The Add New User
 * page allows an admin user to add a new user.
 * 
 * @author Cindy
 */
public class AddUserPage extends AtriumBasePage {

	private final String url = "http://127.0.0.1/openatrium/admin/people/create";
	
	private final By editNameLocator = By.id("edit-name");
	private final By createAccountLocator = By.id("edit-submit");
	private final By emailLocator = By.id("edit-mail");
	private final By password1Locator = By.id("edit-pass-pass1");
	private final By password2Locator = By.id("edit-pass-pass2");
	private final By passwordConfirmLocator = By.className("password-confirm");
	private final By statusBlockedLocator = By.id("edit-status-0");
	private final By statusActiveLocator = By.id("edit-status-1");
	private final By roleUserLocator = By.id("edit-roles-2");
	private final By roleAdminLocator = By.id("edit-roles-3");
	private final By roleEditorLocator = By.id("edit-roles-4");
	private final By displayNameLocator = By.id("edit-field-user-display-name-und-0-value");
	
	public AddUserPage(WebDriver driver) {
		super(driver);
	}

	public AddUserPage open() {
		
		driver.get(url);
		waitForPageLoaded();
		return this;
	}
	
	public boolean isAddUserPage() {
		
		return isElementPresent(editNameLocator);
	}
	
	public AddUserPage addUser(UserInfo newUser) {
		
		setUsername(newUser.getUsername());
		selectCreateNewAccount();
		
		return new AddUserPage(driver);
	}
	
	public AddUserPage setUsername(String userName) {
		
		WebElement userNameEdit = driver.findElement(editNameLocator);
		userNameEdit.clear();
		userNameEdit.sendKeys(userName);
		
		return this;
	}
	
	public AddUserPage setEmail(String email) {
		
		WebElement emailEdit = driver.findElement(emailLocator);
		emailEdit.clear();
		emailEdit.sendKeys(email);
		
		return this;
	}
	
	public AddUserPage setPasswords(String pass1, String pass2) {
		
		WebElement pass1Edit = driver.findElement(password1Locator);
		pass1Edit.clear();
		pass1Edit.sendKeys(pass1);
		
		WebElement pass2Edit = driver.findElement(password2Locator);
		pass2Edit.clear();
		pass2Edit.sendKeys(pass2);
		
		return this;
	}
	
	public AddUserPage setStatus(UserInfo.Status status) {
		
		WebElement statusEdit = null;
		if (status == UserInfo.Status.ACTIVE) {
			statusEdit = driver.findElement(statusActiveLocator);
		} else {
			statusEdit = driver.findElement(statusBlockedLocator);
		}
		statusEdit.click();
		
		return this;
	}
	
	/**
	 * Clears the Administrator and Editor Roles.  The Authenticated User is not
	 * editable.
	 * 
	 * @return
	 */
	public AddUserPage clearRoles() {
		
		WebElement admin = driver.findElement(roleAdminLocator);
		WebElement editor = driver.findElement(roleEditorLocator);
		
		if (admin.isSelected()) {
			admin.click();
		}
		if (editor.isSelected()) {
			editor.click();
		}
		
		return this;
	}
	
	/**
	 * Sets the input role.
	 * 
	 * @param role
	 * @return
	 */
	public AddUserPage setRole(UserInfo.Roles role) {
		
		switch (role) {
		case ADMINSTRATOR:
			WebElement admin = driver.findElement(roleAdminLocator);
			admin.click();
			break;
			
		case EDITOR:
			WebElement editor = driver.findElement(roleEditorLocator);
			editor.click();
			break;
		}
		
		return this;
	}

	public AddUserPage setDisplayName(String name) {
		
		WebElement edit = driver.findElement(displayNameLocator);
		edit.clear();
		edit.sendKeys(name);
		
		return this;
	}
	
	public AddUserPage selectCreateNewAccount() {
		
		WebElement createAccountButton = driver.findElement(createAccountLocator);
		createAccountButton.click();
		
		return new AddUserPage(driver);
	}
	
	public String getUsername() {
		
		WebElement edit = driver.findElement(editNameLocator);
		return edit.getAttribute("value");
	}
	
	public String getEmail() {
		
		WebElement edit = driver.findElement(emailLocator);
		return edit.getAttribute("value");
	}
	
	public UserInfo.Status getStatus() {
		
		WebElement statusEdit = driver.findElement(statusActiveLocator);
		if (statusEdit.isSelected())
			return UserInfo.Status.ACTIVE;
		else
			return UserInfo.Status.BLOCKED;
	}

	/**
	 * Get the password confirmation message. This message is displayed next to
	 * the second password field.
	 * 
	 * @return
	 */
	public String getPasswordConfirm() {
		
		WebElement passwordConfirmMessage = driver.findElement(passwordConfirmLocator);
		return passwordConfirmMessage.getText();
	}
	
	/**
	 * Gets the status (true or false) of the input role.
	 * 
	 * @param role
	 * @return
	 */
	public boolean getRole(UserInfo.Roles role) {
		
		boolean roleSet = false;

		switch (role) {

		case AUTHENTICATED_USER:
			WebElement user = driver.findElement(roleUserLocator);
			if (user.isSelected()) {
				roleSet = true;
			}
			break;

		case ADMINSTRATOR:
			WebElement admin = driver.findElement(roleAdminLocator);
			if (admin.isSelected()) {
				roleSet = true;
			}
			break;

		case EDITOR:
			WebElement editor = driver.findElement(roleEditorLocator);
			if (editor.isSelected()) {
				roleSet = true;
			}
			break;
		}
		return roleSet;
	}

	public String getDisplayName() {
		
		WebElement edit = driver.findElement(displayNameLocator);
		return edit.getAttribute("value");
	}
}
