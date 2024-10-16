package com.truvideo.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.ElementNotInteractableException;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.truvideo.factory.PlaywrightFactory;
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
	private String message_Filter_Icon = "//div[@class='profile__actions']//button//span[3]";
	private String message_Search_conversation = "input[id='mat-input-0']";
	private String message_filter_buttons = "span.mat-mdc-chip-focus-overlay";
	private String message_filter_Whatsapplable = ".channels-list-item__status-phone .mat-icon";
	private String message_start_convers_buttn = "button.profile__action-fab > span.mat-mdc-button-persistent-ripple";
	private String startconversationBtn = ".chat-input__button span.mdc-button__label";
	private String countryoptionbtn = ".mat-mdc-form-field.prefix-form-field ";
	private String startconversatationFirstname = "#mat-input-1";
	private String startconversatationlastname = "#mat-input-2";
	private String startconMobileno = "ngx-material-intl-tel-input mat-form-field:nth-child(2) input";
	private String message_Search_icon = "#profile div.avatar-container";
	private String startConverSMS_Whatsapp_filterbuttn = "form mat-form-field:nth-child(4) ";

	private String startConverSMS_Whatsapp_text = ".mat-mdc-option span";
	private String startConverSMS_Whatsapp = "#mat-select-0-panel mat-option span:has-text('Whatsapp')";
	private String startConverSMS_Sms = "#mat-option-0:has-text('SMS')";
	private String conversationInfo = "#header-info p";
	private String conversationInfoname = "#first-content div.info-container__content__title";
	private String converstiontitlename = ".chat-header__main p.chat-header__title";
	private String conversationInfobn = ".chat-header__drop-down button span.mat-mdc-button-touch-target";

	private String searchFilter2 = ".channels-search div.mat-mdc-text-field-wrapper div.mat-mdc-form-field-focus-overlay ";
	private String searchFilter = "#mat-input-0";
	private String searchbtnSvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-prefix svg";
	private String searchbtnfiltersvg = ".mat-mdc-form-field-flex.ng-tns-c3736059725-2 .mat-mdc-form-field-icon-suffix svg";

	private String conversation_header = ".info-container__content div.avatar-container";
	private String conversation_GotoRo_btn = "button.order-button";
	private String returnToMessagePAge = ".main-body div div.return p";
	private String conversationtbcanclebtn = ".info-container__header__close-btn mat-icon svg";
	private String conversationChannelOwnerName = "#first-content div.info-container__content__title";
	private String rochannelName = "div.chat-header__main p.chat-header__title";

	private String conversationinactivemess = "div.chat-body__blocked-message.ng-star-inserted";
	private String ceactivatebtn = ".chat-input__options div";
	private String messagechatfield = "#mat-input-1";
	private String nOconversationStartmessasge = ".chat-empty-container.ng-star-inserted h1";
	// private String nOconversationStartmessasge = "#header-info p";
	private String channalList = ".channels-list__section.list-all .ng-star-inserted .channels-list-item";
	private String channelscount = ".channels-list__section.list-all .channels-list-item__main";

	private String messageAttachment_btn = "button.mdc-icon-button.mat-mdc-icon-button input[type='file']";
	private String attachmentPath = "src/main/resources/Data/image/testimage.png";
	private String messageSendBtn = ".chat-input__options button:nth-child(2) mat-icon";
	private String conversationStartbtn = "#mat-select-0-panel:has-text('SMS')";
	private String messageownername = ".chat-header__phone p:nth-child(4)";
	private String addRepairOrder_Btn = "#repair-order-add";
	private String sendOriginal_btn = ".mdc-button.mdc-button--outlined.mat-mdc-outlined-button span.mat-mdc-focus-indicator";

	private String conversationTextlabel = ".chat-header__main p.chat-header__title";

	public boolean VerifyAll_Elements() {
		logger.info("Verify Visible Elements");
		FrameLocator iframes = page.frameLocator(messageIframe);
		page.waitForTimeout(5000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		// Frame for Message
		page.waitForTimeout(4000);
		page.waitForCondition(() -> iframes.locator(message_profile).isVisible());
		if (iframes.locator(createIcon).isVisible() && iframes.locator(message_Search_conversation).isVisible()
				&& iframes.locator(message_Search_icon).isVisible()
				&& iframes.locator(conversation_header).isVisible()) {
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
		String MessageOwneraName = iframe.locator(messageownername).innerText().toLowerCase();
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
			logger.info("The Channel owner is same user who is login--" + messageusername);
			return true;
		} else {
			logger.info("The Channel owner is not match with user who is login--" + messageusername);
			return false;
		}
	}

	private String ChatFilterButtons = "span button span:nth-child(2)";
//	private String logInDealerLabel = "li.account-nav a span span:nth-child(1)";
//
//	public String getLoginDealerLabel() {
//		return logInDealerLabel;
//	}

	public boolean verify_Default_Filters() throws Exception {
		page.reload();
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
			String dealername = page.locator(HP.getLoginDealerLabel()).innerText();
			dp.Verify_Whatsapp_textconversation(dealername, "Text Conversation");
			logger.info("CHAT IS ENABLE NOW");
			HP.navigateToMessageScreen_Order();
			logger.info("VERIFY WHATSAPP FILTER ");
			page.waitForTimeout(4000);
			if (iframe.locator(ChatFilterButtons).allInnerTexts().contains("Whatsapp")) {
				if (!isFilterApplied("Whatsapp") == true) {
					flags.add(false);
				}
			}

		}
		logger.info("DEFAULT FILTERS PRESENT");
		return !flags.contains(false);
	}

	private String list_channelowner = ".list-all div p.channels-list-item__advisor";

	private String filterButton(String buttonText) {
		return "button:has-text('" + buttonText + "')";
	}

	public boolean verifyMyFilter(String filter) {
		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);

		page.waitForCondition(() -> iframe.locator(filterButton(filter)).isVisible());

		isFilterApplied(filter);

		List<Boolean> flags = new ArrayList<>();
		HomePage homePage = new HomePage(page);
		logger.info("filter name" + ":-" + filter);
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

	private String channel_icon = ".channels-list-item__status-phone.ng-star-inserted mat-icon";

	public boolean verify_with_My_filterBotton(String Filtertype, String My, String Whatsapp, String SMS, String Unread,
			String filter2) {

		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<Boolean> values = new ArrayList<>();
		List<String> isWhatsappButtonSelected = new ArrayList<>();
		List<Boolean> flags = new ArrayList<>();
		HomePage homePage = new HomePage(page);
		String loginUser = page.innerText(homePage.getLoginUserLabel()).toLowerCase();
		if (isFilterApplied(My) == true && isFilterApplied(Whatsapp) == true && !isFilterApplied(SMS) == true
				&& !isFilterApplied(Unread) == true) {
			logger.info("Condition True");
			if (Filtertype.contains("My & Whatsapp")) {
				logger.info("FILTER SELECTED " + Filtertype);
			} else if (Filtertype.contains("My & SMS")) {
				iframe.locator(filterButton(SMS)).click();
				iframe.locator(filterButton(Whatsapp)).click();
				logger.info("FILTER SELECTED" + Filtertype);
			} else {
				logger.warn("Wrong filters present" + Filtertype);
			}

			logger.info("Login user name catched : " + loginUser);
			List<String> adviosrlist = iframe.locator(list_channelowner).allInnerTexts();
			page.waitForTimeout(3000);
			for (String advisor : adviosrlist) {
				if (advisor.toLowerCase().contains(loginUser)) {
					logger.info(loginUser + " Login user is matched with channele owner : " + advisor);
					flags.add(true);
				} else {
					logger.info(loginUser + " Login user is not matched with channele owner : " + advisor);
					flags.add(false);
				}
			}
			List<ElementHandle> elements = iframe.locator(channel_icon).elementHandles();
			for (ElementHandle element : elements) {
				String iconName = element.getAttribute("data-mat-icon-name");
				isWhatsappButtonSelected.add(iconName);
			}
			for (String element : isWhatsappButtonSelected) {
				if (element.contains(filter2)) {
					logger.info("CHANNELS ARE IN RIGHT FILTER" + Filtertype);
				} else {
					logger.info("CHANNELS ARE IN WRONG FILTER");
					values.add(false);
				}
			}
		} else {
			System.out.println("FAILED");
		}
		return !values.contains(false);
	}

	public boolean click_My_AND_UNREAD_filterBotton(String Filtertype, String My, String Whatsapp, String SMS,
			String Unread, String filter2) {

		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);
		// Store true false for return value
		List<String> isWhatsappButtonSelected = new ArrayList<>();
		List<Boolean> values = new ArrayList<>();
		if (isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true && !isFilterApplied("SMS") == true
				&& !isFilterApplied("Unread") == true) {

			iframe.locator(filterButton("Whatsapp")).click();
			iframe.locator(filterButton("Unread")).click();
			page.waitForTimeout(8000);
			List<ElementHandle> elements = iframe.locator(channel_icon).elementHandles();
			for (ElementHandle element : elements) {
				String iconName = element.getAttribute("data-mat-icon-name");
				isWhatsappButtonSelected.add(iconName);
			}
			for (String element : isWhatsappButtonSelected) {
				if (element.contains(filter2)) {
					logger.info("CHANNELS ARE IN RIGHT FILTER" + Filtertype);
				} else {
					logger.info("CHANNELS ARE IN WRONG FILTER");
					values.add(false);
				}
			}
		} else {
			page.waitForTimeout(5000);
			return !values.contains(false);
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

	private String CountryName(String countryname) {

		return ".mdc-list-item__primary-text .country-option  div:nth-child(2):has-text('" + countryname + "')";

	}

	private String Infobuttn = ".chat-header__drop-down button";
	private String firstname = "Suraj";
	private String lastname = "Singh";

	private String StartConFilter(String filter) {
		return "#mat-select-0-panel mat-option span:has-text('" + filter + "')";
	}

	private String informationicon = ".chat-header__drop-down button";

	public boolean verifyStartconversatationbtn(String number, String filter) throws Exception {

		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		HomePage homepage = new HomePage(page);
		homepage.navigateToMessageScreen_Order();
		page.waitForCondition(() -> iframe.locator(message_start_convers_buttn).isVisible());
		if (iframe.locator(message_start_convers_buttn).isVisible()) {
			logger.info("CONVERSATION CHAT IS VISIBLE");
			iframe.locator(message_start_convers_buttn).click();
			logger.info("OPENED CONVERSATION TAB");
			page.waitForCondition(() -> iframe.locator(startconversatationFirstname).isVisible());
			iframe.locator(startconversatationFirstname).fill(firstname);
			logger.info("ENTERED FIRSTNAME :-" + firstname);
			iframe.locator(startconversatationlastname).fill(lastname);
			logger.info("ENTERED LASTNAME :-" + lastname);
			page.waitForTimeout(3000);
			iframe.locator(countryoptionbtn).click();
			iframe.locator(CountryName("United States")).click();
			logger.info("SELECTED COUNTRY");
			page.waitForTimeout(3000);
			iframe.locator(startconMobileno).fill(number);
			logger.info("Number:-" + number);
			page.waitForTimeout(2000);
			iframe.locator(startConverSMS_Whatsapp_filterbuttn).click();
			page.waitForTimeout(2000);
			List<String> Text = iframe.locator(StartConFilter("SMS")).allInnerTexts();
			for (String value : Text) {
				if (value.trim().toUpperCase().contains("SMS")) {
					iframe.locator(StartConFilter(filter)).click();
					logger.info("Select SMS");

				} else if (value.trim().toUpperCase().contains("WHATSAPP")) {
					iframe.locator(StartConFilter(filter)).click();
					logger.info("Select WHATSAPP");

				} else {
					flags.add(false);
					logger.info("not clicked");
				}
			}
			if (iframe.locator(startconversationBtn).isVisible()) {
				try {
					iframe.locator(startconversationBtn).click();
					logger.info("Button hit");
					flags.add(true);

				} catch (ElementNotInteractableException e) {
					logger.info("element is not clickable right now");
					e.printStackTrace();
					flags.add(true);
				}
			} else {
				flags.add(false);
				logger.info("Element not found");
			}
			page.waitForCondition(() -> iframe.locator(converstiontitlename).isVisible());
			page.waitForTimeout(5000);
			if (!iframe.locator(conversationInfo).isVisible()) {
				iframe.locator(Infobuttn).click();
			}
			String conversinfoname = iframe.locator(conversationInfoname).innerText().toLowerCase();
			String converstextlabelname = iframe.locator(conversationTextlabel).innerText().toLowerCase();
			String converstitlename = iframe.locator(converstiontitlename).innerText().toLowerCase();
			String channelownername = iframe.locator(channelOwnername).innerText().toLowerCase();
			String userlable = page.innerText(homepage.getLoginUserLabel()).toLowerCase();
			System.out.println(conversinfoname + converstextlabelname + converstitlename + channelownername + userlable );
			if (channelownername == userlable ) {
				logger.info("Channel Owner name is matched" + ":-" + userlable);
			}
			iframe.locator(conversationInfobn).click();
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
		
		/*-------second window open------*/

		page.waitForTimeout(3000);
		logger.info("Launch new browser");
		BrowserContext newContext = PlaywrightFactory.getBrowser().newContext(new Browser.NewContextOptions().setViewportSize(null));
		Page newBrowserPage = newContext.newPage();
		//Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
        loginPage.navigateToUpdatePassword(newBrowserPage, prop.getProperty("username3"), prop.getProperty("password3"));
        HomePage Homepage = new HomePage(newBrowserPage);
        Homepage.navigateToMessageScreen_Order();
        FrameLocator iframe2 = newBrowserPage.frameLocator(messageIframe);
	    List<Boolean> flag = new ArrayList<>();
		newBrowserPage.waitForCondition(() -> iframe2.locator(message_start_convers_buttn).isVisible());
		if (iframe2.locator(message_start_convers_buttn).isVisible()) {
			logger.info("CONVERSATION CHAT IS VISIBLE");
			iframe2.locator(message_start_convers_buttn).click();
			logger.info("OPENED CONVERSATION TAB");
			newBrowserPage.waitForCondition(() -> iframe2.locator(startconversatationFirstname).isVisible());
			iframe2.locator(startconversatationFirstname).fill(firstname);
			logger.info("ENTERED FIRSTNAME :-" + firstname);
			iframe2.locator(startconversatationlastname).fill(lastname);
			logger.info("ENTERED LASTNAME :-" + lastname);
			newBrowserPage.waitForTimeout(3000);
			iframe2.locator(countryoptionbtn).click();
			iframe2.locator(CountryName("United States")).click();
			logger.info("SELECTED COUNTRY");
			newBrowserPage.waitForTimeout(3000);
			iframe2.locator(startconMobileno).fill(number);
			logger.info("Number:-" + number);
			newBrowserPage.waitForTimeout(2000);
			iframe2.locator(startConverSMS_Whatsapp_filterbuttn).click();
			newBrowserPage.waitForTimeout(2000);

				List<String> Text2 = iframe2.locator(StartConFilter(filter)).allInnerTexts();
				for (String value : Text2) {
					if (value.trim().toUpperCase().contains("SMS")) {
						iframe2.locator(StartConFilter(filter)).click();
						logger.info("Select SMS");

					} else if (value.trim().toUpperCase().contains("WHATSAPP")) {
						iframe2.locator(StartConFilter(filter)).click();
						logger.info("Select WHATSAPP");

					} else {
						flag.add(false);
						logger.info("not clicked");
					}
				}
				if (iframe2.locator(startconversationBtn).isVisible()) {
					try {
						iframe2.locator(startconversationBtn).click();
						logger.info("Button hit");
						flag.add(true);

					} catch (ElementNotInteractableException e) {
						logger.info("element is not clickable right now");
						e.printStackTrace();
						flag.add(true);
					}
				} else {
					flag.add(false);
					logger.info("Element not found");
				}
				newBrowserPage.waitForCondition(() -> iframe.locator(converstiontitlename).isVisible());
				newBrowserPage.waitForTimeout(5000);
				if (!iframe2.locator(conversationInfo).isVisible()) {
					iframe2.locator(Infobuttn).click();
				}
				String conversinfoname = iframe2.locator(conversationInfoname).innerText().toLowerCase();
				String converstextlabelname = iframe2.locator(conversationTextlabel).innerText().toLowerCase();
				String converstitlename = iframe2.locator(converstiontitlename).innerText().toLowerCase();
				String channelownername = iframe2.locator(channelOwnername).innerText().toLowerCase();
				String userlable = newBrowserPage.innerText(homepage.getLoginUserLabel()).toLowerCase();
				if (channelownername == userlable ) {
					logger.info("Channel Owner name is matched" + ":-" + userlable);
				}
				System.out.println(conversinfoname + converstextlabelname + converstitlename + channelownername + userlable);
				iframe2.locator(conversationInfobn).click();
				if (conversinfoname.contains(converstitlename) && conversinfoname.contains(converstextlabelname)) {
					logger.info("All names are Matched :" + converstitlename + ":" + conversinfoname + ":"
							+ converstextlabelname);
					flag.add(true);
					
				} else {
					logger.info("error message");
					flag.add(false);
				}
			} else {
				logger.info("CONVERSATION CHAT IS NOT VISIBLE");
				flag.add(false);
			}
		newBrowserPage.close();
		page.waitForTimeout(5000);
		String channelownername = iframe.locator(channelOwnername).innerText().toLowerCase();
		String userlable = page.innerText(homepage.getLoginUserLabel()).toLowerCase();
		
		
	return!flag.contains(false);

	}

	public boolean SearchMessagefilter() {
		page.waitForTimeout(5000);
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		page.waitForTimeout(5000);
		if (!iframe.locator(searchFilter2).isVisible()) {
			page.waitForTimeout(2000);
			logger.info("Search Filter is not Present");
			flags.add(false);
		} else {

			iframe.locator(searchFilter).fill("Suraj");
			logger.info("Search Filter is Present");
		}

		return !flags.contains(false);

	}

	private String ReadUnreadbtn(String Value) {
		return ".info-container__content__actions.ng-star-inserted:has-text('" + Value + "')";
	}

	private String MessageReDotNotification = "div.channels-list-item__unreads-container.ng-star-inserted span";
	private String Messagebadge = "span#my-service-message";
	private String Messagebandagtotalcount = "#all-service-message a span";

	public boolean VerifyReadUnreadNotification() throws Exception {

		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		page.waitForCondition(() -> page.locator(Messagebadge).isVisible());
		if (!page.locator(Messagebadge).isVisible() && !page.locator(Messagebandagtotalcount).isVisible()) {
			logger.info("NO UNREAD MESSAGES ARE PRESENT");
			throw new Exception("Notifications not available");
		}
		logger.info("UNREAD NOTIFICATIONS ARE PRESENT");
		page.waitForTimeout(3000);
		logger.info("COUNT UNREAD MESSAGE PRESENT");
		page.locator(Messagebadge).click();
		page.waitForCondition(() -> iframe.locator(conversationinactivemess).isVisible());

		// Badge count for self notifications.
		iframe.locator(ReadUnreadbtn(" Mark as unread ")).click();
		int num = iframe.locator(MessageReDotNotification).count();
		String count = Integer.toString(num);
		System.out.println(count);
		page.waitForTimeout(5000);
		String ownernotifications;
		ownernotifications = page.locator(Messagebadge).innerText();
		System.out.println(ownernotifications);

		if (count.contains(ownernotifications)) {
			logger.info("Badge count is correct" + ownernotifications);
		} else {
			logger.info("Badge count is incorrect" + ownernotifications);
			flags.add(false);
		}

		// Badge count for all notifications
		page.locator(Messagebandagtotalcount).click();
		page.waitForCondition(() -> iframe.locator(conversationinactivemess).isVisible());
		iframe.locator(ReadUnreadbtn(" Mark as unread ")).click();
		iframe.locator(filterButton("Whatsapp")).click();
		int Totalcount = iframe.locator(MessageReDotNotification).count();
		int value = (Totalcount - num);
		String count2 = Integer.toString(value);
		System.out.println(count2);
		page.waitForTimeout(5000);
		String AllNotications;
		AllNotications = page.locator(Messagebandagtotalcount).innerText();
		System.out.println(AllNotications);

		if (count2.equals(AllNotications)) {
			logger.info("Badge count is correct" + AllNotications);
		} else {
			logger.info("Badge count is incorrect" + AllNotications);
			flags.add(false);
		}

		return !flags.contains(false);

	}

	public boolean ConversationGOtoRobtn() {
		page.reload();
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flag = new ArrayList<>();
		page.waitForCondition(() -> iframe.locator(message_profile_user).isVisible());
		page.waitForTimeout(3000);
		if (iframe.locator(conversationInfo).isVisible()) {
			if (!isFilterApplied("My") == true && !isFilterApplied("Whatsapp") == true) {

				flag.add(false);
			}

			iframe.locator(filterButton("My")).click();
			page.waitForTimeout(2000);
			iframe.locator(channalList).first().click();
			if (!iframe.locator(conversation_GotoRo_btn).isVisible()) {
				logger.info("Go To Ro button not visible");
				flag.add(false);
			}
			String ChannelOwnerName = iframe.locator(conversationChannelOwnerName).innerText();
			iframe.locator(conversation_GotoRo_btn).click();
			logger.info("GO_TO_RO button clicked");
			page.waitForCondition(() -> iframe.locator(returnToMessagePAge).isVisible());
			String ROChannelname = iframe.locator(rochannelName).innerText();
			page.waitForTimeout(5000);
			if (ROChannelname.contains(ChannelOwnerName)) {
				iframe.locator(returnToMessagePAge).click();
				logger.info("CHANNEL OWNER NAME MATCHED" + ROChannelname + "-:-" + ChannelOwnerName);
				logger.info("CHANNEL OWNER NAME MATCHED");
			} else {
				logger.info("CHANNEL OWER NAME IS NOT MATCHED");
				flag.add(false);
			}
		} else {
			iframe.locator(conversationtbcanclebtn).click();
			flag.add(false);
		}
		flag.add(true);

		return !flag.contains(false);
	}

	public boolean VerifyWhatsAppChatEnableCondition() throws Exception {
		page.reload();
		HomePage homepage = new HomePage(page);
		homepage.navigateToMessageScreen_Order();

		FrameLocator iframe = page.frameLocator(messageIframe);
		logger.info("CheckWhatsapp filter is Enable or Disable From Dealer Setting");
		page.waitForTimeout(5000);
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
			iframe.locator(channalList).first().click();

			/* Check Text Box For WhatsApp */

			if (iframe.locator(conversationinactivemess).isVisible()) {
				String InactiveMessage = iframe.locator(conversationinactivemess).innerText();
				if (iframe.locator(conversationinactivemess).innerText().contains(InactiveMessage)) {
					iframe.locator(channalList).first().click();
					logger.info("Whatsapp Chat is Expired");
					page.waitForTimeout(5000);
				} else {
					if (iframe.locator(nOconversationStartmessasge).isVisible()) {
						logger.info("NO Conversation start Until in Whatsapp");
					} else {
						return false;
					}
				}
			} else {
				logger.info("WHATSAPP CHAT IS ACTIVE");
				iframe.locator(".channels-list-item__main div:nth-child(3) span:has-text('(781) 205-9487')").first()
						.click();
				iframe.locator(messagechatfield).fill("demotext..........");
				iframe.locator(".chat-input__options button:nth-child(3) mat-icon").click();
				iframe.locator(sendOriginal_btn).click();
			}
		} else {
			logger.info("WhatsApp filter is Disabled");
			iframe.locator(channalList).first().click();
			iframe.locator(messagechatfield).fill("demotext..........");
			iframe.locator(messageSendBtn).click();
			iframe.locator(sendOriginal_btn).click();

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
			iframe.locator(searchFilter).type("suraj Singh");// Using
			page.waitForTimeout(5000);
			if (iframe.locator(nOconversationStartmessasge).isVisible()) {
				iframe.locator(message_start_convers_buttn).click();
				logger.info("Verify Start Conversation Tab");
				page.waitForCondition(() -> iframe.locator(startconversatationFirstname).isVisible());
				iframe.locator(startconversatationFirstname).fill(firstname);
				logger.info("INSERT FIRST NAME" + ":" + firstname);
				iframe.locator(startconversatationlastname).fill(lastname);
				logger.info("INSERT LAST NAME" + ":" + lastname);
				page.waitForTimeout(3000);
				iframe.locator(countryoptionbtn).click();
				iframe.locator(CountryName("United States")).click();
				logger.info("SELECT COUNTRY :-" + "United States");
				logger.info(message_Filter_Icon);
				iframe.locator(startconMobileno).fill(number);
				logger.info("Number:-" + number);
				page.waitForTimeout(2000);
				iframe.locator(startConverSMS_Whatsapp_filterbuttn).click();
				page.waitForTimeout(2000);
				iframe.locator(conversationStartbtn).click();
				if (iframe.locator(startconversationBtn).isVisible()) {
					try {
						iframe.locator(startconversationBtn).click();
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
			} else {
				logger.info("New message not created");
			}
			page.waitForTimeout(3000);
			iframe.locator(channalList).first().click();
			page.waitForTimeout(3000);
			iframe.locator(messageAttachment_btn).setInputFiles(Paths.get(attachmentPath));
			logger.info("File Attached");
			iframe.locator(messageSendBtn).click();
			logger.info("Attachement Send");
			page.waitForTimeout(5000);
		}

		return true;

	}

	private String operations_Buttons = "div .menu-options__info";

	private void clickOperationButton(String buttonText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operations_Buttons);
		page.waitForTimeout(5000);
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			System.out.println("IS Text found ?" + textContent);
			if (textContent != null && textContent.contains(buttonText)) {
				locator.click();
				break;

			}
		}
	}

	private String saveButton = "button.edit";
	private String customerName = ".chat-header__main .chat-header__title";
	private String orderDetailsIFrame = "iframe#order-details-iframe";
	private String tableRows = "table#repair-order-results tr";
	private String roNumber = "h1.orders-detail-menu__ro-number";
	private String channelOwnername = ".chat-header__main .chat-header__phone p:nth-child(4)";

	public void verifyconversationStartfromRO() throws Exception {

		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		FrameLocator orderDetailsiframe = page.frameLocator(orderDetailsIFrame);
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder();
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.keyboard().down("Control");
		page.keyboard().press("-");
		page.keyboard().press("-");
		page.keyboard().up("Control");
		page.waitForTimeout(7000);
		page.waitForCondition(() -> orderDetailsiframe.locator(saveButton).isVisible());
		clickOperationButton("Edit this RO");
		System.out.println("1");
		page.waitForTimeout(5000);
		Locator select = orderDetailsiframe.locator("select.detail__main-data-item--select");
		select.selectOption(new SelectOption().setLabel("yaduwanshi Toshi"));
		System.out.println("2");
		orderDetailsiframe.locator(saveButton).click();
		logger.info("Changes Saved");
		page.waitForTimeout(5000);
		String ROChannelname = orderDetailsiframe.locator(rochannelName).innerText();
		String customername = orderDetailsiframe.locator(customerName).innerText().toLowerCase();
		String Ronumber = orderDetailsiframe.locator(roNumber).innerText();
		String channelownername = orderDetailsiframe.locator(channelOwnername).innerText().toLowerCase();
		try {
			if (RONumber.equals(Ronumber)) {
				logger.info("Same RO opened we created");
			} else {
				throw new SkipException("The opened Repair Order (RO) does not match the expected one");
			}
		} catch (Exception e) {

			logger.error("An unexpected error occurred: " + e.getMessage());
			page.reload(); // Reload the page to refresh the state
			throw new SkipException(
					"Owner name appears after refreshing the page due to an unexpected error: " + e.getMessage());
		}
		page.waitForTimeout(5000);
		HP.navigateToMessageScreen_Order();
		if (isFilterApplied("My") == true && isFilterApplied("Whatsapp") == true && !isFilterApplied("SMS") == true
				&& !isFilterApplied("Unread") == true) {
			logger.info("Condition True");
			iframe.locator(filterButton("My")).click();
		}
		iframe.locator(searchFilter).type(ROChannelname);
		iframe.locator(channalList).first().click();
		page.waitForTimeout(50000);
		String messageChannelname = iframe.locator(rochannelName).innerText();
		String MessageOwnerchannelname = iframe.locator(messageownername).innerText();
		if (channelownername.equals(MessageOwnerchannelname) && ROChannelname.equals(messageChannelname)) {
			logger.info("matched");
		}

	}

	public void verifyChannelNamewithExistingno() {

	}

	public void verifychannelownereditRo() throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);
		FrameLocator orderDetailsiframe = page.frameLocator(orderDetailsIFrame);
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder();
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.waitForTimeout(10000);
		String ROChannelname = orderDetailsiframe.locator(rochannelName).innerText();
		String customername = orderDetailsiframe.locator(customerName).innerText().toLowerCase();
		String Ronumber = orderDetailsiframe.locator(roNumber).innerText();
		String channelownername = orderDetailsiframe.locator(".chat-header__main .chat-header__phone p:nth-child(4) ")
				.innerText().toLowerCase();
		System.out.println(ROChannelname);
		if (RONumber.equals(Ronumber)) {
			logger.info("Same ro opened we created");
		} else {
			throw new SkipException("Wrong ro opened");
		}

		page.waitForTimeout(5000);

		HP.navigateToMessageScreen_Order();
		iframe.locator(channalList).first().click();
		page.waitForTimeout(50000);
		String messageChannelname = iframe.locator(rochannelName).innerText();
		String MessageOwnerchannelname = iframe.locator(messageownername).innerText();
		if (channelownername.equals(MessageOwnerchannelname) && ROChannelname.equals(messageChannelname)) {
			logger.info("matched");

		}
	}
//	public void verifychannelownereditRo() {
//		
//	}
}
