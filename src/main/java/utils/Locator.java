package utils;

/**
 * Class for defining element locator types and their values
 */
public class Locator {
    public LocatorType type;
    public String value;

    /**
     *
     * @param locatorType - type of the Locator that we use for finding the element (XPATH, ID, NAME)
     * @param locatorValue - value of the locator (e.g. "//td[input[@name='updateCartQuantities']]")
     */
    public Locator(LocatorType locatorType, String locatorValue) {
        this.type = locatorType;
        this.value = locatorValue;
    }

    /**
     * Additional LocatorTypes can also be added here: CLASS_NAME, CSS_SELECTOR etc.
     * If you implement a new LocatorType, don't forget to update TestBase.getElement
     * with the correct Selenium method By.
     */
    public enum LocatorType {
        XPATH,
        ID,
        NAME
    }
}
