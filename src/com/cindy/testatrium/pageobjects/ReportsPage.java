package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.WebDriver;

/**
 * Page Object representing the Reports page. The Reports page lets you
 * generate several reports.
 * 
 * @author Cindy
 *
 */
public class ReportsPage extends AtriumBasePage {

	String url = "http://127.0.0.1/openatrium/admin";
	
	public ReportsPage(WebDriver driver) {
		super(driver);
	}

}
