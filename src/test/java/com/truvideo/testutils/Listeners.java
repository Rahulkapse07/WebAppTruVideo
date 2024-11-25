
package com.truvideo.testutils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

public class Listeners extends TestUtils implements ITestListener {
    Page page;
    ExtentTest test;
    ExtentReports extent = TestUtils.getReporterObject();
    BrowserContext context;
    
    @Override
    public void onTestStart(ITestResult result) {
        // Extracting test method name
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

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass(result.getMethod().getMethodName() + " passed successfully");

        String traceFilePath = "path/to/trace.zip"; // Replace with the actual path where trace file is saved
        test.info("Playwright Trace File: <a href='" + traceFilePath + "' target='_blank'>Download Trace</a>");

        // Add screenshot if available
        Page page = null;
        try {
            page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (page != null) {
            try {
                // Capture screenshot and get the file path
                String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
                // Add screenshot to the report using the file path
                test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
            } catch (IOException e) {
                test.fail("Failed to capture screenshot: " + e.getMessage());
            }
        } else {
            test.fail("Page object is null, unable to capture screenshot.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());

        // Add trace link if available
        String traceFilePath = "path/to/trace.zip"; // Replace with the actual path where trace file is saved
        test.info("Playwright Trace File: <a href='" + traceFilePath + "' target='_blank'>Download Trace</a>");

        // Add screenshot if available
        Page page = null;
        try {
            page = (Page) result.getTestClass().getRealClass().getField("page").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (page != null) {
            try {
                // Capture screenshot and get the file path
                String screenshotPath = getScreenShotPath(result.getMethod().getMethodName(), page);
                // Add screenshot to the report using the file path
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
        // Optionally handle test skip logic here
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optionally handle failed-but-within-success-percentage logic here
    }

    @Override
    public void onStart(ITestContext context) {
        // Optionally handle any logic before the test starts
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
