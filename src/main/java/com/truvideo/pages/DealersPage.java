package com.truvideo.pages;

import java.util.List;

import com.microsoft.playwright.Page;
import com.truvideo.utility.JavaUtility;

import net.bytebuddy.implementation.bytecode.Throw;

public class DealersPage extends JavaUtility {
	private Page page;

	public DealersPage(Page page) {
		this.page = page;
	}

	private String Dealerlistedit(int num) {
		return "#dealer-results tbody tr:nth-child(" + num + ") td:nth-child(4) i:nth-child(1)";
	}

	private String DealerSettingSAVEbtn = "button#page-title-save";
	private String DealerName = "#dealer-search div:nth-child(1) #name";
	private String DealerSearchBtn = "input.btn.btn-primary.btn-block";
	private String DealerList = "#dealer-results tbody tr td:nth-child(1)";
	private String EnableWhatsappcheckboxbtn = "input#enableWhatsAppConversation";

	private String Verify_Dearler_internal_listClick(String Value) {
		return "div#floating-sidenav a:has-text('" + Value + "')";
	}

	public boolean Verify_Whatsapp_textconversation(String Name , String value ) {

		page.waitForCondition(() -> page.locator(DealerName).isVisible());
		page.fill(DealerName, Name);
		page.locator(DealerSearchBtn).click();
		page.waitForTimeout(4000);
		System.out.println("click1");

		List<String> Dealerlist = page.locator(DealerList).allInnerTexts();

		logger.info(DealerList);
		String name = "TruVideo";
		int n = 0;
		int i = 2;
		while (n <= DealerList.length()) {
			for (String list : Dealerlist) {
				if (!name.contains(list)) {
					++i;
					n++;
				} else if (name.contains(list)) {
					page.locator(Dealerlistedit(i + 0)).click();
					page.waitForTimeout(5000);
					break;
				} else {
					logger.info("NO DEALER FOUND");
					return false;
				}
			}
			break;
		}
		page.waitForTimeout(2000);
		page.locator(Verify_Dearler_internal_listClick(value)).click();

		if (!page.locator(EnableWhatsappcheckboxbtn).isChecked()) {
			logger.info("ENABLE WHATSAPP CHAT");
			page.locator(EnableWhatsappcheckboxbtn).click();
			page.locator(DealerSettingSAVEbtn).click();
		} else if (page.locator(EnableWhatsappcheckboxbtn).isChecked()) {
			logger.info("ALREADY ENABLE");
			return false;
		}

		return true;
	}
}
