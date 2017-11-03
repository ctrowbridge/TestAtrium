package com.cindy.testatrium.steps;

import java.util.List;

import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Contains step definitions for Cucumber tests for Content page tests.
 * 
 * @author Cindy
 */
public class ContentStepDefs extends BaseStepDefs {

	@When("^I open the Content Page$")
	public void i_open_the_Content_Page() throws Throwable {

		logger.info("Open Content page ...");

		Assert.assertNotNull(homePage);
		contentPage = homePage.selectContentFromMenu();
		logger.info(" contentPage = " + contentPage);
		Assert.assertNotNull(contentPage);
		
		logger.debug(" contentPage title       = " + contentPage.getTitle());
		logger.debug(" contentPage header      = " + contentPage.getHeader());
		logger.debug(" contentPage breadCrumbs = " + contentPage.getBreadcrumbs());
	}

	@Then("^I see the Content Page$")
	public void i_see_the_Content_Page() throws Throwable {
	   
		Assert.assertNotNull(contentPage);
		Assert.assertTrue(contentPage.isContentPage());
	}
	
	@Then("^the Content page has a <tab> tab$")
	public void the_Content_page_has_a_tab_tab(DataTable tabList) throws Throwable {
		
		Assert.assertNotNull(contentPage);
		
		int row = 0;
		List<List<String>> data = tabList.raw();
		for (List<String> rows : data) {
			for (String col : rows) {
				logger.debug(" col = " + col);
				
				if (row > 0) {
					Assert.assertTrue(contentPage.tabExists(col));
				}
				row++;
			}
		}
	}
	
	@When("^I select Add content from the Content page$")
	public void i_select_Add_content_from_the_Content_page() throws Throwable {
	    
		logger.info("Select Add Content ...");
		Assert.assertNotNull(contentPage);
		addContentPage = contentPage.selectAddContent();
		logger.info(" addContentPage = " + addContentPage);
		Assert.assertNotNull(addContentPage);
		
		logger.debug(" addContentPage title       = " + addContentPage.getTitle());
		logger.debug(" addContentPage header      = " + addContentPage.getHeader());
		logger.debug(" addContentPage breadCrumbs = " + addContentPage.getBreadcrumbs());
		
		List<String> options = addContentPage.getContentOptions();
		for (String option : options) {
			logger.debug(" addContentPage option = " + option);
		}
	}

	@Then("^I see the Add Content page$")
	public void i_see_the_Add_Content_page() throws Throwable {

		Assert.assertNotNull(addContentPage);
		Assert.assertTrue(addContentPage.isAddContentPage());
	}
	
	@When("^I select Group from the Add Content page$")
	public void i_select_Group_from_the_Add_Content_page() throws Throwable {
	   
		logger.info("Select Group ...");

		Assert.assertNotNull(addContentPage);
		createGroupPage = addContentPage.selectGroup();
		logger.info(" createGroupPage = " + createGroupPage);
		Assert.assertNotNull(createGroupPage);
		
		logger.debug(" createGroupPage title       = " + createGroupPage.getTitle());
		logger.debug(" createGroupPage header      = " + createGroupPage.getHeader());
	}

	@Then("^I see the Create Group page$")
	public void i_see_the_Create_Group_page() throws Throwable {
		
		Assert.assertNotNull(createGroupPage);
		Assert.assertTrue(createGroupPage.isCreateGroupPage());
	}
}
