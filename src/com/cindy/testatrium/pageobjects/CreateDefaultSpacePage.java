package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.Space;
import com.cindy.testatrium.data.Space.GroupVisibility;

/**
 * Page object representing the Create Default Space page. The Create Default
 * Space page allows you to select various content types to create.
 * 
 * @author Cindy
 */
public class CreateDefaultSpacePage extends AtriumBasePage {

	private static String url = "http://localhost/openatrium/node/add/oa-space/1";

	private static By titleLocator = By.id("edit-title");
	private static By descriptionLocator = By.id("tinymce");
	private static By publicLocator = By.id("edit-group-access-und-0");
	private static By privateLocator = By.id("edit-group-access-und-1");
	private static By menuLinkLocator = By.id("edit-menu-enabled");
	private static By publishLocator = By.className("oa-form-actions");

	private static String iframeID = "edit-body-und-0-value_ifr";

	public CreateDefaultSpacePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Creates a Space given the input Space data
	 * 
	 * @param spaceData Data to be used to create a Space.
	 * @return this page
	 */
	public SpacePage createSpace(Space spaceData) {

		setTitle(spaceData.getTitle());
		setDescription(spaceData.getDescription());
		setGroupVisibility(spaceData.getGroupVisibilty());
		setMenuLink(spaceData.isProvideMenuLink());
		SpacePage spacePage = selectPublish();
		return spacePage;
	}

	public CreateDefaultSpacePage setTitle(String title) {
		
		WebElement titleField = driver.findElement(titleLocator);
		titleField.clear();
		titleField.sendKeys(title);

		return this;
	}

	public SpacePage setDescription(String description) {

		driver.switchTo().frame(iframeID);
		WebElement descriptionField = driver.findElement(descriptionLocator);
		descriptionField.clear();
		descriptionField.sendKeys(description);
		driver.switchTo().defaultContent();

		return new SpacePage(driver);
	}

	public SpacePage selectPublish() {

		WebElement publishform = driver.findElement(publishLocator);
		WebElement publishButton = publishform.findElement(By.tagName("label"));
		publishButton.click();

		return new SpacePage(driver);
	}

	public CreateDefaultSpacePage setGroupVisibility(GroupVisibility vis) {
		
		WebElement groupVisibility = null;
		switch (vis) {
		case PUBLIC:
			groupVisibility = driver.findElement(publicLocator);
			break;
		case PRIVATE:
			groupVisibility = driver.findElement(privateLocator);
			break;
		}
		groupVisibility.click();
		return this;
	}

	public CreateDefaultSpacePage setMenuLink(boolean check) {
		
		WebElement menuLink = driver.findElement(menuLinkLocator);
		boolean checked = menuLink.isSelected();
		if (check && !checked) {
			menuLink.click();
		} else if (check && checked) {
			menuLink.click();
		}

		return this;
	}
}
