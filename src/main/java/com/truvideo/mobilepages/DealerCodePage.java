package com.truvideo.mobilepages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class DealerCodePage {
	AppiumDriver driver;

	public DealerCodePage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "OK")
	@AndroidFindBy(accessibility = "OK")
	private WebElement popUpOK;

	@iOSXCUITFindBy(accessibility = "1")
	@AndroidFindBy(accessibility = "1")
	private WebElement ONE;

	@iOSXCUITFindBy(accessibility = "2")
	@AndroidFindBy(accessibility = "2")
	private WebElement TWO;

	@iOSXCUITFindBy(accessibility = "3")
	@AndroidFindBy(accessibility = "3")
	private WebElement THREE;

	@iOSXCUITFindBy(accessibility = "4")
	@AndroidFindBy(accessibility = "4")
	private WebElement FOUR;

	@iOSXCUITFindBy(accessibility = "5")
	@AndroidFindBy(accessibility = "5")
	private WebElement FIVE;

	@iOSXCUITFindBy(accessibility = "6")
	@AndroidFindBy(accessibility = "6")
	private WebElement SIX;

	@iOSXCUITFindBy(accessibility = "7")
	@AndroidFindBy(accessibility = "7")
	private WebElement SEVEN;

	@iOSXCUITFindBy(accessibility = "8")
	@AndroidFindBy(accessibility = "8")
	private WebElement EIGHT;

	@iOSXCUITFindBy(accessibility = "9")
	@AndroidFindBy(accessibility = "9")
	private WebElement NINE;

	@iOSXCUITFindBy(accessibility = "0")
	@AndroidFindBy(accessibility = "0")
	private WebElement ZERO;

	@iOSXCUITFindBy(accessibility = "OK")
	@AndroidFindBy(accessibility = "OK")
	private WebElement oKButton;
	
	@AndroidFindBy(xpath  = "//android.widget.Button[@text='Allow']")
	private WebElement allow_Button;


	public void dealerLogin_ValidCredentials() throws Exception {
		navigateToUserListScreen_Order();
	}

	public UserListPage navigateToUserListScreen_Order() throws Exception {
		try {
			if (allow_Button.isDisplayed()) {
				Thread.sleep(2000);
				System.out.println("PopUp Displayed");
				allow_Button.click();
			}
		} catch (NoSuchElementException e) {
			System.out.println("PopUp not present");
		}
	   enterDealerCode_TruvideoDealer_Order();
		Thread.sleep(5000);
		return new UserListPage(driver);
	}

	
	public void enterDealerCode_TruvideoDealer_Order() throws InterruptedException {
		ZERO.click();
		SIX.click();
		FIVE.click();
		FOUR.click();
		SEVEN.click();
		SEVEN.click();
		Thread.sleep(1000);
	}
}
