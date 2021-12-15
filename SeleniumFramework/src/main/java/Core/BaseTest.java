package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;


public class BaseTest{

	static Properties prop = new Properties();
	static FileInputStream ip;
	static String value; 
	
	
	public WebDriver driver;
	
	public  WebDriver initialize(){
		String browser  = readProperties("browser");
		System.out.println(browser);
		// Load the browser
		
		if(browser.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
		}
		
		if(browser.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		
		// maximize the browser
		driver.manage().window().maximize();
		return driver;
	}
	
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String readProperties(String properties){
		 value = null;
		try {
			ip = new FileInputStream("./Configuration\\config.properties");
			prop.load(ip);
			
			 value  = prop.getProperty(properties);
			System.out.println(prop.getProperty(properties));
		
		}
		catch(Exception e){

		}
		return value;

	}
	
	public  void openUrl(String property) {
		String url  = readProperties(property);
		System.out.println(url);
		
		driver.get(url);
			
		}


	public void captureScreenshot(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
					FileUtils.copyFile(src,
							new File("./FailureScreenshot/" + result.getName() + ".png"));
				} 

			catch (IOException e) {
				System.out.println("Screenshot not captured");
			}

		}

	}


	
}
