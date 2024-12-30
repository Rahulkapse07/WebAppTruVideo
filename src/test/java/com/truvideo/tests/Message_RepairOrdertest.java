package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.HomePage;
import com.truvideo.pages.LoginPage;
import com.truvideo.pages.MessageScreen_Order;

public class Message_RepairOrdertest extends BaseTest {

	MessageScreen_Order MessageScreen_order;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		MessageScreen_order = new MessageScreen_Order(getPage());
	}

	@Test(description = "MT-2137")
	public void verifyelement() {
		Assert.assertTrue(MessageScreen_order.VerifyAll_Elements());
	}

	@Test(description = "WA-5539")
	public void VerifyReadUnreadnotification() throws Exception {
		Assert.assertTrue(MessageScreen_order.VerifyReadUnreadNotification());
	}

	@Test(description = "WA-2434")
	public void verifyGoToRopage() {
		Assert.assertTrue(MessageScreen_order.ConversationGOtoRobtn());
	}

	@Test(dependsOnMethods = "verifyelement",description = "MT-2139")
	public void verifySearchfilterbtn() {
		Assert.assertTrue(MessageScreen_order.SearchMessagefilter());
	}

	@Test(description = "MT-2292")
	public void verifyDefaultFilters() throws Exception {
		Assert.assertTrue(MessageScreen_order.verify_Default_Filters());
	}

	@Test(dependsOnMethods = "verifyDefaultFilters",description = "MT-2136")
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

	@Test(dataProvider = "VerifyFilters",description = "MT-2136")
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

	@Test(description = "WA-5539")
	public void click_My_AND_UNREAD_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_My_AND_UNREAD_filterBotton("My & Unread", "My", "whatsapp", "SMS",
				"Unread", ""));
	}

	@Test(description = "MT-2147 , MT-2383")
	public void message_Profile_setting_button() {
		Assert.assertTrue(MessageScreen_order.Verify_Profile_setting_button("Sandeep singh"));
	}

	@Test(description = "MT-2294 , MT-2197 , MT-2157")
	public void verifystartConversatationbtn() throws Exception {
		Assert.assertTrue(MessageScreen_order.verifyStartconversatationbtn(prop.getProperty("MobileNo"), "SMS"));
	}

	@Test()
	public void Verify_Conversation_Channel() throws Exception {
		Assert.assertTrue(MessageScreen_order.Verify_Conversation_Channel());
	}

	@Test(description = "MT-2159 ,MT-2287")
	public void MessageSendattachments() throws Exception {
		Assert.assertTrue(MessageScreen_order.MessageSendAttachments(prop.getProperty("MobileNo")));
	}

	@Test(description = "MT-2205")
	public void verifyconversationStartfromRO() throws Exception {
		MessageScreen_order.verifyconversationStartfromRO();
	}

	@Test(description = "MT-2189")
	public void verifychannelownereditRo() throws Exception {
		MessageScreen_order.verifychannelownereditRo();
	}

	@Test(description = "MT-2211")
	public void verifyChannelname() throws Exception {
		MessageScreen_order.verifychannelname("SMS");

	}
	@Test(description = "WA-6163")
	public void verify_videolink_functionality() {
		MessageScreen_order.verify_videolink_functionality("SMS");
	}
	@Test
	public void Verify_welcome_message() {
		MessageScreen_order.Verify_welcome_message();
	}
}
