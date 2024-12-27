package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.AppiumFactory;
import com.truvideo.mobilepages.DealerCodePage;
import com.truvideo.mobilepages.UserListPage;
import com.truvideo.utility.JavaUtility;

import io.appium.java_client.AppiumDriver;

public class OrderListPage extends JavaUtility {
	private Page page;
	private AppiumDriver driver;

	public OrderListPage(Page page) {
		this.page = page;
	}

	private String myROs_FilterButton = "#LBL_MY_RO";
	private String allOpen_FilterButton = "#LBL_ALL_OPEN";
	private String ForReview_FilterButton = "#LBL_FOR_REVIEW";
	private String allClosed_FilterButton = "#LBL_ALL_CLOSED";
	private String selectDealer_Dropdown = "#s2id_dealer a .select2-chosen";
	private String dealerSearch_TextBox = "div.select2-search input";

	private String getSearchedDealer(String dealerToSearch) {
		return "span.select2-match:has-text('" + dealerToSearch + "')";
	}

	private String closeRepairOrder_Button = "#repair-order-close";
	private String addRepairOrder_Button = "#repair-order-add";
	private String tableRows = "table#repair-order-results tr";
	private String bottomNavigations = "div.pagination ul";
	private String notification_TopRight = ".notifications";
	private String closeMessage_XButton = "a.close";
	private String nextButton = "li.ot3-nextPage a";
	// Add Order
	private String repairOrderNumber_Field = "(//input[@id='jobServiceNumber'])[1]";
	private String firstName_Field = "(//input[@id='customer.firstName'])[1]";
	private String lastName_Field = "(//input[@id='customer.lastName'])[1]";
	private String fleetCheckBox = "(//input[@id='isFleetCustomer'])[1]";
	private String companyName_TextBox = "(//input[@id='customer.companyName'])[1]";
	private String phoneNumber_Field = "//input[@id='phoneNumberCreate']";
	private String emailId_Field = "(//input[@id='customer.email'])[1]";
	// private String technician_Dropdown = "(//select[@id='technician'])[1]";
	private String technician_Dropdown = "//select[@id='technician']";
	private String save_Button = "#add-repair-order-save";
	private String cancel_Button = "#add-repair-order-cancel";
	// Error messages
	private String repairOrder_MandatoryField = "small:has-text('Repair Order No. is a required field.')";
	private String firstName_MandatoryField = "small:has-text('First Name is a required field.')";
	private String lastName_MandatoryField = "small:has-text('Last Name is a required field.')";
	private String companyName_RequiredField = "#companyNameRequired";
	private String firstName_RequiredField = "#firstNameRequired";
	private String lastName_RequiredField = "#lastNameRequired";
	private String repairOrders = "table#repair-order-results tr td:nth-child(4)";
	public static String newRoNumber;

	public boolean checkAllAvailableElements_ROListPage() {
		page.waitForLoadState();
		if (page.isVisible(myROs_FilterButton) && page.isVisible(allOpen_FilterButton)
				&& page.isVisible(ForReview_FilterButton) && page.isVisible(allClosed_FilterButton)
				&& page.isVisible(selectDealer_Dropdown) && page.isVisible(closeRepairOrder_Button)
				&& page.isVisible(addRepairOrder_Button) && page.isVisible(bottomNavigations)) {
			logger.info("All elements are visible on RO List page");
			return true;
		} else {
			logger.info("Some Elements are missing on RO List page");
			return false;
		}
	}

	public boolean clickOnFilter(String filterType) {
		String filterButton = null;
		final String urlFragment;

		switch (filterType.toLowerCase()) {
		case "myros":
			filterButton = myROs_FilterButton;
			urlFragment = "MY_RO";
			break;
		case "allopen":
			filterButton = allOpen_FilterButton;
			urlFragment = "ALL_OPEN";
			break;
		case "forreview":
			filterButton = ForReview_FilterButton;
			urlFragment = "FOR_REVIEW";
			break;
		case "allclosed":
			filterButton = allClosed_FilterButton;
			urlFragment = "ALL_CLOSED";
			break;
		default:
			logger.error("Invalid filter type provided: " + filterType);
			return false;
		}
		page.click(filterButton);
		logger.info("Clicked on " + filterType + " filter");

		page.waitForURL(url -> url.contains(urlFragment));
		page.waitForSelector(tableRows);
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<>();

		for (int i = 0; i < rowCount - 1; i++) {
			Locator advisor = tableRow.locator("td:nth-child(5)").nth(i);
			Locator technician = tableRow.locator("td:nth-child(6)").nth(i);
			Locator roNumbers = tableRow.locator("td:nth-child(4)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(10)").nth(i);

			String advisorName = advisor.textContent().trim();
			String technicianName = technician.textContent().trim();
			String roNumber = roNumbers.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ").trim();

			boolean matchFound = false;

			switch (filterType.toLowerCase()) {
			case "myros":
				if (advisorName.equals(LoginPage.logInUsername) || technicianName.equals(LoginPage.logInUsername)) {
					logger.info("Match found in Row " + (i + 1) + ": Advisor: " + advisorName + ", Technician: "
							+ technicianName);
					flags.add(true);
					matchFound = true;
				}
				break;

			case "allopen":
				if (!status.contains("Closed")) {
					logger.info("The RO: " + roNumber + " is open & Status is: " + status);
					flags.add(true);
				} else {
					flags.add(false);
				}
				page.waitForTimeout(1000);
				break;

			case "forreview":
				page.waitForTimeout(2000); // Wait to ensure elements are fully loaded
				if (status.contains("For Review")) {
					logger.info("The Status of RO: " + roNumber + " Contains For Review and is: " + status);
					flags.add(true);
				} else {
					logger.info("The Status of RO: " + roNumber + " does NOT contain For Review: " + status);
					flags.add(false);
				}
				break;

			case "allclosed":
				page.waitForTimeout(1000); // Adding a wait for elements to load properly
				if (status.contains("Closed")) {
					logger.info("The Status of RO: " + roNumber + " is closed & Contains: " + status);
					flags.add(true);
				} else {
					logger.info("The Status of RO: " + roNumber + " is NOT closed & Contains: " + status);
					flags.add(false);
				}
				break;
			}
			if (!matchFound && filterType.equals("myros")) {
				logger.info("No match found for My ROs filter with login user: " + LoginPage.logInUsername);
			}
		}
		if (!filterType.equals("myros")) {
			return !flags.contains(false); // Ensures no invalid rows exist
		}
		return flags.contains(true);
	}

	public boolean selectDealerFromSelectDealerDropdown() {
		page.waitForTimeout(5000);
		page.click(selectDealer_Dropdown);
		logger.info("Clicked on 'Select Dealer' dropdown");
		page.waitForTimeout(2000);
		logger.info("Selected dealer is :" + prop.getProperty("anotherDealer"));
		page.fill(dealerSearch_TextBox, prop.getProperty("anotherDealer"));
		page.waitForTimeout(2000);
		page.click(getSearchedDealer(prop.getProperty("anotherDealer")));
		page.waitForTimeout(2000);
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator DealerNames = tableRow.locator("td:nth-child(3)").nth(i);
			String dealerName = DealerNames.textContent().trim();
			if (dealerName.contains(prop.getProperty("anotherDealer"))) {
				flags.add(true);
			} else {
				logger.info("All ROs is Not of selected dealer & dealer is : " + dealerName);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean closeRepairOrder() {
		page.click(myROs_FilterButton);
		logger.info("Clicked on My RO filter");
		page.waitForURL(url -> url.contains("MY_RO"));
		page.click(closeRepairOrder_Button);
		logger.info("Clicked on 'Close Repair Order' Button without selecting any RO to closed");
		SoftAssert softAssert = new SoftAssert();
		boolean flag = false;
		if (page.innerText(notification_TopRight)
				.contains(AppConstants.ERROR_MESSAGE_CLICKONCLOSERO_WHEN_RONOTSELECTED)) {
			logger.info("Please select repair orders to be closed. message displayed ");
			flag = true;
			if (page.isVisible(notification_TopRight)) {
				page.click(closeMessage_XButton);
			}
		}
		softAssert.assertTrue(flag, "Error message when RO is not selected to close");
		page.waitForTimeout(1000);
		Locator myTableRow = page.locator(tableRows);
		String firstRO_SelectedForClose = myTableRow.locator("td:nth-child(4)").first().textContent().trim();
		Locator checkBox = myTableRow.locator("td:nth-child(1)").first();
		checkBox.click();
		logger.info("selected first RO's checkox in My RO's Filter");
		page.click(closeRepairOrder_Button);
		logger.info("Clicked on 'Close Repair Order' Button when One RO is selected to closed'");
		page.waitForTimeout(1000);
		page.click(allClosed_FilterButton); // open closed RO list to verify
		logger.info("Clicked on All Closed filter");
		page.waitForURL(url -> url.contains("ALL_CLOSED"));

		boolean roFound = false;
		while (!roFound) {
			Locator closedTableRow = page.locator(tableRows);
			int rowCount = closedTableRow.count();
			for (int i = 0; i < rowCount - 1; i++) {
				Locator roNumberList = closedTableRow.locator("td:nth-child(4)").nth(i);
				String roNumber = roNumberList.innerText().trim();
				if (roNumber.equals(firstRO_SelectedForClose)) {
					logger.info("The Closed RO " + firstRO_SelectedForClose + " found in closed list and RO Number is: "
							+ roNumber);
					roFound = true;
					break;
				}
				logger.info("Checking for: " + firstRO_SelectedForClose + " And found :" + roNumber);
			}
			if (!roFound && page.isVisible(nextButton)) {
				logger.info("next button displayed ");
				page.click(nextButton);
				logger.info("The Closed RO is not found on the current page, checking on the next page.");
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForTimeout(4000);
			} else if (!roFound) {
				logger.info("RO number not found and no more pages to check.");
				roFound = false;
				break;
			}
		}
		return roFound;
	}

	public boolean clickOnAddRepairOrder() {
		page.reload();
		page.click(myROs_FilterButton);
		page.click(addRepairOrder_Button);
		logger.info("Clicked on Add Repair Order Button");
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		if (page.isVisible(repairOrderNumber_Field) && page.isVisible(firstName_Field) && page.isVisible(lastName_Field)
				&& page.isVisible(fleetCheckBox) && page.isVisible(companyName_TextBox)
				&& page.isVisible(phoneNumber_Field) && page.isVisible(emailId_Field) && page.isVisible(save_Button)) {
			logger.info("All fields are available on add repair order screen");
			page.click(cancel_Button);
			return true;
		} else {
			logger.info("Something went wrong while validating available fields on Add Repair Order screen");
			page.click(cancel_Button);
			return false;
		}
	}

	public boolean checkfleet_CheckBox_EnableDisabled() {
		page.waitForLoadState();
		page.click(addRepairOrder_Button);
		logger.info("Clicked on Add Repair Order button");
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		page.reload();
		page.click(fleetCheckBox);
		logger.info("Feet checkbox selected");
		List<Boolean> requiredLabels = new ArrayList<Boolean>();
		page.waitForTimeout(2000);
		if (page.isVisible(companyName_RequiredField)) {
			logger.info("Company Name is Required field label displayed");
			requiredLabels.add(true);
		} else {
			logger.info("Company Name is Required field label not displayed");
			page.click(cancel_Button);
			requiredLabels.add(false);
		}
		page.waitForTimeout(2000);
		page.click(fleetCheckBox);
		logger.info("Feet checkbox deselected");
		page.waitForTimeout(2000);
		if (page.isVisible(firstName_RequiredField) && page.isVisible(lastName_RequiredField)) {
			logger.info("First Name & Last Name are required field label displayed");
			requiredLabels.add(true);
		} else {
			logger.info("First Name & Last Name are required field label not displayed");
			if (page.isVisible(cancel_Button)) {
				page.click(cancel_Button);
			}
			requiredLabels.add(false);
		}
		if (page.isVisible(cancel_Button)) {
			page.click(cancel_Button);
		}
		return !requiredLabels.contains(false);
	}

	public boolean checkAllMandatoryErrorMessage() {
		page.waitForLoadState();
		page.click(addRepairOrder_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		page.reload();
		logger.info("Clicked on Add Repair Order button");
		page.click(save_Button);
		page.waitForTimeout(2000);
		logger.info("Clicked on save button without entering any data");
		if (page.isVisible(repairOrder_MandatoryField) && page.isVisible(firstName_MandatoryField)
				&& page.isVisible(lastName_MandatoryField)) {
			logger.info("All Mandatory field warning message displayed");
			logger.info("Repair order Mandatory field message displayed as: "
					+ page.textContent(repairOrder_MandatoryField));
			logger.info(
					"First Name Mandatory field message displayed as: " + page.textContent(firstName_MandatoryField));
			logger.info("Last Name Mandatory field message displayed as: " + page.textContent(lastName_MandatoryField));
			page.click(cancel_Button);
			return true;
		} else {
			logger.info("Something went wrong during validating mandatory error messages");
			page.click(cancel_Button);
			return false;
		}
	}

	public String addRepairOrder(String Number) {
		page.click(addRepairOrder_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		logger.info("Clicked on Add Repair Order button");
		page.waitForLoadState();
		newRoNumber = "Auto" + getRandomString(5);
		page.fill(repairOrderNumber_Field, newRoNumber);
		logger.info("Repair Order Number filled : " + newRoNumber);
		String firstName = "Automation";
		page.fill(firstName_Field, firstName);
		logger.info("First Name filled : " + firstName);
		String lastName = "Last" + getRandomString(4);
		page.fill(lastName_Field, lastName);
		logger.info("Last Name filled : " + lastName);
		page.click(phoneNumber_Field);
		String phoneNumber = "781205" + getRandomNumber(4);

		// This method use for to switch between Random number and Existing number

		switch (Number) {
		case "Existing":
			page.fill(phoneNumber_Field, "7812059487");
			logger.info("Phone number filled : " + phoneNumber);
			break;
		case "New":
			page.fill(phoneNumber_Field, phoneNumber);
			logger.info("Phone number filled : " + phoneNumber);
			break;

		default:
			break;
		}
		String emailId = "Automated" + getRandomString(4) + "@gmail.com";
		page.fill(emailId_Field, emailId);
		logger.info("Email Id filled : " + emailId);
		// Check for technician dropdown available

		if (!page.locator(technician_Dropdown).isVisible()) {
			page.click(save_Button);
			logger.info("Clicked on Save Button");
			page.waitForSelector(tableRows);
			return newRoNumber;
		} else {
			page.selectOption(technician_Dropdown, prop.getProperty("MobileUserLogin").trim());
			page.waitForTimeout(2000);
			page.click(save_Button);
			logger.info("Clicked on Save Button");
			page.waitForSelector(tableRows);
			return newRoNumber;
		}
	}

	public String addRepairOrderWithoutMobileNO() {
		page.click(addRepairOrder_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		logger.info("Clicked on Add Repair Order button");
		page.waitForLoadState();
		newRoNumber = "Auto" + getRandomString(5);
		page.fill(repairOrderNumber_Field, newRoNumber);
		logger.info("Repair Order Number filled : " + newRoNumber);
		String firstName = "Automation";
		page.fill(firstName_Field, firstName);
		logger.info("First Name filled : " + firstName);
		String lastName = "Last" + getRandomString(4);
		page.fill(lastName_Field, lastName);
		logger.info("Last Name filled : " + lastName);
		page.selectOption(technician_Dropdown, prop.getProperty("MobileUserLogin").trim());
		page.waitForTimeout(2000);
		page.click(save_Button);
		logger.info("Clicked on Save Button");
		page.waitForSelector(tableRows);
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + newRoNumber + "')").first().click();
		return newRoNumber;
	}

	public void addmultipleRepairOrder(String firstname, String lastname, String emailId, String phoneNumber) {

		page.click(addRepairOrder_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_ORDER_URL));
		logger.info("Clicked on Add Repair Order button");
		page.waitForLoadState();
		newRoNumber = "Automation" + getRandomString(5);
		page.fill(repairOrderNumber_Field, newRoNumber);
		logger.info("Repair Order Number filled : " + newRoNumber);
		page.fill(firstName_Field, firstname);
		logger.info("First Name filled : " + lastname);
		page.fill(lastName_Field, lastname);
		logger.info("Last Name filled : " + lastname);
		page.click(phoneNumber_Field);
		page.fill(phoneNumber_Field, phoneNumber);
		logger.info("Phone number filled : " + phoneNumber);
		page.fill(emailId_Field, emailId);
		logger.info("Email Id filled : " + emailId);
		page.selectOption(technician_Dropdown, prop.getProperty("MobileUserLogin").trim());
		page.waitForTimeout(2000);
		page.click(save_Button);
		logger.info("Clicked on Save Button");
		page.waitForSelector(tableRows);
	}

	public String getFirstROInList() {
		page.waitForSelector(tableRows);
		String firstRO = page.locator(repairOrders).first().textContent();
		return firstRO;
	}

	public String verifyCreatedRO_OnMobile() throws Exception {
		driver = AppiumFactory.launchApp();
		DealerCodePage dp = new DealerCodePage(driver);
		dp.dealerLogin_ValidCredentials();
		UserListPage up = new UserListPage(driver);
		return up.login_verify_created_RO(prop.getProperty("MobileUserLogin"));
	}

	public RepairOrderDetailPage navigateToOrderDetails(String String) {
		newRoNumber = addRepairOrder(String);
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + newRoNumber + "')").first().click();
		// page.locator("table#repair-order-results
		// td:nth-child(4)").first().click();
		try {
			Thread.sleep(14000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.waitForURL(url -> url.contains("order/service/view"));
		return new RepairOrderDetailPage(page);
	}

// Inspection
	private String RepairOrdertab = "li.nav-item > a[href=\"/crud/repair-order\"]";
	private String SearchInspectionStatus = "#repair-order-results tbody tr td.results-row:nth-child(10) span:has-text('Insp-Review')";
	private String SearchInspectionStatus1 = "td.results-row:nth-child(10)";
	private String Inspec_Review = "td.results-row:nth-child(10) > span.label.status-insp-submitted";

// Get Ro number from table
	private String Ronumber = "td.results-row:nth-child(4)";

	public boolean checkInspectionStatus() {
		page.click(RepairOrdertab);
		logger.info("click on repair order tab");
		page.waitForTimeout(10000);
		page.click(allOpen_FilterButton);
		logger.info("check table");

		String Value = "Insp-Review";

		boolean roFound = false;
		while (!roFound) {
			Locator TableRow = page.locator(tableRows);
			int rowCount = TableRow.count();
			logger.info(rowCount);
			for (int i = 0; i < rowCount - 1; i++) {
				Locator roNumberList = TableRow.locator(SearchInspectionStatus1).nth(i);
				String roNumber = roNumberList.innerText().trim();

				if (roNumber.contains(Value)) {
					logger.info("The Closed RO " + Value + " found in closed list and RO Number is: " + roNumber);

					TableRow.locator(SearchInspectionStatus1).nth(i).click();
					page.waitForTimeout(4000);
					// page.waitForCondition(() ->page.locator(".order__column--main div
					// div.orders-detail-communications__title span").isVisible());

					roFound = true;
					break;
				}
				logger.info("Checking for: " + Value + " And found :" + roNumber);
			}

			if (!roFound && page.isVisible(nextButton)) {
				logger.info("next button displayed ");
				page.click(nextButton);
				logger.info("The Closed RO is not found on the current page, checking on the next page.");
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForTimeout(4000);
			} else if (!roFound) {
				logger.info("RO number not found and no more pages to check.");
				roFound = false;
				break;
			}
		}
		return roFound;

	}

	public boolean checkInspReturnedStatus() {
		page.click(RepairOrdertab);
		logger.info("click on repair order tab");
		page.waitForTimeout(10000);
		page.click(allOpen_FilterButton);
		logger.info("check table");

		String Value = "Insp-Returned";

		boolean roFound = false;
		while (!roFound) {
			Locator TableRow = page.locator(tableRows);
			int rowCount = TableRow.count();
			logger.info(rowCount);
			for (int i = 0; i < rowCount - 1; i++) {
				Locator roNumberList = TableRow.locator(SearchInspectionStatus1).nth(i);
				String roNumber = roNumberList.innerText().trim();

				if (roNumber.contains(Value)) {
					logger.info("The Closed RO " + Value + " found in closed list and RO Number is: " + roNumber);

					TableRow.locator(SearchInspectionStatus1).nth(i).click();
					page.waitForTimeout(4000);
					// page.waitForCondition(() ->page.locator(".order__column--main div
					// div.orders-detail-communications__title span").isVisible());

					roFound = true;
					break;
				}
				logger.info("Checking for: " + Value + " And found :" + roNumber);
			}
			if (!roFound && page.isVisible(nextButton)) {
				logger.info("next button displayed ");
				page.click(nextButton);
				logger.info("The Closed RO is not found on the current page, checking on the next page.");
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForTimeout(4000);
			} else if (!roFound) {
				logger.info("RO number not found and no more pages to check.");
				roFound = false;
				break;
			}
		}
		return roFound;
	}

	public boolean checkInspPublishStatus() {
		page.click(RepairOrdertab);
		logger.info("click on repair order tab");
		page.waitForTimeout(10000);
		page.click(allOpen_FilterButton);
		logger.info("check table");

		String Value = "Insp-Published";

		boolean roFound = false;
		while (!roFound) {
			Locator TableRow = page.locator(tableRows);
			int rowCount = TableRow.count();
			logger.info(rowCount);
			for (int i = 0; i < rowCount - 1; i++) {
				Locator roNumberList = TableRow.locator(SearchInspectionStatus1).nth(i);
				String roNumber = roNumberList.innerText().trim();

				if (roNumber.contains(Value)) {
					logger.info("The Closed RO " + Value + " found in closed list and RO Number is: " + roNumber);

					TableRow.locator(SearchInspectionStatus1).nth(i).click();
					page.waitForTimeout(4000);
					// page.waitForCondition(() ->page.locator(".order__column--main div
					// div.orders-detail-communications__title span").isVisible());

					roFound = true;
					break;
				}
				logger.info("Checking for: " + Value + " And found :" + roNumber);
			}
			if (!roFound && page.isVisible(nextButton)) {
				logger.info("next button displayed ");
				page.click(nextButton);
				logger.info("The Closed RO is not found on the current page, checking on the next page.");
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.waitForTimeout(4000);
			} else if (!roFound) {
				logger.info("RO number not found and no more pages to check.");
				roFound = false;
				break;
			}
		}
		return roFound;
	}

}
