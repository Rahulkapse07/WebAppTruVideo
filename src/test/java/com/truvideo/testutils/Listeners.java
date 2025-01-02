package com.truvideo.testutils;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.lang.reflect.Method;

import static com.truvideo.factory.PlaywrightFactory.getBrowserContext;
import static com.truvideo.factory.PlaywrightFactory.getPage;
import static com.truvideo.testutils.JiraTestCaseUtils.attachJiraTestId;

public class Listeners extends TestUtils implements ITestListener {
    public Logger logger = LogManager.getLogger(this.getClass().getName());
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    private ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        extent = TestUtils.getReporterObject();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestContext().getName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(methodName)
                .assignCategory(testName);
        threadLocalTest.set(test);
        logger.info("Test Execution started for test case: {}", methodName);

        // Start tracing for Playwright
        if (getBrowserContext() != null) {
            getBrowserContext().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
        }
        attachJiraTestId(result.getMethod().getConstructorOrMethod().getMethod(), test); // Attach Jira ID if applicable
        threadLocalPage.set(getPage());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        if (test != null) {
            test.pass("Test passed: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        handleTestCompletion(result, false);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        String methodName = result.getMethod().getMethodName();
        Page page = threadLocalPage.get();
        if (test != null) {
            attachScreenshotToReport(test, methodName, page);
            test.skip("Test skipped: " + result.getThrowable());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used in this implementation
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // Write everything to the report
        }
    }

    private void handleTestCompletion(ITestResult result, boolean isSuccess) {
        String methodName = result.getMethod().getMethodName();
        try {
            Page page = threadLocalPage.get();
            ExtentTest test = threadLocalTest.get();
            if (page != null && test != null) {
                attachScreenshotToReport(test, methodName, page);
                //attachTrace(test, methodName);
                Throwable throwable = result.getThrowable();
                if (!isSuccess) {
                        test.fail("Assertion Failed: " + throwable.getMessage());
                    } else {
                        test.fail("Test failed due to an unexpected exception: " + throwable.getClass().getSimpleName());
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadLocalPage.remove();
            threadLocalTest.remove();
        }
    }

    private void stopTracing() {
        try {
            if (getBrowserContext().tracing() != null) {
                getBrowserContext().tracing().stop();
            }
        } catch (Exception e) {
            System.err.println("Error stopping tracing: " + e.getMessage());
        }
    }
}
