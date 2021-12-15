package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Core.BasePage;

public class GoogleMapPage extends BasePage {
	
	
	@FindBy(xpath ="//*[contains(@aria-label,'Search Google Maps')]")
	public WebElement  searchGoogleMapsTxtBox; 
	
	
	@FindBy(xpath ="//button[contains(@id,'searchbox')]")
	public WebElement  searchBtn; 
	
	@FindBy(xpath ="//div[contains(text(),'Directions')]")
	public WebElement  directionBtn; 
	
	
	@FindBy(xpath ="//input[contains(@tooltip,'Choose starting point')]")
	public WebElement  chooseStartingPoint; 

	@FindBy(xpath ="//button[@data-tooltip='Search']")
	public  List<WebElement>  search; 
	
	@FindBy(xpath ="//button[@role='radio']/*[@data-tooltip='Driving']")
	public  WebElement  driveBycar; 
	
	
	@FindBy(xpath ="//div[contains(@id,'section-directions-trip')]")
	public  List<WebElement>  routes; 
	
	@FindBy(xpath ="//h1[contains(@id,'section-directions-trip-title')]")
	public  List<WebElement>  routesTitle; 
	
	@FindBy(xpath ="//*[contains(@aria-labelledby,'section-directions-trip-travel-mode')]//span[contains(text(),'hr')]")
	public  List<WebElement>  routesTime; 
	
	@FindBy(xpath ="//*[contains(@aria-labelledby,'section-directions-trip-travel-mode')]//div[contains(text(),'miles')]")
	public  List<WebElement>  routesDistence; 
	
		
	
}
