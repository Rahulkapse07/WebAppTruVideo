package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectDetailPage;

public class ProspectDetailPageTest extends BaseTest {
	ProspectDetailPage prospectdetailpage;
	@BeforeClass
	public void salesOrderDetailPageSetup()
	{
		prospectdetailpage=loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password")).navigateToProspectList().navigateToProspectDetails();
	}

	@Test(priority = 1,description = "")
	public void test1()
	{
		prospectdetailpage.addVideoToOrder();
	}
	
	//@Test(priority = 3)           //WhatsApp feature is not wokring in Sales side
	public void verifySendToCustomer_ForFirstVideo() {
		prospectdetailpage.sendVideoToCustomer("WhatsApp");
	}

	@Test(priority = 4,description = "")
	public void verifyAddMediaFunction_SecondVideo() {
		prospectdetailpage.addVideoToOrder();
	}

	@Test(priority = 5,description = "")
	public void verifySendToCustomer_ForSecondVideo() {
		prospectdetailpage.sendVideoToCustomer("SMS");
	}
	@Test(priority = 6,description = "")
	public void verifyViewedStatus() {
		prospectdetailpage.checkStatus_OnVideoWatch();
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
