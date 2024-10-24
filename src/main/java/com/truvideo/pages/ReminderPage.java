package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Page;
import com.truvideo.constants.AppConstants;

public class ReminderPage {

	public Logger logger = LogManager.getLogger(getClass().getName());

	private Page page;

	private String other = "//a[contains(text(), 'Other ')]";
	private String remindertab = "  li #reminders-tab  > a[href='/reminder?filterBy=MY_REMINDERS']";
	private String backtohome = "div.pull-left > button#page-title-back";
	private String remindertxt = "#page-title-text";
	private String noresultfound = "//div[contains(text(), 'No results found.')]";
	private String orderDetailsIFrame = "#order-details-iframe";
	private String myreminder = "#LBL_MY_REMINDERS"; 
	private String repairOrderNo = "#repair-order-results tbody tr:nth-child(2) td:nth-child(3) p a:nth-child(1)";
	private String dealer = "#repair-order-results tbody tr td:nth-child(1)";
	private String username = "#repair-order-results tbody tr td:nth-child(5) p";
	private String logInUserLabel = "li.account-nav a span span:nth-child(3)";
	private String sendBtn = "#repair-order-results tbody tr:nth-child(2) td:nth-child(10) button.send-reminder";
	private String cancelBtn = "#repair-order-results tbody tr:nth-child(2) td:nth-child(10) button.cancel-reminder";
	private String today = "#LBL_ALL_REMINDERS_TODAY"; 
	private String overdue = "#LBL_ALL_REMINDERS_OVERDUE"; 
	private String all = "#LBL_ALL_REMINDERS"; 
	private String sent = "#LBL_ALL_REMINDERS_CLOSED"; 
	private String cancelled = "#LBL_ALL_REMINDERS_CANCELLED";
	private String selectdealer = "#s2id_dealer";
	private String searchdealer = "div.select2-search > input.select2-input";
	private String kenilityDealer = ".select2-results li:has-text('Kenility Store ')";
	private String TruVideoDealer = ".select2-results li:has-text('TruVideo ')";
	private String DealerName = ".account-nav.dropdown a span span:nth-child(1)";
	private String form = "div#form-panel";
	private String closeBtn = ".btn.btn-link";
	private String anothersendBtn = "button#sendButton";
	private String topRightCornerNotification1 = "div.tru-toast";
	private String topRightCornerNotification = "div.notifications";

	public static final String Reminder_Send_Message = "Text message sent to customer";
	public static final String Reminder_Send_EmailMessage = "Email message sent to customer";
	public static final String Reminder_Cancel_Message = "Reminder has been cancelled";
	public static final String Reminder_Save = "Service recomendation successfully saved";

	public ReminderPage(Page page) {
		this.page = page;
	}

	public boolean checkremindernavigateToOrderListPage() throws InterruptedException {
		page.waitForTimeout(3000);
    	List<Boolean> flags = new ArrayList<>();
		if (page.locator(noresultfound).isVisible()) {
			// Step 1: Create a new repair order
			HomePage hp = new HomePage(page);
			RepairOrderDetailPage RO = new RepairOrderDetailPage(page);
			hp.navigateToOrderList();
			RO.createreminder();
			hp.navigateToReminder();
		} else {
			System.out.println("Reminder already exists.");
		}
		return !flags.contains(false);
		}

	public boolean getRemindersPageServiceAdvisor() {
		page.waitForTimeout(4000);
		List<String> list = page.locator(username).allInnerTexts();
		logger.info(list);
		String loginusername = page.locator(logInUserLabel).innerText().toLowerCase();
		System.out.println(loginusername);
		for (String name : list) {

			if (name.toLowerCase().contains(loginusername)) {
				logger.info("Name of remider is matched" + loginusername);
				return true;
			} else {
				logger.info("Name of remider is notmatched" + loginusername);
				return false;
			}
		}
		return true;
	}

	public boolean getRemindersPageDealer() {
		page.waitForTimeout(6000);
		page.waitForCondition(() -> page.locator(selectdealer).isVisible());
		page.locator(selectdealer).click();
		page.locator(kenilityDealer).click();
		List<String> list = page.locator(dealer).allInnerTexts();
		List<Boolean> flags = new ArrayList<>();
		String dealername = page.locator(DealerName).innerText().toLowerCase();
		for (String dealr : list) {
			if (dealr.toLowerCase().contains(dealername)) {
				logger.info("Name of remider is matched" + dealername);
				flags.add(true);
			} else {
				logger.info("Name of remider is notmatched" + dealername);
				flags.add(false);
			}
		}
		return !flags.contains(false);
	}

	public boolean reminder() {
		page.waitForTimeout(3000);
    	List<Boolean> flags = new ArrayList<>();
		page.locator(backtohome).hover();
		logger.info("Hover on backtohome");
		if(page.locator(remindertxt).isVisible() && page.locator(myreminder).isVisible()) {
			logger.info("Both elements are vissible"); }
		else
		{ System.out.println("Elements are not vissible"); flags.add(false); }
		page.locator(today).click();
		logger.info("Click on today");
		page.locator(overdue).click();
		logger.info("Click on overdue");
		page.locator(all).click();
		logger.info("Click on all");
		page.locator(sent).click();
		logger.info("Click on sent");
		page.locator(cancelled).click();
		logger.info("Click on cancelled");
		page.locator(myreminder).click();
		page.waitForTimeout(5000);
		page.locator(selectdealer).click();
		page.locator(TruVideoDealer).first().click();
		logger.info("Click on selectdealer");
		return !flags.contains(false);
	}

	public boolean sentreminder() {
		page.waitForTimeout(2000);
		page.reload();
		page.locator(sendBtn).click();
		logger.info("Click on sendBtn");
		page.locator(form).isVisible();
		logger.info("Check form");
		page.locator(closeBtn).click();
		logger.info("Click on closeBtn");
		page.locator(sendBtn).click();
		logger.info("Click on sendBtn");
		page.locator(form).isVisible();
		logger.info("Check form");
		page.locator(anothersendBtn).click();
		logger.info("Click on anothersendBtn");
		page.waitForTimeout(3000);
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Reminder_Send_Message)
				|| topRightCornerNotificationPopup.contains(AppConstants.Reminder_Send_EmailMessage)) {

			logger.info("Reminder sent Successfully");
		} else {
			logger.info("Not displaying message");
		}
       return true;
	}

	public boolean cancelreminder() {
		page.waitForTimeout(3000);
    	List<Boolean> flags = new ArrayList<>();
		page.onDialog(dialog -> {
			String text = dialog.message();
			System.out.println("First dialog message: " + text);
			dialog.accept();

		});
		page.locator(cancelBtn).click();
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Reminder_Cancel_Message)) {

			logger.info("Reminder has been cancelled");
		} else {
			logger.info("Not displaying message");
		}
		return !flags.contains(false);
	}

}
