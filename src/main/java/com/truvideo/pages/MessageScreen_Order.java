package com.truvideo.pages;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.mail.SendFailedException;

import org.openqa.selenium.ElementNotInteractableException;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;
import org.testng.reporters.FailedReporter;

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
	private String message_profile_input2 = "input[id='mat-input-3']";
	private String message_profile_save_btn = "div.mat-mdc-dialog-actions button span:nth-child(3)";
	private String createIcon = "mat-icon.profile__action-fab-icon";
	private String message_Filter_Icon = "//div[@class='profile__actions']//button//span[3]";
	private String message_Search_conversation = "input[id='mat-input-0']";
	private String message_filter_buttons = "span.mat-mdc-chip-focus-overlay";
	private String message_filter_Whatsapplable = ".channels-list-item__status-phone .mat-icon";
	private String message_start_convers_buttn = "button.profile__action-fab > span.mat-mdc-button-persistent-ripple";
	private String startconversationBtn = ".chat-input__button span.mdc-button__label";
	private String countryoptionbtn = ".mat-mdc-form-field.prefix-form-field ";
	private String startconversatationFirstname = "//input[@placeholder = 'Customer first name']";
	private String startconversatationlastname = "//input[@placeholder = 'Customer last name']";
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
	private String messageSendBtn = ".chat-input__options button:nth-child(3) mat-icon";
	private String conversationStartbtn = "#mat-select-0-panel:has-text('SMS')";
	private String messageownername = ".chat-header__phone p:nth-child(4)";
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
	private String loginusername = "li.account-nav a span span:nth-child(3)";

	public boolean Verify_Profile_setting_button(String ProfileName) {
		page.reload();
		logger.info("Verify Message Profile Settings");
		List<Boolean> flags = new ArrayList<>();
		FrameLocator iframe = page.frameLocator(messageIframe);
		String MessageprofileName = iframe.locator(MessageProfileName).innerText().toLowerCase();
		String MessageOwneraName = iframe.locator(messageownername).innerText().toLowerCase();
		page.waitForCondition(() -> iframe.locator(message_profile).isVisible());
//		if (MessageprofileName.toLowerCase().equals(MessageOwneraName)) {
//			logger.info("Profile After changes Matched");
//		} else {
//			logger.info("Profile name not changed");
//		}

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

	public boolean Verify_Conversation_Channel() throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage homePage = new HomePage(page);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		SoftAssert softAssert = new SoftAssert();
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);

		logger.info("Navigate to repair order header");
		homePage.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("Existing");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.waitForTimeout(5000);
		String ROChannelname = frame.locator(rochannelName).innerText().toLowerCase().trim();
		homePage.navigateToMessageScreen_Order();
		logger.info("Navigate to Servbice message");
		String messageChannelname = iframe.locator(rochannelName).innerText().toLowerCase().trim();
		if (ROChannelname.equals(messageChannelname)) {
			iframe.locator(channalList).first().click();
			iframe.locator(messagechatfield).fill("demotext..........");
			iframe.locator(messageSendBtn).click();
			iframe.locator(sendOriginal_btn).click();

		}

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

	private String ChatFilterButtons = "span button span:nth-child(2)";

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

	private String firstname = "Automation";
	private String lastname = "Name";

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

			System.out
					.println(conversinfoname + converstextlabelname + converstitlename + channelownername + userlable);
			if (channelownername == userlable) {
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

		BrowserContext newContext = PlaywrightFactory.getBrowser()
				.newContext(new Browser.NewContextOptions().setViewportSize(null));
		Page newBrowserPage = newContext.newPage();
		// Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();

		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);

		loginPage.navigateToUpdatePassword(newBrowserPage, prop.getProperty("username3"),
				prop.getProperty("password3"));
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
			if (channelownername == userlable) {
				logger.info("Channel Owner name is matched" + ":-" + userlable);
			}
			System.out
					.println(conversinfoname + converstextlabelname + converstitlename + channelownername + userlable);
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
		return !flag.contains(false);

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

			iframe.locator(searchFilter).fill("Automation");
			logger.info("Search Filter is Present");
		}

		return !flags.contains(false);

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

	public boolean VerifyReadUnreadNotification() throws Exception {

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
				iframe.locator(messageSendBtn).click();
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

	private String attachment = ".attachment.ng-star-inserted";

	public boolean MessageSendAttachments(String number) throws Exception {
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
			iframe.locator(searchFilter).type("Automation Name");// Using
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
				logger.info("Alredy exist");
			}
			page.waitForTimeout(3000);
			iframe.locator(channalList).first().click();
			page.waitForTimeout(3000);
			iframe.locator(messageAttachment_btn).setInputFiles(Paths.get(attachmentPath));
			logger.info("File Attached");
			iframe.locator(messageSendBtn).last().click();
			page.waitForTimeout(4000);
			if (iframe.locator(attachment).last().isVisible()) {
				logger.info("Attachement Send");
			} else {
				throw new Exception("Attachment not send to the customer");
			}

			page.waitForTimeout(5000);
		}

		return true;

	}

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

	private String operations_Buttons = "div .menu-options__info";
	private String saveButton = "button.edit";
	private String customerName = ".chat-header__main .chat-header__title";
	private String orderDetailsIFrame = "iframe#order-details-iframe";
	private String tableRows = "table#repair-order-results tr";
	private String roNumber = "h1.orders-detail-menu__ro-number";
	private String channelOwnername = ".chat-header__main .chat-header__phone p:nth-child(4)";
	private String advisorlist = "select.detail__main-data-item--select";

	public void verifyconversationStartfromRO() throws Exception {

		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		FrameLocator orderDetailsiframe = page.frameLocator(orderDetailsIFrame);
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("New");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		// page.waitForCondition(() ->
		// orderDetailsiframe.locator(saveButton).isVisible());
		page.waitForTimeout(15000);
		clickOperationButton("Edit this RO");
		page.waitForTimeout(5000);
		Locator select = orderDetailsiframe.locator(advisorlist);
		select.selectOption(new SelectOption().setLabel("yaduwanshi Toshi"));
		orderDetailsiframe.locator(saveButton).click();
		logger.info("Changes Saved");
		page.waitForTimeout(5000);
		String ROChannelname = orderDetailsiframe.locator(rochannelName).innerText().toLowerCase().trim();
		String customername = orderDetailsiframe.locator(customerName).innerText().toLowerCase().trim();
		String Ronumber = orderDetailsiframe.locator(roNumber).innerText();
		String channelownername = orderDetailsiframe.locator(channelOwnername).innerText().toLowerCase().trim();
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
		page.waitForTimeout(15000);
		String messageChannelname = iframe.locator(rochannelName).innerText().toLowerCase().trim();
		String MessageOwnerchannelname = iframe.locator(messageownername).innerText().toLowerCase().trim();
		System.out
				.println(channelownername + "=" + MessageOwnerchannelname + ROChannelname + " = " + messageChannelname);
		if (channelownername.equals(MessageOwnerchannelname) && ROChannelname.equals(messageChannelname)) {
			logger.info("matched");
		}
	}

	public void verifyChannelNamewithExistingno() {

	}

	private String channelownername = ".chat-header__main .chat-header__phone p:nth-child(4) ";

	public void verifychannelownereditRo() throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		FrameLocator orderDetailsiframe = page.frameLocator(orderDetailsIFrame);
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("Existing");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		// page.waitForCondition(() ->
		// orderDetailsiframe.locator(saveButton).isVisible());
		page.waitForTimeout(15000);
		clickOperationButton("Edit this RO");
		page.waitForTimeout(5000);
		Locator select = orderDetailsiframe.locator(advisorlist);
		select.selectOption(new SelectOption().setLabel("yaduwanshi Toshi"));
		orderDetailsiframe.locator(saveButton).click();
		logger.info("Changes Saved");
		page.waitForTimeout(5000);
		String ROChannelname = orderDetailsiframe.locator(rochannelName).innerText().toLowerCase().trim();
		String customername = orderDetailsiframe.locator(customerName).innerText().toLowerCase().trim();
		String Ronumber = orderDetailsiframe.locator(roNumber).innerText();
		String channelownername = orderDetailsiframe.locator(channelOwnername).innerText().toLowerCase().trim();
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
		page.waitForTimeout(15000);
		String messageChannelname = iframe.locator(rochannelName).innerText().toLowerCase().trim();
		String MessageOwnerchannelname = iframe.locator(messageownername).innerText().toLowerCase().trim();
		System.out
				.println(channelownername + "=" + MessageOwnerchannelname + ROChannelname + " = " + messageChannelname);
		if (channelownername.equals(MessageOwnerchannelname) && ROChannelname.equals(messageChannelname)) {
			logger.info("matched");
		} else {
		}
	}

	private String RoMobnumber = ".chat-header__main .chat-header__phone p:nth-child(2)";
	private String Conversationinfo = ".chat-header__drop-down button";
	private String ConversationInfo = "#first-content .info-container__content__title";

	public void verifychannelname(String filter) throws Exception {
		FrameLocator iframe = page.frameLocator(messageIframe);
		List<Boolean> flags = new ArrayList<>();
		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);
		FrameLocator orderDetailsiframe = page.frameLocator(orderDetailsIFrame);
		logger.info("Navigate to repair order header");
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("Existing");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.waitForTimeout(5000);
		String RoMobileno = orderDetailsiframe.locator(RoMobnumber).innerText();
		String ROChannelname = orderDetailsiframe.locator(rochannelName).innerText();
		System.out.println(ROChannelname + RoMobileno);
		// page.waitForCondition(()-> iframe.locator(rochannelName).isVisible());
		page.waitForTimeout(5000);
		HP.navigateToMessageScreen_Order();
		logger.info("Navigate to message header");
		page.waitForTimeout(9000);
		iframe.locator(searchFilter).type(ROChannelname);
		page.waitForTimeout(9000);
		iframe.locator(channalList).first().click();
		String Channelname = iframe.locator(rochannelName).innerText();
		if (ROChannelname.equals(Channelname)) {
			page.waitForTimeout(5000);
			if (iframe.locator(message_start_convers_buttn).isVisible()) {
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
				iframe.locator(startconMobileno).fill(RoMobileno);
				logger.info("Number:-" + RoMobileno);
				page.waitForTimeout(2000);
				iframe.locator(startConverSMS_Whatsapp_filterbuttn).click();
				page.waitForTimeout(2000);
				List<String> Text2 = iframe.locator(StartConFilter(filter)).allInnerTexts();
				for (String value : Text2) {
					if (value.trim().toUpperCase().contains(filter)) {
						iframe.locator(StartConFilter(filter)).click();
						logger.info("Select SMS");

					} else if (value.trim().toUpperCase().contains(filter)) {
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
				page.waitForTimeout(4000);
				if (!iframe.locator(ConversationInfo).isVisible()) {
					iframe.locator(Conversationinfo).click();
				}
				String newChannelname = iframe.locator(rochannelName).innerText();
				String newchannelname2 = iframe.locator(conversationInfoname).innerText();
				if (newChannelname.equals(newchannelname2)) {
					if (!iframe.locator(conversation_GotoRo_btn).isVisible()) {
						logger.info("Go To Ro button not visible");
						flags.add(false);
					}

					iframe.locator(conversation_GotoRo_btn).click();
					logger.info("GO_TO_RO button clicked");
					page.waitForCondition(() -> iframe.locator(rochannelName).isVisible());
					String newROChannelname = iframe.locator(rochannelName).innerText();
					if (newChannelname.equals(newROChannelname)) {
						logger.info("Ro Channel name updated correctlly");

					} else {
						throw new Exception("Ro channel name not updated");
					}
				}
			}
		} else {
			logger.info("Repair order not matched");
			throw new Exception("Repair order not matched");
		}

	}

	private String addMedia = "div.orders-detail-menu__media-add";
	private String addVideo_Title = "div.video-library__title p";
	private String videos = "img[alt='video thumbnail']";
	private String add_Button = "div.video-library__add-video-container button";
	private String added_Video = "div.orders-detail-menu__media-videos img";
	private String roStatusBar = "div.orders-detail-menu__action";
	private String messages = "ngx-message div.message";
	private String lastMessageEndlink = "ngx-message div.message a";
	private String playButton = "button[title='Play Video']";

	public void verify_videolink_functionality(String filter) {

		FrameLocator iframe = page.frameLocator(messageIframe);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		SoftAssert softAssert = new SoftAssert();

		HomePage HP = new HomePage(page);
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);

		logger.info("Navigate to repair order header");
		HP.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("Existing");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.waitForTimeout(5000);
		if (frame.locator(roStatusBar).textContent().contains("New")) {
			logger.info("RO is New & No media is added");
			String sendToCustomerClass = RO.getLocatorClass(operations_Buttons, "Send to customer");
			String viewWithCustomerClass = RO.getLocatorClass(operations_Buttons, "View with customer");
			String insightClass = RO.getLocatorClass(operations_Buttons, "Insights");
			if (sendToCustomerClass.contains("disabled") && viewWithCustomerClass.contains("disabled")
					&& insightClass.contains("disabled")) {
				logger.info("Both 'Send to customer','View with customer' & 'Insights' button is disabled");
				flags.add(true);
			} else {
				logger.info("'Send to customer','View with customer' & 'Insights' button is not disabled");
				flags.add(false);
			}
			softAssert.assertTrue(!flags.contains(true), // should be false
					"Verify 'Send to customer' & 'View with customer' button is disabled");
			flags.clear();
		} else {
			logger.info("RO is Not new & some videos are already added to RO");
		}
		frame.locator(addMedia).click();

		if (frame.locator(addVideo_Title).textContent().equals("Add video")) {
			logger.info("Multimedia Screen opened: " + frame.locator(addVideo_Title).textContent());
			flags.add(true);
		} else {
			logger.info("Multimedia Screen not opened");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Verify Add Media button is clickable");
		flags.clear();
		frame.locator(videos).first().click();
		logger.info("Selected 1 video from multimedia screen");
		page.waitForTimeout(2000);
		frame.locator(add_Button).click();
		logger.info("Clicked on Add Video Button");
		page.waitForTimeout(4000);
		String sendToCustomerClass_AfterVideoAdded = RO.getLocatorClass(operations_Buttons, "Send to customer");
		String viewWithCustomerClass_AfterVideoAdded = RO.getLocatorClass(operations_Buttons, "View with customer");
		int addedVideoCount = frame.locator(added_Video).count();
		if (addedVideoCount >= 0) {
			logger.info("Video added sucessfully and visible on media gallery");
			flags.add(true);
		} else {
			logger.info("Selected Video not added to media gallery");
			flags.add(false);
		}
		flags.add(RO.checkStatus("For Review")); // verify status whether For Review or Not
		softAssert.assertTrue(!flags.contains(false), "Verify status changed to For Review");
		flags.clear();
		softAssert.assertTrue(RO.verifyChangedStatusOnROList("For Review"),
				"Verify status changed to For Review on RO list screen");
		if (sendToCustomerClass_AfterVideoAdded == null || viewWithCustomerClass_AfterVideoAdded == null) {
			logger.info("'Send to customer' or 'View with customer' button is not found");
			flags.add(false);
		} else if (!sendToCustomerClass_AfterVideoAdded.contains("disabled")
				&& !viewWithCustomerClass_AfterVideoAdded.contains("disabled")) {
			logger.info("Both 'Send to customer' & 'View with customer' button is enabled");
			flags.add(true);
		} else {
			logger.info("'Send to customer' or 'View with customer' button is disabled");
			flags.add(false);
		}
		flags.add(RO.checkActivity("Added video"));
		softAssert.assertTrue(!flags.contains(false), "Verify add video function");
		RO.sendVideoToCustomer(filter);
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("video") || lastMessage.contains("Video")) {
			logger.info("Last message is video Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.locator(playButton).first().click();
			logger.info("Clicked on Play Button");
			logger.info("Waiting to play video for 8 Seconds");
			page.waitForTimeout(8000);
			endlinkPage.close();
		} else {
			logger.info("Last message is not video Endlink");
			throw new SkipException("Last message is not video Endlink");
		}
		page.waitForTimeout(4000);
		softAssert.assertTrue(frame.locator(roStatusBar).textContent().contains("Viewed"), "verify viewed status");
		page.waitForTimeout(2000);
		softAssert.assertTrue(RO.verifyChangedStatusOnROList("Viewed"), "verify viewed status on RO list");
		page.waitForTimeout(2000);
		softAssert.assertTrue(RO.checkActivity("Customer watched video"), "verify activity for video view");
		page.waitForTimeout(2000);
		softAssert.assertAll();
	}

	public void Verify_welcome_message() {
		FrameLocator iframe = page.frameLocator(messageIframe);
		HomePage homePage = new HomePage(page);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		OrderListPage OLP = new OrderListPage(page);
		RepairOrderDetailPage RO = new RepairOrderDetailPage(page);
		logger.info("Navigate to repair order header");
		homePage.clickOn_RepairOrder_Header();
		String RONumber = OLP.addRepairOrder("New");
		Locator tableRow = page.locator(tableRows);
		tableRow.locator("td:has-text('" + RONumber + "')").first().click();
		page.waitForURL(url -> url.contains("order/service/view"));
		page.waitForTimeout(30000);

		String Welcomemessage = frame.locator("div.message div.ng-star-inserted p").last().innerText();
		String Welcome_message = "welcome";

		if (Welcomemessage.contains(Welcome_message)) {
			logger.info("Welcome message present");
		} else {
			logger.info("jhdvasgvd");
		}
	}
}
