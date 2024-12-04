package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.LoginPage;


public class LoginPageTest extends BaseTest {


	@Test(priority = 1, description = "WA-5572, WA-5555")
	public void verifyAllElementsOfLoginPage() {
		Assert.assertTrue(loginpage.checkAllElements_LoginPage());

	}

	@Test(priority = 2)
	public void verifyIsCreateUserButtonWorking() {
		String actualSignUpPageTitle = loginpage.click_CreateAccount_Button();
		Assert.assertEquals(actualSignUpPageTitle, AppConstants.SIGN_UP_PAGE_TITLE);
		page.goBack();

	}

	@Test(priority = 3)
	public void verifyIsForgotPasswordButtonWorking() {
		String actualForgotPasswordPageTitle = loginpage.click_ForgotPassword_Button();
		Assert.assertEquals(actualForgotPasswordPageTitle, AppConstants.FORGOT_PASSWORD_PAGE_TITLE);
		page.goBack();
	}

//	@Test(priority = 4)
//	public void loginToApplicationUpdatedpass() {
//
//		String actualTitle = loginpage.loginToApplicationUpdatedpass(prop.getProperty("validUserEmail"),
//				prop.getProperty("NewPassword"));
//		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
//
//	}
	@DataProvider(name = "loginDataProvider")
	public Object[][] loginDataProvider() {
		return new Object[][] { { " ", " ", "Empty" }, // Empty field
				{ "XYZASSA@dg.com", "***********", "INVALID" }, // Invalid credentials
				{ prop.getProperty("username"), prop.getProperty("password") , "VALID" } // Valid credentials
		};
	}

	@Test(dataProvider = "loginDataProvider", priority = 5, description = "WA-5571")
	public void testLogin(String Username, String password, String loginType) {

		switch (loginType) {
		case "Empty":
			String actualError_WhenCredentialsNotEntered = loginpage.tryToLoginWithoutEnteringCredentials();
			Assert.assertTrue(actualError_WhenCredentialsNotEntered
					.contains(AppConstants.ERROR_MESSAGE_WITHOUT_ENTERING_lOGIN_CREDENTIALS));
			break;
		case "INVALID":
			String actualError = loginpage.loginWithInvalidCredentials(Username, password);
			Assert.assertTrue(actualError.contains(AppConstants.ERROR_MESSAGE_WHEN_ENTERING_WRONG_CREDENTIALS));
			break;

		case "VALID":
			String actualTitle = loginpage.loginToApplication(Username, password);
			Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
			break;

		default:
			throw new IllegalArgumentException("Invalid login type: " + loginType);
		}
	}

}
