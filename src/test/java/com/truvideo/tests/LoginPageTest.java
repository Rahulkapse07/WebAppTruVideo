package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;

public class LoginPageTest extends BaseTest {

	@Test(priority = 1)
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

	@Test(priority = 4)
	public void loginWithoutEnteringCredentials_showsErrorMessage() {
		String actualError_WhenCredentialsNotEntered = loginpage.tryToLoginWithoutEnteringCredentials();
		Assert.assertTrue(actualError_WhenCredentialsNotEntered
				.contains(AppConstants.ERROR_MESSAGE_WITHOUT_ENTERING_lOGIN_CREDENTIALS));
	}

	@Test(priority = 5)
	public void loginWithInvalidCredentials_showErrorMessage() {
		String actualError_WhenWrongCredentialsEntered = loginpage.loginWithInvalidCredentials();
		Assert.assertTrue(actualError_WhenWrongCredentialsEntered
				.contains(AppConstants.ERROR_MESSAGE_WHEN_ENTERING_WRONG_CREDENTIALS));
	}

	@Test(priority = 6)
	public void loginToApplication_ValidCredentials() {
		String actualTitle = loginpage.loginToApplication(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(actualTitle, AppConstants.HOME_PAGE_TITLE);
	}
}
