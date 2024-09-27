package com.jguerra47.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"firstName\"]");
    private final By lastName = By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"lastName\"]");
    private final By postalCode = By.xpath("//div[@class=\"checkout_info\"]/div[@class=\"form_group\"]/input[@data-test=\"postalCode\"]");
    private final By continueButton = By.xpath("//div[@class='checkout_buttons']/input");
    private final By finishButton = By.xpath("//div[@class=\"summary_info\"]/div[@class=\"cart_footer\"]/button[@data-test='finish']");
    private final By subtotalLabel = By.xpath("//div[@class=\"summary_info\"]/div[@data-test=\"subtotal-label\"]");
    private final By checkoutPrices = By.xpath("//div[@data-test]/div[@data-test=\"inventory-item\"]/div/div[@class=\"item_pricebar\"]/div");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCheckoutInformation(String firstNameInput, String lastNameInput, String postalCodeInput) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(firstNameInput);
        driver.findElement(lastName).sendKeys(lastNameInput);
        driver.findElement(postalCode).sendKeys(postalCodeInput);
        driver.findElement(continueButton).click();
    }

    public void finishPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public double getSubtotal() {
        String subtotalText = wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalLabel)).getText().split("\\$")[1];
        return Double.parseDouble(subtotalText);
    }

    public List<String> getCheckoutPrices(int quantity) {
        List<WebElement> pricesElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(checkoutPrices));
        return pricesElements.stream()
                .limit(quantity)
                .map(WebElement::getText)
                .toList();
    }
}

