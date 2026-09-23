package Framework.mavensample;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


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

