package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllOrganisationsDashboard extends BasePage {
    public static final String MY_XERO_COM = "https://my.xero.com/";
    public static final String MY_XERO_TITLE = "My Xero | Home";

    public AllOrganisationsDashboard(WebDriver driver) {
        super(driver);
    }

    public static AllOrganisationsDashboard goToAllOrganisationsDashboard(WebDriver driver) throws InterruptedException {
        while (!driver.getTitle().contains(MY_XERO_TITLE)){
            Thread.sleep(100L);
            driver.navigate().to(MY_XERO_COM);
        }

        return new AllOrganisationsDashboard(driver);
    }

    public void removeAllOrganisations() throws InterruptedException {
        final By removeLinks = By.cssSelector("a[data-automationid='deleteOrgLink']");
        int size = driver.findElements(removeLinks).size();
        while (size-- > 0) {
            driver.findElements(removeLinks).stream()
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new)
                    .click();

            new CancelSubscriptionPopup(driver)
                    .clickConfirmCancellation()
                    .tickSomeReason()
                    .clickSendFeedback();

            while (size != 0 && driver.findElements(removeLinks).size() != size)
                Thread.sleep(100);
        }
    }

    public OrganisationSetup addNewOrganisation() {
        driver.navigate().to(driver.getCurrentUrl().replace("Dashboard", "Organisation/Setup"));
        return new OrganisationSetup(driver);
    }
}
