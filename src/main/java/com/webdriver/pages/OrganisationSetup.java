package com.webdriver.pages;

import com.webdriver.helpers.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickStartTrial(){
        startTrial.click();
    }
}
