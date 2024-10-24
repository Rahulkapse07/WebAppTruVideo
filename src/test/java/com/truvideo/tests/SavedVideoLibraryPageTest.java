package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.SavedVideoLibraryPage;

public class SavedVideoLibraryPageTest extends BaseTest{
	
	SavedVideoLibraryPage savedvideolibrarypage;
	@BeforeClass
	public void homePageSetup() throws InterruptedException {
		savedvideolibrarypage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToSavedVideoLibrary();	
	}
	
	@Test(priority  = 1)
	public void VerificationSavedvideolibrary() {
		Assert.assertTrue(savedvideolibrarypage.verificationSavedVideoLibrary());
	}
	@Test(priority  = 2)
	public void VerifyAddVideo() {
		Assert.assertTrue(savedvideolibrarypage.addVideoFunctionality());
	}
	@Test(priority  = 3)
	public void VerifyEditfunctionality() {
		Assert.assertTrue(savedvideolibrarypage.editFunctionality());
	}
	@Test(priority  = 4)
	public void VerifyRemovefunctionality() {
		Assert.assertTrue(savedvideolibrarypage.removeFunctionality());
	} 
	@Test(priority  = 5)
	public void VerifyFavoritefunctionality() {
		Assert.assertTrue(savedvideolibrarypage.favoriteFunctionality());
	}

}
