package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealersPage;

public class DealersPageTest extends BaseTest {
	DealersPage dealerspage;
	@BeforeClass
	public void homePageSetup() throws InterruptedException {
		dealerspage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToDealersPage();
		
	}
	
	@Test(priority  = 1, description = "WA-5519")
	public void VerifyReminderEnaDisablefunctionality() {
		Assert.assertTrue(dealerspage.Verify_Reminder_EnaDisable_functionality("Kenility Store ","Repair Service Messages"));
	}
	@Test(priority = 2)
	public void VerifyAddNewStandardResponsefunctionality() {
		Assert.assertTrue(dealerspage.verifyAddNewStandardResponse("Kenility Store ","Standard responses to be used for texting"));
	}

}
