package com.truvideo.pages;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.utility.JavaUtility;

import io.reactivex.rxjava3.exceptions.Exceptions;

public class Multimediapage extends JavaUtility {

	private Page page;

	public Multimediapage(Page page) {
		this.page = page;
	}

	// Iframe
	private String orderDetailsIFrame = "iframe#order-details-iframe";

	// search-header
	private String Searchheader = "input#search-header";

	private String tableRows = "table#repair-order-results tr";
	private String nextButton = "li.ot3-nextPage a";
	private String Ronumber = "td.results-row:nth-child(4)";

	// multimedia page elements
	private String crossbutton = ".container-gallery span mat-icon";
	private String threedots = ".container-gallery button.mat-mdc-menu-trigger";
	private String datetime = ".orders-detail-video__info div.orders-detail-video__secondary-info-container p";
	private String imageinlarge = ".container-gallery  .detail-image__fullscreen-img mat-icon";
	private String imagename = ".container-gallery h3";
	private String sendtocustomerbtn = ".orders-detail-video__details.ng-star-inserted span";

	private String multioptions(String Option) {
		return ".cdk-overlay-pane div.mat-mdc-menu-content button:has-text('" + Option + "')";
	}

	public void verifyDownloadsingleimage(String format) throws Exception {

		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();
		// Objects
		HomePage homepage = new HomePage(page);
		RepairOrderDetailPage repairorder = new RepairOrderDetailPage(page);
		OrderListPage orderpage = new OrderListPage(page);

		page.waitForCondition(() -> page.locator(Searchheader).isVisible());
		if (page.locator(Searchheader).isVisible()) {
			page.locator(Searchheader).fill("49494984");
			page.keyboard().press("Enter");
			page.waitForTimeout(4000);
			String Value = "49494984";

			boolean roFound = false;
			while (!roFound) {
				Locator TableRow = page.locator(tableRows);
				int rowCount = TableRow.count();
				logger.info(rowCount);
				for (int i = 0; i < rowCount - 1; i++) {
					Locator roNumberList = TableRow.locator(Ronumber).nth(i);
					String roNumber = roNumberList.innerText().trim();

					if (roNumber.contains(Value)) {
						logger.info("The RO " + Value + " found in  list and RO Number is: " + roNumber);

						TableRow.locator(Ronumber).nth(i).click();
						page.waitForTimeout(4000);
						roFound = true;
						break;
					}
					logger.info("Checking for: " + Value + " And found :" + roNumber);
				}
				if (!roFound && page.isVisible(nextButton)) {
					logger.info("next button displayed ");
					page.click(nextButton);
					logger.info("The RO is not found on the current page, checking on the next page.");
					page.waitForLoadState(LoadState.DOMCONTENTLOADED);
					page.waitForTimeout(4000);
				} else if (!roFound) {
					logger.info("RO number not found and no more pages to check.");
					roFound = false;
					break;
				}
			}
			logger.info("Verify RO details");
			String Type = iframe.locator(".orders-detail-menu__media-gallery mat-icon:has-text('photo_camera')")
					.innerText();
			System.out.println(Type);
			if (Type.equals("photo_camera")) {
				iframe.locator(".orders-detail-menu__media-gallery mat-icon:has-text('photo_camera')").click();
				page.waitForTimeout(4000);
				logger.info("");
			} else {
				logger.error("No image is added in Ro");
			}
			page.waitForCondition(() -> iframe.locator(".container-gallery h2").isVisible());
			logger.info("Image  opened");

			if (iframe.locator(crossbutton).isVisible() && iframe.locator(imagename).isVisible()
					&& iframe.locator(imageinlarge).isVisible() && iframe.locator(datetime).isVisible()
					&& iframe.locator(sendtocustomerbtn).isVisible() && iframe.locator(threedots).isVisible()) {
				logger.info("All elements present in media page");

				// hidden to customer
				logger.info("Verify hidden functionality");
				if (iframe.locator(threedots).isVisible()) {
					iframe.locator(threedots).click();
					if (iframe.locator(multioptions(" Hide from customer ")).isVisible()) {

						iframe.locator(multioptions(" Hide from customer ")).click();
						page.waitForTimeout(3000);
						Locator hidelabel = iframe.locator(".orders-detail-video__details p:has-text('Hidden')");
						Softassert.assertEquals(hidelabel.isVisible(), true, "Hidden tag is not shown");
						String notify = iframe.locator(".tru-toast__right-container p.ng-tns-c2835246437-0 ")
								.innerText();
						Softassert.assertEquals(notify, "This image is hidden from the customer",
								"Wrong toaster message here");

					} else if (iframe.locator(multioptions(" Show to customer ")).isVisible()) {

						iframe.locator(multioptions(" Show to customer ")).click();
						page.waitForTimeout(3000);
						Locator hidelabel = iframe.locator(".orders-detail-video__details p:has-text('Hidden')");
						Softassert.assertEquals(hidelabel.isVisible(), false, "Wrong label Shown");
						String notify = iframe.locator(".tru-toast__right-container p.ng-tns-c2835246437-0 ")
								.innerText();
						Softassert.assertEquals(notify, "This image is visible to the customer",
								"Wrong toaster message here");
					} else {

						logger.info("wrong tags");
					}
				}
				page.waitForTimeout(2000);
				// download
				iframe.locator(threedots).click();
				logger.info("Verify download feature");
				page.waitForTimeout(2000);
				if (iframe.locator(multioptions("Download ")).isVisible()) {
					iframe.locator(multioptions("Download")).click();
					String Dowmloadnotify = iframe.locator(".tru-toast__right-container p.ng-tns-c2835246437-0")
							.innerText();
					Softassert.assertEquals(Dowmloadnotify,
							"Your download has started. Please check your downloads folder.", "Image not downloading");

				} else {
					throw new Exception("Downlaod button not available");
				}
				// Media Insights

				logger.info("verify insights");
				page.waitForTimeout(2000);
				iframe.locator(threedots).click();
				if (iframe.locator(multioptions("Media Insights")).isVisible()) {
					logger.info("media insights is available");
				} else {
					logger.info("Three dots not  visible in screen");
				}

				// send to customer
				logger.info("Verify send to customer");

				if (iframe.locator(multioptions("Send to customer")).isVisible()) {
					iframe.locator(multioptions("Send to customer")).click();
					logger.info("Image send to customer");

					iframe.locator(".selected-channel-actions button mat-icon:has-text('sms')");
					String Sendtocustomer = iframe.locator(".tru-toast__right-container p.ng-tns-c2835246437-0")
							.innerText();
					Softassert.assertEquals(Sendtocustomer, "Message send to customer", "Not send to the customer");
					String status = iframe.locator(".order__column--navigation div p:has-text('RO Status - Sent')").innerText();
					System.out.println(status);
					if(status.contains("Sent")){
						logger.info("Status change into sent" +  status);
					}
				}
			} else {
				logger.info("Three dots not  visible in screen");
			}
		} else {
			logger.info("All elements are not present");

		}

		Softassert.assertAll();
	}

	public void VerifyDownloadMultipleimage(String format) {

	}

	public void verify_functionality_Selectall() {

	}
}
