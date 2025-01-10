package com.truvideo.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealerGroupPage;

public class DealerGroupPageTest extends BaseTest {
	DealerGroupPage dealergrouppage;
	
	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		dealergrouppage = new DealerGroupPage(getPage());

	}

	
	@Test(priority  = 1, description = "WA-5495")
	public void VerifyUserCreateEditDeleteDealerGroup() throws InterruptedException {
		dealergrouppage.userCreateDeleteEditDealerGroup();
	}
	@Test(priority  = 2, description = "WA-5496")
	public void VerifyAssignDealerGroupAndPerformSwitchDealerFunctionality() throws InterruptedException {
		dealergrouppage.userAssignDGAndSwitchDealerFunctionality(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Test(priority  = 3, description = "WA-5498")
	public void VerifyMultipleDealerGroupAssignToSingleAndMultipleUser() throws InterruptedException{
		dealergrouppage.multipleDealerGroupAssignToSingleAndMultipleUser(prop.getProperty("username"),prop.getProperty("password"),"Advisor","Truvideo");
	}
}
