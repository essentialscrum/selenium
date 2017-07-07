package com.webdriver.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

    public abstract class TestBase {
    protected static final String EMAIL = "alex-hellsing@ya.ru";
    protected static final String PASSWORD = "12345678";
    protected static final String SITE_URL = "https://login.xero.com/";

    protected WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Checking functionality not a performance
        driver.manage().window().setSize(new Dimension(1200, 850));
        driver.manage().deleteAllCookies();
        driver.navigate().to(SITE_URL);
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }
}
