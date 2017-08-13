package com.webdriver.Popups;

import com.webdriver.Popups.Popup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.pagefactory.ByChained;

public class FeedbackPopup implements Popup {
    private WebElement popUp;

    public FeedbackPopup(WebElement popUp) {
        this.popUp = popUp;
    }

    public void tickSomeReason(){
        final WebElement checkboxWithReason = popUp.findElement(
                new ByChained(
                        By.id("feedback_form-body"),
                        By.xpath("//label[contains(normalize-space(.), 'Added by mistake')]")
                )
        );
        checkboxWithReason.click();
    }

    public void clickSendFeedback(){
        final WebElement sendFeedbackButton = popUp.findElement(
                new ByChained(
                        By.id("feedback_form-body"),
                        By.xpath("//a[contains(normalize-space(.), 'Send feedback')]")
                )
        );
        sendFeedbackButton.click();
    }

    @Override
    public void doAction() {
        tickSomeReason();
    }

    @Override
    public void dismiss() {
        //TODO
    }

    @Override
    public void accept() {
        clickSendFeedback();
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public void sendKeys(String s) {

    }

    @Override
    public void setCredentials(Credentials credentials) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void authenticateUsing(Credentials credentials) {
        throw new UnsupportedOperationException();
    }
}
