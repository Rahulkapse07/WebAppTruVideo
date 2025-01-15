package com.truvideo.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.base.BaseTest;
import com.truvideo.pages.SoMultimediapage;

public class SoMultimediapagetest extends BaseTest {

	SoMultimediapage Somultimediapage;

	@BeforeMethod(dependsOnMethods = "initialize_Browser_With_Session")
	public void navigateToMultimediaPage_And_InitializeMultimediaPage() {
		getPage().navigate(prop.getProperty("somultimediaUrl"), new Page.NavigateOptions().setTimeout(100000));
		getPage().waitForLoadState(LoadState.DOMCONTENTLOADED);
		Somultimediapage = new SoMultimediapage(getPage());

	}
	
	@Test
	
	public void method1() {
		Somultimediapage.method1();
	}
	
	@Test
	
	public void method2() {
		
	}
	
	@Test
	
	public void method3() {
		
	}
	
	@Test
	
	public void method4() {
		
	}
	
	@Test
	
	public void method5() {
		
	}
	
	@Test
	
	public void method6() {
		
	}
	
	

}
