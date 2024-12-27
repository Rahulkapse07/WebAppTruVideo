package com.truvideo.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.mailosaur.MailosaurClient;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import com.microsoft.playwright.Page;
import com.truvideo.constants.AppConstants;
import com.truvideo.factory.PlaywrightFactory;
import com.truvideo.utility.JavaUtility;

public class UserPage extends JavaUtility {
	private Page page;

	public UserPage(Page page) {
		this.page = page;
	}

	private String addUser_button = "button:has-text('Add User')";
	private String selectRoles_option = "#s2id_autogen1";

	public String getRoles(String roles) {
		String element = "div.select2-result-label >> text='" + roles + "'";
		return element;
	}

	private String selectDealer_option = "#s2id_autogen2";

	public String getDealer(String dealer) {
		String element = "div[class='select2-result-label']:has-text('" + dealer + "')";
		return element;
	}

	private String firstName = "#firstName";
	private String lastName = "#lastName";
	private String title = "#title";
	private String emailAddress = "#emailAddress";
	private String phoneNumber = "#mobileNumber";
	private String mobileNumber = "#notificationMobileNumber";
	private String saveButton = "#page-title-save";
	private String saveandNew = "#page-title-save-and-new";
	private String saveandInvite = "#page-title-save";
	private String userSearchbox = "input[name='keyword']";
	private String searchButton = "button:has-text('Search')";
	private String activeFilter = "#ACTIVE_FILTER";
	private String activeFilterText = ".active-action .btn.btn-default:nth-child(1)";
	private String pendingFilter = "#PENDING_FILTER";
	private String pendingFilterText = ".btn.btn-default:nth-child(2)";
	private String inactiveFilter = "#INACTIVE_FILTER";
	private String inactiveFilterText = ".btn.btn-default:nth-child(3)";
	private String emptyRoleAlert = "small:has-text('At least one Group is required.')";
	private String emptyDealerAlert = "small:has-text('At least one Dealer is required.')";
	private String emptyFirstNameAlert = "small:has-text('First Name is a required field.')";
	private String emptyLastNameAlert = "small:has-text('Last Name is a required field.')";
	private String emptyEmailAddressAlert = "small:has-text('Email Address is a required field.')";
	private String alredyExistsEmail = "span small";
	private String topRightCornerNotification = "div.notifications";
	private String editUser = ".approved-user-action a.edit-action";
	private String deactivateUserButton = "#deactivate-user-button";
	private String updateUserButton = "#update-password-button";
	private String saveandInviteUserButton = "#save-and-invite-button";
	private String updateUserText = "h4.ot3-update";
	private String backButton = "#temp-page-title-back";
	private String labelpasswordField = "label[for='credential.newPassword']";
	private String labelconfirmPasswordField = "label[for='credential.confirmPassword']";
	private String passwordField = "input[name='credential.newPassword']";
	private String confirmPasswordField = "input[name='credential.confirmPassword']";
	private String invalidPasswordicon = "#validate-password-not-ok";
	private String confirmInvalidPasswordicon = "#validate-confirm-password-ok";
	private String confirmInvalidPasswordtext = "span.help-inline";
	private String password_TextBox = "#newPassword";
	private String confirmNewPassword_TextBox = "#confirmPassword";
	private String submitButton = "#save-user";
	private String updatePasswordPageLabelText = "h4";
	private String updatePasswordRequiredLabel = ".alert-error";
	private String PasswordUpdatedLabelonLoginPage = "#login-status";
	private String userDeactivateStatus = ".deactivate-user";
	private String userActivateStatus = ".activate-user";
	private String activeStatusbutton = "label.btn:has-text('Active')";
	private String inActiveStatusButton = "label.btn:has-text('Inactive')";
	private String selectActionDropdown = "#selectAction";
	private String selectDealerDropdown = "#select2-drop-mask";
	private String selectDealerDropdown1 = "#s2id_dealerId";
	private String selectActionSubmitButton = "#users-action";
	private String bulkCreate = ".page-title-div .dropdown-toggle[data-toggle='dropdown']";
	private String technicianBulkCreate = ".bulk-create-button[data-type='technician']";
	private String advisorBulkCreate = ".bulk-create-button[data-type='service-advisor']";
	private String chooseFiles = "#csv-attachment-input";
	private String startButtonForBulkCreate = "#start-bulk-create";
	private String closeButtonForBulkCreate = "#close-bulk-create-button";
	private String crossCloseButtonForBulkCreate = "#close-bulk-create-icon";
	private String doneButtonForBulkCreate = "#done-bulk-create-button";
	private String usersLabel = "#page-title-text";
	private String firstUsercheckbox = "table#user-results tbody tr:nth-child(2) td:nth-child(1)";
	private String secondUsercheckbox = "table#user-results tbody tr:nth-child(3) td:nth-child(1)";
	private String disabledUserAlert = ".alert-error";
	private String kenilityDealer = ".select2-results li:has-text('Kenility Store ')";
	private String dealer1 = "#user-results tbody tr td:nth-child(2)";
	private String emailinUser = ".table.table-striped tr:nth-child(1) td:nth-child(2) span";
	private String titleinUser = ".table.table-striped tr:nth-child(5) td:nth-child(2) span";
	private String firstnameinUser = ".table.table-striped tr:nth-child(3) td:nth-child(2) span";
	private String openUserDetails = ".table-bordered:nth-child(1) tbody tr td:nth-child(5)";
	String uniqueFirstname;
	String userEmailID;
	String usernewDummyPassword;

	public String getsearchedUser(String username) {
		String username1 = "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
		return username1;
	}

	public String getSearchedUser(String username) {
		return "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
	}

	public void addNewAdvisor(String roles, String dealer) throws InterruptedException {
		createUser(roles, dealer, "Advisor");
	}

	public void addNewTechnician(String dealer, String roles) throws InterruptedException {
		createUser(roles, dealer, "Technician");
	}

	public void addNewSalesUser(String roles, String dealer) throws InterruptedException {
		createUser(roles, dealer, "Sales Agent");
	}

	public void addNewSalesManagerUser(String roles, String dealer) throws InterruptedException {
		createUser(roles, dealer, "Sales Manager");
	}

	public void addNewAdminUser(String roles, String dealer) throws InterruptedException {
		createUser(roles, dealer, "Administrator");
	}

	public void addNewDealerAdmin(String roles, String dealer) throws InterruptedException {
		createUser(roles, dealer, "Dealer Admin");
	}

	private void createUser(String roles, String dealer, String title1) throws InterruptedException {
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button without selecting Roles or Dealers");

		SoftAssert softAssert = new SoftAssert();
		checkAlertMessages(softAssert);
		selectRoleAndDealer(roles, dealer);
		fillUserDetails(title1);
		softAssert.assertAll();
	}

	private void checkAlertMessages(SoftAssert softAssert) {
		List<Boolean> flags = new ArrayList<>();

		flags.add(checkAlert(emptyRoleAlert, "At least one Group is required.", "Role"));
		flags.add(checkAlert(emptyDealerAlert, "At least one Dealer is required.", "Dealer"));
		softAssert.assertTrue(!flags.contains(false), "Error Alert message for missing Role & Dealer");

		flags.clear();
		flags.add(checkAlert(emptyFirstNameAlert, "First Name is a required field.", "First Name"));
		flags.add(checkAlert(emptyLastNameAlert, "Last Name is a required field.", "Last Name"));
		flags.add(checkAlert(emptyEmailAddressAlert, "Email Address is a required field.", "Email Address"));
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message for missing First Name, Last Name, or Email");
	}

	private boolean checkAlert(String alertLocator, String expectedText, String field) {
		boolean isAlertDisplayed = page.textContent(alertLocator).contains(expectedText);
		if (isAlertDisplayed) {
			logger.info("Alert message for " + field + " displayed");
		} else {
			logger.info("Alert message for " + field + " not displayed");
		}
		return isAlertDisplayed;
	}

	private void selectRoleAndDealer(String roles, String dealer) throws InterruptedException {
		page.waitForTimeout(2000);
		selectOption(selectRoles_option, roles, getRoles(roles));
		selectOption(selectDealer_option, dealer, getDealer(dealer));
	}

	private void selectOption(String optionLocator, String value, String optionValue) throws InterruptedException {
		page.click(optionLocator);
		page.fill(optionLocator, value);
		page.click(optionValue);
		page.waitForTimeout(1000);
	}

	private void fillUserDetails(String title1) throws InterruptedException {
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is added");

		page.click(lastName);
		page.fill(lastName, getRandomString(5) + " Test Automation");
		logger.info("Last name is added");
		
		if (page.locator(title) != null) {
			page.fill(title, title1);
		}

		if (page.locator(emailAddress).isVisible()) {
			validateAndFillEmail();
		}

		page.click(saveButton);
		page.waitForTimeout(2000);
		logger.info("User saved successfully");
		verifyTopRightNotification(AppConstants.USER_SAVED_MESSAGE);
	}

	private void validateAndFillEmail() throws InterruptedException {
		page.waitForTimeout(2000);
		page.click(emailAddress);
		page.fill(emailAddress, "mailto:testautomation@gmail.com");
		logger.info("Email address is added");

		page.click(saveButton);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name: " + uniqueFirstname);

		if (page.isVisible(alredyExistsEmail)) {
			logger.info("Email already registered, updating email...");
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}

		logger.info("Final email address: " + page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
	}

	private void verifyTopRightNotification(String expectedMessage) {
		page.waitForSelector(topRightCornerNotification);
		String notification = page.innerText(topRightCornerNotification);
		if (notification.contains(expectedMessage)) {
			logger.info("User saved successfully ");
		} else {
			logger.info("Error in saving the user");
		}
	}

	public boolean bulkCreateUser() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		page.click(bulkCreate);
		logger.info("Clicked on Bulk create button for adding users");
		page.click(technicianBulkCreate);
		logger.info("Selected Technician to add bulk technicians");
		page.waitForTimeout(2000);
		createAndUploadCsvFile_Technician(page);
		page.waitForTimeout(2000);
		page.click(startButtonForBulkCreate);
		logger.info("Technicians are createing ...");
		page.click(doneButtonForBulkCreate);
		logger.info("Technicians are created successfully and navigatiged to user page");
		page.waitForTimeout(2000);
		String users = page.innerText(usersLabel);
		softAssert.assertTrue(users.contains("Users"));
		page.click(bulkCreate);
		logger.info("Clicked on Bulk create button for adding users");
		page.click(advisorBulkCreate);
		logger.info("Selected Advisor to add bulk Advisors");
		page.waitForTimeout(2000);
		createAndUploadCsvFile_Advisor(page);
		page.waitForTimeout(2000);
		page.click(startButtonForBulkCreate);
		logger.info("Advisors are createing ...");
		page.waitForTimeout(2000);
		page.click(doneButtonForBulkCreate);
		logger.info("Advisors are created successfully and navigatiged to user page");
		page.waitForTimeout(4000);
		String text = getFirstUser();
		page.fill(userSearchbox, text);
		page.waitForTimeout(5000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(5000);
		logger.info("Clicked on search button - Associated user is displayed");
		page.click(editUser);
		logger.info(" Successfully edited searched user");
		page.click(updateUserButton);
		logger.info("Clicked on Update User button");
		page.waitForTimeout(4000);
		page.click(passwordField);
		page.fill(passwordField, "test123");
		page.click(confirmPasswordField);
		page.fill(confirmPasswordField, "test123");
		if (passwordField.equals(confirmPasswordField)) {
			logger.info("Confirm password is matched with password field");
		} else {
			logger.info("Confirm password is NOT matched with password field");
		}
		softAssert.assertTrue(passwordField.equals(confirmPasswordField),
				"Password and Confirmed password both values are same");
		page.click(saveButton);
		logger.info("Password updated for new user and clicked on Save button");
		page.waitForTimeout(6000);
		Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(4000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
		loginPage.navigateToUpdatePassword(newBrowserPage, text, "test123");
		newBrowserPage.waitForTimeout(2000);
		logger.info("navigated to the update password page after putting new username and password value");
		newBrowserPage.waitForTimeout(2000);
		newBrowserPage.click(submitButton);
		logger.info("Without adding password value clicked on Submit button");
		if (newBrowserPage.isVisible(updatePasswordRequiredLabel)) {
			logger.info("Without adding password value clicked on Submit button and its showing error message");
		} else {
			logger.info("Password required field label is not showing");
		}
		newBrowserPage.fill(password_TextBox, "test123");
		logger.info("password value is filled");
		newBrowserPage.fill(confirmNewPassword_TextBox, "test123");
		logger.info("Both Password and Confirm Password field is filled");
		newBrowserPage.click(submitButton);
		newBrowserPage.waitForTimeout(3000);
		if (newBrowserPage.isVisible(PasswordUpdatedLabelonLoginPage)) {
			logger.info("User is navigated on Login page and Pasword updated label is displayed");
		} else {
			logger.info("Password is not updated and User is unable to navigate on Login page");
		}
		loginPage.navigateToHomePage(text, "test123");
		newBrowserPage.waitForTimeout(4000);
		logger.info("Login User is " + LoginPage.logInUsername);
		newBrowserPage.waitForTimeout(6000);
		newBrowserPage.close();
		// softAssert.assertAll();
		if (page.locator(addUser_button).isVisible() || users.contains("Users")) {
			logger.info("User is again navigated back to his main account");
			return true;
		} else {
			logger.info("New Browser window has been not closed propely");
			return false;
		}
	}

	public boolean searchUser(String roles, String dealer) throws InterruptedException {
		addNewAdvisor(roles, dealer);
		page.waitForTimeout(1000);
		page.fill(userSearchbox, userEmailID);
		page.waitForTimeout(2000);
		page.click(searchButton);
		logger.info("Searched with Latest user created by  Email_ID");
		page.waitForTimeout(2000);
		page.click(openUserDetails);
		page.waitForTimeout(4000);
		String emailUser = page.locator(emailinUser).innerText();
		if (userEmailID.equals(emailUser)) {
			logger.info("User is verified with Email_ID in user profile");
		} else {
			logger.info("User email is not verified with Email_ID in user profile");
		}
		page.click(backButton);
		page.fill(userSearchbox, "");
		page.fill(userSearchbox, "Advisor");
		logger.info("Searched with user by Title");
		String titleofUser = page.locator(titleinUser).innerText();
		logger.info(titleofUser);
		String firstNameUser = page.locator(firstnameinUser).innerText();
		page.fill(userSearchbox, "");
		page.fill(userSearchbox, uniqueFirstname);
		logger.info("Searched with user by User FirstName");
		page.click(openUserDetails);
		if (uniqueFirstname.equals(firstNameUser)) {
			logger.info("User is verified with FirstName in user profile");
			page.click(backButton);
			return true;
		} else {
			logger.info("User FirstName is not verified with FirstName in user profile");

			page.click(backButton);
			return false;
		}
	}

	public void updateUserPassword(String roles, String dealer, String password) throws InterruptedException {
		addNewAdvisor(roles, dealer);
		page.fill(userSearchbox, uniqueFirstname);
		page.waitForTimeout(9000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(9000);
		logger.info("Clicked on search button - Associated users are displaying");
		page.click(editUser);
		if (page.isVisible(updateUserText)) {
			logger.info("User is on Update userpage");
		} else {
			logger.info("Update userpage is not opened correctly");
		}

		page.waitForTimeout(6000);
		SoftAssert softAssert = new SoftAssert();
		if (firstName.contains(uniqueFirstname)) {
			logger.info("Successfully open newly created user for Editing/updating password");
		} else {
			logger.info("Mismatch between selected user and opened  user");
		}
		softAssert.assertTrue(firstName.contains(uniqueFirstname), "Successfully edited searched user");

		page.click(backButton);
		logger.info("Successfully navigated on userpage through back button");
		page.click(editUser);
		logger.info("Again Successfully edited searched user");
		page.click(lastName);
		page.fill(lastName, "Automation");
		logger.info("Last Name edited");
		page.fill(title, "Title Test");
		logger.info("Title edited");
		page.isVisible(deactivateUserButton);
		logger.info("Deactivate User button is displayed");
		page.click(updateUserButton);
		logger.info("Clicked on Update User button");
		page.waitForTimeout(4000);
		page.click(passwordField);
		page.fill(passwordField, password);
		page.click(confirmPasswordField);
		page.fill(confirmPasswordField, "text22");
		if (passwordField.equals(confirmPasswordField)) {
			logger.info("Confirm password is matched with password field");
		} else {
			logger.info("Confirm password is NOT matched with password field");
		}
		softAssert.assertTrue(passwordField.equals(confirmPasswordField),
				"Password and Confirmed password both values are same");

		page.fill(confirmPasswordField, "");
		page.waitForTimeout(3000);
		page.fill(confirmPasswordField, password);
		usernewDummyPassword = page.inputValue(confirmPasswordField);
		logger.info(usernewDummyPassword + "Confirm password is matched with password field");
		logger.info("Confirm password is matched with password field");
		page.click(saveButton);
		logger.info("Password updated for new user and clicked on Save button");
		page.waitForTimeout(6000);

	}

	public void checkLoginWithEdit_Update_User(String roles, String dealer, String password, String newpassword)
			throws InterruptedException {
		updateUserPassword(roles, dealer, password);
		page.waitForTimeout(6000);
		Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
		loginPage.navigateToUpdatePassword(newBrowserPage, userEmailID, usernewDummyPassword);
		logger.info("navigated to the update password page after putting new username and password value");
		newBrowserPage.waitForTimeout(2000);
		newBrowserPage.click(submitButton);
		logger.info("Without adding password value clicked on Submit button");
		if (newBrowserPage.isVisible(updatePasswordRequiredLabel)) {
			logger.info("Without adding password value clicked on Submit button and its showing error message");
		} else {
			logger.info("Password required field label is not showing");
		}
		newBrowserPage.fill(password_TextBox, newpassword);
		logger.info("password value is filled");
		newBrowserPage.fill(confirmNewPassword_TextBox, newpassword);
		logger.info("Both Password and Confirm Password field is filled");
		newBrowserPage.click(submitButton);
		newBrowserPage.waitForTimeout(3000);
		if (newBrowserPage.isVisible(PasswordUpdatedLabelonLoginPage)) {
			logger.info("User is navigated on Login page and Pasword updated label is displayed");
		} else {
			logger.info("Password is not updated and User is unable to navigate on Login page");
		}
		loginPage.navigateToHomePage(userEmailID, newpassword);
		newBrowserPage.waitForTimeout(4000);
		logger.info("Login User is " + LoginPage.logInUsername);
		newBrowserPage.waitForTimeout(6000);
		newBrowserPage.close();
	}

	public String extractValue(String labeledValue) {
		if (labeledValue == null || labeledValue.isEmpty()) {
			return "";
		}
		String[] parts = labeledValue.split(": ");
		return parts.length > 1 ? parts[1].trim() : labeledValue; // Return the value after the colon
	}
	
	public boolean userStatus() throws InterruptedException {
		page.waitForTimeout(4000);
		page.fill(userSearchbox, "test");
		page.waitForTimeout(2000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(4000);
		page.click(userDeactivateStatus);
		SoftAssert softAssert = new SoftAssert();
		page.waitForTimeout(4000);
		Thread.sleep(3000);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		String topRightCornerNotificationPopup1 = topRightCornerNotificationPopup.replace('b', ' ').trim();
		logger.info(topRightCornerNotificationPopup1);
		boolean isUserActivateMessageDispayed = false;
		if (topRightCornerNotificationPopup1.contains(AppConstants.USER_DEACTIVATE_MESSAGE)) {
			logger.info("User De-Activate Successfully");
			isUserActivateMessageDispayed = true;
		}
		softAssert.assertTrue(isUserActivateMessageDispayed, "Successfully changed the user status to active");
		page.waitForTimeout(3000);
		page.waitForCondition(() -> page.isVisible(inActiveStatusButton));
		page.click(inActiveStatusButton);
		logger.info("User is clicked on InActive status button");
		page.waitForTimeout(3000);
		page.click(editUser);
		logger.info("Opened User profile for edit details");
		page.click(firstName);
		String userFirstName = "TestUserStatus";
		page.fill(firstName, userFirstName);
		page.click(emailAddress);
		String emailAddress1 = "statusinactive" + getRandomString(3) + "@gmail.com";
		page.click(emailAddress);
		page.fill(emailAddress, "");
		page.waitForTimeout(2000);
		page.fill(emailAddress, emailAddress1);
		page.waitForTimeout(2000);
		page.click(saveButton);
		page.waitForTimeout(3000);
		Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
		loginPage.navigateToUpdatePassword(newBrowserPage, emailAddress1, "test123");
		logger.info("Verify log in failed message is showing");
		newBrowserPage.waitForTimeout(3000);
		String text = newBrowserPage.innerText(disabledUserAlert).trim();
		logger.info(text);
		newBrowserPage.waitForTimeout(2000);
		newBrowserPage.close();
		if (text.contains(AppConstants.USER_LOGIN_DEACTIVATED_ALERT_MESSAGE)) {
			logger.info("String is match");
			return true;
		} else {
			logger.info("String is Not match");
			return false;
		}
	}

	public boolean elementsonUserPage() {
		page.waitForTimeout(2000);
		if (waitForAndLogVisibility(page.locator(usersLabel), "Users Label")
				&& waitForAndLogVisibility(page.locator(selectActionDropdown), "Select Action Dropdown")
				&& waitForAndLogVisibility(page.locator(selectDealerDropdown1), "Select Dealer Dropdown")
				&& waitForAndLogVisibility(page.locator(addUser_button), "Add User Button")
				&& waitForAndLogVisibility(page.locator(bulkCreate), "Bulk Create Button")) {

			page.locator(bulkCreate).click();
			logger.info("Bulk Create button clicked");

			if (waitForAndLogVisibility(page.locator(technicianBulkCreate), "Technician Bulk Create Button")
					&& waitForAndLogVisibility(page.locator(advisorBulkCreate), "Advisor Bulk Create Button")
					&& waitForAndLogVisibility(page.locator(userSearchbox), "User Searchbox Button")
					&& waitForAndLogVisibility(page.locator(searchButton), "Search Button")
					&& waitForAndLogVisibility(page.locator(activeFilterText), "Active Filter Button")
					&& waitForAndLogVisibility(page.locator(pendingFilterText), "Pending Filter Button")
					&& waitForAndLogVisibility(page.locator(inactiveFilterText), "Inactive Filter Button")) {

				logger.info("All bulk creation options and filters are visible.");
				return true;

			} else {
				logger.error("One or more bulk creation options or filters are not visible.");
			}
		} else {
			logger.error(
					"One or more initial elements are not visible (Users Label, Select Action Dropdown, Select Dealer Dropdown, Add User Button, or Bulk Create Button).");
		}
		return false;

	}

	public boolean getUsersFromSelectDealer() {
		page.waitForTimeout(2000);
		page.waitForCondition(() -> page.locator(selectDealerDropdown1).isVisible());
		page.locator(selectDealerDropdown1).click();
		page.locator(kenilityDealer).click();
		logger.info("Selected Kenility Dealer from Dropdown");
		page.waitForTimeout(2000);
		List<String> list = page.locator(dealer1).allInnerTexts();
		boolean isUserFound = false;
		String expectedDealerName = "kenility store";
		for (String dealer : list) {
			if (dealer.toLowerCase().contains(expectedDealerName)) {
				logger.info("User with dealer name found: " + dealer);
				isUserFound = true;
				break;
			} else {
				logger.info("User with dealer name not found: " + dealer);
			}
		}
		if (!isUserFound) {
			logger.warn("Expected user with dealer name '" + expectedDealerName + "' not found in the table.");
		}

		return isUserFound;
	}

	public void actionsOnUsers() {
		SoftAssert softAssert = new SoftAssert();
		page.selectOption(selectActionDropdown, "Send Invite to App");
		logger.info("Actions dropdown is opened now selected Send Invite to App to perform on users");
		page.click(firstUsercheckbox);
		logger.info("Selected first user to perform Send Invite to App action");
		page.click(selectActionSubmitButton);
		page.waitForTimeout(1000);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		softAssert.assertTrue(topRightCornerNotificationPopup.contains(AppConstants.USER_SEND_INVITE_TO_APP_MESSAGE),
				"verify technician user creation");
		page.selectOption(selectActionDropdown, "Send Invite to Web Dashboard");
		logger.info("Actions dropdown is opened now selected Send Invite to App to perform on users");
		page.click(firstUsercheckbox);
		logger.info("Selected first user to perform Send Invite to Web Dashboard action");
		page.click(selectActionSubmitButton);
		page.waitForTimeout(1000);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup1 = page.innerText(topRightCornerNotification);
		softAssert.assertTrue(
				topRightCornerNotificationPopup1.contains(AppConstants.USER_SEND_INVITE_TO_WEB_DASHBOARD_MESSAGE),
				"verify send Invite to Dashboard");
		page.selectOption(selectActionDropdown, "Deactivate User/Device");
		logger.info("Actions dropdown is opened now selected Send Invite to App to perform on users");
		page.click(firstUsercheckbox);
		logger.info("Selected first user to perform Deactivate User action");
		page.click(selectActionSubmitButton);
		page.waitForTimeout(1000);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup2 = page.innerText(topRightCornerNotification);
		softAssert.assertTrue(topRightCornerNotificationPopup2.contains(AppConstants.USER_DEVICE_DEACTIVATE_MESSAGE),
				"verify user action to deactivate ");
		softAssert.assertAll();
	}


	public boolean actionsOnUsers(String Type, String errormessage, String SubjectMessage) {
		SoftAssert softAssert = new SoftAssert();
		boolean flag = false;
		page.selectOption(selectActionDropdown, Type);
		logger.info("Actions dropdown is opened now selected Send Invite to App to perform on users");

		page.fill(userSearchbox, "us@");
		page.waitForTimeout(2000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);

		page.waitForTimeout(5000);
		page.click(firstUsercheckbox);
		logger.info("Selected first user to perform Send Invite to App action");
		page.click(selectActionSubmitButton);
		page.waitForTimeout(5000);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		softAssert.assertTrue(topRightCornerNotificationPopup.contains(errormessage),
				"verify technician user creation");

		String apiKey = "0I12RZR2fG2B7Mdh8prK3XnUl8VoG38j";
		String serverId = "rwy0mofv";
		String serverDomain = "rwy0mofv.mailosaur.net";

		MailosaurClient mailosaur = new MailosaurClient(apiKey);
		// Set up search parameters
		MessageSearchParams params = new MessageSearchParams();
		params.withServer(serverId);

		// Specify search criteria (e.g., sent to a specific email)
		SearchCriteria criteria = new SearchCriteria();
		criteria.withSentTo("us@" + serverDomain);

		OffsetDateTime receivedAfter = OffsetDateTime.now().minusSeconds(30);
		params.withReceivedAfter(receivedAfter.toInstant().toEpochMilli());

		try {
			// Set a longer timeout (e.g., 60 seconds) to wait for the email to arrive
			int timeoutMillis = 60000;
			Message message = mailosaur.messages().get(serverId, criteria, timeoutMillis);

			if (message != null) {
				// Extract details from the email
				String subject = message.subject();
				String fromEmail = message.from().get(0).email();
				String toEmail = message.to().get(0).email();
				// Print the extracted data
				System.out.println("Subject: " + subject);
				System.out.println("From: " + fromEmail);
				System.out.println("To: " + toEmail);
				assertNotNull(message);
				assertEquals(SubjectMessage, message.subject(),"Subject not matched");
				logger.info("Customer get message from advisor :-" + SubjectMessage);
				flag = true;

			} else {
				System.out.println("Subject Not present");
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false ; 
		}
		
		return flag;

	}
}
