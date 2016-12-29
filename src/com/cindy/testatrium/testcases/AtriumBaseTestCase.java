package com.cindy.testatrium.testcases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	protected final String userCounterFileName = "user_counter.txt";

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

	protected int getUserCounter() throws IOException {
		File file = new File(userCounterFileName);
		int counter = 1;
		if (file.exists()) {
			FileReader fr = new FileReader(userCounterFileName);
			BufferedReader br = new BufferedReader(fr);
			String count = br.readLine();
			counter = Integer.parseInt(count);
		} else {
			FileWriter fw = new FileWriter(userCounterFileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(counter);
			bw.close();
			fw.close();
		}
		return counter;
	}
	
	protected void updateUserCounter() throws IOException {
		int counter = getUserCounter() + 1;
		FileWriter fw = new FileWriter(userCounterFileName);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(counter);
		bw.close();
		fw.close();
	}
}
