package com.webdriver.tests;

import com.webdriver.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This test cases do not cover all potential issues with Organisation types. Some of them was tested manually,
 * that helped to realise potential risks(but not enough time to cover it in auto).
 *
 * There are a few hypothesis on which was based testing effort.
 * 1) Was covered only NZ companies, cause ANZ (NZ) bank
 * 2) Any Xero Organisation means all possible variants to create a NZ company.
 *
 * Discovered issues:
 * 1) There are duplicates in the combolist with Organisation Industries
 * 2) If country was chosen after the Industry then industry will be discarded
 *
 * These tests shouldn't be run in Parallel
 */
public class BankAccountsCreationTest extends TestBase {
    private OneOrganisationDashboard organisationDashboard;

    @Before
    public void setUp() {
        //Here Should be the mock of login link or the REST/API request to login without selenium routine
        final AllOrganisationsDashboard dashboard = logIn();

        //Here should be the REST/API request to crete our Organisation
        organisationDashboard = dashboard.addDefaultNewZealandOrganisation();
    }

    @After
    public void reset() {
        //Here should be the REST/API request to remove our Organisation
        final AllOrganisationsDashboard dashboard = new AllOrganisationsDashboard(driver);
        dashboard.removeAllOrganisations();
    }

    private AllOrganisationsDashboard logIn() {
        return new LoginPage(driver).loginSuccessfully(EMAIL, PASSWORD);
    }

    /*
    This test fails cause accountName shouldn't accept ' '
     */
    @Test
    public void createAccountWithMinParams() throws InterruptedException {
        final AccountsPage accountsPageBefore = organisationDashboard.selectAcccountsTab();
        final ANZAccountPage anzAccountPage = accountsPageBefore.addANZBankAccount();
        String accountName = " ";//Minimum required value and highest probability error
        anzAccountPage.setAccountName(accountName);
        anzAccountPage.setAccountType("Other");//Not a credit card
        anzAccountPage.setAccountNumber("/");//The same story, but ' ' is not accepted here
        final AccountsPage accountsPageAfter = anzAccountPage.pushContinue();
        Assert.assertTrue(accountsPageAfter.availableBanksContains(accountName));
    }

    @Test
    public void htmlInjection() throws InterruptedException {
        final AccountsPage accountsPageBefore = organisationDashboard.selectAcccountsTab();
        final ANZAccountPage anzAccountPage = accountsPageBefore.addANZBankAccount();
        final String accountName = "<span>The longest</span>";//Maximum required value and html injection
        anzAccountPage.setAccountName(accountName);
        anzAccountPage.setAccountType("Other");
        final String accountNumber = "</span>Number1<span>";//Maximum required value and html injection
        anzAccountPage.setAccountNumber(accountNumber);
        final AccountsPage accountsPageAfter = anzAccountPage.pushContinue();
        Assert.assertTrue(accountsPageAfter.availableBanksContains(accountName));
        Assert.assertTrue(accountsPageAfter.availableBanksContains(accountNumber));
    }

    @Test
    public void htmlInjectionCreditCardOnly() throws InterruptedException {
        final AccountsPage accountsPageBefore = organisationDashboard.selectAcccountsTab();
        final ANZAccountPage anzAccountPage = accountsPageBefore.addANZBankAccount();
        final String accountName = "Account Name";
        anzAccountPage.setAccountName(accountName);
        anzAccountPage.setAccountType("Credit card");
        final String cardNumber = "<br>";//Max (Min the same) required value and html injection
        anzAccountPage.setCreditCardNumber(cardNumber);
        final AccountsPage accountsPageAfter = anzAccountPage.pushContinue();
        Assert.assertTrue(accountsPageAfter.availableBanksContains(cardNumber));
    }

    @Test
    public void inheritCardNumber() throws InterruptedException {
        final AccountsPage accountsPageBefore = organisationDashboard.selectAcccountsTab();
        final ANZAccountPage anzAccountPage = accountsPageBefore.addANZBankAccount();
        final String accountName = "Account Name";
        anzAccountPage.setAccountName(accountName);
        anzAccountPage.setAccountType("Other");
        final String accountNumber = "123456";//Credit card number will be inherited from this
        anzAccountPage.setAccountNumber(accountNumber);
        anzAccountPage.setAccountType("Credit card");//Change on Credit card
        final AccountsPage accountsPageAfter = anzAccountPage.pushContinue();
        Assert.assertTrue(accountsPageAfter.alertIsShown());
    }

    @Test
    public void create2AccountsWithTheSameName() throws InterruptedException {
        //TODO just to show a test case idea
    }
}
