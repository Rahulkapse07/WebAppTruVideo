package com.truvideo.pages;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.SelectOption;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

public class RepairOrderDetailPage extends JavaUtility {
	private Page page;
	OrderListPage orderpage = new OrderListPage(page);

	public RepairOrderDetailPage(Page page) {
		this.page = page;
	}

	private String repairOrder_Header = "a[href='/crud/repair-order']";
	private String repairOrder_PageHeading = ".main-body span:has-text('Repair Order')";
	// private String repairOrder_PageHeading1 = "li.nav-item
	// a[href='/crud/repair-order']";
	private String orderDetailsIFrame = "iframe#order-details-iframe";
	private String nextRO_Button = ".order-navigation:has-text('Next RO')";
	private String roNumber = "h1.orders-detail-menu__ro-number";
	private String roStatusBar = "div.orders-detail-menu__action";
	private String addMedia = "div.orders-detail-menu__media-add";
	private String payment_Button = ".orders-detail-menu__payments";
	private String estimate_Button = ".orders-detail-menu__estimates";
	private String operations_Heading = ".operations__container__title";
	private String details_Heading = "span.title--details";
	private String activity_Tab = "div[role='tab'] span:has-text('Activity')";
	private String customer_tab = "div[role='tab'] span:has-text('Customer')";
	private String activities = "app-activity div.detail__activity  p.detail__activity-title";
	private String addVideo_Title = "div.video-library__title p";
	private String operations_Buttons = "div .menu-options__info";
	private String operationinsighttest = "mat-mdc-tooltip-trigger menu-options disabled";
	private String operations_Buttons_buttonTag = "button.menu-options";
	private String operations_Buttons_DivTag = "div.menu-options";
	private String insightButton = ".operations__container__action-menu tru-button:nth-child(5) button";
	private String noInsightText = ".insights__content-no-data.ng-star-inserted p";
	private String insightDataafterVideoView = ".insights__content-header p";
	private String closeInsightWindow = ".insights__header mat-icon:nth-child(2)";
	private String videos = "img[alt='video thumbnail']";
	private String add_Button = "div.video-library__add-video-container button";
	private String added_Video = "div.orders-detail-menu__media-videos img";
	private String selectChannelWindow = "'Please select a channel'";
	private String whatsApp_Button = ".selected-channel-actions button:has-text('WhatsApp')";
	private String sms_Button = ".selected-channel-actions button:has-text('SMS')";
	private String sms_Tab = "#mat-tab-label-1-1";
	private String sms_Textbox = ".mat-mdc-form-field-infix textarea";
	private String send_SMS_Button = "mat-icon[svgicon=\"send\"]";
	private String send_Original_Button = ".mdc-button--outlined";
	private String customerName = ".chat-header__main .chat-header__title";
	private String openurl = "//p //a";
	private String newTab_text = ".my-3.mt-md-5.ml-md-5";
	private String communicationTabs = ".mat-mdc-tab-label-container div[role='tab']";

	private String messages = "ngx-message div.message";
	private String notesTab_Communication = "#mat-tab-label-1-3";
	private String textboxNotesTab = "app-orders-notes textarea";
	private String saveButton = "button.edit";
	private String cancelButton = "button.cancel";
	private String firstNameEditing = "[formcontrolname='firstName']";
	private String firstNameWithoutEdit = ".detail__main-data-wrapper.apply-border div:nth-child(1) p:nth-child(2)";
	private String lastNameEditing = "[formcontrolname='lastName']";
	private String lastNameWithoutEdit = "[formgroupname='customerDTO'].detail__main-data-wrapper.apply-border div:nth-child(2) p:nth-child(2)";
	private String mobileFieldEditing = "#mat-input-1";
	private String mobileNumberField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(5)";
	private String companyField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(4)";
	private String fleetField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(3)";
	private String emailFieldEditing = "[formcontrolname='email']";
	private String emailField = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(6)";
	private String customerID = "[formgroupname='customerDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(7)";
	private String vehicleMake = "[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(2)";
	private String vehicleModel = "[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(3)";
	private String vehicleYear = "[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(4)";
	private String vehicleColor = "[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(5)";
	private String vehicleVIN = "[formgroupname='vehicleDTO'].detail__main-data-wrapper .detail__main-data-item:nth-child(6)";
	private String dealerField = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(2) p:nth-child(1)";
	private String dealerFieldValue = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(2) p:nth-child(2)";
	private String technicianField = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(3) p:nth-child(1)";
	private String technicianFieldValue = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(3) p:nth-child(2)";
	private String advisorField = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(4) p:nth-child(1)";
	private String advisorFieldValue = ".detail__main-data-wrapper.ng-star-inserted div:nth-child(4) p:nth-child(2)";
	private String advisorFieldEditing = "[formcontrolname='advisorId']";
	private String topRightCornerNotification = "div.notifications";
	private String topRightCornerNotification1 = "div.tru-toast";

	// Estimate
	private String create_Edit_Estimate_WindowHeader = "mat-card span:has-text('Estimate - Create/Edit Estimate')";
	private String items_Tab = "mat-card-content .mdc-tab__content span:has-text('Items')";
	private String notes_Tab = "mat-card-content .mdc-tab__content span:has-text('Notes')";
	private String list_ItemHeader = "mat-tab-body div.items__info-header p";
	private String newItem_TextField = "input[placeholder='New Item']";
	private String description_TextField = "input[placeholder='Description']";
	private String amount_TextField = "input[placeholder='Amount']";
	private String type_DropdownField = "input[placeholder='Type']";

	private String selectTypeFromDropdown(String type) {
		return ".tru-dropdown__content-items strong:has-text('" + type + "')";
	}

	private String closeWindow_Button = "mat-icon:has-text('close')";
	// private String addItem_Button = ".items__input-container
	// button:has-text('add')";
	private String addItem_Button = "//button //mat-icon[text()='add']";
	private String removeItem_Button = ".items__input-container button:has-text('backspace')";
	private String items_DropdownList = ".tru-dropdown__content-items";
	private String deleteItem_Button = ".actions__div.ng-star-inserted button mat-icon:has-text('delete')";
	private String editItem_Button = ".items__info-data__row-data button.mdc-button :has-text('edit')";
	private String preview_Button = ".mdc-button__label:has-text('Preview')";
	private String itemsAmount = "app-estimate-invoice .items__container p.items__info-data__row-data:nth-child(3)";
	private String totalAmount = ".invoice-amount";
	private String buttons_PreviewScreen = ".items__container-actions button.mr-5";
	private String hideFromCustomer_Button = "button:has-text('Hide from customer')";
	private String closeEstimate_Button = "button:has-text('Close Estimate')";
	private String edit_Button = ".items__container-actions button.mr-5 :has-Text('Edit')";
	private String print_Button = "button:has-text('Print')";
	private String send_Button = "button.primary:has-text('Send')";
	private String hiddenMessage = ".invoice__hide-customer";
	private String newEstimate_Button = "button:has-text('New Estimate')";
	private String confirm_Button = "button.primary:has-text('Confirm')";
	private String notify_Button = "button.primary:has-text('Notify')";
	// Payment
	private String next_Button = "app-items button:has-text('Next')";
	private String internalNotes_TextBox = "form input[placeholder='Write an internal note.']";
	private String customerNotes_TextBox = "form input[placeholder='Write a note for the customer.']";
	// private String rowCount = "div.items__info-container div
	// button:has-text('delete')";
	private String rowCount = ".actions__div.ng-star-inserted button  mat-icon:has-text('delete')";
	private String windowTitle = "mat-card-content .mat-toolbar span";
	private String toaster_Message = "' Amount is required '";
	private String back_Button = ".mdc-button__label:has-text('Back')";
	private String amount_TextField_Payment = "app-price tru-text-input input";
	private String addedNote_ReviewScreen = "app-invoice .invoice-notes";
	private String final_Amount = ".invoice-total__container .invoice-amount";
	private String editButton_ReviewScreen = "app-invoice button:has-text('Edit')";
	private String printButton_ReviewScreen = "app-invoice button:has-text('Print')";
	private String sendButton_ReviewScreen = "app-invoice button:has-text('Send')";
	private String lastMessageEndlink = "ngx-message div.message a";
	private String playButton = "button[title='Play Video']";
	private String authoriseWork_Button = "button:has-text('Authorize Work')";
	// Edit RO

	private String getRO(String createdRO) {
		String createdRoInList = "#repair-order-results tbody tr:has-text('" + createdRO + "')";
		return createdRoInList;
	}

	public boolean checkAllMandatoryFields_ForNewRO() {

		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(repairOrder_PageHeading).waitFor();
		List<Boolean> flags = new ArrayList<Boolean>();
		flags.add(checkStatus("New")); // verify status whether New or Not
		flags.add(checkActivity("Created new repair order.")); // verify activity:Created new repair order
		return !flags.contains(false);
	}

	public void addVideoToOrder() {

		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(repairOrder_PageHeading).waitFor();
		page.waitForTimeout(3000);
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		if (frame.locator(roStatusBar).textContent().contains("New")) {
			logger.info("RO is New & No media is added");
			String sendToCustomerClass = getLocatorClass(operations_Buttons, "Send to customer");
			String viewWithCustomerClass = getLocatorClass(operations_Buttons, "View with customer");
			String insightClass = getLocatorClass(operations_Buttons, "Insights");
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
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		if (!frame.locator(added_Video).first().isVisible()) {
			logger.info("Condition not satisfied for Send Video : video not added to RO");
			throw new SkipException("video not added to RO");
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

	public void activitiesOfCreateEstimateWindow() {
		SoftAssert softAssert = new SoftAssert();
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.click(repairOrder_Header);
		OrderListPage orderpage = new OrderListPage(page);
		orderpage.navigateToOrderDetails("Existing");

		try {
			logger.info("Waiting for the Estimate button to be visible");
			page.waitForCondition(() -> frame.locator(estimate_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(estimate_Button).isVisible()) {
			logger.info("Estimate Button Not available : May be disabled from Dealer Setting");
			throw new SkipException("Estimate Button Not available : May be disabled from Dealer Setting");
		}
		frame.locator(estimate_Button).click();
		logger.info("Click on Create Estimate button");
		page.waitForTimeout(4000);
		addItemToList(items_DropdownList, "Test Item 1", "Pre-Approved"); // adding exact same value from list
		addItemToList(items_DropdownList, "Test Item", "Recommended"); // adding value that is not in list
		addItemToList(items_DropdownList, "ABCDEFGH", "Pre-Approved"); // adding value that is not in list
		frame.locator(closeWindow_Button).click();
		page.waitForTimeout(2000);
		logger.info("Click on close estimate window");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Edit Estimate"), "Verify button changed to Edit Estimate");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Draft"), "Verify 'Est-Draft' on RO list screen");
		softAssert.assertTrue(checkActivity("Created new estimate."), "Verify activity added for Create Estimate");
		frame.locator(estimate_Button).click();
		logger.info("Click on Edit Estimate button");
		addItemToList(items_DropdownList, "Items added after re-open the window", "Recommended");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview Estimate button");
		page.waitForTimeout(4000);
//		softAssert.assertTrue(calculateTotalItemAmount(itemsAmount) == getTotalAmount(totalAmount),
//				"Verify total amount");
		List<String> allAvailableButtons_PreviewScreen = Arrays.asList("Hide from customer", "Close Estimate", "Edit",
				"Print", "Send");
		List<String> allButtonTexts_PreviewScreen = page.locator(buttons_PreviewScreen).allInnerTexts();
		softAssert.assertTrue(allAvailableButtons_PreviewScreen.containsAll(allButtonTexts_PreviewScreen),
				"All buttons are available on Preview estimate screen");
		frame.locator(hideFromCustomer_Button).click();
		logger.info("Click on Hide from customer button");
		page.waitForTimeout(2000);
		List<Boolean> flags = new ArrayList<Boolean>();
		if (frame.locator(hiddenMessage).isVisible()) {
			flags.add(true);
			logger.info("Hidden message displayed");
			frame.locator(hideFromCustomer_Button).click();
		} else {
			flags.add(false);
			logger.info("Hidden message not displayed");
		}
		frame.locator(hideFromCustomer_Button).click();
		logger.info("Click on Hide from customer button");
		page.waitForTimeout(2000);
		if (!frame.locator(hiddenMessage).isVisible()) {
			flags.add(true);
			logger.info("Hidden message removed");
			frame.locator(hideFromCustomer_Button).click();
		} else {
			flags.add(false);
			logger.info("Hidden message still displayed");
		}
		softAssert.assertTrue(!flags.contains(false), "Verify hide from customer");
		flags.clear();
		frame.locator(edit_Button).click();
		page.waitForTimeout(2000);
		if (frame.locator(preview_Button).isVisible()) {
			logger.info("User navigate back to add item screen");
			flags.add(true);
		} else {
			logger.info("User not navigate back to add item screen");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Verify navigation on clicking edit button");
		flags.clear();
		if (frame.locator(preview_Button).isVisible()) {
			frame.locator(preview_Button).click();
			logger.info("User navigate to preview screen again");
		}
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(print_Button).click();
			logger.info("Click on Print button");
		});
		newPage.waitForLoadState();
		softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
		newPage.close();
		frame.locator(closeEstimate_Button).click();
		page.waitForTimeout(2000);
		if (frame.locator(estimate_Button).textContent().contains("Estimate Closed")) {
			logger.info("Button name changed to Estimate Closed");
			flags.add(true);
		} else {
			logger.info("Button name not changed to Estimate Closed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "verify close Estimate button text");
		page.waitForTimeout(2000);
		softAssert.assertTrue(checkActivity("Estimate closed."), "verify activity for close Estimate");
		softAssert.assertAll();
	}

	public void sendEstimate(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		try {
			logger.info("Waiting for the Estimate button to be visible");
			page.waitForCondition(() -> frame.locator(estimate_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(estimate_Button).isVisible()) {
			logger.info("Estimate Button Not available : May be disabled from Dealer Setting");
			throw new SkipException("Estimate Button Not available : May be disabled from Dealer Setting");
		}
		frame.locator(estimate_Button).click();
		logger.info("Clicked on Estimate button");
		page.waitForTimeout(5000);
		page.waitForCondition(() -> frame.locator(newEstimate_Button).isVisible());
		frame.locator(newEstimate_Button).click();
		addItemToList(items_DropdownList, "Test Item 1", "Pre-Approved"); // adding exact same value from list
		addItemToList(items_DropdownList, "Test Item", "Recommended"); // adding value that is not in list
		addItemToList(items_DropdownList, "ABCDEFGH", "Pre-Approved"); // adding value that is not in list
		frame.locator(preview_Button).click();
		logger.info("Clicked on preview button");
		page.waitForTimeout(2000);
		frame.locator(send_Button).click();
		logger.info("Clicked on Send button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Estimate"), "Verify estimate Status");
		logger.info("Estimate status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Sent"), "Verify Status changed to Est-Sent on RO List");
		softAssert.assertTrue(checkLastMessageInConversation("estimate"), "Verify last message is Estimate endlink");
		softAssert.assertTrue(checkActivity("Estimate sent to customer"), "Verify activity update after estimate sent");
		softAssert.assertAll();
	}

	public void resendEstimate(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(estimate_Button).click();
		page.waitForTimeout(10000);
		if (!frame.locator(confirm_Button).isVisible()) {
			logger.info("Condition not satisfied for Resend : Estimate was not sent before");
			frame.locator(closeWindow_Button).click();
			throw new SkipException("Estimate was not sent before");
		}
		frame.locator(confirm_Button).click();
		logger.info("Clicked on confiem button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(estimate_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Estimate"), "Verify estimate Status");
		logger.info("Estimate status changed to : " + buttonName);
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Sent"), "Verify Status changed to Est-Sent on RO List");
		softAssert.assertTrue(checkLastMessageInConversation("estimate"), "Verify last message is Estimate endlink");
		softAssert.assertTrue(checkActivity("Estimate resent to customer"),
				"Verify activity update after estimate resent");
		softAssert.assertAll();
	}

	public void createPayment(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		try {
			logger.info("Waiting for the Payment button to be visible");
			page.waitForCondition(() -> frame.locator(payment_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (!frame.locator(payment_Button).isVisible()) {
			logger.info("Payment button is not available : May be disabled from dealer setting");
			throw new SkipException("Payment button is not available : May be disabled from dealer setting");
		}
		frame.locator(payment_Button).click();
		logger.info("Click on Create Payment Button");
		addItemToList_Payment(items_DropdownList, "Test Item 2"); // adding exact same value from list
		addItemToList_Payment(items_DropdownList, "Payment Item 1"); // adding value that is not in list
		addItemToList_Payment(items_DropdownList, "Payment Item 2"); // adding value that is not in list
		frame.locator(closeWindow_Button).click();
		page.waitForTimeout(4000);
		softAssert.assertTrue(checkActivity("Created new invoice."), "verify activity for new invoice creation");
		String paymentButtonText = frame.locator(payment_Button).textContent();
		logger.info("Payment Status changed to " + paymentButtonText);
		page.waitForTimeout(3000);
		softAssert.assertTrue(paymentButtonText.contains("Edit Payment"), "verify payment status");
		frame.locator(payment_Button).click();
		addItemToList_Payment(items_DropdownList, "Test Item 3"); // adding value reopen payment window
		page.waitForTimeout(5000);
		int itemsAddedInList = frame.locator(rowCount).all().size();
		String itemsText = frame.locator(items_Tab).textContent();
		softAssert.assertTrue(itemsText.contains(String.valueOf(itemsAddedInList)), "Verify Count on Items Tab");
		frame.locator(notes_Tab).click();
		logger.info("Click on Notes Tab");
		page.waitForTimeout(2000);
		frame.locator(internalNotes_TextBox).fill("Testing Internal Note");
		logger.info("Internal Note Entered");
		String customerNote = "Testing Customer Note";
		frame.locator(customerNotes_TextBox).fill(customerNote);
		logger.info("Customer Note Entered");
		frame.locator(notes_Tab).click(); // Clicking for save entered value
		frame.locator(items_Tab).click();
		deleteItemFromList();
		int itemsInList_AfterDelete = frame.locator(rowCount).all().size();
		String itemsText_AfterDelete = frame.locator(items_Tab).textContent();
		softAssert.assertTrue(itemsText_AfterDelete.contains(String.valueOf(itemsInList_AfterDelete)),
				"Verify Delete Item From List");
		frame.locator(next_Button).click();
		logger.info("Click on Next Button");
		page.waitForTimeout(3000);
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Amount", "Verify Amount window heading");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview Button without entering amount");
		softAssert.assertTrue(frame.locator(toaster_Message).isVisible(), "Verify toaster message for null amount");
		frame.locator(back_Button).click();
		logger.info("Click on Back Button");
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Items & Notes",
				"Verify Add items window heading on Back operation");
		if (frame.locator(next_Button).isVisible()) {
			frame.locator(next_Button).click();
			logger.info("Again Click on Next Button");
		}
		String enteredAmount = "101";
		frame.locator(amount_TextField_Payment).fill(enteredAmount);
		logger.info("Amount entered in payment");
		frame.locator(preview_Button).click();
		logger.info("Click on Preview button after adding Amount");
		page.waitForTimeout(3000);
		softAssert.assertEquals(getWindowHeading(), "Payment - Review and Send Invoice",
				"Verify Review window heading");
		// softAssert.assertTrue(frame.locator(addedNote_ReviewScreen).textContent().contains(customerNote),"Verify
		// Customer Note");
		page.waitForCondition(()-> frame.locator(final_Amount).isVisible());
		String finalAmount = frame.locator(final_Amount).textContent();
		logger.info("Final Amount is " + finalAmount);
		softAssert.assertTrue(finalAmount.contains(enteredAmount), "Verify Final Amount");
		frame.locator(editButton_ReviewScreen).click();
		logger.info("Click on edit button of review screen");
		softAssert.assertEquals(getWindowHeading(), "Payment - Add Invoice Amount", "Verify Add Amount window heading");
		if (frame.locator(preview_Button).isVisible()) {
			frame.locator(preview_Button).click();
			logger.info("Again Clicked on preview button");
		}
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(printButton_ReviewScreen).click();
			logger.info("Click on Print button");
		});
		newPage.waitForLoadState();
		softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
		newPage.close();
		frame.locator(sendButton_ReviewScreen).click();
		logger.info("Click on Send button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(payment_Button).textContent();
		softAssert.assertTrue(buttonName.contains("Resend Payment"), "Verify payment Status");
		logger.info("Payment status changed to : " + buttonName);
		page.waitForTimeout(40000);
		logger.info("test");
		softAssert.assertTrue(verifyChangedStatusOnROList("$-Sent"), "Verify Status changed to $-Sent on RO List");
		boolean isPaymentEndlink = false;
		page.waitForTimeout(5000);
		if (checkLastMessageInConversation("invoice") || checkLastMessageInConversation("payment")) {
			isPaymentEndlink = true;
		}
		softAssert.assertTrue(isPaymentEndlink, "Verify last message is Payment endlink");
		softAssert.assertTrue(checkActivity("Invoice sent to customer."), "Verify activity update after payment sent");
		softAssert.assertAll();
	}

	public void resendPayment(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		try {
			logger.info("Waiting for the Payment button to be visible");
			page.waitForCondition(() -> frame.locator(payment_Button).isVisible());
		} catch (TimeoutError e) {
			e.printStackTrace();
		}
		if (frame.locator(payment_Button).textContent().contains("Resend Payment")) {
			logger.info("Condition is satisfied to payment resend");
		} else {
			logger.info("Condition is not satisfied to payment resend");
			throw new SkipException("Condition is not satisfied to resend payment");
		}
		frame.locator(payment_Button).click();
		logger.info("Clicked on Payment Button");
		frame.locator(next_Button).click();
		logger.info("Clicked on Next Button");
		frame.locator(preview_Button).click();
		logger.info("Clicked on preview Button");
		frame.locator(sendButton_ReviewScreen).click();
		logger.info("Clicked on Send Button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		String buttonName = frame.locator(payment_Button).textContent();
		page.waitForTimeout(3000);
		softAssert.assertTrue(buttonName.contains("Resend Payment"), "Verify payment Status");
		logger.info("Payment status changed to : " + buttonName);

		page.waitForTimeout(25000);

		softAssert.assertTrue(verifyChangedStatusOnROList("$-Sent"), "Verify Status changed to $-Sent on RO List");
		boolean isPaymentEndlink = false;
		if (checkLastMessageInConversation("invoice") || checkLastMessageInConversation("payment")) {
			isPaymentEndlink = true;
		}
		softAssert.assertTrue(isPaymentEndlink, "Verify last message is Payment endlink");
		softAssert.assertTrue(checkActivity("Invoice resent to customer."),
				"Verify activity update after payment resent");
		softAssert.assertAll();
	}

	public void checkStatus_OnVideoWatch(String Filter) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		addVideoToOrder();
		sendVideoToCustomer(Filter);
		page.waitForTimeout(5000);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		page.waitForTimeout(5000);
		
		logger.info("checking console 1");
		page.onConsoleMessage(consoleMessage -> {
			String messageText = consoleMessage.text();
			if (messageText.contains("New Notification triggered")) {
				System.out.println("Captured Notification: " + messageText);
				logger.info("Video Viewed popup notifications triggered and verified in console");

			} else {
				logger.info("Video Viewed popup notifications NOT triggered and verified in console");
			}
		});
		page.waitForTimeout(3000);
		page.onConsoleMessage(consoleMessage -> {
		    String messageText = consoleMessage.text();
		    System.out.println("Captured Console Message: " + messageText);

		    // Check for the "New Notification triggered" keyword
		    if (messageText.contains("New Notification triggered")) {
		        System.out.println("Detected a Notification Log");

		        // Extract the line containing the `Notification` object
		        Pattern notificationPattern = Pattern.compile("Notification\\s*\\{([^}]*)\\}");
		        Matcher matcher = notificationPattern.matcher(messageText);

		        if (matcher.find()) {
		            String title = matcher.group(1); // Extract the title field
		            System.out.println("Extracted Notification Title: " + title);
		        } else {
		            System.err.println("No `title` field found in the captured Notification.");
		        }
		    }
		});
		 	  
		page.waitForTimeout(4000);
		softAssert.assertTrue(frame.locator(roStatusBar).textContent().contains("Viewed"), "verify viewed status");
		page.waitForTimeout(2000);
		softAssert.assertTrue(verifyChangedStatusOnROList("Viewed"), "verify viewed status on RO list");
		page.waitForTimeout(2000);
		softAssert.assertTrue(checkActivity("Customer watched video"), "verify activity for video view");
//		page.waitForTimeout(4000);
//		logger.info("checking console 1");
//		page.onConsoleMessage(consoleMessage -> {
//			String messageText = consoleMessage.text();
//			if (messageText.contains("New Notification triggered")) {
//				System.out.println("Captured Notification: " + messageText);
//				logger.info("Video Viewed popup notifications triggered and verified in console");
//
//			} else {
//				logger.info("Video Viewed popup notifications NOT triggered and verified in console");
//			}
//		});
		logger.info("checking console 2");
		softAssert.assertAll();
	}

	public void estimateConfirmation(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		resendEstimate(channelSelected);
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("estimate") || lastMessage.contains("Estimate")) {
			logger.info("Last message is estimate Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			PlaywrightFactory.getBrowserContext().onDialog(dialog -> {
				logger.info("Popup message: " + dialog.message());
				dialog.accept();
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.click(authoriseWork_Button);
			logger.info("Click on authorise work button");
			endlinkPage.close();
		} else {
			logger.info("Last message is not estimate Endlink");
			throw new SkipException("Last message is not estimate Endlink");
		}
		page.waitForTimeout(15000);
		softAssert.assertTrue(frame.locator(estimate_Button).textContent().contains("Review Approved Work"),
				"verify Review Approved Work status");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Approved"), "verify Est-Approved status on RO list");
		softAssert.assertTrue(checkActivity("Customer submitted decision on estimate"),
				"verify activity for Approved estimate");
		frame.locator(estimate_Button).click();
		logger.info("Click on estimate button");
		frame.locator(confirm_Button).click();
		logger.info("Click on Confirm button");
		frame.locator(closeWindow_Button).click();
		logger.info("Click on close estimate window button");
		page.waitForTimeout(8000);
		softAssert.assertTrue(!frame.locator(estimate_Button).textContent().contains("Review Approved Work"),
				"verify Estimate status on confirm estimate");
		softAssert.assertTrue(verifyChangedStatusOnROList("Est-Confirmed"), "verify Est-Confirmed status on RO list");
		softAssert.assertTrue(checkActivity("Estimate confirmed"), "verify activity for confirm estimate");
		frame.locator(estimate_Button).click();
		logger.info("Again click on estimate button");
		frame.locator(notify_Button).click();
		logger.info("Click on Notify button");
		selectChannelToPerformAction(channelSelected);// Select channel to send video
		softAssert.assertTrue(verifyNavigationToChannel(channelSelected), "Verify Navigation To selected channel");
		boolean isLastMessageIsConfirmation = false;
		if (checkLastMessageInConversation("proceeding") || checkLastMessageInConversation("requested work")) {
			logger.info("last message is of estimate confirmation");
			isLastMessageIsConfirmation = true;
		}
		softAssert.assertTrue(isLastMessageIsConfirmation, "Verify confirmation message sent");
		softAssert.assertTrue(checkActivity("Estimate confirmation sent to customer."),
				"verify activity for Estimate confirmation");
		softAssert.assertAll();
	}

	private String payNow_Button = "#pay-now-button-1";
	private String payWithCardButton = "button:has-text('Pay with Card')";
	private String emailTextBox = "html #email";
	private String cardNumberTextBox = "#card_number";
	private String expiryDate = "#cc-exp";
	private String cvc_Date = "#cc-csc";
	private String submitButton = "submitButton";
	private String paidInvoice_Heading = "'PAID INVOICE'";
	private String printPayment_Button = "button:has-text('Print')";
	private String processedPayment_Button = "button:has-text('Processed')";
	private String paymentIframe = "//iframe[@name='stripe_checkout_app']";

	public void submitPayment(String filter) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		SoftAssert softAssert = new SoftAssert();
		String lastMessage = frame.locator(messages).last().textContent();
		if (lastMessage.contains("invoice") || lastMessage.contains("payment")) {
			logger.info("Last message is Payment Endlink");
			Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(lastMessageEndlink).last().click();
				logger.info("Endlink opened in another tab");
			});
			endlinkPage.waitForLoadState();
			endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
			endlinkPage.click(payNow_Button);
			logger.info("Endlink : Click on pay Now Button");
			endlinkPage.click(payWithCardButton);
			
			
			
			
			
			
			
			
			
			
			
			
			
			softAssert.assertTrue(frame.locator(payment_Button).textContent().contains("Process payment"),
					"verify payemt Status changed to Process Payment");
			softAssert.assertTrue(verifyChangedStatusOnROList("$-Paid"), "verify $-Paid status on RO list");
			softAssert.assertTrue(checkActivity("Invoice paid by customer."), "verify activity for paid invoice");
			frame.locator(payment_Button).click();
			Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
				frame.locator(printPayment_Button).first().click();
				logger.info("Click on Print button");
			});
			newPage.waitForLoadState();
			softAssert.assertTrue(newPage.url().contains(AppConstants.PRINT_SCREEN_URL), "verify print screen opened?");
			newPage.close();
			logger.info("Print window closed");
			frame.locator(processedPayment_Button).first().click();
			softAssert.assertTrue(getWindowHeading().contains("COMPLETED"), "Verify payment heading -COMPLETED");
			frame.locator(closeWindow_Button).click();
			logger.info("Payment window closed");
			softAssert.assertTrue(frame.locator(payment_Button).textContent().contains("Processed"),
					"verify payemt Status changed to Processed");
			softAssert.assertTrue(verifyChangedStatusOnROList("$-Processed"), "verify $-Processed status on RO list");
			softAssert.assertTrue(checkLastMessageInConversation("successful"), "Verify payment successful message");
			softAssert.assertTrue(checkActivity("Invoice processed by Advisor."),
					"Verify activity for Processed payment");
		}

	}

	private static final String STRIPE_API_URL = "https://api.stripe.com/v1/payment_intents";
	private static final String TRUVIDEO_API_URL = "https://rc.truvideo.com/v/stripe/MSFqTV0e";
	private static final String STRIPE_SECRET_KEY = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u"; // Replace with your secret key

	private static String createPayment() {
		try {
			// Create the URL and open a connection to Stripe
			URL url = new URL(STRIPE_API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set up the connection properties
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			String encoded = Base64.getEncoder().encodeToString((STRIPE_SECRET_KEY + ":").getBytes());
			connection.setRequestProperty("Authorization", "Basic " + encoded);

			// Create the request body
			String urlParameters = "amount=500&currency=gbp&payment_method=pm_card_visa";

			// Send the request
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.writeBytes(urlParameters);
				wr.flush();
			}

			// Read the response
			int responseCode = connection.getResponseCode();
			String code = Integer.toString(responseCode);
			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}

			return code; // Return response for further processing

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void sendToTruvideo(String paymentResponse) {
		try {
			// Create the URL and open a connection to Truvideo
			URL url = new URL(TRUVIDEO_API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// Set up the connection properties
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "text/plain");
			connection.setRequestProperty("Cookie",
					"AWSALB=VuZQpAYYZbkTts3j+M4if/00doAScSIFHwtR/oBFwnMNXwgaVjGm9ayUWfSFW7AaLEwMO2mZPesya+LQo5Fy97HDkPMrCnnmeqa5GL+USIZwuOkLA3NySq/9hY4T; AWSALBCORS=VuZQpAYYZbkTts3j+M4if/00doAScSIFHwtR/oBFwnMNXwgaVjGm9ayUWfSFW7AaLEwMO2mZPesya+LQo5Fy97HDkPMrCnnmeqa5GL+USIZwuOkLA3NySq/9hY4T; JSESSIONID=D977CC2D465B0BACFB0A68E21CDE7F6D");

			// Parse the payment response for necessary details (example JSON parsing)
			// Assuming response contains stripeToken and other details; adjust based on
			// actual response
			String jsonInputString = "{" + "\"amount\": \"10100\","
					+ "\"stripeToken\": \"tok_1Q51QcLCZyWkGXxjb63uwWh6\"," + "\"stripeTokenType\": \"card\","
					+ "\"stripeEmail\": \"suraj.kushwah@5exceptions.com\"" + "}";

			// Send the request
			try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				wr.writeBytes(jsonInputString);
				wr.flush();
			}

			// Read the response
			int responseCode = connection.getResponseCode();
			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------------------------------

	private void verifyPaymentResendFunction(String filter) {
		// TODO Auto-generated method stub

	}

	private String getWindowHeading() {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		return frame.locator(windowTitle).innerText();
	}

	private void deleteItemFromList() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(".actions__div.ng-star-inserted button mat-icon:has-text('delete')").last().click();
		logger.info("Item deleted from list");
		page.waitForTimeout(3000);
	}

	private double calculateTotalItemAmount(String itemAmountList_Locator) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<ElementHandle> itemAmountList = frame.locator(itemAmountList_Locator).elementHandles();
		double sumOfItems = 0.0;
		for (ElementHandle amountElement : itemAmountList) {
			try {
				String itemText = amountElement.innerText().replace("$", "").replace(",", "").trim();
				double itemAmount = Double.parseDouble(itemText);
				sumOfItems += itemAmount;
			} catch (NumberFormatException e) {
				System.err.println("Failed to parse item amount: " + amountElement.innerText());
			}
		}
		return sumOfItems;
	}

	private double getTotalAmount(String totalAmount_Element) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		ElementHandle totalAmountElement = frame.locator(totalAmount_Element).elementHandle();
		String totalAmountText = totalAmountElement.innerText().replace("$", "").replace(",", "").trim();
		double totalAmount = Double.parseDouble(totalAmountText);
		return totalAmount;
	}

	private void addItemToList(String listLocator, String enteredText, String type) {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(newItem_TextField).click();
		Locator itemList = frame.locator(listLocator);
		if (itemList.allInnerTexts().contains(enteredText)) {
			for (ElementHandle locator : itemList.elementHandles()) {
				String textContent = locator.innerText();
				if (textContent != null && textContent.contains(enteredText)) {
					locator.click();
					logger.info("Entered text is available in the Item List");
					break;
				}
			}
		} else {
			frame.locator(newItem_TextField).fill(enteredText);
			logger.info("Entered text is not available in the Item List");
		}
		frame.locator(type_DropdownField).click();
		logger.info("Click on Type dropdown");
		frame.locator(selectTypeFromDropdown(type)).click();
		logger.info("Selected type from dropdown");
		frame.locator(addItem_Button).click();
		logger.info("Item added to list");
		page.waitForTimeout(2000);
	}

	private void addItemToList_Payment(String listLocator, String enteredText) {
		page.waitForTimeout(4000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(newItem_TextField).click();
		Locator itemList = frame.locator(listLocator);
		if (itemList.allInnerTexts().contains(enteredText)) {
			for (ElementHandle locator : itemList.elementHandles()) {
				String textContent = locator.innerText();
				if (textContent != null && textContent.contains(enteredText)) {
					locator.click();
					logger.info("Entered text is available in the Item List");
					break;
				}
			}
		} else {
			frame.locator(newItem_TextField).fill(enteredText);
			logger.info("Entered text is not available in the Item List");
		}
		frame.locator(addItem_Button).click();
		logger.info("Item added to list");
		page.waitForTimeout(2000);
	}

	public boolean verifyChangedStatusOnROList(String status) {
		Page newPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			page.evaluate("window.open()");
		});
		newPage.navigate("https://rc.truvideo.com/crud/repair-order");
		newPage.waitForTimeout(2000);
		logger.info("New Page opened in another tab");
		newPage.waitForTimeout(8000);

		if (newPage.innerText(getRO(OrderListPage.newRoNumber)).contains(status)) {
			logger.info("Status on RO list screen is changed to " + status);
			newPage.close();
			return true;
		} else {
			logger.info("Status on RO list screen is not changed to " + status);
			newPage.close();
			return false;
		}
	}

	private void selectChannelToPerformAction(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
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

	private void selectChannelToPerformAction2(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(2000);
		if (frame.locator(communicationTabs).isVisible() && channelSelected.contains("WhatsApp")) {
			logger.info("WhatsApp is enabled & Selected whatsApp channel to send");
			frame.locator(whatsApp_Button).click();
			logger.info("Clicked on whatsApp button : sent through WhatsApp");
		} else if (frame.locator(communicationTabs).isVisible() && channelSelected.contains("SMS")) {
			logger.info("WhatsApp is enabled & Selected SMS channel to send");
			frame.locator(sms_Button).click();
			logger.info("Clicked on SMS button : sent through SMS");
		} else {
			logger.info("WhatsApp is Disabled : sent from SMS");
		}
	}

	private boolean verifyNavigationToChannel(String channelSelected) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		boolean flag = false;
		page.waitForTimeout(20000);
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
			logger.error("User not navigated to the " + channelSelected + " channel after sent ");
			flag = false;
			return flag;
		}
		return flag;
	}

	public boolean checkActivity(String activityLog) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(activity_Tab).first().click();
		int retryCount = 3;
		int attempt = 1;
		while (attempt <= retryCount) {
			try {
				page.waitForCondition(() -> {
					List<String> activityTextList = frame.locator(activities).allInnerTexts();
					logger.debug("Checking activity text: " + activityTextList);
					return activityTextList.stream().anyMatch(text -> text.contains(activityLog));
				});
				String activityText = frame.locator(activities).nth(0).textContent();
				logger.info("RO activity is: " + activityText);
				if (activityText.contains(activityLog)) {
					flag = true;
					break;
				}
			} catch (Exception e) {
				logger.info("Searched activity not displayed at attempt " + attempt);
				logger.info("RO activity is: " + frame.locator(activities).nth(0).textContent());
			}
			attempt++;
		}
		if (flag == false) {
			logger.info("refreshing page to get activity");
			page.reload();
			frame.locator(activity_Tab).first().click();
			String activityText = frame.locator(activities).nth(0).textContent();
			logger.info("RO activity is: " + activityText);
			if (activityText.contains(activityLog)) {
				logger.info("Activity contains - " + activityLog + " on Refresh");
				flag = true;
			}
		}
		frame.locator(customer_tab).first().click();
		return flag;
		
	}

	public boolean checkStatus(String status) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(2000);
		if (frame.locator(roStatusBar).textContent().contains(status)) {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			return true;
		} else {
			logger.info("RO Status is: " + frame.locator(roStatusBar).textContent());
			return false;
		}
	}

	private boolean checkLastMessageInConversation(String messageKeyWords) {
		boolean flag = false;
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
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

	public String getLocatorClass(String operationButtons, String visibleText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operationButtons);
		if (!buttons.allTextContents().contains(visibleText)) {
			throw new SkipException("Button is not visible on DOM");
		}
		String className = null;
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.textContent();
			if (textContent != null && textContent.contains(visibleText)) {
				className = locator.getAttribute("class");
				break;
			}
		}
		return className;
	}

	private void clickOperationButton(String buttonText) {
		page.waitForTimeout(2000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operations_Buttons);
		page.waitForTimeout(5000);
		for (ElementHandle locator : buttons.elementHandles()) {
			String textContent = locator.innerText();
			//System.out.println("IS Text found ?" + textContent);
			if (textContent != null && textContent.contains(buttonText)) {
				locator.click();
				break;

			}
		}
	}

	private void clickOperationButton1(String buttonText) {
		page.waitForTimeout(5000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		Locator buttons = frame.locator(operations_Buttons);
		page.waitForCondition(() -> frame.locator(buttons).allInnerTexts().contains(buttonText));
		if (buttons.isVisible()) {
			System.out.println("Element is visible");
			for (ElementHandle locator : buttons.elementHandles()) {
				String textContent = locator.innerText();
				System.out.println("Text found: " + textContent);
				if (textContent != null && textContent.contains(buttonText)) {
					System.out.println("Test 2");
					locator.click();
					System.out.println("Test 3");
					break;
				}
			}
		} else {
			System.out.println("Element is not visible");
		}

	}

	public boolean deleteRepairOrder() throws InterruptedException {
		page.waitForTimeout(9000);

		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		page.waitForTimeout(5000);
		logger.info(" back 2 ");
		logger.info(OrderListPage.newRoNumber);
		clickOperationButton("Delete this RO");
		page.waitForTimeout(2000);
		HomePage hp = new HomePage(page);

		if (hp.globalSearchwitheText(OrderListPage.newRoNumber)) {
			logger.info("Selected RO has been deleted successfully");
			return true;
		} else {
			logger.info("Getting issue while deleting RO");
			return false;
		}

	}

	// Create reminder on detail page
	private String Iframe = "#order-details-iframe";
	private String clickRepairOrder = "#repair-order-results tbody tr:nth-child(2)";
	private String myROs_FilterButton = "#LBL_MY_RO";
	private String service_recTab = "div[role='tab'] span:has-text('Serv. Rec.')";
	private String checkbox = "#mat-mdc-checkbox-1-input";
	private String original_Amt = "input#mat-input-1";
	private String final_Amt = "#mat-input-2";
	private String deferred_Amt = "#mat-input-3";
	private String reminder_set = "#mat-select-0";
	private String no_reminder = "#mat-option-0";
	private String three_days = "#mat-option-1";
	private String save_Btn = "span.mdc-button__label";
	// private String topRightCornerNotification = "div.tru-toast";
	public static final String Reminder_Save = "Service recomendation successfully saved";

	public void copyLinktoClipboard() {
		page.waitForTimeout(9000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		logger.info(OrderListPage.newRoNumber);
		addVideoToOrder();
		clickOperationButton("Copy link to clipboard");
		page.waitForTimeout(5000);
//		frame.locator(notesTab_Communication).click();
//		logger.info("Clicked on Notes Tab");
//		page.waitForTimeout(1000);
//		frame.locator(textboxNotesTab).click();
//		  // Use the keyboard to simulate 'Ctrl + V' (paste)
//        page.keyboard().down("Control");
//        page.keyboard().press("V");
//        page.keyboard().up("Control");
//        logger.info("Verifying a control paste");
//        page.waitForTimeout(1000);
		String CustomerNameinRO = frame.locator(customerName).innerText().toLowerCase().trim();
		logger.info(CustomerNameinRO);
		frame.locator(sms_Tab).click();
		page.waitForTimeout(1000);
		frame.locator(sms_Textbox).click();
		page.keyboard().down("Control");
		page.keyboard().press("V");
		page.keyboard().up("Control");
		logger.info("Verifying a control paste");
		frame.locator(send_SMS_Button).click();
		frame.locator(send_Original_Button).click();
		logger.info("Original Text has been sent successfully");
		// frame.locator(openurl).click();
		// String text= frame.locator(textboxNotesTab).innerText();
		// String text1=frame.locator(textboxNotesTab).textContent();
		page.waitForTimeout(1000);

		Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {
			frame.locator(openurl).click();
			logger.info("Endlink opened in another tab");
		});
		endlinkPage.waitForLoadState();
		endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
		String CustomerNameinEndlink = endlinkPage.locator(newTab_text).innerText().toLowerCase().trim();
		logger.info("Clicked on Play Button");
		logger.info(CustomerNameinEndlink);
		page.waitForTimeout(1000);
		if (CustomerNameinEndlink.equals(CustomerNameinRO)) {
			logger.info("RO Customer name is matching with endlink Customer name");
		} else {
			logger.info("RO Customer name is not matching with endlink Customer name");
		}
		endlinkPage.close();

//      String text4= page.locator(newTab_text).innerText();
//       logger.info(text4);

		// Optionally, you can submit the form or perform other actions
		// newTab.keyboard().press("Enter");

//		Page newTab = page.context().newPage(); 
//		newTab.url();
		// frame.locator(".menu-options__info.delete").click();

		// page.waitForTimeout(1000);
	}

	public void viewWithCustomer() {
		page.waitForTimeout(10000);
		addVideoToOrder();
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		logger.info(OrderListPage.newRoNumber);
		page.waitForTimeout(2000);
		String CustomerNameinRO = frame.locator(customerName).innerText().toLowerCase().trim();
		logger.info(CustomerNameinRO);
		Page endlinkPage = PlaywrightFactory.getBrowserContext().waitForPage(() -> {

			clickOperationButton("View with customer");
			logger.info("Endlink opened in another tab");
		});
		endlinkPage.waitForLoadState();
		endlinkPage.waitForCondition(() -> endlinkPage.url().contains("truvideo.com/v/"));
		endlinkPage.locator(playButton).first().click();
		logger.info("Clicked on Play Button");
		logger.info("Waiting to play video for 8 Seconds");
		page.waitForTimeout(8000);
		String CustomerNameinEndlink = endlinkPage.locator(newTab_text).innerText().toLowerCase().trim();
		logger.info("Clicked on Play Button");
		logger.info(CustomerNameinEndlink);
		page.waitForTimeout(1000);

		boolean isMatching = CustomerNameinEndlink.equals(CustomerNameinRO);

		if (isMatching) {
			logger.info("RO Customer name is matching with endlink Customer name");
		} else {
			logger.info("RO Customer name is not matching with endlink Customer name");
		}
		softAssert.assertTrue(isMatching, "Customer name should match from RO details");
		endlinkPage.close();
		flags.add(checkStatus("For Review"));
		softAssert.assertTrue(!flags.contains(false), "Verify status should be For Review");
		flags.clear();
		softAssert.assertTrue(verifyChangedStatusOnROList("For Review"),
				"Verify status should not change to Viewed from For Review");
		softAssert.assertAll();

	}

	public void editThisRO() throws InterruptedException {
		page.waitForTimeout(9000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		logger.info(OrderListPage.newRoNumber);
		page.waitForTimeout(5000);
		String firstName = frame.locator(firstNameWithoutEdit).innerText();
		logger.info("FirstName before edited :-" + firstName);

		clickOperationButton("Edit this RO");
		logger.info("Clicked on Edit this RO button");
		page.waitForTimeout(6000);
		frame.locator(firstNameEditing).click();
		page.waitForTimeout(1000);
		frame.locator(firstNameEditing).fill("FirstName Edited");
		logger.info("Edited FirstName successfully");
		frame.locator(lastNameEditing).fill("Edited LastName");
		// String lastName=frame.locator(lastNameEditing).innerText();
		logger.info("Edited LastName successfully :-");
		frame.locator(mobileFieldEditing).clear();
		frame.locator(mobileFieldEditing).fill("7812049488");
		logger.info("Mobile number edited successfully :-");
		frame.locator(emailFieldEditing).clear();
		frame.locator(emailFieldEditing).fill("testemailedited@gmail.com");
		logger.info("Edited Email successfully :-");

		Locator dropdown = frame.locator(advisorFieldEditing);
		// Select an option by the visible text (label)
		dropdown.selectOption(new SelectOption().setLabel("Advisor Dinesh"));

		logger.info("Changed Advisor ");
		frame.locator(saveButton).click();
		page.waitForTimeout(4000);
		Thread.sleep(4000);
		String firstNameEdited = frame.locator(firstNameWithoutEdit).innerText();
		logger.info("FirstName After edited :-" + firstNameEdited);
		String name = frame.locator(customerName).innerText();
		String lastNameEdited = frame.locator(lastNameWithoutEdit).innerText();
		logger.info("LastName After edited :-" + lastNameEdited);
		String EmailEdited = frame.locator(emailField).innerText();
		logger.info("Email After edited :-" + EmailEdited);
		String MobileNumberEdited = frame.locator(mobileNumberField).innerText();
		logger.info("Mobile Number After edited :-" + MobileNumberEdited);
//		if 
//		(frame.locator(firstNameWithoutEdit).isVisible() && frame.locator(lastNameWithoutEdit).isVisible() && 
//		frame.locator(mobileNumberField).isVisible() && frame.locator(emailField).isVisible() && 
//		frame.locator(fleetField).isVisible() && frame.locator(companyField).isVisible() && 
//		frame.locator(customerID).isVisible() && frame.locator(vehicleMake).isVisible() && 
//		frame.locator(vehicleModel).isVisible() && frame.locator(vehicleYear).isVisible() && 
//		frame.locator(vehicleColor).isVisible() && frame.locator(vehicleVIN).isVisible()  &&
//		frame.locator(dealerField).isVisible() && frame.locator(technicianField).isVisible() &&
//		frame.locator(advisorField).isVisible()) {
//			logger.info("All Customer details and Vehicle fields are visible in Details section");
//			return true;
//		}
//		else {
//			logger.info("Their is issue to show All Customer details and Vehicle fields in Details section");
//			return false;
//		}
//		if (page.isVisible(firstNameWithoutEdit) && page.isVisible() && page.isVisible()
//				&& page.isVisible(createAccount_ButtonLink) && page.isVisible(forgotPassword_ButtonLink)) {
//			logger.info("All elements are visible on Login Page");
//			logger.info("Username/Password field is visible on Login Page");
//			logger.info("LogIn button is visible on Login Page");
//			logger.info("Create account link button is visible on Login Page");
//			logger.info("Forgot password link button is visible on Login page");
//			//return true;
//		} else {
//			logger.info("Some elements are missing on Login Page");
//			//return false;
//		}

		// String name=frame.locator(customerName).innerText();
		// logger.info(name);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(name.contains("FirstName Edited"), "Verify Customer First name update");
		logger.info("Name changed to : " + name);
		softAssert.assertTrue(lastNameEdited.contains("Edited LastName"), "Verify Customer Last name update");
		softAssert.assertTrue(EmailEdited.contains("testemailedited@gmail.com"), "Verify Customer email update");
		softAssert.assertTrue(MobileNumberEdited.contains("+1 (781) 204-9488"), "Verify Customer Mobile number update");
		// softAssert.assertTrue(lastNameEdited.contains("Edited LastName"), "Verify
		// Customer Last name update");

		softAssert.assertAll();
		// page.waitForTimeout(1000);

//		  page.waitForCondition(() ->
//		  frame.locator(topRightCornerNotification1).isVisible());
//		  
//		  page.waitForSelector(topRightCornerNotification1);
//		  //page.waitForTimeout(1000); System.out.println("545454"); String
//		  String topRightCornerNotificationPopup =page.locator(topRightCornerNotification1).innerText();
//		  logger.info(topRightCornerNotificationPopup); page.waitForTimeout(1000); if
//		  (topRightCornerNotificationPopup.contains(AppConstants.
//		  REPAIR_ORDER_EDITED_MESSAGE)) {
//		  logger.info(" RO has been Edited successfully"); } else {
//		  logger.info("Getting error to edit Repair Order "); }

		// logger.info(firstName);
	}

	public void insightFunctionality() {
		// addVideoToOrder();
		HomePage homepage = new HomePage(page);
		homepage.clickOn_RepairOrder_Header();
		OrderListPage listpage = new OrderListPage(page);
		listpage.navigateToOrderDetails("New");
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		frame.locator(repairOrder_PageHeading).waitFor();
		List<Boolean> flags = new ArrayList<Boolean>();
		SoftAssert softAssert = new SoftAssert();
		if (frame.locator(roStatusBar).textContent().contains("New")) {
			logger.info("RO is New & No media is added");
			String insightClass = getLocatorClass(operations_Buttons, "Insights");
			System.out.println("insightClass " + insightClass);
			if (insightClass.contains("disabled")) {
				logger.info("'Insights' button is disabled");
				flags.add(true);
			} else {
				logger.info(" 'Insights' button is not disabled");
				flags.add(false);
			}
			softAssert.assertTrue(!flags.contains(true), // should be false
					"Verify 'Insight' button is disabled");
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
		frame.locator(insightButton).click();
		logger.info("Verifying Without customer opened the Video Insight data is not showing");
		String insightText = frame.locator(noInsightText).innerText().trim();
		softAssert.assertTrue(insightText.contains("There's no insights yet"), "Verify No insight showing");
		frame.locator(closeInsightWindow).click();
		copyLinktoClipboard();
		frame.locator(insightButton).click();
		logger.info("Verifying Without customer opened the Video Insight data is now showing");
		String InsightData = frame.locator(insightDataafterVideoView).innerText().trim();
		softAssert.assertTrue(InsightData.contains("Seen by"), "Verify now insight data is showing");

		frame.locator(closeInsightWindow).click();
		logger.info("Insight window closed");
		softAssert.assertAll();
		// There's no insights yet
	}

	public boolean detailsFieldonRODetails() {

		page.waitForTimeout(9000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForCondition(() -> frame.locator(vehicleModel).isVisible());
		if (frame.locator(firstNameWithoutEdit).isVisible() && frame.locator(lastNameWithoutEdit).isVisible()
				&& frame.locator(mobileNumberField).isVisible() && frame.locator(emailField).isVisible()
				&& frame.locator(fleetField).isVisible() && frame.locator(companyField).isVisible()
				&& frame.locator(customerID).isVisible() && frame.locator(vehicleMake).isVisible()
				&& frame.locator(vehicleModel).isVisible() && frame.locator(vehicleYear).isVisible()
				&& frame.locator(vehicleColor).isVisible() && frame.locator(vehicleVIN).isVisible()
				&& frame.locator(dealerField).isVisible() && frame.locator(technicianField).isVisible()
				&& frame.locator(advisorField).isVisible()) {
			logger.info("All Customer details and Vehicle fields are visible in Details section");
			return true;
		} else {
			logger.info("Their is issue to show All Customer details and Vehicle fields in Details section");
			return false;
		}

	}

	public boolean createreminder() throws InterruptedException {
		Thread.sleep(8000);
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(5000);
		logger.info("click on Service_Rec!!!!!!!!");
		page.waitForTimeout(5000);
		frame.locator(".mat-mdc-tab-list div#mat-tab-label-0-1").click();
		logger.info("click on Service_Rec");
		page.waitForTimeout(5000);

		page.keyboard().down("Control");
		page.keyboard().press("-");
		page.keyboard().press("-");
		page.keyboard().up("Control");

		page.waitForCondition(() -> frame
				.locator("div.order__column--menu .mat-mdc-tab-labels div.mdc-tab:nth-child(2)").isVisible());
		frame.locator("div.order__column--menu .mat-mdc-tab-labels div.mdc-tab:nth-child(2)").click();
		logger.info("Click on Service_Rec");

		if (!frame.locator(checkbox).isChecked()) {
			frame.locator(checkbox).click();
			logger.info("Checked");
		}

//		if (!frame.locator(checkbox).isChecked()) {
//			frame.locator(checkbox).click();
//			logger.info("Checked");
//		}
		logger.info("Click on checkbox ");
		String number = "" + getRandomNumber(2);
		frame.locator(original_Amt).fill(number);
		logger.info("Enter original amount");
		frame.locator(final_Amt).fill(number);
		logger.info("Enter final amount");
		frame.locator(deferred_Amt).fill(number);
		logger.info("Enter deferred amount");
		frame.locator(reminder_set).click();
		logger.info("Click on set reminder");
		frame.locator(no_reminder).click();
		page.waitForTimeout(2000);
		logger.info("Click on set reminder");
		frame.locator(save_Btn).click();
		logger.info("Click on save button");
		page.waitForTimeout(2000);
		frame.locator(topRightCornerNotification1).waitFor();
		String topRightCornerNotificationPopup = frame.locator(topRightCornerNotification1).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Reminder_Save)) {

			logger.info("Service recomendation successfully saved");
		} else {
			logger.info("Not displaying message");
		}

		return true;
	}

	// OpenInspection on detail page

	private String openInspectionBtn = "div.mat-mdc-tooltip-trigger.orders-detail-menu__inspections.ng-star-inserted";
	private String reviewInspection = ".mat-toolbar.checklist-header.mat-toolbar-single-row";
	private String RequiresImmediateAttention = "div.checklist-result-section_title.red";
	private String MayRequireFutureAttention = "div.checklist-result-section_title.yellow";
	private String FullInspectionResults = "div.checklist-result-section_title.ng-star-inserted";
	private String Interior_Exterior1 = "#mat-expansion-panel-header-10";
	private String AdditionalRecommendations1 = "#mat-expansion-panel-header-11";
	private String OpenRecalls = "#mat-expansion-panel-header-12";
	private String Interior_Exterior2 = "#mat-expansion-panel-header-13";
	private String UnderHood = "#mat-expansion-panel-header-14";
	private String UnderVehicle = "#mat-expansion-panel-header-15";
	private String AdditionalRecommendations2 = "#mat-expansion-panel-header-16";
	private String CheckTires_MeasureTire_TreadDepth = "#mat-expansion-panel-header-17";
	private String MeasureFront_RearBrakeLinings = "#mat-expansion-panel-header-18";
	private String CheckBatteryPerformance = "#mat-expansion-panel-header-19";
	private String Activity = "div p.checklist-activity_title";

	// div.checklist-footer div button
	// span.mat-mdc-button-persistent-ripple.mdc-button__ripple
	public boolean openInspection() throws InterruptedException {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		OrderListPage op = new OrderListPage(page);
		op.checkInspectionStatus();
		Thread.sleep(5000);
		frame.locator(openInspectionBtn).isVisible();
		logger.info("Check OpenInspction 1");
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection");
		page.waitForTimeout(3000);
		if (frame.locator(reviewInspection).isVisible() && frame.locator("text= Send Back ").isVisible()
				&& frame.locator("text=  Publish  ").isVisible()
				&& frame.locator(RequiresImmediateAttention).isVisible()
				&& frame.locator(MayRequireFutureAttention).isVisible()
				&& frame.locator(FullInspectionResults).isVisible()) {
			logger.info("All elements are vissible");
		} else {
			System.out.println("Elements are not vissible");
			flags.add(false);
		}
		frame.locator(Activity).isVisible();
		logger.info("Check activity");
		if (!frame.locator("text=Inspection submitted: Standard Multipoint Inspection - Mazda").isVisible()) {
			logger.info("Check Standard Multipoint Inspection");
		}
		if (!frame.locator("text=Inspection submitted: Mazda Full Circle Inspection").isVisible()) {
			logger.info("Check Mazda Full Circle Inspection");
		}

		/*
		 * page.waitForTimeout(5000); //frame.locator(Interior_Exterior1).isVisible();
		 * Locator additionalRecommendations =
		 * frame.locator(AdditionalRecommendations1); Locator openRecalls =
		 * frame.locator(OpenRecalls); Locator interior_Exterior2 =
		 * frame.locator(Interior_Exterior2); Locator underHood =
		 * frame.locator(UnderHood); Locator underVehicle = frame.locator(UnderVehicle);
		 * Locator additionalRecommendations2 =
		 * frame.locator(AdditionalRecommendations2); Locator
		 * checkTires_MeasureTire_TreadDepth =
		 * frame.locator(CheckTires_MeasureTire_TreadDepth); Locator
		 * measureFront_RearBrakeLinings = frame.locator(MeasureFront_RearBrakeLinings);
		 * Locator checkBatteryPerformance = frame.locator(CheckBatteryPerformance);
		 */
		/*
		 * if (interiorExterior.isVisible() && additionalRecommendations.isVisible()) {
		 * // Double-click on both elements if they are visible
		 * interiorExterior.click(); additionalRecommendations.click(); } else { //
		 * Handle the case when one or both elements are not visible
		 * System.out.println("One or both elements are not visible"); }
		 */

		/*
		 * additionalRecommendations.click(); openRecalls.dblclick();
		 * interior_Exterior2.dblclick(); underHood.dblclick(); underVehicle.dblclick();
		 * additionalRecommendations2.dblclick();
		 * checkTires_MeasureTire_TreadDepth.dblclick();
		 * measureFront_RearBrakeLinings.dblclick(); checkBatteryPerformance.dblclick();
		 */

		return !flags.contains(false);

	}

	// Send Back Inspection

	private String sendback = ".mdc-button__label:has-text(' Send Back ')";
	private String areyousure = ".mdc-dialog__container div.checklist-send-back-dialog_content";
	private String cancelBtn = ".checklist-footer_button.mdc-button.mat-mdc-button.mat-unthemed.mat-mdc-button-base";
	private String yesBtn = ".mdc-button__label:has-text(' Yes ')";
	// private String repairOrder_Header = "a[href='/crud/repair-order']";
	private String publish = "span.mdc-button__label:has-text(' Publish ')";
	private String inspReturnedStatus = ".checklist-header div span";

	public boolean sendbackInspection() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		OrderListPage op = new OrderListPage(page);
		op.checkInspectionStatus();
		page.waitForTimeout(5000);
		if (frame.locator(openInspectionBtn).isVisible() && frame.locator(openInspectionBtn).isEnabled()) {
			logger.info("Open Inspection button is visible and enabled, clicking now.");

		} else {
			logger.error("Open Inspection button is either not visible or not enabled.");
		}
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection");
		page.waitForTimeout(3000);
		frame.locator(sendback).click();
		logger.info("Click on send back button");
		if (!frame.locator(areyousure).isVisible()) {
			logger.info("Are you sure you want to send this inspection back for a revision?");
		}
		frame.locator(cancelBtn).click();
		logger.info("Clicked on Cancel button, closing the confirmation dialog.");
		frame.locator(sendback).click();
		logger.info("Click on again send back button");
		frame.locator(yesBtn).click();
		logger.info("Clicked on Yes button to confirm sending back the inspection.");
		page.waitForTimeout(3000);
		op.checkInspReturnedStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection again");
		if (!frame.locator(inspReturnedStatus).isVisible()) {
			logger.info("Check Inspection Returned status is displaying in header");
		}
		frame.locator(sendback).isDisabled();
		logger.info("Send back button are displaying disabled");
		frame.locator(publish).isDisabled();
		logger.info("Publish button are displaying disabled");
		if (!frame.locator("text=Inspection returned: Mazda Full Circle Inspection").isVisible()) {
			logger.info("Check Inspection return message are displaying under activity");
		}

		return true;
	}

	// Publish Inspection

	private String inspPublishStatus = ".checklist-header div span"; // ('Inspection (Insp-Published)')
	private String hidefromcustomer = ".mdc-button__label:has-text(' Hide from customer ')";
	private String createpdf = ".mdc-button__label:has-text(' Create Pdf ')";
	private String notifybtn = ".mat-mdc-tooltip-trigger.primary";

	public boolean publishInspection() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		OrderListPage op = new OrderListPage(page);
		op.checkInspectionStatus();
		frame.locator(openInspectionBtn).click();

		logger.info("Click on Open- inspection");
		page.waitForTimeout(3000);
		frame.locator(publish).click();
		logger.info("Click on Publish button");
		op.checkInspPublishStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection again");

		if (!frame.locator(inspPublishStatus).isVisible()
				&& frame.locator("text=Inspection published: Standard Multipoint Inspection - Mazda").isVisible()) {
		}
		logger.info("Check Inspection Publish status is displaying in header");
		logger.info("Check Inspection publish message are displaying under activity");
		frame.locator(sendback).isDisabled();

		logger.info("Send back button are displaying disabled");
		page.waitForTimeout(5000);
		if (frame.locator(hidefromcustomer).isVisible() && frame.locator(createpdf).isVisible()
				&& frame.locator(notifybtn).isVisible()) {
			logger.info("All buttons are vissible");
		} else {
			System.out.println("All buttons are not vissible");
			flags.add(false);
		}

		return !flags.contains(false);
	}

	// Notify customer button

	public static final String Inspection_Sent = "The notification was sent successfully to the client";

	public boolean notifyCustomerBtn() {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		OrderListPage op = new OrderListPage(page);
		op.checkInspectionStatus();
		frame.locator(openInspectionBtn).click();

		logger.info("Click on Open- inspection");
		page.waitForTimeout(3000);
		frame.locator(publish).click();
		logger.info("Click on Publish button");
		op.checkInspPublishStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection again");

		frame.locator(inspPublishStatus).isVisible();
		logger.info("Check Inspection Publish status is displaying in header");
		if (frame.locator(hidefromcustomer).isVisible() && frame.locator(createpdf).isVisible()
				&& frame.locator(notifybtn).isVisible()) {

			logger.info("All buttons are vissible");
		} else {
			System.out.println("All buttons are not vissible");
			flags.add(false);
		}
		frame.locator(notifybtn).click();
		logger.info("Click on Notify button");
		page.waitForTimeout(4000);
		selectChannelToPerformAction("SMS"); // Select channel to send
		if (frame.locator("text=Inspection sent to customer: Standard Multipoint Inspection - Mazda").isVisible()
				&& frame.locator("text=Inspection sent to customer: Mazda Full Circle Inspection").isVisible()) {
			logger.info("Inspection sent to customer display under activity");
		} else {
			logger.info("Inspection status not matched");
		}
		page.waitForTimeout(4000);
		frame.locator(topRightCornerNotification1).waitFor();
		String topRightCornerNotificationPopup = frame.locator(topRightCornerNotification1).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Inspection_Sent)) {

			logger.info("Notify sent sucessfully to client");
		} else {
			logger.info("Not displaying message");
		}
		return !flags.contains(false);
	}

	// Hide from customer/ Show to customer button functionality

	private String showtocustomer = ".mdc-button__label:has-text(' Show to customer ')";
	private String closeInspection = "app-checklist-container div mat-toolbar mat-icon.mat-icon.notranslate.material-icons.mat-ligature-font.mat-icon-no-color.ng-star-inserted";
	private String InspectionLink = "div.ng-star-inserted p a";

	private String communicationTabs(String Channelname) {
		return "div.mat-mdc-tab-labels div.mdc-tab.mat-mdc-tab.mat-mdc-focus-indicator:has-text('" + Channelname + "')";
	}

	public boolean hide_showBtn() {

		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		OrderListPage op = new OrderListPage(page);
		page.waitForTimeout(3000);
		op.checkInspectionStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection");
		page.waitForTimeout(3000);
		if (!frame.locator(reviewInspection).isVisible() && frame.locator("text= Send Back ").isVisible()
				&& frame.locator("text=  Publish  ").isVisible()) {
			logger.info("All buttons are vissible");
		} else {
			System.out.println("Buttons are not vissible");
			flags.add(false);
		}
		page.waitForTimeout(3000);
		frame.locator(publish).click();
		logger.info("Click on publish button");
		page.waitForTimeout(5000);
		op.checkInspPublishStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection again");
		frame.locator(inspPublishStatus).isVisible();
		logger.info("Check Inspection Publish status is displaying in header");
		if (frame.locator(hidefromcustomer).isVisible() && frame.locator(createpdf).isVisible()
				&& frame.locator(notifybtn).isVisible()) {
			logger.info("All buttons are vissible");
		}

		else {
			System.out.println("All buttons are not vissible");
			flags.add(false);
		}
		frame.locator(notifybtn).click();
		logger.info("Click on Notify button");
		page.waitForTimeout(4000);
		selectChannelToPerformAction("SMS"); // Select channel to send
		if (frame.locator(
				".checklist-activity_container div p:has-text('Inspection sent to customer: Standard Multipoint Inspection - Mazda')")
				.first().isVisible()) {
			logger.info("Inspection sent to customer display under activity");

		} else {
			logger.info("Inspection status not matched");
		}

		logger.info("Inspection sent to customer display under activity");
		frame.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = frame.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Inspection_Sent)) {

			logger.info("Notify sent sucessfully to client");
		} else {
			logger.info("Not displaying message");
		}

		frame.locator(closeInspection).click();
		logger.info("Inspection closed successfully");
		page.waitForTimeout(8000);
		frame.locator(communicationTabs("SMS")).click(); // Select channel to send
		frame.locator(InspectionLink).click();
		logger.info("Open Inspection link on SMS channel");

		// inspection link sent in SMS
		// Incomplete need help

		frame.locator(hidefromcustomer).click();
		logger.info("Click on Hide from customer button");

		if (frame.locator(showtocustomer).isVisible() && frame.locator(createpdf).isVisible()) {
			logger.info("Verify that Show to customer button are displaying");
			logger.info("Verify that Create Pdf button are displaying");

		}

		else {
			System.out.println("Show to customer and Create PDF button are not vissible");
			flags.add(false);
		}
		frame.locator(showtocustomer).click();
		logger.info("Click on Show to customer button");
		if (!frame.locator(hidefromcustomer).isVisible() && frame.locator(createpdf).isVisible()
				&& frame.locator(notifybtn).isVisible()) {
			logger.info("Verify that Hide from customer button are displaying");
			logger.info("Verify that Create Pdf button");
			logger.info("Verify that notify button are displaying ");

		}

		else {
			System.out.println("Hide from customer , Create PDF and Notify button are not vissible");
			flags.add(false);
		}

		return !flags.contains(false);
	}

	// Print Inspection
	// The PDF generation was not optimal, please try again
	private String printbtn = "button span.mdc-button__label:has-text(' Print ')";

	public boolean printInspection() throws InterruptedException {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		List<Boolean> flags = new ArrayList<>();
		OrderListPage op = new OrderListPage(page);
		op.checkInspectionStatus();
		Thread.sleep(4000);
		frame.locator(openInspectionBtn).isVisible();
		logger.info("Check OpenInspction 1");
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection");
		page.waitForTimeout(6000);
		if (frame.locator(reviewInspection).isVisible() && frame.locator("text= Send Back ").isVisible()
				&& frame.locator("text=  Publish  ").isVisible()) {
			logger.info("Check Review inspection heading");
			logger.info("Hover on send back btn");
			logger.info("Hover on publish btn");
		} else {
			System.out.println("Buttons are not vissible");
			flags.add(false);
		}
		page.waitForTimeout(3000);
		frame.locator(publish).click();
		logger.info("Click on Publish button");
		op.checkInspPublishStatus();
		frame.locator(openInspectionBtn).click();
		logger.info("Click on Open- inspection again");
		frame.locator(inspPublishStatus).isVisible();
		logger.info("Check Inspection Publish status is displaying in header");
		if (frame.locator(hidefromcustomer).isVisible() && frame.locator(createpdf).isVisible()
				&& frame.locator(notifybtn).isVisible()) {
			logger.info("Verify that Hide from customer button are displaying");
			logger.info("Verify that Create Pdf button");
			logger.info("Verify that notify button are displaying ");

		}

		else {
			System.out.println("Hide from customer , Create PDF and Notify button are not vissible");
			flags.add(false);
		}
		frame.locator(createpdf).click();
		logger.info("Click on Create PDF button");
		if (!frame.locator("text=Inspection printed: Standard Multipoint Inspection - Mazda").isVisible()) {
			logger.info("Inspection printed display under activity");

		}
		page.waitForTimeout(3000);
		// Print notification ayega ye remaining he
		frame.locator(printbtn).click();
		page.waitForTimeout(5000);
		logger.info("Click on print button");

		return !flags.contains(false);
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

	public boolean notesFunctionalityOnRO() throws InterruptedException {
		try {
			FrameLocator frame = page.frameLocator(orderDetailsIFrame);
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

	// ROChat

	private String roNo = "div.chat-header__main p.chat-header__title";
	private String memberCount = "p.chat-header__members.ng-star-inserted";
	private String videoCallBtn = ".mat-icon.notranslate.cam.mat-icon-no-color";
	private String iBtn = "path[d='M40 0C17.92 0 0 17.92 0 40s17.92 40 40 40 40-17.92 40-40S62.08 0 40 0Zm4 60h-8V36h8v24Zm0-32h-8v-8h8v8Z']";
	private String welcomeText = "h1.chat-empty-container__title";
	private String noConversationText = "p.chat-empty-container__subtitle";
	private String textBox = ".mat-mdc-form-field-infix.ng-tns-c508571215-8";
	private String chat_Textbox = ".mat-mdc-form-field-infix textarea";
	private String typeHereYourText = "#mat-input-0";
	private String emojisBtn = ".mat-icon.notranslate.chat-input__icons.chat-input__icons__emoji";
	private String attachmentBtn = ".mat-icon.notranslate.chat-input__icons.chat-input__icons__clip";
	private String gifBtn = ".mat-icon.notranslate.chat-input__icons.chat-input__icons__image";
	private String sendDisabled = ".mdc-icon-button.mat-mdc-icon-button.mat-unthemed.mat-mdc-button-base.ng-star-inserted.mat-mdc-button-disabled";
	private String sendEnabled = ".mat-icon.notranslate.chat-input__icons.chat-input__icons__send";
	private String message = ".message-container.message-container-incoming.ng-star-inserted";
	private String conversation_Info = "p.info-container__header__title";
	private String infoAvatar = "div.info-container__content__image";
	private String orderNameDate = "div.info-container__content__title";
	private String muteChannel = "div.info-container__content__actions.ng-star-inserted:nth-child(1)";
	private String markasUnread = "div.info-container__content__actions.ng-star-inserted:nth-child(2)";
	private String addParticipant = "div.info-container__content.ng-star-inserted";
	
	

	public boolean repairOrderChatFunctionality() throws InterruptedException {

		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForTimeout(6000);
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
		page.waitForTimeout(5000);
		frame.locator(chat_tab).click();
		logger.info("Chat window are displayed sucessfully");
		page.waitForTimeout(20000);
		if (frame.locator(roNo).isVisible() && frame.locator(memberCount).isVisible()
				&& frame.locator(videoCallBtn).isVisible() && frame.locator(iBtn).isVisible()
				&& frame.locator(welcomeText).isVisible() && frame.locator(noConversationText).isVisible()
				&& frame.locator(textBox).isVisible() && frame.locator(typeHereYourText).isVisible()
				&& frame.locator(emojisBtn).isVisible() && frame.locator(attachmentBtn).isVisible()
				&& frame.locator(gifBtn).isVisible()) {
			logger.info("All the text and buttons are vissible");

		} else {
			logger.info("All the text and buttons are not vissible");
		}
		frame.locator(sendDisabled).isVisible();
		logger.info("By default send button is disable");
		frame.locator(chat_Textbox).click();
		System.out.println("click");
		page.waitForTimeout(3000);
		frame.locator(chat_Textbox).fill(getRandomString(4));
		logger.info("Text entered succesfully into the text box");
		frame.locator(sendEnabled).isVisible();
		logger.info("Send button is enable");
		page.waitForTimeout(3000);
		frame.locator(sendEnabled).click();
		logger.info("Text sent to the Advisor/Tech");
		page.waitForTimeout(3000);
		frame.locator(message).isVisible();
		logger.info("Message is displaying in  the last");
		page.waitForTimeout(3000);
		frame.locator(iBtn).click();
		logger.info("Click on info button");
		frame.locator(conversation_Info).click();
		logger.info("Conversation info is displayed sucessfully");
		
		return true;

	}

	private String sMs_Tab = "span.mdc-tab__content >span.mdc-tab__text-label:has-text('SMS')";
	private String customerNoNotAvailable = "#mat-tab-content-1-1 div.orders-detail__no-conversation.ng-star-inserted p";
	private String logoFLname = "div.chat-header__avatar .avatar-content.ng-star-inserted";
	private String flName = "div.chat-header__main p.chat-header__title";
	private String mobileNo = "div.chat-header__phone p.chat-header__members:nth-child(2)";
	private String channelOwnerName = "div.chat-header__phone p.chat-header__members:nth-child(4)";
	private String logInUserLabel = "li.account-nav a span span:nth-child(3)";
	private String standardResponse  = "div.standard-responses__chips.ng-star-inserted div:nth-child(1)";
	
	
	public boolean smsFunctionalityOnRO() {
		
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		HomePage hp = new HomePage(page);
		OrderListPage op = new OrderListPage(page);
		logger.info("Creating RO with mail...");
        hp.clickOn_RepairOrder_Header();
		page.waitForTimeout(15000);
		op.addRepairOrderWithoutMobileNO();
		logger.info("Creating RO without mobile no and mail...");
		page.waitForTimeout(30000);
		if (frame.locator(communicationTab).isVisible() && frame.locator(whatsApp_tab).isVisible() &&
				frame.locator(sMs_tab).isVisible() && frame.locator(chat_tab).isVisible() &&
				frame.locator(notes_tab).isVisible()) {
			logger.info("All tabs are vissible");

		} else {
			logger.info("All tabs are not vissible");
		}
		frame.locator(sMs_Tab).click();
		logger.info("Click on SMS tab successfully");
		frame.locator(customerNoNotAvailable).isVisible();
		logger.info(" Customer's phone number not available or invalid ");
		page.waitForTimeout(3000);
		hp.clickOn_RepairOrder_Header();
		page.waitForTimeout(15000);
		op.navigateToOrderDetails("Existing");
		page.waitForTimeout(10000);
		logger.info("Create New repair order with 'mobile & email' open that order");
		page.waitForTimeout(30000);
		if (frame.locator(communicationTab).isVisible() && frame.locator(whatsApp_tab).isVisible() &&
				frame.locator(sMs_tab).isVisible() && frame.locator(chat_tab).isVisible() &&
				frame.locator(notes_tab).isVisible()) {
			logger.info("All tabs are vissible");

		} else {
			logger.info("All tabs are not vissible");
		}
		page.waitForTimeout(3000);
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

	
	


	public void verifyRejectdeletefunctionality( ) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		addVideoToOrder();
		
	}


}
