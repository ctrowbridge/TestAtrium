package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.AccountInfo.Status;
import com.cindy.testatrium.data.UserInfo;

public class PeoplePage extends AtriumBasePage {

	private static final Logger logger = LogManager.getLogger("PeoplePage");

	private static By tableLocator = By.xpath("//div[@class='table-responsive']/table[2]");
	private static By rowLocator = By.xpath("//tbody/tr");
	private static By userFilterLocator = By.id("edit-name");
	private static By applyLocator = By.id("edit-submit-admin-views-user");

	public PeoplePage(WebDriver driver) {
		super(driver);
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
		logger.debug("*** rows size = " + rows.size());
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
		Thread.sleep(3000);

		return this;
	}

	private UserInfo parseUserRow(WebElement row) {

		List<WebElement> cells = row.findElements(By.tagName("td"));
		// for (WebElement cell : cells) {
		// logger.debug("*** cell text = " + cell.getText());
		// }

		UserInfo user = new UserInfo();

		WebElement nameCell = cells.get(1);
		String text = nameCell.getText();
		String[] textParts = text.split("\n");
		user.setUsername(textParts[0]);
		user.setEmail(textParts[1]);

		WebElement activeCell = cells.get(2);
		text = activeCell.getText();
		if (text.equals("Yes")) {
			user.setStatus(UserInfo.Status.ACTIVE);
		} else {
			user.setStatus(UserInfo.Status.BLOCKED);
		}

		return user;
	}
}
