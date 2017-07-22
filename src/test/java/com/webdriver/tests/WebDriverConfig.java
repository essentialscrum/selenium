package com.webdriver.tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebDriverConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DesiredCapabilities desiredCapabilities(
            @Value("${webdriver.capabilities.browserName:chrome}") String browserName
    ) {
        return new DesiredCapabilities(browserName, "", Platform.ANY);
    }

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver(DesiredCapabilities desiredCapabilities) {
        switch (desiredCapabilities.getBrowserName()) {
            case BrowserType.FIREFOX:
                System.setProperty("webdriver.gecko.driver", "target/geckodriver");
                return setupDriver(new FirefoxDriver(desiredCapabilities));
            case BrowserType.HTMLUNIT:
                return setupDriver(new HtmlUnitDriver(desiredCapabilities));
            case BrowserType.PHANTOMJS:
                return setupDriver(new HtmlUnitDriver(desiredCapabilities));
            case BrowserType.CHROME:
                ChromeDriverManager.getInstance().setup();
                return setupDriver(new ChromeDriver(desiredCapabilities));
            default:
                throw new IllegalStateException("unknown browser " + desiredCapabilities.getBrowserName());
        }
    }

    private WebDriver setupDriver(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//Checking functionality not a performance
        driver.manage().window().setSize(new Dimension(1200, 850));
        return driver;
    }
}
