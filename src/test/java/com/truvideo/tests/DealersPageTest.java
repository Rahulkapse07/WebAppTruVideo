package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.ChatPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealersPage;

public class DealersPageTest extends BaseTest {
	DealersPage dealerspage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToDealerPage_And_InitializeDealerPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		dealerspage = new DealersPage(getPage());
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
