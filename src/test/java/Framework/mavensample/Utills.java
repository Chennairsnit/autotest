package Framework.mavensample;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utills {
		
	WebDriver driver;
	
	public void explicit () {	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	// Wait until the element is visibly ready to be clicked
	WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit-btn")));
	button.click();
	
	}
	public void fluentWait () {
		
		FluentWait<WebDriver> wait = new FluentWait<>(driver)
			    .withTimeout(Duration.ofSeconds(30))      // Max timeout
			    .pollingEvery(Duration.ofSeconds(2))     // Check every 2 seconds
			    .ignoring(NoSuchElementException.class);  // Ignore this exception during the loop

			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamic-id")));	
	}
	
	public void implicitlyWait () {
		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	public void ac () {
	
	Actions actions = new Actions(driver);
    
    // Example A: Simulating a Mouse Hover and Click
    WebElement menu = driver.findElement(By.id("menu-id"));
    WebElement subMenu = driver.findElement(By.id("submenu-id"));
    
    actions.moveToElement(menu)
           .moveToElement(subMenu)
           .click()
           .perform(); // Executes the chain
    
    // Example B: Keyboard Shortcut (e.g., Ctrl + A to Select All text)
    WebElement textField = driver.findElement(By.id("username"));
    
    actions.click(textField)
           .keyDown(org.openqa.selenium.Keys.CONTROL)
           .sendKeys("a")
           .keyUp(org.openqa.selenium.Keys.CONTROL)
           .perform();
	}
	public void se () {
	
	WebElement countryDropdown = driver.findElement(By.id("country"));

    // 2. Wrap it with the Select class
    Select selectCountry = new Select(countryDropdown);

    // 3. Select an option using any of the available strategies
    selectCountry.selectByVisibleText("Canada"); 
    selectCountry.deselectByIndex(0);
    selectCountry.selectByValue("india");
	}
	
	public void iframe () {
	driver.switchTo().frame("frame-id-or-name");// Switch to the frame using its ID or Name attribute

	// Interact with elements inside the frame
	driver.findElement(By.id("username")).sendKeys("test_user");
	
	WebElement modernFrame = driver.findElement(By.cssSelector("iframe.src*='login'"));

	// Switch to the frame
	driver.switchTo().frame(modernFrame);	// Locate the frame as a WebElement
	driver.switchTo().frame(0);// Switch to the first frame on the page
	}
	
	public void window () {
	// 1. Store the current (parent) window handle
	String parentWindow = driver.getWindowHandle();

	// 2. Perform action that opens a new window
	driver.findElement(By.id("open-tab-btn")).click();

	// 3. Get all open window handles
	Set<String> allWindows = driver.getWindowHandles();

	// 4. Loop through handles to switch to the child window
	for (String windowHandle : allWindows) {
	    if (!windowHandle.equals(parentWindow)) {
	        driver.switchTo().window(windowHandle);
	        break; // Focus shifted to the child window
	    }
	}

	// Perform actions inside the new window...
	System.out.println("Child Window Title: " + driver.getTitle());

	// 5. Close child window and switch back to parent
	driver.close(); 
	driver.switchTo().window(parentWindow);
	}
	public void tab() {
	
	// Opens a brand new blank tab and automatically switches focus to it
	driver.switchTo().newWindow(WindowType.TAB);
	driver.get("https://google.com");

	// Opens a brand new separate browser window and switches focus to it
	driver.switchTo().newWindow(WindowType.WINDOW);
	driver.get("https://bing.com");
	
	}
	public void tabs() {
	
		try {
        // 1. Cast WebDriver to TakesScreenshot
        TakesScreenshot ts = (TakesScreenshot) driver;

        // 2. Capture screenshot as a temporary file object
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        // 3. Define the destination path 
        File destinationFile = new File("./screenshots/homepage.png");

        // 4. Copy the file to the destination folder
        Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Screenshot saved successfully!");

    } catch (IOException e) {
        System.out.println("Failed to save screenshot: " + e.getMessage());
    } finally {
        driver.quit();
       // driver.quit();
			ok
        // driver.quit();
    }
		
	}
}

