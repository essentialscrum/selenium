package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllOrganisationsDashboard extends BasePage {
    public static final String MY_XERO_COM = "https://my.xero.com/";

    public AllOrganisationsDashboard(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().startsWith(MY_XERO_COM)) {
            driver.navigate().to(MY_XERO_COM);
        }
    }

    public void removeAllOrganisations() {
        final By removeLinks = By.cssSelector("a[data-automationid='deleteOrgLink']");
        int size = driver.findElements(removeLinks).size();
        while (size-- > 0) {
            SeleniumHelper.waitUntilClickable(driver, removeLinks);
            final WebElement link = driver.findElements(removeLinks).stream().findAny().orElseThrow(IllegalArgumentException::new);
            link.click();

            driver.findElement(By.cssSelector("div[class='x-btn red x-exclude x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']")).click();
            driver.findElement(By.cssSelector("input[class='x-form-field x-form-radio x-form-cb']")).click();
            driver.findElement(By.cssSelector("div[class='x-btn blue x-exclude x-unselectable x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']")).click();
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

    public OneOrganisationDashboard addDefaultNewZealandOrganisation() {
        final OrganisationSetup organisationSetup = addNewOrganisation();
        String companyName = "Test Name " + System.currentTimeMillis();
        organisationSetup.setTheCompanyName(companyName);
        organisationSetup.setCountryForTaxes("New Zealand");//Only from New Zealand should be
        organisationSetup.setCompanyIndustry("Test Industry");
        organisationSetup.clickStartTrial();
        return new OneOrganisationDashboard(driver);
    }
}
