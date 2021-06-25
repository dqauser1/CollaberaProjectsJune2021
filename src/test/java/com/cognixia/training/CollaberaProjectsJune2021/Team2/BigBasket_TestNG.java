package com.cognixia.training.CollaberaProjectsJune2021.Team2;

import java.io.IOException;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class BigBasket_TestNG extends BigBasket_Utility {

	@BeforeTest
	public void beforeTest() {
		openSite();
	}

	@Test
	public void bigBasketTest() throws IOException 
	{	

		selectCity();

		addProduct();

		viewBasket();

		verifyProductName();

		checkLoginPopUpDisplay();

		takeScreenShot();

		closeThePopUp();
	}

	@Test(dataProvider = "getData")
	public void mongodbTest(String Product_names, String Quantity) throws InterruptedException {
		mywait = new WebDriverWait(driver, 10);
		WebElement searchbar = driver.findElement(By.xpath("//input[@qa='searchBar']"));
		searchbar.sendKeys(Product_names);
		WebElement search = driver.findElement(By.xpath("//button[@class='btn btn-default bb-search']"));
		int quant = Integer.parseInt(Quantity);
		search.click();
		WebElement addButton = driver.findElement(By.xpath("(//button[@qa='add'])[1]"));
		addButton.click();
		while ((quant - 1 )> 0) {
			WebElement plus = driver.findElement(By.xpath("(//button[@ng-click='vm.increamentQuantity();'])[1]"));
			plus.click();
			Thread.sleep(3000);
			quant--;
		}
		driver.navigate().refresh();

		System.out.println(Product_names + " " + Quantity);
	}

	@DataProvider
	public Object[][] getData() {
		return readFromMongoDB("products", "Product_names", "Quantity");
	}

	@AfterTest
	public void afterTest() {

		driver.close();
		driver.quit();
	}

}
