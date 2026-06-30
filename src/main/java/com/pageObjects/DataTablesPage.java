package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DataTablesPage {
    //WebElements
    @FindBy(className = "dt-input")
    private WebElement entriesPerPage;


    //Constructor
    public DataTablesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    //Methods
    public void selectEntry(int value) {
        Select dropdown = new Select(entriesPerPage);
        dropdown.selectByValue(String.valueOf(value));
    }
}
