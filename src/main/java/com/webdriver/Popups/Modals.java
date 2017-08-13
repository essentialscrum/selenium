package com.webdriver.Popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Modals {
    public static ExpectedCondition<Popup> modalIsDisplayed() {
        return driver -> {
            List<WebElement> somePopup = driver.findElements(By.className("x-popup"));
            if (somePopup.isEmpty())
                return null;

            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            List<WebElement> cancelSubscrTitle = somePopup.get(0).findElements(By.xpath("//span[contains(normalize-space(.), 'Cancel my trial subscription')]"));
            List<WebElement> feedbackTitle = somePopup.get(0).findElements(By.xpath("//span[contains(normalize-space(.), 'Feedback')]"));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            return !cancelSubscrTitle.isEmpty()
                    ? new CancelSubscriptionPopup(somePopup.get(0))
                    : !feedbackTitle.isEmpty()
                    ? new FeedbackPopup(somePopup.get(0))
                    : null;
        };
    }
}
