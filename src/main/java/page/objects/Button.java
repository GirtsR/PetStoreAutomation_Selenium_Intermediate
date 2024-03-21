package page.objects;

import common.TestBase;
import utils.Locator;

/**
 * Class for clickable elements (<button≥, <a>, <input type="button">, <input type="checkbox"> etc.)
 * If naming is confusing: class can be renamed to Clickable
 */
public class Button extends BaseWebElement {
    public Button(String elementName, Locator elementLocator) {
        super(elementName, elementLocator);
    }

    public void click() {
        this.element.click();
        TestBase.logInfo(String.format("Button %s clicked", this.name));
    }

    public void clickAndWait() {
        click();
        TestBase.waitForPageLoaded();
    }

    public String getText() {
        return this.element.getText();
    }
}
