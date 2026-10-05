package Framework.mavensample;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LaunchBrowser {
	
	
	static WebDriver driver;
	static Properties prop;
	
	public static void main(String[] args) {
		
		LaunchBrowser lb = new LaunchBrowser();
		lb.init_properties();
		lb.init_driver(prop);
		
	}
	
	public Properties init_properties()
	{
	prop = new Properties();
	try {
		
		
	FileInputStream ip = new FileInputStream("C:\\Users\\Admin\\eclipse-workspace\\Framework\\Config.properties");
	prop.load(ip);
	
	System.out.println(ip);
	} catch (FileNotFoundException e) {
	e.printStackTrace();
	}catch (IOException e)
	{
	e.printStackTrace();
	}

	return prop;


	}

	
		public WebDriver init_driver(Properties prop)
		
		{
		String browser = prop.getProperty("browser");

		if(browser.equals("chrome"))
		{

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		}else if(browser.equals("firefox"))
		{

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		}
		else
		{
		System.out.println("Please provide a proper browser value..");
		}

		driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));

		return driver;
		}
}
		public void  LoginTest() {
		    
		    @BeforeClass
		    public void setUp() {
		        // 1. Initialize the browser once before any test runs in this class
		        driver = new ChromeDriver();
		        driver.manage().window().maximize();
		        System.out.println("--- Browser Launched ---");
		    }

		    @BeforeMethod
		    public void navigateToApp() {
		        // 2. Refresh or navigate to the base URL before every single test case
		        driver.get("https://example.com/login");
		        System.out.println("Navigated to Login Page");
		    }

		    @Test(priority = 1)
		    public void verifyPageTitle() {
		        // 3. Test Case 1: Validate page title
		        String expectedTitle = "Login | Example App";
		        String actualTitle = driver.getTitle();
		        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
		    }

		    @Test(priority = 2)
		    public void invalidLoginTest() {
		        // 4. Test Case 2: Validate invalid login flow
		        driver.findElement(By.id("username")).sendKeys("wrong_user");
		        driver.findElement(By.id("password")).sendKeys("wrong_password");
		        driver.findElement(By.id("loginBtn")).click();
		        
		        boolean errorDisplayed = driver.findElement(By.id("errorMessage")).isDisplayed();
		        Assert.assertTrue(errorDisplayed, "Error message was not displayed for wrong credentials.");
		    }

		    @AfterClass
		    public void tearDown() {
		        // 5. Close and quit the browser cleanly once all tests complete
		        if (driver != null) {
		            driver.quit();
		            System.out.println("--- Browser Closed Cleanly ---");
		        }
		    }
		}

