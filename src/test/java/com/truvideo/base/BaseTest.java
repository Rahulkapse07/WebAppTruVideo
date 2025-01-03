package com.truvideo.base;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.BrowserContext;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.factory.SessionManagement;
import org.testng.annotations.*;
import java.util.Properties;
import static com.truvideo.factory.SessionManagement.clearSessionFile;

public class BaseTest {

	private PlaywrightFactory playwrightFactory;
	private Properties prop;

	@Parameters({"browser", "headless"})
	@BeforeTest
	public void initializeBrowserAndSaveSession(@Optional("chrome") String browser, @Optional("false") String headless) {
		playwrightFactory = new PlaywrightFactory();
		prop = playwrightFactory.init_prop();

		// Set browser and headless defaults
		browser = (browser == null || browser.isEmpty()) ? prop.getProperty("browser", "chrome") : browser;
		headless = (headless == null || headless.isEmpty()) ? prop.getProperty("headless", "false") : headless;

		boolean headlessMode = Boolean.parseBoolean(headless);

		// Clear previous session
		clearSessionFile();

		// Initialize shared browser context and close it to save the session
		try {
			Page sessionPage = playwrightFactory.initBrowser(browser, headlessMode);
			if (sessionPage != null) {
				sessionPage.context().browser().close();
			}
		} catch (Exception e) {
			System.err.println("Error during session initialization: " + e.getMessage());
		}
	}

	@Parameters({"browser", "headless"})
	@BeforeMethod
	public void initializeBrowserWithSession(@Optional("chrome") String browser, @Optional("false") String headless) {
		prop = playwrightFactory.init_prop();

		// Set browser and headless defaults
		browser = (browser == null || browser.isEmpty()) ? prop.getProperty("browser", "chrome") : browser;
		headless = (headless == null || headless.isEmpty()) ? prop.getProperty("headless", "false") : headless;

		boolean headlessMode = Boolean.parseBoolean(headless);

		SessionManagement sessionManagement = new SessionManagement();
		try {
			if (sessionManagement.isBeforeLoggedInClass()) {
				playwrightFactory.initBrowser_WithoutLogin(browser, headlessMode);
			} else {
				playwrightFactory.initBrowser(browser, headlessMode);
			}
		} catch (Exception e) {
			System.err.println("Error during browser initialization: " + e.getMessage());
		}
	}

	protected Page getPage() {
		return PlaywrightFactory.getCurrentPage();
	}

	@AfterMethod
	public void tearDown() {
		try {
			// Close the page
			Page page = PlaywrightFactory.getCurrentPage();
			if (page != null) {
				try {
					page.waitForTimeout(5000); // Optional: Add a short wait if needed
					page.close();
					System.out.println("Page closed successfully.");
				} catch (Exception e) {
					System.err.println("Error closing page: " + e.getMessage());
				}
			}

			// Close the browser context
			BrowserContext context = PlaywrightFactory.getBrowserContext();
			if (context != null) {
				try {
					context.close();
					System.out.println("BrowserContext closed successfully.");
				} catch (Exception e) {
					System.err.println("Error closing BrowserContext: " + e.getMessage());
				}
			}
		} catch (Exception e) {
			System.err.println("Error during tearDown: " + e.getMessage());
		}
	}

	@AfterTest
	public void clearSession() {
		try {
			clearSessionFile();
			System.out.println("Session file cleared successfully.");
		} catch (Exception e) {
			System.err.println("Error clearing session file: " + e.getMessage());
		}
	}
}
