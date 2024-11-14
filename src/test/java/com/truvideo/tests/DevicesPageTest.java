package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DevicesPage;

public class DevicesPageTest extends BaseTest {
	
	DevicesPage devicespage;
	@BeforeClass
	public void homePageSetup() throws InterruptedException {
		devicespage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToDevicesPage();	
	}
	
	@Test(priority = 1)
	public void VerificationDealersPage() {
		Assert.assertTrue(devicespage.verifyDevicesPage());
	}

}
