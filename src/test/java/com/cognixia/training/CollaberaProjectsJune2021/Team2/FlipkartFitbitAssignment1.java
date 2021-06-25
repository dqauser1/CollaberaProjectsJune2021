package com.cognixia.training.CollaberaProjectsJune2021.Team2;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartFitbitAssignment1 {

	public static void main(String args[]) throws IOException 
	{
		
		
	 // 1. Opening flipkart.com

		System.setProperty("webdriver.chrome.driver", "C:\\Tool\\Selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://flipkart.com");	
		WebElement pop = driver.findElement(By.xpath("//div[@class = '_2QfC02']/button[contains(text(),x)]"));
        pop.click();
        

	 // 2. Entering fitbit
	
		WebElement searchbox = driver.findElement(By.className("_3704LK"));
		searchbox.sendKeys("fitbit");
		searchbox.sendKeys(Keys.ENTER);
		
		
     // 3. Waiting for products page to load completely
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement pricerange = driver.findElement(By.className("_10Ermr"));

     // 4. Checking if results are more than 900
		
		String str = pricerange.getText().trim();
		String arr[] = str.split(" ");
		String testData = arr[5];

		if (Integer.parseInt(testData) > 900)
		{
			System.out.println("Test Passed , there are more than 900 products\n");
		} 
		else
		{
			System.out.println();
			System.out.println("Test Failed,   there are less than 900 products\n");
		}
		
     // 5. Waiting for 1st product link to load completely
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement link = driver.findElement(By.xpath("//a[@class='s1Q9rs' and contains(@title, 'Versa Special Edition')]"));
		link.click();

	 // 6. Taking ScreenShot
		
		File f;
		f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f,new File("C:\\Users\\lenovo\\Desktop\\Projects\\SeleniumTraining\\Screenshots\\productdetails.jpg"));
		
	 // 7. Finding original price
		
		WebElement originalprice = driver.findElement(By.xpath("//div[@class = '_25b18c']/div[2]"));
		String original = originalprice.getText();
		original = original.substring(1).replace(",", "");
		System.out.println("Original Price : " + original);
		
     // 8. Finding Discounted price
		
		WebElement availableprice = driver.findElement(By.xpath("//div[@class='_25b18c']/div"));
		String price = availableprice.getText();
		price = price.substring(1).replace(",", "");
		System.out.println("Available Price : " + price);

     // 9. Finding Discount percent
		
		WebElement discountper = driver.findElement(By.xpath("//div[@class = '_25b18c']/div[3]"));
		String discount = discountper.getText();
		System.out.println("Discount : " + discount);
		
     // 10. Comparing discounted price with calculated price
		
		double discountpercent = Double.parseDouble(discount.substring(0, 2));
		double beforediscount = Double.parseDouble(original);
		double afterdiscount = Double.parseDouble(price);
		double discountamount = (discountpercent / 100) * (beforediscount);

		System.out.println("\nbefore : " + beforediscount + "\nafter : " + afterdiscount + "\ndiscount : "
				+ discountpercent + "\ndiscountamount : " + discountamount);
		
		double discountedprice = beforediscount - discountamount;
		System.out.println("Discounted price :" + discountedprice);

		if (discountedprice == Double.parseDouble(price)) {
			System.out.println("\nDiscount % is correct\n");
		} else {
			System.out.println("\nDiscount % is  NOT correct\n");
		}

		  
	    Set<String> handles = driver.getWindowHandles();
		int count = 0;
		for(String handle : handles)
		{
			
			count ++;
		     if(count == 2)
		     {
				driver.switchTo().window(handle);
				
				break;
		   }
		     
		}	
	
		
	// 11. Clicking on Add To Cart
		
		driver.findElement(By.xpath("//div[@class  = '_1p3MFP dTTu2M']/ul/li/button")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String handle2 = driver.getWindowHandle();
		driver.switchTo().window(handle2);
		
   
    // 12. Comparing final price with calculateed amount
		
	    String finalprice = driver.findElement(By.xpath("//div[@class ='Ob17DV _3X7Jj1']/span")).getText();
	    finalprice = finalprice.substring(1).replace(",", "");
	    Double doublefinal = Double.parseDouble(finalprice);
	    System.out.println("finl = " + doublefinal);
	    if(doublefinal  == discountedprice)
	    {
	    	System.out.println("/nFinal amount is discounted correctly -  Test Passed");
	    }
	    else
	    {
	    	System.out.println("Final amount is NOT  discounted correctly -  Test Failed");
	    }
	    
	    driver.close();
	    driver.quit();
	    
   
	}		
	
}