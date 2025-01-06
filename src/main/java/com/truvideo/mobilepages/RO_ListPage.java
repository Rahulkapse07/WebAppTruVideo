package com.truvideo.mobilepages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class RO_ListPage {
	AppiumDriver driver;

	public RO_ListPage(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public WebElement getcreatedRO(String roNumber) {
		return driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+roNumber+"')]"));
	}
	

}
