package page.objects;

import common.TestBase;
import page.objects.BaseWebElement;
import utils.Locator;

/**
 * Class for <input type="text">, <input type="email">, <input type="password"> and similar elements
 */
public class TextBox extends BaseWebElement {
    public TextBox(String elementName, Locator elementLocator) {
        super(elementName, elementLocator);
    }

    public void sendKeys() {
        sendKeys(this.value);
    }

    public void sendKeys(String inputText) {
        this.element.sendKeys(inputText);
        TestBase.logInfo(String.format("Entered text in text box %s: %s", this.name, inputText));
    }

    public void clearText() {
        this.element.clear();
        TestBase.logInfo(String.format("Text box %s contents cleared", this.name));
    }
}
