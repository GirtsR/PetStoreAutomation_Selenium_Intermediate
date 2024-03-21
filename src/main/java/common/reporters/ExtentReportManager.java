package common.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import common.ConfigReader;
import org.testng.ITestResult;

/**
 * Manager class for generating Extent reports
 * Hooked up to TestNGTestListener methods and TestBase.logInfo/failTest
 */
public class ExtentReportManager {
    private static ExtentReports extentReport;

    public static final String TEST_REPORT_PATH = ConfigReader.getProperties().getProperty("extent.report.path");

    private static final ThreadLocal<ExtentTest> currentTest = new ThreadLocal<>();

    public static void setCurrentTest(ExtentTest test) {
        currentTest.set(test);
    }

    public static ExtentTest getCurrentTest() {
        return currentTest.get();
    }

    public static void initializeReport() {
        extentReport = new ExtentReports();
        var sparkReporter = new ExtentSparkReporter(TEST_REPORT_PATH);
        extentReport.attachReporter(sparkReporter);
    }

    public static void flushReport() {
        extentReport.flush();
    }

    public static void startTest(String testName, String description) {
        setCurrentTest(extentReport.createTest(testName, description));
    }

    public static void setFailed(ITestResult testResult) {
        getCurrentTest().fail(testResult.getThrowable());
    }

    public static void setSkipped(ITestResult testResult) {
        getCurrentTest().skip(testResult.getThrowable());
    }

    public static void logMessage(String message) {
        logMessage(Status.INFO, message);
    }

    public static void logMessage(Status status, String message) {
        getCurrentTest().log(status, message);
    }

    public static void logLabel(String message) {
        logLabel(ExtentColor.GREEN, message);
    }

    public static void logLabel(ExtentColor color, String message) {
        getCurrentTest().info(MarkupHelper.createLabel(message, color));
    }
}
