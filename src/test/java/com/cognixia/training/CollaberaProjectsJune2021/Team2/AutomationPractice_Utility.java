package com.cognixia.training.CollaberaProjectsJune2021.Team2;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AutomationPractice_Utility extends Base {
	protected static File f;
	protected static WebDriverWait mywait;
	protected static double finalcheckprice;
	

	// 1. Navigating to the site
	public static void openSite() {
		openBrowser("Chrome");
		driver.get("http://www.automationpractice.com");
	}

	// 2. Signing In
	public static void signIn() {
		driver.findElement(By.xpath("//a[@class='login']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("yogeshgoswami800@gmail.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Teambassign");
		driver.findElement(By.xpath("//button[@name='SubmitLogin']")).click();
	}
	
	// 3. Searching product
	public static void searchProduct() {
		WebElement searchbox = driver.findElement(By.xpath("//input[@id='search_query_top']"));
		searchbox.sendKeys("dress");
		searchbox.submit();
	}

	// 4. Accessing drop-down and clicking lowest price
	public static void clickDropdown() {
		driver.findElement(By.xpath("//select[@id='selectProductSort']")).click();
		driver.findElement(By.xpath("//select[@id='selectProductSort']/option[@value='price:asc']")).click();
	}

	// 5.Hovering on first product image and clicking on more button
	public static void hoverOver() {
		WebElement more = driver.findElement(By.xpath("(//a[@class='button lnk_view btn btn-default']/span)[1]"));
		Actions myActions = new Actions(driver);
		myActions.moveToElement(driver.findElement(By.xpath("//img[@alt= 'Faded Short Sleeve T-shirts']")));
		myActions.pause(5000);
		Action myAction = myActions.build();
		myAction.perform();
		mywait = new WebDriverWait(driver, 10);
		mywait.until(ExpectedConditions.elementToBeClickable(more));
		more.click();
	}

	// Private method to compare two prices
	private static void validatePrice(double price1, double price2) {
		if (price1 == price2) {
			System.out.println(" price on add to cart and final price are equal");
		} else {
			System.out.println("price on add to cart and final price are not equal");
		}
	}

	// 6. Capturing price before adding to cart
	public static void checkInitialPrice() {
		WebElement availableprice = driver.findElement(By.xpath("//div[@class= 'content_prices clearfix']"));
		String price = availableprice.getText();
		System.out.println("Available Price before adding to cart : " + price);
	}

	//Private method to define file variable
	private static void takeScreenshot() {
		f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	// 7. Taking screenshot of all images
	public static void captureImages() throws IOException {
		// Image 1
		driver.findElement(By.xpath("//img[@id='thumb_1']")).click();
		takeScreenshot();
		FileUtils.copyFile(f,
				new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\image1.jpg"));
		driver.findElement(By.xpath("//a[@title= 'Close']")).click();

		// Image 2
		driver.findElement(By.xpath("//img[@id='thumb_2']")).click();
		takeScreenshot();
		FileUtils.copyFile(f,
				new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\image2.jpg"));
		driver.findElement(By.xpath("//a[@title= 'Close']")).click();

		// Image 3
		driver.findElement(By.xpath("//img[@id='thumb_3']")).click();
		takeScreenshot();
		FileUtils.copyFile(f,
				new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\image3.jpg"));
		driver.findElement(By.xpath("//a[@title= 'Close']")).click();

		// Scrolling images
		driver.findElement(By.xpath("//a[@id = 'view_scroll_right']")).click();

		// Image 4
		driver.findElement(By.xpath("//img[@id='thumb_4']")).click();
		takeScreenshot();
		FileUtils.copyFile(f,
				new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\image4.jpg"));
		driver.findElement(By.xpath("//a[@title= 'Close']")).click();

	}

	// 8. Adding to cart and comparing total final price
	public static void checkTotalPrice() {
		
		//clicking on checkout
		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		
		//waiting for pop-up to display
		mywait = new WebDriverWait(driver, 10);
		WebElement proceed = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
		mywait.until(ExpectedConditions.elementToBeClickable(proceed));
		
		//Capturing before summary page price
		String beforesummaryprice = driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();
		System.out.println("Price on add to cart pop-up : " + beforesummaryprice);
		beforesummaryprice = beforesummaryprice.substring(1).trim();
		double doublebeforesummaryprice = Double.parseDouble(beforesummaryprice);
		
		//clicking on proceed.
		proceed.click();
		
		//Capturing final price on summary page
		String totalfinalprice = driver.findElement(By.xpath("//td/span[@id = 'total_price']")).getText();
		System.out.println("Price after clicking on proceed : " + totalfinalprice);
		totalfinalprice = totalfinalprice.substring(1).trim();
		double doubletotalfinalprice = Double.parseDouble(totalfinalprice);
		
		//assigning final price to global variable
		finalcheckprice = doubletotalfinalprice;

		// Validating initial and final price by calling vaidatePrice() function
		validatePrice(doublebeforesummaryprice, doubletotalfinalprice);

	}

	// 9. Proceed to checkout
	public static void checkOut()
	{
		driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();
		// address checkout
		driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
		// shipping
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		//clicking on terms and  conditions
		driver.findElement(By.xpath("//button[@class='button btn btn-default standard-checkout button-medium']"))
				.click();
		// Capturing post shipping price
		String postshippingprice = driver.findElement(By.xpath("//td/span[@id = 'total_price']")).getText();
		// Printing post shipping price
		System.out.println("Post Shipping price : " + postshippingprice);
		// clicking on pay by check
		driver.findElement(By.xpath("//div//a[@class='cheque']")).click();
		// Confirming order
		driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click();
		// Displaying order confirmation
		String confirmation = driver.findElement(By.xpath("//p[@class ='alert alert-success']")).getText();
		System.out.println(confirmation);
	}
	

	// 10. Finding reference ID
	public static void findReference() {
		String referenceLine = "";
		WebElement element = driver.findElement(By.xpath("//div[@class = 'box order-confirmation']"));
		String paragraph = element.getText();
		String[] lines = paragraph.split("-");
		for (String line : lines) {
			if (line.contains("reference")) {
				referenceLine = line.trim();
				//System.out.println(line);
				break;
			}
		}
		String[] referenceArray = referenceLine.split(" ");
		String referenceId = referenceArray[8];
		referenceId = referenceId.substring(0,9);
		System.out.println("Reference ID : " + referenceId);

	}

	// 11. Signing Out
	public static void signOut() {
		driver.findElement(By.xpath("//a[@class='logout']")).click();
	}

}
