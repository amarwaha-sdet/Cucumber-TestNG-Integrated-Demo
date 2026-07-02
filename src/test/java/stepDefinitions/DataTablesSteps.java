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

}
