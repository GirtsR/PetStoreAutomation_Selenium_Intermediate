package pages;

import page.objects.Button;
import page.objects.fields.TextBox;
import utils.Locator;
import utils.Locator.*;

public class HomePage {
    public TextBox searchField = new TextBox("Search field", new Locator(LocatorType.NAME, "keyword"));

    public Button searchButton = new Button("Search button", new Locator(LocatorType.NAME, "searchProducts"));
}
