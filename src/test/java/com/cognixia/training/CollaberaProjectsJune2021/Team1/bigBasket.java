package com.cognixia.training.CollaberaProjectsJune2021.Team1;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cognixia.training.CollaberaProjectsJune2021.Team1.BigbasketDatabase;

public class bigBasket extends methodsBigBasket  {
	

    @Test
    public void firstTest() throws IOException, InterruptedException {
    	
    	
    	selectItemAndVerified();
    	
    	hoverMyBasket();
    	
		verifyItemInBasket();
        
        loginPopUp();
		
		
		 	 
    }
    
    @Test(dataProvider="getDatafromMongo")
	public void test2(String productname,int quantity) throws InterruptedException
	{
		
		searchProduct(productname,quantity);
		System.out.println(productname +" "+ quantity);
		
		//Scrolling The WebPage to Upward
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");
		 
		 
		hoverMyBasket();
		
		verifyItemsOnBasket();
		
	 
		
	}

}



	

