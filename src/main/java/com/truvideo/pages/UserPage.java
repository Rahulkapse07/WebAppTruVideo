package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;
import org.testng.asserts.SoftAssert;
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
		//String element = "div[class='select2-result-label']:has-text('" + roles + "')";
		String element = "div.select2-result-label >> text='"+roles+"'";
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
	private String pendingFilter = "#PENDING_FILTER";
	private String inactiveFilter = "#INACTIVE_FILTER";
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
	String uniqueFirstname;
	String userEmailID;
	String usernewDummyPassword;

	public String getsearchedUser(String username) {
		String username1 = "#user-results tbody tr td:nth-child(3) p:text('" + username + "')";
		return username1;
	}

	public void addNewAdvisor(String roles, String dealer) throws InterruptedException {
		System.out.println("test rnner");
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		page.waitForTimeout(2000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));
		page.waitForTimeout(1000);
		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(1000);
		if (page.textContent(emptyFirstNameAlert).contains("First Name is a required field")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for First Name is Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyLastNameAlert).contains("Last Name is a required field.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Last Name Not is Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyEmailAddressAlert).contains("Email Address is a required field.")) {

			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Email Address Not is Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message when Role,Dealer,First Name,Last Name and Email is not entered");
		page.waitForTimeout(1000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, getRandomString(5) + "Test Automation");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForTimeout(2000);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name " + uniqueFirstname);
		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}
		softAssert.assertAll();
	}

	public void addNewTechnician(String dealer, String roles) throws InterruptedException {
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		page.waitForTimeout(2000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));
		page.waitForTimeout(1000);
		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(1000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, getRandomString(5) + "Test Automation");
		logger.info("Last name is Added");
		page.fill(title, "Technician");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		softAssert.assertTrue(topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE),
				"verify technician user creation");
		softAssert.assertAll();
	}
	public void addNewSalesUser(String roles, String dealer) throws InterruptedException {
		System.out.println("test rnner");
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		page.waitForTimeout(2000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));
		page.waitForTimeout(1000);
		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(1000);
		if (page.textContent(emptyFirstNameAlert).contains("First Name is a required field")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for First Name is Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyLastNameAlert).contains("Last Name is a required field.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Last Name Not is Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyEmailAddressAlert).contains("Email Address is a required field.")) {

			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Email Address Not is Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message when Role,Dealer,First Name,Last Name and Email is not entered");
		page.waitForTimeout(1000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, getRandomString(5) + "Test Automation");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForTimeout(2000);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name " + uniqueFirstname);
		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}
		softAssert.assertAll();
	}
	
	public void addNewSalesManager(String roles, String dealer) throws InterruptedException {
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		page.waitForTimeout(2000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));
		page.waitForTimeout(1000);
		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(1000);
		if (page.textContent(emptyFirstNameAlert).contains("First Name is a required field")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for First Name is Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyLastNameAlert).contains("Last Name is a required field.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Last Name Not is Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyEmailAddressAlert).contains("Email Address is a required field.")) {

			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Email Address Not is Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message when Role,Dealer,First Name,Last Name and Email is not entered");
		page.waitForTimeout(1000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, getRandomString(5) + "Test Automation");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForTimeout(2000);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name " + uniqueFirstname);
		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}
		softAssert.assertAll();
	}
	
	public void addNewAdminUser(String roles, String dealer) throws InterruptedException {
		page.click(addUser_button);
		logger.info("Clicked on Add User Button");
		page.click(saveButton);
		logger.info("Clicked on Save button when no Roles or Dealers are selected");
		SoftAssert softAssert = new SoftAssert();
		List<Boolean> flags = new ArrayList<Boolean>();
		if (page.textContent(emptyRoleAlert).contains("At least one Group is required.")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Role Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyDealerAlert).contains("At least one Dealer is required.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Dealer Not Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false), "Error Alert message when Role & Dealer is not entered");
		flags.clear();
		page.waitForTimeout(2000);
		page.click(selectRoles_option);
		page.fill(selectRoles_option, roles);
		page.click(getRoles(roles));
		page.waitForTimeout(1000);
		page.click(selectDealer_option);
		page.fill(selectDealer_option, dealer);
		page.click(getDealer(dealer));
		page.waitForTimeout(1000);
		if (page.textContent(emptyFirstNameAlert).contains("First Name is a required field")) {
			logger.info("Alert message for Role Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for First Name is Not Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyLastNameAlert).contains("Last Name is a required field.")) {
			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Last Name Not is Displayed");
			flags.add(false);
		}
		if (page.textContent(emptyEmailAddressAlert).contains("Email Address is a required field.")) {

			logger.info("Alert message for Dealer Displayed");
			flags.add(true);
		} else {
			logger.info("Alert message for Email Address Not is Displayed");
			flags.add(false);
		}
		softAssert.assertTrue(!flags.contains(false),
				"Error Alert message when Role,Dealer,First Name,Last Name and Email is not entered");
		page.waitForTimeout(1000);
		page.click(firstName);
		page.fill(firstName, getRandomString(5));
		logger.info("First name is Added");
		page.click(lastName);
		page.fill(lastName, getRandomString(5) + "Test Automation");
		logger.info("Last name is Added");
		page.click(emailAddress);
		page.fill(emailAddress, "testautomation@gmail.com");
		logger.info("Email address is Added");
		page.click(saveButton);
		logger.info("Clicked on Save button");
		page.waitForTimeout(2000);
		uniqueFirstname = page.inputValue(firstName);
		logger.info("Updated First Name " + uniqueFirstname);
		softAssert.assertTrue(page.textContent(alredyExistsEmail).contains("is already registered."),
				"Verify Existing users email error message");
		if (page.isVisible(alredyExistsEmail)) {
			page.fill(emailAddress, getRandomString(5) + "@gmail.com");
		}
		logger.info(page.inputValue(emailAddress));
		userEmailID = page.inputValue(emailAddress);
		page.click(saveButton);
		page.waitForSelector(topRightCornerNotification);
		String topRightCornerNotificationPopup = page.innerText(topRightCornerNotification);
		logger.info(topRightCornerNotificationPopup);
		if (topRightCornerNotificationPopup.contains(AppConstants.USER_SAVED_MESSAGE)) {
			logger.info("New User saved Successfully");
		} else {
			logger.info("Getting error to add New User ");
		}
		softAssert.assertAll();
	}

	public void bulkCreateUser() throws InterruptedException {
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
		page.waitForTimeout(2000);
		softAssert.assertTrue(users.contains("Users"));
		softAssert.assertAll();
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

	public void loginwithNewUser(String roles, String dealer, String password, String newpassword)
			throws InterruptedException {
		updateUserPassword(roles, dealer, password);
		page.waitForTimeout(6000);
		Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		newBrowserPage.navigate("https://rc.truvideo.com/");
		logger.info("navigated to the url" + newBrowserPage.url());
		newBrowserPage.waitForTimeout(6000);
		LoginPage loginPage = new LoginPage(newBrowserPage);
		// HomePage homePage = new HomePage(newBrowserPage);
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
			logger.info("User is navigated on Login page and Pawword updated label is displayed");
		} else {
			logger.info("Password is not updated and User is unable to navigate on Login page");
		}
		loginPage.navigateToHomePage(userEmailID, newpassword);
		newBrowserPage.waitForTimeout(4000);
		logger.info("Login User is " + LoginPage.logInUsername);
		newBrowserPage.waitForTimeout(6000);
		newBrowserPage.close();
	}

	public void userStatus() throws InterruptedException {
		page.waitForTimeout(9000);
		page.fill(userSearchbox, "test");
		page.waitForTimeout(9000);
		logger.info("Searched with Latest user created");
		page.click(searchButton);
		page.waitForTimeout(9000);
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

		page.waitForTimeout(9000);
		page.waitForCondition(() -> page.isVisible(inActiveStatusButton));
		page.click(inActiveStatusButton);
		logger.info("User is clicked on InActive status button");
		page.waitForTimeout(3000);
		page.click(userActivateStatus);
		Thread.sleep(3000);
		logger.info("Again Successfully Activated user");
		page.waitForTimeout(2000);
		page.click(activeStatusbutton);
		logger.info("Again Clicked on Active status button and Verified user is present in Active filter");
		softAssert.assertAll();
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
}
