package com.cognixia.training.CollaberaProjectsJune2021.Team1;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
public class AssignmentClearTrip extends AssignmentBase {
	@Test
	public void testClearTrips() throws InterruptedException, IOException {
		
	    // user should be selected the Trip Type, Date of travel and Class OfTravel
		selectRoundTrip();
	   
		//Capturing the ScreenShot
		captureScreenshoot();
		
		//Selecting the Non-Stop Flights And then Capturing the ScreenShot
		selectNonStopFlights();
		
		//Verifies The TotalPare to the fare Price after Booking
		verifiedFarePrice();
		
	}    
	private void verifiedFarePrice() throws InterruptedException {
		//Capture Total Price of Ticket
		WebElement finalCost =driver.findElement(By.xpath("//*[@class='c-neutral-900 mx-4  fw-700 flex flex-right fs-7']"));
		String finalPrice=finalCost.getText();
		System.out.println("Total Price Brfore Booking :  "+finalPrice.replace("₹","").replace(",",""));
		
		//Capture Only Return Price
		WebElement captureReturnPrice = driver.findElement(By.xpath("(//div[@class='flex flex-middle']//p[@class='m-0 fs-caption-2 fw-600 c-secondary-500'])[2]"));
		String returnPrice = captureReturnPrice.getText();
		System.out.println("Return Price :"+ returnPrice.replace("₹","").replace(",",""));
	    
		windowHandle();
	    waitForTitleToContain("#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
	    
	    //Capture the Cost in Page 2
	    WebElement totalcost =driver.findElement(By.xpath("//*[@class='fs-6 fw-700']"));
	    String totalPrice=totalcost.getText();
		System.out.println("Total price is :  "+totalPrice.replace("₹","").replace(",",""));
		
		//Condition for verify
		Assert.assertEquals(totalPrice, finalPrice);
		System.out.println("Ticket Price is correct and verified");
		
		Thread.sleep(3000);
		
		//Click on Standard Fare
		WebElement standardFare= driver.findElement(By.xpath("//*[@class='fs-4 c-neutral-900 fw-400']"));
		standardFare.click();
		
		
		//click on continue 
		WebElement clickOnContinueButton= driver.findElement(By.xpath("//*[@class='px-7 bg-primary-500 hover:bg-primary-600 c-white bc-transparent c-pointer w-100p py-2 px-5 h-10 fs-4 fw-600 t-all button bs-solid tp-color td-500 bw-1 br-4 lh-solid box-border']"));
		clickOnContinueButton.click();
		
		By e = By.xpath("//*[@class='fs-7 px-4 c-neutral-900 fw-600'][contains(text(),'Add contact details')]");
		waitForTextToBePresentInElement(e,"Add contact details");
		
		//Last verification for the TotalFare
		WebElement farecost =driver.findElement(By.xpath("//*[@class='fs-6 fw-700']"));
		String farePrice=farecost.getText();
		System.out.println("Total fare cost is :  "+farePrice.replace("₹","").replace(",",""));
		
		//Condition for verify
		Assert.assertEquals(totalPrice, farePrice);
		System.out.println("Ticket fare Price is correct");

}


	private void selectNonStopFlights() throws IOException {
		//select non stop flights
        WebElement selectNonStop= driver.findElement(By.xpath("(//div[@class='pt-3'])[1]/label/div"));
        selectNonStop.click();
        
        // Screenshoot of Non-Stop Flights
        screenShot2();
        
        // Number Of Flights On Each Leg
        int findFlightsOnLeftLeg =driver.findElements(By.xpath("//*[@data-test-attrib='onward-view']//*[@class='rt-tuple-container__details__time ms-grid-column-2']")).size();
        System.out.println("No Of Flights From Delhi to Mumbai = "+findFlightsOnLeftLeg);
        
        int findFlightsOnRightLeg =driver.findElements(By.xpath("//*[@data-test-attrib='return-view']//*[@class='rt-tuple-container__details__time ms-grid-column-2']")).size();
        System.out.println("No Of Flights From Mumbai to Delhi = "+findFlightsOnRightLeg);

//		
	}

	private void captureScreenshoot() throws IOException {
		waitForTitleToContain("New Delhi");
	    // Screenshoot of All Flights
	    screenShot();
	}

	private void selectRoundTrip() throws InterruptedException {
		// 2 Select Round Trip
		waitForTitleToContain("#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
		WebElement clickOnRoundTrip =driver.findElement(By.xpath("//*[@class='fs-3 fw-600 c-neutral-900'][contains(text(),'Round trip')]"));
		clickOnRoundTrip.click();
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,500)");

		//Waiting For Element To Load
		By e = By.xpath("//*[@class='fs-3 fw-600 c-neutral-900 mb-2 pl-2 pr-2'][contains(text(),'Return on')]");
		waitForTextToBePresentInElement(e,"Return on");

		//Select Flight From : New Delhi
		WebElement dropDownOfSourceCity= driver.findElement(By.xpath("//input[starts-with(@class,'field bw-1 bs-solid')][1]"));
		dropDownOfSourceCity.click();
				   	
		WebElement chooseSourceCity= driver.findElement(By.xpath("//*[@class='to-ellipsis o-hidden ws-nowrap c-inherit fs-3'][contains(text(),'New Delhi, IN - Indira Gandhi Airport (DEL)')]"));
		chooseSourceCity.click();
		
		//Select Flight To : Mumbai
		WebElement dropDownOfDestinationCity= driver.findElement(By.xpath("(//*[@placeholder='Any worldwide city or airport'])[2]"));
		dropDownOfDestinationCity.click();
				   	
		WebElement chooseDestinationCity= driver.findElement(By.xpath("//*[@class='to-ellipsis o-hidden ws-nowrap c-inherit fs-3'][contains(text(),'Mumbai, IN - Chatrapati Shivaji Airport (BOM)')]"));
		chooseDestinationCity.click();
		
		//Dropdown for date selection
		WebElement dateDropDown =driver.findElement(By.xpath("//button[@class='flex flex-middle flex-between t-all fs-2 focus:bc-secondary-500 bg-transparent bc-neutral-100 c-pointer pr-2 pl-3 pt-2 pb-2 ba br-4 h-8 c-neutral-900'][1]"));
		dateDropDown.click();
			       
		//Select Depart Date (1 July)
		WebElement chooseDepartDate= driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[2]/div[3]/div/div[4]"));
		chooseDepartDate.click();
			        
		//Select the ReturnDate
		WebElement chooseReturnDate= driver.findElement(By.xpath("//div[@class='DayPicker-Months']/div[1]/div[3]/div[2]/div[4]"));
		chooseReturnDate.click();
			        
		//click on More Options Link
		WebElement clickOnMoreOptions= driver.findElement(By.xpath("//*[@class='td-none hover:td-underline px-4 mt-1 weak']"));
		clickOnMoreOptions.click();
			
		//Select Business Class
		WebElement selectClassOfTravel= driver.findElement(By.xpath("(//*[@class='bc-neutral-100 bg-transparent'])[4]"));
		selectClassOfTravel.click();
		WebElement clickOnBuisinessClass= driver.findElement(By.xpath("//option[@value='Business']"));
		clickOnBuisinessClass.click();
			        
		//Click on SearchFlight
		WebElement clickOnSearchFlight= driver.findElement(By.xpath("//button[@class='px-7 bg-primary-500 hover:bg-primary-600 c-white bc-transparent c-pointer w-100p py-2 px-5 h-10 fs-4 fw-600 t-all button bs-solid tp-color td-500 bw-1 br-4 lh-solid box-border']"));
		clickOnSearchFlight.click();	
		
	}

	private void windowHandle() {
		
		WebElement bookFlight= driver.findElement(By.xpath("//button[@class='bg-primary-500 hover:bg-primary-600 c-white bc-transparent c-pointer py-1 px-3 h-8 fs-3 fw-600 t-all button bs-solid tp-color td-500 bw-1 br-4 lh-solid box-border']"));
		bookFlight.click();
		
		Set<String> handles = driver.getWindowHandles();
		
		Iterator<String> iter = handles.iterator();
		while(iter.hasNext())
		{
			String handle = iter.next();
			driver.switchTo().window(handle);
			
			if(driver.getTitle().contains("Booking Flights, Hotels, Packages"))
			{
				break;
			}
		}
	}
}