package com.truvideo.factory;

import com.microsoft.playwright.*;
import com.truvideo.utility.JavaUtility;
import com.truvideo.utility.LoginUtils;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;

public class PlaywrightFactory extends JavaUtility {
    Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
    private static final ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
    private static final ThreadLocal<Page> tlPage = new ThreadLocal<>();
    private static Properties prop;

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
        System.out.println("Initializing browser: " + browserName);
        tlPlaywright.set(Playwright.create());

        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("--start-maximized");
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screensize.getWidth();
        int height = (int) screensize.getHeight();
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                .setHeadless(headless)
                .setArgs(arguments);
        switch (browserName.toLowerCase()) {
            case "chrome":
                tlBrowser.set(getPlaywright().chromium().launch(launchOptions));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(launchOptions));
                break;
            case "webkit":
                tlBrowser.set(getPlaywright().webkit().launch(launchOptions));
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser name: " + browserName);
        }
        if (Paths.get(prop.getProperty("sessionPath")).toFile().exists()) {
            System.out.println("Reusing session storage from: " + prop.getProperty("sessionPath"));
            tlBrowserContext.set(getBrowser().newContext(
                    new Browser.NewContextOptions().setStorageStatePath(Paths.get(prop.getProperty("sessionPath")))
            ));
        } else {
            System.out.println("Creating new browser context and saving session.");
            tlBrowserContext.set(getBrowser().newContext());
            tlPage.set(getBrowserContext().newPage());
            LoginUtils loginUtils = new LoginUtils();
            loginUtils.loginAndSaveSession(prop.getProperty("loginUrl"), prop.getProperty("username"), prop.getProperty("password"), prop.getProperty("sessionPath"));
        }
        tlPage.set(getBrowserContext().newPage());
        return getPage();
    }

    public static Properties init_prop() {
        if (prop == null) {
        	prop = new Properties();
            try(FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties")){
                prop.load(ip);
                System.out.println("Properties loaded successfully.");
            } catch (IOException e) {
                System.err.println("Error loading properties file:" + e.getMessage());
                e.printStackTrace();
            }
        }else{
        	 System.out.println("Properties already initialized.");
        }
        return prop;
    }

    public static void cleanUp() {
        if (tlBrowserContext.get() != null) {
            tlBrowserContext.get().close();
        }
        if (tlBrowser.get() != null) {
            tlBrowser.get().close();
        }
        if (tlPlaywright.get() != null) {
            tlPlaywright.get().close();
        }

        tlBrowserContext.remove();
        tlBrowser.remove();
        tlPage.remove();
        tlPlaywright.remove();

        System.out.println("Resources cleaned up successfully.");
    }
}
