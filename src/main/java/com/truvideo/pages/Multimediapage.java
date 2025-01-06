package com.truvideo.pages;

import java.util.ArrayList;
import java.util.List;

import javax.mail.Flags.Flag;

import org.apache.poi.hpsf.Array;
import org.testng.asserts.SoftAssert;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.truvideo.utility.JavaUtility;

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
	private String Hiddenlabel = ".orders-detail-video__details p:has-text('Hidden')";

	// roaster notification

	private String Roasternotify = ".tru-toast__right-container p.ng-tns-c2835246437-0 ";

	private String multioptions(String Option) {
		return ".cdk-overlay-pane div.mat-mdc-menu-content button:has-text('" + Option + "')";
	}

	public void verifyDownloadsingleimage() throws Exception {

		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();
		// Objects
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
				logger.info("Number of image  in RO " + ":" + rowCount);
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
						Locator hidelabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(hidelabel.isVisible(), true, "Hidden tag is not shown");
						String notify = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify, "This image is hidden from the customer",
								"Wrong toaster message here");

					} else if (iframe.locator(multioptions(" Show to customer ")).isVisible()) {

						iframe.locator(multioptions(" Show to customer ")).click();
						page.waitForTimeout(3000);
						Locator hidelabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(hidelabel.isVisible(), false, "Wrong label Shown");
						String notify = iframe.locator(Roasternotify).innerText();
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
					String Dowmloadnotify = iframe.locator(Roasternotify).innerText();
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
					String Sendtocustomer = iframe.locator(Roasternotify).innerText();
					Softassert.assertEquals(Sendtocustomer, "Message send to customer", "Not send to the customer");
					String status = iframe.locator(".order__column--navigation div p:has-text('RO Status - Sent')")
							.innerText();
					System.out.println(status);
					if (status.contains("Sent")) {
						logger.info("Status change into sent" + status);
					}
				}
			} else {
				logger.info("Three dots not  visible in screen");
			}
		} else {
			logger.info("All elements are not present");

		}

		// verify status of image sent

		// ------

		Softassert.assertAll();
	}

	private String imageThreedots = ".orders-detail-menu__media-videos span";
	private String Hide_show_button = ".cdk-overlay-pane button:nth-child(1) span";
	private String Images = "div.orders-detail-menu__media-gallery img";
	private String add_btn = ".orders-detail-menu__media-footer p:nth-child(1)";
	private String viewbtn = ".orders-detail-menu__media-footer p:nth-child(2)";

	private String Threedots = ".detail-media__menu-container button mat-icon";
	private String hidecustomer = ".mat-mdc-menu-content button:nth-child(1) span";
	private String Download = ".mat-mdc-menu-content button span:has-text('Download')";
	private String mediainsight = ".mat-mdc-menu-content button span:has-text('Media Insights')";

	private String addbutton = ".button-row > mat-icon:nth-child(1)";
	private String viewallbutton = ".button-row > mat-icon:nth-child(2)";

	private String View1 = ".button-row > mat-icon:nth-child(1)";
	private String View2 = ".button-row > mat-icon:nth-child(2)";

	private String Downloadbutton(int i) {
		return ".mat-mdc-menu-content button:nth-child(" + i + ")";
	}

	public void VerifyHideandshowMultipleimage() {
		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();

		page.waitForCondition(() -> page.locator(Searchheader).isVisible());
		if (page.locator(Searchheader).isVisible()) {
			page.locator(Searchheader).fill("4543381849");
			page.keyboard().press("Enter");
			page.waitForTimeout(4000);

			String Value = "4543381849";
			boolean roFound = false;
			while (!roFound) {
				Locator TableRow = page.locator(tableRows);
				int rowCount = TableRow.count();
				logger.info(rowCount);
				for (int i = 0; i < rowCount - 1; i++) {
					Locator roNumberList = TableRow.locator(Ronumber).nth(i);
					String roNumber = roNumberList.innerText().trim();

					if (roNumber.equals(Value)) {
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

			page.waitForTimeout(20000);

			Locator list = iframe.locator(imageThreedots);
			int count = list.count();
			System.out.println(count);
			for (int i = 1; i < count; i++) {

				iframe.locator(imageThreedots).nth(i).click();

				page.waitForTimeout(5000);
				if (iframe.locator(Hide_show_button).isVisible()) {
					String Label = iframe.locator(Hide_show_button).innerText();
					System.out.println(Label);
					if (Label.contains("Hide from customer")) {
						page.waitForTimeout(1000);
						iframe.locator(Hide_show_button).click();
						String notify = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify, "This image is hidden from the customer",
								"Wrong toaster message here");
						iframe.locator(Images).nth(i).click();
						Locator hidelabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(hidelabel.isVisible(), true, "Hidden tag is not shown");
						page.waitForCondition(() -> iframe.locator(crossbutton).isVisible());
						iframe.locator(crossbutton).click();
						page.waitForTimeout(2000);
						iframe.locator(imageThreedots).nth(i).click();
						page.waitForTimeout(5000);
						iframe.locator(Hide_show_button).click();
						page.waitForTimeout(3000);

						String notify2 = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify2, "This image is visible to the customer",
								"Wrong toaster message here");
						iframe.locator(Images).nth(i).click();
						page.waitForTimeout(3000);
						Locator Showlabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(Showlabel.isVisible(), false, "Wrong label Shown");
						iframe.locator(crossbutton).click();

					} else if (Label.contains("Show to customer")) {
						iframe.locator(Hide_show_button).click();
						String notify2 = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify2, "This image is visible to the customer",
								"Wrong toaster message here");
						iframe.locator(Images).nth(i).click();
						Locator Showlabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(Showlabel.isVisible(), false, "Wrong label Shown");
						page.waitForCondition(() -> iframe.locator(crossbutton).isVisible());
						iframe.locator(crossbutton).click();
						page.waitForTimeout(2000);
						iframe.locator(imageThreedots).nth(i).click();
						iframe.locator(Hide_show_button).click();
						String notify = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify, "This image is hidden from the customer",
								"Wrong toaster message here");
						iframe.locator(Images).nth(i).click();
						page.waitForTimeout(3000);
						Locator hidelabel = iframe.locator(Hiddenlabel);
						Softassert.assertEquals(hidelabel.isVisible(), true, "Hidden tag is not shown");
						page.waitForCondition(() -> iframe.locator(crossbutton).isVisible());
						iframe.locator(crossbutton).click();
					} else {
						logger.error("Incorrect name find");
					}
				} else {
					System.out.println("failed");
				}
			}
			page.waitForTimeout(4000);

			if (iframe.locator(add_btn).isVisible()) {
				iframe.locator(viewbtn).click();
				page.waitForTimeout(5000);
				if (iframe.locator(View1).isVisible() && iframe.locator(View2).isVisible()) {

					iframe.locator(View2).click();
					logger.info("Grid view");
					iframe.locator(View1).click();
					logger.info("normal view");
					iframe.locator(View2).click();
				}

				logger.info("clicked on download button");

				for (int i = 1; i <= 2; i++) {
					iframe.locator("button.mat-mdc-menu-trigger >span").click();
					page.waitForTimeout(5000);
					iframe.locator(Downloadbutton(i)).click();

					System.out.println("checked");
					page.waitForTimeout(5000);
				}
				iframe.locator(crossbutton).click();
				logger.info("Image download in both format");
			} else {
				System.out.println("failed");
			}

		}

	}

	private String Imagecheckbox = ".cdk-drag.image-list__card-item:nth-child(1) >div > mat-checkbox > div.mdc-form-field";
	private String Selectclosebtn = "div.footer-actions mat-icon:has-text('close')";
	private String Select_all = "span.mdc-button__label:has-text(' Select All ')";
	private String Download_Selectedimage = ".mdc-button__label  > span:has-text('Download Selected in')";

	public void verify_functionality_Selectall() throws Exception {

		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();

		if (iframe.locator(viewbtn).isVisible()) {
			iframe.locator(viewbtn).click();

			if (iframe.locator(Imagecheckbox).isVisible()) {

				iframe.locator(Imagecheckbox).click();
				System.out.println("Single image selected");
				page.waitForTimeout(1000);
				iframe.locator(Select_all).click();
				System.out.println("All images are selected");
				page.waitForTimeout(1000);
				iframe.locator(Selectclosebtn).click();
				System.out.println("Removed selected images");
				page.waitForTimeout(2000);
				iframe.locator(Imagecheckbox).click();
				for (int i = 1; i <= 2; i++) {

					iframe.locator(Download_Selectedimage).click();
					System.out.println("Download single image");

					page.waitForTimeout(2000);
					iframe.locator(Downloadbutton(i)).click();
					String notify2 = iframe.locator(Roasternotify).innerText();
					System.out.println(notify2);
					Softassert.assertEquals(notify2, "Your download has started. Please check your downloads folder");
					page.waitForTimeout(2000);

				}

			} else {
				throw new Exception("failed");
			}

			page.waitForTimeout(5000);
		} else {
			throw new Exception("View / ADD Element is missing from UI");
		}

	}

	private String Conversationinfo = ".chat-header__drop-down  button span:nth-child(3)";
	private String Conversationtext = "#header-info p";
	private String Mark_read_buttn = ".info-container__content div.info-container__content__actions";
	private String Mark_read = ".info-container__content div.info-container__content__actions span";
	private String Ownbadgecount = ".km-drop-container #my-service-message";

	public boolean verify_functionality_Mark_Unmark() {

		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();
		boolean flag = true;
		OrderListPage order = new OrderListPage(page);
		order.navigateToOrderDetails("New");
		logger.info("New Ro created");
		page.waitForCondition(() -> iframe.locator(Conversationinfo).isVisible());
		if (iframe.locator(Conversationinfo).isVisible()) {

			logger.info("Conversation Info. visible");
			iframe.locator(Conversationinfo).click();
			logger.info("converdastion info. clicked");
			page.waitForTimeout(10000);
			iframe.locator(Mark_read).click();

			if (iframe.locator(Mark_read_buttn).isVisible()) {
				logger.info("Mark as read element is avaiable on UI");
				String MarkAsRead = iframe.locator(Mark_read_buttn).innerText();

				System.out.println(MarkAsRead);
				iframe.locator(Mark_read).click();
				if (MarkAsRead.contains(" Mark as unread ")) {
					iframe.locator(Mark_read).click();
					page.waitForTimeout(3000);
					// ---initial count
					System.out.println(MarkAsRead);
					String initialCountStr = page.locator(Ownbadgecount).textContent().trim();
					int initialCount = Integer.parseInt(initialCountStr);
					// ---After mark as unread
					String increasedCount = page.locator(Ownbadgecount).textContent().trim();
					int finalCount = Integer.parseInt(increasedCount);

					if (finalCount == initialCount + 1) {
						System.out.println("Badge count increased as expected.");
					} else {
						System.out.println("Badge count did not increase as expected. Initial: " + initialCount
								+ ", After: " + increasedCount);
					}
					iframe.locator(Mark_read_buttn).click();

				} else if (MarkAsRead.contains("Mark as read")) {

					iframe.locator(Mark_read).click();
					// ---initial count
					System.out.println(MarkAsRead);
					String initialCountStr = page.locator(Ownbadgecount).textContent().trim();
					int initialCount = Integer.parseInt(initialCountStr);
					// ---After mark as unread
					String increasedCount = page.locator(Ownbadgecount).textContent().trim();
					int finalCount = Integer.parseInt(increasedCount);

					if (finalCount == initialCount + 1) {
						System.out.println("Badge count increased as expected.");
					} else {
						System.out.println("Badge count did not increase as expected. Initial: " + initialCount
								+ ", After: " + increasedCount);
					}
					iframe.locator(Mark_read_buttn).click();

				} else {
					logger.info("Element is not visible on UIdscsdcsdcdscsd");
					flag = false;
				}

			} else {
				logger.info("Element is not visible on UI");
				flag = false;
			}
		} else {
			logger.info("Element is not visible on UI");
			flag = false;
		}

		return flag;
	}

	private String videos = "#mat-tab-label-2-0 span.mdc-tab__text-label";
	private String images = "#mat-tab-label-2-1 span.mdc-tab__text-label";
	private String threedotforvideo = "button.mat-mdc-menu-trigger";
	private String videohidden = ".mat-mdc-menu-content button:nth-child(1)";
	private String videoshow = ".mat-mdc-menu-content button:nth-child(1)";
	private String videoMediaInsight = ".mat-mdc-menu-content button:nth-child(2)";
	private String videoSave = ".mat-mdc-menu-content button:nth-child(3)";
	private String videoEditinfo = ".mat-mdc-menu-content button:nth-child(4)";

	private String Hiddentag1 = ".orders-detail-video__hide-video-status > p";
	private String imageName = "div.orders-detail-video__title-body h3";

	// image

	private String threedotforimage = ".detail-media__menu-container button mat-icon";
	private String imagehidden = ".mat-mdc-menu-content button:nth-child(1)";
	private String imagedownload = ".mat-mdc-menu-content button:nth-child(2)";
	private String imagemediaInsight = ".mat-mdc-menu-content button:nth-child(3)";

	public boolean verify_View_all_functionalityforimage() {
		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		SoftAssert Softassert = new SoftAssert();
		// Objects
		page.waitForCondition(() -> page.locator(Searchheader).isVisible());
		if (page.locator(Searchheader).isVisible()) {
			page.locator(Searchheader).fill("96385276");
			page.keyboard().press("Enter");
			page.waitForTimeout(4000);
			String Value = "96385276";

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
				page.waitForCondition(() -> iframe.locator(imageThreedots).first().isVisible());

				Locator list = iframe.locator(imageThreedots);
				int count = list.count();
				System.out.println(count);
				if (count > 2) {
					iframe.locator(viewbtn).isVisible();
					logger.info("View button is visible , When we added " + count + " " + "images");

					if (!iframe.locator(".orders-detail-menu__media-gallery  mat-icon.mat-icon:has-text('videocam')")
							.first().isVisible()) {
						iframe.locator(add_btn).isVisible();
						iframe.locator(add_btn).click();
						for (int i = 1; count >= i; i++) {
							iframe.locator(
									".mat-grid-list.video-library__video-grid mat-grid-tile:nth-child(" + i + ")")
									.click();
							logger.info("Select multiple videos");

						}
						iframe.locator(".video-library__add-video-button > span:nth-child(2)").click();
						logger.info("Clicked on add Video button");
						iframe.locator(Roasternotify).waitFor();
						String notify2 = iframe.locator(Roasternotify).innerText();
						Softassert.assertEquals(notify2, "Added to RO successfully");
					}
					iframe.locator(viewbtn).click();
					// List<String> imagenames = Extractname();

					if (iframe.locator(videos).isVisible() && iframe.locator(images).isVisible()) {
						logger.info("Verify Hidden functionality for videos");
						iframe.locator(threedotforvideo).click();
						page.waitForTimeout(2000);
						if (iframe.locator(videohidden).isVisible() && iframe.locator(videoMediaInsight).isVisible()
								&& iframe.locator(videoSave).isVisible() && iframe.locator(videoEditinfo).isVisible()) {
							logger.info("All elements present after clicking Three dots");
							if (iframe.locator(videohidden).isVisible()) {
								String Label = iframe.locator(videohidden).innerText();
								System.out.println(Label);
								if (Label.contains("Hide from customer")) {
									page.waitForTimeout(1000);

									String imagename = iframe.locator(imageName).innerText();
									iframe.locator(videohidden).click();
									page.waitForTimeout(2500);
									Softassert.assertEquals(true, iframe.locator(Hiddentag1).isVisible(),
											"Hidden tag is not Available");
									for (int i = 1; i <= 3; i++) {
										String Imagename = iframe.locator(
												".cdk-drop-list.images-list__container.gallery-view >div:nth-child(" + i
														+ ") p.info__name")
												.innerText();
										String[] parts = Imagename.split(":");
										String interior = parts.length > 1 ? parts[1].trim() : "";
										System.out.println(interior);

										if (imagename.contains(interior)) {
											logger.info("Image matched" + " : " + imagename);
											if (iframe.locator(
													".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
															+ i + ") p.info__hidden")
													.isVisible()) {

												System.out.println("hidden button is visible");
											}

										}

									}

								} else if (Label.contains("Show to customer")) {
									page.waitForTimeout(2000);
									iframe.locator(videohidden).click();

									String imagename = iframe.locator(imageName).innerText();
									page.waitForTimeout(2500);
									Softassert.assertEquals(false, !iframe.locator(Hiddentag1).isVisible(),
											"Hidden tag is Available");
									for (int i = 1; i <= 3; i++) {
										String Imagename = iframe.locator(
												".cdk-drop-list.images-list__container.gallery-view >div:nth-child(" + i
														+ ") p.info__name")
												.innerText();
										String[] parts = Imagename.split(":");
										String interior = parts.length > 1 ? parts[1].trim() : "";
										System.out.println(interior);

										if (imagename.contains(interior)) {
											logger.info("Image matched" + " : " + imagename);
											if (iframe.locator(
													".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
															+ i + ") p.info__hidden")
													.isVisible()) {

												System.out.println("hidden button is visible");
											}

										}

									}

								}
							}
							page.waitForTimeout(count);
							iframe.locator(images).click();
							logger.info("Switched in Video to Image");
							page.waitForTimeout(5000);
							iframe.locator(threedotforimage).click();
							page.waitForTimeout(2000);
							if (iframe.locator(imagehidden).isVisible() && iframe.locator(imagedownload).isVisible()
									&& iframe.locator(imagemediaInsight).isVisible()) {
								logger.info("All elements present after clicking Three dots");
								if (iframe.locator(imagehidden).isVisible()) {
									String Label = iframe.locator(imagehidden).innerText();
									System.out.println(Label);
									if (Label.contains("Hide from customer")) {
										page.waitForTimeout(1000);

										String imagename = iframe.locator(imageName).innerText();
										iframe.locator(videohidden).click();
										page.waitForTimeout(2500);
										Softassert.assertEquals(true, iframe.locator(Hiddentag1).isVisible(),
												"Hidden tag is not Available");
										for (int i = 1; i <= 3; i++) {
											String Imagename = iframe.locator(
													".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
															+ i + ") p.info__name")
													.innerText();
											String[] parts = Imagename.split(":");
											String interior = parts.length > 1 ? parts[1].trim() : "";
											System.out.println(interior);

											if (imagename.contains(interior)) {
												logger.info("Image matched" + " : " + imagename);
												if (iframe.locator(
														".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
																+ i + ") p.info__hidden")
														.isVisible()) {

													System.out.println("hidden button is visible");
												}

											}

										}

									} else if (Label.contains("Show to customer")) {
										page.waitForTimeout(2000);
										iframe.locator(imagehidden).click();

										String imagename = iframe.locator(imageName).innerText();
										page.waitForTimeout(2500);
										Softassert.assertEquals(false, !iframe.locator(Hiddentag1).isVisible(),
												"Hidden tag is Available");
										for (int i = 1; i <= 3; i++) {
											String Imagename = iframe.locator(
													".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
															+ i + ") p.info__name")
													.innerText();
											String[] parts = Imagename.split(":");
											String interior = parts.length > 1 ? parts[1].trim() : "";
											System.out.println(interior);

											if (imagename.contains(interior)) {
												logger.info("Image matched" + " : " + imagename);
												if (iframe.locator(
														".cdk-drop-list.images-list__container.gallery-view >div:nth-child("
																+ i + ") p.info__hidden")
														.isVisible()) {

													System.out.println("hidden button is visible");
												}

											}

										}

									}

									if (iframe.locator(".mat-mdc-menu-trigger.btn-download-all span").isVisible()) {
										iframe.locator(".mat-mdc-menu-trigger.btn-download-all span").click();
										iframe.locator("span.mat-mdc-menu-item-text:has-text('JPG')").click();

									}
								}

							} else {

							}
						}
					}
				}
			}
		}

		return true;
	}

	private List<String> Extractname() {
		FrameLocator iframe = page.frameLocator(orderDetailsIFrame);
		List<String> extractedname = new ArrayList<>();
		List<String> imagenames = iframe.locator("div.info-container").allInnerTexts();
		for (String image : imagenames) {
			if (image != null && !image.trim().isEmpty()) {
				String[] parts = image.split(":");
				String interior = parts.length > 1 ? parts[1].trim() : "";
				extractedname.add(interior);
				System.out.println("Extracted value: " + interior);

			}
		}
		return extractedname;
	}
}
