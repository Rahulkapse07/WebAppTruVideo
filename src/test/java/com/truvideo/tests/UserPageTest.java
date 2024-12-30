package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.*;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.LoginPage;
import com.truvideo.pages.UserPage;

public class UserPageTest extends BaseTest {
	UserPage userPage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		userPage = new UserPage(getPage());
	}

	@DataProvider(name = "userData")
	public Object[][] userDataProvider() {
		return new Object[][] {
				{ "Service Dashboard", "Kenility Store", "loginwithNewUser", "Test123", "updatedpassword" },
				{ "Service App", "Kenility Store", "addNewTechnician", "", "" },
				{ "Sales App and Dashboard", "Kenility Store", "addNewSalesUser", "", "" },
				{ "Sales Manager", "Kenility Store", "addNewSalesManager", "", "" },
				{ "Administrator", "Kenility Store", "addNewAdminUser", "", "" },
				{ "Service Dashboard", "Kenility Store", "addNewAdvisor", "", "" },
				{ "Dealer admin", "Kenility Store", "addNewDealerAdmin", "", "" },
				{ "Service Dashboard", "Kenility Store", "verifyusersSearchFunctionality", "", "" } };
	}

	@Test(dataProvider = "userData", priority  = 1, 
			description  = "WA-5577, WA-5578, WA-5575, WA-5576, WA-5521, WA-5570, WA-5511")
	public void verifyUserCreation(String role, String store, String methodName, String password,
			String updatedPassword) throws InterruptedException {
		role = userPage.extractValue(role);
		store = userPage.extractValue(store);
		password = userPage.extractValue(password);
		updatedPassword = userPage.extractValue(updatedPassword);

		switch (methodName) {
		case "addNewAdvisor":
			userPage.addNewAdvisor(role, store);
			break;
		case "addNewTechnician":
			userPage.addNewTechnician(store, role);
			break;
		case "addNewSalesUser":
			userPage.addNewSalesUser(role, store);
			break;
		case "addNewSalesManager":
			userPage.addNewSalesManagerUser(role, store);
			break;
		case "addNewAdminUser":
			userPage.addNewAdminUser(role, store);
			break;
		case "loginwithNewUser":
			userPage.checkLoginWithEdit_Update_User(role, store, password, updatedPassword);
			break;
		case "addNewDealerAdmin":
			userPage.addNewDealerAdmin(role, store);
			break;
		case "verifyusersSearchFunctionality":
			userPage.searchUser(role, store);
			break;
		default:
			throw new IllegalArgumentException("Invalid method name: " + methodName);
		}
	}

	@Test(priority  = 2 ,description = "WA-5612")
	public void verifyUserStatus() throws InterruptedException {
		Assert.assertTrue(userPage.userStatus());
	}

	@Test(priority  = 3 ,description = "WA-5668")
	public void verifyBulkUserCreation() throws InterruptedException {
		Assert.assertTrue(userPage.bulkCreateUser());
	}

	@Test(priority  = 4 ,description = "WA-5512")
	public void verifyselectActionsonUser() throws InterruptedException {
		userPage.actionsOnUsers();
	}

	@Test(priority  = 5 ,description = "WA-5562")
	public void verifyElementsOnUserPage() throws InterruptedException {
		Assert.assertTrue(userPage.elementsonUserPage());
	}

	@Test(priority  = 6 ,description = "WA-5670")
	public void verifyusersfromSelectDealer() throws InterruptedException {
		Assert.assertTrue(userPage.getUsersFromSelectDealer());
	}

	@DataProvider(name = "userActionsProvider")
	public Object[][] userActionsProvider() {
		return new Object[][] { { "Send Invite to App", "Sent invitation to app", "Kenility Store  - App Invitation" },
				{ "Send Invite to Web Dashboard", "Sent invitation to dashboard.", "Password Creation" } };
	}

	@Test(dataProvider = "userActionsProvider",priority  = 7 ,description = "WA-5512")
	public void verifySelectActionsOnUser(String actionType, String expectedMessage, String message)
			throws InterruptedException {

		Assert.assertTrue(userPage.actionsOnUsers(actionType, expectedMessage, message));
	}

}
