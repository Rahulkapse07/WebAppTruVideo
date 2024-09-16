package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

	@Test(priority = 3)
	public void verifySendToCustomer_ForFirstVideo() {
		repairOrderPage.sendVideoToCustomer("WhatsApp");
	}

	@Test(priority = 4)
	public void verifyAddMediaFunction_SecondVideo() {
		repairOrderPage.addVideoToOrder();
	}

	@Test(priority = 5)
	public void verifySendToCustomer_ForSecondVideo() {
		repairOrderPage.sendVideoToCustomer("SMS");
	}

	@Test(priority = 6)
	public void verifyViewedStatus() {
		repairOrderPage.checkStatus_OnVideoWatch();
	}

	@Test(priority = 7)
	public void verifyVariousActivityOfEstimate() {
		page.reload();
		repairOrderPage.activitiesOfCreateEstimateWindow();
	}

	@Test(priority = 8)
	public void verifySendEstimateFunction() {
		repairOrderPage.sendEstimate("SMS");
	}

	@Test(priority = 9)
	public void verifyResendEstimateFunction() {
		repairOrderPage.resendEstimate("WhatsApp");
	}

	@Test(priority = 10)
	public void verifyEstimateConfirmationFunction() {
		repairOrderPage.estimateConfirmation("WhatsApp");
	}

	@Test(priority = 11)
	public void verifyPaymentFunction() {
		page.reload();
		repairOrderPage.createPayment("WhatsApp");
	}

	@Test(priority = 12)
	public void verifyPaymentResendFunction() {
		repairOrderPage.resendPayment("SMS");
	}

	@Test(priority = 13)
	public void verifySubmitPayment_ProcessedPayment() {
		repairOrderPage.submitPayment();
	}
	@Test(priority = 14)
	public void verifyCreateReminder() throws InterruptedException {
		Assert.assertTrue(repairOrderPage.createreminder());
	}
	@Test(priority = 15)
	public void VerifyOpenInspection() throws InterruptedException{
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
	


	
	


	//Added by RK
	@Test(priority = 14)

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
	
	
	
	
	
	
	
	
	
	


	@Test(priority = 17)       //try to run this method at the end of class
	public void verifyDeleteRepairOrderFunction() throws InterruptedException {
		repairOrderPage.deleteRepairOrder();
	}


}
