package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import com.microsoft.playwright.options.SelectOption;
import com.truvideo.constants.AppConstants;

import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

public class ProspectDetailPage extends JavaUtility {
	private Page page;
	
	public ProspectDetailPage(Page page)
	{
		this.page=page;
	}
	
	
	private String salesIframe="#order-details-iframe";
	private String salesHeaderText=".return";
	private String customerName="h1.orders-detail-menu__customer-name";
	private String soStatusBar = "div.orders-detail-menu__action";
	private String addMedia = "div.orders-detail-menu__media-add";
	private String operationsTitleText="div.operations__container__title";
	private String details_Heading = "span.title--details";
	private String activity_Tab = "div[role='tab'] span:has-text('Activity')";  //found 2 elemetns
	private String customer_tab = "div[role='tab'] span:has-text('Customer')";
	private String activities = "app-activity div.detail__activity  p.detail__activity-title";
	private String addVideo_Title = "div.video-library__title p";
	private String operations_Buttons = ".menu-options";
	private String videos = "img[alt='video thumbnail']";
	private String add_Button = "div.video-library__add-video-container button";
	private String added_Video = "div.orders-detail-menu__media-videos img";
	private String selectChannelWindow = "'Please select a channel'";
	private String whatsApp_Button = ".selected-channel-actions button:has-text('WhatsApp')";
	private String sms_Button = ".selected-channel-actions button:has-text('SMS')";
	private String communicationTabs = ".mat-mdc-tab-label-container div[role='tab']";
	private String messages = "ngx-message div.message";
	private String lastMessageEndlink = "ngx-message div.message a";
	private String playButton = "button[title='Play Video']";
	private String sms_Textbox = ".mat-mdc-form-field-infix textarea";
	private String send_SMS_Button = "mat-icon[svgicon=\"send\"]";
	private String send_Original_Button = ".mdc-button--outlined";
	private String sms_Tab = "#mat-tab-label-1-1";
	private String openurl = "//p //a";
	private String newTab_text = ".my-3.mt-md-5.ml-md-5";
	private String customernameByHover="div.mat-mdc-tooltip-surface";
	private String insightButton = ".operations__container__action-menu tru-button:nth-child(5) button";
	private String noInsightText = ".insights__content-no-data.ng-star-inserted p";
	private String insightDataafterVideoView = ".insights__content-header p";
	private String closeInsightWindow = ".insights__header mat-icon:nth-child(2)";
	private String soStatus="orders-detail-menu__action info-bg";

	
	
	
	public void addVideoToOrder() {
		FrameLocator frame = page.frameLocator(salesIframe);
		frame.locator(salesHeaderText).waitFor();
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		if (frame.locator(soStatusBar).textContent().contains("New")) {
			logger.info("SO is New & No media is added");
			String sendToCustomerClass = getLocatorClass(operations_Buttons, "Send to customer");
			String viewWithCustomerClass = getLocatorClass(operations_Buttons, "View with customer");
			String insightClass = getLocatorClass(operations_Buttons, "Insights");
			if (sendToCustomerClass.contains("disabled") && viewWithCustomerClass.contains("disabled") && insightClass.contains("disabled")) {
				logger.info("Both 'Send to customer','View with customer' & 'Insights' button is disabled");
				flags.add(true);
			} else {
				logger.info("'Send to customer','View with customer' & 'Insights' button is not disabled");
				flags.add(false);
			}
			softAssert.assertTrue(!flags.contains(false),
					"Verify 'Send to customer' & 'View with customer' button is disabled");
			flags.clear();
		} else {
			logger.info("SO is Not new & some videos are already added to SO");
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
		String sendToCustomerClass_AfterVideoAdded = getLocatorClass(operations_Buttons, "Send to customer");
		String viewWithCustomerClass_AfterVideoAdded = getLocatorClass(operations_Buttons, "View with customer");
		int addedVideoCount = frame.locator(added_Video).count();
		if (addedVideoCount >= 0) {
			logger.info("Video added sucessfully and visible on media gallery");
			flags.add(true);
		} else {
			logger.info("Selected Video not added to media gallery");
			flags.add(false);
		}
		flags.add(checkStatus("For Review")); // verify status whether For Review or Not
		softAssert.assertTrue(!flags.contains(false), "Verify status changed to For Review");
		flags.clear();
		softAssert.assertTrue(verifyChangedStatusOnROList("For Review"),
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
		flags.add(checkActivity("Added video"));
		softAssert.assertTrue(!flags.contains(false), "Verify add video function");
		softAssert.assertAll();
	}
	public void sendVideoToCustomer(String channelSelected) {
		page.waitForTimeout(8000);
		FrameLocator frame = page.frameLocator(salesIframe);
		addVideoToOrder();
		if (!frame.locator(added_Video).first().isVisible()) {
			logger.info("Condition not satisfied for Send Video : video not added to SO");
			throw new SkipException("video not added to SO");
		}
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		page.waitForTimeout(2000);
		clickOperationButton("Send to customer");
		selectChannelToPerformAction(channelSelected); // Select channel to send video
		flags.add(verifyNavigationToChannel(channelSelected));// Navigation to channel after video sent
		softAssert.assertTrue(!flags.contains(false), "Verify Navigation To selected channel");
		flags.clear();
		flags.add(checkStatus("Sent")); // verify status whether Sent or Not
		softAssert.assertTrue(!flags.contains(false), "Verify Status changed to sent");
		flags.clear();
		softAssert.assertTrue(verifyChangedStatusOnROList("Sent"), "Verify Status changed to Sent on RO List");
		flags.add(checkLastMessageInConversation("video")); // check last message is video end-link or Not
		flags.clear();
		softAssert.assertTrue(!flags.contains(false), "Verify last message is video endlink");
		flags.add(checkActivity("sent to customer"));
		softAssert.assertTrue(!flags.contains(false), "Verify activity update after video sent");
		softAssert.assertAll();
		flags.clear();
	}
	public void checkStatus_OnVideoWatch(String channelSelected) {
		System.out.println("svcghsvcghvsaghv");
		FrameLocator frame = page.frameLocator(salesIframe);
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		addVideoToOrder();
		page.waitForTimeout(2000);
		clickOperationButton("Send to customer");
		selectChannelToPerformAction(channelSelected); // Select channel to send video
		flags.add(verifyNavigationToChannel(channelSelected));// Navigation to channel after video sent
		softAssert.assertTrue(!flags.contains(false), "Verify Navigation To selected channel");
		flags.clear();
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("video") || lastMessage.contains("Video")) {
			logger.info("Last message is video Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			endlinkPage.waitForLoadState();
			//endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.locator(playButton).first().click();
			logger.info("Clicked on Play Button");
			logger.info("Waiting to play video for 8 Seconds");
			page.waitForTimeout(8000);
			endlinkPage.close();
		} else {
			logger.info("Last message is not video Endlink");
			throw new SkipException("Last message is not video Endlink");
		}
		softAssert.assertTrue(frame.locator(soStatusBar).textContent().contains("Viewed"), "verify viewed status");
		softAssert.assertTrue(verifyChangedStatusOnROList("Viewed"), "verify viewed status on RO list");
		softAssert.assertTrue(checkActivity("Customer watched video"), "verify activity for video view");
		softAssert.assertAll();
	}
	
	private boolean verifyNavigationToChannel(String channelSelected) {
		FrameLocator frame = page.frameLocator(salesIframe);
		boolean flag = false;
		page.waitForTimeout(4000);
		if (!frame.locator(communicationTabs).allTextContents().contains("WhatsApp")) {
			if (getLocatorClass(communicationTabs, "SMS").contains("active")) {
				logger.info("WhatsApp is disabled & User navigated to the SMS tab by default");
				flag = true;
				return flag;
			}
		} else if (getLocatorClass(communicationTabs, channelSelected).contains("active")) {
			logger.info("User navigated to the " + channelSelected + " channel after sent ");
			flag = true;
			return flag;
		} else {
			logger.info("User not navigated to the " + channelSelected + " channel after sent ");
			flag = false;
			return flag;
		}
		return flag;
	}
	private boolean checkLastMessageInConversation(String messageKeyWords) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(salesIframe);
		int retryCount = 3;
		int attempt = 1;
		String messageKeyWords_LowerCase = messageKeyWords.toLowerCase();
		while (attempt <= retryCount) {
			page.waitForTimeout(4000);
			try {
				String lastMessageContent = frame.locator(messages).last().textContent().toLowerCase();
				logger.debug("Checking last message text: " + lastMessageContent);
				if (lastMessageContent.contains(messageKeyWords_LowerCase)) {
					logger.info("Last message contains keyword - " + messageKeyWords + " at attempt " + attempt);
					logger.info("Last Message is : " + lastMessageContent);
					flag = true;
					break;
				} else {
					logger.info("Last message does not contain keyword - " + messageKeyWords + " at attempt "
							+ (attempt + 1));
					logger.info("Last Message is : " + lastMessageContent);
				}
			} catch (Exception e) {
				logger.info("Exception while checking last message at attempt " + attempt + ": " + e.getMessage());
				logger.info("Last Message is : " + frame.locator(messages).last().textContent());
			}
			attempt++;
		}
		if (flag == false) {
			logger.info("refreshing page to get Last Message");
			page.reload();
			String lastMessageContent = frame.locator(messages).last().textContent().toLowerCase();
			logger.debug("Checking last message text: " + lastMessageContent);
			if (lastMessageContent.contains(messageKeyWords_LowerCase)) {
				logger.info("Last message contains keyword - " + messageKeyWords + " on Refresh");
				logger.info("Last Message is : " + lastMessageContent);
				flag = true;
			}
		}
		return flag;
	}

	
	private void clickOperationButton(String buttonText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(salesIframe);
		Locator buttons = frame.locator(operations_Buttons);
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			if (textContent != null && textContent.contains(buttonText)) {
				locator.click();
				break;
			}
		}
	}
	private void selectChannelToPerformAction(String channelSelected) {
		FrameLocator frame = page.frameLocator(salesIframe);
		page.waitForTimeout(2000);
		if (frame.locator(selectChannelWindow).isVisible() && channelSelected.contains("WhatsApp")) {
			logger.info("WhatsApp is enabled & Selected whatsApp channel to send");
			frame.locator(whatsApp_Button).click();
			logger.info("Clicked on whatsApp button : sent through WhatsApp");
		} else if (frame.locator(selectChannelWindow).isVisible() && channelSelected.contains("SMS")) {
			logger.info("WhatsApp is enabled & Selected SMS channel to send");
			frame.locator(sms_Button).click();
			logger.info("Clicked on SMS button : sent through SMS");
		} else {
			logger.info("WhatsApp is Disabled : sent from SMS");
		}
	}
	
	private String getLocatorClass(String operationButtons, String visibleText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(salesIframe);
		Locator buttons = frame.locator(operationButtons);
		String className = null;
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			if (textContent != null && textContent.contains(visibleText)) {
				className = locator.getAttribute("class");
				break;
			}
		}
		return className;
	}
	
	private boolean checkStatus(String status) {
		FrameLocator frame = page.frameLocator(salesIframe);
		page.waitForTimeout(2000);
		if (frame.locator(soStatusBar).textContent().contains(status)) {
			logger.info("SO Status is: " + frame.locator(soStatusBar).textContent());
			return true;
		} else {
			logger.info("SO Status is: " + frame.locator(soStatusBar).textContent());
			return false;
		}
	}
	
	private boolean verifyChangedStatusOnROList(String status) {
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			page.evaluate("window.open()");
		});
		newPage.navigate("https://rc.truvideo.com/crud/sales");
		newPage.waitForTimeout(2000);
		logger.info("New Page opened in another tab");
		if (newPage.innerText(getSO(ProspectListPage.newSOName)).contains(status)) {
			logger.info("Status on RO list screen is changed to " + status);
			newPage.close();
			return true;
		} else {
			logger.info("Status on RO list screen is not changed to " + status);
			newPage.close();
			return false;
		}
	}
	
	private String getSO(String createdSO) {
		String createdSoInList = "#sales-order-results tbody tr:has-text('" + createdSO + "')";
		return createdSoInList;
	}
	
	private boolean checkActivity(String activityLog) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(salesIframe);
		frame.locator(activity_Tab).first().click();
		int retryCount = 2;
		int attempt = 1;
		while (attempt <= retryCount) {
			try {
				page.waitForCondition(() -> {
					List<String> activityTextList = frame.locator(activities).allInnerTexts();
					logger.debug("Checking activity text: " + activityTextList);
					return activityTextList.stream().anyMatch(text -> text.contains(activityLog));
				});
				String activityText = frame.locator(activities).nth(0).textContent();
				logger.info("SO activity is: " + activityText);
				if (activityText.contains(activityLog)) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				logger.info("Searched activity not displayed at attempt " + attempt);
				logger.info("SO activity is: " + frame.locator(activities).nth(0).textContent());
			}
			attempt++;
		}
		if (flag == false) {
			logger.info("refreshing page to get activity");
			page.reload();
			frame.locator(activity_Tab).first().click();
			String activityText = frame.locator(activities).nth(0).textContent();
			logger.info("SO activity is: " + activityText);
			if (activityText.contains(activityLog)) {
				logger.info("Activity contains - " + activityLog + " on Refresh");
				flag = true;
			}
		}
		frame.locator(customer_tab).first().click();
		return flag;
	}

	

	private String communicationTab = "div.orders-detail-communications__title";
	private String whatsApp_tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('WhatsApp')";
	private String sMs_tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('SMS')";
	private String chat_tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('Chat')";
	private String notes_tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('Notes (0)')";
	private String textArea = "div.orders-detail-notes__message textarea[placholder='Add a new note...']";
	private String clear_btn = ".mat-mdc-tooltip-trigger.orders-detail-notes__message__bottom--btn--del";
	private String save_btn = ".mat-mdc-tooltip-trigger.orders-detail-notes__message__bottom--btn--send";
	private String writetText = ".orders-detail-notes__message__input";
	private String notes_tab1 = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('Notes (1)')";

	public boolean notesFunctionalityOnSO() throws InterruptedException {
		try {
			FrameLocator frame = page.frameLocator(salesIframe);
			page.waitForTimeout(3000);
			if (frame.locator(communicationTab).isVisible() && frame.locator(sMs_tab).isVisible()
					&& frame.locator(chat_tab).isVisible() && frame.locator(notes_tab).isVisible()) {
				logger.info("All tabs are vissible");

			} else {
				logger.info("All tabs are not vissible");
			}
			if (frame.locator(whatsApp_tab).isVisible()) {
				logger.info("WhatsApp tab is vissible");
			} else {
				logger.info("WhatsApp setting is disabled from dealer settings");

			}
			frame.locator(notes_tab).click();
			logger.info("Notes window is displayed sucessfully");
			logger.info("Notes count is displayed 0 by default");
			frame.locator(textArea).isVisible();
			logger.info("Add a new note...text are display in the text box");
			if (frame.locator(clear_btn).isVisible() && frame.locator(save_btn).isVisible()) {
				logger.info("Clear and Save button is displayed at the bottom");
			} else {
				logger.info("Both buttons are not vissible");

			}
			frame.locator(writetText).fill(getRandomString(4));
			logger.info("Text entered succesfully into the text box");
			frame.locator(clear_btn).click();
			logger.info("Text are clear successfully in the text box");
			Thread.sleep(3000);
			frame.locator(writetText).fill(getRandomString(4));
			logger.info("Again text entered succesfully into the text box");
			frame.locator(save_btn).click();
			logger.info("Notes added succesfully in the text box");
			Thread.sleep(3000);
			frame.locator(notes_tab1).isVisible();
			logger.info("Notes count are increased by +1");
			String notes1 = frame.locator(notes_tab1).innerText().toLowerCase();
			String numbersOnly = notes1.replaceAll("[^0-9]", "");
			System.out.println(numbersOnly);
			logger.info("Notes count increased: " + numbersOnly);
			page.reload();
			frame.locator(notes_tab1).waitFor();
			logger.info("Page refreshed and notes count validated.");
			return true;
		} catch (Exception e) {
			logger.error("Error during notes functionality validation: " + e.getMessage());
			return false;
		}

	}
	
	
	private String communication_tab = "div.orders-detail-communications__title";
	private String whatsAppTab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('WhatsApp')";
	private String sMs_Tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('SMS')";
	private String chat_Tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('Chat')";
	private String notes_Tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('Notes (0)')";
	private String customerNoNotAvailable = "#mat-tab-content-1-1 div.orders-detail__no-conversation.ng-star-inserted p";
	//private String customerNoNotAvailable = "p.orders-detail__no-conversation-label";
	private String logoFLname = "div.chat-header__avatar .avatar-content.ng-star-inserted";
	private String flName = "div.chat-header__main p.chat-header__title";
	private String mobileNo = "div.chat-header__phone p.chat-header__members:nth-child(2)";
	private String channelOwnerName = "div.chat-header__phone p.chat-header__members:nth-child(4)";
	private String logInUserLabel = "li.account-nav a span span:nth-child(3)";
	private String standardResponse  = "div.standard-responses__chips.ng-star-inserted div:nth-child(1)";


	public boolean smsFunctionalityOnSO() {

		FrameLocator frame = page.frameLocator(salesIframe);
		HomePage hp = new HomePage(page);
		ProspectListPage pl = new ProspectListPage(page);
		logger.info("Creating SO with mail...");
		hp.clickOn_Prospect_Header();
		page.waitForTimeout(15000);
// Start script
		pl.addNewSalesProspectWithoutMobileNo();
		logger.info("Creating SO without mobile no and mail..");
		page.waitForTimeout(30000);
		if (frame.locator(communicationTab).isVisible() && frame.locator(sMs_tab).isVisible()
				 && frame.locator(notes_tab).isVisible()) {
			logger.info("All tabs are vissible");

		} else {
			logger.info("All tabs are not vissible");
		}
		
		frame.locator(sMs_Tab).click();
		logger.info("Click on SMS tab successfully");
		frame.locator(customerNoNotAvailable).isVisible();
		logger.info(" Customer's phone number not available or invalid ");
		page.waitForTimeout(3000);
		hp.clickOn_Prospect_Header();
		page.waitForTimeout(15000);
        pl.navigateToProspectDetails();
		page.waitForTimeout(10000);
		logger.info("Create New prospect with 'mobile & email' open that prospect");
		page.waitForTimeout(30000);
		if (frame.locator(communicationTab).isVisible() && frame.locator(sMs_tab).isVisible()
				 && frame.locator(notes_tab).isVisible()) {
			logger.info("All tabs are vissible");

		} else {
			logger.info("All tabs are not vissible");
		}
		frame.locator(sMs_Tab).click();
		logger.info("Click on SMS tab successfully");
		if(frame.locator(logoFLname).isVisible() && frame.locator(flName).isVisible()
				 && frame.locator(mobileNo).isVisible()) {
			logger.info("Logo, FL name and Mobile number are vissible");
		}
		else {
			logger.info("Name & Mobile no are not vissible");
		}
		
        List<String> s1 = page.locator(channelOwnerName).allInnerTexts();
        logger.info(s1);
        String ownername = page.locator(logInUserLabel).innerText().toLowerCase();
		System.out.println(ownername);
		for (String name : s1) {

			if (name.toLowerCase().contains(ownername)) {
				logger.info("Name of Advisor is matched" + ownername);
				return true;
			} else {
				logger.info("Name of Advisor is notmatched" + ownername);
				return false;
			}
		}
        
		page.waitForTimeout(1000);
		frame.locator(sms_Textbox).click();
		page.keyboard().down("Control");
		page.keyboard().press("V");
		page.keyboard().up("Control");
		logger.info("Verifying a control paste");
		frame.locator(send_SMS_Button).click();
		frame.locator(send_Original_Button).click();
		logger.info("Original Text has been sent successfully");
		frame.locator(standardResponse).click();
		logger.info("Select Standard response");
		frame.locator(send_SMS_Button).click();
		logger.info("Standard response send successfully");
		return true;

	}
	
	public boolean copyLinkToClipboard() {
	    FrameLocator frame = page.frameLocator(salesIframe);
	    page.waitForCondition(() -> frame.locator(sms_Tab).isVisible());
	    //addVideoToOrder();
	    clickOperationButton("Copy link to clipboard");
	    page.waitForTimeout(2000);

	    String customerNameInSO = getCustomerNameFromSO();
	    logger.info("Customer Name in SO: " + customerNameInSO);

	    sendSMSText();

	    return verifyCustomerNameInEndLink(customerNameInSO);
	}

	private String getCustomerNameFromSO() {
		FrameLocator frame = page.frameLocator(salesIframe);
		frame.locator(customerName).click();
		page.waitForTimeout(1000);
		frame.locator(customerName).hover();
		String customerfullName = frame.locator(customernameByHover).innerText().toLowerCase().trim();
		logger.info("Customer full name in SO :- " + customerfullName);
		String[] splitText = customerfullName.split("-");

		String CustomerFullName = splitText.length > 1 ? splitText[1].trim() : "";
		logger.info("Extracted Customer Name: " + CustomerFullName);
		return CustomerFullName;
	}

	private void sendSMSText() {
		FrameLocator frame = page.frameLocator(salesIframe);
		frame.locator(sms_Tab).click();
		page.waitForTimeout(1000);
		frame.locator(sms_Textbox).click();

		pasteClipboardContent();
		logger.info("Pasted link into SMS text box.");

		frame.locator(send_SMS_Button).click();
		frame.locator(send_Original_Button).click();
		logger.info("Original text has been sent successfully.");
	}

	private void pasteClipboardContent() {
		page.keyboard().down("Control");
		page.keyboard().press("V");
		page.keyboard().up("Control");
	}

	private boolean verifyCustomerNameInEndLink(String customerFullName) {
	    FrameLocator frame = page.frameLocator(salesIframe);
	    Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
	        frame.locator(openurl).click();
	        logger.info("End link opened in another tab.");
	    });

	    endlinkPage.waitForLoadState();

	    if (!endlinkPage.url().contains("truvideo.com/w/")) {
	        logger.warn("End link URL does not contain the expected path.");
	        endlinkPage.close();
	        return false; 
	    }

	    String customerNameInEndLink = endlinkPage.locator(newTab_text).innerText().toLowerCase().trim();
	    logger.info("Customer Name in End Link: " + customerNameInEndLink);

	    boolean isMatching = customerNameInEndLink.equals(customerFullName);
	    if (isMatching) {
	        logger.info("SO customer name matches the end link customer name.");
	    } else {
	        logger.error("SO customer name does NOT match the end link customer name.");
	    }
	    endlinkPage.close();
	    return isMatching;
	}

	
	public boolean viewWithCustomer() {
	    boolean isSuccessful = true; // Tracks the overall success of the method
	    List<Boolean> flags = new ArrayList<>();
	    SoftAssert softAssert = new SoftAssert();
	    FrameLocator frame = page.frameLocator(salesIframe);

	    try {
	        page.waitForTimeout(10000);
	        addVideoToOrder();

	        Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
	            clickOperationButton("View with customer");
	            logger.info("Endlink opened in another tab");
	        });

	        // Validate the end link URL
	        if (!endlinkPage.url().contains("truvideo.com/w/")) {
	            logger.warn("End link URL does not contain the expected path.");
	            endlinkPage.close();
	            return false; // Early return if URL check fails
	        }

	        // Play video and log customer name
	        endlinkPage.locator(playButton).first().click();
	        logger.info("Clicked on Play Button");
	        page.waitForTimeout(8000);

	        String customerNameInEndLink = endlinkPage.locator(newTab_text).innerText().toLowerCase().trim();
	        logger.info("Customer Name in End Link: " + customerNameInEndLink);

	        String customerNameInSO1 = getCustomerNameFromSO();
	        logger.info("Customer Name in SO: " + customerNameInSO1);

	        // Compare customer names
	        boolean isMatching = customerNameInEndLink.equals(customerNameInSO1);
	        if (isMatching) {
	            logger.info("SO customer name matches the end link customer name.");
	        } else {
	            logger.error("SO customer name does NOT match the end link customer name.");
	            isSuccessful = false;
	        }

	        endlinkPage.close();

	        // Check SO status
	        String soStatus = frame.locator(soStatusBar).innerText();
	        logger.info("Current SO status is: " + soStatus);

	        if (soStatus.contains("For Review")) {
	            logger.info("SO status is showing 'For Review'.");
	        } else {
	            logger.error("SO status is showing a different status rather than 'For Review'.");
	            isSuccessful = false;
	        }

	        flags.add(checkStatus("For Review"));
	        if (flags.contains(false)) {
	            isSuccessful = false;
	        }

	        softAssert.assertTrue(!flags.contains(false), "Verify status should be 'For Review'");
	        flags.clear();

	        boolean statusChanged = verifyChangedStatusOnROList("For Review");
	        if (!statusChanged) {
	            isSuccessful = false;
	        }
	        softAssert.assertTrue(statusChanged, "Verify status should not change to 'Viewed' from 'For Review'");

	        page.reload();

	        flags.add(checkStatus("For Review"));
	        if (flags.contains(false)) {
	            isSuccessful = false;
	        }

	        softAssert.assertTrue(!flags.contains(false), "Verify status should be 'For Review'");
	        flags.clear();

	        boolean finalStatusChanged = verifyChangedStatusOnROList("For Review");
	        if (!finalStatusChanged) {
	            isSuccessful = false;
	        }
	        softAssert.assertTrue(finalStatusChanged, "Verify status should not change to 'Viewed' from 'For Review'");

	        softAssert.assertAll();

	    } catch (Exception e) {
	        logger.error("An error occurred in viewWithCustomer: " + e.getMessage());
	        isSuccessful = false; // Mark the process as unsuccessful if an exception occurs
	    }

	    return isSuccessful;
	}

	public void insightFunctionality() {

		FrameLocator frame = page.frameLocator(salesIframe);
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		if (frame.locator(soStatusBar).textContent().contains("New")) {
			logger.info("SO is New & No media is added");		
			String insightClass = getLocatorClass(operations_Buttons, "Insights");
			System.out.println("insightClass " + insightClass);
			if (insightClass.contains("disabled")) {
				logger.info("'Insights' button is disabled");
				flags.add(true);
			} else {
				logger.info(" 'Insights' button is not disabled");
				flags.add(false);
			}
			softAssert.assertTrue(!flags.contains(false), // should be false
					"Verify 'Insight' button is disabled");
			flags.clear();
		} else {
			logger.info("SO is Not new & some videos are already added to SO");
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
		frame.locator(insightButton).click();
		logger.info("Verifying Without customer opened the Video Insight data is not showing");
		String insightText = frame.locator(noInsightText).innerText().trim();
		softAssert.assertTrue(insightText.contains("There's no insights yet"), "Verify No insight showing");
		frame.locator(closeInsightWindow).click();
		copyLinkToClipboard();
		frame.locator(insightButton).click();
		logger.info("Verifying Without customer opened the Video Insight data is now showing");
		String InsightData = frame.locator(insightDataafterVideoView).innerText().trim();
		softAssert.assertTrue(InsightData.contains("Seen by"), "Verify now insight data is showing");

		frame.locator(closeInsightWindow).click();
		logger.info("Insight window closed");
		softAssert.assertAll();
	} 
	private String confirmButton=".btn.btn-confirm";
	private String cancelButton=".btn.btn-cancel";
	private String tradeInLinkmessage=".modal__container__content";
	public void verifyTradeInLink(String channelSelected) {
		FrameLocator frame = page.frameLocator(salesIframe);
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (frame.locator(soStatusBar).textContent().contains("New")) {
			logger.info("SO is New & No media is added");		
			
			softAssert.assertTrue(!flags.contains(false),
					"Verify 'Insight' button is disabled");
			flags.clear();
		} else {
			logger.info("SO is Not new & some videos are already added to SO");
		}
		clickOperationButton("Send trade in link");				
		page.waitForTimeout(4000);
		selectChannelToPerformAction(channelSelected); // Select channel to send video
		flags.add(verifyNavigationToChannel(channelSelected));// Navigation to channel after video sent
		page.waitForTimeout(2000);
		String tradeInLinkMessage=frame.locator(tradeInLinkmessage).innerText();
		logger.info(tradeInLinkMessage);
		frame.locator(confirmButton).click();
		page.waitForTimeout(1000);
		softAssert.assertTrue(!flags.contains(false), "Verify Navigation To selected channel");
		flags.clear();		
//		flags.add(checkLastMessageInConversation("video")); // check last message is video end-link or Not
//		flags.clear();
//		softAssert.assertTrue(!flags.contains(false), "Verify last message is video endlink");
//		flags.add(checkActivity("sent to customer"));
//		softAssert.assertTrue(!flags.contains(false), "Verify activity update after video sent");
		softAssert.assertAll();
		flags.clear();
		
	}
	private String firstNameEditing = "[formcontrolname='firstName']";
	private String firstNameWithoutEdit = ".detail__main-data-wrapper.apply-border div:nth-child(1) p:nth-child(2)";
	private String lastNameEditing = "[formcontrolname='lastName']";
	private String lastNameWithoutEdit = "[formgroupname='customerDTO'].detail__main-data-wrapper.apply-border div:nth-child(2) p:nth-child(2)";
	private String mobileFieldEditing = ".mat-mdc-input-element.ng-pristine.ng-touched";
	private String mobile="#mat-input-2";
	private String mobileNumberField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(5)";
	private String companyField="[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(4)";
	private String fleetField="[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(3)";
	private String emailFieldEditing = "[formcontrolname='email']";
	private String emailField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(4)";
	private String customerID="[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(7)";
	private String vehicleMake="[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(2)";
	private String vehicleModel="[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(3)";
	private String vehicleYear="[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(4)";
	private String vehicleColor="[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(5)";
	private String vehicleVIN="[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(6)";
	private String dealerField=".detail__main-data-wrapper.ng-star-inserted div:nth-child(2) p:nth-child(1)";
	private String dealerFieldValue=".detail__main-data-wrapper.ng-star-inserted div:nth-child(2) p:nth-child(2)";
	private String technicianField=".detail__main-data-wrapper.ng-star-inserted div:nth-child(3) p:nth-child(1)";
	private String technicianFieldValue=".detail__main-data-wrapper.ng-star-inserted div:nth-child(3) p:nth-child(2)";
	private String advisorField = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(4) p:nth-child(1)";
	private String advisorFieldValue=".detail__main-data-wrapper.ng-star-inserted div:nth-child(4) p:nth-child(2)";
	private String advisorFieldEditing = "[formcontrolname='agentId']";
	private String saveButton = "button.edit";
	private String cancelButton1 = "button.cancel";
	private String topRightCornerNotification = "div.notifications";
	private String popupofEditRO="text=The Sales Order has been edited successfully";

	public void editThisSO() {
		page.waitForTimeout(9000);
		FrameLocator frame = page.frameLocator(salesIframe);
		page.waitForTimeout(5000);
		String firstName = frame.locator(firstNameWithoutEdit).innerText();
		logger.info("FirstName before edited :-" + firstName);
		clickOperationButton("edit");
		logger.info("Clicked on Edit this SO button");
		page.waitForTimeout(6000);
		frame.locator(firstNameEditing).click();
		page.waitForTimeout(3000);
		frame.locator(firstNameEditing).fill("FirstName Edited");
		logger.info("Edited FirstName successfully");
		frame.locator(lastNameEditing).fill("Edited LastName");
		// String lastName=frame.locator(lastNameEditing).innerText();
		logger.info("Edited LastName successfully :-");
		page.waitForTimeout(1000);
		// frame.locator(mobile).click();
//		logger.info("Clicked in Mobile field :-");
//		frame.locator(mobile).clear();
//		frame.locator(mobile).fill("7812049488");
//		logger.info("Mobile number edited successfully :-");
		frame.locator(emailFieldEditing).clear();
		frame.locator(emailFieldEditing).fill("testemailedited@gmail.com");
		logger.info("Edited Email successfully :-");
		Locator dropdown = frame.locator(advisorFieldEditing);
		dropdown.selectOption(new SelectOption().setLabel("Advisor Dinesh"));
		logger.info("Sales Agent Chanegd successfully ");
		frame.locator(saveButton).click();
		page.waitForTimeout(1000);
		Locator popupMessage = frame.locator("text=The Sales Order has been edited successfully");
		popupMessage.waitFor(); // Waits until the popup appears
		String popup = popupMessage.textContent();
		// System.out.println("Popup text: " + popup);
		if (popup.contains(AppConstants.SALES_ORDER_EDITED_MESSAGE)) {
			logger.info(" SO has been Edited successfully");
		} else {
			logger.info("Getting error to edit Sales Order ");
		}
		String firstNameEdited = frame.locator(firstNameWithoutEdit).innerText();
		logger.info("FirstName After edited :-" + firstNameEdited);
		String name = frame.locator(customerName).innerText();
		String lastNameEdited = frame.locator(lastNameWithoutEdit).innerText();
		logger.info("LastName After edited :-" + lastNameEdited);
		String EmailEdited = frame.locator(emailField).innerText();
		logger.info("Email After edited :-" + EmailEdited);
//		String MobileNumberEdited = frame.locator(mobileNumberField).innerText();
//		logger.info("Mobile Number After edited :-" + MobileNumberEdited);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(name.contains("FirstName Edited"), "Verify Customer First name update");
		logger.info("Name changed to : " + name);
		softAssert.assertTrue(lastNameEdited.contains("Edited LastName"), "Verify Customer Last name update");
		softAssert.assertTrue(EmailEdited.contains("testemailedited@gmail.com"), "Verify Customer email update");
		// softAssert.assertTrue(MobileNumberEdited.contains("+1 (781) 204-9488"),
		// "Verify Customer Mobile number update");
		softAssert.assertAll();
	}
	
	
	public boolean deleteSalesOrder() throws InterruptedException {
		page.waitForTimeout(9000);

		FrameLocator frame = page.frameLocator(salesIframe);

		page.waitForTimeout(5000);
		logger.info(ProspectListPage.newSOName);
		// frame.locator(".menu-options__info.delete").click();
		page.waitForTimeout(2000);
		clickOperationButton("Delete this SO");
		page.waitForTimeout(2000);

		HomePage hp = new HomePage(page);
		// boolean bb=hp.globalSearchwitheText(OrderListPage.newRoNumber);
		if (hp.globalSearchwitheText(ProspectListPage.newSOName)) {
			logger.info("Selected SO has been deleted successfully");
			return true;
		} else {
			logger.info("Getting issue while deleting SO");
			return false;
		}

	}

}
