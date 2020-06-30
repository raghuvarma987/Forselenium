package PackageForBeforeSuit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

//import Amz.utility.Util;
//import Amz.utility.WebEventListener;

public class BaseClass {
	public static WebDriver	driver;
	public static Properties	prop;
	public static EventFiringWebDriver	e_driver;

//	public static WebEventListener	eventListener;
	
	
	public BaseClass() {
		try {
		prop = new Properties();
		
			FileInputStream ip = new FileInputStream("R:\\New Aut\\CloudPR_forCanada\\src\\main\\java\\CloudPR\\Config\\urlpath.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
			e.printStackTrace();
			}catch (IOException e) {
			e.printStackTrace();
			}
       	}
		@BeforeSuite
		public static void initialization() {
			String browsername = prop.getProperty("browser");
			if(browsername.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", 
						"R:\\Testing\\Java\\chromedriver 71\\chromedriver.exe");
			    driver = new ChromeDriver();
				driver.get("http://www.google.co.in");
			}else {
				System.out.println("ff");
			}
			e_driver = new EventFiringWebDriver(driver);
			//to create obj to event listener handler it register with event firing 
			/*eventListener = new WebEventListener();
			e_driver.register(eventListener);
			driver = e_driver;*/
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//driver.manage().timeouts().pageLoadTimeout(Util.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//	driver.manage().timeouts().implicitlyWait(Util.PAGE_IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(prop.getProperty("URL"));
			driver.findElement(By.xpath("//*[@id=\"EmailAddress\"]")).sendKeys("raghu.chennu44@gmail.com");
			driver.findElement(By.xpath("//*[@placeholder='Password']")).sendKeys("Raghu@123");
			driver.findElement(By.xpath("//*[text()='SIGN IN']")).click();
		}
		@AfterSuite
	    public void afterSuite() {
	        if(null != driver) {
	            driver.close();
	            driver.quit();
	        }
	

}
}
