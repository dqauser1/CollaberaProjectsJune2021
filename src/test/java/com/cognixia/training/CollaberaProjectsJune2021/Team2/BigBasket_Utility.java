package com.cognixia.training.CollaberaProjectsJune2021.Team2;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class BigBasket_Utility extends Base {

	protected static WebDriverWait mywait;

	public static void openSite() {
		openBrowser("Chrome");
		driver.get("https://www.bigbasket.com");
	}

	public static void selectCity() {
		WebElement dropdown = driver.findElement(By.xpath("//span[@class='arrow-marker']"));
		dropdown.click();

		WebElement searchbox = driver.findElement(By.xpath("//span[@class = 'ng-binding ng-scope' and contains(text() ,'Bangalore')]"));
		searchbox.click();

		WebElement bangalore = driver.findElement(By.xpath("//a[@class= 'ui-select-choices-row-inner']/span[contains(text(),'Bangalore')]"));
		bangalore.click();

		WebElement continuebutton = driver.findElement(By.xpath("//button[@class = 'btn btn-default ng-scope' and @name ='skipandexplore']"));
		continuebutton.click();

	}

	public static void addProduct() {
		WebElement add = driver.findElement(By.xpath("(//h2[text()='Best Sellers']/../../div[2]//*[@qa='add'])[1]"));
		add.click();

		WebElement product = driver.findElement(By.xpath("(//h2[text()='Best Sellers']/../../div[2]//*[@class='ng-binding'])[3]"));
		System.out.println(product.getText());

		WebElement popupclosebutton = driver.findElement(By.xpath("//div[@class = 'toast toast-success']/button "));
		popupclosebutton.click();
	}

	protected static void viewBasket() {
		WebElement bigbasket = driver.findElement(By.xpath("//a[@class='btn btn-default basket-btn-drop hidden-xs hidden-sm']"));
		mywait = new WebDriverWait(driver, 10);
		mywait.until(ExpectedConditions.elementToBeClickable(bigbasket));
		Actions myActions = new Actions(driver);
		myActions.moveToElement(bigbasket);
		myActions.pause(5000);
		Action myAction = myActions.build();
		myAction.perform();
		WebElement viewbasket = driver.findElement(By.xpath("//button[@qa='viewBasketMB']"));
		mywait = new WebDriverWait(driver, 10);
		mywait.until(ExpectedConditions.elementToBeClickable(viewbasket));
		viewbasket.click();

	}

	protected static void verifyProductName() {
		WebElement productName = driver.findElement(By.xpath("(//h2[text()='Best Sellers']/../../div[2]//*[@qa='add'])[1]"));
		String addedProduct = productName.getText();
		System.out.println(addedProduct);
		WebElement productnameinBasket = driver.findElement(By.xpath("//div[@class='product-name']"));
		String basketProduct = productnameinBasket.getText();
		Assert.assertTrue((basketProduct.contains(addedProduct)));
		System.out.println("Product name matches - Test Passed");
	}

	protected static void checkLoginPopUpDisplay() {
		WebElement logintitle = driver.findElement(By.xpath("//li[@ng-class=\"{ active : loginAndSignupBase.tabs.login }\"]"));
		String title = logintitle.getText();
		Assert.assertTrue((title).equalsIgnoreCase("login"));
		System.out.println("Login pop is displayed - Test Passed");

	}

	protected static void takeScreenShot() throws IOException {
		File f;
		f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\LoginPopUp.jpg"));
	}

	protected static void closeThePopUp() {
		driver.findElement(By.xpath("//button[@ng-click='dismiss()']")).click();
	}

	@SuppressWarnings("resource")
	protected static Object[][] readFromMongoDB(String collectionName, String field1, String field2) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Bigbasket");
		MongoCollection<Document> collection = database.getCollection(collectionName);
		MongoCursor<Document> cursor1 = collection.find().iterator();
		int datalength = 0;
		while (cursor1.hasNext()) {
			Document d = cursor1.next();
			datalength++;
		}

		Object[][] array = new Object[datalength][2];
		MongoCursor<Document> cursor = collection.find().iterator();
		int row = 0;
		while (cursor.hasNext()) {
			Document d = cursor.next();
			array[row][0] = ((String) d.get(field1)).trim();
			array[row][1] = ((String) d.get(field2)).trim();
			// System.out.println(array[row][0] + " " + array[row][1]);
			row++;
		}
		return array;
	}

}
