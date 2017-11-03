package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cindy.testatrium.data.UserInfo;

/**
 * Page Object representing the People Page. The People Page shows the list of
 * current users.
 * 
 * @author Cindy
 */
public class PeoplePage extends AdminBasePage {

	public enum Operation {
		CANCEL_USER, CHANGE_VALUE, CHANGE_USER_ROLES, SEND_EMAIL
	};

	private static final String pageTitle = "People";

	private static By tableLocator = By.xpath("//div[@class='table-responsive']/table[2]");
	private static By rowLocator = By.xpath("//tbody/tr");
	private static By userFilterLocator = By.id("edit-name");
	private static By applyLocator = By.id("edit-submit-admin-views-user");
	private static By executeLocator = By.id("edit-submit--2");
	private static By addUserLocator = By.linkText("Add user");
	private static By editNameLocator = By.id("edit-name");
	private static By editSubmitLocator = By.id("edit-submit");
	private static By sureCancelLocator = By.id("views-form-admin-views-user-system-1");
	private static By closeButtonLocator = By.className("close");
	
	public PeoplePage(WebDriver driver) {
		super(driver);
	}

	public boolean isPeoplePage() {

		WebElement title = driver.findElement(pageTitleLink);
		return title.getText().equals(pageTitle);
	}

	/**
	 * Retrieves a list of people from the People Page.
	 * 
	 * @return
	 */
	public List<UserInfo> getPeople() {

		List<UserInfo> people = new ArrayList<UserInfo>();

		WebElement table = driver.findElement(tableLocator);
		List<WebElement> rows = table.findElements(rowLocator);
		WebElement row1 = rows.get(0);
		if (!row1.getText().contains("No users available.")) {

			for (WebElement row : rows) {

				UserInfo userInfo = parseUserRow(row);
				people.add(userInfo);
			}
		}
		return people;
	}

	public PeoplePage setFilterUsername(String name) {

		WebElement nameField = driver.findElement(userFilterLocator);
		nameField.clear();
		nameField.sendKeys(name);
		return this;
	}

	public PeoplePage selectApplyFilter() throws InterruptedException {

		WebElement applyButton = driver.findElement(applyLocator);
		applyButton.click();
		waitForPageLoaded();

		return this;
	}

	public PeoplePage selectExecute() {
		WebElement executeButton = driver.findElement(executeLocator);
		executeButton.click();

		return this;
	}

	public AddUserPage selectAddUser() throws InterruptedException {

		WebElement addUserButton = driver.findElement(addUserLocator);
		addUserButton.click();
		waitForElement(editNameLocator);

		return new AddUserPage(driver);
	}

	private UserInfo parseUserRow(WebElement row) {

		List<WebElement> cells = row.findElements(By.tagName("td"));
		UserInfo user = new UserInfo();

		WebElement checkbox = cells.get(0);
		WebElement nameCell = cells.get(1);
		String text = nameCell.getText();
		String[] textParts = text.split("\n");

		user.setDisplayName(textParts[0]);
		user.setEmail(textParts[1]);
		user.setCheckbox(checkbox);

		WebElement activeCell = cells.get(2);
		text = activeCell.getText();
		if (text.equals("Yes")) {
			user.setStatus(UserInfo.Status.ACTIVE);
		} else {
			user.setStatus(UserInfo.Status.BLOCKED);
		}
		return user;
	}

	public boolean ifUserExists(UserInfo userInfo) {

		List<UserInfo> people = getPeople();
		for (UserInfo user : people) {
			if (user.getDisplayName().equals(userInfo.getDisplayName())) {
				return true;
			}
		}
		return false;
	}

	public PeoplePage selectUser(UserInfo userInfo) {

		List<UserInfo> people = getPeople();
		for (UserInfo user : people) {
			if (user.getDisplayName().equals(userInfo.getDisplayName())) {
				user.getCheckbox().click();
			}
		}
		return this;
	}

	public PeoplePage selectOperation(Operation op) {
		
		Select dropdown = new Select(driver.findElement(By.name("operation")));
		dropdown.selectByVisibleText(operationToString(op));
		
		return this;
	}

	public String operationToString(Operation op) {

		String operationStr = "";
		switch (op) {
		case CANCEL_USER:
			operationStr = "Cancel user account";
			break;
		case CHANGE_VALUE:
			operationStr = "Change value";
			break;
		case CHANGE_USER_ROLES:
			operationStr = "Change user roles";
			break;
		case SEND_EMAIL:
			operationStr = "Send e-mail";
			break;
		}
		return operationStr;
	}
	
	public PeoplePage selectDeleteAccount() {
		
		WebElement radioContainer = driver.findElement(By.id("views-form-admin-views-user-system-1"));
		List<WebElement> divs = radioContainer.findElements(By.tagName("label"));
		divs.get(4).click();
		
		return this;
	}
	
	public PeoplePage confirmDelete() throws InterruptedException {
		
		WebElement nextButton = driver.findElement(editSubmitLocator);
		nextButton.click();
		
		waitForElement(sureCancelLocator);
		
		WebElement confirmButton = driver.findElement(editSubmitLocator);
		confirmButton.click();
		
		waitForElementVisible(closeButtonLocator);
		
		return this;
	}
	
	public PeoplePage deleteUser(UserInfo userInfo) throws InterruptedException {
		
		selectUser(userInfo);
		selectOperation(Operation.CANCEL_USER);
		selectExecute();
		selectDeleteAccount();
		confirmDelete();
		
		waitForElementVisible(closeButtonLocator);
		
		return this;
	}
}
