package com.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;


public class DriverFactory extends Base {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

    public static WebDriver createInstance(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", properties.getProperty("chromeDriverPath"));
            return new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", properties.getProperty("edgeDriverPath"));
            String downloadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\downloadedFiles";
            EdgeOptions options = new EdgeOptions();
            Map<String, Object> edgeOptions = new HashMap<>();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath);
            edgeOptions.put("prefs", prefs);
            options.setCapability("ms:edgeOptions", edgeOptions);
            return new EdgeDriver();
        }
        else {
            throw new IllegalArgumentException("Browser not supported! : " + browserName );
        }
    }

}
