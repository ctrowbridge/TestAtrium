package com.cindy.testatrium.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.Topic;


/**
 * Page object representing the User Dashboard Page.  The User
 * Dashboard page displays a summary of user spaces, tasks, topics
 * and reply topics.
 * 
 * @author Cindy
 */
public class DashboardPage extends AtriumBasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
	}

	public String getHeader() {
		WebElement header = driver.findElement(By.id("page-title"));
		return header.getText();
	}
	
	public List<Task> getTasks() {
		List<Task> taskList = new ArrayList<Task>();
		
		return taskList; 
	}
	
	public List<Topic> getTopics() {
		List<Topic> topicList = new ArrayList<Topic>();
		
		return topicList; 
	}
	
	public List<Topic> getReplyTopics() {
		List<Topic> replyTopicList = new ArrayList<Topic>();
		
		return replyTopicList; 
	}
}
