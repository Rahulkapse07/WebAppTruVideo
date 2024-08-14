package com.truvideo.base;

import java.util.Properties;
import org.testng.annotations.*;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;

public class BaseTest {
	protected PlaywrightFactory pf;
	public Page page;
	protected Properties prop;
	protected LoginPage loginpage;

	@Parameters({ "browser", "headless" }) // this line is added
	@BeforeTest
	public void loginPageSetup(@Optional("chrome") String browser, @Optional("false") String headless) {
		pf = new PlaywrightFactory();
		prop = pf.init_prop(); // will call config file

		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser", "chrome");
		}

		if (headless == null || headless.isEmpty()) {
			headless = prop.getProperty("headless", "false");
		}

		boolean headlessMode = Boolean.parseBoolean(headless);
		page = pf.initBrowser(browser, headlessMode);

		loginpage = new LoginPage(page); // Initialize LoginPage with the Page instance
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
