package page.objects;

import common.TestBase;
import org.openqa.selenium.WebElement;
import utils.Locator;

/**
 * Base class for all page objects (all object classes inherit from BaseWebElement)
 */
public class BaseWebElement {
    public String name;
    public String value;
    public Locator locator;
    public WebElement element;

    /**
     * BaseWebElement constructor: constructs the object,
     * and check whether the element is located in the page.
     * Ends the test with a failure if the element cannot be located
     * @param elementName - basic name of the element (used in logging)
     * @param elementLocator - Locator strategy used for finding the element (see utils.Locator)
     */
    public BaseWebElement(String elementName, Locator elementLocator) {
        this.name = elementName;
        this.locator = elementLocator;
        this.element = TestBase.getElement(this.locator);
        if (this.element == null) {
            TestBase.failTest(String.format("%s '%s' not found", this.getClass().getSimpleName(), this.name));
        }
    }
}
