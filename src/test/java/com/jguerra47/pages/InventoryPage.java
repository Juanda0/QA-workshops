package com.jguerra47.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class InventoryPage {
    private final WebDriver driver;
    private final By addButtons = By.xpath("(//div[@data-test=\"inventory-item-description\"])/div[@class=\"pricebar\"]/button");
    private final By prices = By.xpath("(//div[@data-test=\"inventory-item-description\"])/div[@class=\"pricebar\"]/div[@data-test=\"inventory-item-price\"]");
    private final By cartButton = By.xpath("//div[@data-test=\"primary-header\"]/div[@class=\"shopping_cart_container\"]/a");
    private final By pageTitle = By.xpath("//span[@data-test='title']");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getPrices(int quantity) {
        List<WebElement> pricesElements = driver.findElements(prices);
        return pricesElements.stream().limit(quantity).map(WebElement::getText).toList();
    }

    public void addProductsToCart(int quantity) {
        List<WebElement> addBtns = driver.findElements(addButtons);
        addBtns.stream().limit(quantity).forEach(WebElement::click);
    }

    public void goToCart() {
        driver.findElement(cartButton).click();
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
