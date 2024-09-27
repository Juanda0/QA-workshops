package com.jguerra47.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jguerra47.base.BaseTest;
import com.jguerra47.pages.InventoryPage;
import com.jguerra47.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        // Arrange
        driver.get("https://www.saucedemo.com/");

        // Act
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials("standard_user", "secret_sauce");
        loginPage.login();

        InventoryPage inventoryPage = new InventoryPage(driver);
        String actualPageTitle = inventoryPage.getPageTitle();

        // Assert
        assertEquals("Products", actualPageTitle, "The page title after login should be 'Products'.");
    }
}
