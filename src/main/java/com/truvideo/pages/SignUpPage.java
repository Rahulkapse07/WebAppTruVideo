package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;
import com.truvideo.constants.AppConstants;

public class SignUpPage extends JavaUtility {
	private Page page;

	public SignUpPage(Page page) {
		this.page = page;
	}

	private String signUpUser_Heading = "h4:has-text('Sign Up User')";
	private String dealerCode_LabelText = "label[for='dealerCode']";
	private String dealerCode_TextBox = "#dealerCode";
	private String verify_DealerCode_Button = "#verify-dealer-code";
	private String redError_AlertMessage = "div[class='alert alert-error']";
	private String alreadyHaveAnAccount_Text = "span:has-text('Already have an account?')";
	private String signIn_Navigation_Button = "a:has-text('Sign in')";
	private String notification_RightTopCorner = "div[class='notifications top-right']";
	private String email_TextBox = "#email";
	private String valid_Email_CheckMark = "#validate-email-ok";
	private String invalid_Email_CrossMark = "#validate-email-not-ok";
	private String sharedEmail_CheckBox = "#sharedEmail";
	private String checkUser_Button = "#check-username";
	private String back_Button = "#back-to-create";
	private String retrieve_Account_Button = "#retrieve-user";
	private String username_TextBox = "#username";
	private String validate_UserName_CheckMark = "#validate-username-ok";
	private String Invalidated_UserName_CrossMark = "#validate-username-not-ok";

	public boolean validateAllAvailableElements_SignUpPage() {
		if (page.isVisible(signUpUser_Heading) && page.isVisible(dealerCode_LabelText)
				&& page.isVisible(dealerCode_TextBox) && page.isVisible(verify_DealerCode_Button)
				&& page.isVisible(alreadyHaveAnAccount_Text) && page.isVisible(signIn_Navigation_Button)) {
			logger.info("All Required elemets are available on Sign Up page");
			logger.info("Sign Up User heading is available on Sign Up page");
			logger.info("Dealer Code label text is available on Sign Up page");
			logger.info("Verify Dealer Code button is available on Sign Up page");
			logger.info("Already have an account text is available on Sign Up page");
			logger.info("SignIn button is available on Sign Up page");
			return true;
		} else {
			logger.info("Something went wrong during validating all available Elements on Sign Up page");
			return false;
		}
	}

	public String clickOnSignInButton() {
		page.click(signIn_Navigation_Button);
		logger.info("Clicked on Already has account - Sign In Button");
		return page.title();
	}

	public String enterInvalidDealerCodeAndClickVerifyButton() {
		page.fill(dealerCode_TextBox, "123456");
		logger.info("Entered Invalid dealer code");
		page.click(verify_DealerCode_Button);
		logger.info("Clicked on verify dealer button after entering invaid dealer code");
		String errorMessage = page.textContent(redError_AlertMessage);
		return errorMessage;
	}

	public boolean enterValidDealerCodeAndClickVerifyButton() {
		page.fill(dealerCode_TextBox, prop.getProperty("dealercode_ForNewUserCreation"));
		logger.info("Entered valid dealer code");
		page.click(verify_DealerCode_Button);
		logger.info("Clicked on verify dealer button after entering valid dealer code");
		if (page.textContent(notification_RightTopCorner).contains(AppConstants.DEALERCODE_VALID_MESSAGE)
				&& page.isVisible(email_TextBox) && page.isVisible(sharedEmail_CheckBox)
				&& page.isVisible(checkUser_Button)) {
			logger.info("Valid dealer code green notification is displayed");
			logger.info("Email text box, Shared Email checkbox & Check User button are displayed");
			return true;
		} else {
			logger.info("Something went wrong during validating valid dealer code");
			return false;
		}
	}

	public String clickOnCheckUserButton_WithotEnteringEmailId() {
		page.click(checkUser_Button);
		logger.info("Clicked on check User without entering email Id");
		String errorMessage_BlankEmail = page.textContent(redError_AlertMessage);
		return errorMessage_BlankEmail;
	}
}
