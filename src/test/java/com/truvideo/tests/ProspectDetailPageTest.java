package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.Multimediapage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectDetailPage;

public class ProspectDetailPageTest extends BaseTest {
	ProspectDetailPage prospectdetailpage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		prospectdetailpage = new ProspectDetailPage(getPage());
	}

	@Test(priority = 1,description = "")
	public void test1()
	{
		prospectdetailpage.addVideoToOrder();
	}
	
	@Test(priority = 2,description = "")
	public void verifyAddMediaFunction_SecondVideo() {
		prospectdetailpage.addVideoToOrder();
	}


	@Test(priority = 3,description = "WA-5406")
	public void verifySendToCustomer_ForSecondVideo() {
		prospectdetailpage.sendVideoToCustomer("SMS");
	}
	@Test(priority = 4,description = "")
	public void verifyViewedStatus() {
		prospectdetailpage.checkStatus_OnVideoWatch("SMS");
	}
	@Test(priority = 5, description = "WA-5408" ,dependsOnMethods = "test1")
	public void copyLinktoClipboard() {
		 boolean isCustomerNameMatching = prospectdetailpage.copyLinkToClipboard();
		    Assert.assertTrue(isCustomerNameMatching, "SO customer name does NOT match the end link customer name.");
		   // Assert.assertTrue(prospectdetailpage.copyLinkToClipboard());
	}
	@Test(priority = 6, description = "WA-5407")
	public void verifyViewWithCustomerFunctionality() {
		boolean result = prospectdetailpage.viewWithCustomer();
	    Assert.assertTrue(result, "The 'viewWithCustomer' process failed.");
	}
	@Test(priority = 7,description = "WA-5527")
	public void verifyInsightFunctionality() {
		prospectdetailpage.insightFunctionality();
	}
	
	@Test(priority = 8, description = "5410")
	public void verifyTradeInLinkFunctionality() {
		prospectdetailpage.verifyTradeInLink("SMS");
	}
	@Test(priority = 9, description = "5405")
	public void verifyEditthisSOFunctionality() {
		prospectdetailpage.editThisSO();
	}
	@Test(priority = 10,description = "WA-5409") // try to run this method at the end of class
	public void verifyDeleteSalesOrderFunction() throws InterruptedException {
		Assert.assertTrue(prospectdetailpage.deleteSalesOrder());
	}
	@Test(priority = 7, description = "WA-5413")
	public void verifyNotesFunctionalityOnSoDetailPage() throws InterruptedException {
		prospectdetailpage.notesFunctionalityOnSO();
	}
	@Test(priority = 8, description = "WA-5412")
	public void VerifySMSFunctionalityOnSoDetailPage() throws InterruptedException {
		prospectdetailpage.smsFunctionalityOnSO();
	}



}
