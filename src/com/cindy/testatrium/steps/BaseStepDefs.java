package com.cindy.testatrium.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.pageobjects.AddContentPage;
import com.cindy.testatrium.pageobjects.AddUserPage;
import com.cindy.testatrium.pageobjects.AdminPage;
import com.cindy.testatrium.pageobjects.ContentPage;
import com.cindy.testatrium.pageobjects.CreateGroupPage;
import com.cindy.testatrium.pageobjects.DashboardPage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.pageobjects.LoginPage;
import com.cindy.testatrium.pageobjects.PeoplePage;
import com.cindy.testatrium.pageobjects.ReportsPage;
import com.cindy.testatrium.pageobjects.SearchResultsPage;
import com.cindy.testatrium.pageobjects.SettingsPage;
import com.cindy.testatrium.pageobjects.SiteMapPage;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;


/**
 * Base class for definition steps.
 * 
 * @author Cindy
 */
public class BaseStepDefs {

	/**
	 * Title for site - this is set when the Bitnami Atrium installer is run.
	 * TODO Read site title from a configuration file.
	 */
	protected final String siteTitle = "Cindy's Site";
	
	protected final String userName = "user";
	protected final UserInfo mainUser = new UserInfo(userName, "admin");
	
	protected static final Logger logger = LogManager.getLogger("StepDefs");
	
	protected static AtriumTest test;
	protected static final String url = "http://127.0.0.1/openatrium/";
	
	protected static LoginPage loginPage = null;
	protected static SearchResultsPage searchResultsPage = null;
	protected static SiteMapPage siteMapPage = null;
	protected static AdminPage adminPage = null;
	protected static HomePage homePage = null;
	protected static ReportsPage reportsPage = null;
	protected static PeoplePage peoplePage = null;
	protected static SettingsPage settingsPage = null;
	protected static DashboardPage dashboardPage = null;
	protected static AddUserPage addUserPage = null;
	protected static ContentPage contentPage = null;
	protected static AddContentPage addContentPage = null;
	protected static CreateGroupPage createGroupPage = null;
	
	public BaseStepDefs() {
		super();
	}
}
