package com.truvideo.base;

import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.factory.SessionManagement;
import org.testng.annotations.*;

import java.util.Properties;

import static com.truvideo.factory.SessionManagement.clearSessionFile;

public class BaseTest {
	protected PlaywrightFactory playwrightFactory;
	protected Properties prop;

	@Parameters({"browser", "headless"})
	@BeforeTest
	public void initialize_Browser_And_Save_Session(@Optional("chrome") String browser, @Optional("false") String headless) {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.init_prop();
		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser", "chrome");
		}
		if (headless == null || headless.isEmpty()) {
			headless = prop.getProperty("headless", "false");
		}
		boolean headlessMode = Boolean.parseBoolean(headless);
		// Pre-store session using shared BrowserContext
		clearSessionFile();
		Page sessionPage = playwrightFactory.initBrowser(browser, headlessMode);
		sessionPage.context().browser().close();
	}

	@Parameters({"browser", "headless"})
	@BeforeMethod
	public void initialize_Browser_With_Session(@Optional("chrome") String browser, @Optional("false") String headless) {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.init_prop();
		if (browser == null || browser.isEmpty()) {
			browser = prop.getProperty("browser", "chrome");
		}
		if (headless == null || headless.isEmpty()) {
			headless = prop.getProperty("headless", "false");
		}
		boolean headlessMode = Boolean.parseBoolean(headless);
		playwrightFactory = new PlaywrightFactory();
		SessionManagement sessionManagement = new SessionManagement();
		if (sessionManagement.isBeforeLoggedInClass()) {
			playwrightFactory.initBrowser_WithoutLogin(browser, headlessMode);
		} else {
			playwrightFactory.initBrowser(browser, headlessMode);
		}
	}

	protected Page getPage() {
		return PlaywrightFactory.getCurrentPage();
	}

	@AfterMethod
	public void tearDown() {
		try {
			Page page = PlaywrightFactory.getCurrentPage();
			if (page != null) {
				try {
					page.waitForTimeout(5000);
					page.close();
					System.out.println("Page closed successfully.");
				} catch (Exception e) {
					System.out.println("Error closing page: " + e.getMessage());
				}
			}
			if (PlaywrightFactory.getBrowserContext() != null) {
				try {
					PlaywrightFactory.getBrowserContext().close();
					System.out.println("BrowserContext closed successfully.");
				} catch (Exception e) {
					System.out.println("Error closing BrowserContext: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.out.println("Error during tearDown: " + e.getMessage());
		}
	}

	@AfterTest
	public void clearSession() {
		clearSessionFile();
	}
}
