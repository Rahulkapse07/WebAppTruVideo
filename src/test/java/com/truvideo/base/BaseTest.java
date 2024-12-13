package com.truvideo.base;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;
import com.truvideo.testutils.TestUtils;

public class BaseTest {

	protected PlaywrightFactory pf;
	public Page page;
	protected Properties prop;
	protected LoginPage loginpage;
	protected ExtentTest test;
	
	TestUtils util = new TestUtils();
	
	private String baseUrl; // For storing the final base URL

	@BeforeClass
	@Parameters({ "browser", "headless", "env" })
	public void loginPageSetup(
			@Optional("chrome") String browser, 
			@Optional("false") String headless,
			@Optional("stagingrc") String env)
			{

		pf = new PlaywrightFactory();
		prop = pf.init_prop(); // Load config.properties

		// Use config.properties as default, override with XML values if provided
		browser = prop.getProperty("browser", browser);
		headless = prop.getProperty("headless", headless);

		  baseUrl = util.getBaseUrlForEnvironment(env);
	    
		if (baseUrl.isEmpty()) {
			baseUrl = prop.getProperty("baseUrl");
		}
		if (baseUrl == null || baseUrl.isEmpty()) {
			throw new IllegalArgumentException("Base URL must be specified in the XML file or config.properties");
		}

		boolean headlessMode = Boolean.parseBoolean(headless);
		page = pf.initBrowser(browser, headlessMode);

		loginpage = new LoginPage(page);
		page.navigate(baseUrl);

	}

	@AfterClass
	public void tearDown() {
		String destinationField = System.getProperty("user.dir") + "/Reports/";
		String traceFilePath = destinationField + "trace.zip";
		pf.stopTracing(traceFilePath);
		pf.closeBrowser();
	}
}

