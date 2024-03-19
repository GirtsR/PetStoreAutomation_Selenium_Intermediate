import common.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResults;

public class DemoTests extends TestBase {
    @Test
    public void firstTest() {
        initializeDriver();
        getDriver().get(getConfig().getPetStoreUrl());
        waitForPageLoaded();

        var homePage = new HomePage();
        homePage.searchField.sendKeys("Angelfish");
        homePage.searchButton.clickAndWait();

        homePage.searchField.clearText();
        var petName = "Iguana";
        homePage.searchField.value = petName;
        homePage.searchField.sendKeys();
        homePage.searchButton.clickAndWait();

        var searchResults = new SearchResults();
        searchResults.resultsTable.validateCellText(petName);

        searchResults.resultsTable.validateCellText("Angelfish");

        closeDriver();
    }
}
