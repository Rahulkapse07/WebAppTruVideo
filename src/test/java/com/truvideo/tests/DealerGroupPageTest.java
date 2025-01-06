package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.DealerGroupPage;

public class DealerGroupPageTest extends BaseTest {
	DealerGroupPage dealergrouppage;
	
	@BeforeMethod
	public void homePageSetup() throws InterruptedException{
	dealergrouppage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
                       .navigateToDealerGroupPage();
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
