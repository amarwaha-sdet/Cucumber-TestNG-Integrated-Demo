package stepDefinitions;

import com.pageObjects.SauceLabsPage;
import com.utility.Base;
import com.utility.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class SauceLabsSteps {

    SauceLabsPage sauceLabsPage = new SauceLabsPage(DriverFactory.getDriver());

    @When("the user enters credentials {string} and {string}")
    public void enterCredentials(String userName, String password) throws InterruptedException {
        System.out.println("Credentials being entered: " + userName + ", " + password);
        sauceLabsPage.enterCredentials(userName, password);
        Thread.sleep(1000);
    }

    @And("the user clicks on login button")
    public void clickLogin() throws InterruptedException {
        sauceLabsPage.clickLogin();
        Thread.sleep(1000);
    }

    @Then("the user verifies login result as {string}")
    public void verifyLoginFunctionality(String result) throws InterruptedException {

        List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath("//div[@class='error-message-container error']"));

        String loginResult = "successful";
        if (!elements.isEmpty()) {
            loginResult = "unsuccessful";
        }

        Assert.assertEquals(loginResult,result,"Expected and Actual results for login mismatched!");

        System.out.println("Login test verified successfully");
        Thread.sleep(1000);
    }

    @Then("the user verifies following products and their prices")
    public void verifyProductPrices(DataTable table) throws InterruptedException {
        List<Map<String,String>> products = table.asMaps(String.class,String.class);

        for (Map<String,String> item : products) {
            WebElement productName = DriverFactory.getDriver().findElement(By.xpath("//div[text()='" + item.get("productName")+ "']"));
            String productPrice = productName.findElement(By.xpath("parent::a/parent::div/parent::div/descendant::div[4]/div")).getText();
            System.out.println("Item price: " + item.get("price") );

            Assert.assertEquals(item.get("price"), productPrice);
        }

        Thread.sleep(1000);
    }

}
