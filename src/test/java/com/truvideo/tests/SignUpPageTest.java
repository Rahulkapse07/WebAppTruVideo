package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.RepairOrderDetailPage;
import com.truvideo.pages.SavedVideoLibraryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.SignUpPage;

public class SignUpPageTest extends BaseTest {
	SignUpPage signUpPage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("signUpPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		signUpPage = new SignUpPage(getPage());
	}
	
	@Test(priority = 1)
	public void verifyAllElementsAreAvailable_SignUpPage() {
		Assert.assertTrue(signUpPage.validateAllAvailableElements_SignUpPage());
	}
	
	@Test(priority = 2)
	public void verifyAlreadyHaveAccount_SignInButtonWorking() {
		String actualLoginPageTitle=signUpPage.clickOnSignInButton();
		Assert.assertEquals(actualLoginPageTitle, AppConstants.LOGINPAGE_TITLE);
		getPage().goBack();
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
