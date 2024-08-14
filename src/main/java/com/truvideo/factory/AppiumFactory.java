package com.truvideo.factory;

import java.net.MalformedURLException;
import com.truvideo.utility.JavaUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumFactory extends JavaUtility {
	public static AppiumDriver driver;
	private static AppiumDriverLocalService service;

	public static AppiumDriver launchApp() throws MalformedURLException {
		startAppiumService();
		UiAutomator2Options options = new UiAutomator2Options().setPlatformName(prop.getProperty("OSverison"))
				.setDeviceName(prop.getProperty("DeviceName")).setPlatformVersion(prop.getProperty("platformVersion"))
				.setApp("./src/main/resources/MobileApp/RCFlutterApp.apk")
				.setAutoGrantPermissions(true).setAutomationName("UiAutomator2");
		driver = new AppiumDriver(service, options);
		System.out.println("Mobile Application launched");
		return driver;
	}

	private static void startAppiumService() {
		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingAnyFreePort();
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "warn");
		builder.withArgument(GeneralServerFlag.USE_PLUGINS, "element-wait");
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

}
