package com.truvideo.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.mailosaur.MailosaurException;
import com.truvideo.base.BaseTest;
import com.truvideo.constants.AppConstants;
import com.truvideo.pages.ForgotPasswordPage;

public class ForgotPasswordPageTest extends BaseTest {
	ForgotPasswordPage forgotPasswordPage;

	@BeforeClass
	public void forgotPasswordPageSetup() {
		forgotPasswordPage = loginpage.navigateToForgotPasswordPage();
	}

	@Test(priority = 1)
	public void validateAllAvailableElementsOnForgotPasswordPage()  {
		Assert.assertTrue(forgotPasswordPage.validateAllAvailableElements());
	}
	
	@Test(priority = 2)
	public void verifyValidationOfResetPass_WithoutEnteringEmail() {
		String actualError=forgotPasswordPage.clickOnResetButtonWithoutEnteringEmail();
		Assert.assertEquals(actualError, AppConstants.ERROR_MESSAGE_BLANK_EMAIL_FORGOTPASS);
	}
	
	@Test(priority = 3)
	public void verifyValidationOfCorrectIncorrectEmailIds()  {
		Assert.assertTrue(forgotPasswordPage.validateEmailId_WithWrongAndCorrectEmailId());
	}
	
	@Test(priority = 4)
	public void verifyUserAvailabilityValidation() {
		String actalError=forgotPasswordPage.enterEmailNotAssociateWithActiveUSer();
		Assert.assertEquals(actalError, AppConstants.ERROR_MESSAGE_USER_NOT_AVAILABLE_FOR_EMAIL);
	}
	
	@Test(priority = 5)
	public void verifySendPasswordRecoveryMail() throws IOException, MailosaurException, InterruptedException {
		forgotPasswordPage.enterValidUsersEmailId_ClickOnResendPass(prop.getProperty("validUserEmail"),prop.getProperty("NewPassword"));
	}
	
	@Test(priority = 6)
	public void verifyBackToLoginPageButtonIsWorking()  {
		String actualPageTitle=forgotPasswordPage.clickOnBackToLoginPage_Button();
		Assert.assertEquals(actualPageTitle, AppConstants.LOGINPAGE_TITLE);
	}
	
 
}
