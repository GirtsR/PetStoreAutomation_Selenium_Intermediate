package common;

import com.aventstack.extentreports.Status;
import common.reporters.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import utils.Locator;

import java.time.Duration;

/**
 * Generic logic of the testing framework
 * 1) ConfigReader creation
 * 2) Initializing Selenium (Automatic Chromedriver installation using the WebDriverManager library
 * 3) Default command wait implementation (20 seconds)
 * 4) WebElement retrieval
 * 5) Waiting for page loaded using JavascriptExecutor and document.readyState
 * 6) Test failure handling
 * 7) Logging
 * 8) navigateToUrl helper
 */
public class TestBase {
    private static final Duration implicitWait = Duration.ofSeconds(20);

    // region ConfigReader methods
    private static ConfigReader configReader = new ConfigReader();
    public static ConfigReader getConfig() {
        return configReader;
    }
    // endregion

    // region RemoteWebDriver methods
    private static ThreadLocal<RemoteWebDriver> remoteDriver = new ThreadLocal<>();

    public static RemoteWebDriver getDriver() {
        return remoteDriver.get();
    }

    public static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
    }

    public static void initializeDriver() {
        remoteDriver.set(new ChromeDriver());
        wait.set(new WebDriverWait(getDriver(), implicitWait));
    }

    public static void closeDriver() {
        if (getDriver() != null) {
            getDriver().close();
            getDriver().quit();
        }
    }
    // endregion

    // region Wait implementation
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();
    private static WebDriverWait getWait() {
        return wait.get();
    }
    // endregion

    // region Element retrieval
    public static WebElement getElement(Locator locator) {
        WebElement returnElement = null;
        try {
            switch (locator.type) {
                case XPATH:
                    returnElement = getDriver().findElement(By.xpath(locator.value));
                    break;
                case NAME:
                    returnElement = getDriver().findElement(By.name(locator.value));
                    break;
                case ID:
                    returnElement = getDriver().findElement(By.id(locator.value));
                    break;
                default:
                    TestBase.failTest(String.format("Incorrect locator type: %s", locator.type.name()));
            }
        } catch (NoSuchElementException ignored) {
        }
        return returnElement;
    }
    // endregion

    // region Page loading
    public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            getWait().until(expectedCondition);
        } catch (Exception exception) {
            TestBase.failTest(String.format("Page loaded more than %d seconds", implicitWait.getSeconds()));
        }
    }
    // endregion

    // region Test failure handling
    public static void failTest(String message) {
        failTest(message, true);
    }
    public static void failTest(String message, boolean stopTest) {
        var failMessage = String.format("TEST FAILURE: %s", message);
        System.err.println(failMessage);
        Reporter.log(failMessage);
        Reporter.getCurrentTestResult().setStatus(ITestResult.FAILURE);
        ExtentReportManager.logMessage(Status.FAIL, failMessage);
        Allure.addAttachment("failTest", failMessage);
        if (stopTest) {
            throw new AssertionError(failMessage);
        }
    }
    // endregion

    // region Logging
    public static void logInfo(String message) {
        Reporter.log(message, true);
        ExtentReportManager.logMessage(message);
        Allure.attachment("logInfo", message);
    }
    // endregion

    public static void navigateToUrl(String url) {
        getDriver().get(url);
        waitForPageLoaded();
    }

}
