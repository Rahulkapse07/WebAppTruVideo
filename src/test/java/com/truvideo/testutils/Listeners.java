package com.truvideo.testutils;

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

	@Override
	public void onTestStart(ITestResult result) {
		Object[] parameters = result.getParameters();
		String methodName = result.getMethod().getMethodName();
		String description = result.getMethod().getDescription(); // Retrieve the description from @Test annotation
		String caseType = parameters.length > 2 ? (String) parameters[2] : " ";

		String testDisplayName = methodName + "  " + caseType;

		test = extent.createTest(testDisplayName, description);

		if (description != null && !description.isEmpty()) {
			test.assignCategory(description);
		}

		test.assignCategory(caseType);

		try {
			List<String> tags = getTicketID(methodName);
			if (!tags.isEmpty()) {
				for (String tag : tags) {
					if (tag.contains("AT-")) {
						test.assignCategory(tag);
					} else {
						test.log(Status.INFO, tag);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.pass("Test Passed");
		 test.pass(result.getMethod().getMethodName() + " passed successfully");

		    Page page = null;
		    try {
		        page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
//		    if (page != null) {
//		        try {
//		            // Capture screenshot and get the file path
//		            String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
//		            // Add screenshot to the report using the file path
//		            test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
//		        } catch (IOException e) {
//		            test.fail("Failed to capture screenshot: " + e.getMessage());
//		        }
//		    } else {
//		        test.fail("Page object is null, unable to capture screenshot.");
//		    }
		    
		    if (page != null) {
		        try {
		            // Get the base64 screenshot
		            String base64Screenshot = getBase64Screenshot(page);
		            // Embed the screenshot in the report
		            test.addScreenCaptureFromBase64String(base64Screenshot, result.getMethod().getMethodName());
		        } catch (Exception e) {
		            test.fail("Failed to capture screenshot: " + e.getMessage());
		        }
		    } else {
		        test.fail("Page object is null, unable to capture screenshot.");
		    }
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		Page page = null;
		try {
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
		//sendReportToEmail();
	}

}
