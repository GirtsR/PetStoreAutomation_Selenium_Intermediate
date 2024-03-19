package page.objects;

import utils.Locator;

public class BaseWebElement {
    public String name;
    public String value;
    public Locator locator;

    public BaseWebElement(String elementName, Locator elementLocator) {
        this.name = elementName;
        this.locator = elementLocator;
    }
}
