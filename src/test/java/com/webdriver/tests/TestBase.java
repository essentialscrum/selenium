package com.webdriver.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebDriverConfig.class)
public abstract class TestBase {
    protected static final String EMAIL = "alex-hellsing@ya.ru";
    protected static final String PASSWORD = "12345678";
    protected static final String SITE_URL = "https://login.xero.com/";

    @Inject
    protected WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver.get(SITE_URL);
    }

}
