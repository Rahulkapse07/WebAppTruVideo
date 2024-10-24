package com.truvideo.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.truvideo.base.BaseTest;
import com.truvideo.pages.Multimediapage;

public class Multimediapagetest extends BaseTest {

	Multimediapage multimediapage;

	@BeforeClass
	public void NavigatetoMultimedia() {
		multimediapage = loginpage.navigateToHomePage(prop.getProperty("username"), prop.getProperty("password")).NavigateToOrderList();
}
	
	@Test
	public void  verifyDownloadsingleimage() throws Exception {
		multimediapage.verifyDownloadsingleimage("sdf");
	}

}
