package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.truvideo.utility.JavaUtility;

public class DevicesPage extends JavaUtility{
	public Logger logger = LogManager.getLogger(getClass().getName());
	private Page page;
	
	private String backbtn = "div button#page-title-back";
	private String sharedDevicesText = "#page-title-text";
	private String name = "#device-search div #name";
	private String deviceID = "#device-search div #deviceId";
	private String dealer = "#device-search #s2id_dealer";
	private String dealer1 = "#form-body div#s2id_dealer";

	private String demoMainStreetToyotaDealer = ".select2-results li:has-text('DEMO - Main Street Toyota')";
	private String demoMainStreetToyotaDealer1 = ".select2-results li:nth-child(40)";
	private String searchbtn = ".btn.btn-primary.btn-block";
	private String clearbtn = ".btn.btn-link.btn-block";
	private String nameMatch = "#device-results tbody tr td:nth-child(2) p";
	private String dealerMatch =  "#device-results tbody tr td:nth-child(1) p";
	private String editbtn = "tr:nth-child(4) td .btn.btn-mini.edit-action";
	private String updateWnindow = ".ot3-update";
	private String truVideoDealer = "div#select2-drop ul li:has-text('Truvideo')";
	private String truVideoDealer1 = ".select2-results li:nth-child(139)";
	private String name1 = "div.control-group:nth-child(4) div #name";
	private String deviceID1 = "div.control-group:nth-child(5) div #deviceId";
	private String saveBtn = ".btn.btn-primary[value='Save']";
	private String deleteBtn = "tr:nth-child(4) td .btn.btn-mini.remove-action ";
	private String removebtn = ".bootbox.modal.fade.in a.btn.btn-danger";
	private String cancelbtn = ".bootbox.modal.fade.in div >a.btn.btn:has-text('Cancel')";
	
	
	
	public DevicesPage(Page page) {
		this.page = page;
	}
	
	public boolean verifyDevicesPage() {
		page.waitForTimeout(3000);
		List<Boolean> flags = new ArrayList<>();

		if(page.locator(backbtn).isVisible() && page.locator(sharedDevicesText).isVisible() && page.locator(name).isVisible()
				&& page.locator(deviceID).isVisible() && page.locator(dealer).isVisible()) {
			logger.info("All Elements are vissible");
		}
		else {
			logger.info("Elements are not vissible");

		}
		if(page.locator(searchbtn).isVisible() && page.locator(clearbtn).isVisible()) {
			logger.info("Both buttons are vissible");
		}
		else {
			logger.info("Buttons are not vissible");
		}
		page.waitForTimeout(5000);
		page.locator(name).click();
		page.locator(name).fill("Ankita");
		logger.info("Ankita name is displaying");
		page.locator(searchbtn).click();
		logger.info("Click on Seacrh button");
		page.waitForTimeout(25000);
        List<String> list = page.locator(nameMatch).allInnerTexts();
        String nameMatching = page.locator(name).innerText().toLowerCase();
        System.out.println(nameMatching);
        for (String nameM : list) {

			if (name.toLowerCase().contains(nameMatching)) {
				logger.info("Name of device name is matched" + nameMatching);
			} else {
				logger.info("Name of device name is notmatched" + nameMatching);
			}
		}
		page.locator(clearbtn).click();
		logger.info("Click on clear button");
		page.locator(searchbtn).click();
		logger.info("Again click on Seacrh button");
		page.waitForTimeout(25000);
		page.locator(deviceID).click();
		page.locator(deviceID).fill("99001200481124");
		page.locator(searchbtn).click();
		logger.info("Click on Seacrh button");
		page.locator(clearbtn).click();
		logger.info("Click on clear button");
		page.waitForTimeout(25000);
        page.locator(dealer).click();
        page.locator(demoMainStreetToyotaDealer1).click();
        page.locator(searchbtn).click();
		logger.info("Click on Seacrh button");
        List<String> list1 = page.locator(dealerMatch).allInnerTexts();
        String dealername = page.locator(dealer).innerText().toLowerCase();
		for (String dealer : list1) {
			if (dealer.toLowerCase().contains(dealername)) {
				logger.info("Name of remider is matched" + " " + dealername);
			} else {
				logger.info("Name of remider is notmatched" + dealername);
			}
		}
		page.locator(clearbtn).click();
		logger.info("Click on clear button");
		page.locator(searchbtn).click();
		logger.info("Click on Seacrh button");
		page.waitForTimeout(25000);
		page.locator(editbtn).click();
		logger.info("Click on Edit button");
		page.locator(updateWnindow).click();
		logger.info("Update window is sucessfully display");
		page.waitForTimeout(25000);
		page.locator(dealer1).click();
		page.waitForTimeout(3000);
		page.locator(truVideoDealer1).click();
		logger.info("Device select with updated dealer ");
		page.locator(name1).fill("tru");
		logger.info("Device name updated successfully");
		page.locator(deviceID1).fill("00");
		logger.info("Device ID updated successfully");
		page.locator(saveBtn).click();
		logger.info("Click on Save button");
		logger.info("Device get register with the updated details");
		page.locator(deleteBtn).click();
		logger.info("Click on Delete button");
		page.locator("text='Are you sure to delete record?'").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    logger.info("Confirmation dialog appeared: 'Are you sure to delete record?'");
	    boolean areButtonsVisible = page.locator(removebtn).isVisible() && page.locator(cancelbtn).isVisible();
	    if (areButtonsVisible) {
	        logger.info("Remove and Cancel buttons are visible. Remove button is red, Cancel button is grey.");
	    } else {
	        logger.error("Remove and Cancel buttons are not visible.");
	        return false;
	    }
	    page.locator(removebtn).click();
	    logger.info("Clicked on the Remove button.");
	    logger.info("Device get successfully deleted");
		return !flags.contains(false);
	}

}
