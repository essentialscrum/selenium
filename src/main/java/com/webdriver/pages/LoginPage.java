package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.webdriver.pages.OneOrganisationDashboard.isOneOrganisationDashboardPage;
import static com.webdriver.pages.OrganisationSetup.isOrganisationSetupPage;

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

    public BasePage loginSuccessfully(String email, String password) throws InterruptedException {
        this.email.sendKeys(email);
        this.password.sendKeys(password);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        this.loginButton.click();
        while (true){
            if (isOrganisationSetupPage(driver)) {
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Checking functionality not a performance
                return new OrganisationSetup(driver);
            }

            if (isOneOrganisationDashboardPage(driver)) {
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Checking functionality not a performance
                return new OneOrganisationDashboard(driver);
            }
        }
    }
}
