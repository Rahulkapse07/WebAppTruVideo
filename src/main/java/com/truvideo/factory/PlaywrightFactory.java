package com.truvideo.factory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.microsoft.playwright.*;
import com.truvideo.utility.JavaUtility;

public class PlaywrightFactory extends JavaUtility {
	private Playwright playwright;
	private Browser browser;
	private BrowserContext browserContext;
	private Page page;

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

	public Page initBrowser(String browserName, boolean headless) {
		System.out.println("Browser name is : " + browserName);
		tlPlaywright.set(Playwright.create());
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add("--start-maximized");

		switch (browserName.toLowerCase()) {
		case "chromium":
			tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)));

			break;
		case "firefox":
			tlBrowser.set(getPlaywright().firefox().launch(
					new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(headless).setArgs(arguments)));
			break;
		case "safari":
			tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
			break;
		case "chrome":
			tlBrowser.set(getPlaywright().chromium().launch(
					new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless).setArgs(arguments)));
			break;
		case "edge":
			tlBrowser.set(getPlaywright().chromium().launch(
					new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(headless).setArgs(arguments)));
			break;
		default:
			System.out.println("Please pass the correct browser name.");
			break;
		}

		tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));

		// UnComment below code to start Tracing of script
 		//startTracing("Test Trace");


 		// UnComment below code to start recording of browser
		String videoDir = System.getProperty("user.dir") + "/Videos/";
// 		 tlBrowserContext.set(
// 		        getBrowser().newContext(
// 		            new Browser.NewContextOptions()
// 		                .setViewportSize(null)
// 		                .setRecordVideoDir(Paths.get(videoDir))
// 		                .setRecordVideoSize(1920, 1080) // Optional: specify video resolution
// 		        )
// 		    );
 		 
 		
		// Initialize page
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("baseUrl").trim());


//		if(browserName.equalsIgnoreCase("chrome" )||browserName.equalsIgnoreCase( "edge")) {
//			tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null)));
//			tlPage.set(getBrowserContext().newPage());
//			getPage().navigate(prop.getProperty("baseUrl").trim());
//			//getPage().setViewportSize(width, height);
//			return getPage();
//		}else {
//		
//		tlBrowserContext.set(getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(width, height)));
//		tlPage.set(getBrowserContext().newPage());
//		getPage().navigate(prop.getProperty("baseUrl").trim());
//		getPage().setViewportSize(width, height);
//
//	return getPage();
//	}
		return getPage();		}


	private Tracing tracing;

	public void startTracing(String testName) {
		try {
			tracing = getBrowserContext().tracing();
			tracing.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setTitle(testName));
			System.out.println("Started tracing for test: " + testName);
		} catch (Exception e) {
			System.out.println("Error starting tracing: " + e.getMessage());
		}
	}

	public void stopTracing(String traceFilePath) {
		try {
			if (tracing != null) {
				tracing.stop(new Tracing.StopOptions().setPath(Paths.get(traceFilePath)));
				System.out.println("Stopped tracing and saved to: " + traceFilePath);
			} else {
				throw new PlaywrightException("Tracing is not active");
			}
		} catch (Exception e) {
			System.out.println("Error stopping tracing: " + e.getMessage());
		}

	}

	public static String takeScreenshot_Web() {
		return takeScreenshot(getPage());
	}

	public void closeBrowser() {
		if (getBrowser() != null) {
			getBrowser().close();
		}
	}
//	public BrowserContext createNewContextWithVideo(String videoDir, String testName) {
//	    // Create a new browser context with video recording enabled
//	    BrowserContext context = getBrowser().newContext(
//	        new Browser.NewContextOptions()
//	            .setViewportSize(null)
//	            .setRecordVideoDir(Paths.get(videoDir + testName)) // Unique directory per test
//	            .setRecordVideoSize(1920, 1080) // Set video resolution
//	    );
//	    tlBrowserContext.set(context); // Store context in ThreadLocal
//	    return context;
//	}


}
