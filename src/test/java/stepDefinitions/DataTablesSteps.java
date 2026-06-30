package stepDefinitions;

import com.pageObjects.DataTablesPage;
import com.utility.Base;
import com.utility.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class DataTablesSteps {

    DataTablesPage dataTablesPage = new DataTablesPage(DriverFactory.getDriver());

    @When("user selects {int} entries per page using selenium")
    public void selectEntriesPerPage(int entry) throws InterruptedException {
        System.out.println("Selecting entries per page : " + entry);
        dataTablesPage.selectEntry(entry);
        Thread.sleep(1000);
    }

    @Then("verify {int} entries are shown in data table")
    public void verifyEntriesInDataTable(int total) {
        WebElement dataTable = DriverFactory.getDriver().findElement(By.id("example"));
        WebElement tableBody = dataTable.findElement(By.tagName("tbody"));
        List<WebElement> rows = tableBody.findElements(By.tagName("tr"));
        System.out.println("Total number of rows in table : " + rows.size());
        Assert.assertEquals(rows.size(), total);
    }

    @Then("user performs various operations using JavaScript")
    public void performOperations() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        WebElement searchBox = DriverFactory.getDriver().findElement(By.id("dt-site-search"));
        js.executeScript("arguments[0].value='hello';", searchBox);
        Thread.sleep(3000);

        //select 5th element from google suggestion
        DriverFactory.getDriver().navigate().to("https://www.google.com/");
        WebElement googleSearchTextBox = DriverFactory.getDriver().findElement(By.xpath("//textarea[@title='Search']"));
        googleSearchTextBox.sendKeys("java");
//        js.executeScript("arguments[0].value='java';", googleSearchTextBox);

        WebElement fifthElement = DriverFactory.getDriver().findElement(By.xpath("//li[@role='presentation'][5]/div/div/following-sibling::div//span"));
        String text = fifthElement.getText();
        System.out.println("Fifth element result : " + text);
        Thread.sleep(3000);
    }


    @When("user performs various actions on flipkart page")
    public void flipkartActions() throws InterruptedException {
        navigateToFlipkartAndCloseLoginPopupWindow();

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
        navigateToFlipkartAndCloseLoginPopupWindow();

        //click travel icon using selenium
        WebElement travelIcon = DriverFactory.getDriver().findElement(By.xpath("//img[contains(@src,'flixcart.com/fk-p-flap/72/36/image/')]"));
//        travelIcon.click();
//        Thread.sleep(2000);

        //click travel icon using javascript
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].click();", travelIcon);

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
        By roundTripButtonLocator = By.xpath("//div[contains(text(), 'Round')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(roundTripButtonLocator));

        Assert.assertTrue(DriverFactory.getDriver().findElement(roundTripButtonLocator).isDisplayed());
        Thread.sleep(3000);
    }

    public void navigateToFlipkartAndCloseLoginPopupWindow() throws InterruptedException {
        DriverFactory.getDriver().navigate().to("https://www.flipkart.com/");
        System.out.println("Page title : " + DriverFactory.getDriver().getTitle());
        Thread.sleep(2000);

        List<WebElement> loginForm = DriverFactory.getDriver().findElements(By.xpath("//span[@role='button']"));
        if(!loginForm.isEmpty()) {
            loginForm.getFirst().click();
        }
    }
}
