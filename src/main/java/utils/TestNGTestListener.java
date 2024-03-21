package utils;

import com.aventstack.extentreports.markuputils.ExtentColor;
import common.TestBase;
import common.reporters.ExtentReportManager;
import common.reporters.SlackReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

/**
 * Listens to events emitted by TestNG framework ITestListener interface
 * Handles creation of reports, initializing and closing browser sessions,
 * sending Slack report after test finish
 */
public class TestNGTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        TestBase.initializeDriver();
        var testName = result.getName();
        if (result.getParameters().length != 0) {
            // Append test parameters to the test name in the Extent report
            testName +=  Arrays.toString(result.getParameters());
        }
        ExtentReportManager.startTest(testName, result.getMethod().getDescription());
        ExtentReportManager.logLabel("Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TestBase.closeDriver();
        ExtentReportManager.logLabel("Test finished successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TestBase.closeDriver();
        ExtentReportManager.setFailed(result);
        ExtentReportManager.logLabel(ExtentColor.RED, "Test failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TestBase.closeDriver();
        ExtentReportManager.setSkipped(result);
        ExtentReportManager.logLabel(ExtentColor.GREY, "Test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        TestBase.closeDriver();
        ExtentReportManager.logLabel(ExtentColor.YELLOW, "Test failed, but within success percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        TestBase.closeDriver();
        ExtentReportManager.setFailed(result);
        ExtentReportManager.logLabel(ExtentColor.RED, "Test failed with timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        TestBase.setupChromeDriver();
        ExtentReportManager.initializeReport();
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
        var slackMessage = String.format("Test results are ready! Passed: %d, failed: %d, skipped: %d",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size()
        );
        SlackReporter.sendTestReport(slackMessage, ExtentReportManager.TEST_REPORT_PATH);
    }
}
