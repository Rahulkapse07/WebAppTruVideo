package com.truvideo.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class MessageScreen_Order extends JavaUtility {

	private Page page;

	public MessageScreen_Order(Page page) {
		this.page = page;
	}

	private String messageIframe = "#messages-body-iframe > #messages-iframe";
	private String message_profile = "//div[@class='profile__avatar']//div//div";
	private String message_profile_user = ".profile__user-info  p.profile__user-info-title";
	// private String Store_profile_name = "//a[@class='dropdown-toggle']//span[2]";
	private String Store_profile_name = "a.dropdown-toggle >span >span:nth-child(3)";
	private String message_profile_inside_close_btn = ".mat-mdc-dialog-component-host > .close";
	private String message_profile_input = "#mat-input-8";
	private String message_profile_save_btn = "//span[@class='mdc-button__label'][text()=' Save ']";
	private String message_profile_change = ".ng-pristine .avatar-container >.avatar-content";
	private String createIcon = "mat-icon.profile__action-fab-icon";
	private String Message_Filter_Icon = "//div[@class='profile__actions']//button//span[3]";
	private String Message_Search_conversation = "//input[@placeholder ='Search a conversation']";
	private String Message_Search_icon = "//div[@class='mat-mdc-form-field-icon-prefix ng-tns-c3736059725-2 ng-star-inserted']";
	private String Message_filter_icon = "//div[@class='mat-mdc-form-field-icon-suffix ng-tns-c3736059725-2 ng-star-inserted']";
	private String Message_filter_buttons = "span.mat-mdc-chip-focus-overlay";
	private String Message_list_icon_whatsapp = "div.channels-list-item__main > .channels-list-item__status-phone > .channels-list-item__avatar__service-icon";
	private String Message_filter_Whatsapplable = ".channels-list-item__status-phone .mat-icon";
	private String Message_start_convers_buttn = "button.profile__action-fab > span.mat-mdc-button-persistent-ripple";
	private String StartconversationBtn = ".chat-input__button span.mat-mdc-button-persistent-ripple";
	private String Countryoptionbtn = ".mat-mdc-form-field.prefix-form-field ";
	private String StartconversatationFirstname = "#mat-input-1";
	private String Startconversatationlastname = "#mat-input-2";
	private String StartconMobileno = "#mat-input-3";

	// Check UI of message R/O
	public boolean VerifyAll_Elements() {
		logger.info("Verify Visible Elements");
		FrameLocator iframe = page.frameLocator(messageIframe);
	    page.waitForCondition(()-> iframe.locator(Converstiontitlename).isVisible());
		// Frame for Message
		page.waitForCondition(() -> iframe.locator(message_profile).isVisible());
		if (iframe.locator(Message_Filter_Icon).isVisible() && iframe.locator(createIcon).isVisible()
				&& iframe.locator(Message_Search_conversation).isVisible()) {
			logger.info("Elements are verifed and Visible");
			return true;
		} else {
			logger.info("Elements are not visibles");
			return false;
		}
	}
	// Verify functionality of all Elements

	public boolean Message_Profile_setting_button() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		page.waitForCondition(()->iframe.locator(message_profile).isVisible());
		if (iframe.locator(message_profile).isVisible()) {
			iframe.locator(message_profile).click();
			logger.info("Message profile clicked");
			page.waitForTimeout(2000);
			
		//	iframe.locator(message_profile_input).click();
			logger.info("hsv");
			page.keyboard().press("Control+A");
			page.keyboard().press("Backspace");
			iframe.locator(message_profile_input).fill("Suraj singh");

			iframe.locator(message_profile_save_btn).click();
			logger.info("Clicked on save button");

			// iframe.locator(message_profile_change).click();
			// iframe.locator(message_profile_change).setInputFiles(Paths.get("./src/main/resources/Images/image.jpg"));
			iframe.locator(message_profile_inside_close_btn);
			logger.info("message_profile_close");
			return true;
		}
		return false;
	}

	public boolean Verify_message_Name() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage homePage = new HomePage(page);
		String storeusername = page.innerText(homePage.getLoginUserLabel()).toLowerCase();
		System.out.println(storeusername);
		String messageusername = iframe.locator(message_profile_user).innerText().toLowerCase();
		System.out.println(messageusername);
		if (storeusername.trim().equals(messageusername.trim())) {
			logger.info("The Channel owner is same user who is login" + messageusername);
			return true;
		} else {
			logger.info("The Channel owner is not match with user who is login");
			return false;
		}
	}

	private String list_channelowner = ".list-all div p.channels-list-item__advisor";

	private String filterButton(String buttonText) {
		return "button:has-text('" + buttonText + "')";
	}

	public boolean verifyMyFilter() {
		FrameLocator iframe = page.frameLocator(messageIframe);

		page.waitForCondition(() -> iframe.locator(filterButton("My")).isVisible());

		isFilterApplied("My");

		List<Boolean> flags = new ArrayList<>();
		HomePage homePage = new HomePage(page);
		String loginUser = page.innerText(homePage.getLoginUserLabel()).toLowerCase();
		logger.info("Login user name catched : " + loginUser);
		page.waitForTimeout(5000);
		List<String> adviosrlist = iframe.locator(list_channelowner).allInnerTexts();
		for (String advisor : adviosrlist) {
		if (advisor.toLowerCase().contains(loginUser)) {
				logger.info(loginUser + " Login user is matched with channele owner : " + advisor);
				flags.add(true);
			} else {
				logger.info(loginUser + " Login user is not matched with channele owner : " + advisor);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	private String ChatFilterButtons = "span button";

	public boolean verifyfilterbuttons() {
		page.reload();
		page.waitForTimeout(5000);
		FrameLocator iframe = page.frameLocator(messageIframe);

		page.waitForTimeout(5000);
		page.waitForCondition(() -> iframe.locator(filterButton("My")).isVisible());

		String isMyButtonSelected = iframe.locator(filterButton("My")).getAttribute("aria-selected");
		String iswhatsappButtonSelected = iframe.locator(filterButton(" Whatsapp")).getAttribute("aria-selected");
		String isSmsButtonSelected = iframe.locator(filterButton(" SMS")).getAttribute("aria-selected");
		String isunreadButtonSelected = iframe.locator(filterButton(" Unread")).getAttribute("aria-selected");

		List<Boolean> flags = new ArrayList<>();
		if (!isMyButtonSelected.equals("true") == true) {
			logger.info("My filter is not selected by default");
			iframe.locator(filterButton("My")).click();
			logger.info("Click on My filter to select it");
			flags.add(false);
		}
		iframe.locator(filterButton("My")).click();
		logger.info("My button working properly");

		if (!iswhatsappButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			iframe.locator(filterButton("Whatsapp")).dblclick();
			logger.info("Click on My filter to select it");
			flags.add(false);
		}
		iframe.locator(filterButton("Whatsapp")).click();
		logger.info("Whatsapp button working properly");
		page.waitForTimeout(2000);
		if (isSmsButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			flags.add(false);
		}
		iframe.locator(filterButton("SMS")).click();
		logger.info("Click on My filter to select it");
		page.waitForTimeout(2000);

		if (isunreadButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			flags.add(false);
			logger.info("Click on My filter to select it");
		}
		iframe.locator(filterButton("Unread")).click();
		page.waitForTimeout(2000);

		return !flags.contains(false);

	}

	public boolean checkMy_WhatsApp_FilterIsApplied() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		if (!isFilterApplied("My") == true) {
			flags.add(false);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean check_my_filterIsApplied() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		if (!isFilterApplied("My") == true) {
			flags.add(false);
		}
		iframe.locator(filterButton("My")).click();

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				flags.add(false);
			}
		}

		return !flags.contains(false);
	}

	// method to select filter buttons

//	public boolean click_My_filterBotton() {
//		FrameLocator iframe = page.frameLocator(messageIframe);
//		// Store true false for return value
//		List<Boolean> values = new ArrayList<>();
//
//		if (!isFilterApplied("My") == true) {
//			logger.info("My button was not selected");
//			iframe.locator(filterButton("My")).click();
//			values.add(false);
//		}
//		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
//			if (!isFilterApplied("Whatsapp") == true) {
//				logger.info("whatsapp is alredy selected");
//				values.add(false);
//			}
//			iframe.locator(filterButton("Whatsapp")).click();
//			logger.info("Remove Whatsapp filter");
//			page.waitForTimeout(5000);
//		}
//
//		return !values.contains(false);
//
//	}

	public boolean click_Whatsapp_filterBotton() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (isFilterApplied("My") == true) {
			logger.info("Remove My filter");
			iframe.locator(filterButton("My")).click();
			values.add(true);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (isFilterApplied("Whatsapp") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Whatsapp")).click();
			logger.info("whatsapp selected");
			page.waitForTimeout(5000);
		}

		return !values.contains(false);

	}

	public boolean click_Sms_filterBotton() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (isFilterApplied("My") == true) {
			logger.info("Remove My filter");
			values.add(false);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (isFilterApplied("Whatsapp") == true) {
				iframe.locator(filterButton("Whatsapp")).click();
				values.add(true);
			}
			page.waitForTimeout(5000);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("SMS")).click();
			logger.info("SMS selected");

			page.waitForTimeout(5000);
		}

		return !values.contains(false);

	}

	public boolean click_Unread_filterBotton() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (isFilterApplied("My") == true) {
			logger.info("Remove My filter");
			values.add(true);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				values.add(true);
			}
			page.waitForTimeout(5000);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (!isFilterApplied("SMS") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("SMS")).click();

			page.waitForTimeout(5000);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Unread")) {
			if (isFilterApplied("Unread") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Unread")).click();
			logger.info("Unread selected");

			page.waitForTimeout(5000);
		}

		return !values.contains(false);

	}

	private String Channal_list_MY_SmS = ".channels-list-item__main .channels-list-item__advisor";

	public boolean click_My_AND_Sms_filterBotton() {

		page.reload();

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (!isFilterApplied("My") == true) {
			values.add(false);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Whatsapp")).click();
			page.waitForTimeout(5000);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				values.add(true);
			}
			iframe.locator(filterButton("SMS")).click();
			page.waitForTimeout(5000);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Unread")) {
			if (isFilterApplied("Unread") == true) {
				values.add(false);
			}
			page.waitForTimeout(5000);
		}

		page.waitForCondition(() -> iframe.locator(filterButton("my")).isVisible());
		List<Boolean> flags = new ArrayList<>();
		HomePage homePage = new HomePage(page);
		String loginUser = page.innerText(homePage.getLoginUserLabel()).toLowerCase();
		logger.info("Login user name catched : " + loginUser);
		page.waitForTimeout(5000);
		List<String> adviosrlist = iframe.locator(Channal_list_MY_SmS).allInnerTexts();
		for (String advisor : adviosrlist) {
			logger.info("Iterating list of owner");
			if (advisor.toLowerCase().contains(loginUser)) {
				logger.info(loginUser + " Login user is matched with channele owner : " + advisor);
				flags.add(true);
			} else {
				logger.info(loginUser + " Login user is not matched with channele owner : " + advisor);
				flags.add(false);
			}
		}

		return !values.contains(false);

	}

	public boolean click_My_AND_UNREAD_filterBotton() {
		page.reload();

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (!isFilterApplied("My") == true) {
			values.add(false);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Whatsapp")).click();
			page.waitForTimeout(5000);
		}

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				values.add(false);
			}
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Unread")) {
			if (isFilterApplied("Unread") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Unread")).click();
			page.waitForTimeout(5000);
		}

		return !values.contains(false);

	}

	// Method For Verify Functionality Of filter buttons
	
	private boolean isFilterApplied(String buttonName) {
		FrameLocator iframe = page.frameLocator(messageIframe);
		String isMyButtonSelected = iframe.locator(filterButton(buttonName)).getAttribute("aria-selected");
		if (isMyButtonSelected.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	private String filterButtonforwhatsapp(String buttonText) {
		return ".channels-list-item__status-phone .mat-icon : has-text('" + buttonText + "')";
	}

	private String firstname = "Suraj";
	private String lastname = "Singh";

	private String StartConverSMS_Whatsapp_filterbuttn = "#mat-select-value-1";
	private String StartConverSMS_Whatsapp_text = ".mat-mdc-option span";
	private String StartConverSMS_Whatsapp = "#mat-select-0-panel mat-option span:has-text('Whatsapp')";
	private String StartConverSMS_Sms = "#mat-option-0:has-text('SMS')";
	private String ConversationInfo = "#first-content .info-container__content__title";
	private String Converstiontitlename = ".chat-header__main p.chat-header__title";
	private String ConversationInfobn = ".chat-header__drop-down button span.mat-mdc-button-touch-target";

	private String conversationTextlabel = "div.channels-list-item__title-container p[aria-describedby='cdk-describedby-message-ng-1-4']";

	private String CountryName(String countryname) {

		return ".mdc-list-item__primary-text .country-option  div:nth-child(2):has-text('" + countryname + "')";
	}

	public boolean verifyStartconversatationbtn(String number) {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		iframe.locator(Message_start_convers_buttn).click();
		logger.info(Message_Filter_Icon);
		page.waitForCondition(() -> iframe.locator(StartconversatationFirstname).isVisible());
		iframe.locator(StartconversatationFirstname).fill(firstname);
		logger.info(Message_Filter_Icon);
		iframe.locator(Startconversatationlastname).fill(lastname);
		logger.info(Message_Filter_Icon);
		page.waitForTimeout(3000);
		iframe.locator(Countryoptionbtn).click();
		logger.info(Message_Filter_Icon);
		iframe.locator(CountryName("United States")).click();
		logger.info(Message_Filter_Icon);
		iframe.locator(StartconMobileno).fill(number);
		logger.info("Number:-" + number);
		page.waitForTimeout(2000);
		iframe.locator(StartConverSMS_Whatsapp_filterbuttn).click();
		page.waitForTimeout(2000);

		List<String> Text = iframe.locator(filterButton("SMS")).allInnerTexts();

		for (String value : Text) {

			if (value.trim().toUpperCase().contains("SMS")) {
				iframe.locator(filterButton("SMS")).click();
				logger.info("Select SMS");

			} else {
				logger.info("not clicked");

			}
		}
        if(iframe.locator(StartconversationBtn).isVisible()) {
        	try {
				iframe.locator(StartconversationBtn).click();
				logger.info("Button hit");
				flags.add(true);

			} catch (ElementNotInteractableException e) {
				logger.info("elemnt is not clickable right now");
				e.printStackTrace();
				flags.add(true);
			}
		} else {
			logger.info("Element not found");
		}
		page.waitForCondition(() -> iframe.locator(Converstiontitlename).isVisible());
		page.waitForTimeout(7000);
		String conversinfoname = iframe.locator(ConversationInfo).innerText().toLowerCase();
		String converstextlabelname = iframe.locator(conversationTextlabel).innerText().toLowerCase();
		String converstitlename = iframe.locator(Converstiontitlename).innerText().toLowerCase();
		System.out.println(conversinfoname + converstextlabelname + converstitlename);
		iframe.locator(ConversationInfobn).click();
		if (conversinfoname.contains(converstitlename) && conversinfoname.contains(converstextlabelname)) {
			logger.info(
					"All names are Matched :" + converstitlename + ":" + conversinfoname + ":" + converstextlabelname);
			flags.add(true);
		} else {
			logger.info("error message");
			flags.add(false);
		}
		return !flags.contains(false);
	}

	private String SearchFilter2 = ".channels-search div.mat-mdc-text-field-wrapper div.mat-mdc-form-field-focus-overlay ";
	private String SearchFilter = "#mat-input-0";
	private String SearchbtnSvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-prefix svg";
	private String Searchbtnfiltersvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-suffix svg";

	public boolean SearchMessagefilter() {
		page.waitForTimeout(5000);
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		if (iframe.locator(SearchFilter2).isVisible()) {
			iframe.locator(SearchFilter).fill("suraj");
			page.waitForTimeout(2000);
			logger.info("Search Filter is not Present");
			flags.add(false);
		} else {
			String name = "Suraj";
			iframe.locator(SearchFilter).fill(name);
		}

		return !flags.contains(false);

	}

	private String ConversationTab = "#header-info p";
	private String ReadUnreadbtn = ".info-container__content__actions span";

	private String MessageRedDotNotification = ".channels-list-item__unreads-container.ng-star-inserted span";
	private String Messagebandage = "#my-service-message span.km-tab";
	private String Messagebandagtotalcount = "#all-service-message a span";

	public boolean VerifyReadUnreadNotification() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		// iframe.locator("#first-content button span:nth-child(2)").click();
		List<Boolean> flags = new ArrayList<>();

		page.waitForCondition(() -> iframe.locator(ConversationTab).isVisible());
		if (!iframe.locator(Conversation_GotoRo_btn).isVisible()) {
			logger.info("Conversation Tab Not Visible");
			flags.add(false);
		}
		logger.info("Conversation Tab Visible");

		page.waitForTimeout(4000);
		String beforeupdate;

		if (page.locator(Messagebandage).isVisible()) {
			logger.info("Unread messages are present");

			int count = iframe.locator(MessageRedDotNotification).count();
			String Str = String.valueOf(count);
			System.out.println(Str);
			if (!iframe.locator(ReadUnreadbtn).isVisible()) {
				logger.info("Button has not clicked");
				flags.add(false);
			}
			iframe.locator(ReadUnreadbtn).click();
			logger.info("Button has clicked");
			page.waitForTimeout(5000);
			beforeupdate = page.locator(Messagebandage).innerText();
			System.out.println(beforeupdate);
			logger.info("a");

		} else {
			beforeupdate = "0";
			logger.info("No Unread Message present");
			if (!iframe.locator(ReadUnreadbtn).isVisible()) {
				logger.info("Button has not clicked");
				flags.add(false);
			}
			iframe.locator(ReadUnreadbtn).click();
			logger.info("Button has clicked");
			beforeupdate = page.locator(Messagebandage).innerText();
			System.out.println(beforeupdate);
			logger.info("a");
			int count = iframe.locator(MessageRedDotNotification).count();
			String Str = String.valueOf(count);
			System.out.println(Str);
		}

		// Count Total Number Of Bandags
		List<Boolean> values = new ArrayList<>();
		logger.info("Select Unread filter");
		if (!isFilterApplied("My") == true) {
			logger.info("Remove My filter");
			values.add(false);
		}
		iframe.locator(filterButton("My")).click();

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				values.add(false);
			}
			page.waitForTimeout(5000);
			iframe.locator(filterButton("Whatsapp")).click();
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				iframe.locator(filterButton("SMS")).click();
				values.add(false);
			}
			page.waitForTimeout(5000);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Unread")) {
			if (isFilterApplied("Unread") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("Unread")).click();
			logger.info("Unread selected");

		}

		page.waitForTimeout(4000);
		iframe.locator(".ng-star-inserted div.channels-list-item.channels-list-item--active").click();
		System.out.println("clicked");
		page.waitForTimeout(2000);

		if (page.locator(Messagebandagtotalcount).isVisible()) {
			logger.info("Unread messages are present");

			int count = iframe.locator(MessageRedDotNotification).count();
			String Str = String.valueOf(count);
			System.out.println(Str);
			if (!iframe.locator(ReadUnreadbtn).isVisible()) {
				logger.info("Button has not clicked");
				flags.add(false);
			}
			iframe.locator(ReadUnreadbtn).click();
			logger.info("Button has clicked");
			page.waitForTimeout(5000);
			flags.add(true);
		} else {
			logger.info("No Unread Message present");
			if (!iframe.locator(ReadUnreadbtn).isVisible()) {
				logger.info("Button has not clicked");
				flags.add(false);
			}
			iframe.locator(ReadUnreadbtn).click();
			logger.info("Button has clicked");
			beforeupdate = page.locator(Messagebandagtotalcount).innerText();
			System.out.println(beforeupdate);
			logger.info("a");
		}

		return !flags.contains(false);
	}

	private String Conversation_GotoRo_btn = "button.order-button";
	private String ReturnToMessagePAge = ".main-body div div.return p";
	private String RopageIframe = "messages-iframe";
	private String Conversationtbcanclebtn = ".info-container__header__close-btn mat-icon svg";

	public boolean ConversationGOtoRobtn() {

		FrameLocator iframe = page.frameLocator(messageIframe);

		page.waitForCondition(() -> iframe.locator(Conversation_GotoRo_btn).isVisible());
		if (!iframe.locator(Conversation_GotoRo_btn).isVisible()) {
			logger.info("Go To Ro button not visible");
			return false;
		}
		iframe.locator(Conversation_GotoRo_btn).click();
		logger.info("GO_TO_RO button clicked");

		page.waitForCondition(() -> iframe.locator(ReturnToMessagePAge).isVisible());
		iframe.locator(ReturnToMessagePAge).click();
		logger.info("ReturnToMessagePAge");
		iframe.locator(Conversationtbcanclebtn).click();
		return true;
	}

	private String Conversationinactivemess = ".chat-body__blocked-message.ng-star-inserted mat-icon";
	private String Reactivatebtn = ".chat-input__options div";
	private String InsertTextfield = "#mat-input-1";
	private String InsertTextField2 = ".mat-mdc-input-element.ng-tns-c3736059725-6.ng-pristine.mat-mdc-form-field-textarea-control.mat-mdc-form-field-input-control.mdc-text-field__input.cdk-text-field-autofill-monitored.ng-touched.ng-valid";

	public boolean VerifyWhatsAppChatEnableCondition() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		logger.info("CheckWhatsapp filter is Enable or Disable From Dealer Setting");

		page.waitForTimeout(8000);
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {

			if (isFilterApplied("My") == true) {
				logger.info("Remove My filter");
			}
			logger.info("Remove My filter");
			iframe.locator(filterButton("My")).click();
			if (isFilterApplied("Whatsapp") == true) {
				logger.info("Clicked on whatsapp filter");
			}
			page.waitForTimeout(5000);

			if (isFilterApplied("SMS") == true) {
				iframe.locator(filterButton("SMS")).click();
			}
			page.waitForTimeout(5000);

			if (isFilterApplied("Unread") == true) {
				iframe.locator(filterButton("Unread")).click();
			}
			page.waitForTimeout(5000);

			/* Check Text Box For WhatsApp */
			if (iframe.locator(Conversationinactivemess).isVisible()) {
				String InactiveMessage = iframe.locator(Conversationinactivemess).innerText();
				if (iframe.locator(Conversationinactivemess).innerText().contains(InactiveMessage)) {
					iframe.locator(Reactivatebtn).click();
					logger.info("click");
				} else {
					iframe.locator(InsertTextfield).fill("DemoTestMessage");
					logger.info("Message Insert");
				}

			}

			logger.info("WhatsApp filter is Disabled");
			flags.add(false);

		}

		return !flags.contains(false);

	}

	private String Messagechannel = ".channels-list-item__main p";

	public boolean MessageSendAttachments() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> values = new ArrayList<>();
		if (isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true) {

			iframe.locator(filterButton("My")).click();
			iframe.locator(filterButton("Whatsapp")).click();
			logger.info("Filter Removed");
		} else {
			values.add(false);
		}
		page.waitForTimeout(5000);

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				values.add(false);
			}
			iframe.locator(filterButton("SMS")).click();
			logger.info("SMS selected");
			page.waitForTimeout(5000);
			
			iframe.locator(SearchFilter).type("suraj Singh");
//			iframe.locator(SearchFilter).fill("suraj Singh");
			page.waitForTimeout(8000);
			
			iframe.locator(".channels-list-item.channels-list-item--active").first().click();
			page.waitForTimeout(3000);
			iframe.locator("button input[type=file]").setInputFiles(Paths.get(".src/main/resources/Images/image.jpg"));
			
		
			  

	            
			
		}

		return true;

	}

}
