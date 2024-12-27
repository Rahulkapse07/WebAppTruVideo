package com.truvideo.mobilepages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.truvideo.pages.OrderListPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class UserListPage {
	AppiumDriver driver;

	public UserListPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public static String userName;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
	private WebElement searchBar;

	public WebElement getUserSearchBar() {
		return searchBar;
	}

	public WebElement getUserToLogin(String searchUser) {
		WebElement search_User = driver
				.findElement(By.xpath("//android.view.View[contains(@content-desc,'" + searchUser + "')]"));
		return search_User;
	}

	@AndroidFindBy(accessibility = "0")
	@iOSXCUITFindBy(accessibility = "0")
	private WebElement zero;

	@AndroidFindBy(accessibility = "1")
	@iOSXCUITFindBy(accessibility = "1")
	private WebElement one;

	@AndroidFindBy(accessibility = "2")
	@iOSXCUITFindBy(accessibility = "2")
	private WebElement two;

	@AndroidFindBy(accessibility = "3")
	@iOSXCUITFindBy(accessibility = "3")
	private WebElement three;

	@AndroidFindBy(accessibility = "4")
	@iOSXCUITFindBy(accessibility = "4")
	private WebElement four;

	@AndroidFindBy(accessibility = "5")
	@iOSXCUITFindBy(accessibility = "5")
	private WebElement five;

	@AndroidFindBy(accessibility = "6")
	@iOSXCUITFindBy(accessibility = "6")
	private WebElement six;

	@AndroidFindBy(accessibility = "7")
	@iOSXCUITFindBy(accessibility = "7")
	private WebElement seven;

	@AndroidFindBy(accessibility = "8")
	@iOSXCUITFindBy(accessibility = "8")
	private WebElement eight;

	@AndroidFindBy(accessibility = "9")
	@iOSXCUITFindBy(accessibility = "9")
	private WebElement nine;

	@AndroidFindBy(accessibility = "NO")
	@iOSXCUITFindBy(accessibility = "NO")
	private WebElement biometric_no;

	@AndroidFindBy(accessibility = "YES")
	@iOSXCUITFindBy(accessibility = "YES")
	private WebElement yesButton;

	@AndroidFindBy(accessibility = "NO")
	@iOSXCUITFindBy(accessibility = "NO")
	private WebElement noButton;

	public WebElement getNoButton() {
		return noButton;
	}

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	@iOSXCUITFindBy(accessibility = "Allow")
	private WebElement notificationsAllow;

	public String login_verify_created_RO(String user) throws Exception {
		navigateTo_RO_Prospect_ListPage(user);
		RO_ListPage roListpage = new RO_ListPage(driver);
		Thread.sleep(3000);
		return roListpage.getcreatedRO(OrderListPage.newRoNumber).getAttribute("content-desc");
	}

	public RO_ListPage navigateTo_RO_Prospect_ListPage(String user) throws Exception {
		Thread.sleep(2000);
		searchUser(user);
		Thread.sleep(3000);
		one.click();
		two.click();
		three.click();
		four.click();
		five.click();
		six.click();
		try {
			if (biometric_no.isDisplayed()) {
				biometric_no.click();

			}
		} catch (NoSuchElementException e) {

		}
		try {
			if (notificationsAllow.isDisplayed()) {
				notificationsAllow.click();

			}
		} catch (NoSuchElementException e) {

		}
		return new RO_ListPage(driver);
	}

	public void searchUser(String user) throws Exception {
		Thread.sleep(1000);
		searchBar.click();
		searchBar.clear();
		searchBar.sendKeys(user);
		Thread.sleep(1000);
		userName = searchBar.getText();
		Thread.sleep(1000);
		getUserToLogin(userName).click();
	}

}
