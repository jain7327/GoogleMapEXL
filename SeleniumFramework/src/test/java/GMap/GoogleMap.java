package GMap;

import org.testng.annotations.Test;

import Core.BaseTest;
import Pages.GoogleMapPage;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class GoogleMap extends BaseTest {
	
	GoogleMapPage googleMapPage;

	
@BeforeMethod
  public void setup() {
	 driver  = initialize();
	googleMapPage  = PageFactory.initElements(driver, GoogleMapPage.class);
  }
	
  @Test
  public void f() throws IOException {	  
	//open url
		 openUrl("url");
		 sleep(5000);
		
		 //maximize windows
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		  WebDriverWait wait = new WebDriverWait(driver,10);
		  wait.until(ExpectedConditions.visibilityOf(googleMapPage.searchGoogleMapsTxtBox));
		 
		 googleMapPage.searchGoogleMapsTxtBox.click();		 
		 googleMapPage.searchGoogleMapsTxtBox.sendKeys("San Francisco, California");		  
		
		 googleMapPage.searchBtn.click();
		 sleep(5000);
		 String currentUrl = driver.getCurrentUrl();
		 sleep(5000);
		
		 // validating coordinates
		 Assert.assertTrue(currentUrl.contains("37.757aa"),"coordinates not matched");

		 googleMapPage.directionBtn.click();
		 googleMapPage.chooseStartingPoint.sendKeys("Chico CA");
		 
		 googleMapPage.search.get(0).click();		
		 
		 googleMapPage.driveBycar.click();
		
		 int numberOfRoutes = googleMapPage.routes.size();
		// validating number of routes
		 Assert.assertTrue(numberOfRoutes>=2,"number of route are less then 2");
		 int titlecount = googleMapPage.routesTitle.size();
		
		 File file = new File("routes.txt");
		 if(!file.exists()) {
				// creating new file
				file.createNewFile();
		 }
		 PrintWriter pw = new PrintWriter(file);
		 for( int i=0; i<titlecount; i++) {
			//saving data in file
			 pw.println("Title = "+googleMapPage.routesTitle.get(i).getText());
			 pw.println("Time = "+googleMapPage.routesTime.get(i).getText());
			 pw.println("Distence = "+googleMapPage.routesDistence.get(i).getText());
			 pw.println("");
		 }
		 
		 pw.close();
		 System.out.println("Done");
  }
  

  @AfterMethod
	public void tearDown(ITestResult result) {
	  //capturing screenshot for failure
		captureScreenshot(result);
		driver.close();
	}
}
