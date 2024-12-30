package com.truvideo.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.mail.SendFailedException;
import org.openqa.selenium.ElementNotInteractableException;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
	private String message_profile_input2 = "input[id='mat-input-3']";
	private String message_profile_save_btn = "div.mat-mdc-dialog-actions button span:nth-child(3)";
	private String SMS_channel_icon = ".channels-list-item__status-phone.ng-star-inserted mat-icon";
	private String list_channelowner = ".list-all div p.channels-list-item__advisor";
	private String message_profile_user = ".profile__user-info  p.profile__user-info-title";

	private String Messagechatfield = "#mat-input-1";
	private String channelownername = "div.chat-header__phone p:nth-child(4)";

	private String SendOriginal_btn = ".mdc-button.mdc-button--outlined.mat-mdc-outlined-button span.mat-mdc-focus-indicator";
	private String MessageSendBtn = ".chat-input__options button:nth-child(2) mat-icon";

	private String Converstiontitlename = ".chat-header__main p.chat-header__title";
	private String Countryoptionbtn = ".mat-mdc-form-field.prefix-form-field ";
	private String StartconMobileno = "ngx-material-intl-tel-input mat-form-field:nth-child(2) input";
	private String StartConverSMS_Whatsapp_filterbuttn = "form mat-form-field:nth-child(4) ";
	private String ConversationInfo = "#first-content .info-container__content__title";
	private String conversationTextlabel = ".chat-header__main p.chat-header__title";
	private String ConversationInfobn = ".chat-header__drop-down button span.mat-mdc-button-touch-target";
	private String Message_start_convers_buttn = "button.profile__action-fab > span.mat-mdc-button-persistent-ripple";
	private String StartconversatationFirstname = "#mat-input-1";
	private String Startconversatationlastname = "#mat-input-2";
	private String StartconversationBtn = ".chat-input__button span.mdc-button__label";

	private String SearchFilter = "#mat-input-0";
	private String NOconversationStartmessasge = ".chat-empty-container.ng-star-inserted h1";
	private String Message_Filter_Icon = "//div[@class='profile__actions']//button//span[3]";
	private String ConversationStartbtn = "#mat-select-0-panel:has-text('SMS')";
	private String ChannalList = ".channels-list__section.list-all .ng-star-inserted .channels-list-item";
	private String MessageAttachment_btn = "button.mdc-icon-button.mat-mdc-icon-button input[type='file']";
	private String AttachmentPath = "src/main/resources/Data/image/testimage.png";

	public boolean VerifyAll_Elements() {
		logger.info("Verify Visible Elements");
		FrameLocator iframes = page.frameLocator(messageIframe);
		page.waitForTimeout(5000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		// Frame for Message
		page.waitForTimeout(4000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		if (iframes.locator(createIcon).isVisible() && iframes.locator(Message_Search_conversation).isVisible()
				&& iframes.locator(Message_Search_icon).isVisible()
				&& iframes.locator(Conversation_header).isVisible()) {

			logger.info("Elements are verifed and Visible");
			return true;
		} else {
			logger.info("Elements are not visibles");
			return false;
		}
	}

	private String messageownername = ".chat-header__phone p:nth-child(4)";
	private String MessageProfileName = ".profile__user-info p:nth-child(1)";
	private String loginusername = "li.account-nav a span span:nth-child(3)";

	public boolean Verify_Profile_setting_button(String ProfileName) {
		page.reload();
		logger.info("Verify Message Profile Settings");
		List<Boolean> flags = new ArrayList<>();
		FrameLocator iframe = page.frameLocator(messageIframe);
		String MessageprofileName = iframe.locator(MessageProfileName).innerText().toLowerCase();
		String MessageOwneraName = iframe.locator(messageownername).innerText().toLowerCase();
		page.waitForCondition(() -> iframe.locator(message_profile).isVisible());
//			if (MessageprofileName.toLowerCase().equals(MessageOwneraName)) {
//				logger.info("Profile After changes Matched");
//			} else {
//				logger.info("Profile name not changed");
//			}

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
			// page.reload(); // We dont need this reload,This is the issue right now
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

		if (iframe.locator(message_profile).isVisible()) {
			page.waitForTimeout(3000);
			iframe.locator(message_profile).click();
			logger.info("Message profile clicked");
			page.waitForTimeout(5000);
			Locator Loginuserlabel = page.locator(loginusername);
			String Loginuser = Loginuserlabel.innerText();
			System.out.println(Loginuser);
			page.keyboard().press("Control+A");
			page.waitForTimeout(5000);
			iframe.locator(message_profile_input2).type(Loginuser);
			page.waitForTimeout(1500);
			iframe.locator(message_profile_save_btn).click();
			logger.info("Clicked on save button");
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
			logger.info(storeusername + "The Channel owner is same user who is login--" + messageusername);
			return true;
		} else {
			logger.info(storeusername + "The Channel owner is not match with user who is login--" + messageusername);
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

		for (String names : Advisorname) {
			if (!names.toLowerCase().contains(MessOwnername)) {
				iframe.locator(list_channelowner(names)).first().click();
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

	private String firstname = "Automation";
	private String lastname = "Name";

	private String StartConFilter(String filter) {
		return "#mat-select-0-panel mat-option span:has-text('" + filter + "')";
	}

	private String CountryName(String countryname) {

		return ".mdc-list-item__primary-text .country-option  div:nth-child(2):has-text('" + countryname + "')";
	}

	private String Conversationinfo = ".chat-header__drop-down button";

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
			logger.info("ENTERED FIRSTNAME :-" + firstname);
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
			page.waitForTimeout(4000);
			if (!iframe.locator(ConversationInfo).isVisible()) {
				iframe.locator(Conversationinfo).click();
			}
			String conversinfoname = iframe.locator(ConversationInfo).innerText().toLowerCase();
			String converstextlabelname = iframe.locator(conversationTextlabel).innerText().toLowerCase();
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
				page.waitForTimeout(3000);
				// iframe.locator(StartconMobileno).click();
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
		OLP.addRepairOrder("Existing");
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

	private String Chat(int Value) {
		return ".channels-list__section.list-all ngx-channels-list-item:nth-child(" + Value
				+ ") div.channels-list-item__main";
	}

	private String MarkChat(int Value) {
		return ".channels-list__section.list-all ngx-channels-list-item:nth-child(" + Value
				+ ") span.channels-list-item__unreads";
	}

	private String MessageReDotNotification = "div.channels-list-item__unreads-container.ng-star-inserted span";
	private String Messagebadge = "span#my-service-message span";
	private String Messagebandagtotalcount = "#all-service-message a span";
	private String Markbuttn = "div.info-container__content__actions.ng-star-inserted";
	private String Readmark = "span.info-container__content__actions__unreads";
	private String MarkedChat = ".channels-list__section.list-all ngx-channels-list-item span.channels-list-item__unreads";
	
	public  boolean VerifyBadgecountfunctionality() throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		page.waitForTimeout(10000);
		
		if (!page.locator(Messagebadge).isVisible()) {

			logger.info("NO UNREAD MESSAGES ARE PRESENT");
			if (isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true && !isFilterApplied("SMS") == true
					&& !isFilterApplied("Unread") == true) {
				iframe.locator(filterButton("Whatsapp")).click();

				for (int i = 1; i < 5; i++) {

					iframe.locator(Chat(i)).click();
					page.waitForTimeout(5000);
					String value = iframe.locator(Markbuttn).innerText();
					System.out.println(value);
					if (value.contains("Mark as unread")) {
						iframe.locator(Readmark).click();
						page.waitForTimeout(1500);
						logger.info("Mark as read");
					}
				}
				if (!page.locator(Messagebadge).isVisible()) {
					logger.info("Badge count is not updating");
					iframe.locator(filterButton("Unread")).click();
					page.waitForTimeout(5000);
					int Markedcount = iframe.locator(MarkedChat).count();
					System.out.println(Markedcount);
					page.reload();
					page.waitForTimeout(5000);
					String afterrefreshcount = page.locator(Messagebadge).innerText();
					int initialcount = Integer.parseInt(afterrefreshcount);
					System.out.println(initialcount);
					if (Markedcount == initialcount) {
						logger.info("Badge count is increasing correctly after Refresh the page");
						throw new SendFailedException("Badge count increase but after refresh");
					} else {
						throw new SendFailedException("Badge count is not incresing correctly");
					}

				} else {
                    logger.info("Badge count is updating");
					iframe.locator(filterButton("Unread")).click();
					int Markedcount = iframe.locator(MarkedChat).count();
					page.waitForTimeout(5000);
					String afterrefreshcount = page.locator(Messagebadge).innerText();
					int initialcount = Integer.parseInt(afterrefreshcount);

					if (Markedcount == initialcount) {
						logger.info("Badge count is increasing correctly");
					} else {
						throw new SendFailedException("Badge count is not incresing correctly");
					}
				}

			}

		}
		iframe.locator(filterButton("Whatsapp")).click();
		String Count = page.locator(Messagebadge).innerText();
		System.out.println(Count);
		int initialcount = Integer.parseInt(Count);
		for (int j = 1; j <= (initialcount + initialcount); j++) {
			logger.info("enter in for loop");

			iframe.locator(Chat(j)).click();
			logger.info("click on chat no." + " :- " + " " + j);
			String value = iframe.locator(Markbuttn).innerText();
			if (value.contains("Mark as unread")) {
				iframe.locator(Readmark).click();
				logger.info("Chat is Marked");
			}

			else if (value.contains("Mark as read")) {
				iframe.locator(Readmark).click();
				logger.info("Chat is Marked");
			} else {
				logger.info("Mark button is not visible on page");
			}
		}

		page.waitForTimeout(5000);
		iframe.locator(filterButton("Unread")).click();
		page.waitForTimeout(5000);
		int Markedcount = iframe.locator(MarkedChat).count();
		System.out.println(Markedcount);
		String count = page.locator(Messagebadge).innerText();
		int Finalcount = Integer.parseInt(count);
		if (Finalcount == Markedcount) {
			logger.info("Badge count is updating properly");
		} else if (Finalcount != Markedcount) {
			page.reload();
			String Afterrefreshcount = page.locator(Messagebadge).innerText();
			int RefreshFinalcount = Integer.parseInt(Afterrefreshcount);
			System.out.println(RefreshFinalcount);
			logger.info("Refresh the page");
			page.waitForTimeout(5000);
			if (Markedcount == RefreshFinalcount) {
				throw new SendFailedException("To update badge count page need to refresh");
			} else {
				throw new SendFailedException("Badge count is not updating after even refresh the page");
			}
		}

		return true;
	}
	

}


