package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvException;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.OrderListPage;
import com.truvideo.testutils.TestUtils;

public class OrderListPageTest extends BaseTest {
	OrderListPage orderlistpage;
	TestUtils ExcelUtil = new TestUtils();

	@BeforeClass
	public void orderListPageSetup() {
		orderlistpage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToOrderList();
	}

	@DataProvider(name = "filterTypes")
	public Object[][] filterTypes() {
		return new Object[][] { { "myros", true }, { "allopen", true }, { "forreview", true }, { "allclosed", true },
				{ "invalidFilter", false } };
	}

	@Test(dataProvider = "filterTypes",description = "WA-5893")
	public void testFilters(String filterType, boolean expectedResult) {
		boolean result = orderlistpage.clickOnFilter(filterType);
		Assert.assertEquals(result, expectedResult, "Filter type: " + filterType + " did not behave as expected.");
	}

	@Test(priority = 1)
	public void verifyAllAvailableElementsOnROListPage() {
		Assert.assertTrue(orderlistpage.checkAllAvailableElements_ROListPage());
	}

	@Test(priority = 6)
	public void verify_DealerDropdown_ROListPage() {
		Assert.assertTrue(orderlistpage.selectDealerFromSelectDealerDropdown());
	}

	@Test(priority = 7,description = "WA-5894")
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

	@Test(priority = 11,description = "WA-5600")
	public void verifyAddRepairOrder() throws Exception {
		String newCreatedRO = orderlistpage.addRepairOrder("New");
		String firstROInList = orderlistpage.getFirstROInList();
		Assert.assertEquals(firstROInList, newCreatedRO);
	}


	@DataProvider(name = "repairOrderData")
	public Object[][] repairOrderData() throws CsvException {
		return ExcelUtil.readCSV("src/test/resources/Testdata/readcsvdata.csv"); // Update with your actual CSV file
																					// path
	}

	@Test(dataProvider = "repairOrderData")
	public void addMultipleRepairOrder(String firstname, String lastname, String Email, String mobile) {
		System.out.println("Adding repair order for: " + firstname + " " + lastname);
		orderlistpage.addmultipleRepairOrder(firstname, lastname, Email, mobile);
	}




	@Test(priority = 12, dependsOnMethods = "verifyAddRepairOrder")
	public void verifyCreatedROIsVisibleObMobileApp() throws Exception {
		orderlistpage.verifyCreatedRO_OnMobile();
	}

	// added by yash
	@Test(priority = 18)
	public void verifyInspectionStatus() {
		Assert.assertTrue(orderlistpage.checkInspectionStatus());
	}

}
