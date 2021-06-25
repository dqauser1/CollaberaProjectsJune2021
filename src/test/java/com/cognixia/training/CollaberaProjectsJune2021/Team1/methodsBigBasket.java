package com.cognixia.training.CollaberaProjectsJune2021.Team1;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import com.cognixia.training.CollaberaProjectsJune2021.Team1.BigbasketDatabase;

public class methodsBigBasket extends bigBasketBase {
	

	protected String productname;
	protected int quantity;
	
	
	
	// 6) Verify all the Product In the Basket is Correct Or Not
	protected void verifyItemsOnBasket() throws InterruptedException {
		Thread.sleep(2000);
		String addtocart= driver.findElement(By.xpath("//*[@ng-bind='cartItem.description']")).getText();
		
		Assert.assertTrue(addtocart.contains(productname));
		System.out.println(productname +" is verified ");
		
		String cartQuantity= driver.findElement(By.xpath("//div[@qa='pcsMB']")).getText();
		int lastindex=cartQuantity.indexOf("x");
		cartQuantity=cartQuantity.substring(0,lastindex).trim();
		int cartquantity=Integer. valueOf(cartQuantity);
		Assert.assertEquals(quantity, cartquantity);
		System.out.println(productname +" with "+quantity+" is verified :----------------------");
		System.out.println();
		WebElement remove= driver.findElement(By.xpath("//*[@qa='removeMB']"));
		remove.click();
		By e=By.xpath("//*[@id='totalNumberOfCartItems']");
	    waitForTextToBePresentInElement(e, "0 items");
	    
		
	}

	// 5) Select the Product From Mongo Db DataBase
	protected void searchProduct(String productname_, int quantity_) 
	{
		this.productname=productname_;
		this.quantity=quantity_;
		WebElement searchbox=driver.findElement(By.xpath("(//div[@class='input-group']//input)[1]"));                                            
		searchbox.sendKeys(productname);
		searchbox.sendKeys(Keys.ENTER);
		
		
		WebElement QuantityElement=driver.findElement(By.xpath("(//input[@ng-model='vm.startQuantity'])[1]")); 
	    String FetchDefaultQuantity= QuantityElement.getAttribute("value");
	    int defaultQuantity=Integer.parseInt(FetchDefaultQuantity);
		if(defaultQuantity==quantity)
		{
			  driver.findElement(By.xpath("(//button[@qa='add'])[1]")).click();	  
		  
	    }
		  
		else
		{
		   QuantityElement.clear();
		   QuantityElement.sendKeys(String.valueOf(quantity));
		   driver.findElement(By.xpath("(//button[@qa='add'])[1]")).click();
		}
		  
		
			  	 
	}



	@DataProvider
	protected Object[] getDatafromMongo() throws IOException
	{
		return BigbasketDatabase.readFromMongo("products");
		
	}
	
	// 1) Select the first item from Best Sellers and verified
    protected void selectItemAndVerified() {
		
		By e=By.xpath("//*[@class='section-title ng-binding'][contains(text(),'Best Sellers')]");
    	waitForTextToBePresentInElement(e, "Best Sellers");
    	//click on the add button
    	WebElement addButton= driver.findElement(By.xpath("(//h2[text()='Best Sellers']/../../div[2]//button[@qa='add'])[1]"));
		addButton.click();
		
		e=By.xpath("//*[@class='col-xs-12 bskt-opt ng-scope ng-hide']");
		waitForTextToBePresentInElement(e, "");
		System.out.println("Add button is disabled now after clicking on the add button ");
	
		e=By.xpath("//*[@id='totalNumberOfCartItems']");
	    waitForTextToBePresentInElement(e, "1 items");
	    
		// verifing that the add to cart is updated
		String addtocart= driver.findElement(By.xpath("//*[@id='totalNumberOfCartItems']")).getText();
		Assert.assertTrue(addtocart.contains("1"));
		System.out.println("Number of items is correct in the Basket ");
		
		//Scrolling up
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,-2000)");
		
    }
    
    // 2) Scroll and Hover the mouse to the MyBasket View
    protected void hoverMyBasket() {
		
	    
	    //move the cart mouse
		WebElement element = driver.findElement(By.xpath("//*[@class='icon svg-basket svg-header svg-basket-dim']"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	
    }
    
    // 3)  Verifing the basket Product
    protected void verifyItemInBasket() throws InterruptedException  {
	
	
		String nameOfProduct= driver.findElement(By.xpath("(//h2[text()='Best Sellers']/../../div[2]//a)[2]")).getText();
		System.out.println("PRODUCT THAT IS TO BE ADD IN BASKET IS:- "+nameOfProduct);
		Thread.sleep(3000);
//		By e=By.xpath("//*[@id='totalNumberOfCartItems']");
//	    waitForTextToBePresentInElement(e, "1 items");
	    
		String addtocartProduct= driver.findElement(By.xpath("//*[@qa='prodNameMB']")).getText();
		System.out.println("PRODUCT THAT IS ADDED IN THE BASKET IS:- "+addtocartProduct);
		
		//verifing that the product is same in cart or not		
		Assert.assertTrue(addtocartProduct.contains(nameOfProduct));
		System.out.println("Product that is added is correct in Basket  .");
		
		//Remove the Item
		WebElement remove= driver.findElement(By.xpath("//*[@qa='removeMB']"));
		remove.click();
		By e=By.xpath("//*[@id='totalNumberOfCartItems']");
	    waitForTextToBePresentInElement(e, "0 items");
	
    }

    // 4)  Clicked on CheckoutButton , Capture Screenshot and verify the login Page is popup or not
    protected void loginPopUp() throws IOException {
    	
    	
		
		WebElement checkoutButton= driver.findElement(By.xpath("//*[@qa='viewBasketMB']"));
		checkoutButton.click();
		 
		//verified that pop is come or not
		String popup= driver.findElement(By.xpath("//div[@class='login-main-content']")).getText();
		Assert.assertTrue(popup.contains("LOGIN"));
		System.out.println("LOGIN POPUP IS COMING  .");
		
		screenShot();
		WebElement closeButton= driver.findElement(By.xpath("//*[@class='close']"));
		closeButton.click();
		
		System.out.println("Test one completed successfully   ");
		
    }

}
