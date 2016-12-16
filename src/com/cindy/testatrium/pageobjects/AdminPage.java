package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object representing the Admin page.  The Admin Page contains a
 * list of administrative tasks.
 * 
 * @author Cindy
 *
 */
public class AdminPage extends AtriumBasePage {

	String url = "http://127.0.0.1/openatrium/admin";
	
	private static By navbarLocator = By.id("navbar-administration");
	private static By taskDivLocator = By.id("block-system-main");
	private static By taskLinkLocator = By.tagName("a");
	private static By tasksTabLocator = By.partialLinkText("Tasks");
	private static By indexTabLocator = By.partialLinkText("Index");
	private static By reportLinkLocator = By.linkText("Reports");
	private static By reportAdminLocator = By.linkText("Administration");
	private static By peopleLinkLocator = By.linkText("People");
	
	public AdminPage(WebDriver driver) {
		super(driver);
	}

	public AdminPage open() throws InterruptedException {

		driver.get(url);

		waitForElement(navbarLocator);

		return this;
	}
	
	public List<String> getTasks() {
		
		List<String> taskList = new ArrayList<String>();
		
		WebElement taskContainer = driver.findElement(taskDivLocator);
		List<WebElement> tasks = taskContainer.findElements(taskLinkLocator);
		for (WebElement task : tasks) {
			taskList.add(task.getText());
		}
		
		return taskList;
	}
	
	public AdminPage selectTaskView() {
		WebElement taskTab = driver.findElement(tasksTabLocator);
		taskTab.click();
		return this;
	}
	
	public AdminPage selectIndexView() {
		WebElement indexTab = driver.findElement(indexTabLocator);
		indexTab.click();
		return this;
	}
	
	public ReportsPage selectReportsPage() throws InterruptedException {
		WebElement reportsLink = driver.findElement(reportLinkLocator);
		reportsLink.click();
		
		waitForElement(reportAdminLocator);
		
		return new ReportsPage(driver);
	}
	
	public PeoplePage selectPeoplePage() throws InterruptedException {
		WebElement reportsLink = driver.findElement(peopleLinkLocator);
		reportsLink.click();
		
		waitForElement(reportAdminLocator);
		
		return new PeoplePage(driver);
	}
}
