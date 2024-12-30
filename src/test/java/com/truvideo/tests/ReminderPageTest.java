package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealersPage;
import com.truvideo.pages.ReminderPage;

public class ReminderPageTest extends BaseTest {
	ReminderPage reminderspage;
//	@BeforeClass
//	public void homePageSetup() throws InterruptedException {
//		reminderspage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
//				.navigateToReminder();
//	}

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
