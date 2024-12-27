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
		multimediapage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.NavigateToOrderList();
	}

	@Test(priority = 1,description = "WA-5790 ,WA-5793 ")
	public void verifyDownloadsingleimage() throws Exception {
		multimediapage.verifyDownloadsingleimage();
	}

	@Test(priority = 2,description = "WA-5789 , WA-5794")
	public void verifyDownloadMultipleimage() {

		multimediapage.VerifyHideandshowMultipleimage();
	}

	@Test(priority = 3, dependsOnMethods = "verifyDownloadMultipleimage",description = "WA-5795 , WA-5796")
	public void verify_functionality_Selectall() throws Exception {

		multimediapage.verify_functionality_Selectall();
	}

	@Test(priority = 4,description = "")
	public void verify_functionality_Mark_Unmark() {
		Assert.assertTrue(multimediapage.verify_functionality_Mark_Unmark());
	}

	@Test(priority = 5,description = "WA-5404 , WA-5403")
	public void verify_View_all_functionality() {
		Assert.assertTrue(multimediapage.verify_View_all_functionalityforimage());
	}
}
