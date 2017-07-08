package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OneOrganisationDashboard extends BasePage {
    private static final By byIdAccounts = By.id("Accounts");

    public OneOrganisationDashboard(WebDriver driver) {
        super(driver);
    }

    public static boolean isOneOrganisationDashboardPage(WebDriver driver){
        final List<WebElement> accounts = driver.findElements(byIdAccounts);
        return accounts != null && !accounts.isEmpty();
    }

    public AccountsPage selectAccountsTab() {
        SeleniumHelper.waitUntilClickable(driver, byIdAccounts);
        driver.navigate().to("https://go.xero.com/Bank/BankAccounts.aspx");
        return new AccountsPage(driver);
    }

    public AllOrganisationsDashboard selectMyXero() throws InterruptedException {
        return AllOrganisationsDashboard.goToAllOrganisationsDashboard(driver);
    }
}
