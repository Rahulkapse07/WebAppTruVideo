package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.OrderListPage;

public class OrderListPageTest extends BaseTest {
	OrderListPage orderlistpage;

	@BeforeClass
	public void orderListPageSetup() {
		orderlistpage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToOrderList();
	}

	@Test(priority = 1)
	public void verifyAllAvailableElementsOnROListPage() {
		Assert.assertTrue(orderlistpage.checkAllAvailableElements_ROListPage());
	}

	@Test(priority = 2)
	public void verify_MyROs_Filter() {
		Assert.assertTrue(orderlistpage.clickOn_MyROs_Filter());
	}

	@Test(priority = 3)
	public void verify_AllOpen_Filter() {
		Assert.assertTrue(orderlistpage.clickOn_AllOpen_Filter());
	}

	@Test(priority = 4)
	public void verify_ForReview_Filter() {
		Assert.assertTrue(orderlistpage.clickOn_ForReview_Filter());
	}

	@Test(priority = 5)
	public void verify_AllClosed_Filter() {
		Assert.assertTrue(orderlistpage.clickOn_AllClosed_Filter());
	}

	@Test(priority = 6)
	public void verify_DealerDropdown_ROListPage() {
		Assert.assertTrue(orderlistpage.selectDealerFromSelectDealerDropdown());
	}

	@Test(priority = 7)
	public void verify_CloseRepairOrderFunction_ROListPage() {
		Assert.assertTrue(orderlistpage.closeRepairOrder());
	}

	@Test(priority = 8)
	public void verify_AllFieldsOn_AddOrderScreen() {
		Assert.assertTrue(orderlistpage.clickOnAddRepairOrder());
	}

	@Test(priority = 9)
	public void verify_RequiredField_AccordingToFleetCustomer() {
		Assert.assertTrue(orderlistpage.checkfleet_CheckBox_EnableDisabled());
	}

	@Test(priority = 10)
	public void verify_AllMandatoryErrorMessage() {
		Assert.assertTrue(orderlistpage.checkAllMandatoryErrorMessage());
	}

//	@Test(priority = 11)
//	public void verifyAddRepairOrder() throws Exception {
//		String newCreatedRO = orderlistpage.addRepairOrder();
//		String firstROInList = orderlistpage.getFirstROInList();
//		Assert.assertEquals(firstROInList, newCreatedRO);
//	}
	
	
	  @Test(priority = 11)
	    public void verifyAddRepairOrder() throws Exception {
	        String newCreatedRO = orderlistpage.addRepairOrder();
	        String firstROInList = orderlistpage.getFirstROInList();
	        
//	        String testCaseId = "686"; // Replace with your actual Zephyr test case ID
//	        try {
//	            Assert.assertEquals(firstROInList, newCreatedRO);
//	            ZephyrReporter.publishTestResult(testCaseId, "Pass");
//	        } catch (AssertionError e) {
//	            ZephyrReporter.publishTestResult(testCaseId, "Fail");
//	            throw e;
//	        }
	    }
	
	
	

	@Test(priority = 12, dependsOnMethods = "verifyAddRepairOrder")
	public void verifyCreatedROIsVisibleObMobileApp() throws Exception {
		orderlistpage.verifyCreatedRO_OnMobile();
	}

}
