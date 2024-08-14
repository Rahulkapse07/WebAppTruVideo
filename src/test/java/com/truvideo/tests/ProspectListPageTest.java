 package com.truvideo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.ProspectListPage;

public class ProspectListPageTest extends BaseTest {

	ProspectListPage prospectListPage;

	@BeforeClass
	public void prospectListPageSetup() {
		prospectListPage=loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password"))
				.navigateToProspectList();
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
