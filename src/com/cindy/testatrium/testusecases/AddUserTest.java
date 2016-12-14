package com.cindy.testatrium.testusecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cindy.SeleniumCommon.BaseTestCase.DriverType;
import com.cindy.testatrium.testcases.AtriumBaseTestCase;

/**
 * Contains test to run Scenario 2
 * 
 * @author Cindy
 */
/*-
 * Scenario 2: Add User to Site
 *  	given  A new instance of OpenAtrium
 *  	and    OpenAtrium is running
 *  	then   Open Login page
 * 		and    Log in as user
 *  	and    Select option to add new user
 *      and    Add new user
 *      and    Ensure new user was added
 *      and    Logoff
 */
public class AddUserTest extends AtriumBaseTestCase {

	private static final Logger logger = LogManager.getLogger("AddUserTest");
	
	@BeforeClass
	public void beforeClass() {
		logger.info("Create driver ...");
		createDriver(DriverType.CHROME, 15);
	}
	
	@Test
	public void f() {
		
	}
	
	@AfterClass
	public void afterClass() {

		logger.info("Close driver ...");
		driver.close();
	}
}
