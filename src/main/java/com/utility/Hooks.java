package com.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks extends Base{
    private WebDriver driver;

    @Before()
    public void setup() {
        logger.info("Starting webdriver...");
        //select driver based on browser type
        String browserName = properties.getProperty("browserName");
        driver = DriverFactory.createInstance(browserName);

        //set thread-local driver
        DriverFactory.setDriver(driver);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        logger.info("Closing webdriver...");
        DriverFactory.removeDriver();
    }

}
