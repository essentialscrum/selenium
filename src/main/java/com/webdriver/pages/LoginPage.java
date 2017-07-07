package com.webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(id = "email")
    private WebElement email;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(id = "submitButton")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public AllOrganisationsDashboard loginSuccessfully(String email, String password){
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.loginButton.click();
        return new AllOrganisationsDashboard(driver);
    }
}
