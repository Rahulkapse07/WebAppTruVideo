package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class ForgotPasswordPage extends JavaUtility {
	private Page page;

	public ForgotPasswordPage(Page page) {
		this.page = page;
	}

	private String forgotPasswordHeading_Text = "text='Forgot Password'";
	private String email_Label = "label[for='email']";
	private String email_TextBox = "#email";
	private String resetPassword_Button = "#reset-password";
	private String error_AlertMessage = "div[class='alert alert-error']";
	private String emailNotOk_CrossMark = "#validate-email-not-ok";
	private String emailOk_CheckMark = "#validate-email-ok";
	private String anEmailSent_TextMessage = "span:has-text('An Email is sent to you')";
	private String backToLoginPage_LinkButton = "a:has-text('Back to Login Page.')";

	public boolean validateAllAvailableElements() {
		page.waitForLoadState();
		if (page.isVisible(forgotPasswordHeading_Text) && page.isVisible(email_Label) && page.isVisible(email_TextBox)
				&& page.isVisible(resetPassword_Button)) {
			logger.info("All Elements are available on Forgot Password Page ");
			logger.info("Page heading is displayed as : " + page.textContent(forgotPasswordHeading_Text));
			logger.info("Email Label is displayed as : " + page.textContent(email_Label).trim()
					+ " And Email text box is available");
			logger.info("Reset password button is displayed with text on it as : "
					+ page.textContent(resetPassword_Button));
			return true;
		} else {
			logger.info("Something went wrong while validating all available elements on Forgot Password page");
			return false;
		}
	}

	public String clickOnResetButtonWithoutEnteringEmail() {
		page.click(resetPassword_Button);
		logger.info("Clicked on reset password button without entering email id");
		return page.textContent(error_AlertMessage);
	}

	public boolean validateEmailId_WithWrongAndCorrectEmailId()  {
		boolean flag = false;
		page.fill(email_TextBox, "abcd");
		logger.info("wrongly formatted email entered");
		page.waitForSelector(emailNotOk_CrossMark);
		if (page.isVisible(emailNotOk_CrossMark)) {
			logger.info("Cross mark displayed for wrong email id");
			page.fill(email_TextBox, "test@gmail.com");
			logger.info("Email entered in correct format");
			page.waitForSelector(emailOk_CheckMark);
			flag = true;
			if (page.isVisible(emailOk_CheckMark)) {
				logger.info("Check mark is displayed for correct email id");
				flag = true;
			} else {
				logger.info("Check mark not displayd for correct email id");
				flag = false;
			}
		} else {
			logger.info("Cross mark not displayed for wrong email id");
			flag = false;
		}
		return flag;
	}

	public String enterEmailNotAssociateWithActiveUSer() {
		page.fill(email_TextBox, "usernotregister@gmail.com");
		logger.info("Entered Email which is not associated with any active user");
		page.click(resetPassword_Button);
		page.waitForTimeout(1000);
		logger.info("Clicked on reset pass button after entering Email which is not associated with any active user");
		return page.textContent(error_AlertMessage);
	}

	public boolean enterValidUsersEmailId_ClickOnResendPass(){
		page.fill(email_TextBox, prop.getProperty("validUserEmail"));
		logger.info("Entered valid user's Email id");
		page.click(resetPassword_Button);
		logger.info("Clicked on reset password button after entering valid user's email id");
		page.waitForTimeout(1000);
		if (page.isVisible(anEmailSent_TextMessage) && page.isVisible(backToLoginPage_LinkButton)) {
			logger.info("An email sent message & back to login page button is displayed");
			return true;
		} else {
			logger.info("Something went wrong while sending password recovery mail");
			return false;
		}
	}

	public String clickOnBackToLoginPage_Button() {
		page.click(backToLoginPage_LinkButton);
		logger.info("Clicked on Back to Login button");
		return page.title();
	}
}
