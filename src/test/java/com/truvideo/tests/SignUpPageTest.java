package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
	SignUpPage signUpPage;

//	@BeforeClass
//	public void signUpPageSetup() {
//		signUpPage = loginpage.navigateToSignUpPage();
//	}
	
	@Test(priority = 1)
	public void verifyAllElementsAreAvailable_SignUpPage() {
		Assert.assertTrue(signUpPage.validateAllAvailableElements_SignUpPage());
	}
	
	@Test(priority = 2)
	public void verifyAlreadyHaveAccount_SignInButtonWorking() {
		String actualLoginPageTitle=signUpPage.clickOnSignInButton();
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGINPAGE_TITLE);
		page.goBack();
	}
	
	@Test(priority = 3)
	public void verifyValidationOnEnteringInvalidDealerCode() {
		String actualErrorMessage=signUpPage.enterInvalidDealerCodeAndClickVerifyButton();
		Assert.assertEquals(actualErrorMessage, AppConstants.DEALERCODE_INVALID_MESSAGE);
	}
	
	@Test(priority = 4)
	public void verifyValidationOnEnteringValidDealerCode() {
		Assert.assertTrue(signUpPage.enterValidDealerCodeAndClickVerifyButton());
	}
	
	@Test(priority = 5)
	public void verifyValidationWhenClickedOnCheckUserButton_WithotEnteringEmailId() {
		String actualErrorMessage=signUpPage.clickOnCheckUserButton_WithotEnteringEmailId();
		Assert.assertEquals(actualErrorMessage, AppConstants.ERROR_MESSAGE_BLANK_EMAIL);
	}
	
	
}
