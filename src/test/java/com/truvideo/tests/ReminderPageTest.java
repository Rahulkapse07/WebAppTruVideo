package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.ProspectListPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealersPage;
import com.truvideo.pages.ReminderPage;

public class ReminderPageTest extends BaseTest {
	ReminderPage reminderspage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		reminderspage = new ReminderPage(getPage());
	}

	@Test(priority = 1)
	public void reminder() {
		Assert.assertTrue(reminderspage.getRemindersPageServiceAdvisor());

	}
	@Test(priority = 2)
	public void remindera() {
		Assert.assertTrue(reminderspage.getRemindersPageDealer());
	}
	
	@Test(priority = 3,description = "WA-5560")
	public void verifykeys() {
		Assert.assertTrue(reminderspage.reminder());
	}

	@Test(priority = 4)
	public void sentreminder() {
		Assert.assertTrue(reminderspage.sentreminder());
	}

	@Test(priority = 5)
	public void cancelreminder() {
	Assert.assertTrue(reminderspage.cancelreminder());	
	}
	@Test(priority = 6)
	public void checkremindernavigateToOrderList() throws InterruptedException {
		Assert.assertTrue(reminderspage.checkremindernavigateToOrderListPage());
	}
}
