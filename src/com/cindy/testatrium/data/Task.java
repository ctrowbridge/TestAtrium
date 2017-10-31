package com.cindy.testatrium.data;

import org.openqa.selenium.WebElement;

/**
 * Encapsulates a Task in Atrium.
 * 
 * @author Cindy
 */
public class Task {
	
	String label;
	WebElement link;
	
	public Task(String label, WebElement link) {
		super();
		this.label = label;
		this.link = link;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public WebElement getLink() {
		return link;
	}
	public void setLink(WebElement link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Task [label=" + label + ", link=" + link + "]";
	}

}
