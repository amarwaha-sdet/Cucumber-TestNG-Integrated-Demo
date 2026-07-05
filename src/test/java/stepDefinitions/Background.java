package stepDefinitions;

import com.utility.DriverFactory;
import io.cucumber.java.en.Given;
import com.utility.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Background extends Base {

    @Given("the user navigates to sauce labs home page")
    public void navigateToSauceHomePage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("sauceUrl"));
        Thread.sleep(1000);
        System.out.println("SauceLabs page title: " + DriverFactory.getDriver().getTitle());
    }


    @Given("the user navigates to data tables page")
    public void navigateToDatTableHomePage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("dataTableUrl"));
        Thread.sleep(1000);
        System.out.println("DataTable.net page title: " + DriverFactory.getDriver().getTitle());
    }


    @Given("user navigates to demoQA website")
    public void navigateToDemoQAPage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("demoQAUrl"));
        Thread.sleep(1000);
        System.out.println("DemoQA page title: " + DriverFactory.getDriver().getTitle());
    }

    @Given("user navigates to flipkart home page")
    public void navigateToFlipkartPage() throws InterruptedException {
        DriverFactory.getDriver().get(properties.getProperty("flipkartUrl"));
        Thread.sleep(1000);
        System.out.println("Flipkart page title: " + DriverFactory.getDriver().getTitle());

        //close login popup if it appears
        List<WebElement> loginForm = DriverFactory.getDriver().findElements(By.xpath("//span[@role='button']"));
        if(!loginForm.isEmpty()) {
            loginForm.getFirst().click();
        }
    }

}
