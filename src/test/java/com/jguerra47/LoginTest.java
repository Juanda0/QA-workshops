package com.jguerra47;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {
    WebDriver driver;

    @BeforeEach
    public void init() {
        ChromeOptions options = new ChromeOptions().setBinary("/Applications/Brave Browser.app/Contents/MacOS/Brave Browser");
        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }

    @Test
    public void loginTest() {
        // Arrange
        driver.get("https://www.saucedemo.com/");

        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));

        // Act
        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();

        WebElement pageTitle = driver.findElement(By.xpath("//span[@data-test='title']"));

        // Assert
        assertEquals("Products", pageTitle.getText());
    }

    @Test
    public void checkoutPricesMatchTest() {
        // Arrange
        int numberOfItems = 3;
        driver.get("https://www.saucedemo.com/");

        WebElement inputUser = driver.findElement(By.id("user-name"));
        WebElement inputPassword = driver.findElement(By.id("password"));
        WebElement buttonLogin = driver.findElement(By.id("login-button"));
        inputUser.sendKeys("standard_user");
        inputPassword.sendKeys("secret_sauce");
        buttonLogin.click();

        WebElement cartButton = driver.findElement(By.xpath("//div[@data-test=\"primary-header\"]/div[@class=\"shopping_cart_container\"]/a"));
        List<WebElement> addButtons = driver.findElements(By.xpath( "(//div[@data-test=\"inventory-item-description\"])/div[@class=\"pricebar\"]/button"));
        List<WebElement> pricesShop = driver.findElements(By.xpath("(//div[@data-test=\"inventory-item-description\"])/div[@class=\"pricebar\"]/div[@data-test=\"inventory-item-price\"]"));
        List<String> shopPricesText = pricesShop.stream().limit(numberOfItems).map(WebElement::getText).toList();
        addButtons.stream().limit(numberOfItems).forEach(WebElement::click);
        cartButton.click();

        WebElement goCheckoutViewButton = driver.findElement(By.xpath("//div[@class='cart_footer']/button[@data-test='checkout']"));
        goCheckoutViewButton.click();

        WebElement inputCheckoutFirstName = driver.findElement(By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"firstName\"]"));
        WebElement inputCheckoutLastName = driver.findElement(By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"lastName\"]"));
        WebElement inputCheckoutPostalCode = driver.findElement(By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"postalCode\"]"));
        WebElement checkoutButton = driver.findElement(By.xpath("//div[@class='checkout_buttons']/input"));
        inputCheckoutFirstName.sendKeys("John");
        inputCheckoutLastName.sendKeys("Doe");
        inputCheckoutPostalCode.sendKeys("12345");
        checkoutButton.click();

        WebElement finishButton = driver.findElement(By.xpath("//div[@class=\"summary_info\"]/div[@class=\"cart_footer\"]/button[@data-test='finish']"));
        List<WebElement> checkoutPrices = driver.findElements(By.xpath("//div[@data-test]/div[@data-test=\"inventory-item\"]/div/div[@class=\"item_pricebar\"]/div"));
        List<String> checkoutPricesText = checkoutPrices.stream().limit(numberOfItems).map(WebElement::getText).toList();
        finishButton.click();

        WebElement successTitle = driver.findElement(By.xpath("//div[@data-test=\"header-container\"]/div[@data-test=\"secondary-header\"]/span[@data-test=\"title\"]"));

        // Assert
        assertEquals(shopPricesText, checkoutPricesText);
        assertEquals("Checkout: Complete!", successTitle.getText());
    }
}
