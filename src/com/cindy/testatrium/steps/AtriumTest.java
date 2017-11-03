package com.cindy.testatrium.steps;

import org.openqa.selenium.WebDriver;

import com.cindy.SeleniumCommon.BaseTestCase;

/**
 * Test object used by Cucumber tests.  Simple wrapper which extends
 * the Base Test Case.
 * 
 * @author Cindy
 */
public class AtriumTest extends BaseTestCase {

	public AtriumTest() {
		super();
	}

	/**
	 * Setup the browser driver.
	 */
	public void setup() {
		createDriver(DriverType.CHROME, 10);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
