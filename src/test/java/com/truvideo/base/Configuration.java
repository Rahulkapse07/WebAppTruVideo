package com.truvideo.base;

import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.LoginUtils;

import java.util.Properties;

public class Configuration {
    private static Properties prop;

    static {
        try {
            // Initialize properties
            prop = PlaywrightFactory.init_prop();

            // Perform login and save session
            LoginUtils.loginAndSaveSession(
                    prop.getProperty("loginUrl"),
                    prop.getProperty("username"),
                    prop.getProperty("password"),
                    prop.getProperty("sessionPath")
            );

            System.out.println("Static initialization completed successfully.");
        } catch (Exception e) {
            System.err.println("Failed during static initialization: " + e.getMessage());
            throw new RuntimeException("Static initialization error", e);
        }
    }

    public static Properties getProperties() {
        return prop;
    }
}

