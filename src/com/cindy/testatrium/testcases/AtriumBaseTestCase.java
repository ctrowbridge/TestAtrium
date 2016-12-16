package com.cindy.testatrium.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.cindy.SeleniumCommon.BaseTestCase;
import com.cindy.testatrium.data.UserInfo;
import com.cindy.testatrium.pageobjects.LoginPage;

/**
 * Base Test Case for Atrium tests.
 * 
 * @author Cindy
 */
public abstract class AtriumBaseTestCase extends BaseTestCase {

	protected LoginPage loginPage;

	protected final String userName = "user";
	protected final String siteTitle = "Cindy's Site";
	protected final UserInfo mainUser = new UserInfo(userName, "admin");

	private static final Logger logger = LogManager.getLogger("AtriumBaseTestCase");

	/**
	 * Opens the Login page.
	 * 
	 * @throws InterruptedException
	 */
	protected void openLoginPage() throws InterruptedException {
		
		logger.info("Open Login page ...");
		loginPage = new LoginPage(driver);
		loginPage = loginPage.open();
		logger.info(" loginPage = " + loginPage);
		String loginPageTitle = loginPage.getTitle();

		logger.info(" title = " + loginPageTitle);
		Assert.assertEquals(siteTitle, siteTitle);
	}

}
