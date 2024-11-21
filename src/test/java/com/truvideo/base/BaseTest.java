package com.truvideo.base;

import java.util.Properties;
import org.testng.annotations.*;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;

public class BaseTest {
  // Add baseUrl field
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

//@Parameters({ "browser", "headless", "baseUrl" , "Produrl" })
//@BeforeTest
//public void loginPageSetup(
//        @Optional("chrome") String browser, 
//        @Optional("false") String headless, 
//        @Optional("") String baseUrl,
//        @Optional("") String Produrl ){
//    pf = new PlaywrightFactory();
//    prop = pf.init_prop(); // Load config.properties
//
//    // System Property Check (Highest Priority)
//    baseUrl = System.getProperty("baseUrl", baseUrl);
//    browser = prop.getProperty("browser", browser);
//    headless = System.getProperty("headless", headless);
//
//    // XML Parameter Check (Medium Priority)
//    if (baseUrl == null || baseUrl.isEmpty()) {
//        baseUrl = prop.getProperty("baseUrl", "https://rc.truvideo.com/login"); // Fallback to config.properties
//    }
//    if (browser == null || browser.isEmpty()) {
//        browser = prop.getProperty("browser", "chrome");
//    }
//    if (headless == null || headless.isEmpty()) {
//        headless = prop.getProperty("headless", "false");
//    }
//
//    this.baseUrl = baseUrl;
//    System.out.println("Final Base URL: " + this.baseUrl);
//
//    boolean headlessMode = Boolean.parseBoolean(headless);
//    page = pf.initBrowser(browser, headlessMode);
//
//    loginpage = new LoginPage(page);
//    page.navigate(this.baseUrl);
//}
//}
   
        protected PlaywrightFactory pf;
        public Page page;
        protected Properties prop;
        protected LoginPage loginpage;
        private String baseUrl;

        @Parameters({ "browser", "headless", "baseUrl", "Produrl", "environment" })
        @BeforeTest
        public void loginPageSetup(
                @Optional("chrome") String browser, 
                @Optional("false") String headless, 
                @Optional("") String baseUrl,
                @Optional("") String Produrl,
                @Optional("staging") String environment) { // Add environment parameter to distinguish staging/production

            pf = new PlaywrightFactory();
            prop = pf.init_prop(); // Load config.properties

            // System Property Check (Highest Priority)
            baseUrl = prop.getProperty("baseUrl", baseUrl);
            Produrl = prop.getProperty("Produrl", Produrl);
            browser = prop.getProperty("browser", browser);
            headless = System.getProperty("headless", headless);

            // Environment-based Check: Default to 'staging' if not provided
            if (environment.equals("prod")) {
                baseUrl = Produrl; // Use Produrl if environment is 'prod'
            } else {
                // Use baseUrl (RC) for non-prod (staging or others)
                if (baseUrl == null || baseUrl.isEmpty()) {
                    baseUrl = prop.getProperty("baseUrl", "https://rc.truvideo.com/login"); // Fallback to config.properties
                }
            }

            // If baseUrl is still empty, default to RC URL
            if (baseUrl == null || baseUrl.isEmpty()) {
                baseUrl = "https://rc.truvideo.com/login";
            }

            if (browser == null || browser.isEmpty()) {
                browser = prop.getProperty("browser", "chrome");
            }
            if (headless == null || headless.isEmpty()) {
                headless = prop.getProperty("headless", "false");
            }

            this.baseUrl = baseUrl;  // Set the final baseUrl (either RC or production)

            // Log the final baseUrl
            System.out.println("Final Base URL: " + this.baseUrl);

            boolean headlessMode = Boolean.parseBoolean(headless);
            page = pf.initBrowser(browser, headlessMode);

            loginpage = new LoginPage(page);
            page.navigate(this.baseUrl); // Navigate to the selected baseUrl (RC or Production)
        }
    }


