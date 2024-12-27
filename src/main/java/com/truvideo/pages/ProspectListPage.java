package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

public class ProspectListPage extends JavaUtility {
	private Page page;

	public ProspectListPage(Page page) {
		this.page=page;
	}
	
	private String myProspects_FilterButton = "#LBL_MY_RO";
	private String allOpen_FilterButton = "#LBL_ALL_OPEN";
	private String ForReview_FilterButton = "#LBL_FOR_REVIEW";
	private String allClosed_FilterButton = "#LBL_ALL_CLOSED";
	private String closeSalesProspect_Button = "#sales-order-close";
	private String addSalesProspect_Button = "#sales-order-add";
	//Add Sales Prospect
	private String salesProspect_Label = "#sales-order-page-title-text";
	private String lastName_Field = "(//input[@id='customer.lastName'])[1]";
	private String firstName_Field = "(//input[@id='customer.firstName'])[1]";
	private String lastNameErrorMessage = "//small[text()='Last Name is a required field.']";
	private String firstNameErrorMessage = "//small[text()='First Name is a required field.']";
	private String phoneNumber_Field = "#phoneNumberCreate";
	private String invalidPhoneNumberErrorMessage = "//small[text()=' Phone number is not valid']";
	private String validatePhoneNumber_Field = "#validate-mobile-number";
	private String emailId_Field = "(//input[@id='customer.email'])[1]";
	private String stockNo_Field = "(//input[@id='vehicle.stockNo'])[1]";
	private String vehicleMake_Field = "(//input[@id='vehicle.make'])[1]";
	private String vehicleModel_Field = "(//input[@id='vehicle.model'])[1]";
	private String vehicleYear_Field = "(//input[@id='vehicle.year'])[1]";
	private String salesAgentDropdown_Field = "(//select[@id='salesAgent'])[1]";
	private String savePropsectButton = "(//input[@value='Save'])[1]";
	private String saveAndNewPropsectButton = "(//input[@value='Save and New'])[1]";
	private String cancelPropsectButton = "//button[@type='button'][@class='btn'][text()='Cancel']";
	public static String newSOName;
	private String firstSOCustomerName = "#sales-order-results tbody tr:nth-child(2) td:nth-child(6)";
	
	private String tableRows = "table#sales-order-results tr";
	
	
	public boolean checkAllAvailableElements_SOListPage() {
		page.waitForLoadState();
		if (page.isVisible(myProspects_FilterButton) && page.isVisible(allOpen_FilterButton)
				&& page.isVisible(ForReview_FilterButton) && page.isVisible(allClosed_FilterButton)
				&& page.isVisible(salesProspect_Label)) {
			logger.info("All elements are visible on Prospect List page");
			return true;
		} else {
			logger.info("Some Elements are missing on Prospect List page");
			return false;
		}
	}
	
	public boolean clickOn_MySOs_Filter() {
		page.click(myProspects_FilterButton);
		logger.info("Clicked on My SO's Filter button");
		page.waitForURL(url -> url.contains("MY_RO"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		boolean flag = false;
		for (int i = 0; i < rowCount - 1; i++) {
			Locator salesAgent = tableRow.locator("td:nth-child(4)").nth(i);
			String salesAgentName = salesAgent.textContent().trim();
			if (salesAgentName.equals(LoginPage.logInUsername)) {
				logger.info("Match found in Row " + (i + 1) + ": SalesAgent: " + salesAgentName );
				flag = true;
			}
		}
		if (flag == false) {
			logger.info("Something went wrong during SO loding on My SO's filter with login user as : "
					+ LoginPage.logInUsername);
		}
		return flag;
	}
	
	public boolean clickOn_AllOpen_Filter() {
		page.click(allOpen_FilterButton);
		logger.info("Clicked on All Open filter");
		page.waitForURL(url -> url.contains("ALL_OPEN"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String soNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (!status.contains("Closed")) {
				logger.info("The SO :" + soNAMES + " is open & Statuses is: " + status);
				flags.add(true);
			} else {
				logger.info("The SO :" + soNAMES + " is open & Statuses is: " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	public boolean clickOn_ForReview_Filter() {
		page.click(ForReview_FilterButton);
		logger.info("Clicked on For Review filter");
		page.waitForURL(url -> url.contains("FOR_REVIEW"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String soNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("For Review")) {
				logger.info("The Status of SO :" + soNAMES + " Contains For Review and  : " + status);
				flags.add(true);
			} else {
				logger.info("The Status of SO :" + soNAMES + " Not Contains For Review  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	public boolean clickOn_AllClosed_Filter() {
		page.click(allClosed_FilterButton);
		logger.info("Clicked on All Closed filter");
		page.waitForURL(url -> url.contains("ALL_CLOSED"));
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		List<Boolean> flags = new ArrayList<Boolean>();
		for (int i = 0; i < rowCount - 1; i++) {
			Locator soNames = tableRow.locator("td:nth-child(6)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String roNAMES = soNames.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("Closed")) {
				logger.info(
						"The Status of SO :" + roNAMES + " under All Closed filter is closed & Contains: " + status);
				flags.add(true);
			} else {
				logger.info("The Status of SO :" + roNAMES + " under All Closed filter is Not closed & Contains: "
						+ status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}
	
	public boolean clickOnAddSalesProspect() {
		page.reload();
		page.click(myProspects_FilterButton);
		page.click(addSalesProspect_Button);
		logger.info("Clicked on Add Prospect Button");
		page.waitForURL(url -> url.contains(AppConstants.ADD_PROSPECT_URL));
		if (page.isVisible(firstName_Field) && page.isVisible(lastName_Field) && page.isVisible(emailId_Field)
				&& page.isVisible(phoneNumber_Field) && page.isVisible(stockNo_Field)
				&& page.isVisible(vehicleMake_Field) && page.isVisible(vehicleModel_Field) && page.isVisible(vehicleYear_Field) && page.isVisible(savePropsectButton)) {
			logger.info("All fields are available on add repair order screen");
			page.click(cancelPropsectButton);
			return true;
		} else {
			logger.info("Something went wrong while validating available fields on Add Repair Order screen");
			page.click(cancelPropsectButton);
			return false;
		}
	}
	
	public String addNewSalesProspect()
	{
		page.click(addSalesProspect_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_PROSPECT_URL));
		logger.info("Clicked on AddSalesProspect button");
		page.waitForLoadState();
		page.click(lastName_Field);
		String lastName="LastName"+getRandomString(3);
		page.fill(lastName_Field, lastName);
		logger.info("Filled Last name of Customer");
		String firstName="FirstName"+getRandomString(3);
		page.click(firstName_Field);
		page.fill(firstName_Field, firstName);
		logger.info("Filled First name of Customer");
		newSOName=lastName+", "+firstName;
		page.click(phoneNumber_Field);
		String phoneNumber = "781205" + getRandomNumber(4);
		page.fill(phoneNumber_Field, phoneNumber);
		logger.info("Phone number filled : " + phoneNumber);
		page.click(emailId_Field);
		String emailId = getRandomString(8) + "@gmail.com";
		page.fill(emailId_Field, emailId);
		logger.info("Email Id filled : " + emailId);
		page.click(stockNo_Field);
		page.fill(stockNo_Field, "cshpp44344");
		logger.info("Stock number field is filled");
		page.click(vehicleMake_Field);
		page.fill(vehicleMake_Field, "Test11");
		logger.info("Filled Vehicle Make  of Customer");
		page.click(vehicleModel_Field);
		page.fill(vehicleModel_Field, "Test22");
		logger.info("Filled Model of Customer vehicle");
		page.click(vehicleYear_Field);
		page.fill(vehicleYear_Field, "2026");
		logger.info("Filled Year value");
		page.waitForTimeout(2000);
		page.selectOption(salesAgentDropdown_Field, prop.getProperty("MobileUserLogin").trim());
		page.waitForTimeout(2000);
		logger.info("Selected sales Agent");
		page.click(savePropsectButton);
		logger.info("Clicked on Save Button");
		return newSOName;
	}
	public String addNewSalesProspectWithoutMobileNo()
	{
		page.click(addSalesProspect_Button);
		page.waitForURL(url -> url.contains(AppConstants.ADD_PROSPECT_URL));
		logger.info("Clicked on AddSalesProspect button");
		page.waitForLoadState();
		page.click(lastName_Field);
		String lastName="LastName"+getRandomString(3);
		page.fill(lastName_Field, lastName);
		logger.info("Filled Last name of Customer");
		String firstName="FirstName"+getRandomString(3);
		page.click(firstName_Field);
		page.fill(firstName_Field, firstName);
		logger.info("Filled First name of Customer");
		newSOName=lastName+", "+firstName;
		page.click(stockNo_Field);
		page.fill(stockNo_Field, "cshpp44344");
		logger.info("Stock number field is filled");
		page.click(vehicleMake_Field);
		page.fill(vehicleMake_Field, "Test11");
		logger.info("Filled Vehicle Make  of Customer");
		page.click(vehicleModel_Field);
		page.fill(vehicleModel_Field, "Test22");
		logger.info("Filled Model of Customer vehicle");
		page.click(vehicleYear_Field);
		page.fill(vehicleYear_Field, "2026");
		logger.info("Filled Year value");
		page.waitForTimeout(1000);
		page.selectOption(salesAgentDropdown_Field, prop.getProperty("MobileUserLogin").trim());
		page.waitForTimeout(2000);
		logger.info("Selected sales Agent");
		page.click(savePropsectButton);
		logger.info("Clicked on Save Button");
		page.locator("#sales-order-results tbody tr:nth-child(2) td:nth-child(6)").first().click();
		logger.info("Open first sales order");
		return newSOName;
	}
	public String getFirstSOInList() {
		page.waitForSelector(tableRows);
		String firstSO = page.locator(firstSOCustomerName).first().textContent();
		return firstSO;
	}
	public ProspectDetailPage navigateToProspectDetails() {
		    newSOName = addNewSalesProspect();
			Locator tableRow = page.locator(tableRows);
			tableRow.locator("td:has-text('" + newSOName + "')").first().click();
			page.waitForURL(url-> url.contains("/order/sales/view/"));
			return new ProspectDetailPage(page);
	}
	public ProspectDetailPage navigateToProspectDetails1() {
	    newSOName = addNewSalesProspectWithoutMobileNo();
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + newSOName + "')").first().click();
		page.waitForURL(url-> url.contains("/order/sales/view/"));
		return new ProspectDetailPage(page);
}

	
}
