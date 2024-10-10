package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.*;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.UserPage;

public class UserPageTest extends BaseTest {
	UserPage userPage;

	@BeforeClass
	public void init() {
		userPage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToUserspage();
	}

	@Test (priority = 2)
	public void verifyRequiredFieldinAddUser() throws InterruptedException {
		userPage.addNewAdvisor("Service Dashboard", "Kenility Store");
	}

	@Test(priority = 1)
	public void verifyNewTechnicianUser() throws InterruptedException {
		userPage.addNewTechnician("Kenility Store", "Service App");
	}
	@Test(priority = 3)
	public void verifyNewSalesUser() throws InterruptedException {
		userPage.addNewSalesUser("Sales App and Dashboard","Kenility Store");
	}
	@Test(priority = 4)
	public void verifyNewSalesManagerCreation() throws InterruptedException {
		userPage.addNewSalesManager("Sales Manager","Kenility Store");
	}
	@Test(priority = 5)
	public void verifyNewAdminUserCreation() throws InterruptedException {
		userPage.addNewAdminUser("Administrator","Kenility Store");
	}
	@Test(priority = 9)
	public void updatePasswordNewUser() throws InterruptedException {
		userPage.updateUserPassword("Service Dashboard", "Kenility Store", "Test123");
	}
	@Test(priority = 10)
	public void loginwithNewUser() throws InterruptedException {
		userPage.loginwithNewUser("Service Dashboard", "Kenility Store", "Test123", "updatedpassword");
	}

	@Test(priority = 7)
	public void verifyUserStatus() throws InterruptedException {
		userPage.userStatus();
	}
	@Test(priority = 6)
	public void verifyBulkUserCreation() throws InterruptedException {
		userPage.bulkCreateUser();
	}
	
	  @DataProvider(name = "userActionsProvider")
	    public Object[][] userActionsProvider() {
	        return new Object[][]{
	                {"Send Invite to App","Sent invitation to app" , "Kenility Store  - App Invitation" },
	                {"Send Invite to Web Dashboard","Sent invitation to dashboard." , "Password Creation"}
	        };
	    }
	
	  @Test(dataProvider = "userActionsProvider")
	    public void verifySelectActionsOnUser(String actionType, String expectedMessage, String message ) throws InterruptedException {
		  
		  Assert.assertTrue(userPage.actionsOnUsers(actionType , expectedMessage , message ));		
	        }

}
