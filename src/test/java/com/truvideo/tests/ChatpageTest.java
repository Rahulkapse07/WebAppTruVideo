package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ChatPage;


public class ChatpageTest extends BaseTest {

	ChatPage chatpage = new ChatPage(page);
	
	@BeforeClass
	public void NavigateChatpage() {
		chatpage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password")).navigateToChat();
	}
	
	@Test(priority = 1)
	public void VerifyprofilePicture() {
		chatpage.VerifyProfilepicture();
		
	}
	@Test(priority = 2)
	public void VerifychannelLeavefunc() {
		chatpage.VerifyChannelleaveFunc();
	}
	
}
