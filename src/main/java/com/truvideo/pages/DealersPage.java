package com.truvideo.pages;

import java.util.List;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

import net.bytebuddy.implementation.bytecode.Throw;

public class DealersPage extends JavaUtility {
	private Page page;

	public DealersPage(Page page) {
		this.page = page;
	}

	private String Dealerlistedit(int num) {
		return "#dealer-results tbody tr:nth-child(" + num + ") td:nth-child(4) i:nth-child(1)";
	}

	private String DealerSettingSAVEbtn = "button#page-title-save";
	private String DealerName = "#dealer-search div:nth-child(1) #name";
	private String DealerSearchBtn = "input.btn.btn-primary.btn-block";
	private String DealerList = "#dealer-results tbody tr td:nth-child(1)";
	private String EnableWhatsappcheckboxbtn = "input#enableWhatsAppConversation";

	private String Verify_Dearler_internal_listClick(String Value) {
		return "div#floating-sidenav a:has-text('" + Value + "')";
	}

	public boolean Verify_Whatsapp_textconversation(String Name, String value) {

		page.waitForCondition(() -> page.locator(DealerName).isVisible());
		page.fill(DealerName, Name);
		page.locator(DealerSearchBtn).click();
		page.waitForTimeout(4000);
		List<String> Dealerlist = page.locator(DealerList).allInnerTexts();
		String name = Name;
		int n = 0;
		int i = 2;
		while (n <= DealerList.length()) {
			for (String list : Dealerlist) {
				if (!name.contains(list)) {
					++i;
					n++;
				} else if (name.contains(list)) {
					page.locator(Dealerlistedit(i + 0)).click();
					page.waitForTimeout(5000);
					break;
				} else {
					logger.info("NO DEALER FOUND");
					return false;
				}
			}
			break;
		}
		page.waitForTimeout(2000);
		page.locator(Verify_Dearler_internal_listClick(value)).click();

		if (!page.locator(EnableWhatsappcheckboxbtn).isChecked()) {
			logger.info("ENABLE WHATSAPP CHAT");
			page.locator(EnableWhatsappcheckboxbtn).click();
			page.locator(DealerSettingSAVEbtn).click();
		} else if (page.locator(EnableWhatsappcheckboxbtn).isChecked()) {
			logger.info("ALREADY ENABLE");
			return false;
		}

		return true;
	}

	private String repairServiceMessage = "div#floating-sidenav a[href='javascript:;']:nth-child(5)";
	private String enableRemindercheckboxbtn = "input#enableReminder1";
	private String other = "//a[contains(text(), 'Other ')]";
	private String remindertab = "  li #reminders-tab  > a[href='/reminder?filterBy=MY_REMINDERS']";

	public boolean Verify_Reminder_EnaDisable_functionality(String Name, String value) {
		page.waitForCondition(() -> page.locator(DealerName).isVisible());
		page.waitForTimeout(5000);

		page.fill(DealerName, Name);
		page.waitForCondition(() -> page.locator(DealerSearchBtn).isVisible());
		page.locator(DealerSearchBtn).click();
		page.waitForTimeout(3000);
		System.out.println("click1");
		List<String> Dealerlist = page.locator(DealerList).allInnerTexts();
		System.out.println("click1");
		Locator String = page.locator(DealerList);
		String name = Name;
		int n = 0;
		int i = 2;
		while (n <= String.count()) {
			System.out.println("click1");
			page.waitForTimeout(8000);
			for (String list : Dealerlist) {
				if (!name.contains(list)) {
					++i;
					n++;
				} else if (name.contains(list)) {
					page.locator(Dealerlistedit(i + 0)).click();
					page.waitForTimeout(5000);
					break;
				} else {
					logger.info("NO DEALER FOUND");
					return false;
				}
			}
			break;
		}
		page.waitForTimeout(3000);
		page.locator(Verify_Dearler_internal_listClick(value)).click();
		// page.locator(repairServiceMessage).isVisible();
		page.locator(repairServiceMessage).click(new Locator.ClickOptions().setTimeout(30000));
		logger.info("Click on Repair Service Message tab");
//		Locator element = page.locator("enableRemindercheckboxbtn");
//		element.scrollIntoViewIfNeeded();
		page.waitForTimeout(3000);
		if (!page.locator(enableRemindercheckboxbtn).isChecked()) {
			logger.info("ENABLE Reminder");
			page.locator(enableRemindercheckboxbtn).click();
			page.locator(DealerSettingSAVEbtn).click();
			HomePage hp = new HomePage(page);
			hp.LogOutfunctionality();
			LoginPage lp = new LoginPage(page);
			lp.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"));
			page.waitForTimeout(2000);
			page.locator(other).click();
			logger.info("Click on Others tab");
			page.waitForCondition(() -> page.locator(remindertab).isVisible());
			if (page.locator(remindertab).isVisible()) {
				logger.info("Reminder header is vissible Now");

			} else {
				logger.info("Reminder header is not vissible Now");
				return false;
			}

		} else if (page.locator(enableRemindercheckboxbtn).isChecked()) {
			logger.info("ALREADY ENABLE");
			if (page.locator(other).isVisible()) {
				logger.info("Click on Others tab");
				page.locator(remindertab).isVisible();
				logger.info("Reminder header is already vissible");
			}
			else {
				logger.info("Reminder header is not vissible Now");
			}

			return true;
		}

		return true;

	}
	
	private String standardResponses = "div#floating-sidenav a[href='javascript:;']:nth-child(16)";
	private String categoryHeading = "#standard-responses-table tr th:nth-child(1)";
	private String rankHeading = "#standard-responses-table tr th:nth-child(2)";
	private String messageHeading = "#standard-responses-table tr th:nth-child(3)";
	private String addNew = "a#add-standard-response-link";
	private String editBtn = "#standard-responses-table tbody tr:nth-child(11) td a.edit-sr";
	private String deleteBtn = "#standard-responses-table tbody tr:nth-child(11) td a.delete-sr";
	private String dialogBox = "div#add-standard-responses-popup";
	private String category = "select#sr-category";
	private String sales = "select#sr-category option[value='CAT_SALES']";
	private String service = "select#sr-category option[value='CAT_SERVICE']";
	private String rank = "input#sr-rank";
	private String message = "textarea#sr-message";
	private String save = "div#save-standard-response";
	private String close = "i#close-standard-responses-popup";
	private String orderDetailsIFrame = "iframe#order-details-iframe";
	private String topRightCornerNotification = "div.notifications";
    private String topRightCornerNotification1 = "div.tru-toast";
    private String Standard = "div.standard-responses__chips.ng-star-inserted div:nth-child(3)";
	private String sms_Tab = "#mat-tab-label-1-1";
	private String sms_Textbox = ".mat-mdc-form-field-infix textarea";
	private String send_SMS_Button = "mat-icon[svgicon=\"send\"]";

	
	
	public boolean verifyAddNewStandardResponse(String Name, String value) {
		FrameLocator frame = page.frameLocator(orderDetailsIFrame);
		page.waitForCondition(() -> page.locator(DealerName).isVisible());
		page.waitForTimeout(5000);
		page.fill(DealerName, Name);
		page.waitForCondition(() -> page.locator(DealerSearchBtn).isVisible());
		page.locator(DealerSearchBtn).click();
		page.waitForTimeout(3000);
		List<String> Dealerlist = page.locator(DealerList).allInnerTexts();
		System.out.println("click1");
		Locator String = page.locator(DealerList);
		String name = Name;
		int n = 0;
		int i = 2;
		while (n <= String.count()) {
			System.out.println("click1");
			page.waitForTimeout(8000);
			for (String list : Dealerlist) {
				if (!name.contains(list)) {
					++i;
					n++;
				} else if (name.contains(list)) {
					page.locator(Dealerlistedit(i + 0)).click();
					page.waitForTimeout(5000);
					break;
				} else {
					logger.info("NO DEALER FOUND");
					return false;
				}
			}
			break;
		}
		page.waitForTimeout(3000);
		page.locator(Verify_Dearler_internal_listClick(value)).click();
		page.locator(standardResponses).click(new Locator.ClickOptions().setTimeout(30000));
		logger.info("Click on Standard Response tab");
		if(page.locator(categoryHeading).isVisible() && page.locator(rankHeading).isVisible() &&
				page.locator(messageHeading).isVisible() && page.locator(addNew).isVisible() && 
				page.locator(editBtn).isVisible() && page.locator(deleteBtn).isVisible()) {
			logger.info("All Elements are vissible");
		}
		else {
			logger.info("All Elements are not vissible");

		}
		page.locator(addNew).click();
		logger.info("Click on Add New Response");
		if(page.locator(dialogBox).isVisible() && page.locator(category).isVisible() &&
				page.locator(rank).isVisible() && page.locator(message).isVisible() && page.locator(save).isVisible() && 
				page.locator(close).isVisible()	) {
			logger.info("All Elements are vissible");
		}
		else {
			logger.info("All Elements are not vissible");
		}
		page.locator(category).selectOption(new SelectOption().setLabel("Service"));
		logger.info("Service is select");
        page.locator(rank).fill("3");
		logger.info("Rank entered successfully");
		page.locator(message).fill("{{firstname}} Please come to showroom, your vehicle is ready");
		logger.info("Message is entered successfully");
		page.locator(save).click();
		logger.info("Click on Save button");
//		frame.locator(topRightCornerNotification).waitFor();
//		String topRightCornerNotificationPopup = frame.locator(topRightCornerNotification).innerText();
//		logger.info(topRightCornerNotificationPopup);
//		if (topRightCornerNotificationPopup.contains(AppConstants.StandardResponseAdded)) {
//
//			logger.info("Service recomendation successfully saved");
//		} else {
//			logger.info("Not displaying message");
//		}
		page.locator(DealerSettingSAVEbtn).click();
		page.waitForTimeout(4000);
		HomePage hp = new HomePage(page);
		hp.clickOn_RepairOrder_Header();
		OrderListPage op = new OrderListPage(page);
		//op.clickOnAddRepairOrder();
		op.navigateToOrderDetails("New");
		page.waitForTimeout(5000);
		page.waitForCondition(() -> frame.locator(sms_Tab).isVisible());
		frame.locator(sms_Tab).click();
		page.waitForTimeout(6000);
		logger.info("Click on SMS tab");
		frame.locator(sms_Textbox).click();
		page.waitForTimeout(5000);
		logger.info("1");
		//page.waitForCondition(() -> page.locator("div.standard-responses__chips.ng-star-inserted div:nth-child(3)").isVisible());
		logger.info("2");
		//page.locator("div.standard-responses__chips.ng-star-inserted div:nth-child(3)").click();
		page.locator("div.ngx-messaging-chat__container div.standard-responses__chips.ng-star-inserted div.mat-mdc-tooltip-trigger:has-text(' Please come to show... ')").click();
		logger.info("3");
		frame.locator(send_SMS_Button).click();
		logger.info("Standard Response Sent successfully");

		//Sales

		page.waitForCondition(() -> page.locator(DealerName).isVisible());
		page.waitForTimeout(5000);
		page.fill(DealerName, Name);
		page.waitForCondition(() -> page.locator(DealerSearchBtn).isVisible());
		page.locator(DealerSearchBtn).click();
		page.waitForTimeout(3000);
		List<String> Dealerlist1 = page.locator(DealerList).allInnerTexts();
		System.out.println("click1");
		Locator String1 = page.locator(DealerList);
		String name1 = Name;
		int n1 = 0;
		int i1 = 2;
		while (n1 <= String1.count()) {
			System.out.println("click1");
			page.waitForTimeout(8000);
			for (String list : Dealerlist1) {
				if (!name1.contains(list)) {
					++i1;
					n1++;
				} else if (name1.contains(list)) {
					page.locator(Dealerlistedit(i1 + 0)).click();
					page.waitForTimeout(5000);
					break;
				} else {
					logger.info("NO DEALER FOUND");
					return false;
				}
			}
			break;
		}
		page.waitForTimeout(3000);
		page.locator(Verify_Dearler_internal_listClick(value)).click();
		page.locator(standardResponses).click(new Locator.ClickOptions().setTimeout(30000));
		logger.info("Click on Standard Response tab");
		if(page.locator(categoryHeading).isVisible() && page.locator(rankHeading).isVisible() &&
				page.locator(messageHeading).isVisible() && page.locator(addNew).isVisible() && 
				page.locator(editBtn).isVisible() && page.locator(deleteBtn).isVisible()) {
			logger.info("All Elements are vissible");
		}
		else {
			logger.info("All Elements are not vissible");

		}
		page.locator(addNew).click();
		logger.info("Click on Add New Response");
		if(page.locator(dialogBox).isVisible() && page.locator(category).isVisible() &&
				page.locator(rank).isVisible() && page.locator(message).isVisible() && page.locator(save).isVisible() && 
				page.locator(close).isVisible()	) {
			logger.info("All Elements are vissible");
		}
		else {
			logger.info("All Elements are not vissible");
		}
		page.locator(category).selectOption(new SelectOption().setLabel("Sales"));
		logger.info("Sales is select");
        page.locator(rank).fill("1");
		logger.info("Rank entered successfully");
		page.locator(message).fill("{{firstname}} Are you happy with our services");
		logger.info("Message is entered successfully");
		page.locator(save).click();
		logger.info("Click on Save button");
		page.locator(DealerSettingSAVEbtn).click();
		page.waitForTimeout(4000);
		HomePage hp1 = new HomePage(page);
		hp1.clickOn_Prospect_Header();
		ProspectListPage pp = new ProspectListPage(page);
		pp.navigateToProspectDetails();
		page.waitForTimeout(5000);
		page.waitForCondition(() -> frame.locator(sms_Tab).isVisible());
		frame.locator(sms_Tab).click();
		page.waitForTimeout(6000);
		logger.info("Click on SMS tab");
		frame.locator(sms_Textbox).click();
		page.waitForTimeout(5000);
		logger.info("1");
		
		
		return true;
	}
	
	
	
	

}
