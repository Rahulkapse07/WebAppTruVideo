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
import static com.truvideo.factory.PlaywrightFactory.*;
import static com.truvideo.testutils.JiraTestCaseUtils.attachJiraTestId;

public class Listeners extends TestUtils implements ITestListener {
    private static final Logger logger = LogManager.getLogger(Listeners.class);
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
            getBrowserContext().tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true));
            ExtentTest test = extent.createTest(methodName);
            threadLocalTest.set(test);
            logger.info("Test execution started for: {}", methodName);
            String tracePath = "./Reports/traces/" + methodName + ".zip";
            threadLocalTracePath.set(tracePath);
            attachJiraTestId(method, test);
        } catch (Exception e) {
            logger.error("Error during test setup: {}", e.getMessage(), e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        handleTestCompletion(result, true);
        logger.info("Test passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        handleTestCompletion(result, false);
        logger.info("Test failed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        String methodName = result.getMethod().getMethodName();
        if (test != null) {
            attachScreenshotToReport(test, methodName, getCurrentPage());
            test.skip(result.getThrowable());
        }
        logger.info("Test skipped: {}", methodName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used in this implementation
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
        try {
            if (test != null) {
                attachScreenshotToReport(test, methodName, getCurrentPage());
                attachTrace(test, methodName);
                if (!isSuccess) {
                    test.fail(result.getThrowable());
                } else {
                    test.pass(result.getThrowable());
                }
            }
        } catch (Exception e) {
            logger.info("Error during test completion for : {}", e.getMessage());
        } finally {
            //stopTracing(threadLocalTracePath.get());
            cleanupThreadLocals();
        }
    }

    private void cleanupThreadLocals() {
        threadLocalTest.remove();
        threadLocalTracePath.remove();
    }
}
