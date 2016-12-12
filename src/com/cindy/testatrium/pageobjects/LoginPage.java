package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Page Object representing the Login page. The Login page allows the
 * user to log in to Atrium. It also provides a link to the site map,
 * documentation and professional services.
 * 
 * @author Cindy
 */
public class LoginPage extends AtriumBasePage {

	private By usernameLocator = By.id("edit-name");
	private By passwordLocator = By.id("edit-pass");
	private By submitLocator = By.id("edit-submit");
	private By siteMapLocator = By.linkText("Site map");
	private By loginButtonLocator = By.linkText("Login");
	private By documentationLocation = By.linkText("documentation");
	private By mainMenuButtonLocator = By.linkText("Recent Spaces");
	private By searchFieldLocator = By.name("search_text");
	private By searchFieldButtonLocator = By.xpath("//button[@type='submit']");
	private By searchLabelLocator = By.xpath("//div[@id='edit-basic']/div/label");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	String url = "http://127.0.0.1/openatrium/";

	public LoginPage open() throws InterruptedException {

		driver.get(url);

		waitForElement(By.id("mini-panel-oa_toolbar_modern_panel"));

		return this;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public SiteMapPage selectSiteMap() throws InterruptedException {

		WebElement menuButton = driver.findElement(mainMenuButtonLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement siteMapButton = driver.findElement(siteMapLocator);
		siteMapButton.click();

		waitForElement(By.id("oa-sitemap-top"));

		return new SiteMapPage(driver);
	}

	public HomePage login(String user, String password) {

		driver.findElement(usernameLocator).clear();
		driver.findElement(usernameLocator).sendKeys(user);
		
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(password);
		driver.findElement(submitLocator).click();

		return new HomePage(driver);
	}
	
	public boolean isLoggedIn() {
		if (isElementPresent(loginButtonLocator)) {
			return false;
		}
		return true;
	}
	
	public String getDocumentationURL() {
		WebElement documentationLink = driver.findElement(documentationLocation);
		return documentationLink.getAttribute("href");
	}
	
	public LoginPage enterSearchString(String searchString) {
		WebElement searchField = driver.findElement(searchFieldLocator);
		searchField.clear();
		searchField.sendKeys(searchString);
		return this;
	}
	
	public SearchResultsPage selectSearch() throws InterruptedException {
		WebElement searchButton = driver.findElement(searchFieldButtonLocator);
		searchButton.click();
		
		waitForElement(searchLabelLocator);
		
		return new SearchResultsPage(driver);
	}
}
