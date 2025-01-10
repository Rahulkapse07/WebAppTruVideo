package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectDetailPage;

public class ProspectDetailPageTest extends BaseTest {
	ProspectDetailPage prospectdetailpage;


	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToSOdetailPage_And_InitializeSOdetailPage() {
		getPage().navigate(prop.getProperty("sodetailUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		prospectdetailpage = new ProspectDetailPage(getPage());
	}

	
	@Test(description = "")
	public void verifyAddMediaFunction_Video() {
		prospectdetailpage.addVideoToSOOrder();
	}
	@Test(description = "WA-5406")
	public void verifySendToCustomer() {
		prospectdetailpage.sendVideoToCustomer("SMS");
	}
	@Test(description = "")
	public void verifyViewedStatus() {
		prospectdetailpage.checkStatus_OnVideoWatch("SMS");
	}
	@Test( description = "WA-5408")
	public void copyLinktoClipboard() {
		 boolean isCustomerNameMatching = prospectdetailpage.copyLinkToClipboard();
		    Assert.assertTrue(isCustomerNameMatching, "SO customer name does NOT match the end link customer name.");
		   // Assert.assertTrue(prospectdetailpage.copyLinkToClipboard());
	}
	@Test(description = "WA-5407")
	public void verifyViewWithCustomerFunctionality() {
		boolean result = prospectdetailpage.viewWithCustomer();
	    Assert.assertTrue(result, "The 'viewWithCustomer' process failed.");
	}
	@Test(description = "WA-5527")
	public void verifyInsightFunctionality() {
		prospectdetailpage.insightFunctionality();
	}
	
	@Test(description = "5410")
	public void verifyTradeInLinkFunctionality() {
		prospectdetailpage.verifyTradeInLink("SMS");
	}
	@Test( description = "5405")
	public void verifyEditthisSOFunctionality() {
		prospectdetailpage.editThisSO();
	}
	@Test(description = "WA-5409") // try to run this method at the end of class
	public void verifyDeleteSalesOrderFunction() throws InterruptedException {
		Assert.assertTrue(prospectdetailpage.deleteSalesOrder());
	}
	@Test( description = "WA-5413")
	public void verifyNotesFunctionalityOnSoDetailPage() throws InterruptedException {
		prospectdetailpage.notesFunctionalityOnSO();
	}
	@Test( description = "WA-5412")
	public void VerifySMSFunctionalityOnSoDetailPage() throws InterruptedException {
		prospectdetailpage.smsFunctionalityOnSO();
	}



}
