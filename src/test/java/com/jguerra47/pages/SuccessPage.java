package com.jguerra47.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SuccessPage {
    private final WebDriver driver;

    private final By successTitle = By.xpath("//div[@data-test=\"header-container\"]/div[@data-test=\"secondary-header\"]/span[@data-test=\"title\"]");

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(successTitle).getText();
    }
}
