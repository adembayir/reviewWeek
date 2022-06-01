package com.cydeo.tests.reviewWeek;

import com.cydeo.tests.utilities.ConfigReader;
import com.cydeo.tests.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task01 {

    public static Double totalPrice() {
        try {
            Double priceDecimalPart = Double.parseDouble(Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-fraction'])[1]")).getText());

            Double price = Double.parseDouble(Driver.getDriver().findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText());

            return price + (priceDecimalPart/100);
        } catch (Exception e) {

            String priceText = Driver.getDriver().findElement(By.xpath("(//span[@class='a-size-medium a-color-base sc-price sc-white-space-nowrap'])[1]")).getText().substring(1);

            Double price = Double.parseDouble(priceText);

            return price;
        }
    }


    WebDriver driver = Driver.getDriver();

    @BeforeMethod
    public void setUp() {


//    1.	Go to https://www.amazon.com

        driver.get(ConfigReader.getProperty("env"));
    }

    /*
    @AfterMethod
    public void tearDown() {
        Driver.getDriver().quit();
    }
    */

    @Test
    public void amazonTask() {

//    2.	Search for "hats for men" (Call from Configuration.properties file)

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys(ConfigReader.getProperty("searchValue") + Keys.ENTER);

//    3.	Add the first hat appearing to Cart with quantity 2


        driver.findElement(By.xpath("(//a[@class='a-link-normal s-underline-text s-underline-link-text s-link-style a-text-normal'])[1]")).click();

        Double oneItemPrice = Double.parseDouble(Driver.getDriver().findElement(By.xpath("//span[@class='a-price a-text-price a-size-medium']/span[@aria-hidden='true']")).getText().substring(1));

        Select selectQuantity = new Select(driver.findElement(By.xpath("//select[@name='quantity']")));

        selectQuantity.selectByValue("2");

        WebElement addToChartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));

        addToChartButton.click();

//    4.	Open cart and assert that the total price and quantity are correct

        driver.findElement(By.xpath("//div[@id='nav-cart-count-container']")).click();

        Double expectedPrice = oneItemPrice * 2;
        Double actualPrice = totalPrice();
        Assert.assertEquals(actualPrice,expectedPrice);

        int expectedQuantity = 2;
        int actualQuantity = Integer.parseInt(driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText());

        Assert.assertEquals(actualQuantity, expectedQuantity);

//    5.	Reduce the quantity from 2 to 1 in Cart for the item selected in the step 3

        Select itemQuantity = new Select(driver.findElement(By.xpath("//select[@name='quantity']")));

        itemQuantity.selectByValue("1");

//    6.	Assert that the total price and quantity has been correctly changed

        expectedPrice = oneItemPrice;
        actualPrice = totalPrice();
        Assert.assertEquals(actualPrice,expectedPrice);

        expectedQuantity = 1;
        actualQuantity = Integer.parseInt(driver.findElement(By.xpath("//span[@class='a-dropdown-prompt']")).getText());


    }

}
