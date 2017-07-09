package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ANZAccountPage extends BasePage {
    public static final String ANZ_BANK = "ANZ (NZ)";
    @FindBy(id = "accountname-1037-inputEl")
    private WebElement accountName;
    @FindBy(id = "accounttype-1039-inputEl")
    private WebElement accountType;
    @FindBy(id = "common-button-submit-1015")
    private WebElement continueButton;

    public ANZAccountPage(WebDriver driver) {
        super(driver);
        SeleniumHelper.waitUntilClickable(driver, By.id("accountname-1037-inputEl"));
    }

    public void setAccountName(String name) {
        accountName.clear();
        accountName.sendKeys(name);
        accountName.sendKeys(Keys.ENTER);
    }

    public void setAccountType(String type) {
        accountType.click();
        driver.findElements(By.cssSelector("li.ba-combo-list-item")).stream()
                .filter(t->t.getText().toLowerCase().contains(type.toLowerCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .click();
    }

    public void setAccountNumber(String number) {
        final WebElement accountNumber = driver.findElement(By.id("accountnumber-1068-inputEl"));
        accountNumber.clear();
        accountNumber.sendKeys(number);
        accountNumber.sendKeys(Keys.ENTER);
    }

    public void setCreditCardNumber(String number) {
        final WebElement cardNumber = driver.findElement(By.id("accountnumber-1063-inputEl"));
        cardNumber.clear();
        cardNumber.sendKeys(number);
        cardNumber.sendKeys(Keys.ENTER);
    }

    public AccountsPage pushContinue() {
        continueButton.click();
        return new AccountsPage(driver);
    }
}
