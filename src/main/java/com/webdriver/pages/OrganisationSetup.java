package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrganisationSetup extends BasePage {
    @FindBy(id = "text-1022-inputEl")
    private WebElement organisationName;
    @FindBy(id = "countryCmb-inputEl")
    private WebElement countryForTaxes;
    @FindBy(id = "industrysearchcombofield-1025-inputEl")
    private WebElement companyIndustry;
    @FindBy(id = "simplebutton-1034")
    private WebElement startTrial;


    public OrganisationSetup(WebDriver driver) {
        super(driver);
    }

    public static boolean isOrganisationSetupPage(WebDriver driver){
        final List<WebElement> organisationName = driver.findElements(By.id("text-1022-inputEl"));
        return organisationName != null && !organisationName.isEmpty();
    }

    public void setTheCompanyName(String companyName){
        organisationName.clear();
        organisationName.sendKeys(companyName);
    }

    public void setCountryForTaxes(String countryName){
        countryForTaxes.clear();
        countryForTaxes.sendKeys(countryName);
        countryForTaxes.sendKeys(Keys.ENTER);
    }

    public void setCompanyIndustry(String industry){
        companyIndustry.clear();
        companyIndustry.sendKeys(industry);
        SeleniumHelper.waitUntilClickable(driver, By.className("industryComboList"));
        companyIndustry.sendKeys(Keys.ENTER);
    }

    public OneOrganisationDashboard clickStartTrial(){
        startTrial.click();
        return new OneOrganisationDashboard(driver);
    }

    public OneOrganisationDashboard addDefaultNewZealandOrganisation() {
        setTheCompanyName("Company Name " + System.currentTimeMillis());
        setCountryForTaxes("New Zealand");//Only from New Zealand should be
        setCompanyIndustry("Test Industry");
        return clickStartTrial();
    }
}
