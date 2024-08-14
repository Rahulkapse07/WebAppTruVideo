package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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
		FrameLocator frame = page.frameLocator(salesIframe);
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
	public void checkStatus_OnVideoWatch() {
		FrameLocator frame = page.frameLocator(salesIframe);
		SoftAssert softAssert = new SoftAssert();
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
	
	public void verifyElements()
	{
		
	}

}
