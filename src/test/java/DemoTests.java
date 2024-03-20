import common.TestBase;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResults;
import utils.TestNGTestListener;

@Listeners(TestNGTestListener.class)
public class DemoTests extends TestBase {
//    @DataProvider(parallel = true)
    @DataProvider
    public Object[][] getPetNames() {
        return new Object [][] {
                {"Iguana"},
                {"Angelfish"},
                {"Cat"}
        };
    }

    @Test(dataProvider = "getPetNames", description = "Search for a pet by its name and check that it can be found")
//    @Parameters({ "petName" })
    public void searchForPetByName(String petName) {
        getDriver().get(getConfig().getPetStoreUrl());
        waitForPageLoaded();

        var homePage = new HomePage();
        homePage.searchField.sendKeys(petName);
        homePage.searchButton.clickAndWait();

        var searchResults = new SearchResults();
        searchResults.resultsTable.validateCellText(petName);
    }

    @Test(description = "Skipped test example")
    public void secondTest() {
        throw new SkipException("Test skip reason");
    }
}
