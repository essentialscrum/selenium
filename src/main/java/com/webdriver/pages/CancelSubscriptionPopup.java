package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

public class CancelSubscriptionPopup extends BasePage {
    private WebElement confirmCancellation;

    public CancelSubscriptionPopup(WebDriver driver) {
        super(driver);
        this.confirmCancellation = driver.findElement(
                new ByChained(
                        By.className("x-popup"),
                        By.xpath("//a[contains(normalize-space(.), 'Confirm cancellation')]")
                ));
    }

    public FeedbackPopup clickConfirmCancellation() {
        confirmCancellation.click();
        return new FeedbackPopup(driver);
    }
}
