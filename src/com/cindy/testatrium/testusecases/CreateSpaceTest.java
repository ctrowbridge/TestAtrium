package com.cindy.testatrium.testusecases;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.testatrium.data.Space;
import com.cindy.testatrium.data.Space.GroupVisibility;
import com.cindy.testatrium.pageobjects.AddContentPage;
import com.cindy.testatrium.pageobjects.CreateDefaultSpacePage;
import com.cindy.testatrium.pageobjects.HomePage;
import com.cindy.testatrium.pageobjects.SiteMapPage;
import com.cindy.testatrium.pageobjects.SpacePage;
import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Contains tests to run Scenario 1
 * 
 * @author Cindy
 */
/*-
 * Scenario 1:  Create a new Default Space
 * 		given  A new instance of OpenAtrium
 *  	and    OpenAtrium is running
 *  	then   Open Login page
 * 		and    Log in as user
 *  	and    Create a default space
 *      and    Ensure default space
 *      and    Logoff
 */
public class CreateSpaceTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("CreateSpaceTest");
	
	private static String spaceName = "My Default Space";
	private static String spaceDescription = "My Default Space Description";
	
	HomePage homePage;
	SiteMapPage siteMapPage;
	AddContentPage addContentPage;
	SpacePage spacePage;

	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}

	@Test
	public void basicTest() throws InterruptedException {
		
		logger.info("Start CreateSpaceTest");
		
		openLoginPage();
		login();
		createDefaultSpace();
		checkDefaultSpace();
			
		logout();
	}

	private void login() {
		logger.info("Login in to site ...");
		homePage = loginPage.login(mainUser);
	}

	private void createDefaultSpace() throws InterruptedException {
		
		logger.info("Click on Create Content ...");
		addContentPage  = homePage.selectAddContentFromMenu();
		logger.info(" addContentPage = " + addContentPage);
		
		String title = addContentPage.getTitle();
		logger.info(" title = " + title);
		Assert.assertEquals(title, "Add content | " + siteTitle);
		
		List<String> contentLinks = addContentPage.getContentOptions();
		for (String linkText : contentLinks) {
			logger.info(" link text = " + linkText);
		}
		
		logger.info("Select Space ...");
		CreateDefaultSpacePage defaultSpacePage = addContentPage.selectSpace();
		logger.info(" defaultSpacePage = " + defaultSpacePage);
		title = defaultSpacePage.getTitle();
		Assert.assertEquals(title, "Create Default Space | " + siteTitle);
		
		logger.info("Create default space ...");
		Space newSpace = new Space();
		newSpace.setTitle(spaceName);
		newSpace.setDescription(spaceDescription);
		newSpace.setGroupVisibilty(GroupVisibility.PUBLIC);
		newSpace.setProvideMenuLink(true);
		
		spacePage = defaultSpacePage.createSpace(newSpace);
		logger.info(" spacePage = " + spacePage);
		
		spacePage.selectHome();
		
	}
	
	private void checkDefaultSpace() {
		logger.info("Check default space ...");
		Assert.assertTrue(homePage.isSpaceMenuPresent(spaceName));
		
	}
	
	private void logout() throws InterruptedException {
		
		logger.info("Log out of site ...");
		loginPage = loginPage.logout();
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}
}
