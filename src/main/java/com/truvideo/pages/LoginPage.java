package com.truvideo.pages;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

public class LoginPage extends JavaUtility {
	private Page page;

	public LoginPage(Page page) {
		this.page = page;
	}

	private String username_Field = "input[name='j_username']";
	private String password_Field = "input[name='j_password']";
	private String logIn_Button = "input[value='Log In']";
	private String createAccount_ButtonLink = "#register-user";
	private String forgotPassword_ButtonLink = "#forgot-password"; // added alert word
	private String errorAlertMessage_Login = "div[class='alert alert-error']";
	private String close_Button_ErrorAlert = "a[class='close']";
	// private String close_Button_ErrorAlert = "b 2 a[class='close']";

	public static String logInUsername;
	public static String logInDealer;

	public boolean checkAllElements_LoginPage() {
		if (page.isVisible(username_Field) && page.isVisible(password_Field) && page.isVisible(logIn_Button)
				&& page.isVisible(createAccount_ButtonLink) && page.isVisible(forgotPassword_ButtonLink)) {
			logger.info("All elements are visible on Login Page");
			logger.info("Username/Password field is visible on Login Page");
			logger.info("LogIn button is visible on Login Page");
			logger.info("Create account link button is visible on Login Page");
			logger.info("Forgot password link button is visible on Login page");
			return true;
		} else {
			logger.info("Some elements are missing on Login Page");
			return false;
		}
	} 

	public String click_CreateAccount_Button() {
		navigateToSignUpPage();
		logger.info("Navigated to the Create User Page");
		return page.title();
	}

	public String click_ForgotPassword_Button() {
		navigateToForgotPasswordPage();
		logger.info("Navigated to the Forgot Password Page");
		return page.title();
	}

	public String tryToLoginWithoutEnteringCredentials() {
		page.fill(username_Field, "");
		page.fill(password_Field, "");
		page.click(logIn_Button);
		logger.info("Clicked on LogIn button without entering credentials");
		String errorMessage = page.textContent(errorAlertMessage_Login).trim();
		logger.info("Error message displayed for null credentials ");
		page.locator(close_Button_ErrorAlert).first().click();
		return errorMessage;
	}

	public String loginWithInvalidCredentials(String Username, String password) {
		page.fill(username_Field, Username);
		page.fill(password_Field, password);
		page.click(logIn_Button);
		logger.info("Clicked on LogIn button when entering invalid login credentials");
		String errorMessage = page.textContent(errorAlertMessage_Login);
		logger.info("Error message displayed for ivalid credentials ");
		page.locator(close_Button_ErrorAlert).first().click();
		return errorMessage;
	}

	public String loginToApplicationUpdatedpass(String username, String password) {

		navigateToHomePage(username, password);
		logger.info("Username / password entered and clicked on LogIn button");
		System.out.println("New Page title is : " + page.title());
		logger.info("navigated to the Home Page & Title is : " + page.title());
		return page.title();
	}

	public String loginToApplication(String username, String password) {
		page.reload();
		navigateToHomePage(username, password);
		logger.info("Username / password entered and clicked on LogIn button");
		System.out.println("New Page title is : " + page.title());
		logger.info("navigated to the Home Page & Title is : " + page.title());
		return page.title();
	}

	public HomePage navigateToHomePage(String username, String password) {
		//System.out.println(page.url());

		if (page.url().contains("https://rc.truvideo.com/login")) {

			page.fill(username_Field, username);
			logger.info("Entered User Name  " + username);
			page.fill(password_Field, password);
			logger.info("Entered Password    *********");
			page.click(logIn_Button);
			logger.info("Navigated to the Home Page");
			HomePage homePage = new HomePage(page);
			logInUsername = page.textContent(homePage.getLoginUserLabel());
			logInDealer = page.textContent(homePage.getLoginDealerLabel());
			// PlaywrightFactory.getBrowserContext().storageState(new
			// BrowserContext.StorageStateOptions()
			// .setPath(Paths.get("./src/main/resources/LoginStorageState/loginDetails.json")));

		}
			else if(page.url().contains("https://app.truvideo.com/login")) { 
		  page.fill(username_Field,prop.getProperty("Produsername")); 
		  logger.info("Entered User Name  " + prop.getProperty("Produsername")); 
		  page.fill(password_Field, prop.getProperty("Prodpassword")); //
		  logger.info("Entered Password    *********"); page.click(logIn_Button); 
		  logger.info("Navigated to the Home Page"); HomePage homePage = new
		  HomePage(page); logInUsername =
		  page.textContent(homePage.getLoginUserLabel()); logInDealer =
		  page.textContent(homePage.getLoginDealerLabel()); 
//		  PlaywrightFactory.getBrowserContext().storageState(new 
//		  BrowserContext.StorageStateOptions() // //
//		  .setPath(Paths.get("./src/main/resources/LoginStorageState/loginDetails.json"
//		  )));  
		  }
		  else {
			logger.info("Incorrect url showing");
		}
		return new HomePage(page);
	}

	public void navigateToUpdatePassword(Page newPage, String username, String password) {
		newPage.fill(username_Field, username);
		newPage.fill(password_Field, password);
		newPage.click(logIn_Button);
		logger.info("Navigated to the Update Password Screen");
	}

	public SignUpPage navigateToSignUpPage() {
		page.click(createAccount_ButtonLink);
		return new SignUpPage(page);
	}

	public ForgotPasswordPage navigateToForgotPasswordPage() {
		page.click(forgotPassword_ButtonLink);
		return new ForgotPasswordPage(page);
	}

}
