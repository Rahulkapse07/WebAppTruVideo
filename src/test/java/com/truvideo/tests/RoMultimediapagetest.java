package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.MessageScreen_Prospect;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.Multimediapage;
import com.truvideo.testutils.AdditionalDescriptions;

public class RoMultimediapagetest extends BaseTest {

	Multimediapage multimediapage;


	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToMultimediaPage_And_InitializeMultimediaPage() {
		getPage().navigate(prop.getProperty("romultimediaUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		multimediapage = new Multimediapage(getPage());
	}


	@Test(priority = 1,description = "WA-5790")
	@AdditionalDescriptions({"WA-5793"})
	public void verifyDownloadsingleimage() throws Exception {
		multimediapage.verifyDownloadsingleimage();
	}

	@Test(priority = 2,description = "WA-5789")
	@AdditionalDescriptions({"WA-5794"})
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
