package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAPage {

    @FindBy(id = "uploadFile")
    WebElement chooseFileButton;

    @FindBy(id = "downloadButton")
    WebElement downloadButton;

    public DemoQAPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void uploadFileToPage(String filePath) {
        chooseFileButton.sendKeys(filePath);
    }

    public void downloadFileFromPage() {
        downloadButton.click();
    }

}
