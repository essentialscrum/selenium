package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends BasePage {
    public AccountsPage(WebDriver driver) {
        super(driver);
    }

    public ANZAccountPage addANZBankAccount() throws InterruptedException {
        driver.navigate().to("https://go.xero.com/Banking/Account/#find");
        final WebElement bankNameInput = driver.findElement(By.id("xui-searchfield-1018-inputEl"));
        bankNameInput.sendKeys(ANZAccountPage.ANZ_BANK);
        Thread.sleep(2000L);//TODO
        final List<WebElement> banks = driver.findElements(By.cssSelector("li[class='ba-banklist--item xui-contentblock--item'"));
        banks.stream()
                .filter(b->b.getText().toLowerCase().equals(ANZAccountPage.ANZ_BANK.toLowerCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .click();
        return new ANZAccountPage(driver);
    }

    public boolean availableBanksContains(String bankAccountName){
        return driver.findElements(By.cssSelector("div[class='bank']")).stream()
                .anyMatch(b->b.findElement(By.cssSelector("a[class='bank-name global']")).getText().contains(bankAccountName));
    }

    public boolean alertIsShown(){
        return driver.findElement(By.cssSelector("ul[class='x-list-plain']")).isDisplayed();
    }
}
