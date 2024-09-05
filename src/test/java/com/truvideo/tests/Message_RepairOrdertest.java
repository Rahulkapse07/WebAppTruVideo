package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.MessageScreen_Order;

public class Message_RepairOrdertest extends BaseTest {

	MessageScreen_Order MessageScreen_order;

	@BeforeClass
	public void setuplogin() throws Exception {

		MessageScreen_order = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToMessageScreen_Order();
		}

	@Test(priority = 1)
	public void verifyallelement() {
		Assert.assertTrue(MessageScreen_order.VerifyAll_Elements());
	}
//   @Test(priority = 2)
//	public void VerifyReadUnreadnotification() {
//		Assert.assertTrue(MessageScreen_order.VerifyReadUnreadNotification());
//	}
    @Test(priority = 3)
	public void verifyDefaultFilters() throws Exception {
		Assert.assertTrue(MessageScreen_order.verify_Default_Filters());
	}
    @Test(priority = 4)
    public void verifyGoToRopage() {
    	Assert.assertTrue(MessageScreen_order.ConversationGOtoRobtn());
    }
    @Test(priority = 5)
    public void VerifySearchfilterbtn() {
    	Assert.assertTrue(MessageScreen_order.SearchMessagefilter());
    }

    @Test(priority= 6)
    public void VerifyWhatsAppChatEnablecondition() {
    	Assert.assertTrue(MessageScreen_order.VerifyWhatsAppChatEnableCondition());
    }
    @Test(priority = 8)
	public void click_whatsapp_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_Whatsapp_filterBotton());
	}

	@Test(priority = 9)
	public void click_sms_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_Sms_filterBotton());
	}

	@Test(priority = 10)
	public void click_unread_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_Unread_filterBotton());
	}

	@Test(priority = 11)
	public void click_My_and_Sms_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_My_AND_Sms_filterBotton());
	}

	@Test(priority = 12)
	public void click_My_and_unread_filterBotton() {
		Assert.assertTrue(MessageScreen_order.click_My_AND_UNREAD_filterBotton());
	}
    @Test(priority = 13)
	public void message_Profile_setting_button() {
		Assert.assertTrue(MessageScreen_order.Message_Profile_setting_button("Suraj singh"));
	}
    @Test(priority = 14)
	public void verifystartConversatationbtn() {
		Assert.assertTrue(MessageScreen_order.verifyStartconversatationbtn(prop.getProperty("MobileNo"),"WHATSAPP"));
	}
	@Test(priority = 15)
	public void verifyMessageuser() {
		Assert.assertTrue(MessageScreen_order.Verify_message_Name());
	}
    @Test(priority = 16)
	public void verifyMyFilter() {
		Assert.assertTrue(MessageScreen_order.verifyMyFilter());
	}
    @Test(priority = 17)
	public void Verifyfilterbuttons() {

		Assert.assertTrue(MessageScreen_order.verifyfilterbuttons());
	}
    @Test(priority = 19)
    public void MessageSendattachments() {
    	Assert.assertTrue(MessageScreen_order.MessageSendAttachments(prop.getProperty("MobileNo")));
    }
    
    @Test(priority = 18)
    public void VerifyConversationMessage_roDetails() throws Exception {
    	MessageScreen_order.VerifyConversationMessage_RoDetails();
    }
}

