package com.truvideo.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class MessageScreen_Order extends JavaUtility {

	private Page page;

	public MessageScreen_Order(Page page) {
		this.page = page;
	}
	
	private String messageIframe = "#messages-body-iframe > #messages-iframe";
	private String message_profile = "#profile .avatar-container";
	private String message_profile_user = ".profile__user-info  p.profile__user-info-title";
	// private String Store_profile_name = "//a[@class='dropdown-toggle']//span[2]";
	private String message_profile_input = "input[id='mat-input-2']";
	private String message_profile_save_btn = "div.mat-mdc-dialog-actions button span:nth-child(3)";
	private String createIcon = "mat-icon.profile__action-fab-icon";
	private String Message_Filter_Icon = "//div[@class='profile__actions']//button//span[3]";
	private String Message_Search_conversation = "input[id='mat-input-0']";
	private String Message_filter_buttons = "span.mat-mdc-chip-focus-overlay";
	private String Message_filter_Whatsapplable = ".channels-list-item__status-phone .mat-icon";
	private String Message_start_convers_buttn = "button.profile__action-fab > span.mat-mdc-button-persistent-ripple";
	private String StartconversationBtn = ".chat-input__button span.mdc-button__label";
	private String Countryoptionbtn = ".mat-mdc-form-field.prefix-form-field ";
	private String StartconversatationFirstname = "#mat-input-1";
	private String Startconversatationlastname = "#mat-input-2";
	private String StartconMobileno = "#mat-input-7";
	private String Message_Search_icon = "#profile div.avatar-container";

	private String StartConverSMS_Whatsapp_filterbuttn = "#mat-select-value-1";
	private String StartConverSMS_Whatsapp_text = ".mat-mdc-option span";
	private String StartConverSMS_Whatsapp = "#mat-select-0-panel mat-option span:has-text('Whatsapp')";
	private String StartConverSMS_Sms = "#mat-option-0:has-text('SMS')";
	private String ConversationInfo = "#first-content .info-container__content__title";
	private String Converstiontitlename = ".chat-header__main p.chat-header__title";
	private String ConversationInfobn = ".chat-header__drop-down button span.mat-mdc-button-touch-target";

	private String SearchFilter2 = ".channels-search div.mat-mdc-text-field-wrapper div.mat-mdc-form-field-focus-overlay ";
	private String SearchFilter = "#mat-input-0";
	private String SearchbtnSvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-prefix svg";
	private String Searchbtnfiltersvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-suffix svg";

	private String Conversation_header = ".info-container__content div.avatar-container";
	private String Conversation_GotoRo_btn = "button.order-button";
	private String ReturnToMessagePAge = ".main-body div div.return p";
	private String Conversationtbcanclebtn = ".info-container__header__close-btn mat-icon svg";
	private String ConversationChannelOwnerName = "#first-content div.info-container__content__title";
	private String ROChannelOwnerName = "div.chat-header__main p.chat-header__title";

	private String Conversationinactivemess = "div.chat-body__blocked-message.ng-star-inserted";
	private String Reactivatebtn = ".chat-input__options div";
	private String Messagechatfield = "#mat-input-1";
	private String NOconversationStartmessasge = ".chat-empty-container.ng-star-inserted h1";
	private String ChannalList = ".channels-list__section.list-all .ng-star-inserted .channels-list-item";

	private String MessageAttachment_btn = "button.mdc-icon-button.mat-mdc-icon-button input[type='file']";
	private String AttachmentPath = "src/main/resources/Images/testimage.png";
	private String MessageSendBtn = ".chat-input__options button:nth-child(2) mat-icon";
	private String ConversationStartbtn = "#mat-select-0-panel:has-text('SMS')";
	private String Messageownername = ".chat-header__phone p:nth-child(4)";
	private String addRepairOrder_Btn = "#repair-order-add";
	private String SendOriginal_btn = ".mdc-button.mdc-button--outlined.mat-mdc-outlined-button span.mat-mdc-focus-indicator";

	private String conversationTextlabel = ".chat-header__main p.chat-header__title";

	// Check UI of message R/O
	public boolean VerifyAll_Elements() {
		logger.info("Verify Visible Elements");
		FrameLocator iframes = page.frameLocator(messageIframe);
		page.waitForTimeout(5000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		// Frame for Message
		page.waitForTimeout(4000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		if  (iframes.locator(createIcon).isVisible() && iframes.locator(Message_Search_conversation).isVisible()
				&& iframes.locator(Message_Search_icon).isVisible() && iframes.locator(Conversation_header).isVisible()) {
			logger.info("Elements are verifed and Visible");
			return true;
		} else {
			logger.info("Elements are not visibles");
			return false;
		}
	}


	// Verify functionality of all Elements

	private String MessageProfileName = ".profile__user-info p:nth-child(1)";

	public boolean Verify_Profile_setting_button(String ProfileName) {
		page.reload();
		logger.info("Verify Message Profile Settings");
		List<Boolean> flags = new ArrayList<>();
		FrameLocator iframe = page.frameLocator(messageIframe);
		String MessageprofileName = iframe.locator(MessageProfileName).innerText().toLowerCase();
		String MessageOwneraName = iframe.locator(Messageownername).innerText().toLowerCase();
		page.waitForCondition(() -> iframe.locator(message_profile).isVisible());
		if (MessageprofileName.toLowerCase().equals(MessageOwneraName)) {
			logger.info("Profile After changes Matched");
		} else {
			logger.info("Profile name not changed");
		}
		if (iframe.locator(message_profile).isVisible()) {
			page.waitForTimeout(3000);
			iframe.locator(message_profile).click();
			logger.info("Message profile clicked");
			page.waitForTimeout(5000);
			// iframe.locator(message_profile_input).click();
			page.keyboard().press("Control+A");
			page.waitForTimeout(5000);
			iframe.locator(message_profile_input).type(ProfileName);
			page.waitForTimeout(1500);
			iframe.locator(message_profile_save_btn).click();
			page.waitForTimeout(3000);
			logger.info("Clicked on save button");
			page.reload(); // We dont need this reload,This is the issue right now
		} else {
			logger.info("Message Profile Setting Failed To Open");
			flags.add(false);
		}
		page.waitForTimeout(3000);
		if (MessageprofileName.toLowerCase().equals(MessageOwneraName)) {
			logger.info("Profile After changes Matched");
		} else {
			logger.info("Profile name not changed");
		}
		return !flags.contains(false);
	}

	public boolean Verify_message_Name() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage homePage = new HomePage(page);
		String storeusername = page.innerText(homePage.getLoginUserLabel()).toLowerCase();
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
		System.out.println(adviosrlist);
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

	private String ChatFilterButtons = "span button span:nth-child(2)";

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

	public boolean verify_Default_Filters() throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage HP = new HomePage(page);
		List<Boolean> flags = new ArrayList<>();
		if (!isFilterApplied("My") == true) {
			flags.add(false);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				flags.add(false);
			}
		} else {
			HP.navigateToDealersPage();
			page.waitForTimeout(5000);
			DealersPage dp = new DealersPage(page);
			dp.Verify_Whatsapp_textconversation("Tru", "Text Conversation");
			logger.info("CHAT IS ENABLE NOW");
			HP.navigateToMessageScreen_Order();
			logger.info("VERIFY WHATSAPP FILTER ");
			if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
				if (!isFilterApplied("Whatsapp") == true) {
					flags.add(false);
				}
			}

		}
		logger.info("DEFAULT FILTERS PRESENT");
		return !flags.contains(false);
	}

	public boolean Verify_my_filterIsApplied() {
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

	public boolean verify_Whatsapp_filterBotton() {
		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("Select only Whatsapp filter");

		if (!isFilterApplied("My") == true) {
			values.add(false);
		}
		iframe.locator(filterButton("My")).click();

		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (!isFilterApplied("Whatsapp") == true) {
				values.add(false);
			}

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

	private String CountryName(String countryname) {

		return ".mdc-list-item__primary-text .country-option  div:nth-child(2):has-text('" + countryname + "')";
	}

	private String firstname = "Suraj";
	private String lastname = "Singh";

	private String StartConFilter(String filter) {
		return "#mat-select-0-panel mat-option span:has-text('" + filter + "')";
	}

	public boolean verifyStartconversatationbtn(String number, String filter) {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		page.waitForCondition(() -> iframe.locator(Message_start_convers_buttn).isVisible());
		if (iframe.locator(Message_start_convers_buttn).isVisible()) {
			logger.info("CONVERSATION CHAT IS VISIBLE");
			iframe.locator(Message_start_convers_buttn).click();
			logger.info("OPENED CONVERSATION TAB");
			page.waitForCondition(() -> iframe.locator(StartconversatationFirstname).isVisible());
			iframe.locator(StartconversatationFirstname).fill(firstname);
			logger.info("ENTERED FIRSTNAME :-"+ firstname);
			iframe.locator(Startconversatationlastname).fill(lastname);
			logger.info("ENTERED LASTNAME :-" + lastname);
			page.waitForTimeout(3000);
			iframe.locator(Countryoptionbtn).click();
			iframe.locator(CountryName("United States")).click();
			logger.info("SELECTED COUNTRY");
			page.waitForTimeout(3000);
			iframe.locator(StartconMobileno).fill(number);
			logger.info("Number:-" + number);
			page.waitForTimeout(2000);
			iframe.locator(StartConverSMS_Whatsapp_filterbuttn).click();
			page.waitForTimeout(2000);

			List<String> Text = iframe.locator(StartConFilter(filter)).allInnerTexts();
			for (String value : Text) {
				if (value.trim().toUpperCase().contains("SMS")) {
					iframe.locator(StartConFilter(filter)).click();
					logger.info("Select SMS");

				}
				if (value.trim().toUpperCase().contains("WHATSAPP")) {
					iframe.locator(StartConFilter(filter)).click();
					logger.info("Select WHATSAPP");

				} else {
					flags.add(false);
					logger.info("not clicked");
				}
			}
			if (iframe.locator(StartconversationBtn).isVisible()) {
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
				flags.add(false);
				logger.info("Element not found");
			}
			page.waitForCondition(() -> iframe.locator(Converstiontitlename).isVisible());
			page.waitForTimeout(7000);
			if (!iframe.locator(ConversationInfo).isVisible()) {
				iframe.locator(".chat-header__drop-down button").click();
			}
			logger.info("text1");
			String conversinfoname = iframe.locator(ConversationInfo).innerText().toLowerCase();
			logger.info("text1");
			String converstextlabelname = iframe.locator(conversationTextlabel).innerText().toLowerCase();
			logger.info("text1");
			String converstitlename = iframe.locator(Converstiontitlename).innerText().toLowerCase();
			System.out.println(conversinfoname + converstextlabelname + converstitlename);
			iframe.locator(ConversationInfobn).click();
			if (conversinfoname.contains(converstitlename) && conversinfoname.contains(converstextlabelname)) {
				logger.info("All names are Matched :" + converstitlename + ":" + conversinfoname + ":"
						+ converstextlabelname);
				flags.add(true);
			} else {
				logger.info("error message");
				flags.add(false);
			}
		} else {
			logger.info("CONVERSATION CHAT IS NOT VISIBLE");
			flags.add(false);
		}
		return !flags.contains(false);
	}

	public boolean SearchMessagefilter() {
		page.waitForTimeout(5000);
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		if (!iframe.locator(SearchFilter2).isVisible()) {
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

//	private String ConversationTab = "#header-info p";
//    private String ReadUnreadbtn(String Value) {
//		return ".info-container__content__actions.ng-star-inserted:has-text('" + Value + "')";
//	}
//
//	private String MessageRedDotNotification = ".channels-list-item__unreads-container.ng-star-inserted span";
//	private String Messagebadge = "span#my-service-message";
//	private String Messagebandagtotalcount = "#all-service-message a span";
//
//	public boolean VerifyReadUnreadNotification() {
//
//		FrameLocator iframe = page.frameLocator(messageIframe);
//		List<Boolean> flags = new ArrayList<>();
//		page.waitForCondition(() -> page.locator(Messagebadge).isVisible());
//		if (!page.locator(Messagebadge).isVisible()) {
//			logger.info("NO UNREAD MESSAGES ARE PRESENT");
//		}
//		logger.info("UNREAD NOTIFICATIONS ARE PRESENT");
//		page.waitForTimeout(3000);
//		String beforeupdate;
//		logger.info("COUNT UNREAD MESSAGE PRESENT");
//		page.locator(Messagebadge).click();
//        logger.info("Button has clicked");
//		page.waitForTimeout(5000);
//		beforeupdate = page.locator(Messagebadge).innerText();
//		System.out.println(beforeupdate);
//		logger.info("a");
//		return true;
//
//	}
	public boolean ConversationGOtoRobtn() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		page.waitForCondition(() -> iframe.locator(message_profile_user).isVisible());
		page.waitForTimeout(3000);

		if (iframe.locator(NOconversationStartmessasge).isVisible()) {
			if (!isFilterApplied("My") == true && !isFilterApplied("Whatsapp") == true) {
				return false;
			}
			iframe.locator(filterButton("My")).click();
			page.waitForTimeout(2000);
			iframe.locator(ChannalList).first().click();
			if (!iframe.locator(Conversation_GotoRo_btn).isVisible()) {
				logger.info("Go To Ro button not visible");
				return false;
			}
			String ChannelOwnerName = iframe.locator(ConversationChannelOwnerName).innerText();
			iframe.locator(Conversation_GotoRo_btn).click();
			logger.info("GO_TO_RO button clicked");
			page.waitForCondition(() -> iframe.locator(ReturnToMessagePAge).isVisible());
			String ROChannelname = iframe.locator(ROChannelOwnerName).innerText();
			page.waitForTimeout(10000);
			if (ROChannelname.contains(ChannelOwnerName)) {
				iframe.locator(ReturnToMessagePAge).click();
				logger.info("CHANNEL OWNER NAME MATCHED" + ROChannelname + ":" + ChannelOwnerName);
			} else {
				logger.info("CHANNEL OWER NAME IS NOT MATCHED");
				return false;
			}
		}
		iframe.locator(Conversationtbcanclebtn).click();
		return true;
	}

	public boolean VerifyWhatsAppChatEnableCondition() {
		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);
		logger.info("CheckWhatsapp filter is Enable or Disable From Dealer Setting");
		page.waitForTimeout(6000);
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			logger.info("CheckWhatsapp filter is Enable");
			if (!isFilterApplied("My") == true && !isFilterApplied("Whatsapp") == true) {
				return false;
			}
			iframe.locator(filterButton("My")).click();
			page.waitForTimeout(2000);
			if (isFilterApplied("SMS") == true && isFilterApplied("Unread") == true) {
				iframe.locator(filterButton("SMS")).click();
				iframe.locator(filterButton("Unread")).click();
			}
			page.waitForTimeout(5000);
			logger.info("WHATSAPP FILTER SELECTED");
			iframe.locator(ChannalList).first().click();

			/* Check Text Box For WhatsApp */

			if (iframe.locator(Conversationinactivemess).isVisible()) {
				String InactiveMessage = iframe.locator(Conversationinactivemess).innerText();
				if (iframe.locator(Conversationinactivemess).innerText().contains(InactiveMessage)) {
					iframe.locator(".channels-list-item__main div:nth-child(3) span:has-text('(781) 205-9487')")
							.click();
					logger.info("Whatsapp Chat is Expired");
					page.waitForTimeout(5000);
				} else {
					if (iframe.locator(NOconversationStartmessasge).isVisible()) {
						logger.info("NO Conversation start Until in Whatsapp");
					} else {
						return false;
					}
				}
			} else {
				logger.info("WHATSAPP CHAT IS ACTIVE");
				iframe.locator(".channels-list-item__main div:nth-child(3) span:has-text('(781) 205-9487')").first()
						.click();
				iframe.locator(Messagechatfield).fill("demotext..........");
				iframe.locator(".chat-input__options button:nth-child(3) mat-icon").click();
				iframe.locator(SendOriginal_btn).click();
			}
		} else {
			logger.info("WhatsApp filter is Disabled");
			iframe.locator(ChannalList).first().click();
			iframe.locator(Messagechatfield).fill("demotext..........");
			iframe.locator(MessageSendBtn).click();
			iframe.locator(SendOriginal_btn).click();

		}
		return true;
	}

	public boolean MessageSendAttachments(String number) {
		page.reload();
		logger.info("VERIFY ATTACHMENT");
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		logger.info("SELECT SMS FILTER TOS END ATTACHMENT");
		if (isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true) {
			iframe.locator(filterButton("My")).click();
			iframe.locator(filterButton("Whatsapp")).click();
		} else {
			flags.add(false);
			logger.info("Filters are not Visible");
		}
		page.waitForTimeout(5000);
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("SMS")) {
			if (isFilterApplied("SMS") == true) {
				flags.add(false);
			}
			iframe.locator(filterButton("SMS")).click();
			logger.info("SMS SELECTED");
			page.waitForTimeout(5000);
			iframe.locator(SearchFilter).type("suraj Singh");// Using Type
//			iframe.locator(SearchFilter).fill("suraj Singh");//
			page.waitForTimeout(5000);

			if (iframe.locator(NOconversationStartmessasge).isVisible()) {
				iframe.locator(Message_start_convers_buttn).click();
				logger.info("Verify Start Conversation Tab");
				page.waitForCondition(() -> iframe.locator(StartconversatationFirstname).isVisible());
				iframe.locator(StartconversatationFirstname).fill(firstname);
				logger.info("INSERT FIRST NAME" + ":" + firstname);
				iframe.locator(Startconversatationlastname).fill(lastname);
				logger.info("INSERT LAST NAME" + ":" + lastname);
				page.waitForTimeout(3000);
				iframe.locator(Countryoptionbtn).click();
				iframe.locator(CountryName("United States")).click();
				logger.info("SELECT COUNTRY :-" + "United States");
				logger.info(Message_Filter_Icon);
				iframe.locator(StartconMobileno).fill(number);
				logger.info("Number:-" + number);
				page.waitForTimeout(2000);
				iframe.locator(StartConverSMS_Whatsapp_filterbuttn).click();
				page.waitForTimeout(2000);
				iframe.locator(ConversationStartbtn).click();
				if (iframe.locator(StartconversationBtn).isVisible()) {
					try {
						iframe.locator(StartconversationBtn).click();
						logger.info("START CONVERSATION");
						flags.add(true);

					} catch (ElementNotInteractableException e) {
						logger.info("elemnt is not clickable right now");
						e.printStackTrace();
						flags.add(true);
					}
				} else {
					logger.info("Element not found");
				}
			}
			page.waitForTimeout(3000);
			iframe.locator(ChannalList).first().click();
			page.waitForTimeout(3000);
			iframe.locator(MessageAttachment_btn).setInputFiles(Paths.get(AttachmentPath));
			logger.info("File Attached");
			iframe.locator(MessageSendBtn).click();
			logger.info("Attachement Send");
			page.waitForTimeout(5000);
		}

		return true;

	}

	public boolean VerifyConversationMessage_RoDetails() throws Exception {
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);

		FrameLocator iframe = page.frameLocator(messageIframe);

		HP.clickOn_RepairOrder_Header();
		OLP.addRepairOrder();
		page.waitForTimeout(5000);

		HP.navigateToMessageScreen_Order();

		page.waitForTimeout(5000);

		List<Boolean> flags = new ArrayList<>();
		if (!isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true) {
			return false;
		}
		iframe.locator(filterButton("Whatsapp")).click();
		iframe.locator(filterButton("SMS")).click();

		logger.info("Filters are not Visible");

		iframe.locator(ChannalList).first().click();

		iframe.locator(Messagechatfield).fill("DemoTestMessage");

		iframe.locator(MessageSendBtn).click();
		page.waitForTimeout(3000);
		iframe.locator(SendOriginal_btn).click();
		page.waitForTimeout(5000);
		return true;
	}

}
