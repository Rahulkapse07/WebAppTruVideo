package com.truvideo.base;

import java.util.Properties;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;

public class BaseTest {
	protected PlaywrightFactory pf;
	public Page page;
	protected Properties prop;
	protected LoginPage loginpage;
	protected ExtentTest test;

	@Parameters({ "browser", "headless" })

	@BeforeTest
	public void loginPageSetup(@Optional("chrome") String browser, @Optional("false") String headless) {

		pf = new PlaywrightFactory();
		prop = pf.init_prop();

		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser", "chrome");
		}

		if (headless == null || headless.isEmpty()) {
			headless = prop.getProperty("headless", "false");
		}

		boolean headlessMode = Boolean.parseBoolean(headless);
		page = pf.initBrowser(browser, headlessMode);

		if (page != null) {
			loginpage = new LoginPage(page);
			System.out.println("LoginPage initialized: " + (loginpage != null));
		} else {
			System.out.println("Page initialization failed.");
		}
	}

	@AfterTest
	public void tearDown() {
		String destinationField = System.getProperty("user.dir") + "/Reports/";
		String traceFilePath = destinationField + "trace.zip";
		pf.stopTracing(traceFilePath);
		pf.closeBrowser();
	}

}
