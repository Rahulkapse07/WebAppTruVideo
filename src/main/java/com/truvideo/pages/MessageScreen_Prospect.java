package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.sun.tools.javac.util.Names;
import com.truvideo.utility.JavaUtility;

public class MessageScreen_Prospect extends JavaUtility {
	private Page page;

	public MessageScreen_Prospect(Page page) {
		this.page = page;
	}

	private String messageIframe = "#messages-iframe";
	private String Conversation_header1 = ".info-container__content #first-content";
	private String Conversation_header = ".info-container__content div.avatar-container";
	private String Message_Search_conversation = "input[id='mat-input-0']";
	private String Message_Search_icon = "#profile div.avatar-container";
	private String message_profile = "#profile .avatar-container";
	private String createIcon = "mat-icon.profile__action-fab-icon";
	// Check UI of message R/O
	private String Messageownername = ".chat-header__phone p:nth-child(4)";
	private String message_profile_input = "input[id='mat-input-2']";
	private String message_profile_save_btn = "div.mat-mdc-dialog-actions button span:nth-child(3)";
	private String SMS_channel_icon = ".channels-list-item__status-phone.ng-star-inserted mat-icon";
	private String list_channelowner = ".list-all div p.channels-list-item__advisor";
	private String message_profile_user = ".profile__user-info  p.profile__user-info-title";

	private String Messagechatfield = "#mat-input-1";
	private String channelownername = "div.chat-header__phone p:nth-child(4)";

	private String SendOriginal_btn = ".mdc-button.mdc-button--outlined.mat-mdc-outlined-button span.mat-mdc-focus-indicator";
	private String MessageSendBtn = ".chat-input__options button:nth-child(2) mat-icon";

	public boolean VerifyAll_Elements() {
		logger.info("Verify Visible Elements");
		FrameLocator iframes = page.frameLocator(messageIframe);
		page.waitForTimeout(5000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		// Frame for Message
		page.waitForTimeout(4000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		if (iframes.locator(createIcon).isVisible() && iframes.locator(Message_Search_conversation).isVisible()
				&& iframes.locator(Message_Search_icon).isVisible() && iframes.locator(Conversation_header).isVisible()){
		
			logger.info("Elements are verifed and Visible");
			return true;
		} else {
			logger.info("Elements are not visibles");
			return false;
		}
	}

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
		if (storeusername.trim().equals(messageusername.trim())) {
			logger.info("The Channel owner is same user who is login" + messageusername);
			return true;
		} else {
			logger.info("The Channel owner is not match with user who is login");
			return false;
		}
	}

	private String filterButton(String buttonText) {
		return "button:has-text('" + buttonText + "')";
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

	private boolean isFilterApplied(String buttonName) {
		FrameLocator iframe = page.frameLocator(messageIframe);
		String isMyButtonSelected = iframe.locator(filterButton(buttonName)).getAttribute("aria-selected");
		if (isMyButtonSelected.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyMyFilter() {
		FrameLocator iframe = page.frameLocator(messageIframe);

		page.waitForCondition(() -> iframe.locator(filterButton("My")).isVisible());

		isFilterApplied("My");

		List<Boolean> flags = new ArrayList<>();
		String loginUser = iframe.locator(message_profile_user).innerText().toLowerCase();
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
		if (!isMyButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			iframe.locator(filterButton("My")).click();
			logger.info("Click on My filter to select it");
			flags.add(false);
		}
		iframe.locator(filterButton("My")).click();
		logger.info("MY FILTER WORKING FINE");

		if (!iswhatsappButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			iframe.locator(filterButton("Whatsapp")).dblclick();
			logger.info("Click on My filter to select it");
			flags.add(false);
		}
		iframe.locator(filterButton("Whatsapp")).click();
		logger.info("WHATSAPP FILTER WORKING FINE");
		page.waitForTimeout(2000);
		if (isSmsButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			flags.add(false);
		}
		iframe.locator(filterButton("SMS")).click();
		logger.info("SMS FILTER WORKING FINE");
		page.waitForTimeout(2000);

		if (isunreadButtonSelected.equals("true")) {
			logger.info("My filter is not selected by default");
			flags.add(false);
			logger.info("Click on My filter to select it");
		}
		iframe.locator(filterButton("Unread")).click();
		logger.info("UNREAD FILTER WORKING FINE");
		page.waitForTimeout(2000);
		page.reload();

		return !flags.contains(false);

	}

	public boolean Verify_my_filterIsApplied() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		if (!isFilterApplied("My") == true) {
			flags.add(false);
		}
		if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
			if (isFilterApplied("Whatsapp") == true) {
				iframe.locator(filterButton("Whatsapp")).click();
			}
		} else {

			logger.info("WHATSAPP IS DISABLE");
		}
		page.waitForTimeout(5000);
		List<String> Names = iframe.locator(list_channelowner).allInnerTexts();
		String Channelownername = iframe.locator(message_profile_user).innerText().toLowerCase();
		for (String values : Names) {
			if (values.toLowerCase().contains(Channelownername)) {
				logger.info(values + "--Name Matched--" + Channelownername);
			} else {
				logger.info(values + "NOT MATCHED" + Channelownername);
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
		List<String> isWhatsappButtonSelected = new ArrayList<>();
		List<ElementHandle> elements = iframe.locator(SMS_channel_icon).elementHandles();

		for (ElementHandle element : elements) {
			String iconName = element.getAttribute("data-mat-icon-name");
			isWhatsappButtonSelected.add(iconName);

		}
		for (String element : isWhatsappButtonSelected) {
			if (element.contains("whatsapp")) {
				logger.info("CHANNELS ARE IN RIGHT FILTER");
			} else {
				logger.info("CHANNELS ARE IN WRONG FILTER");
				values.add(false);
			}
		}
		return !values.contains(false);

	}

	public boolean Verify_Sms_filterBotton() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();

		logger.info("VERIFY SMS FILTER");

		if (isFilterApplied("My") == true) {
			iframe.locator(filterButton("My")).click();
			logger.info("Remove My filter");

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
		// GET ATTRIBUTE DATA METHODE
		List<String> isWhatsappButtonSelected = new ArrayList<>();
		List<ElementHandle> elements = iframe.locator(SMS_channel_icon).elementHandles();

		for (ElementHandle element : elements) {
			String iconName = element.getAttribute("data-mat-icon-name");
			isWhatsappButtonSelected.add(iconName);

		}
		for (String element : isWhatsappButtonSelected) {
			if (element.contains("sms")) {
				logger.info("CHANNELS ARE IN RIGHT FILTER" + element);
			} else {
				logger.info("CHANNELS ARE IN WRONG FILTER");
				values.add(false);
			}
		}
		page.reload();
		return !values.contains(false);

	}

	private String list_channelowner(String OwnerName) {

		return ".list-all div p:has-text('" + OwnerName + "')";
	}

	public boolean Verify_channel_ownername() {

		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> values = new ArrayList<>();
		// Switch to SMS filter
		if (isFilterApplied("My") == true) {
			iframe.locator(filterButton("My")).click();
			logger.info("Remove My filter");

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

		}
		page.waitForTimeout(5000);
		List<String> Advisorname = iframe.locator(list_channelowner).allInnerTexts();
		String MessOwnername = iframe.locator(message_profile_user).innerText().toLowerCase();
		System.out.println(Advisorname);

		for (String names : Advisorname) {
			if (!names.toLowerCase().contains(MessOwnername)) {
				iframe.locator(list_channelowner(names)).click();
				String oldowner = iframe.locator(channelownername).innerText().toLowerCase();
				if (names.toLowerCase().contains(oldowner)) {
					logger.info("OLD OWNER NAME MATCED");
				} else {
					logger.info("OLD OWNER NAME NOT MATCED");
					values.add(false);
				}
				page.waitForTimeout(2000);
				logger.info("CHAT HAS BEEN SELECTED");
				break;
			}
		}
		iframe.locator(Messagechatfield).fill("test");
		iframe.locator(MessageSendBtn).click();
		iframe.locator(SendOriginal_btn).click();
		page.waitForTimeout(5000);
		String Newowner = iframe.locator(channelownername).innerText().toLowerCase();
		if (MessOwnername.toLowerCase().contains(Newowner)) {
			logger.info("NEW OWNER NAME MATCED");
		} else {
			logger.info("NEW OWNER NAME NOT MATCED");
			values.add(false);
		}
		return !values.contains(false);
	}

	public boolean Verify_Unread_filterBotton() {

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
}