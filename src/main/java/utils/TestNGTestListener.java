package utils;

import com.aventstack.extentreports.markuputils.ExtentColor;
import common.TestBase;
import common.reporters.ExtentReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGTestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        TestBase.initializeDriver();
        ExtentReportManager.startTest(result.getName(), result.getMethod().getDescription());
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
    }
}
