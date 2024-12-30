package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ChatPage;


public class ChatpageTest extends BaseTest {

	ChatPage chatpage ;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		chatpage = new ChatPage(getPage());
	}
	
	@Test(priority = 1)
	public void VerifyprofilePicture() {
		chatpage.VerifyProfilepicture();
		
	}
	@Test(priority = 2)
	public void VerifychannelLeavefunc() {
		chatpage.VerifyChannelleaveFunc();
	}
	
	@Test(priority = 3, description = "MT-2382")
	public void VerifyconversationFilterOnChat() {
		chatpage.verifySelectConversationFilter();
	}
	
}
