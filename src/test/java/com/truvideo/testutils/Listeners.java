package com.truvideo.testutils;

import java.io.IOException;
import java.util.List;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

public class Listeners extends TestUtils implements ITestListener {
	// AppiumDriver driver;
	Page page;
	ExtentTest test;
	ExtentReports extent = TestUtils.getReporterObject();

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName()); // Method Name
		test.assignCategory("All");
		try {
			//result.getMethod().getDescription();
			List<String> tags = getTicketID(result.getMethod().getMethodName());
			if (!tags.isEmpty()) {
				for (String tag : tags) {
					if (tag.contains("AT-"))
						test.assignCategory(tag);
					else
						test.log(Status.INFO, tag);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void onTestFailure(ITestResult result) {
//		test.fail(result.getThrowable());
//		try {
//			Object testInstance = result.getInstance();
//			page = (Page) testInstance.getClass().getField("page").get(testInstance);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//
//		if (page != null) {
//			try {
//				String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
//				test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	//Testing new report with screenshot on mail from RK
	public void onTestFailure(ITestResult result) {
	    test.fail(result.getThrowable());
	    Page page = null;
	    try {
	        // Assuming 'page' is the name of your Playwright Page object
	        page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }

	    if (page != null) {
	        String base64Screenshot = getBase64Screenshot(page);
	        test.addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());
	    } else {
	        test.fail("Page object is null, unable to capture screenshot.");
	    }
	}


	public void onTestSkipped(ITestResult result) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		extent.flush();
		sendReportToEmail();
	}

}
