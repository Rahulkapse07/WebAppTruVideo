 package com.truvideo.tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.pages.ProspectDetailPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectListPage;

public class ProspectListPageTest extends BaseTest {

	ProspectListPage prospectListPage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToChatPage_And_InitializeChatPage() {
		getPage().navigate(prop.getProperty("chatPageUrl"),
				new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		prospectListPage = new ProspectListPage(getPage());
	}
	
	@Test(priority = 1)
	public void verifyAllAvailableElementsOnROListPage() {
		Assert.assertTrue(prospectListPage.checkAllAvailableElements_SOListPage());
	}
	
	@Test(priority = 2)
	public void verify_MyROs_Filter() {
		Assert.assertTrue(prospectListPage.clickOn_MySOs_Filter());
	}
	
	@Test(priority = 3)
	public void verify_AllOpen_Filter() {
		Assert.assertTrue(prospectListPage.clickOn_AllOpen_Filter());
	}
	
	@Test(priority = 4)
	public void verify_ForReview_Filter() {
		Assert.assertTrue(prospectListPage.clickOn_ForReview_Filter());
	}
	
	@Test(priority = 5)
	public void verify_AllClosed_Filter() {
		Assert.assertTrue(prospectListPage.clickOn_AllClosed_Filter());
	}
	@Test(priority = 6)
	public void verify_AllFieldsOn_AddSalesProspectScreen() {
		Assert.assertTrue(prospectListPage.checkAllAvailableElements_SOListPage());
	}
	
	@Test(priority = 7)
	public void verify_AddNewSalesProspect() {
		String newSO=prospectListPage.addNewSalesProspect();
		String firstSOinTable=prospectListPage.getFirstSOInList();
		Assert.assertEquals(newSO, firstSOinTable);
	}

}
