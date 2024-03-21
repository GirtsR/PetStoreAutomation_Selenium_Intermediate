package pages;

import page.objects.Button;
import utils.Locator;
import utils.Locator.*;

/**
 * Generic class for the viewProduct page
 * Example: https://petstore.octoperf.com/actions/Catalog.action?viewProduct=&productId=K9-BD-01
 */
public class PetDetails {
    public Button getCartButtonByGender(boolean isMale) {
        String searchText;
        if (isMale) {
            searchText = "Male";
        } else {
            searchText = "Female";
        }
        return new Button("Add to cart button", new Locator(LocatorType.XPATH,
                String.format("//td[contains(text(), '%s')]/following-sibling::td[2]/a", searchText)));
    }

    public String getItemIDByGender(boolean isMale) {
        String searchText;
        if (isMale) {
            searchText = "Male";
        } else {
            searchText = "Female";
        }
        var button = new Button("Item ID", new Locator(LocatorType.XPATH,
                String.format("//td[contains(text(), '%s')]/preceding-sibling::td[2]/a", searchText)));
        return button.getText();
    }
}
