package com.jguerra47.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By inputUsername = By.id("user-name");
    private final By inputPassword = By.id("password");
    private final By buttonLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterCredentials(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void login() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonLogin)).click();
    }
}
