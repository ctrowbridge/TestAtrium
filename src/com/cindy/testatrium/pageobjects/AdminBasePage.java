package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.Task;

/**
 * Implements base page for Page Objects accessed from the Admin page.  All AdminBasePage
 * objects contain breadcrumbs, and a list of tasks.  Some of them may contain tabs.
 * 
 * @author Cindy
 */
public class AdminBasePage extends AtriumBasePage {

	protected static By pageTitleLink = By.id("page-title");

	protected static By taskDivLocator = By.id("block-system-main");
	protected static By taskLinkLocator = By.tagName("a");
	
	public AdminBasePage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * Returns the list of tasks present on the Task tab of the page.
	 * 
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
	
	/**
	 * Determines whether a particular task exists on the page.
	 * 
	 * @param task Task to check
	 * @return true if the input task exists, false otherwise
	 */
	public boolean taskExists(String task) {
		return isElementPresent(By.linkText(task));
	}
}
