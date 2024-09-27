package com.jguerra47;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
