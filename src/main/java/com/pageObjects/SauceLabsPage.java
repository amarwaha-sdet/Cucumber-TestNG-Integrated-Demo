package com.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class SauceLabsPage {

    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement userName;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement login;

    public SauceLabsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterCredentials(String un, String pwd) {
        userName.sendKeys(un);
        password.sendKeys(pwd);
    }

    public void clickLogin() {
        login.click();
    }
}
