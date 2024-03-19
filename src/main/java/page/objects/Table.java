package page.objects;

import common.TestBase;
import utils.Locator;
import utils.Locator.*;

public class Table extends BaseWebElement {
    public Table(String elementName, Locator elementLocator) {
        super(elementName, elementLocator);
    }

    public void validateCellText(String textToFind) {
        var element = TestBase.getElement(this.locator);
        if (element == null) {
            TestBase.failTest(String.format("Table %s not found", this.name));
            return;
        }
        var cell = TestBase.getElement(new Locator(LocatorType.XPATH, String.format(".//td[text()='%s']", textToFind)));
        if (cell == null) {
            TestBase.failTest(String.format("Could not find cell with text contents '%s' in table %s", textToFind, this.name));
        }
        TestBase.logInfo(String.format("Found cell with text '%s' in table %s", textToFind, this.name));
    }
}
