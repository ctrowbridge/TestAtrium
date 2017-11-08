package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.UserInfo;

/**
 * Page Object representing the Login page. The Login page allows the user to
 * log in to Atrium. It also provides a link to the site map, documentation and
 * professional services.
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
	private By searchFieldLocator = By.name("search_text");
	private By searchFieldButtonLocator = By.xpath("//button[@type='submit']");
	private By searchLabelLocator = By.xpath("//div[@id='edit-basic']/div/label");
	private By siteMapTopLocator = By.id("oa-sitemap-top");
	private By menuLocator = By.id("oa-space-menu");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	String url = "http://127.0.0.1/openatrium/";

	public LoginPage open() throws InterruptedException {

		driver.get(url);

		waitForElement(toolBarLocator);

		return this;
	}

	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Does the "hamburger" menu exist on the login page?
	 * 
	 * @return True if the "hamburger" menu exists on the login page.
	 */
	public boolean menuExists() {

		if (isElementPresent(mainMenuButtonLocator)) {
			return true;
		}
		return false;
	}

	/**
	 * Retrieve a list of items in the "hamburger" menu.
	 * 
	 * @return List of items (tasks) in the "hamburger" menu
	 */
	public List<Task> getMenuTasks() {
		
		List<Task> tasks = new ArrayList<Task>();
		
		WebElement menuButton = driver.findElement(mainMenuButtonLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();
		
		WebElement menu = driver.findElement(menuLocator);
		List<WebElement> menuItems = menu.findElements(By.tagName("a"));
		for (WebElement item : menuItems) {
			String text = item.getText();
			Task task = new Task(text, item);
			tasks.add(task);
		}
		return tasks;	
	}
	
	public SiteMapPage selectSiteMap() throws InterruptedException {

		WebElement menuButton = driver.findElement(mainMenuButtonLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();

		WebElement siteMapButton = driver.findElement(siteMapLocator);
		siteMapButton.click();

		waitForElement(siteMapTopLocator);

		return new SiteMapPage(driver);
	}

	public HomePage login(UserInfo user) {

		login(user.getUsername(), user.getPassword());

		return new HomePage(driver);
	}

	public HomePage login(String user, String password) {

		setUsername(user);
		setPassword(password);
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

	public boolean searchFieldExists() {

		if (isElementPresent(searchFieldLocator)) {
			return true;
		}
		return false;
	}
	
	public boolean searchButtonExists() {

		if (isElementPresent(searchFieldButtonLocator)) {
			return true;
		}
		return false;
	}
	
	public LoginPage selectLoginError() throws InterruptedException {
		
		WebElement loginButton = driver.findElement(submitLocator);
		loginButton.click();
		
		waitForElement(errorLocator);
		
		return this;
	}
	
	public LoginPage setUsername(String username) {
		
		driver.findElement(usernameLocator).clear();
		driver.findElement(usernameLocator).sendKeys(username);
		
		return this;
	}
	
	public LoginPage setPassword(String password) {
		
		driver.findElement(passwordLocator).clear();
		driver.findElement(passwordLocator).sendKeys(password);
		
		return this;
	}
}
