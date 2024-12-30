package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ChatPage;


public class ChatpageTest extends BaseTest {
	ChatPage chatpage ;
	@BeforeMethod
	public void initializeChatPage(){
		page.navigate(prop.getProperty("chatPageUrl"));
		chatpage = new ChatPage(page);
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
