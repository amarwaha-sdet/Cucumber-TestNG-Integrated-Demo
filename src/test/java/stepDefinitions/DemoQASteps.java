package stepDefinitions;

import com.pageObjects.DemoQAPage;
import com.utility.Base;
import com.utility.DriverFactory;
import io.cucumber.java.en.And;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class DemoQASteps {
    DemoQAPage demoQAPage = new DemoQAPage(DriverFactory.getDriver());

    @And("user uploads a file and verifies file is uploaded")
    public void fileUpload() throws InterruptedException {
        String uploadFilePath = "src/main/resources/filesToUpload/sampleResume.png";
        File file = new File(uploadFilePath);
        //make element visible if hidden <input type="file">
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        By locator = By.xpath("//input[@label='Select a File']");
        WebElement uploadButton = DriverFactory.getDriver().findElement(locator);
        js.executeScript("arguments[0].style.display='block'; arguments[0].style.visibility='visible';",uploadButton);
        Thread.sleep(5000);


        demoQAPage.uploadFileToPage(file.getAbsolutePath());

        FluentWait fluentWait = new FluentWait(DriverFactory.getDriver());
        fluentWait.withTimeout(Duration.ofSeconds(20));
        fluentWait.pollingEvery(Duration.ofMillis(250));
        fluentWait.ignoring(NoSuchElementException.class);

        By successMessageLocator = By.xpath("//p[text()='C:\\fakepath\\sampleResume.png']");

        WebElement element = (WebElement) fluentWait.until(ExpectedConditions.visibilityOfElementLocated(successMessageLocator));

        Assert.assertTrue(element.isDisplayed());

        Thread.sleep(3000);
    }

    @And("user downloads a file and verifies file is downloaded")
    public void fileDownload() throws InterruptedException, IOException {
        //clean download directory before proceeding
        String downloadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\downloadedFiles";
        File folder = new File(downloadPath);
        FileUtils.cleanDirectory(folder);

        //download file to download folder
        demoQAPage.downloadFileFromPage();
        Thread.sleep(3000);

        //check file is downloaded
        File[] fileList = folder.listFiles();
        String downloadedFileName = "";

        for (File file : fileList) {
            if (file.exists()) {
                downloadedFileName = file.getName();
            }
        }

        Assert.assertTrue(downloadedFileName.equals("sampleFile.jpeg"));
    }


}
