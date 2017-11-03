package com.cindy.testatrium.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.cindy.SeleniumCommon.BasePage;
import com.cindy.testatrium.data.UserInfo;

/**
 * Implements a Base Page Object for Atrium tests.
 * 
 * @author Cindy
 */
public abstract class AtriumBasePage extends BasePage {

	private static final Logger logger = LogManager.getLogger("AtriumBasePage");

	protected By mainMenuButtonLocator = By.linkText("Recent Spaces");
	protected By errorLocator = By.xpath("//div[@class='alert alert-danger alert-dismissable']");
	protected By successLocator = By.xpath("//div[@class='alert alert-success alert-dismissable']");
	protected By breadcrumbLocator = By.id("breadcrumb");
	protected By pageTitleLocator = By.id("page-title");
	protected By homeLocator = By.cssSelector("img.oa-site-banner-img");
	protected By userMenuLocator = By.id("user-badge-dropdown");
	protected By dashboardLocator = By.linkText("Dashboard");
	protected By settingsLocator = By.linkText("Settings");
	protected By editProfileLocator = By.linkText("Edit profile");
	protected By logoutLocator = By.linkText("Log out");
	protected By toolBarLocator = By.id("mini-panel-oa_toolbar_modern_panel");
	protected By closeButtonLocator = By.className("close");

	public AtriumBasePage(WebDriver driver) {
		super(driver);
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public AtriumBasePage back() {
		driver.navigate().back();
		return this;
	}

	public String getHeader() {
		WebElement header = driver.findElement(pageTitleLocator);
		return header.getText();
	}

	public LoginPage selectHome() throws InterruptedException {

		driver.findElement(homeLocator).click();
		waitForElement(By.cssSelector("h1"));

		return new LoginPage(driver);
	}

	public DashboardPage selectUserDashboard() throws InterruptedException {

		WebElement menuButton = driver.findElement(userMenuLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(dashboardLocator);
		dashboardButton.click();

		waitForElement(pageTitleLocator);

		return new DashboardPage(driver);
	}

	public SettingsPage selectUserSettings() throws InterruptedException {

		WebElement menuButton = driver.findElement(userMenuLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(settingsLocator);
		dashboardButton.click();

		waitForElement(pageTitleLocator);

		return new SettingsPage(driver);
	}

	public ProfileAccountPage selectUserProfile() throws InterruptedException {

		WebElement menuButton = driver.findElement(userMenuLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(editProfileLocator);
		dashboardButton.click();

		waitForElement(pageTitleLocator);

		return new ProfileAccountPage(driver);
	}

	public String getPageTitle() {

		if (isElementPresent(pageTitleLocator)) {
			WebElement pageTitle = driver.findElement(pageTitleLocator);
			return pageTitle.getText();
		}
		return "Unknown";
	}

	public LoginPage logout() throws InterruptedException {

		WebElement menuButton = driver.findElement(userMenuLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(logoutLocator);
		dashboardButton.click();

		waitForElement(toolBarLocator);

		return new LoginPage(driver);
	}

	/**
	 * Gets the error message, if it exists, from the current page.
	 * 
	 * @return The error message
	 */
	public String getErrorMessage() {

		String message = "";

		if (isElementPresent(By.className("close"))) {
			if (isElementPresent(errorLocator)) {
				
				WebElement errorMessage = driver.findElement(errorLocator);
				message = parseMessage(errorMessage);
			}
			if (isElementPresent(successLocator)) {
				WebElement successMessage = driver.findElement(successLocator);
				message = parseMessage(successMessage);
			}
		}
		return message;
	}

	public boolean closeErrorMessage() {

		if (isElementPresent(closeButtonLocator)) {
			WebElement closeButton = driver.findElement(closeButtonLocator);
			closeButton.click();
			return true;
		}
		return false;
	}

	public String getBreadcrumbs() {

		String breadcrumbs = "";
		if (isElementPresent(breadcrumbLocator)) {
			WebElement breadcrumbDiv = driver.findElement(breadcrumbLocator);
			List<WebElement> links = breadcrumbDiv.findElements(By.tagName("a"));
			int linkCount = links.size();
			int i = 0;
			for (WebElement link : links) {
				breadcrumbs += link.getText();
				if (i < linkCount - 1) {
					breadcrumbs += ">>";
				}
				i++;
			}
		}
		return breadcrumbs;
	}
	
	private String parseMessage(WebElement messageDiv) {
		
		String message = messageDiv.getAttribute("innerHTML");
		if (message.contains("<ul>")) {
			message = message.substring(message.lastIndexOf("<ul>") + 1, message.length());
			message = message.replace("</ul>", "");
		} else {
			message = message.substring(message.lastIndexOf('>') + 1, message.length());
		}
		return message;
	}
}
