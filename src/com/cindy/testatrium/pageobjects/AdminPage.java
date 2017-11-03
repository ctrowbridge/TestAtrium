package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.Task;

/**
 * Page Object representing the Admin page.  The Admin Page contains a
 * list of administrative tasks.
 * 
 * @author Cindy
 */
public class AdminPage extends AdminBasePage {

	String url = "http://127.0.0.1/openatrium/admin";
	
	private static final String pageTitle = "Administration";
	
	private static By navbarLocator = By.id("navbar-administration");
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
	
	public AdminPage openNotAuthorized() throws InterruptedException {

		driver.get(url);

		waitForElement(pageTitleLocator);

		return this;
	}
	
	public boolean isAdminPage() {
		
		WebElement title = driver.findElement(pageTitleLink);
		System.out.println("*** title = " + title.getText());
		return title.getText().equals(pageTitle);
	}
	
	/**
	 * Returns the list of tasks present on the Task tab of the Admin page.
	 * @return List of tasks in the Task tab
	 */
	public List<Task> getTasks() {
		
		List<Task> taskList = new ArrayList<Task>();
		
		WebElement taskContainer = driver.findElement(taskDivLocator);
		List<WebElement> tasks = taskContainer.findElements(taskLinkLocator);
		for (WebElement task : tasks) {
			Task oneTask = new Task(task.getText(), task);
			taskList.add(oneTask);
		}
		return taskList;
	}

	public boolean tabExists(String tabName) {
		
		if (tabName.equals("Tasks")) {
			return isElementPresent(tasksTabLocator);
			
		}
		if (tabName.equals("Index")) {
			return isElementPresent(indexTabLocator);
		}
		
		return false;
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
