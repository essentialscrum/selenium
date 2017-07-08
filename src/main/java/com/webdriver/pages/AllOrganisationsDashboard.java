package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllOrganisationsDashboard extends BasePage {
    public static final String MY_XERO_COM = "https://my.xero.com/";

    public AllOrganisationsDashboard(WebDriver driver) {
        super(driver);
    }

    public static AllOrganisationsDashboard goToAllOrganisationsDashboard(WebDriver driver) throws InterruptedException {
        while (!(driver.getCurrentUrl().startsWith(MY_XERO_COM) && driver.getCurrentUrl().contains("Dashboard"))){
            Thread.sleep(100L);
            driver.navigate().to(MY_XERO_COM);
            System.out.println(driver.getCurrentUrl());
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

            driver.findElement(By.cssSelector("div[class='x-btn red x-exclude x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']")).click();
            driver.findElement(By.cssSelector("input[class='x-form-field x-form-radio x-form-cb']")).click();
            driver.findElement(By.cssSelector("div[class='x-btn blue x-exclude x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']")).click();

            while (size != 0 && driver.findElements(removeLinks).size() != size)
                Thread.sleep(100);
        }
    }

    public OrganisationSetup addNewOrganisation() {
        driver.navigate().to(driver.getCurrentUrl().replace("Dashboard", "Organisation/Setup"));
        return new OrganisationSetup(driver);
    }

    public OneOrganisationDashboard goToOneOrganisationDashboard(String companyName) {
        final List<WebElement> organisationLinks = driver.findElements(By.cssSelector("a[data-automationid='dashboardOrgName']"));
        organisationLinks.stream()
                .filter(org -> org.getText().equals(companyName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new).click();
        return new OneOrganisationDashboard(driver);
    }
}
