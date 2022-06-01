package com.cydeo.tests.reviewWeek;

import com.cydeo.tests.utilities.ConfigReader;
import com.cydeo.tests.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task02 {

    WebDriver driver = Driver.getDriver();

    @Test
    public void registrationTest() {

//        1. Navigate to: https://moneygaming.qa.gameaccount.com/

        driver.get("https://moneygaming.qa.gameaccount.com/");

//        2. Click the JOIN NOW button to open the registration page

        WebElement joinNowButton = driver.findElement(By.xpath("//a[@class='newUser green']"));
        joinNowButton.click();

//        3. Select a title value from the dropdown

        Select titleDropdown = new Select(driver.findElement(By.xpath("//select[@name='map(title)']")));

        titleDropdown.selectByValue("Mr");

//        4. Enter your first name and surname in the form

        WebElement nameInputBox = driver.findElement(By.xpath("//input[@name='map(firstName)']"));
        nameInputBox.sendKeys("Adam");

        WebElement surnameInputBox = driver.findElement(By.xpath("//input[@name='map(lastName)']"));
        surnameInputBox.sendKeys("Ridge");

//        5. Check the tick box with text 'I accept the Terms and Conditions and certify that I am over the age of 18.'

        WebElement acceptCheckBox = driver.findElement(By.xpath("//input[@name='map(terms)']"));

        acceptCheckBox.click();

//        6. Submit the form by clicking the JOIN NOW button

        WebElement submitButton = driver.findElement(By.xpath("//input[@alt='Join Now']"));

        submitButton.click();

//        7. Validate that a validation message with text ‘ This field is required’ appears under the date of birth box

        WebElement errorMessageBox = driver.findElement(By.xpath("//label[@for='dob']"));

        String expectedMessage = "This field is required";
        String actualMessage = errorMessageBox.getText();

        Assert.assertEquals(actualMessage, expectedMessage);
    }


}
