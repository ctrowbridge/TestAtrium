package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object representing the Reports page. The Reports page lets you
 * generate several reports.
 * 
 * @author Cindy
 *
 */
public class ReportsPage extends AdminBasePage {

	String url = "http://127.0.0.1/openatrium/admin/reports";
	
	private static final String pageTitle = "Reports";
	
	public ReportsPage(WebDriver driver) {
		super(driver);
	}

	public boolean isReportsPage() {
		
		WebElement title = driver.findElement(pageTitleLink);
		System.out.println("*** title = " + title.getText());
		return title.getText().equals(pageTitle);
	}
}
