package com.truvideo.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;
import org.testng.annotations.*;
import com.microsoft.playwright.Page;

import static com.truvideo.factory.PlaywrightFactory.getBrowserContext;

public class BaseTest {
    protected PlaywrightFactory pf;
    public Page page;
    protected Properties prop;

    @Parameters({"browser", "headless"})
    @BeforeMethod
    public void playwrightSetup(@Optional("chrome") String browser, @Optional("false") String headless) {
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
    }

    @Parameters({"browser", "headless"})
    @BeforeSuite
    public void playwrightSetup_SaveSession(@Optional("chrome") String browser, @Optional("false") String headless) {
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
        getBrowserContext().browser().close();
    }

    @AfterMethod
    public void tearDown() {
        pf.cleanUp();
    }

    @AfterSuite
    public void clearSession() {
        pf = new PlaywrightFactory();
        String sessionPath = pf.init_prop().getProperty("sessionPath");
        if (sessionPath != null && !sessionPath.isEmpty()) {
            try {
                Path path = Path.of(sessionPath);
                if (Files.exists(path)) {
                    Files.delete(path);
                    System.out.println("Session file cleared: " + sessionPath);
                } else {
                    System.out.println("Session file does not exist: " + sessionPath);
                }
            } catch (IOException e) {
                System.err.println("Failed to clear session file: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Session path is not configured.");
        }
    }
}