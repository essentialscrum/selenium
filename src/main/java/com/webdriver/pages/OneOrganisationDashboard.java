package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OneOrganisationDashboard extends BasePage {

    public OneOrganisationDashboard(WebDriver driver) {
        super(driver);
        SeleniumHelper.waitUntilClickable(driver, By.id("Accounts"));
    }

    public AccountsPage selectAcccountsTab() {
        driver.navigate().to("https://go.xero.com/Bank/BankAccounts.aspx");
        return new AccountsPage(driver);
    }
}
