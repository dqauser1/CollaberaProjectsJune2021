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

public class TestFlipKartBase {
	


	
	
		
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
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		
		@AfterTest
		protected void tearDown() {
			driver.quit();
		}

		@BeforeMethod
		protected void resetBrowser() {
			driver.get("https://www.flipkart.com");
			 WebElement parent= driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
			 parent.click();
			 String searchstring = "fitbit";
			   WebElement searchbox= driver.findElement(By.name("q"));
			   searchbox.sendKeys(searchstring);
//			searchbutton = driver.findElement(By.name("btnK"));
			 WebElement search= driver.findElement(By.xpath("//button[@class='L0Z3Pu']"));
        	 search.click();
		}
		void screenShot() throws IOException {
			File f;
			f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File("screenshot/product.jpg"));
			
		}
//		protected void waitForSuggestionsToLoad(String searchstring) {
//			WebDriverWait myWait = new WebDriverWait(driver, 5);
//			myWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//li[@data-view-type='1']//div[@role='option']"), searchstring));
//		}
//		
//		protected void waitForTextToBePresentInElement(By e, String text) {
//			WebDriverWait myWait = new WebDriverWait(driver, 5);
//			myWait.until(ExpectedConditions.textToBePresentInElementLocated(e, text));
//			
//		}
//		
//		protected void waitForTitleToContain(String searchstring) {
//			WebDriverWait myWait = new WebDriverWait(driver, 5);
//			myWait.until(ExpectedConditions.titleContains(searchstring));
//		}
		
		
		
	}

