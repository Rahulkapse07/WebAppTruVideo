package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.RepairOrderDetailPage;

public class RepairOrderDetailPageTest extends BaseTest {
	RepairOrderDetailPage repairOrderPage;

	@BeforeClass
	public void repairOrderDetailPageSetup() {
		repairOrderPage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToOrderList().navigateToOrderDetails();
	}

	@Test(priority = 1)
	public void verifyAllAvailableElementsOnOrderDetails() {
		Assert.assertTrue(repairOrderPage.checkAllMandatoryFields_ForNewRO());
	}

	@Test(priority = 2)
	public void verifyAddMediaFunction_FirstVideo() {
		repairOrderPage.addVideoToOrder();
	}

	@Test(dependsOnMethods = "verifyAddMediaFunction_FirstVideo")
	public void verifySendToCustomer_ForFirstVideo() {
		repairOrderPage.sendVideoToCustomer("WhatsApp");
	}

	@Test(dependsOnMethods = "verifySendToCustomer_ForFirstVideo")
	public void verifySendToCustomer_ForSecondVideo() {
		repairOrderPage.sendVideoToCustomer("SMS");
	}

	@Test(dependsOnMethods = "verifySendToCustomer_ForSecondVideo")
	public void verifyViewedStatus() {
		repairOrderPage.checkStatus_OnVideoWatch("SMS");
	}

	@Test(priority = 7)
	public void verifyVariousActivityOfEstimate() {
		repairOrderPage.activitiesOfCreateEstimateWindow();
	}

	@Test(priority = 8, dependsOnMethods = "verifyVariousActivityOfEstimate")
	public void verifySendEstimateFunction() {
		repairOrderPage.sendEstimate("SMS");
	}

	@Test(priority = 9, dependsOnMethods = "verifySendEstimateFunction")
	public void verifyResendEstimateFunction() {
		repairOrderPage.resendEstimate("WhatsApp");
	}

	@Test(priority = 10, dependsOnMethods = "verifySendEstimateFunction")
	public void verifyEstimateConfirmationFunction() {
		repairOrderPage.estimateConfirmation("WhatsApp");
	}

	@Test(priority = 11)
	public void verifyPaymentFunction() {
		page.reload();
		repairOrderPage.createPayment("WhatsApp");
	}

	@Test(priority = 12, dependsOnMethods = "verifyPaymentFunction")
	public void verifyPaymentResendFunction() {
		repairOrderPage.resendPayment("SMS");
	}

	@Test(priority = 13, dependsOnMethods = "verifyPaymentResendFunction")
	public void verifySubmitPayment_ProcessedPayment() {

		repairOrderPage.submitPayment("SMS");
	}

	@Test(priority = 14)
	public void verifyCreateReminder() throws InterruptedException {
		Assert.assertTrue(repairOrderPage.createreminder());
	}

	@Test(priority = 15)
	public void VerifyOpenInspection() throws InterruptedException {
		Assert.assertTrue(repairOrderPage.openInspection());
	}

	@Test(priority = 16)
	public void VerifySendbackInspection() {
		Assert.assertTrue(repairOrderPage.sendbackInspection());
	}

	@Test(priority = 17)
	public void VerifyPublishInspections() {
		Assert.assertTrue(repairOrderPage.publishInspection());
	}

	@Test(priority = 18)
	public void VerifyNotifyCustomer() {
		Assert.assertTrue(repairOrderPage.notifyCustomerBtn());
	}

	@Test(priority = 19)
	public void VerifyHide_Show() {
		Assert.assertTrue(repairOrderPage.hide_showBtn());
	}

//	
	// Added by RK
	@Test(priority = 20)

	public void verifyCopylinktoClipboardFunctionality() {
		repairOrderPage.copyLinktoClipboard();
	}

	@Test(priority = 21)
	public void verifyViewWithCustomerFunctionality() {
		repairOrderPage.viewWithCustomer();
	}

	@Test(priority = 22)
	public void verifyEditThisROFunctionality() throws InterruptedException {
		repairOrderPage.editThisRO();
	}

	@Test(priority = 23)
	public void verifyInsightFunctionality() throws InterruptedException {
		repairOrderPage.insightFunctionality();
	}

	@Test(priority = 24) // try to run this method at the end of class
	public void verifyDeleteRepairOrderFunction() throws InterruptedException {
		repairOrderPage.deleteRepairOrder();
	}

}
