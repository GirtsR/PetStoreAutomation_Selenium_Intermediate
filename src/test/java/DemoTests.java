import common.TestBase;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utils.TestNGTestListener;

@Listeners(TestNGTestListener.class)
public class DemoTests extends TestBase {

    /**
     * Commented out - Parallel execution of DataProvider (parameterized) tests
     */
    // @DataProvider(parallel = true)
    @DataProvider
    public Object[][] getPetNames() {
        return new Object[][]{
                {"Iguana"},
                {"Angelfish"},
                {"Cat"}
        };
    }

    /**
     * Using the dataProvider to run the same test with a different parameter
     */
    @Test(dataProvider = "getPetNames")
    /**
     * Commented out - Reading of parameters from the TestNG file, e.g.
     * <parameter name="petName" value="Iguana"/>
     */
    // @Parameters({ "petName" })
    public void searchForPetByName(String petName) {
        navigateToUrl(getConfig().getPetStoreUrl());

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

    @Test(description = "Purchase two male adult bulldoga and check the sub total price")
    public void purchaseAdultBulldogs() {
        var dogName = "Bulldog";
        var isMale = true;
        var dogQuantity = 2;
        var expectedTotalPrice = 37;

        navigateToUrl(getConfig().getPetStoreUrl());

        /**
         * Step 1: click on the 'Dogs' quick link
         */
        var homePage = new HomePage();
        homePage.dogsQuickLink.clickAndWait();

        /**
         * Step 2: click on the product ID based on the dog's name
         */
        var categoryPage = new Category();
        categoryPage.getProductIDButtonByPetName(dogName).clickAndWait();

        /**
         * Step 3: Add the male adult bulldog from the list of bulldogs to our cart
         */
        var petDetails = new PetDetails();
        var itemID = petDetails.getItemIDByGender(isMale);
        petDetails.getCartButtonByGender(isMale).clickAndWait();

        /**
         * Step 4: Update quantity to 2, click 'Update cart' button and check subtotal
         */
        var shoppingCart = new ShoppingCart();
        shoppingCart.setItemQuantityForItemID(itemID, dogQuantity);
        shoppingCart.updateCart.click();
        var actualSubTotal = shoppingCart.getSubTotal();

        Assert.assertEquals(actualSubTotal, expectedTotalPrice,
                "Subtotal does not match expected value");
    }
}
