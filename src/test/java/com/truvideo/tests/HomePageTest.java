package com.truvideo.tests;

import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.LoadState;
import com.truvideo.testutils.AdditionalDescriptions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.HomePage;

public class HomePageTest extends BaseTest {
	HomePage homepage;
	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToHomePage_And_InitializeHomePage() {
		getPage().navigate(prop.getProperty("homePageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		homepage = new HomePage(getPage());

	}

	@Test(priority = 1, description = "")
	public void verify_RepairOrder_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_RepairOrder_Header();
		Assert.assertEquals(actualTitle, AppConstants.REPAIR_ORDERS_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 2, description = "")
	public void verify_OrdersMessage_HeaderTab_Working() throws Exception {
		Assert.assertTrue(homepage.clickOn_Order_MessagesHeader());
		getPage().goBack();
	}

	@Test(priority = 3, description = "")
	public void verify_Prospect_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Prospect_Header();
		Assert.assertEquals(actualTitle, AppConstants.PROSPECT_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 4, description = "")
	public void verify_ProspectsMessage_HeaderTab_Working() {
		Assert.assertTrue(homepage.clickOn_Prospect_MessagesHeader());
		getPage().goBack();
	}

	@Test(priority = 5, description = "")
	public void verify_Reminder_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Reminder_Header();
		Assert.assertEquals(actualTitle, AppConstants.REMINDER_PAGE_URL);
		getPage().goBack();
	}

	@Test(priority = 6, description = "")
	public void verify_Training_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_Training_Header();
		Assert.assertEquals(actualTitle, AppConstants.TRAINING_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 7, description = "")
	public void verify_User_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_User_Header();
		Assert.assertEquals(actualTitle, AppConstants.USER_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 8, description = "")
	public void verify_Contact_HeaderTab_Working() {
		String actualTitle = homepage.clickOn_ContactList_Header();
		Assert.assertEquals(actualTitle, AppConstants.CONTACT_LIST_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 9, description = "")
	@AdditionalDescriptions({"",""})
	public void verifyDealerTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnDealersHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEALERS_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 10, description = "")
	public void verifyDealerGroupTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnDealerGroupHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEALER_GROUP_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 11, description = "")
	public void verifyUserGroupTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnUserGroupHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.USER_GROUP_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 12, description = "")
	public void verifySavedVideoLibraryTab_UnderOrgainizationHeader_Working() {
		String actualTitle = homepage.clickOnSAvedVideoLibraryHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.SAVED_VIDEO_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 13, description = "")
	public void verifyDevicesTab_UnderSystemHeader_Working() {
		String actualTitle = homepage.clickOnDevicesHeaderTab();
		Assert.assertEquals(actualTitle, AppConstants.DEVICES_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 14, description = "")
	public void verify_Open_Close_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.openAndCloseAdvanceSearchWindow());
	}

	@Test(priority = 15, description = "WA-5563")

	public void verify_AllAvailableCheckBox_Filters_OnAdvanceSearchWindow() {
		homepage.checkVariousCheckBoxFilters();
	}

	@Test(priority = 16)
	public void verify_ThisWeek_RadioFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkThisWeeksRepairOrders());
	}

	@Test(priority = 17, description = "WA-5509")

	public void verify_ThisMonth_RadioFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkThisMonthRepairOrders());
	}

	@Test(priority = 18, description = "WA-5580,5581")
	public void verify_DateRangeFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.checkRepairOrdersWithinDateRange(), "Searched Data is NOT Within Date range");

	}

	@Test(priority = 19, description = "")
	public void verify_SearchFilter_OnAdvanceSearchWindow() {
		Assert.assertTrue(homepage.listAsPerTheTextSearch());
		// Assert.assertTrue(homepage.listAsPerTheTextSearch("text")); //Added by RK }
	}

	@Test(priority = 20, description = "")
	public void verify_Notification_BellIconWorking() {
		Assert.assertTrue(homepage.checkBellIcon());
	}

//	  @Test(priority = 21) public void verify_DealerCodeButtonIsWorking() {
//	  Assert.assertTrue(homepage.checkDealerCodeButton()); 
//	  }

	@Test(priority = 22, description = "")
	public void verify_Chat_ButtonIsWorking() {
		String actualTitle = homepage.clickOnChatButton();
		Assert.assertEquals(actualTitle, AppConstants.CHAT_PAGE_TITLE);
		getPage().goBack();
	}

	@Test(priority = 23, description = "")
	public void verify_BackAway_ButtonIsWorking() {
		Assert.assertTrue(homepage.clickOn_Back_Away_Button());
	}

	@Test(priority = 24, description = "")
	public void verify_UserAccountDropdown_And_Options() {
		Assert.assertTrue(homepage.openUserAccountDropdown());
	}

	@Test(priority = 25, description = "")
	public void verify_AccountSetting_TextButtonIsClickable() {
		String actualTitle = homepage.clickOnAccountSettingTextButton();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_SETTING_PAGE_TITLE);
		getPage().goBack();
		getPage().reload();
	}

	@Test(priority = 26, description = "")
	public void verify_SwitchDealerFunction() {
		Assert.assertTrue(homepage.switchDealer());
	}

	@Test(priority = 27, description = "")
	public void verifyHelpPageOpenedInAnotherTab() {
	 homepage.clickOnHelpPage_TextButton();
		// Assert.assertEquals(actualTitle, AppConstants.HELP_PAGE_TITLE);
	}

	@Test(priority = 28, description = "")
	public void verify_Self_ForReview_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_ForReview_RO_Badge());
	}

	@Test(priority = 29, description = "")
	public void verify_All_ForReview_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_ForReview_RO_Badge());
	}

	@Test(priority = 30, description = "")
	public void verify_Self_ForReview_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_ForReview_SO_Badge());
	}

	@Test(priority = 31, description = "")
	public void verify_All_ForReview_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_ForReview_SO_Badge());
	}

	@Test(priority = 32, description = "")
	public void verify_Self_Messages_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Messages_RO_Badge());
	}

	@Test(priority = 33, description = "")
	public void verify_All_Messages_RO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Messages_RO_Badge());
	}

	@Test(priority = 34, description = "")
	public void verify_Self_Messages_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Messages_SO_Badge());
	}

	@Test(priority = 35, description = "")
	public void verify_All_Messages_SO_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Messages_SO_Badge());
	}

	@Test(priority = 36, description = "")
	public void verify_Self_Reminder_Badge() {
		Assert.assertTrue(homepage.clickOn_Own_Reminder_Badge());
	}

	@Test(priority = 37, description = "")
	public void verify_All_Reminder_Badge() {
		Assert.assertTrue(homepage.clickOn_All_Reminder_Badge());
	}

	@Test(priority = 38, description = "")
	public void Verify_dealer_Name() {
		homepage.Verify_dealer_Name(prop.getProperty("dealerused"));
	}

}
