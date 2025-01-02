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
    private static final Logger logger = LogManager.getLogger(Listeners.class);
    private static final ThreadLocal<Page> threadLocalPage = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalTracePath = new ThreadLocal<>();
    private ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        extent = TestUtils.getReporterObject();
        logger.info("Test suite execution started.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        try {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            String methodName = result.getMethod().getMethodName();
            // Start tracing with Playwright
            getBrowserContext().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
            // Create ExtentTest instance
            ExtentTest test = extent.createTest(methodName);
            threadLocalTest.set(test);
            logger.info("Test execution started for: {}", methodName);
            // Set up trace file path
            String tracePath = "./Reports/traces/" + methodName + ".zip";
            threadLocalTracePath.set(tracePath);
            // Attach Jira Test ID
            attachJiraTestId(method, test);
            // Get Playwright Page instance
            Page page = getPage();
            threadLocalPage.set(page);
        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage(), e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        if (test != null) {
            test.pass("Test passed: " + result.getMethod().getMethodName());
        }
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        handleTestCompletion(result, false);
        logger.error("Test failed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        Page page = threadLocalPage.get();
        String methodName = result.getMethod().getMethodName();
        if (test != null) {
            attachScreenshotToReport(test, methodName, page);
            test.skip(result.getThrowable());
        }
        logger.info("Test skipped: {}", methodName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // This method is not used in the current implementation
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
            logger.info("Test suite execution completed.");
        }
        cleanupThreadLocals();
    }

    private void handleTestCompletion(ITestResult result, boolean isSuccess) {
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = threadLocalTest.get();
        Page page = threadLocalPage.get();
        try {
            if (test != null && page != null) {
                attachScreenshotToReport(test, methodName, page);
                attachTrace(test, methodName);
                if (!isSuccess) {
                    test.fail(result.getThrowable());
                }
            }
        } catch (Exception e) {
            logger.info("Error during test completion for {}: {}", methodName, e.getMessage(), e);
        } finally {
            stopTracing();
            cleanupThreadLocals();
        }
    }

    private void stopTracing() {
        try {
            if (getBrowserContext().tracing() != null) {
                getBrowserContext().tracing().stop();
            }
        } catch (Exception e) {
            System.out.println("Error stopping tracing: " + e.getMessage());
        }
    }

    private void cleanupThreadLocals() {
        threadLocalPage.remove();
        threadLocalTest.remove();
        threadLocalTracePath.remove();
    }
}
