package page.objects;

import common.TestBase;
import utils.Locator;

public class Button extends BaseWebElement {
    public Button(String elementName, Locator elementLocator) {
        super(elementName, elementLocator);
    }

    public void click() {
        var element = TestBase.getElement(this.locator);
        if (element == null) {
            TestBase.failTest(String.format("Button %s not found", this.name));
            return;
        }
        element.click();
        TestBase.logInfo(String.format("Button %s clicked", this.name));
    }

    public void clickAndWait() {
        click();
        TestBase.waitForPageLoaded();
    }
}
