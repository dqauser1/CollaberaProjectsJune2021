package com.cognixia.training.CollaberaProjectsJune2021.Team2;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AutomationPractice_TestNG extends AutomationPractice_Utility {

	@BeforeTest
	public void beforeTest() {
		openSite();
		signIn();
	}

	@Test
	public void mainTest() throws IOException {
		// Search product in the search box
		searchProduct();

		// clicking drop-down and selecting lowest
		clickDropdown();

		// Hovering on first product
		hoverOver();

		// Printing Initial price of first product
		checkInitialPrice();

		// Capturing screenshots of corresponding images of first product
		captureImages();

		// Capturing and Comparing add to cart price and total final price
		checkTotalPrice();

		// navigating to summary, sign-In, Address, Shipping, Payment
		checkOut();

		// capturing reference ID of the selected product
		findReference();
	}

	@AfterTest
	public void tearDown() {
		signOut();
		driver.close();
		driver.quit();
	}
}
