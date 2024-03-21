package pages;

import common.TestBase;
import page.objects.Button;
import page.objects.TextBox;
import utils.Locator;
import utils.Locator.*;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Pet store shopping cart: https://petstore.octoperf.com/actions/Cart.action?viewCart=
 */
public class ShoppingCart {
    public void setItemQuantityForItemID(String itemID, int itemQuantity) {
        var quantityTextBox = new TextBox("Quantity text box", new Locator(LocatorType.NAME, itemID));
        quantityTextBox.clearText();
        quantityTextBox.sendKeys(Integer.toString(itemQuantity));
    }

    public Button updateCart = new Button("Update cart", new Locator(LocatorType.NAME, "updateCartQuantities"));

    public Double getSubTotal() {
        var element = TestBase.getElement(new Locator(LocatorType.XPATH, "//td[input[@name='updateCartQuantities']]"));
        var subTotalString = element.getText();
        subTotalString = subTotalString.replaceAll("[^0-9|^\\.]", "");

        try {
            return NumberFormat.getInstance().parse(subTotalString).doubleValue();
        } catch (ParseException e) {
            TestBase.failTest(e.toString());
            return null;
        }
    }
}
