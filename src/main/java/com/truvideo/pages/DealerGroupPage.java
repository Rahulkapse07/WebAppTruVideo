package com.truvideo.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.truvideo.constants.AppConstants;
import com.truvideo.utility.JavaUtility;

public class DealerGroupPage extends JavaUtility {
	Page page;

	public DealerGroupPage(Page page) {
    this.page=page;
	}
	
	private String addDealerGroupbtn = "#group-add";
	private String addDealerGroupHeader = "h4.ot3-add";
	private String enterName = "form#group-form .controls div input#name";
	private String dealerSearch = ".dual-listbox__container div:nth-child(1) input.dual-listbox__search.search";
	private String truvideoDealer =  ".dual-listbox__available.list li[data-id='172']:has-text('TruVideo')";
	private String truvideoSelDealer = ".dual-listbox__selected.list li[data-id='172']:has-text('TruVideo')";
	private String demoToyotaDealer = "li.dual-listbox__item[data-id='41']";
	private String kenilityStoreDealer = "li.dual-listbox__item[data-id='2182']";
	private String cdkTestDealer = "li.dual-listbox__item[data-id='1316']";
	private String phonePurchase = "li.dual-listbox__item[data-id='2212']";
	
	private String availableOptions = "div.dual-listbox__title.title:has-text('Available options')";
	private String selctedOptions = "div.dual-listbox__title.title:has-text('Selected options')";
	private String rightArrow = ".dual-listbox__button.btn.button:nth-child(2)";
	private String leftArrow = ".dual-listbox__button.btn.button:nth-child(3)";
	private String saveBtn = ".btn.btn-success";
	
	private String searchDealerGroup = ".controls div input.input-block-level";
	private String searchBtn = ".btn.btn-primary.btn-block";
	//private String editBtn = "table#group-results tbody tr td i.icon-pencil.edit-action";
	private String editBtn = "tr td i.icon-pencil.edit-action";
	private String deleteBtn = "tr td i.icon-trash.remove-action";
	private String removebtn = ".bootbox.modal.fade.in a.btn.btn-danger";
	private String cancelbtn = ".bootbox.modal.fade.in div >a.btn.btn:has-text('Cancel')";
	private String topRightCornerNotification = "div.notifications";
	
	
	private String userName = "form#user-search-form input[type='text']";
	private String userSearch = "form#user-search-form button[type='submit']";
	private String editUser = ".approved-user-action a.edit-action";
    private String dealerGroupAssign = "#s2id_autogen3";
	private String saveButton = "#page-title-save";
	private String ym ="span.select2-match:has-text('YM')";
	


	public boolean userCreateDeleteEditDealerGroup() throws InterruptedException {
		//User can create Dealer Group,Edit Dealer Group and Delete Dealer Group
		page.waitForTimeout(3000);
		page.locator(addDealerGroupbtn).click();
		logger.info("click on Add Dealer Group button");
		page.locator(addDealerGroupHeader).isVisible();
		logger.info("Navigate to Add dealer group page");
		page.locator(enterName).fill("TestQA");
		logger.info("Enter dealer group name");
		//page.locator(dealerSearch).fill("TruVideo");
		page.waitForTimeout(5000);
		page.waitForCondition(()-> page.locator(truvideoDealer).isVisible());
		logger.info("Enter TruVideo dealer name");
		page.locator(truvideoDealer).click();
		page.waitForTimeout(10000);
		page.locator(rightArrow).click();
//	    page.waitForCondition(()-> page.locator(truvideoSelDealer).isVisible());
//		page.locator(truvideoSelDealer).click();
//		//logger.info("TruVideo dealer is display in Selected list");
//    	page.waitForTimeout(10000);
		page.locator(leftArrow).click();
//		page.waitForCondition(()-> page.locator(truvideoDealer).isVisible());
//		logger.info("TruVideo dealer is display in Available list");
//		page.locator(truvideoDealer).click();
//		page.waitForTimeout(10000);
		page.locator(rightArrow).click();
		page.waitForTimeout(5000);
		page.locator(dealerSearch).clear();
		page.locator(demoToyotaDealer).scrollIntoViewIfNeeded();
		page.locator(demoToyotaDealer).click();
		logger.info("Demo main street toyota dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Demo main street toyota dealer is display in Selected list");
		page.waitForTimeout(3000);
		page.locator(dealerSearch).clear();
		page.locator(kenilityStoreDealer).scrollIntoViewIfNeeded();
		page.locator(kenilityStoreDealer).click();
		logger.info("Kenility Store dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Kenility Store dealer is display in Selected list");
        page.locator(saveBtn).click();
		logger.info("Dealer Group created successfully");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.DealerGroupAdded)) {

			logger.info("Dealer group added sucessfully");
		} else {
			logger.info("Not displaying message");
		}
        page.locator(searchDealerGroup).fill("TestQA");
		logger.info("Enter dealer group name");
        page.locator(searchBtn).click();
		logger.info("Dealer Group search successfully");
		page.waitForTimeout(5000);
		page.locator(editBtn).click();
		logger.info("Click on edit button");
		page.locator(enterName).fill("TestQA1");
		logger.info("Update dealer group name");
		page.locator(saveBtn).click();
		logger.info("Click on Save button");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup1 = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup1);
		if (topRightCornerNotificationPopup1.contains(AppConstants.DealerGroupUpdate)) {

			logger.info("Dealer group updated sucessfully");
		} else {
			logger.info("Not displaying message");
		}
		page.locator(searchDealerGroup).fill("TestQA1");
		logger.info("Enter dealer group name");
        page.locator(searchBtn).click();
		logger.info("Dealer Group search successfully");
		page.waitForTimeout(5000);
		page.locator(deleteBtn).click();
		logger.info("Click on delete button");
		if (page.locator("text='Are you sure to delete record?'").isVisible()) {
	        logger.info("Confirmation dialog appeared: 'Are you sure to delete record?'");
		page.waitForTimeout(8000);
	        if (page.locator(removebtn).isVisible() && page.locator(cancelbtn).isVisible()) {
	            logger.info("Remove and Cancel buttons are visible.");
	        } else {
	            throw new RuntimeException("Remove and Cancel buttons are not visible.");
	        }
	        page.locator(cancelbtn).click();
		    logger.info("Canceled the deletion process; the confirmation dialog is closed.");
			page.locator(deleteBtn).click();
		    logger.info("Clicked on the delete button again.");
	        page.locator(removebtn).click();
	        logger.info("Clicked on the Remove button.");
	    } else {
	        throw new RuntimeException("Confirmation dialog not displayed.");
	    }
	    page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup2 = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup2);
		if (topRightCornerNotificationPopup2.contains(AppConstants.Remove1)) {

			logger.info("Dealer group removed successfully from the list");
		} else {
			logger.info("Message is not displaying");
		}

		return true;
		
	}
	
	public boolean userAssignDGAndSwitchDealerFunctionality(String username,String password) throws InterruptedException {
		// Create New Dealer Group assign to the user and perform switch dealer functionality
		page.locator(addDealerGroupbtn).click();
		logger.info("click on Add Dealer Group button");
		page.locator(addDealerGroupHeader).isVisible();
		logger.info("Navigate to Add dealer group page");
		String delaergroupname = getRandomString(4);
		page.fill(enterName, delaergroupname);
		logger.info("Enter dealer group name");
		page.waitForTimeout(5000);
		page.waitForCondition(()-> page.locator(truvideoDealer).isVisible());
		logger.info("Enter TruVideo dealer name");
		page.locator(truvideoDealer).click();
		page.waitForTimeout(10000);
		page.locator(rightArrow).click();
		page.locator(leftArrow).click();
		page.locator(rightArrow).click();
		page.waitForTimeout(5000);
		page.locator(dealerSearch).clear();
		page.locator(kenilityStoreDealer).scrollIntoViewIfNeeded();
		page.locator(kenilityStoreDealer).click();
		logger.info("Kenility Store dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Kenility Store dealer is display in Selected list");
        page.locator(saveBtn).click();
		logger.info("Dealer Group created successfully");
		page.locator(topRightCornerNotification).waitFor();
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.DealerGroupAdded)) {

			logger.info("Dealer group added sucessfully");
		} else {
			logger.info("Not displaying message");
		}
		HomePage hp = new HomePage(page);
        hp.navigateToUserspage();
        page.locator(userName).fill("Yash Modi");
        logger.info("Enter user name");
        page.locator(userSearch).click();
        logger.info("Search user");
        page.waitForTimeout(8000);
		page.waitForCondition(()-> page.locator(editUser).first().isVisible());
        page.locator(editUser).first().click();
        logger.info("Edit user");
        page.locator(dealerGroupAssign).click();
        page.locator(dealerGroupAssign).fill(delaergroupname);
        page.locator("span.select2-match:has-text('"+ delaergroupname +"')").click();
        logger.info("Dealer group assign successfully to the user");
        page.locator(saveButton).click();
        logger.info("User Update Successfully");
        hp.LogOutfunctionality();
        LoginPage lp = new LoginPage(page);
        lp.navigateToHomePage(username,password);
        hp.switchDealer();

		return true;
		
	}

	
	public boolean multipleDealerGroupAssignToSingleAndMultipleUser(String username,String password,String roles, String dealer) throws InterruptedException{
		
		// Create 1'st DG
		page.locator(addDealerGroupbtn).click();
		logger.info("click on Add Dealer Group button");
		page.locator(addDealerGroupHeader).isVisible();
		logger.info("Navigate to Add dealer group page");
		String delaergroupname = getRandomString(4);
		page.fill(enterName, delaergroupname);
		logger.info("Enter dealer group name");
		page.waitForTimeout(5000);
		page.locator(demoToyotaDealer).scrollIntoViewIfNeeded();
		page.locator(demoToyotaDealer).click();
		logger.info("Demo main street toyota dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Demo main street toyota dealer is display in Selected list");
		page.waitForTimeout(5000);
		page.locator(dealerSearch).clear();
		page.locator(kenilityStoreDealer).scrollIntoViewIfNeeded();
		page.locator(kenilityStoreDealer).click();
		logger.info("Kenility Store dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Kenility Store dealer is display in Selected list");
        page.locator(saveBtn).click();
		logger.info("Dealer Group created successfully");
		String topRightCornerNotificationPopup = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.DealerGroupAdded)) {

			logger.info("Dealer group added sucessfully");
		} else {
			logger.info("Not displaying message");
		}
		
	    // create 2'nd DG	
		page.locator(addDealerGroupbtn).click();
		logger.info("click on Add Dealer Group button");
		page.locator(addDealerGroupHeader).isVisible();
		logger.info("Navigate to Add dealer group page");
		String delaergroupname1 = getRandomString(4);
		page.fill(enterName, delaergroupname1);
		logger.info("Enter dealer group name");
		page.waitForTimeout(5000);
		page.locator(cdkTestDealer).scrollIntoViewIfNeeded();
		page.locator(cdkTestDealer).click();
		logger.info("Cdk Test dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("Cdk Test dealer is display in Selected list");
		page.waitForTimeout(5000);
		page.locator(dealerSearch).clear();
		page.locator(phonePurchase).scrollIntoViewIfNeeded();
		page.locator(phonePurchase).click();
		logger.info("PhonePurchase dealer is display in Available list");
		page.locator(rightArrow).click();
		logger.info("PhonePurchase dealer is display in Selected list");
        page.locator(saveBtn).click();
		logger.info("Dealer Group created successfully");
		String topRightCornerNotificationPopup1 = page.locator(topRightCornerNotification).innerText();
		logger.info(topRightCornerNotificationPopup1);
		if (topRightCornerNotificationPopup1.contains(AppConstants.DealerGroupAdded)) {

			logger.info("Dealer group added sucessfully");
		} else {
			logger.info("Not displaying message");
		}
		HomePage hp = new HomePage(page);
        hp.navigateToUserspage();
        page.locator(userName).fill("Yash Modi");
        logger.info("Enter user name");
        page.locator(userSearch).click();
        logger.info("Search user");
        page.waitForTimeout(8000);
		page.waitForCondition(()-> page.locator(editUser).first().isVisible());
        page.locator(editUser).first().click();
        logger.info("Edit user");
        page.locator(dealerGroupAssign).click();
        page.locator(dealerGroupAssign).fill(delaergroupname);
        page.locator("span.select2-match:has-text('"+ delaergroupname +"')").click();
        page.locator(dealerGroupAssign).fill(delaergroupname1);
        page.locator("span.select2-match:has-text('"+ delaergroupname1 +"')").click();
        logger.info("Dealer group assign successfully to the user");
        page.locator(saveButton).click();
        logger.info("User Update Successfully");
        
        logger.info("Again open user");
        page.waitForTimeout(3000);
        page.locator("form#user-search-form input[type='text']").fill("Yash Modi", 
        	    new Locator.FillOptions().setTimeout(60000));
       //page.locator(userName).fill("Yash Modi");
        logger.info("Enter user name");
        page.locator(userSearch).click();
        logger.info("Search user");
        page.waitForTimeout(8000);
		page.waitForCondition(()-> page.locator(editUser).first().isVisible());
        page.locator(editUser).first().click();
        logger.info("Edit user");
        page.locator(dealerGroupAssign).click();
        page.waitForTimeout(8000);

        String dealerGroupSelector1 = "span.select2-match:has-text('" + delaergroupname + "')";
        String dealerGroupSelector2 = "span.select2-match:has-text('" + delaergroupname1 + "')";
        boolean isGroup1Visible = page.locator(dealerGroupSelector1).isVisible();
        boolean isGroup2Visible = page.locator(dealerGroupSelector2).isVisible();

        if (isGroup1Visible && isGroup2Visible) {
            logger.info("Dealer groups assigned successfully to the user: " + delaergroupname + " & " + delaergroupname1);
        } else {
            logger.info("Dealer groups not assigned properly. Visible: " + 
                (isGroup1Visible ? delaergroupname : "None") + ", " +
                (isGroup2Visible ? delaergroupname1 : "None"));
        }
        
//        page.waitForTimeout(8000);
//        if(page.locator("span.select2-match:has-text('"+ delaergroupname +"')").isVisible() &&
//        		page.locator("span.select2-match:has-text('"+ delaergroupname1 +"')").isVisible()) {
//            logger.info("Verify that Dealer group assign successfully to the user");
//        }
//        else {
//            logger.info("Verify that Dealer group are not assign to the user properly");
//        }
//        hp.navigateToUserspage();
//        page.locator(userName).fill("Gopal Chandre te");
//        logger.info("Enter user name");
//        page.locator(userSearch).click();
//        logger.info("Search user");
//        page.waitForTimeout(8000);
//		page.waitForCondition(()-> page.locator(editUser).first().isVisible());
//        page.locator(editUser).first().click();
//        logger.info("Edit user");
//        page.locator(dealerGroupAssign).click();
//        page.locator(dealerGroupAssign).fill(delaergroupname);
//        page.locator("span.select2-match:has-text('"+ delaergroupname +"')").click();
//        page.locator(dealerGroupAssign).fill(delaergroupname1);
//        page.locator("span.select2-match:has-text('"+ delaergroupname1 +"')").click();
//        logger.info("Dealer group assign successfully to the user");
//        page.locator(saveButton).click();
//        logger.info("User Update Successfully");
//        
        
        
		return true;
		
	}
	
}
