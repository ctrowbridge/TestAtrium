package com.cindy.testatrium.steps;

import org.openqa.selenium.WebDriver;

import com.cindy.SeleniumCommon.BaseTestCase;

public class AtriumTest extends BaseTestCase {

	public AtriumTest() {
		super();
	}

	public void setup() {
		createDriver(DriverType.CHROME, 10);
	}
	
	public WebDriver getDriver() {
		return driver;
	}
}
