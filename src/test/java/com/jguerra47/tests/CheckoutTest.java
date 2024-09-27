package com.jguerra47.tests;

import com.jguerra47.base.BaseTest;
import com.jguerra47.pages.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void checkoutPricesMatchTest() {
        // Arrange
        int numberOfItems = 3;
        driver.get("https://www.saucedemo.com/");

        // Act
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials("standard_user", "secret_sauce");
        loginPage.login();

        InventoryPage inventoryPage = new InventoryPage(driver);
        List<String> shopPricesText = inventoryPage.getPrices(numberOfItems);
        inventoryPage.addProductsToCart(numberOfItems);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.goToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation("John", "Doe", "12345");

        List<String> checkoutPricesText = checkoutPage.getCheckoutPrices(numberOfItems);
        double itemTotalPrice = checkoutPage.getSubtotal();
        checkoutPage.finishPurchase();

        SuccessPage successPage = new SuccessPage(driver);
        String successMessage = successPage.getTitle();

        double sumOfShopPrices = shopPricesText.stream()
                .mapToDouble(price -> Double.parseDouble(price.replace("$", "")))
                .sum();

        // Assert
        assertEquals(shopPricesText, checkoutPricesText, "Prices on the checkout do not match the shop prices.");
        assertEquals("Checkout: Complete!", successMessage, "The success message is not as expected.");
        assertEquals(sumOfShopPrices, itemTotalPrice, "The subtotal does not match the sum of the product prices.");
    }
}
