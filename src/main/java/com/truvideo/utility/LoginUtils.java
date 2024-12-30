package com.truvideo.utility;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.pages.LoginPage;

import java.nio.file.Paths;

import static com.truvideo.factory.PlaywrightFactory.getBrowserContext;

public class LoginUtils {

    public static void loginAndSaveSession(String loginUrl, String username, String password, String sessionPath) {
        Page page = PlaywrightFactory.getPage();
        page.navigate(loginUrl);
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginToApplication(username, password);
        page.waitForTimeout(5000);
        getBrowserContext().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(sessionPath)));
        System.out.println("Session storage saved to: " + sessionPath);
    }
}
