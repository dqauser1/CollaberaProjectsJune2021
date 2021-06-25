package com.cognixia.training.CollaberaProjectsJune2021.Team1;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class bigBasketBase {
	


	
	
		
		protected WebDriver driver;
		protected WebElement searchbox;
		protected WebElement searchbutton;
		protected String browser;
		
		@BeforeTest
		protected void openBrowser() {
			browser = "Chrome";
			System.setProperty("webdriver.chrome.driver", "C:\\\\Tools\\\\Selenium\\\\chromedriver.exe"); //Windows OS
			//The following path is valid for MAC and Linux OS
			//System.setProperty("webdriver.chrome.driver", "C:\\\\\\\\Tools\\\\\\\\Selenium\\\\\\\\chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "C:\\\\Tools\\\\Selenium\\\\geckodriver.exe");
			switch (browser) {
				case "Firefox":
					driver = new FirefoxDriver();
					break;
				case "Chrome":
					driver = new ChromeDriver();
					break;
				default:
					System.out.println("You requested for another browser which is not available. Hence running this script on Google Chrome");
					driver = new ChromeDriver();
					break;
			}
		
				 
				driver.get("https://www.bigbasket.com");
				 //To Select the Location
				waitForTitleToContain("Online Grocery Shopping ");
				 
				WebElement location= driver.findElement(By.xpath("//*[@ng-click='vm.locdropdown()']"));
			    location.click();
				WebElement searchLocation= driver.findElement(By.xpath("//*[@placeholder='Select your city']"));
				searchLocation.click();
				 
				WebElement selectBanglore= driver.findElement(By.xpath("(//*[@class='ng-binding ng-scope'][contains(text(),'Bangalore')])[2]"));
				selectBanglore.click();
				 
				WebElement continueButton= driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
				continueButton.click();
				 
				
			 
		   driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		@AfterTest
		protected void tearDown() {
			System.out.println("Automation Test Completed By Team 1  ");
			driver.quit();
		}

		
		
		void screenShot() throws IOException {
			File f;
			f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File("screenshotBigBasket/bigbasket.jpg"));
			
		}

	
		protected void waitForTextToBePresentInElement(By e, String text) {
			WebDriverWait myWait = new WebDriverWait(driver, 30);
			myWait.until(ExpectedConditions.textToBePresentInElementLocated(e, text));
			
		}
		
		protected void waitForTitleToContain(String searchstring) {
			WebDriverWait myWait = new WebDriverWait(driver, 30 );
			myWait.until(ExpectedConditions.titleContains(searchstring));
		}
		
		
		
	}

