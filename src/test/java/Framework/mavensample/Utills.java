package Framework.mavensample;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

import com.google.common.collect.Table.Cell;

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
		driver.switchTo().frame("iframe_id_or_name"); //name or id 
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
	public void TakesScreenshot() {
	
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

    }
		
		List<WebElement>elements = driver.findElements(By.id(""));
			
		for (int i = 0; i < elements.size(); i++) {
			
			elements.get(i).click();
		    WebElement element = elements.get(i);
		    System.out.println("Element " + i + ": " + element.getText());
		    
		    
		}
	}
		public void StoreElements() {
		   
		        List<WebElement> links = driver.findElements(By.tagName("a"));

		        // 2. Output the total number of elements found
		        System.out.println("Total links found: " + links.size());

		        // 3. Iterate through the list to interact with or extract data from each element
		        for (WebElement link : links) {
		            System.out.println("Link Text: " + link.getText());
		        } 
	}
		
		
		public  void store() {
			
			
			  String[] fruits = {"Apple", "Banana"};
		      String[] colors = {"Red", "Yellow"};
	        // Create a List that explicitly stores String arrays
	        List<String[]> listOfArrays = new ArrayList<>();

	     
	        // Store the arrays in the list
	        listOfArrays.add(fruits);
	        listOfArrays.add(colors);

	        // Retrieve and print an array from the list
	        String[] retrievedArray = listOfArrays.get(0);
	        System.out.println(Arrays.toString(retrievedArray)); // Output: [Apple, Banana]
	    
	}


		public void JavascriptExecutor() {
			// 1. Typecast the driver instance
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        
	        driver.get("https://example.com");
	
	        // 2. Locate an element using standard Selenium
	        WebElement element = driver.findElement(By.id("submit-btn"));
	
	        // 3. Execute JavaScript on that element
	        js.executeScript("arguments[0].click();", element);
	        
	        //sendkey
	        js.executeScript("arguments[0].value='Your Text Here';", element);
	        
	        String pageTitle = js.executeScript("return document.title;").toString();
	       
	        //Highlight an Element (Debugging)
	        js.executeScript("arguments[0].style.border='3px solid red'", element);
	        //Scroll down by a Specific Pixel Amount
	        js.executeScript("window.scrollBy(0, 1000);");
	        //scroll Directly to the Bottom of the Page
	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        //Scroll Until a Specific Element is Visible
	        
	        js.executeScript("arguments[0].scrollIntoView(true);", element);
	        
    }
		public void windowindex() {
			List<String> handles = new ArrayList<>(driver.getWindowHandles());
	
			// Switch to window at index 1 (second tab/window)
			driver.switchTo().window(handles.get(1));
			}
			
		public void tryone() {
			try {
			    WebElement banner = driver.findElement(By.id("promo-banner"));
			    banner.click();
			} catch (NoSuchElementException e) {
			    System.out.println("Promo banner did not appear, continuing execution.");
			}
			}
		public void trytwo() {
			try {
			    Alert alert = driver.switchTo().alert();
			    alert.accept();
			} catch (NoAlertPresentException e) {
			    System.out.println("No pop-up alert present.");
			}
		}
		public void returntype() {
			
	    public ElementUtils(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Returns String (e.g., extracting text)
	    public String getElementText(By locator) {
	        return driver.findElement(locator).getText();
	    }

	    // Returns String (e.g., getting input value or attribute)
	    public String getAttributeValue(By locator, String attributeName) {
	        return driver.findElement(locator).getAttribute(attributeName);
	    }

	    // Returns boolean (e.g., checking visibility or state)
	    public boolean isElementDisplayed(By locator) {
	        try {
	            return driver.findElement(locator).isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
	}
		public void printAllTableData() {
		    List<WebElement> rows = driver.findElements(By.xpath("//table[@id='employeeTable']/tbody/tr"));

		    for (int i = 1; i <= rows.size(); i++) {
		        // Get all columns in the current row
		        List<WebElement> cols = driver.findElements(By.xpath("//table[@id='employeeTable']/tbody/tr[" + i + "]/td"));

		        for (int j = 1; j <= cols.size(); j++) {
		            String cellText = driver.findElement(By.xpath("//table[@id='employeeTable']/tbody/tr[" + i + "]/td[" + j + "]")).getText();
		            System.out.print(cellText + " | ");
		        }
		        System.out.println(); // New line for next row
		    }
		}
		
	
		 public void updateSpecificColumns(String filePath, String sheetName, int targetColIndex, String newValue) {
		        try (FileInputStream fis = new FileInputStream(filePath);
		             Workbook workbook = new XSSFWorkbook(fis)) {

		            Sheet sheet = workbook.getSheet(sheetName);
		            int rowCount = sheet.getLastRowNum(); // 600 entries

		            for (int i = 1; i <= rowCount; i++) { // Skip header row 0
		                Row row = sheet.getRow(i);
		                if (row == null) {
		                    row = sheet.createRow(i);
		                }

		                // Get existing cell or create a new one
		                Cell cell = row.getCell(targetColIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		                
		                // Update value for this specific column
		                cell.setCellValue(newValue + " - " + i);
		            }

		            // Write updates back to the file
		            try (FileOutputStream fos = new FileOutputStream(filePath)) {
		                workbook.write(fos);
		            }

		            System.out.println("Successfully updated column " + targetColIndex + " across " + rowCount + " rows.");

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		
}


