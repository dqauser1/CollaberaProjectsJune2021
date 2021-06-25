package com.cognixia.training.CollaberaProjectsJune2021.Team1;

import java.io.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;








public class TestNgFlipkartAssignment extends TestFlipKartBase{
    
	  @Test
	  //test 1
		   public void testFirst() throws IOException, InterruptedException {
//		range
		    selectRange();
		    numberOfItems();
	        windowHandle();
			screenShot();
			calculation();
		 } 
 
	private void windowHandle() {
		// TODO Auto-generated method stub
		 WebElement firstlink= driver.findElement(By.xpath("//a[@class='s1Q9rs']"));
		 firstlink.click();
			
			Set<String> handles = driver.getWindowHandles();
			System.out.println("No. of windows open: "+handles.size());
			
			Iterator<String> iter = handles.iterator();
			
			while(iter.hasNext()) 
			{
				String handle = iter.next();
				
				driver.switchTo().window(handle);
				
				if(driver.getTitle().contains("Online Shopping India")) 
				{
					break;
				}
			}
	}

	private void numberOfItems() {
		// TODO Auto-generated method stub
		String text = driver.findElement(By.className("_10Ermr")).getText();
	       
	       int startingindex = text.indexOf("of");
	       int endingindex = text.indexOf("results");
	       
	       String numberofitems = text.substring(startingindex+3, endingindex-1);
	       
	       Integer searchresult=Integer.parseInt(numberofitems);
	     Assert.assertTrue(searchresult<900);
//	       if(searchresult>900) 
//	       {
//	    	   System.out.println("Total no. of results is more than 900,Total items are : "+searchresult);
//	       }
//	       else 
//	       {
//	    	   System.out.println("Total no. of results is less than 900, Total items are : "+searchresult);
//	       }
	}

	private void selectRange() throws InterruptedException {
		// TODO Auto-generated method stub
		 Actions myActions= new Actions(driver);
		  
			
		   WebElement lefthandle=driver.findElement(By.xpath("//*[@class='HQL4QS _28DFQy']"));
		
		   myActions.dragAndDropBy(lefthandle,67,0).build().perform();
		   WebElement Righthandle=driver.findElement(By.xpath("//*[@class='HQL4QS WC_zGJ']"));
		   myActions.dragAndDropBy(Righthandle, -67,0).build().perform();
		   Thread.sleep(3000);
	}
      void calculation() {
    	  System.out.println("handles successful");
			WebElement finalprice= driver.findElement(By.xpath("//*[@class='_30jeq3 _16Jk6d']"));
			
			String Finalpric=finalprice.getText();
			System.out.println("Final price is :  "+Finalpric);
			WebElement actualPrice= driver.findElement(By.xpath("//*[@class='_3I9_wc _2p6lqe']"));
			
			String ActualPrice=actualPrice.getText();
			System.out.println("Actual price is :  "+ActualPrice);
			
		    String  Finalprice= Finalpric.replace("₹","");
			Finalprice=Finalprice.replace(",","");
			
			Float Final=Float.parseFloat(Finalprice);
			System.out.println("Final in : "+Final);
			
			ActualPrice= ActualPrice.replace("₹","");
			ActualPrice=ActualPrice.replace(",","");
			
			Float Actual=Float.parseFloat(ActualPrice);
		    System.out.println("Actual in : "+Actual);
		    
		    int discount=(int) (((Actual-Final)/Actual)*100f);
		    
		    System.out.println("Discount Percent is: "+discount+" %");
		    String s=String.valueOf(discount);  
		    

		    String percent = driver.findElement(By.xpath("//div[@class='_3Ay6Sb _31Dcoz']//span[1]")).getText();
		    percent=percent.replace(" %","");
		  
		    if(percent.contains(s))
		    {
		    	System.out.println("Discount that is written in site is correct ");
		    }
		    else 
		    {
		    	System.out.println("Discount that is written in site is not correct ");
	        }
		  
		    driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww' and text()='ADD TO CART']")).click(); 
//		    WebElement e = driver.findElement(By.xpath("//span[@class='_3aPjap']"));	
//		    WebDriverWait myWait = new WebDriverWait(driver, 10);
//		    myWait.until(ExpectedConditions.textToBePresentInElement(e,"PRICE DETAILS"));
		    String cartPrice = driver.findElement(By.xpath("//div[@class='Ob17DV _3X7Jj1']//span[1]")).getText();
		       
		       
		    if(Finalpric.equalsIgnoreCase(cartPrice))
		    {
		    	   System.out.println("Final price equal to cart price" );
		    }
		       
		    else
		    {
		    	   System.out.println("Final price not equal to cart price");
		    }
      }

}	

	

	
		   
     
  

	
