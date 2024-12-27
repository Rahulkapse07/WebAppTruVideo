package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.HoverOptions;
import com.microsoft.playwright.Page;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

public class HomePage extends JavaUtility {
	private Page page;

	public HomePage(Page page) {
		this.page = page;
	}

	private String repairOrder_Header = "a[href='/crud/repair-order']";
	private String users_Header = "a[href='/organization/tce-users/']";
	private String orderMessage_Header = "a#service-messages-link"; // path changed by suraj
	private String prospect_Header = "a[href='/crud/sales']";
	private String prospectMessage_Header = "#sales-messages-link";
	private String reminder_Header = "a[href='/reminder?filterBy=MY_REMINDERS']";
	private String trainings_Header = "a[href='/trainings']";
	private String organization_Header = "a[href='#']:has-text('Organization')";
	private String system_Header = "a[href='#']:has-text('System')";
	private String contactList_Header = "a[href='/contact-list']:has-text('Contact List')";
	private String other_Header = "a[href='#']:has-text('Other')";
	private String dealersTab = "a[href='/dealer/']";
	private String dealerGroupsTab = "a[href='/group/']";
	private String userGroupsTab = "a[href='/organization/usergroups/']";
	private String savedVideoLibraryTab = "a[href='/crud/saved-video']";
	private String devicesTab = "a[href='/device/']";
	private String dealername ="a.dropdown-toggle span span:nth-child(1)";
	

	// search
	private String search_TextBox = "#search-header";
	private String search_TextBox_UnderWindow = "#search-keyword";
	private String close_SearchWindow = "#close-advanced-search";
	private String search_Button = "#search-button";
	private String all_checkBox = "#all_type_search";
	private String repairOrder_checkBox = "#repair_order_type_search";
	private String prospect_checkBox = "#prospect_type_search";
	private String repairOrder_Message_checkBox = "#ro_message_type_search";
	private String prospect_Message_checkBox = "#so_message_type_search";
	private String reminder_checkBox = "#reminder_type_search";
	//private String noResultFoundText="h4:has-text('No Results Found')";
	private String noResultFoundText="#bd h4:has-text('No Results Found.')";
	private String getRadio(String radioType) {
		String radioElement = "label[class='radio']:has-text('" + radioType + "')";
		return radioElement;
	}

	private String repairOrderDates = "#repair-order-results tbody tr td:nth-of-type(2)";
	private String dateRange_CheckBox = "//input[@value='date_range']";
	private String selectDateFrom_1st_Calendar = "div[class='datepicker-days'] tbody tr:nth-of-type(1) td:nth-of-type(2)";
	//private String selectDateFrom_2nd_Calendar = "div[class='datepicker-days'] tbody tr:nth-of-type(6) td:nth-of-type(6)";
	private String selectDateFrom_2nd_Calendar="div[class='datepicker-days'] tbody tr:nth-of-type(2) td:nth-of-type(7)";
	private String fromDate_TextBox = "#date-range-from";
	private String toDate_TextBox = "#date-range-to";

	private String getRoNumber(String count) {
		String getRoNumber = "#repair-order-results tbody tr:nth-of-type(" + count + ") td:nth-of-type(4)";
		return getRoNumber;
	}

	// Headings
	private String heading_RepairOrder = "h4:has-text('Repair Orders')";
	private String heading_Prospect = "h4:has-text('Prospects')";
	private String heading_ROMessage = "h4:has-text('Repair Order Messages')";
	private String heading_ProspectMessage = "h4:has-text('Prospect Messages')";
	private String heading_Reminder = "h4:has-text('Reminders')";
	// bell icon
	private String bellIcon = "li.notify-count:has(#icon-count)";
	private String recentNotificationHeading = "p[class='header']:has-text('Recent Notifications')";
	// DealerCode, chat, Away
	private String dealerCode_Button = "div a:has-text('Dealer Code')";
	private String serviceCode = "#dealer-menu-list li:has-text('Service Code:')";
	private String salesCode = "#dealer-menu-list li:has-text('Sales Code:')";
	private String chatv2_Header = "input#chat-button";
	private String backAway_Button = "#away-button";
	private String awayBackMessage_AlertMessage = "div.notifications-button div";
	private String closeMessageButton = "div.notifications-button a.close";
	// Badges
	private String forReviewBadge_SelfRO = "#my-service span.km-tab";
	private String forReviewBadge_OtherRO = "[title=\"338 of RO's that belong to other service advisors require a review\"]";
	private String forReviewBadge_SelfSO = "#my-sales span.km-tab";
	private String forReviewBadge_OtherSO = "#all-sales span.km-tab";
	private String inboundMessageBadge_SelfRO = " span#my-service-message";
	private String inboundMessageBadge_SelfSO = "#my-sales-message span.km-tab";
	private String inboundMessageBadge_AllRO = "#all-service-message span.km-tab";
	private String inboundMessageBadge_AllSO = "#all-sales-message span.km-tab";
	private String countOfTotalOrders = "#message-panel div span.records";
	private String tableRows = "table#repair-order-results tbody tr";
	private String tableRows_SO = "table#sales-order-results tbody tr";
	private String message_MainFrame = "#messages-iframe";
	private String reminderBadge_SelfRO = "#my-reminders .km-tab";
	private String reminderBadge_AllRO = "#all-reminders .km-tab";

	private String filterButton(String buttonText) {
		return "button:has-text('" + buttonText + "')";
	}

	// User Account Dropdown
	
	private String logInUserLabel = "li.account-nav a span span:nth-child(3)";

	public String getLoginUserLabel() {
		return logInUserLabel;
	}

	private String logInDealerLabel = "li.account-nav a span span:nth-child(1)";

	public String getLoginDealerLabel() {
		return logInDealerLabel;
	}

	private String accountDropdownButton = "li.account-nav";
	private String accountSetting_TextButton = "ul#user-menu-list li a[href='/user-account/'] ";
	private String helpPage_TextButton = "ul#user-menu-list li a[href='https://truvideo.com/help-page/'] ";
	private String dealerId_Label = "a:has-text('Dealer ID')";
	private String supportNumber_Label = "#user-menu-list a:has-text('Support')";
	private String allRightReserved_Label = "#user-menu-list a:has-text('© TruVideo  |  All Rights Reserved.')";
	private String dealerSearch_TextBox = "input#dealer-search-form";
	private String logOut_Button = "#user-menu-list li a[class='logout-a']";
	private String other = "//a[contains(text(), 'Other ')]";
	
	

	private String getSearchedDealer(String dealerName) {
		return "ul#dealerList li a:has-text('" + dealerName + "')";
	}

	public String clickOn_RepairOrder_Header() {
		navigateToOrderList();
		page.click(repairOrder_Header);
		logger.info("Clicked on Repair Order Header Tab");
		return page.title();
	}

	public Multimediapage NavigateToOrderList() {
		page.click(repairOrder_Header);
		return new Multimediapage(page);
	
	}
	
	public OrderListPage navigateToOrderList() {
		page.click(repairOrder_Header);
		return new OrderListPage(page);
	
	}
	

	public boolean clickOn_Order_MessagesHeader() throws Exception {
		navigateToMessageScreen_Order();
		logger.info("Clicked on Orders Message Screen Header Tab");
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE)
				&& page.url().equals(AppConstants.ORDER_MESSAGES_PAGE_URL)) {
			logger.info("Title matched & Title is : " + page.title());
			logger.info("URL matched & opened url is : " + page.url());
			return true;
		} else {
			logger.info("Title not matched & Title is : " + page.title());
			logger.info("URL not matched & opened url is : " + page.url());
			return false;
		}
	}

	public MessageScreen_Order navigateToMessageScreen_Order() throws Exception{
		page.waitForTimeout(5000);
		if(page.locator(orderMessage_Header).isVisible()) {
			page.click(orderMessage_Header);
			return new MessageScreen_Order(page);
			}
		else {
			throw new Exception("Message header Disable");
		}
		
	}

	public String clickOn_Prospect_Header() {
		navigateToProspectList();
		logger.info("Clicked on Prospect Header Tab");
		return page.title();
	}

	public ProspectListPage navigateToProspectList() {
		page.waitForTimeout(5000);
		page.click(prospect_Header);
		return new ProspectListPage(page);
	}

	public boolean clickOn_Prospect_MessagesHeader() {
		navigateToMessageScreen_Prospect();
		logger.info("Clicked on Prospects Message Header Tab");
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE)
				&& page.url().equals(AppConstants.PROSPECT_MESSAGES_PAGE_URL)) {
			logger.info("Title matched & Title is : " + page.title());
			logger.info("URL matched & opened url is : " + page.url());
			return true;
		} else {
			logger.info("Title not matched & Title is : " + page.title());
			logger.info("URL not matched & opened url is : " + page.url());
			return false;
		}
	}

	public MessageScreen_Prospect navigateToMessageScreen_Prospect() {
		page.waitForTimeout(4000);
		page.click(prospectMessage_Header);
		return new MessageScreen_Prospect(page);
	}

	public String clickOn_Reminder_Header() {
		navigateToReminder();
		logger.info("Clicked on Reminder Header Tab");
		return page.url();
	}
	
	public ReminderPage navigateToReminder() {
		page.waitForTimeout(4000);
		if (!page.isVisible(reminder_Header)) {
			page.click(other);
		}
		page.click(reminder_Header);
		return new ReminderPage(page);
	}
	
	/*
	 * public String clickOn_Organization() { navigateToSavedVideoLibrary();
	 * logger.info("Clicked on Organization"); return page.title(); }'
	 */
	public SavedVideoLibraryPage navigateToSavedVideoLibrary() {
		page.waitForTimeout(4000);
		page.click(other_Header);
		page.locator(organization_Header).hover();
		logger.info("test1");
		page.locator("a[href='/crud/saved-video']:has-text('Saved Video Library')").click();
		return new SavedVideoLibraryPage(page);
	}

	public String clickOn_Training_Header() {
		navigateToTraining();
		logger.info("Clicked on Training Header Tab");
		return page.title();
	}

	public TrainingPage navigateToTraining() {
		if (!page.isVisible(trainings_Header)) {
			page.click(other_Header);
		}
		page.click(trainings_Header);
		return new TrainingPage(page);
	}

	public String clickOn_User_Header() {
		navigateToUserspage();
		logger.info("Clicked on User Header Tab");
		return page.title();
	}

	public UserPage navigateToUserspage() {
		if (!page.isVisible(users_Header)) {
			page.click(other_Header);
		}
		page.click(users_Header);
		return new UserPage(page);
	}

	public String clickOn_ContactList_Header() {
		navigateToContactList();
		logger.info("Clicked on Contacts List Header Tab");
		return page.title();
	}

	public ContactList navigateToContactList() {
		if (!page.isVisible(contactList_Header)) {
			page.click(other_Header);
		}
		page.click(contactList_Header);
		return new ContactList(page);
	}

	public String clickOnDealersHeaderTab() {
		navigateToDealersPage();
		logger.info("Dealers Page is opened : " + page.title());
		return page.title();
	}

	public DealersPage navigateToDealersPage() {
		page.waitForTimeout(3000);
		if (!page.isVisible(organization_Header)) {
			page.click(other_Header);
			logger.info("Clicked on Other tab");
		}
		page.hover(organization_Header);
		logger.info("Mouse hover on Organization tab");
		page.click(dealersTab);
		logger.info("Clicked on dealers tab");
		return new DealersPage(page);
	}

	public String clickOnDealerGroupHeaderTab() {
		navigateToDealerGroupPage();
		logger.info("Dealer Group Page is opened : " + page.title());
		return page.title();
	}

	public DealerGroupPage navigateToDealerGroupPage() {
		if (!page.isVisible(organization_Header)) {
			page.click(other_Header);
			logger.info("Clicked on Other tab");
		}
		page.hover(organization_Header);
		logger.info("Mouse hover on Organization tab");
		page.click(dealerGroupsTab);
		logger.info("Clicked on Dealer Group tab");
		return new DealerGroupPage(page);
	}

	public String clickOnUserGroupHeaderTab() {
		navigateToUserGroupPage();
		logger.info("User Group Page is opened : " + page.title());
		return page.title();
	}

	public UserGroupPage navigateToUserGroupPage() {
		if (!page.isVisible(organization_Header)) {
			page.click(other_Header);
			logger.info("Clicked on Other tab");
		}
		page.hover(organization_Header);
		logger.info("Mouse hover on Organization tab");
		page.click(userGroupsTab);
		logger.info("Clicked on User Group tab");
		return new UserGroupPage(page);
	}

	public String clickOnSAvedVideoLibraryHeaderTab() {
		navigateToSavedVideoLibraryPage();
		logger.info("Saved Video Library Page is opened : " + page.title());
		return page.title();
	}

	public SavedVideoLibraryPage navigateToSavedVideoLibraryPage() {
		if (!page.isVisible(organization_Header)) {
			page.click(other_Header);
			logger.info("Clicked on Other tab");
		}
		page.hover(organization_Header);
		logger.info("Mouse hover on Organization tab");
		page.click(savedVideoLibraryTab);
		logger.info("Clicked on Saved Video Library tab");
		return new SavedVideoLibraryPage(page);
	}
	
	private String acctDropDown = ".account-nav.dropdown";
	private String logoutBtn = "ul#user-menu-list  li a.logout-a";
	public void LogOutfunctionality() {
		if(page.isVisible(acctDropDown)) {
			page.click(acctDropDown);
			logger.info("Click on Drop Down");
		}
		page.click(logoutBtn);
		logger.info("Click on LogOut button");	
	}

	public String clickOnDevicesHeaderTab() {
		page.waitForTimeout(5000);
		navigateToDevicesPage();
		logger.info("Devices Page is opened : " + page.title());
		return page.title();
	}

	public DevicesPage navigateToDevicesPage() {
		if (!page.isVisible(system_Header)) {
			page.waitForTimeout(8000);
			page.click(other_Header);
			logger.info("Clicked on Other tab");
		}
		page.waitForTimeout(3000);
		//page.click(other_Header);
		page.locator(system_Header).hover(new HoverOptions().setTimeout(60000));
		//page.locator(system_Header).hover();
		logger.info("Clicked on System tab");
		page.waitForTimeout(2000);
		page.locator(devicesTab).click();
		logger.info("Clicked on Devices tab");
		return new DevicesPage(page);
	}

	public boolean openAndCloseAdvanceSearchWindow() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		if (page.isVisible(search_Button)) {
			logger.info("Search Button displayed");
			page.click(close_SearchWindow);
			logger.info("Clicked on Close advance search window button");
			return true;
		} else {
			logger.info("Something went wrong on performing open and closed operation on Advance Search video");
			return false;
		}
	}

	public void checkVariousCheckBoxFilters() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		page.click(search_Button);
		logger.info("Clicked on search button when by default All filter are selected");
		page.waitForTimeout(2000);
		boolean flag = false;
		SoftAssert softAssert = new SoftAssert();
		if (page.isVisible(heading_RepairOrder) && page.isVisible(heading_Prospect) && page.isVisible(heading_ROMessage)
				&& page.isVisible(heading_ProspectMessage) && page.isVisible(heading_Reminder)) {
			logger.info(
					"Repair Orders Heading , Prospect Heading, Both RO/SO Messages heading, Reminder heading is displayed");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'All' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "All filter"); // Check first default filtered condition :- All filters are selected
		page.click(search_TextBox);
		page.click(all_checkBox); // remove All checkBox
		page.click(repairOrder_checkBox); // Apply Repair order checkBox
		logger.info("'All' filter removed & applied single 'Repair Order' checkbox filter");
		page.click(search_Button);
		page.waitForLoadState();
		if (page.title().equals(AppConstants.REPAIR_ORDERS_PAGE_TITLE)) {
			logger.info("User is navigated to the Repair Order List and Only repair order list is displaying");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'Repair Order' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "Single Repair Order Filter"); // Checking when only Repair Order filter is applied
		page.click(search_TextBox);
		page.click(repairOrder_checkBox); // remove repair order checkBox
		page.click(prospect_checkBox); // Apply prospect checkBox
		logger.info("'Repair Order' filter removed & applied single 'Prospect' checkbox filter");
		page.click(search_Button);
		page.waitForLoadState();
		if (page.title().equals(AppConstants.PROSPECT_PAGE_TITLE)) {
			logger.info("User is navigated to the Prospect List and Only prospect list is displaying");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'Prospect' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "Single Prospect Filter"); // Checking when only Prospect filter is applied
		page.click(search_TextBox);
		page.click(prospect_checkBox); // remove prospect checkBox
		page.click(repairOrder_Message_checkBox); // Apply Repair Order Messages checkBox
		logger.info("'Prospect' filter removed & applied single 'Repair Order Messages' checkbox filter");
		page.click(search_Button);
		page.waitForLoadState();
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE) && page.url().contains("SERVICE")) {
			logger.info("User is navigated to the Repair Order Messages screen");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'Repair Order Messages' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "Single Repair Order Message Filter"); // Checking when only Repair Order Messages
																			// filter is applied
		page.click(search_TextBox);
		page.click(repairOrder_Message_checkBox); // remove Repair Order Messages checkBox
		page.click(prospect_Message_checkBox); // Apply Prospect Messages checkBox
		logger.info("'Repair Order Message' filter removed & applied single 'Prospect Messages' checkbox filter");
		page.click(search_Button);
		page.waitForLoadState();
		if (page.title().equals(AppConstants.MESSAGES_PAGE_TITLE) && page.url().contains("SALES")) {
			logger.info("User is navigated to the Prospects Messages screen");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'Prospect Messages' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "Single Prospect Message Filter"); // Checking when only Prospect Messages filter is
																		// applied
		page.click(search_TextBox);
		page.click(prospect_Message_checkBox); // remove Prospect Messages checkBox
		page.click(reminder_checkBox); // Apply Reminder checkBox
		logger.info("'Prospect Message' filter removed & applied single 'Reminder' checkbox filter");
		page.click(search_Button);
		page.waitForLoadState();
		if (page.url().contains("reminder")) {
			logger.info("User is navigated to the Reminder screen");
			flag = true;
		} else {
			logger.info("Something went wrong , when applied 'Reminder' filter");
			flag = false;
		}
		softAssert.assertTrue(flag, "Single Reminder Filter"); // Checking when only Prospect Messages filter is applied
		softAssert.assertAll();
	}

	public boolean checkThisWeeksRepairOrders() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		if (page.isChecked(all_checkBox)) {
			page.click(all_checkBox);// Removed All checkBox
		}
		if (page.isChecked(reminder_checkBox)) {
			page.click(reminder_checkBox);
		}
		page.waitForTimeout(500);
		page.click(repairOrder_checkBox);// Selecting repair order checkBox
		page.click(getRadio("This week")); // Selecting This Week Radio
		logger.info("Clicked on 'This week' radio filter");
		page.click(search_Button);
		page.waitForLoadState();
		logger.info("Clicked on search button when by This week filter is selected");
		List<Boolean> flags = new ArrayList<>();
		List<String> datesOfAllROInList = page.locator(repairOrderDates).allInnerTexts();
		for (String date : datesOfAllROInList) {
			boolean flag = false;
			flag = isDateInCurrentWeek(date);
			if (flag == true) {
				logger.info("Repair order in list is within this week & date is  : " + date);
				flags.add(true);
			} else {
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean checkThisMonthRepairOrders() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		if (page.isChecked(all_checkBox)) {
			page.click(all_checkBox);// Removed All checkBox
		}
		page.waitForTimeout(500);
		if (!page.isChecked(repairOrder_checkBox)) {
			page.click(repairOrder_checkBox);// Selecting repair order checkBox
		}
		page.click(getRadio("This month")); // Selecting This Month Radio
		logger.info("Clicked on 'This Month' radio filter");
		page.click(search_Button);
		page.waitForLoadState();
		logger.info("Clicked on search button when by This month filter is selected");
		List<Boolean> flags = new ArrayList<>();
		List<String> datesOfAllROInList = page.locator(repairOrderDates).allInnerTexts();
		for (String date : datesOfAllROInList) {
			boolean flag = false;
			flag = isDateInCurrentMonth(date);
			if (flag == true) {
				logger.info("Repair order in list is within this month & date is  : " + date);
				flags.add(true);
			} else {
				logger.info("Repair order in list is not within this month & date is  : " + date);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean checkRepairOrdersWithinDateRange() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		if (page.isChecked(all_checkBox)) {
			page.click(all_checkBox);// Removed All checkBox
		}
		page.waitForTimeout(500);
		if (!page.isChecked(repairOrder_checkBox)) {
			page.click(repairOrder_checkBox);// Selecting repair order checkBox
		}
		page.click(dateRange_CheckBox); // Selecting date range with the help of calendar
		page.click(fromDate_TextBox);
		
		page.locator(selectDateFrom_1st_Calendar).first().click();
		String selected_StartDate = page.inputValue(fromDate_TextBox) + " 12:00 AM";
		logger.info("Entered from date in the calendar & Entered date is : " + selected_StartDate);
		page.click(toDate_TextBox);
		page.locator(selectDateFrom_2nd_Calendar).last().click();
		String selected_EndDate = page.inputValue(toDate_TextBox) + " 11:59 PM";
		logger.info("Entered upto date in the calendar & Entered date is : " + selected_EndDate);
		page.locator("#search-button").click();
		page.waitForTimeout(2000);
		List<String> datesofRepairsOrdersInList = page.locator("#repair-order-results tbody tr td:nth-of-type(2)")
				.allInnerTexts();
		List<Boolean> flags = new ArrayList<>();
		for (String date : datesofRepairsOrdersInList) {
			if (isDateInRange(date, selected_StartDate, selected_EndDate)) {
				flags.add(true);
				logger.info("Date " + date + " is within the range.");
			} else {
				flags.add(false);
				logger.info("Date " + date + " is NOT within the range.");
			}
		}
		return !flags.contains(false);
	}

	public boolean globalSearchwitheText(String text) {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
//		if (page.isChecked(all_checkBox)) {
//			page.click(all_checkBox);// Removed All checkBox
//		}
		page.waitForTimeout(500);
		if (!page.isChecked(repairOrder_checkBox)) {
			page.click(repairOrder_checkBox);// Selecting repair order checkBox
		}
		if (!page.isChecked(getRadio("This month"))) {
			page.click(getRadio("This month"));// Selecting this month radio filter
		}
		String enteredTextForSearch = text;
		page.fill(search_TextBox_UnderWindow, enteredTextForSearch); // searching for the 'test' keyword
		logger.info("Text entered in search box");
		page.click(search_Button);
		logger.info("Clicked on search button when text is entered in the text box");
		page.waitForTimeout(5000);
		
		if(page.locator(noResultFoundText).isVisible()) {
		logger.info("Searched data has been not found in TruVideo");
		return true;
		}else {
			logger.info("Something went wrong to find selected value ");
			return false ;
		}
			
		}
	
	public boolean listAsPerTheTextSearch() {
		page.click(search_TextBox);
		logger.info("Clicked on search text box");
		if (page.isChecked(all_checkBox)) {
			page.click(all_checkBox);// Removed All checkBox
		}
		page.waitForTimeout(500);
		if (!page.isChecked(repairOrder_checkBox)) {
			page.click(repairOrder_checkBox);// Selecting repair order checkBox
		}
		if (!page.isChecked(getRadio("This month"))) {
			page.click(getRadio("This month"));// Selecting this month radio filter
		}
		String enteredTextForSearch = "test";
		page.fill(search_TextBox_UnderWindow, enteredTextForSearch); // searching for the 'test' keyword
		logger.info("Text entered in search box");
		page.click(search_Button);
		logger.info("Clicked on search button when text is entered in the text box");
		page.waitForTimeout(2000);
		
		List<String> allTextUnderRO = page.locator(tableRows).allInnerTexts();
		List<Boolean> flags = new ArrayList<>();
		int intCount = 1;
		for (String textUnderRO : allTextUnderRO) {
			String count = String.valueOf(intCount);
			intCount++;
			if (textUnderRO.toLowerCase().contains(enteredTextForSearch)) {
				logger.info(page.textContent(getRoNumber(count)) + " contains searched text which is "
						+ enteredTextForSearch);
				flags.add(true);
			} else {
				logger.info(page.textContent(getRoNumber(count)) + " not contains searched text which is "
						+ enteredTextForSearch);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean checkBellIcon() {
		page.click(bellIcon);
		logger.info("Clicked on notification bell icon");
		page.waitForTimeout(1000);
		if (page.isVisible(recentNotificationHeading)) {
			logger.info("Recent Notification heading displayed");
			page.waitForTimeout(4000);
			page.click(bellIcon);
			logger.info("Again clicked on notification bell icon");
			return true;
		} else {
			page.reload();
			logger.info("Something went wrong while opening and closing notification bell icon window");
			return false;
		}
	}

	public boolean checkDealerCodeButton() {
		page.click(dealerCode_Button);
		logger.info("Clicked on Dealer Code button");
		if (page.isVisible(serviceCode) && page.isVisible(salesCode)) {
			serviceCode = page.textContent(serviceCode).substring(page.textContent(serviceCode).length() - 6);
			logger.info("Service code is displayed as :" + serviceCode);
			salesCode = page.textContent(salesCode).substring(page.textContent(salesCode).length() - 6);
			logger.info("Sales code is displayed as :" + salesCode);
			page.waitForTimeout(2000);
			page.click(dealerCode_Button);
			return true;
		} else {
			page.reload();
			return false;
		}
	}

	public String clickOnChatButton() {
		navigateToChat();
		logger.info("Clicked on Chat button");
		return page.title();
	}

	public ChatPage navigateToChat() {
		page.click(chatv2_Header);
		page.waitForTimeout(10000);
		return new ChatPage(page);
	}

	public boolean clickOn_Back_Away_Button() {
		page.reload();
		page.click(backAway_Button);
		logger.info("Click on Away button");
		List<Boolean> flags = new ArrayList<>();
		page.waitForTimeout(2000);
		if (page.textContent(backAway_Button).equals("Back")) {
			logger.info("Button change to  " + page.textContent(backAway_Button));
			logger.info("Away Message displayed as : " + page.textContent(awayBackMessage_AlertMessage));
			page.waitForTimeout(2000);
			page.click(closeMessageButton);
			page.waitForTimeout(2000);
			flags.add(true);
		} else {
			logger.info("Something went wrong during Back Away operation");
			flags.add(false);
		}
		page.click(backAway_Button);
		page.waitForTimeout(2000);
		logger.info("Click on Back button");
		if (page.textContent(backAway_Button).equals("Away")) {
			logger.info("Button change to  " + page.textContent(backAway_Button));
			logger.info("Away Message displayed as : " + page.textContent(awayBackMessage_AlertMessage));
			page.waitForTimeout(2000);
			page.click(closeMessageButton);
			page.waitForTimeout(2000);
			flags.add(true);
		} else {
			logger.info("Something went wrong during Back Away operation");
			flags.add(false);
		}
		return !flags.contains(false);
	}

	public boolean openUserAccountDropdown() {
		page.waitForLoadState();
		page.click(accountDropdownButton);
		logger.info("Clicked on user account dropdown button");
		page.isVisible(accountSetting_TextButton);
		page.isVisible(helpPage_TextButton);
		page.isVisible(dealerId_Label);
		page.isVisible(supportNumber_Label);
		page.isVisible(allRightReserved_Label);
		page.isVisible(dealerSearch_TextBox);
		page.waitForTimeout(2000);
		if (page.isVisible(accountSetting_TextButton) && page.isVisible(helpPage_TextButton)
				&& page.isVisible(dealerId_Label) && page.isVisible(allRightReserved_Label) 
				&& page.isVisible(dealerSearch_TextBox))
			{
			logger.info("User Dropdown is opened and all elements are available");
			logger.info("Account setting & Help Page text button is available");
			int colonIndex1 = page.textContent(dealerId_Label).indexOf(':');
			logger.info("Dealer ID is : " + page.textContent(dealerId_Label).substring(colonIndex1 + 1).trim());
			//int colonIndex2 = page.textContent(supportNumber_Label).indexOf(':');
//			logger.info(
//					"Support Number is : " + page.textContent(supportNumber_Label).substring(colonIndex2 + 1).trim());
			logger.info("AllRights Reserved Label and Dealer Search Text box is displayed");
			page.click(accountDropdownButton);
			logger.info("Closed user account dropdown ");
			return true;
		} else {
			logger.info("Something went wrong during opening User Account dropdown");
			return false;
		}
	}

	public String clickOnAccountSettingTextButton() {
		navigateToSelfAccountSetting();
		logger.info("Click on Account Setting text button");
		return page.title();
	}

	public AccountSettingPage navigateToSelfAccountSetting() {
		page.click(accountDropdownButton);
		logger.info("Clicked on user account dropdown button");
		page.click(accountSetting_TextButton);
		return new AccountSettingPage(page);
	}

	public boolean switchDealer() {
		page.click(accountDropdownButton);
		logger.info("Clicked on user account dropdown button");
		page.waitForTimeout(3000);
		page.click(dealerSearch_TextBox);
		page.keyboard().type(prop.getProperty("anotherDealer"));
		// page.fill(dealerSearch_TextBox, prop.getProperty("dealertoswitch"));
		page.waitForTimeout(3000);
		page.locator(getSearchedDealer(prop.getProperty("anotherDealer"))).first().click();
		logger.info("Clicked on another dealer to switch");
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.locator(logInDealerLabel).textContent().contains(prop.getProperty("anotherDealer"))) {
			logger.info("User is switched to another dealer and switched dealer is : "
					+ page.locator(logInDealerLabel).textContent());
			page.waitForLoadState();
			page.waitForTimeout(8000);
			page.click(accountDropdownButton);
			logger.info("Clicked on user account dropdown button again : To switch Back");
			page.waitForTimeout(8000);
			page.click(dealerSearch_TextBox);
			System.out.println("again");
			page.keyboard().type(prop.getProperty("dealerused"));
			// page.fill(dealerSearch_TextBox, prop.getProperty("dealerused"));
			page.waitForTimeout(4000);
			page.locator(getSearchedDealer(prop.getProperty("dealerused"))).first().click();
			logger.info("Clicked on first dealer to switch : To switch Back");
			flags.add(true);
			page.waitForTimeout(15000);
			if (page.locator(logInDealerLabel).textContent().contains(prop.getProperty("dealerused"))) {
				logger.info("User is switched back to first dealer and switched dealer is : "
						+ page.locator(logInDealerLabel).textContent());
				flags.add(true);
			} else {
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public String clickOnHelpPage_TextButton() {
		page.click(accountDropdownButton);
		logger.info("Clicked on user account dropdown button");
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			page.click(helpPage_TextButton);
			logger.info("New Page opened in another tab");
		});
		newPage.waitForLoadState();
		logger.info("New Page Loaded : " + newPage.title());
		String newPageTitle = newPage.title();
		newPage.close();
		logger.info("Newly Opened Page Closed");
		return newPageTitle;
	}

	public boolean clickOn_Own_ForReview_RO_Badge() {
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(forReviewBadge_SelfRO)) {
			logger.info("Badge for video For Review for self RO's is not available");
			throw new SkipException("Badge for Review not available, skipping test.");
		}
		logger.info("Badge for video For Review for self RO's is available");
		String countOnBadge = page.textContent(forReviewBadge_SelfRO);
		page.click(forReviewBadge_SelfRO);
		page.waitForURL(url -> url.contains("MY_FOR_REVIEW"));
		logger.info("Clicked on Badge for video For Review for self RO's");
		String countLabel = page.textContent(countOfTotalOrders).trim();
		if (countLabel.contains(countOnBadge)) {
			logger.info("The count " + countOnBadge + " on badge is equal to total count " + countLabel);
			flags.add(true);
		} else {
			logger.info("The count " + countOnBadge + " on badge is not equal to total count " + countLabel);
			flags.add(false);
		}
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		for (int i = 0; i < rowCount; i++) {
			Locator advisors = tableRow.locator("td:nth-child(5)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(10)").nth(i);
			String advisor = advisors.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("For Review") && advisor.contains(LoginPage.logInUsername)) {
				logger.info("The RO is of :" + advisor + " & Contains status  : " + status);
				flags.add(true);
			} else {
				logger.info("The RO is of :" + advisor + " & Contains status  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean clickOn_All_ForReview_RO_Badge() {
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(forReviewBadge_OtherRO)) {
			logger.info("Badge for video For Review for All RO's is not available");
			throw new SkipException("Badge for All For Review not available, skipping test.");
		}
		logger.info("Badge for video For Review for all RO's is available");
		String countOnBadge = page.textContent(forReviewBadge_OtherRO);
		//page.click(forReviewBadge_OtherRO);
		page.navigate("https://rc.truvideo.com/crud/repair-order?filterDate=&dateTo=12%2F17%2F2024&dateFrom=&keyword=&filterBy=ALL_FOR_REVIEW&dealer=Select+Dealer&orderOption=&orderFlow=&p=1");
		page.waitForURL(url -> url.contains("ALL_FOR_REVIEW"));
		logger.info("Clicked on Badge for video For Review for all RO's");
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		for (int i = 0; i < rowCount; i++) {
			Locator statuses = tableRow.locator("td:nth-child(10)").nth(i);
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("For Review")) {
				logger.info("The RO in the list Contains status  : " + status);
				flags.add(true);
			} else {
				logger.info("The RO in the list Contains status  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean clickOn_Own_ForReview_SO_Badge() {
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(forReviewBadge_SelfSO)) {
			logger.info("Badge for video For Review for self SO's is not available");
			throw new SkipException("Badge for Review not available, skipping test.");
		}
		logger.info("Badge for video For Review for self SO's is available");
		String countOnBadge = page.textContent(forReviewBadge_SelfSO);
		page.click(forReviewBadge_SelfSO);
		page.waitForURL(url -> url.contains("MY_FOR_REVIEW"));
		logger.info("Clicked on Badge for video For Review for self SO's");
		String countLabel = page.textContent(countOfTotalOrders).trim();
		if (countLabel.contains(countOnBadge)) {
			logger.info("The count " + countOnBadge + " on badge is equal to total count " + countLabel);
			flags.add(true);
		} else {
			logger.info("The count " + countOnBadge + " on badge is not equal to total count " + countLabel);
			flags.add(false);
		}
		Locator tableRow = page.locator(tableRows_SO);
		int rowCount = tableRow.count();
		for (int i = 0; i < rowCount; i++) {
			Locator salesAgents = tableRow.locator("td:nth-child(4)").nth(i);
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String salesAgent = salesAgents.textContent().trim();
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (status.contains("For Review") && salesAgent.contains(LoginPage.logInUsername)) {
				logger.info("The SO is of :" + salesAgent + " & Contains status  : " + status);
				flags.add(true);
			} else {
				logger.info("The SO is of :" + salesAgent + " & Contains status  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean clickOn_All_ForReview_SO_Badge() {
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(forReviewBadge_OtherSO)) {
			logger.info("Badge for video For Review for All SO's is not available");
			throw new SkipException("Badge for All For Review not available, skipping test.");
		}
		logger.info("Badge for video For Review for all SO's is available");
		String countOnBadge = page.textContent(forReviewBadge_OtherSO);
		page.click(forReviewBadge_OtherSO);
		page.waitForURL(url -> url.contains("ALL_FOR_REVIEW"));
		logger.info("Clicked on Badge for video For Review for all SO's");
		String countLabel = page.textContent(countOfTotalOrders).trim();
		if (countLabel.contains(countOnBadge)) {
			logger.info("The count " + countOnBadge + " on badge is equal to total count " + countLabel);
			flags.add(true);
		} else {
			logger.info("The count " + countOnBadge + " on badge is not equal to total count " + countLabel);
			flags.add(false);
		}
		Locator tableRow = page.locator(tableRows_SO);
		int rowCount = tableRow.count();
		for (int i = 0; i < rowCount; i++) {
			Locator statuses = tableRow.locator("td:nth-child(9)").nth(i);
			String status = statuses.innerText().replaceAll("\\s+", " ");
			if (countLabel.contains(countOnBadge) && status.contains("For Review")) {
				logger.info("The SO in the list Contains status  : " + status);
				flags.add(true);
			} else {
				logger.info("The SO in the list Contains status  : " + status);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean clickOn_Own_Messages_RO_Badge() {
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(inboundMessageBadge_SelfRO)) {
			logger.info("Badge for Self RO's Messages is not available");
			throw new SkipException("Badge for Self Messages not available, skipping test.");
		}
		logger.info("Badge for Self RO's Messages is available");
		page.click(inboundMessageBadge_SelfRO);
		page.waitForURL(url -> url.contains("filterBy=My+Messages"));
		logger.info("Clicked on Badge for Self RO's Messages");
		String isMyButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("My"))
				.getAttribute("aria-selected");
		String isActiveButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("Active"))
				.getAttribute("aria-selected");
		if (isMyButtonSelected.equals("true")) {
			logger.info("My Filter is selected on message screen");
			flags.add(true);
		} else {
			logger.info("My Filter is not selected on message screen");
			flags.add(false);
		}
		if (isActiveButtonSelected.equals("true")) {
			logger.info("Active Filter is selected on message screen");
			flags.add(true);
		} else {
			logger.info("Active Filter is not selected on message screen");
			flags.add(false);
		}
		String isSMSButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("SMS"))
				.getAttribute("aria-selected");
		logger.info(isSMSButtonSelected + " SMS ");
		return !flags.contains(false);
	}

	public boolean clickOn_All_Messages_RO_Badge() {
		page.waitForTimeout(4000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(inboundMessageBadge_AllRO)) {
			logger.info("Badge for All RO's Messages is not available");
			throw new SkipException("Badge for All Messages not available, skipping test.");
		}
		logger.info("Badge for All RO's Messages is available");
		page.click(inboundMessageBadge_AllRO);
		page.waitForURL(url -> url.contains("filterBy=All+Open"));
		logger.info("Clicked on Badge for All RO's Messages");
		String isMyButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("My"))
				.getAttribute("aria-selected");
		String isActiveButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("Active"))
				.getAttribute("aria-selected");
		if (isMyButtonSelected.equals("false")) {
			logger.info("My Filter is not selected on message screen");
			flags.add(true);
		} else {
			logger.info("My Filter is selected on message screen");
			flags.add(false);
		}
		if (isActiveButtonSelected.equals("true")) {
			logger.info("Active Filter is selected on message screen");
			flags.add(true);
		} else {
			logger.info("Active Filter is not selected on message screen");
			flags.add(false);
		}
		return !flags.contains(false);
	}

	public boolean clickOn_Own_Messages_SO_Badge() {
		page.waitForTimeout(4000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(inboundMessageBadge_SelfSO)) {
			logger.info("Badge for Self SO's Messages is not available");
			throw new SkipException("Badge for Self So Messages not available, skipping test.");
		}
		logger.info("Badge for Self SO's Messages is available");
		page.click(inboundMessageBadge_SelfSO);
		page.waitForURL(url -> url.contains("filterBy=My+Messages"));
		logger.info("Clicked on Badge for Self SO's Messages");
		String isMyButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("My"))
				.getAttribute("aria-selected");
//		String isActiveButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("Active"))
//				.getAttribute("aria-selected");
		if (isMyButtonSelected.equals("true")) {
			logger.info("My Filter is selected on message screen");
			flags.add(true);
		} else {
			logger.info("My Filter is not selected on message screen");
			flags.add(false);
		}
//		if (isActiveButtonSelected.equals("true")) {
//			logger.info("Active Filter is selected on message screen");
//			flags.add(true);
//		} else {
//			logger.info("Active Filter is not selected on message screen");
//			flags.add(false);
//		}
		return !flags.contains(false);
	}

	public boolean clickOn_All_Messages_SO_Badge() {
		page.waitForTimeout(4000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(inboundMessageBadge_AllSO)) {
			logger.info("Badge for All SO's Messages is not available");
			throw new SkipException("Badge for All So Messages not available, skipping test.");
		}
		logger.info("Badge for All SO's Messages is available");
		page.click(inboundMessageBadge_AllSO);
		page.waitForURL(url -> url.contains("filterBy=All+Open"));
		logger.info("Clicked on Badge for All SO's Messages");
		String isMyButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("My"))
				.getAttribute("aria-selected");
//		String isActiveButtonSelected = page.frameLocator(message_MainFrame).locator(filterButton("Active"))
//				.getAttribute("aria-selected");
		if (isMyButtonSelected.equals("false")) {
			logger.info("My Filter is not selected on message screen");
			flags.add(true);
		} else {
			logger.info("My Filter is selected on message screen");
			flags.add(false);
		}
//		if (isActiveButtonSelected.equals("true")) {
//			logger.info("Active Filter is selected on message screen");
//			flags.add(true);
//		} else {
//			logger.info("Active Filter is not selected on message screen");
//			flags.add(false);
//		}
		return !flags.contains(false);
	}

	public boolean clickOn_Own_Reminder_Badge() {
		page.waitForTimeout(4000);
		List<Boolean> flags = new ArrayList<>();
		if (!page.isVisible(reminderBadge_SelfRO)) {
			logger.info("Badge for self Reminder is not available");
			throw new SkipException("Badge for self Reminder not available, skipping test.");
		}
		logger.info("Badge for Self reminder is available");
		String countOnBadge = page.textContent(reminderBadge_SelfRO);
		page.click(reminderBadge_SelfRO);
		page.waitForURL(url -> url.contains("filterBy=MY_REMINDERS"));
		logger.info("Clicked on Badge for Self Reminder");
		String countLabel = page.textContent(countOfTotalOrders).trim();
		if (countLabel.contains(countOnBadge)) {
			logger.info("The count " + countOnBadge + " on badge is equal to total count " + countLabel);
			flags.add(true);
		} else {
			logger.info("The count " + countOnBadge + " on badge is not equal to total count " + countLabel);
			flags.add(false);
		}
		Locator tableRow = page.locator(tableRows);
		int rowCount = tableRow.count();
		for (int i = 0; i < rowCount; i++) {
			Locator advisors = tableRow.locator("td:nth-child(5)").nth(i);
			String advisor = advisors.innerText().trim();
			if (advisor.contains(LoginPage.logInUsername)) {
				logger.info("The RO in the list is of Login User  : " + advisor);
				flags.add(true);
			} else {
				logger.info("The RO in the list is not of Login User  : " + advisor);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean clickOn_All_Reminder_Badge() {
		page.waitForTimeout(4000);
		if (!page.isVisible(reminderBadge_AllRO)) {
			logger.info("Badge for All Reminder is not available");
			throw new SkipException("Badge for All Reminder not available, skipping test.");
		}
		logger.info("Badge for All reminder is available");
		String countOnBadge = page.textContent(reminderBadge_AllRO);
		page.click(reminderBadge_AllRO);
		page.waitForURL(url -> url.contains("filterBy=ALL_REMINDERS_OVERDUE"));
		logger.info("Clicked on Badge for All Reminder");
		String countLabel = page.textContent(countOfTotalOrders).trim();
		if (countLabel.contains(countOnBadge)) {
			logger.info("The count " + countOnBadge + " on badge is equal to total count " + countLabel);
			return true;
		} else {
			logger.info("The count " + countOnBadge + " on badge is not equal to total count " + countLabel);
			return false;
		}
	}
	
	public void Verify_dealer_Name(String name) {
		logger.info("Verify Dealer Name");
		page.waitForCondition(() -> page.locator(dealername).isVisible());
		
		if(dealername.equals(name)){
			logger.info("DEALER IS CORRECT");
		}
		else {
			logger.info("DEALER IS DIFFRENT");
			
		}
	}
}


