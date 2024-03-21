package pages;

import page.objects.Button;
import page.objects.TextBox;
import utils.Locator;
import utils.Locator.*;

/**
 * Pet store homepage: https://petstore.octoperf.com/actions/Catalog.action
 */
public class HomePage {
    /**
     * Standard implementation of locating elements in a page
     * Search for the element will occur immediately when the HomePage object is created in our test
     * Most commonly used for elements that are always present in the page/are not parameterized
     */
    public TextBox searchField = new TextBox("Search field", new Locator(LocatorType.NAME, "keyword"));

    public Button searchButton = new Button("Search button", new Locator(LocatorType.NAME, "searchProducts"));

    public Button dogsQuickLink = new Button("Dogs quick link", new Locator(LocatorType.XPATH,
            "//div[@id='QuickLinks']/a[contains(@href, 'DOGS')]"));

    /**
     * Will throw an error when the HomePage object is constructed
     */
    // public TextBox invalidTextBox = new TextBox("Invalid text field", new Locator(LocatorType.NAME, "invalid_text"));
}
