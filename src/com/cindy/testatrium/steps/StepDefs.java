package com.cindy.testatrium.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.LoginPage;
import com.cindy.testatrium.pageobjects.SearchResultsPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {

	private static final Logger logger = LogManager.getLogger("StepDefs");
	
	private AtriumTest test;
	private static final String url = "http://127.0.0.1/openatrium/";
	
	/**
	 * Title for site - this is set when the Bitnami Atrium installer is run.
	 * TODO Read site title from a configuration file.
	 */
	private final String siteTitle = "Cindy's Site";
	private final String siteHeader = "SITE MAP FOR " + siteTitle.toUpperCase();

	private final String adminHeader = "Access Denied";
	
	private LoginPage loginPage = null;
	private SearchResultsPage searchResultsPage = null;
	private SiteMapPage siteMapPage = null;
	private AdminPage adminPage = null;
	
	@Given("^I open a browser$")
	public void i_open_a_browser() throws Throwable {
		
		test = new AtriumTest();
		test.setup();
	}

	@When("^I open Login page$")
	public void i_open_Login_page() throws Throwable {
		
		logger.info("Open login page ...");
		test.getDriver().get(url);
		loginPage = new LoginPage(test.getDriver());
		Assert.assertNotNull(loginPage);
		logger.info(" loginPage title = " + loginPage.getTitle());
	}

	@Then("^User is not logged in$")
	public void user_is_not_logged_in() throws Throwable {
		
		Assert.assertFalse(loginPage.isLoggedIn());
	}
	
	@When("^I search for \"([^\"]*)\"$")
	public void i_search_for(String searchString) throws Throwable {
		
		logger.info("Search for " + searchString + " ...");
		loginPage.enterSearchString(searchString);
		searchResultsPage = loginPage.selectSearch();
		Assert.assertNotNull(searchResultsPage);
		logger.info(" searchResultsPage = " + searchResultsPage);
	}

	@Then("^I see the Search Results page$")
	public void i_see_the_Search_Results_page() throws Throwable {
		
		String title = searchResultsPage.getTitle(); 
		logger.info(" title = " + title);
		Assert.assertEquals(title, "Search | " + siteTitle);
	}
	
	@When("^I select Site Map$")
	public void i_open_Site_Map() throws Throwable {
		
		logger.info("Open Site Map ...");
		siteMapPage = loginPage.selectSiteMap();
		Assert.assertNotNull(siteMapPage);
		logger.info(" siteMapPage = " + siteMapPage);
	}

	@Then("^I see the Site Map page$")
	public void i_see_the_Site_Map_page() throws Throwable {
		
		Assert.assertNotNull(siteMapPage);
		
		String siteMapTitle = siteMapPage.getTitle();
		String header = siteMapPage.getHeader();
		
		logger.info(" title  = " + siteMapTitle);
		logger.info(" header = " + header);
		
		Assert.assertEquals(siteMapTitle, siteTitle);
		Assert.assertEquals(header, siteHeader);	
	}

	@When("^I open Admin page$")
	public void i_open_Admin_page() throws Throwable {
		
		logger.info("Open Admin Page ...");
		adminPage = new AdminPage(test.getDriver());
		Assert.assertNotNull(adminPage);
		adminPage.open();

		logger.info(" adminPage = " + adminPage);
	}
	
	@When("^I open Admin page while user isn't logged in$")
	public void i_open_Admin_page_while_user_isn_t_logged_in() throws Throwable {
		
		logger.info("Open Admin Page ...");
		adminPage = new AdminPage(test.getDriver());
		Assert.assertNotNull(adminPage);
		adminPage.openNotAuthorized();

		logger.info(" adminPage = " + adminPage);
	}

	@Then("^I see Not Authorized message$")
	public void i_see_Not_Authorized_message() throws Throwable {
		
		Assert.assertNotNull(adminPage);
		String header = adminPage.getHeader();
		logger.info(" header = " + header);
		Assert.assertEquals(header, adminHeader);
	}
	
	@After
	public void afterClass() {

		logger.info("Close driver ...");
		if (test != null) {
			test.shutdown();
		}
	}
}
