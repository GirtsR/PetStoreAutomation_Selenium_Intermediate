package pages;

import page.objects.Button;
import utils.Locator;
import utils.Locator.*;

/**
 * Generic class for the store viewCategory page
 * Example: https://petstore.octoperf.com/actions/Catalog.action?viewCategory=&categoryId=DOGS
 */
public class Category {
    /**
     * Method implementation of object search in a page.
     * Allows to provide custom selectors based on test parameters
     * @param petName - name of the Pet whose product ID anchor element we want to her
     * @return Button class object
     */
    public Button getProductIDButtonByPetName(String petName) {
        return new Button(String.format("%s product ID", petName), new Locator(LocatorType.XPATH,
                String.format("//td[text()='%s']/preceding-sibling::td/a", petName)));
    }
}
