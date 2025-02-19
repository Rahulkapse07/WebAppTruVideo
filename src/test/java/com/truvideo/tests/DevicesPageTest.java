package com.truvideo.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.DevicesPage;

public class DevicesPageTest extends BaseTest {
	
	DevicesPage devicespage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		devicespage = new DevicesPage(getPage());

	}

//	@BeforeClass
//	public void homePageSetup() throws InterruptedException {
//		devicespage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
//				.navigateToDevicesPage();
//	}
	
	@Test(priority = 1)
	public void VerificationDealersPage() {
		Assert.assertTrue(devicespage.verifyDevicesPage());
	}
	

}
