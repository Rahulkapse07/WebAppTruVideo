package com.truvideo.pages;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.truvideo.factory.PlaywrightFactory;
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
	
	private String forgotupdatedpass = "#newPassword";
	private String forgotconfirmpass = "#confirmPassword";
	private String Gotologinpage = "#reset-password-div span a";
	private String savebtn = "button#save-user";
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

	public boolean validateEmailId_WithWrongAndCorrectEmailId() {
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

	public void  enterValidUsersEmailId_ClickOnResendPass(String Username,String NewPassword) throws IOException, MailosaurException, InterruptedException {
		page.fill(email_TextBox, prop.getProperty("validUserEmail"));
		logger.info("Entered valid user's Email id");
		page.click(resetPassword_Button);
		logger.info("Clicked on reset password button after entering valid user's email id");
		page.waitForTimeout(3000);
		if (page.isVisible(anEmailSent_TextMessage) && page.isVisible(backToLoginPage_LinkButton)) {
			logger.info("An email sent message & back to login page button is displayed");

			 String apiKey = "0I12RZR2fG2B7Mdh8prK3XnUl8VoG38j";
		        String serverId = "rwy0mofv";
		        String serverDomain = "rwy0mofv.mailosaur.net";

		        MailosaurClient mailosaur = new MailosaurClient(apiKey);

		        // Set up search parameters
		        MessageSearchParams params = new MessageSearchParams();
		        params.withServer(serverId);

		        // Specify search criteria (e.g., sent to a specific email)
		        SearchCriteria criteria = new SearchCriteria();
		        criteria.withSentTo("us@" + serverDomain);

		        // Set the receivedAfter time (e.g., search only within the last 15 minutes)
		        OffsetDateTime receivedAfter = OffsetDateTime.now().minusSeconds(30);
		        params.withReceivedAfter(receivedAfter.toInstant().toEpochMilli());

		        try {
		            // Set a longer timeout (e.g., 60 seconds) to wait for the email to arrive
		            int timeoutMillis = 60000;
		            Message message = mailosaur.messages().get(serverId, criteria, timeoutMillis);

		            if (message != null) {
		                // Extract details from the email
		                String subject = message.subject();
		                String fromEmail = message.from().get(0).email();
		                String toEmail = message.to().get(0).email();
		                String body = message.html().body();

		                // Print the extracted data
		                System.out.println("Subject: " + subject);
		                System.out.println("From: " + fromEmail);
		                System.out.println("To: " + toEmail);

		                // Remove HTML tags from the body
		                String plainBody = body.replaceAll("<[^>]+>", " ");

		                // Extract the reset link using regex
		                String resetLinkPattern = "(https://[\\w.-]+/[^\\s]+)";
		                Pattern pattern = Pattern.compile(resetLinkPattern);
		                Matcher matcher = pattern.matcher(plainBody);

		                if (matcher.find()) {
		                    String resetLink = matcher.group(1);
		                    System.out.println("Reset Link: " + resetLink);

		                    // Use Playwright to open the reset link
		                    try (Playwright playwright = Playwright.create()) {
		                      //  Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		                    	Page newBrowserPage = PlaywrightFactory.getBrowser().newContext().newPage();
		                		newBrowserPage.navigate(resetLink);
		                		logger.info("navigated to the url" + newBrowserPage.url());
		                    	
//		                        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		                        Page page = browser.newPage();
//		                        page.navigate(resetLink);

		                        // Simulate the password reset steps
		                		newBrowserPage.waitForTimeout(5000); // Wait for the page to load
		                		newBrowserPage.fill(forgotupdatedpass, NewPassword); // Replace with actual password element
		                		newBrowserPage.fill(forgotconfirmpass, NewPassword); // Replace with actual confirm password element
		                		newBrowserPage.click(savebtn); // Adjust to actual save button selector
		                		newBrowserPage.close();
		                        
		                        page.waitForTimeout(5000);
		                        page.click(Gotologinpage);
		                        
		                        LoginPage login = new LoginPage(page);
		                        login.loginToApplicationUpdatedpass(Username,NewPassword);

		                        System.out.println("Password reset completed.");
		                    }
		                } else {
		                    System.out.println("No reset link found in the email.");
		                }
		            } else {
		                System.out.println("No emails found.");
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		
			
	
		
		
		
	}

	public String clickOnBackToLoginPage_Button() {
		page.click(backToLoginPage_LinkButton);
		logger.info("Clicked on Back to Login button");
		return page.title();
	}
}
