package page.objects.fields;

import common.TestBase;
import page.objects.BaseWebElement;
import utils.Locator;

public class TextBox extends BaseWebElement {
    public TextBox(String elementName, Locator elementLocator) {
        super(elementName, elementLocator);
    }

    public void sendKeys() {
        sendKeys(this.value);
    }
    public void sendKeys(String inputText) {
        var element = TestBase.getElement(this.locator);
        if (element == null) {
            TestBase.failTest(String.format("Text box %s not found", this.name));
            return;
        }
        element.sendKeys(inputText);
        TestBase.logInfo(String.format("Entered text in text box %s: %s", this.name, inputText));
    }

    public void clearText() {
        var element = TestBase.getElement(this.locator);
        if (element == null) {
            TestBase.failTest(String.format("Text box %s not found", this.name));
            return;
        }
        element.clear();
        TestBase.logInfo(String.format("Text box %s contents cleared", this.name));
    }
}
