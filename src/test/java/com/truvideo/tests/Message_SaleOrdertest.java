package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.MessageScreen_Prospect;

public class Message_SaleOrdertest extends BaseTest {

	MessageScreen_Prospect MessageScreen_prospect;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		MessageScreen_prospect = new MessageScreen_Prospect(getPage());
	}

	@Test(priority = 1)
	public void VerifyAll_Elements() {
		Assert.assertTrue(MessageScreen_prospect.VerifyAll_Elements());
	}

	@Test(priority = 2)
	public void Verify_Profile_setting_button() {
		Assert.assertTrue(MessageScreen_prospect.Verify_Profile_setting_button("Suraj singh"));
	}

	@Test(priority = 3)
	public void Verify_message_Name() {
		Assert.assertTrue(MessageScreen_prospect.Verify_message_Name());
	}

	@Test(priority = 4)
	public void verifyMyFilter() {
		Assert.assertTrue(MessageScreen_prospect.verifyMyFilter());
	}

	@Test(priority = 5)
	public void Verify_my_filterIsApplied() {
		Assert.assertTrue(MessageScreen_prospect.Verify_my_filterIsApplied());
	}

	@Test(priority = 6)
	public void Verify_channel_ownername() {
		Assert.assertTrue(MessageScreen_prospect.Verify_channel_ownername());
	}

	@Test(priority = 7)
	public void verifystartConversatationbtn() {
		Assert.assertTrue(
				MessageScreen_prospect.verifyStartconversatationbtn(prop.getProperty("MobileNo"), "WHATSAPP"));
	}

	@Test(priority = 8)
	public void VerifyMessageSendattachments() {
		Assert.assertTrue(MessageScreen_prospect.MessageSendAttachments(prop.getProperty("MobileNo")));
	}

	@Test(priority = 9)
	public void VerifyConversationMessage_RoDetails() throws Exception {
		MessageScreen_prospect.VerifyConversationMessage_RoDetails();
	}
	@Test(priority = 10)
	
	public void VerifyBadgecountfunctionality() throws Exception {
		MessageScreen_prospect.VerifyBadgecountfunctionality();
		
	}

}
