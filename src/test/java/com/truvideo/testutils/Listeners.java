package com.truvideo.testutils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
	    // Extract parameters passed to the test
	    Object[] parameters = result.getParameters();
	    String methodName = result.getMethod().getMethodName();

	    // Build a custom name for the test
	    if (parameters != null && parameters.length > 0 && parameters[0] instanceof Map) {
	        Map<String, String> userData = (Map<String, String>) parameters[0];
	        String dealer = userData.get("dealer");
	        String method = userData.get("methodName");
	        String role = userData.get("role");

	        // Create a dynamic test name based on the test parameters
	        String testName = "User Creation: " + method + " for " + role + " at " + dealer;
	        String methodName1=method;

	        // Create the test in Extent Reports with the dynamic name
	        test = extent.createTest(methodName1);

	        // Optionally assign categories
	        test.assignCategory("User Creation");
	        test.assignCategory(role);
	    } else {
	        // Fallback to method name if parameters are not available
	        test = extent.createTest(methodName);
	    }

	    test.assignCategory("All");

	    try {
	        // Handle tags if necessary
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

	//added for test until 41 by RK
	@Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

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
