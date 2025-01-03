package com.truvideo.testutils;

import com.microsoft.playwright.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.lang.reflect.Method;

import static com.truvideo.factory.PlaywrightFactory.*;
import static com.truvideo.testutils.JiraTestCaseUtils.attachJiraTestId;

public class Listeners extends TestUtils implements ITestListener, ISuiteListener {
    private static final Logger logger = LogManager.getLogger(Listeners.class);
    private static final ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();
    private static final ThreadLocal<String> threadLocalTracePath = new ThreadLocal<>();
    private static ExtentReports extent; // Static to persist across tests and suites

    @Override
    public void onStart(ISuite suite) {
        extent = TestUtils.getReporterObject();
        logger.info("Test suite '{}' execution started.", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
            logger.info("Test suite '{}' execution completed. Extent report flushed.", suite.getName());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test '{}' execution started.", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test '{}' execution completed.", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        try {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            String methodName = result.getMethod().getMethodName();
            String className = result.getMethod().getTestClass().getName();
            logger.info("Starting test: {}.{}", className, methodName);
            try {
                getBrowserContext().tracing().start(new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true));
                logger.info("Tracing started for: {}", methodName);
            } catch (Exception e) {
                logger.error("Failed to start tracing for {}: {}", methodName, e.getMessage());
            }
            ExtentTest test = extent.createTest(methodName);
            threadLocalTest.set(test);
            logger.info("ExtentTest created for: {}.{}", className, methodName);
            String tracePath = "./Reports/traces/" + methodName + ".zip";
            threadLocalTracePath.set(tracePath);
            logger.info("Trace path set for: {}", tracePath);
            attachJiraTestId(method, test);
        } catch (Exception e) {
            threadLocalTest.remove();
            threadLocalTracePath.remove();
            logger.error("Error during test setup for {}: {}", result.getMethod().getMethodName(), e.getMessage(), e);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            ExtentTest test = threadLocalTest.get();
            handleTestCompletion(test, result, true);
            logger.info("Test passed: {}", result.getMethod().getMethodName());
        } catch (Exception e) {
            logger.error("Error during onTestSuccess: {}", e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            ExtentTest test = threadLocalTest.get();
            handleTestCompletion(test, result, false);
            logger.info("Test failed: {}", result.getMethod().getMethodName());
        } catch (Exception e) {
            logger.error("Error during onTestFailure: {}", e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            ExtentTest test = threadLocalTest.get();
            String methodName = result.getMethod().getMethodName();
            if (test != null) {
                attachScreenshotToReport(test, methodName, getCurrentPage());
                test.skip(result.getThrowable());
            }
            logger.info("Test skipped: {}", methodName);
        } catch (Exception e) {
            logger.error("Error during onTestSkip: {}", e.getMessage());
        }
    }

    private void handleTestCompletion(ExtentTest test, ITestResult result, boolean isSuccess) {
        String methodName = result.getMethod().getMethodName();
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
            cleanupThreadLocals();
        }
    }

    private void cleanupThreadLocals() {
        threadLocalTest.remove();
        threadLocalTracePath.remove();
    }
}
