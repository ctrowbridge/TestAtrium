package com.cindy.testatrium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends AtriumBasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeader() {
		WebElement header = driver.findElement(By.tagName("h2"));
		return header.getText();
	}
	
}
