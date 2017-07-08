package com.webdriver.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class SeleniumHelper {

    private SeleniumHelper() {
    }

    public static WebElement waitUntilLocated(WebDriver driver, By locator) {
        return (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement waitUntilClickable(WebDriver driver, By locator) {
        return (new WebDriverWait(driver, 60)).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
