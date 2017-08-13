package com.webdriver.Popups;

import com.webdriver.Popups.Popup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.Credentials;

public class CancelSubscriptionPopup implements Popup {
    private WebElement popUp;

    public CancelSubscriptionPopup(WebElement popUp) {
        this.popUp = popUp;
    }

    public void clickConfirmCancellation() {
        final WebElement confirmCancellation = popUp.findElement(By.xpath("//a[contains(normalize-space(.), 'Confirm cancellation')]"));
        confirmCancellation.click();
    }

    @Override
    public void dismiss() {
        //TODO
    }

    @Override
    public void accept() {
        clickConfirmCancellation();
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

    @Override
    public void doAction() {

    }
}
