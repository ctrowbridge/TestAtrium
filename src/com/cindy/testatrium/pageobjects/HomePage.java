package com.cindy.testatrium.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Page Object representing the Home page for a logged in user.
 * 
 * @author Cindy
 */
public class HomePage extends AtriumBasePage {

	private static final Logger logger = LogManager.getLogger("HomePage");
	
	private By createContentLocator = By.linkText("Create Content");
	private By newSpaceLocator = By.className("oa-subspace oa-new-subspace");
	private By menuButtonLocator = By.xpath("//div[@id='mini-panel-oa_toolbar_modern_panel']/div/div/div/div/div/div/div/span");
	private By adminButtonLocator =  By.xpath("//div[@id='toolbar-menu-button']/i");
	private By addContentLocator = By.linkText("Add content");
	private By contentPageLocator = By.linkText("Content Page");
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(By.tagName("h2"));
		return header.getText();
	}
	
	public SiteMapPage selectCreateContent() throws InterruptedException {
		WebElement createContentButton = driver.findElement(createContentLocator);
		createContentButton.click();
		
		waitForElement(newSpaceLocator);
		
		return new SiteMapPage(driver);
	}
	
	/**
	 * Clicks on Menu Button -> Admin Icon -> Add Content.
	 * 
	 * @return
	 * @throws InterruptedException 
	 */
	public AddContentPage selectAddContentFromMenu() throws InterruptedException {
		
		logger.info("Click on << menu button ...");
		WebElement menuButton = driver.findElement(menuButtonLocator);
		menuButton.click();
		waitForElement(adminButtonLocator);
		
		logger.info("Click on admin icon ...");
		WebElement adminButton = driver.findElement(adminButtonLocator);
		logger.info(" isVisible = " + adminButton.isDisplayed());
		waitForElementVisible(adminButtonLocator);
		adminButton.click();
		logger.info(" isVisible = " + adminButton.isDisplayed());
		
		logger.info("Click on Add content link ...");
		WebElement addContentButton = driver.findElement(addContentLocator);
		logger.info(" isVisible = " + addContentButton.isDisplayed());
		addContentButton.click();
		
		waitForElement(contentPageLocator);
		
		logger.info(" Content Page found " + getTitle());
		return new AddContentPage(driver);
	}
	
	public boolean isSpaceMenuPresent(String spaceName) {
		return isElementPresent(By.linkText(spaceName));
	}
	
	public SpacePage selectSpace(String spaceName) {
		
		WebElement menuButton = driver.findElement(mainMenuButtonLocator);

		Actions builder = new Actions(driver);
		builder.moveToElement(menuButton).perform();
		
		WebElement spaceButton = driver.findElement(By.linkText(spaceName));
		
		return new SpacePage(driver);
	}
	
	public int countSpaces() {
		
	}
}
