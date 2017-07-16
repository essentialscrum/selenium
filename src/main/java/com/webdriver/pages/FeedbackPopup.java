package com.webdriver.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

/**
 * Created by A.Chistiakov on 16.07.2017.
 */
public class FeedbackPopup extends BasePage {
    private WebElement checkboxWithReason;
    private WebElement sendFeedbackButton;

    public FeedbackPopup(WebDriver driver) {
        super(driver);
        this.checkboxWithReason = driver.findElement(
                new ByChained(
                        By.id("feedback_form-body"),
                        By.xpath("//label[contains(normalize-space(.), 'Added by mistake')]")
                )
        );
        this.sendFeedbackButton = driver.findElement(
                new ByChained(
                        By.id("feedback_form-body"),
                        By.xpath("//a[contains(normalize-space(.), 'Send feedback')]")
                )
        );
    }

    public FeedbackPopup tickSomeReason(){
        checkboxWithReason.click();
        return this;
    }

    public FeedbackPopup clickSendFeedback(){
        Assert.assertTrue(sendFeedbackButton.isEnabled());
        sendFeedbackButton.click();
        return this;
    }
}
