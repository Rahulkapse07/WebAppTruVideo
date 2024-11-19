package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.HomePage;
import com.truvideo.pages.LoginPage;
import com.truvideo.pages.MessageScreen_Order;

public class Message_RepairOrdertest extends BaseTest {

	MessageScreen_Order MessageScreen_order;

	@BeforeClass
	public void setuplogin() throws Exception {
		MessageScreen_order = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToMessageScreen_Order();
	}

	@Test()
	public void verifyelement() {
		Assert.assertTrue(MessageScreen_order.VerifyAll_Elements());
	}

	@Test()
	public void VerifyReadUnreadnotification() throws Exception {
		Assert.assertTrue(MessageScreen_order.VerifyReadUnreadNotification());
	}

	@Test()
	public void verifyGoToRopage() {
		Assert.assertTrue(MessageScreen_order.ConversationGOtoRobtn());
	}

	@Test(dependsOnMethods = "verifyelement")
	public void verifySearchfilterbtn() {
		Assert.assertTrue(MessageScreen_order.SearchMessagefilter());
	}

	@Test()
	public void verifyDefaultFilters() throws Exception {
		Assert.assertTrue(MessageScreen_order.verify_Default_Filters());
	}

	@Test(dependsOnMethods = "verifyDefaultFilters")
	public void verifyWhatsAppChatEnablecondition() throws Exception {
		Assert.assertTrue(MessageScreen_order.VerifyWhatsAppChatEnableCondition());
	}

	@DataProvider(name = "VerifyFilters")
	public Object[][] VerifyFilters() {
		return new Object[][] { { "My", "My", "SMS", "Whatsapp", "Unread", "" },
				{ "My & Whatsapp", "My", "Whatsapp", "SMS", "Unread", "whatsapp" },
				{ "My & SMS", "My", "Whatsapp", "SMS", "Unread", "sms" },
				{ "My & Unread", "My", "whatsapp", "SMS", "Unread", "" } };
	}

	@Test(dataProvider = "VerifyFilters")
	public void verifyfilters(String filtername, String MY, String Whatsapp, String SMS, String Unread,
			String filter2) {
		switch (filtername) {
		case "My":
			Assert.assertTrue(MessageScreen_order.verifyMyFilter(MY));
			break;

		case "My & Whatsapp":
			Assert.assertTrue(
					MessageScreen_order.verify_with_My_filterBotton(filtername, MY, Whatsapp, SMS, Unread, filter2));
			break;

		case "My & SMS":
			Assert.assertTrue(
					MessageScreen_order.verify_with_My_filterBotton(filtername, MY, Whatsapp, SMS, Unread, filter2));
			break;

		case "My & unread":
			Assert.assertTrue(MessageScreen_order.click_My_AND_UNREAD_filterBotton(filtername, MY, Whatsapp, SMS,
					Unread, filter2));
			break;

		default:

			break;
		}
	}

	@Test()
	public void click_My_AND_UNREAD_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_My_AND_UNREAD_filterBotton("My & Unread", "My", "whatsapp", "SMS",
				"Unread", ""));
	}

	@Test()
	public void message_Profile_setting_button() {
		Assert.assertTrue(MessageScreen_order.Verify_Profile_setting_button("Sandeep singh"));
	}

	@Test()
	public void verifystartConversatationbtn() throws Exception {
		Assert.assertTrue(MessageScreen_order.verifyStartconversatationbtn(prop.getProperty("MobileNo"), "SMS"));
	}

	@Test()
	public void verifyMessageuser() {
		Assert.assertTrue(MessageScreen_order.Verify_message_Name());
	}

	@Test()
	public void MessageSendattachments() throws Exception {
		Assert.assertTrue(MessageScreen_order.MessageSendAttachments(prop.getProperty("MobileNo")));
	}

	@Test()
	public void verifyconversationStartfromRO() throws Exception {
		MessageScreen_order.verifyconversationStartfromRO();
	}

	@Test()
	public void verifychannelownereditRo() throws Exception {
		MessageScreen_order.verifychannelownereditRo();
	}

	@Test()
	public void verifyChannelname() throws Exception {
		MessageScreen_order.verifychannelname("SMS");

	}
}
