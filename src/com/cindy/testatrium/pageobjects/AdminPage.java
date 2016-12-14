package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage extends AtriumBasePage {

	String url = "http://127.0.0.1/openatrium/admin";
	
	private static By navbarLocator = By.id("navbar-administration");
	private static By taskDivLocator = By.id("block-system-main");
	private static By taskLinkLocator = By.tagName("a");
	private static By tasksTabLocator = By.partialLinkText("Tasks");
	private static By indexTabLocator = By.partialLinkText("Index");
	private static By pageTitleLocator = By.id("page-title");
	
	public AdminPage(WebDriver driver) {
		super(driver);
	}

	public AdminPage open() throws InterruptedException {

		driver.get(url);

		waitForElement(navbarLocator);

		return this;
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(pageTitleLocator);
		return header.getText();
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
}
