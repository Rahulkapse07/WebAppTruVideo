package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.RepairOrderDetailPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.SavedVideoLibraryPage;

public class SavedVideoLibraryPageTest extends BaseTest{
	
	SavedVideoLibraryPage savedvideolibrarypage;
	RepairOrderDetailPage repairOrderPage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		repairOrderPage = new RepairOrderDetailPage(getPage());
		savedvideolibrarypage = new SavedVideoLibraryPage(getPage());
	}
	
	@Test(priority  = 1,description = "WA-5556")
	public void VerificationSavedvideolibrary() {
		Assert.assertTrue(savedvideolibrarypage.verificationSavedVideoLibrary());
	}
	@Test(priority  = 2,description = "WA-5611")
	public void VerifyAddVideo() {
		Assert.assertTrue(savedvideolibrarypage.addVideoFunctionality());
	}
	@Test(priority  = 3,description = "WA-5620")
	public void VerifyEditfunctionality() {
		Assert.assertTrue(savedvideolibrarypage.editFunctionality());
	}
	@Test(priority  = 4,description = "WA-5622")
	public void VerifyRemovefunctionality() {
		Assert.assertTrue(savedvideolibrarypage.removeFunctionality());
	} 
	@Test(priority  = 5,description = "WA-5613")
	public void VerifyFavoritefunctionality() {
		Assert.assertTrue(savedvideolibrarypage.favoriteFunctionality());
	}

}
