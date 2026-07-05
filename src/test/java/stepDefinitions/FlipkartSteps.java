package stepDefinitions;

import com.utility.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Set;

public class FlipkartSteps {

    @When("user performs various actions on flipkart page")
    public void flipkartActions() throws InterruptedException {
        //from more menu go to 24x7 customer support page
        WebElement moreMenu = DriverFactory.getDriver().findElement(By.xpath("//a[@title='More']"));

        Actions actions = new Actions(DriverFactory.getDriver());
        actions.moveToElement(moreMenu).perform();
        Thread.sleep(2000);

        WebElement customerCareLink = DriverFactory.getDriver().findElement(By.xpath("//a[@title='More']/parent::div/following-sibling::ul/child::a[@title='24x7 Customer Care']"));
        customerCareLink.click();
        Thread.sleep(2000);

        WebElement customerCarePageHeading = DriverFactory.getDriver().findElement(By.xpath("//h1[text()='Flipkart Help Center | 24x7 Customer Care Support']"));
        System.out.println("Customer Care Page Heading : " + customerCarePageHeading.getText());
    }


    @And("user clicks on travel button")
    public void flipkartTravel() throws InterruptedException {
        DriverFactory.getDriver().navigate().back();
        //click travel icon using selenium
        WebElement travelIcon = DriverFactory.getDriver().findElement(By.xpath("//img[contains(@src,'flixcart.com/fk-p-flap/72/36/image/')]"));
//        travelIcon.click();
//        Thread.sleep(2000);

        //click travel icon using javascript
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].click();", travelIcon);

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
        By roundTripButtonLocator = By.xpath("//div[contains(text(), 'Round')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(roundTripButtonLocator));

        Assert.assertTrue(DriverFactory.getDriver().findElement(roundTripButtonLocator).isDisplayed());
        Thread.sleep(3000);
    }

    @When("user opens new window and tab")
    public void openNewTabAndWindow() throws InterruptedException {
        //legacy

        //save parent window handle
        String parentHandle = DriverFactory.getDriver().getWindowHandle();

        //open new tab
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("window.open('https://edition.cnn.com/');");
        Thread.sleep(2000);

        //switch to new tab
        Set<String> allHandles = DriverFactory.getDriver().getWindowHandles();

        for (String handle : allHandles) {
            if(!handle.equals(parentHandle)) {
                DriverFactory.getDriver().switchTo().window(handle);
            }
        }

        System.out.println("CNN page title: " + DriverFactory.getDriver().getTitle());
        Thread.sleep(2000);

    }

}
