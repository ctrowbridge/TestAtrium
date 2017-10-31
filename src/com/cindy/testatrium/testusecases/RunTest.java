package com.cindy.testatrium.testusecases;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src//features", plugin = { "pretty",
		"html:target/cucumber-html-report" }, glue = "com.cindy.testatrium.steps", tags = { "@wip" })

/**
 * Runs a set of Cucumber tests using TestNG
 * 
 * @author Cindy
 */
public class RunTest extends AbstractTestNGCucumberTests {

	@Test
	public void f() {
	}
}
