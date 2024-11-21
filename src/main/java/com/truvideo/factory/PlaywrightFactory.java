
package com.truvideo.factory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Properties;

import org.testng.annotations.Parameters;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.truvideo.utility.JavaUtility;
import com.microsoft.playwright.BrowserType.LaunchOptions;

public class PlaywrightFactory extends JavaUtility {
	Playwright playwright;
	Browser browser;
	BrowserContext browserContext;
	Page page;

	Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screensize.getWidth();
	int height = (int) screensize.getHeight();
	

	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();

	public static Playwright getPlaywright() {
		return tlPlaywright.get();
	}

	public static Browser getBrowser() {
		return tlBrowser.get();
	}

	public static BrowserContext getBrowserContext() {
		return tlBrowserContext.get();
	}

	public static Page getPage() {
		return tlPage.get();
	}

	
	//public Page initBrowser(Properties prop)
	public Page initBrowser(String browserName, boolean headless)
	{
		//String browserName = prop.getProperty("browser").trim();     //Comment this line if we want to start by parameter
		System.out.println("Browser name is : " + browserName);
		tlPlaywright.set(Playwright.create());
		ArrayList<String> argument = new ArrayList<>();
	    argument.add("--start-maximized");

		switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)));
			break;
		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(new LaunchOptions().setChannel("firefox").setHeadless(false).setArgs(argument)));
			break;
		case "safari":
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			break;
		case "chrome":
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(headless).setArgs(argument)));
			break;
		case "edge":
			tlBrowser.set(
					getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false).setArgs(argument)));
			break;

		default:
			System.out.println("Please pass the right browser name here......");
			break;
		}

		if(browserName.equalsIgnoreCase("chrome" )||browserName.equalsIgnoreCase( "edge")) {
			tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));
			tlPage.set(getBrowserContext().newPage());
			getPage().navigate(prop.getProperty("baseUrl").trim());
			//getPage().setViewportSize(width, height);
			return getPage();
		}else {
		
		tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width, height)));
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("baseUrl").trim());
		getPage().setViewportSize(width, height);
		return getPage();
		
		}
		
	}

	/*
	 * public static String takeScreenshot() { String path =
	 * System.getProperty("user.dir" + "/screenshot/" + System.currentTimeMillis() +
	 * ".png"); getPage().screenshot(new
	 * Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true)); return
	 * path; }
	 */

	public static String takeScreenshot_Web() {
		return takeScreenshot(getPage());
	}

}

