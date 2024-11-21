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
    private String baseUrl; // Add baseUrl field
//
//    @Parameters({ "browser", "headless", "baseUrl" }) // Add baseUrl parameter
//    @BeforeTest
//    public void loginPageSetup(
//            @Optional("chrome") String browser, 
//            @Optional("false") String headless, 
//            @Optional("") String baseUrl) {
//        pf = new PlaywrightFactory();
//        prop = pf.init_prop(); // Initialize from config file
//
//        if (browser == null || browser.isEmpty()) {
//            browser = prop.getProperty("browser", "chrome");
//        }
//
//        if (headless == null || headless.isEmpty()) {
//            headless = prop.getProperty("headless", "false");
//        }
//
//        if (baseUrl == null || baseUrl.isEmpty()) {
//            baseUrl = prop.getProperty("baseUrl", "https://rc.truvideo.com/login");
//        }
//
//        this.baseUrl = baseUrl; // Set baseUrl dynamically
//
//        boolean headlessMode = Boolean.parseBoolean(headless);
//        page = pf.initBrowser(browser, headlessMode);
//
//        loginpage = new LoginPage(page); // Initialize LoginPage with the Page instance
//
//        // Navigate to the base URL after browser initialization
//        page.navigate(this.baseUrl);
//    }
//
//    @AfterTest
//    public void tearDown() {
//        page.context().browser().close();
//    }
//}

@Parameters({ "browser", "headless", "baseUrl" })
@BeforeTest
public void loginPageSetup(
        @Optional("chrome") String browser, 
        @Optional("false") String headless, 
        @Optional("") String baseUrl) {
    pf = new PlaywrightFactory();
    prop = pf.init_prop(); // Load config.properties

    // System Property Check (Highest Priority)
    baseUrl = prop.getProperty("baseUrl", baseUrl);
    browser = prop.getProperty("browser", browser);
    headless = prop.getProperty("headless", headless);

    // XML Parameter Check (Medium Priority)
    if (baseUrl == null || baseUrl.isEmpty()) {
        baseUrl = prop.getProperty("baseUrl", "https://rc.truvideo.com/login"); // Fallback to config.properties
    }
    if (browser == null || browser.isEmpty()) {
        browser = prop.getProperty("browser", "chrome");
    }
    if (headless == null || headless.isEmpty()) {
        headless = prop.getProperty("headless", "false");
    }

    this.baseUrl = baseUrl;
    System.out.println("Final Base URL: " + this.baseUrl);

    boolean headlessMode = Boolean.parseBoolean(headless);
    page = pf.initBrowser(browser, headlessMode);

    loginpage = new LoginPage(page);
    page.navigate(this.baseUrl);
}
}

