package com.cindy.testatrium.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.cindy.SeleniumCommon.BasePage;

/**
 * Implements a Base Page Object for Atrium tests.
 * 
 * @author Cindy
 */
public class AtriumBasePage extends BasePage {

	private static final Logger logger = LogManager.getLogger("AtriumBasePage");
	
	protected By mainMenuButtonLocator = By.linkText("Recent Spaces");
	protected By errorLocator = By.xpath("//div[@class='alert alert-danger alert-dismissable']");
	protected By breadcrumbLocator = By.id("breadcrumb");
	protected By pageTitleLocator = By.id("page-title");
	
	public AtriumBasePage(WebDriver driver) {
		super(driver);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public AtriumBasePage back() {
		driver.navigate().back();
		return new AtriumBasePage(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(pageTitleLocator);
		return header.getText();
	}
	
	public LoginPage selectHome() throws InterruptedException {
		
		driver.findElement(By.cssSelector("img.oa-site-banner-img")).click();
		waitForElement(By.cssSelector("h1"));

		return new LoginPage(driver);
	}
	
	public DashboardPage selectUserDashboard() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Dashboard"));
		dashboardButton.click();

		waitForElement(By.id("page-title"));

		return new DashboardPage(driver);
	}
	
	public SettingsPage selectUserSettings() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Settings"));
		dashboardButton.click();

		waitForElement(By.id("page-title"));

		return new SettingsPage(driver);
	}
	
	public ProfileAccountPage selectUserProfile() throws InterruptedException {

		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Edit profile"));
		dashboardButton.click();

		waitForElement(By.id("page-title"));

		return new ProfileAccountPage(driver);
	}
	
	public String getPageTitle() {
		
		if (isElementPresent(By.id("page-title"))) {
			WebElement pageTitle = driver.findElement(By.id("page-title"));
			return pageTitle.getText();
		}
		return "Unknown";
	}
	
	public LoginPage logout() throws InterruptedException {
		
		WebElement menuButton = driver.findElement(By.id("user-badge-dropdown"));

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement dashboardButton = driver.findElement(By.linkText("Log out"));
		dashboardButton.click();

		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));

		return new LoginPage(driver);
	}
	
	public String getErrorMessage() {
		
		String message = "";
		if (isElementPresent(By.className("close"))) {
			WebElement errorMessage = driver.findElement(errorLocator);
			logger.debug(" errorMessage innerHTML = " + errorMessage.getAttribute("innerHTML"));
			logger.debug(" errorMessage text      = " + errorMessage.getText());
			
			message = errorMessage.getAttribute("innerHTML");
			if (message.contains("<ul>")) {
				
				message = message.substring(message.lastIndexOf("<ul>")+1, message.length());
				message = message.replace("</ul>", "");
			} else {
				message = message.substring(message.lastIndexOf('>')+1, message.length());
			}
		}
		return message;
	}
	
	public boolean closeErrorMessage() {
		
		if (isElementPresent(By.className("close"))) {
			WebElement closeButton = driver.findElement(By.className("close"));
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
				if (i < linkCount-1) {
					breadcrumbs += ">>";
				}
				i++;
			}
		}
		return breadcrumbs;
	}
}
