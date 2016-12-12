package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cindy.SeleniumCommon.BasePage;

/**
 * Page object representing the Search Results page.
 * 
 * @author Cindy
 *
 */
public class SearchResultsPage extends AtriumBasePage {

	private By resultsLocator = By.className("view-empty");
	
	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public boolean noResultsFound() {
		if (isElementPresent(resultsLocator)) {
			return true;
		} else {
			return false;
		}
		
	}
}
