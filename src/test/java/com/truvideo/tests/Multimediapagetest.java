package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.Multimediapage;

public class Multimediapagetest extends BaseTest {

	Multimediapage multimediapage;

	@BeforeClass
	public void NavigatetoMultimedia() {
		multimediapage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password")).NavigateToOrderList();
}
	
	@Test()
	public void  verifyDownloadsingleimage() throws Exception {
		multimediapage.verifyDownloadsingleimage();
	}
	
	@Test()
	public void verifyDownloadMultipleimage() {
		
		multimediapage.VerifyHideandshowMultipleimage("d");
	}
	@Test(priority = 3 , dependsOnMethods = "verifyDownloadMultipleimage")
	public void verify_functionality_Selectall() throws Exception{
		
		multimediapage.verify_functionality_Selectall();
	}
	@Test
	public void  verify_functionality_Mark_Unmark() {
		Assert.assertTrue(multimediapage. verify_functionality_Mark_Unmark());   
	}

}
