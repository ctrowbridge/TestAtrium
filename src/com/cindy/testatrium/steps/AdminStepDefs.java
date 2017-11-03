package com.cindy.testatrium.steps;

import java.util.List;

import org.testng.Assert;

import com.cindy.testatrium.data.Task;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.LoginPage;
import com.cindy.testatrium.pageobjects.SearchResultsPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Contains step definitions for Cucumber tests for Admin page tests.
 * 
 * @author Cindy
 */
public class AdminStepDefs extends BaseStepDefs {
	
	private final String adminHeader = "Access Denied";

	private static final String noOp = "No operation selected. Please select an operation to perform.";
	private static final String noItem = "Please select at least one item.";
	
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

	@Then("^I see the Admin page$")
	public void i_see_the_Admin_page() throws Throwable {

		Assert.assertNotNull(adminPage);
		Assert.assertTrue(adminPage.isAdminPage());
		
		List<Task> tasks = adminPage.getTasks();
		logger.debug(" number of tasks = " + tasks.size());
		for (Task task : tasks) {
			logger.debug(" task = " + task);
		}
		
		String breadcrumbs = adminPage.getBreadcrumbs();
		logger.debug(" breadcrumbs = " + breadcrumbs);
	}
	
	@Then("^the Admin page has a \"([^\"]*)\" tab$")
	public void the_Admin_page_has_a_tab(String tabName) throws Throwable {
	    
		Assert.assertNotNull(adminPage);
		Assert.assertTrue(adminPage.tabExists(tabName));
	}

	@Then("^the Admin page has a <tab> tab$")
	public void the_Admin_page_has_a_task_tab(DataTable tabList) throws Throwable {

		Assert.assertNotNull(adminPage);
		
		int row = 0;
		List<List<String>> data = tabList.raw();
		for (List<String> rows : data) {
			for (String col : rows) {
				logger.debug(" col = " + col);
				
				if (row > 0) {
					Assert.assertTrue(adminPage.tabExists(col));
				}
				row++;
			}
		}
	}

	@Then("^the Admin Page has a <task> task$")
	public void the_Admin_Page_has_a_task_task(DataTable taskList) throws Throwable {
	    
		Assert.assertNotNull(adminPage);
		
		int row = 0;
		List<List<String>> data = taskList.raw();
		for (List<String> rows : data) {
			for (String col : rows) {
				logger.debug(" col = " + col);
				
				if (row > 0) {
					Assert.assertTrue(adminPage.taskExists(col));
				}
				row++;
			}
		}
	}

	@When("^I select the Reports task from the Admin page$")
	public void i_select_the_Reports_task_from_the_Admin_page() throws Throwable {

		logger.info("Open Reports page ...");
		reportsPage = adminPage.selectReportsPage();
		logger.info(" reportsPage = " + reportsPage);
		Assert.assertNotNull(reportsPage);
		
		List<Task> tasks = reportsPage.getTasks();
		logger.debug(" number of tasks = " + tasks.size());
		for (Task task : tasks) {
			logger.debug(" task = " + task);
		}
		
		String breadcrumbs = reportsPage.getBreadcrumbs();
		logger.debug(" breadcrumbs = " + breadcrumbs);
	}

	@Then("^I see the Reports page$")
	public void i_see_the_Reports_page() throws Throwable {
		
	   Assert.assertTrue(reportsPage.isReportsPage());
	}
	
	@Then("^the Reports page as a <task> task$")
	public void the_Reports_page_as_a_task_task(DataTable taskList) throws Throwable {
		
		Assert.assertNotNull(reportsPage);
		
		int row = 0;
		List<List<String>> data = taskList.raw();
		for (List<String> rows : data) {
			for (String col : rows) {
				logger.debug(" col = " + col);
				
				if (row > 0) {
					Assert.assertTrue(reportsPage.taskExists(col));
				}
				row++;
			}
		}
	}
	
	@When("^I select the People task from the Admin page$")
	public void i_select_the_People_task_from_the_Admin_page() throws Throwable {
	    
		logger.info("Open People page ...");
		peoplePage = adminPage.selectPeoplePage();
		logger.info(" peoplePage = " + peoplePage);
		Assert.assertNotNull(peoplePage);
		
		logger.info(" breadcrumbs = " + peoplePage.getBreadcrumbs());
		String title = peoplePage.getTitle();
		logger.info(" title = " + title);
		
		logger.info("Display people ...");
		List<UserInfo> people = peoplePage.getPeople();
		logger.info(" people size = " + people.size());
		for (UserInfo person : people) {
			logger.info("  name          = " + person.getUsername());
			logger.info("   email        = " + person.getEmail());
			logger.info("   status       = " + person.getStatus());
			logger.info("   display name = " + person.getDisplayName());
		}
	}

	@Then("^I see the People page$")
	public void i_see_the_People_page() throws Throwable {

		Assert.assertNotNull(peoplePage);
		Assert.assertTrue(peoplePage.isPeoplePage());
	}
	
	@When("^I set the Username filter name to \"([^\"]*)\"$")
	public void i_set_the_Username_filter_name_to(String arg1) throws Throwable {
		
		logger.info("Apply filter ...");
		peoplePage.setFilterUsername("XXXXXXX");
		peoplePage.selectApplyFilter();
	}

	@Then("^I see no Users available$")
	public void i_see_no_Users_available() throws Throwable {
		
		logger.info("Check people ...");
		Assert.assertNotNull(peoplePage);
		
		List<UserInfo> people = peoplePage.getPeople();
		logger.info(" people size = " + people.size());		
		Assert.assertEquals(people.size(), 0);
	}
	
	@When("^I select the Execute button from the People page$")
	public void i_select_the_Execute_button_from_the_People_page() throws Throwable {

		Assert.assertNotNull(peoplePage);
		logger.info("Click Execute ...");
		peoplePage = peoplePage.selectExecute();
	}

	@Then("^I see no operation selected$")
	public void i_see_no_operation_selected() throws Throwable {

		Assert.assertNotNull(peoplePage);
		String errorMessage = peoplePage.getErrorMessage();
		logger.info(" errorMessage = " + errorMessage);
		Assert.assertTrue(errorMessage.contains(noOp));
		Assert.assertTrue(errorMessage.contains(noItem));
	}
}
