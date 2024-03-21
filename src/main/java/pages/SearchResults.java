package pages;

import page.objects.Table;
import utils.Locator;
import utils.Locator.*;

/**
 *  Search result page
 */
public class SearchResults {
    public Table resultsTable = new Table("Search results", new Locator(LocatorType.XPATH, "//table"));
}
