package com.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks extends Base{
    private WebDriver driver;

    @Before()
    public void setup() {
        logger.info("Starting webdriver...");

        //create driver instance based on passed name
        String browser = System.getProperty("browser", "chrome");
        driver = DriverFactory.createInstance(browser);

        //set thread-local driver
        DriverFactory.setDriver(driver);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        DriverFactory.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        logger.info("Closing webdriver...");
        DriverFactory.removeDriver();
    }

}
