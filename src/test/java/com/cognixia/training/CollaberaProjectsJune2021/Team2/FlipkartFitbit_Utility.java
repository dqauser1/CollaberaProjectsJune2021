package com.cognixia.training.CollaberaProjectsJune2021.Team2;
import java.io.File;
import java.io.IOException;
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

public class FlipkartFitbit_Utility
{
		
		protected static WebDriver driver;
		protected static WebElement searchbox;
		protected static WebElement searchbutton;
		protected static String browser;
		
		@BeforeTest
		  protected static void openBrowser() {		
			browser = "Chrome";
			System.setProperty("webdriver.chrome.driver", "C:\\Tool\\Selenium\\chromedriver.exe"); //Windows OS
			//The following path is valid for MAC and Linux OS
			//System.setProperty("webdriver.chrome.driver", "/Users/ameya/Tools/Selenium/ChromeDriver91/chromedriver");
			System.setProperty("webdriver.gecko.driver", "C:\\Tool\\Selenium\\geckodriver.exe");
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
		protected static void tearDown()
		{
			driver.quit();
		}
		
		
		protected static void takeScreenshot() throws IOException
		{
			File f;
			f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\productdetails.jpg"));
		}
		
		protected static double calculatefinalprice(String discount , String original, String price)
		{
			double discountpercent = Double.parseDouble(discount.substring(0, 2));
			double beforediscount = Double.parseDouble(original);
			double afterdiscount = Double.parseDouble(price);
			double discountamount  = (discountpercent/100)*beforediscount;
			System.out.println("\nbefore : " + beforediscount + "\nafter : " + afterdiscount + "\ndiscount : "
					+ discountpercent + "\ndiscountamount : " + discountamount);
			double discountedprice = beforediscount - discountamount;
			System.out.println("Discounted price :" + discountedprice);

			if (discountedprice == Double.parseDouble(price)) {
				System.out.println("\nDiscount % is correct\n");
			} else {
				System.out.println("\nDiscount % is  NOT correct\n");
			}
			return discountedprice;

		}
		protected static void handlingslider(int leftrange, int rightrange)
		{
			rightrange = (rightrange)*(-1);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement slider = driver.findElement(By.xpath("//div[@class='_2IN3-t _1mRwrD']"));
			
			WebElement leftslider= driver.findElement(By.xpath("//div[@class='HQL4QS _28DFQy']"));
			WebElement rightslider = driver.findElement(By.xpath("//div[@class='HQL4QS WC_zGJ']"));
			
			int slidersize = slider.getSize().width;
			System.out.println("Slidersize = "+slidersize);
			
			Actions myaction = new Actions(driver);
			myaction.dragAndDropBy(leftslider, slidersize/leftrange, 0).build().perform();
			myaction.pause(5000);
			myaction.dragAndDropBy(rightslider, slidersize/(rightrange), 0).build().perform();		
			
		}
	
    protected static void compareFinalpriceAccuracy(String finalprice , double finalcalculatedprice)
    {
    	Double doublefinal = Double.parseDouble(finalprice);
	    System.out.println("final price = " + doublefinal);
	    
	    if(doublefinal  == finalcalculatedprice)
	    {
	    	System.out.println("/nFinal amount is discounted correctly -  Test Passed");
	    }
	    else
	    {
	    	System.out.println("Final amount is NOT  discounted correctly -  Test Failed");
	    }
    }
	
	

}
