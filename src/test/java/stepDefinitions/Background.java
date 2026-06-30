package stepDefinitions;

import com.utility.DriverFactory;
import io.cucumber.java.en.Given;
import com.utility.Base;

public class Background extends Base {

    @Given("the user navigates to sauce labs home page")
    public void navigateToSauceHomePage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("sauceUrl"));
        Thread.sleep(1000);
        System.out.println("Page title is: " + DriverFactory.getDriver().getTitle());
    }


    @Given("the user navigates to data tables page")
    public void navigateToDatTableHomePage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("dataTableUrl"));
        Thread.sleep(1000);
        System.out.println("Page title is: " + DriverFactory.getDriver().getTitle());
    }


    @Given("user navigates to demoQA website")
    public void navigateToDemoQAPage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("demoQAUrl"));
        Thread.sleep(1000);
        System.out.println("Page title is: " + DriverFactory.getDriver().getTitle());
    }
}
