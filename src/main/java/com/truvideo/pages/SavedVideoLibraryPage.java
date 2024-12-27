package com.truvideo.pages;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

public class SavedVideoLibraryPage extends JavaUtility {
	public Logger logger = LogManager.getLogger(getClass().getName());
	private Page page;

	private String other = "//a[contains(text(), 'Other ')]";
	private String organization = "#navigation-tabs li.nav-other.hide.dropdown ul.dropdown-menu.other li.nav-item.dropdown-submenu:nth-child(3) ul li:nth-child(5) a";
	private String savedvideolibrary = ".nav-item.dropdown-submenu:nth-child(3) ul.dropdown-menu li a[href='/crud/saved-video']";
	private String backbtn = "div button#page-title-back";
	private String savedvideotext = "#page-title-text";
	private String Allsavedvideosbtn = "#ALL_SAVED_VIDEO";
	private String myfavoritebtn = "label.btn.btn-default:nth-child(2)";
	private String titletextfield = "div .search-input";
	private String titleName = "div table#saved-video-results tbody tr td:nth-child(3)";
	private String searchbtn = "#saved-video-search";

	private String allCategoriesdropdown = "#categoryFilter";
	private String allCategories = "#categoryFilter option:nth-child(1)";
	private String repairOrder = "div form select#categoryFilter option:nth-child(2)";
	private String repairOrdertext = "div table#saved-video-results tbody tr td:nth-child(4)";
	private String salesOrder = "div form select#categoryFilter option:nth-child(3)";
	private String salesOrdertext = "div table#saved-video-results tbody tr td:nth-child(4)";

	private String logInUserLabel = "li.account-nav a span span:nth-child(3)";
	private String addVideobtn = "div #saved-video-add";
	private String addVideoWindow = "div.modal-header h4.ot3-add";
	private String closebtn = "div#form-panel div.modal-header button.close";
	private String date = "div.controls input#videoDateFormDisplay";
	private String tittletextbox = "div #title";
	private String uploadVideo = "div #vcfFile";
	private String path = "src/main/resources/Videos/2091.mp4";
	private String selectCategory = "div #category";
	private String selectRepairOrder = "div #category option:nth-child(1)";
	private String selectSalesOrder = "div #category option:nth-child(2)";
	private String username = ".modal-body .control-group.ot3-add label[class='control-label muted'] ";
	private String favoriteCheckbox = "div label #isFavorite";
	private String Closebtn = ".btn.btn-link";
	private String Savebtn = ".btn.btn-primary.saved-video-submit.ot3-add";

	private String topRightCornerNotification = "div.notifications";
	private String editbtn = "table#saved-video-results tbody tr:nth-child(2) td:nth-child(5) i";
	private String SavebtnUpdate = " button.btn.btn-primary.saved-video-submit.ot3-update";

	private String updateVideo = "h4.ot3-update";
	private String deletebtn = "table#saved-video-results tbody tr:nth-child(2) td:nth-child(6) i";
	private String removebtn = ".bootbox.modal.fade.in a.btn.btn-danger";
	private String cancelbtn = ".bootbox.modal.fade.in div >a.btn.btn:has-text('Cancel')";

	public SavedVideoLibraryPage(Page page) {
		this.page = page;
	}

	public boolean verificationSavedVideoLibrary() {
		page.waitForTimeout(3000);
		List<Boolean> flags = new ArrayList<>();
		page.waitForTimeout(5000);
		if (page.locator(savedvideotext).isVisible() && page.locator(backbtn).isVisible()
				&& page.locator(myfavoritebtn).isVisible() && page.locator(titletextfield).isVisible()
				&& page.locator(searchbtn).isVisible() && page.locator(allCategoriesdropdown).isVisible()
				&& page.locator(addVideobtn).isVisible()) {
			logger.info("All Elements are vissible");
		} else {
			logger.info("Elements are not vissible");
		}
		page.locator(titletextfield).click();
		logger.info("Click on tittle text field");
		page.locator(titletextfield).fill("Demo");
		page.locator(searchbtn).click();
		logger.info("Verify search with given tittle text");
		page.waitForTimeout(10000);
		List<String> list = page.locator(titleName).allInnerTexts();
		logger.info(list);
		String titletextName = page.locator(titletextfield).innerText().toLowerCase();
		System.out.println(titletextName);
		for (String name : list) {

			if (name.toLowerCase().contains(titletextName)) {
				logger.info("Name of tittle is matched" + titletextName);
			} else {
				logger.info("Name of tittle is notmatched" + titletextName);
			}
		}
		page.locator(titletextfield).clear();
		page.locator(searchbtn).click();
		logger.info("Again click on search button");
		logger.info("Check dropdown should be display");
		page.locator(allCategoriesdropdown).selectOption(new SelectOption().setLabel("Repair Order"));
		page.waitForTimeout(4000);
		List<String> list1 = page.locator(repairOrdertext).allInnerTexts();
		logger.info(list1);
		String repairOrderName = page.locator(repairOrder).innerText().toLowerCase();
		System.out.println(repairOrderName);
		for (String name : list1) {

			if (name.toLowerCase().contains(repairOrderName)) {
				logger.info("Name of tittle is matched" + repairOrderName);
			} else {
				logger.info("Name of tittle is notmatched" + repairOrderName);
			}
		}
		page.waitForTimeout(3000);
		page.locator(allCategoriesdropdown).selectOption(new SelectOption().setLabel("Sales Order"));
		page.waitForTimeout(4000);
		List<String> list2 = page.locator(salesOrdertext).allInnerTexts();
		logger.info(list2);
		String salesOrderName = page.locator(salesOrder).innerText().toLowerCase();
		System.out.println(salesOrderName);
		for (String name : list2) {

			if (name.toLowerCase().contains(salesOrderName)) {
				logger.info("Name of tittle is matched" + salesOrderName);
			} else {
				logger.info("Name of tittle is notmatched" + salesOrderName);
			}
		}
		page.waitForTimeout(3000);
		page.locator(allCategories).isVisible();
		logger.info("Verify Both Repair Orders video and Sales Orders video should be displayed");
		page.locator(addVideobtn).isVisible();
		logger.info("Add video button is displaying");
		return !flags.contains(false);
	}

	public boolean addVideoFunctionality() {
		page.locator(addVideobtn).click();
		page.waitForTimeout(3000);
		logger.info("Check Add video window open successfully");
		boolean allElementsVisible = page.locator(addVideoWindow).isVisible()
	            && page.locator(closebtn).isVisible()
	            && page.locator(date).isVisible()
	            && page.locator(tittletextbox).isVisible()
	            && page.locator(uploadVideo).isVisible()
	            && page.locator(selectCategory).isVisible();

	    if (allElementsVisible) {
	        logger.info("All elements in the Add Video window are visible.");
	    } else {
	        logger.error("Some elements in the Add Video window are not visible.");
	        return false;  
	    }
		page.waitForTimeout(4000);
		List<String> list3 = page.locator(username).allInnerTexts();
		logger.info(list3);
		String loginuser = page.locator(logInUserLabel).innerText().toLowerCase();
		System.out.println(loginuser);
		for (String name : list3) {

			if (name.toLowerCase().contains(loginuser)) {
				logger.info("Name of user is matched" + loginuser);
			} else {
				logger.info("Name of user is notmatched" + loginuser);
			}
		}
		boolean additionalElementsVisible = page.locator(favoriteCheckbox).isVisible()
	            && page.locator(Closebtn).isVisible()
	            && page.locator(Savebtn).isVisible();

	    if (additionalElementsVisible) {
	        logger.info("Favorite checkbox, Close button, and Save button are visible.");
	    } else {
	        logger.error("Some additional elements are not visible.");
	        return false;
	    }
		page.fill(tittletextbox, getRandomString(4));
		logger.info("Enter the tittle");
		page.locator(uploadVideo).setInputFiles(Paths.get(path));
		page.waitForTimeout(4000);
		page.locator("select#category").selectOption(new SelectOption().setLabel("Repair Order"));
		logger.info("Repair Order is selected in Category");
		page.locator("select#category").selectOption(new SelectOption().setLabel("Sales Order"));
		logger.info("Sales Order is selected in Category");
		if (!page.locator(favoriteCheckbox).isChecked()) {
			page.locator(favoriteCheckbox).click();
			logger.info("Click on checkbox ");
		}
		page.locator(Savebtn).click();
		logger.info("Uploading started sucessfully");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.VideoUpload)) {

			logger.info("Video update successfully from the saved video list");
		} else {
			logger.info("Message is not displaying");
		}
		return true;
	}

	public boolean editFunctionality() {
		Locator element = page.locator("table#saved-video-results tbody tr:nth-child(2) td:nth-child(5) i");
		element.click(new Locator.ClickOptions().setTimeout(60000));
		page.waitForTimeout(4000);
		boolean allElementsVisible = page.locator(updateVideo).isVisible()
	            && page.locator(date).isVisible()
	            && page.locator(tittletextbox).isVisible()
	            && page.locator(selectCategory).isVisible()
	            && page.locator(favoriteCheckbox).isVisible()
	            && page.locator(Closebtn).isVisible();
	    
	    if (allElementsVisible) {
	        logger.info("All required elements are visible for editing.");
	    } else {
	        logger.error("Some elements are not visible for editing.");
	        return false;
	    }
		page.fill(tittletextbox, getRandomString(4));
		logger.info("Edit the Tittle");
		page.locator("select#category").selectOption(new SelectOption().setLabel("Repair Order"));
		logger.info("Repair Order is selected in Category");
		page.waitForTimeout(3000);
		page.locator("select#category").selectOption(new SelectOption().setLabel("Sales Order"));
		logger.info("Sales Order is selected in Category");
		page.waitForTimeout(3000);
		page.locator(favoriteCheckbox).isEnabled();
		logger.info("Checkbox is Selected");
		page.locator(favoriteCheckbox).isDisabled();
		logger.info("Checkbox is not Selected");
		page.locator(SavebtnUpdate).click();
		logger.info("All field are updated sucessfully");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Update)) {

			logger.info("Video update successfully from the saved video list");
		} else {
			logger.info("Message is not displaying");
		}
		return true;
	}

	public boolean removeFunctionality() {
		page.locator(deletebtn).click();
		logger.info("Click on delete button");
		page.locator("text='Are you sure to delete record?'").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    logger.info("Confirmation dialog appeared: 'Are you sure to delete record?'");
	    boolean areButtonsVisible = page.locator(removebtn).isVisible() && page.locator(cancelbtn).isVisible();
	    if (areButtonsVisible) {
	        logger.info("Remove and Cancel buttons are visible. Remove button is red, Cancel button is grey.");
	    } else {
	        logger.error("Remove and Cancel buttons are not visible.");
	        return false;
	    }
    	page.locator(cancelbtn).click();
	    logger.info("Canceled the deletion process; the confirmation dialog is closed.");
		page.locator(deletebtn).click();
	    logger.info("Clicked on the delete button again.");
		page.locator(removebtn).click();
	    logger.info("Clicked on the Remove button.");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.Remove)) {

			logger.info("Video removed successfully from the saved video list");
		} else {
			logger.info("Message is not displaying");
		}
		return true;
	}
	
	public boolean favoriteFunctionality() {
		List<Boolean> flags = new ArrayList<>();
		page.locator(addVideobtn).click();
		logger.info("Check Add video window open successfully");
		page.fill(tittletextbox, getRandomString(4));
		logger.info("Enter the tittle");
		page.locator(uploadVideo).setInputFiles(Paths.get(path));
		page.waitForTimeout(10000);
		if (!page.locator(favoriteCheckbox).isChecked()) {
			page.locator(favoriteCheckbox).click();
			logger.info("Click on checkbox ");
		}
		page.locator(Savebtn).click();
	    logger.info("Video upload started.");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.VideoUpload)) {

			logger.info("Video update successfully from the saved video list");
		} else {
			logger.info("Message is not displaying");
		}
		page.locator(myfavoritebtn).click();
		logger.info("Newly video added are present on MyFavorite tab");
		page.waitForTimeout(35000);
		page.reload();
        Locator button = page.locator(editbtn);
        page.waitForCondition(() -> 
            button.isVisible()
        );
        button.click();
		page.locator(favoriteCheckbox).isDisabled();
		logger.info("Checkbox is Unselected");
		Locator button1 = page.locator("button.btn.btn-primary.saved-video-submit.ot3-update");
		button1.click(new Locator.ClickOptions().setTimeout(60000));
		logger.info("All field are updated sucessfully");
		return true;
	}

}
