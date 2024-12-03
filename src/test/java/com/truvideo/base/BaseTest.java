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

	private String baseUrl; // For storing the final base URL

	@BeforeClass
	@Parameters({ "browser", "headless", "baseUrl" })
	public void loginPageSetup(
			@Optional("chrome") String browser, 
			@Optional("false") String headless,
			@Optional("") String baseUrl) {

		pf = new PlaywrightFactory();
		prop = pf.init_prop(); // Load config.properties

		// Use config.properties as default, override with XML values if provided
		browser = prop.getProperty("browser", browser);
		headless = prop.getProperty("headless", headless);

		// Check if baseUrl was passed via XML; if not, use the default from properties
		if (baseUrl.isEmpty()) {
			baseUrl = prop.getProperty("baseUrl");
		}

		if (baseUrl == null || baseUrl.isEmpty()) {
			throw new IllegalArgumentException("Base URL must be specified in the XML file or config.properties");
		}

		System.out.println("Browser: " + browser);
		System.out.println("Headless: " + headless);
		System.out.println("Final Base URL: " + baseUrl);

		boolean headlessMode = Boolean.parseBoolean(headless);
		page = pf.initBrowser(browser, headlessMode);

		loginpage = new LoginPage(page);
		//page.navigate(baseUrl);

	}

	@AfterClass
	public void tearDown() {
		String destinationField = System.getProperty("user.dir") + "/Reports/";
		String traceFilePath = destinationField + "trace.zip";
		pf.stopTracing(traceFilePath);
		pf.closeBrowser();
	}
}

