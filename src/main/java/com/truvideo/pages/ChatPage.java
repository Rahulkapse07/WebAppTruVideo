package com.truvideo.pages;

import java.lang.System.Logger;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.Frame.WaitForURLOptions;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class ChatPage extends JavaUtility {

	Page page;

	public ChatPage(Page page) {
		this.page = page;
	}

	// Iframe-------->
	private String iframe_chat = "#chat-v2-iframe";
	// Page title
	String Pagetile = "TruVideo - Chat v2";
	// Profile picture,
	private String ProFilePicture = ".profile__user .avatar-container";
	private String ProfileName = "mat-form-field.mat-mdc-form-field div.mat-mdc-text-field-wrapper div.mat-mdc-form-field-focus-overlay.ng-tns-c3736059725-26";
	private String message_profile_input = "input[id='mat-input-2']";
	private String profileSavebtn = ".mat-mdc-dialog-actions button";
	private String profileCanclebtn = ".mat-mdc-dialog-component-host div.close";
	private String Profilechange = "form div input[type='file']";
	private String profilebtn = ".modal-content__avatar div .avatar-content";
	private String AttachmentPath = "src/main/resources/Images/testimage.png";
	private String MessageProfileName = ".profile__user-info p:nth-child(1)";

	private String Messagechannellist = ".channels-list .channels-list-item";
	private String Messagechannelheader = ".channels-list .channels-list-item p.mat-mdc-tooltip-trigger";

	public boolean VerifyProfilepicture() {
		FrameLocator iframe = page.frameLocator(iframe_chat);
		List<Boolean> flag = new ArrayList<>();
		SoftAssert softassert = new SoftAssert();
		String Updatename = "Suraj Singh";
		page.waitForCondition(() -> iframe.locator(ProFilePicture).isVisible());
		if (!iframe.locator(ProFilePicture).isVisible()) {
			logger.info("PROFILE SETTING BUTTON NOT VISIBLE");
			flag.add(false);
		}
		page.waitForTimeout(3000);
		iframe.locator(ProFilePicture).click();
		iframe.locator(message_profile_input).click();
		page.waitForCondition(() -> iframe.locator(profilebtn).isVisible());
		page.waitForTimeout(3000);
		if (iframe.locator(profilebtn).isVisible()) {
			iframe.locator(Profilechange).setInputFiles(Paths.get(AttachmentPath));
			logger.info("Profile Updated.......");
		} else {
			logger.info("PROFILE PHOTO NOT VISIBLE");
			flag.add(false);
		}
		page.waitForTimeout(3000);
		iframe.locator(message_profile_input);
		logger.info("text VISIBLE");
		page.keyboard().press("Control+A");
		iframe.locator(message_profile_input).type(Updatename);
		if (!iframe.locator(profileSavebtn).isVisible()) {
			logger.info("SAVE BUTTON NOT VISIBLE");
			flag.add(false);
		}
		logger.info("SAVED BUTTON VISIBLE");
		iframe.locator(profileSavebtn).click();
		String Owername = iframe.locator(MessageProfileName).innerText().trim();
		softassert.assertTrue(Updatename.contains(Owername));
		logger.info(Owername + ":" + "matched with" + Updatename);

		iframe.locator(ProFilePicture).click();
		if (!iframe.locator(profileCanclebtn).isVisible()) {
			flag.add(false);
		}
		softassert.assertTrue(iframe.locator(profileCanclebtn).isVisible());
		iframe.locator(profileCanclebtn).click();
		logger.info("cancle");

		return !flag.contains(false);
	}

	private String ChannelTypeclick(String Name) {
		return ".channels-list .channels-list-item p.mat-mdc-tooltip-trigger:has-text('" + Name + "')";
	}

	public boolean VerifyChannelleaveFunc() {
		FrameLocator iframe = page.frameLocator(iframe_chat);
		List<Boolean> flag = new ArrayList<>();
		SoftAssert softassert = new SoftAssert();
		String Title = page.title();
		System.out.println(Title);
		softassert.assertTrue(Title.equals(Pagetile));

		if (page.title().equals(Pagetile)) {
			logger.info("redirect to chat page");
		}
		page.waitForTimeout(5000);
		List<String> ChannelList = iframe.locator(Messagechannelheader).allInnerTexts();
		page.waitForTimeout(5000);
		int len = iframe.locator(Messagechannellist).count();
		System.out.println(len);

		for (String list : ChannelList) {
			if (list != null) {
				if (list.contains("")) {
					iframe.locator(ChannelTypeclick(list)).click();
					logger.info("test run" + list);
				}
			}
		}

		return true;
	}

}
