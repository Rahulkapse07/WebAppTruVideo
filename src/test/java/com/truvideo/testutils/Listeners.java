
package com.truvideo.testutils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.truvideo.factory.PlaywrightFactory;

public class Listeners extends TestUtils implements ITestListener {
	Page page;
	ExtentTest test;
	ExtentReports extent = TestUtils.getReporterObject();
	BrowserContext context;

	@Override
	public void onTestStart(ITestResult result) {
		Object[] parameters = result.getParameters();
		String methodName = result.getMethod().getMethodName();
		String description = result.getMethod().getDescription();
		String caseType = parameters.length > 2 ? (String) parameters[2] : " ";

		String testDisplayName = methodName + " " + caseType;

		test = extent.createTest(testDisplayName);

		if (description != null && !description.isEmpty()) {
			String[] tags = description.split(",");
			for (String tag : tags) {
				tag = tag.trim();
				test.assignCategory(tag);
			}
		}
	}

//	@Override
//	public void onTestSuccess(ITestResult result) {
//		//test.pass(result.getMethod().getMethodName() + " passed successfully");
//
//		//String traceFilePath = "path/to/trace.zip"; // Replace with the actual path where trace file is saved
//		// test.info("Playwright Trace File: <a href='" + traceFilePath + "'
//		// target='_blank'>Download Trace</a>");
//
//		Page page = null;
//		try {
//			page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (page != null) {
//			try {
//				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
//				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
//			} catch (IOException e) {
//				test.fail("Failed to capture screenshot: " + e.getMessage());
//			}
//		} else {
//			test.fail("Page object is null, unable to capture screenshot.");
//		}
//	}
	private Page getPageFromResult(ITestResult result) {
		Page page = null;
		try {
			// Use reflection to get the "page" field from the test instance
			page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Page page = getPageFromResult(result);

		if (page != null) {
			try {
				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());

				if (page.video() != null) {
					Path videoPath = page.video().path();
					test.info("Video recorded: <a href='" + videoPath + "'>Download Video</a>");
//					String videoDir = System.getProperty("user.dir") + "/Videos/";
//					Path videoFolderPath = Paths.get(videoDir);
//
//					// Step 1: Delete any existing videos in the folder
//					deleteOldVideos(videoFolderPath);
				}
			} catch (IOException e) {
				test.fail("Failed to capture screenshot or video: " + e.getMessage());
			}
		} else {
			test.fail("Page object is null, unable to capture screenshot or video.");
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed: " + result.getMethod().getMethodName());
		test.fail(result.getThrowable());

		Page page = null;
		try {
			page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());

			if (page != null) {
//	            Path videoFolderPath = Paths.get("D:\\Automation RK Repo\\WebAppTruVideo\\Videos");//D:\Automation RK Repo\WebAppTruVideo\Videos
//	            String videoDir =   System.getProperty("user.dir") + "/Videos/";
//	            
//	            // Step 1: Delete any existing videos in the folder
//	            deleteOldVideos(videoFolderPath);

				String videoDir = System.getProperty("user.dir") + "/Videos/";
				Path videoFolderPath = Paths.get(videoDir);

				// Step 1: Delete any existing videos in the folder
//				deleteOldVideos(videoFolderPath);
//				// Step 2: Capture and save the new video
//				Path videoPath = page.video().path();
//				// Add video to the test report as a downloadable link
//				test.info("Video recorded: <a href='" + videoPath + "'>Download Video</a>");
			}
		} catch (Exception e) {
			System.out.println("Error retrieving video: " + e.getMessage());
			e.printStackTrace();
		}

		// Capture screenshot if the page object is available
		if (page != null) {
			try {
				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
			} catch (IOException e) {
				test.fail("Failed to capture screenshot: " + e.getMessage());
			}
		} else {
			test.fail("Page object is null, unable to capture screenshot.");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName() + " was skipped");

		Throwable skipReason = result.getThrowable();
		if (skipReason != null) {
			test.skip("Skip Reason: " + skipReason.getMessage());
		}

		Page page = null;
		try {
			page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (page != null) {
			try {
				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
			} catch (IOException e) {
				test.warning("Failed to capture screenshot for skipped test: " + e.getMessage());
			}
		} else {
			test.warning("Page object is null, unable to capture screenshot for skipped test.");
		}
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
