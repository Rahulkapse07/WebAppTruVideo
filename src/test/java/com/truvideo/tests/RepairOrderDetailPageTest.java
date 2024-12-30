package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.ReminderPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.RepairOrderDetailPage;

import net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy;

public class RepairOrderDetailPageTest extends BaseTest {
	RepairOrderDetailPage repairOrderPage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		repairOrderPage = new RepairOrderDetailPage(getPage());
	}



	@Test(priority = 1 , description = "" )

	public void verifyAllAvailableElementsOnOrderDetails() {
		Assert.assertTrue(repairOrderPage.checkAllMandatoryFields_ForNewRO());
	}
	
	@Test(priority = 2, description = "WA-5354")
	public void verifyAddMediaFunction_FirstVideo() {
		repairOrderPage.addVideoToOrder();
	}

	@Test( priority = 3, description = "WA-5354" )
	public void verifySendToCustomer_ForFirstVideo() {
		repairOrderPage.sendVideoToCustomer("WhatsApp");
	}

	@Test(priority = 4, description = "WA-5354" )
	public void verifySendToCustomer_ForSecondVideo() {
		repairOrderPage.sendVideoToCustomer("SMS");
	}

	@Test(priority = 5, description = "WA-5354")
	public void verifyViewedStatus() {
		repairOrderPage.checkStatus_OnVideoWatch("SMS");
	}


	@Test(priority = 6, description =  "WA-5363 , WA-5364")
	public void verifyVariousActivityOfEstimate() {
		repairOrderPage.activitiesOfCreateEstimateWindow();
	}

	@Test(priority = 7, description = "WA-5363 , WA-5354z",dependsOnMethods = "verifyVariousActivityOfEstimate")
	public void verifySendEstimateFunction() {
		repairOrderPage.sendEstimate("SMS");
	}

	@Test(priority = 8, description = "WA-5363")
	public void verifyResendEstimateFunction() {
		repairOrderPage.resendEstimate("WhatsApp");
	}

	@Test(priority = 9, description = "WA-5363" ,dependsOnMethods = "verifySendEstimateFunction")
	public void verifyEstimateConfirmationFunction() {
		repairOrderPage.estimateConfirmation("WhatsApp");
	}

	@Test(priority = 11, description =  "WA-5375")
	public void verifyPaymentFunction() {
		getPage().reload();
		repairOrderPage.createPayment("WhatsApp");
	}

	@Test(priority = 12, description = "WA-5375" )
	public void verifyPaymentResendFunction() {
		repairOrderPage.resendPayment("SMS");
	}

	@Test(priority = 13, description = "WA-5375" )
	public void verifySubmitPayment_ProcessedPayment() {

		repairOrderPage.submitPayment("SMS");
	}
	/*
	 * @Test(priority = 14, description = "WA-5569") public void
	 * verifyCreateReminder() throws InterruptedException {
	 * Assert.assertTrue(repairOrderPage.createreminder()); }
	 * 
	 * @Test(priority = 15, description = "WA-5378") public void
	 * VerifyOpenInspection() throws InterruptedException {
	 * Assert.assertTrue(repairOrderPage.openInspection()); }
	 * 
	 * @Test(priority = 16, description = "WA-5382") public void
	 * VerifySendbackInspection() {
	 * Assert.assertTrue(repairOrderPage.sendbackInspection()); }
	 * 
	 * @Test(priority = 17, description = "WA-5379") public void
	 * VerifyPublishInspections() {
	 * Assert.assertTrue(repairOrderPage.publishInspection()); }
	 * 
	 * @Test(priority = 18, description = "WA-5381") public void
	 * VerifyNotifyCustomer() {
	 * Assert.assertTrue(repairOrderPage.notifyCustomerBtn()); }
	 * 
	 * @Test(priority = 19, description = "WA-5383") public void VerifyHide_Show() {
	 * Assert.assertTrue(repairOrderPage.hide_showBtn()); }
	 * 
	 * @Test(priority = 20, description = "WA-5380") public void
	 * VerifyPrintInspection() throws InterruptedException {
	 * Assert.assertTrue(repairOrderPage.printInspection()); }
	 */


	@Test(priority = 21, description = "WA-5393")
	public void verifyCopylinktoClipboardFunctionality() {
		repairOrderPage.copyLinktoClipboard();
	}
	
	@Test(description = "WA-5395")
	public void verifyCustomerDetails() {
		Assert.assertTrue(repairOrderPage.detailsFieldonRODetails());
	}

	@Test(priority = 22, description = "WA-5391")

	public void verifyViewWithCustomerFunctionality() {
		repairOrderPage.viewWithCustomer();
	}

	@Test(priority = 23, description = "WA-5390")
	public void verifyEditThisROFunctionality() throws InterruptedException {
		repairOrderPage.editThisRO();
	}

	@Test(priority = 24, description = "WA-5527")
	public void verifyInsightFunctionality() throws InterruptedException {
		repairOrderPage.insightFunctionality();
	}
	
	@Test(priority = 25, description = "WA-5356")
	public void verifyRejectDeletefunctionality() {
		repairOrderPage.verifyRejectdeletefunctionality();
	}

	@Test(priority = 26, description = "WA-5386")
	public void verifyNotesFunctionalityOnRoDetailPage() throws InterruptedException {
		repairOrderPage.notesFunctionalityOnRO();
	}
	@Test(priority = 27, description = "WA-5385")
	public void verifyROChatFunctionalityDetailPage() throws InterruptedException {
		repairOrderPage.repairOrderChatFunctionality();
	}
	
	@Test(priority = 28, description = "WA-5384")
	public void VerifySMSFunctionalityOnRoDetailPage() {
		repairOrderPage.smsFunctionalityOnRO();
	}
	@Test(priority = 29, description = "WA-5394") // try to run this method at the end of class
	public void verifyDeleteRepairOrderFunction() throws InterruptedException {
		repairOrderPage.deleteRepairOrder();
	}

}
