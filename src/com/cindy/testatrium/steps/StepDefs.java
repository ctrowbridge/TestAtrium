package com.cindy.testatrium.steps;

import org.testng.Assert;

import com.cindy.SeleniumCommon.BaseUtils;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.LoginPage;
import com.cindy.testatrium.pageobjects.SearchResultsPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Contains step definitions for Cucumber tests.
 * 
 * @author Cindy
 */
public class StepDefs extends BaseStepDefs {
	
	private final String siteHeader = "SITE MAP FOR " + siteTitle.toUpperCase();

	@Given("^I open a browser$")
	public void i_open_a_browser() throws Throwable {
		
		test = new AtriumTest();
		test.setup();
		//AdminTest
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
	
	@When("^I login as admin user$")
	public void i_login_as_admin_user() throws Throwable {
	  
		logger.info("Login as Admin User ...");
		Assert.assertNotNull(loginPage);
		homePage = loginPage.login(mainUser);
		logger.info(" homePage = " + homePage);
	}

	@After
	public void afterClass() {

		logger.info("Close driver ...");
		if (test != null) {
			test.shutdown();
		}
	}
	
	@Then("^Documentation URL is valid$")
	public void documentation_URL_is_valid() throws Throwable {
		
		logger.info("Check documentation URL ...");
		String url = loginPage.getDocumentationURL();
		logger.info(" url = " + url);
		boolean urlOk = BaseUtils.checkURL(url);
		logger.info(" urlOK = " + urlOk);
		Assert.assertTrue(urlOk);
	}
}
