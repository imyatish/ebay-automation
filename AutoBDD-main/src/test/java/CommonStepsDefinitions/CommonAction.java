package CommonStepsDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class CommonAction {
	
	static int waitFactor = 1;

	//*********Below all are common action methods use to perform multiple Actions*********
	
	/**
	 * Purpose-To return a webelement based on the identifier passed
	 * @param webdriver
	 * @param identifierType - like xpath/css/id
	 * @param identifier - value of xpath/id/linktext
	 * @return
	 */
	
	public static WebElement getWebElement(String identifierType,String identifier) throws Exception{
		 WebDriver driver=DriverFactory.getDriver();
		try{ 
			switch(identifierType.toLowerCase()){ 
			case "xpath": return driver.findElement(By.xpath(identifier)); 
			case "id": return driver.findElement(By.id(identifier));
			default:failTest("Identifier type"+identifierType+" invalid for method getWebElement"); } 
			System.out.println("out of getWebElement");}
		catch(Exception e){
			failTest("Unable to find webelement "+identifier+" using "+identifierType);
		}
		System.out.println("");
		return null;	
	}
	
	/**
	 * Fails test with given message 
	 * @param message the assertion error message 
	 */
	 public static void failTest(String message ) {
		 throw new AssertionError(message); 
	 }
	 
	 /**
		 * Method Name: scrollToElement Purpose: To scroll for view the element
		 * 
		 * @param WebDriver the driver is used to instantiate web browser
		 * @param WebElement whose scroll function is being checked
		 * @throws Exception
		 */
		public static void scrollToElement(WebElement element) throws Exception{
			 WebDriver driver=DriverFactory.getDriver();
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				// js.executeScript("window.scrollBy(0,500)");
			} catch (Exception e) {
				e.printStackTrace();
				failTest("Exception while perform scroll to view Elements");

			}
		}
		
		/**
		 * Method Name:javaScriptEnterValue Purpose: Enter value in text field using javaScript
		 * 
		 * @param WebDriver the driver is used to instantiate web browser
		 * @param ele_it is used to represent an HTML element
		 * @param Value_used to enter in TextField
		 * @throws Exception
		 */
		public static void javaScriptEnterValue(WebElement ele,String value) throws Exception {
			 WebDriver driver=DriverFactory.getDriver();
			Thread.sleep(2000);
			try {

				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value='" + value + "';", ele);
			} catch (Exception e) {
				e.printStackTrace();
				failTest("Exception while entering Text for Element using javascript " + ele);
			}
		} 
		/**
		 * Method Name - javaScriptClick Purpose - Click using javaScript executor
		 * 
		 * @param WebDriver the driver is used to instantiate web browser
		 * @param ele_it is used to represent an HTML element
		 * @throws Exception
		 */
		public static void javaScriptClick(WebElement ele ) throws Exception {
			 WebDriver driver=DriverFactory.getDriver();
			System.out.println("Inside click ele"+ele);
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", ele);
				System.out.println("outside click");
			} catch (Exception e) {
				e.printStackTrace();
				failTest("Exception while clicking on element using javascript click" + ele);
			}
			
			
		}
		
		/**
		 * MethodName - selectDropdownByVisibleText Purpose - select one of the options in a drop-down box
		 * 
		 * @param WebDriver the driver is used to instantiate web browser
		 * @param dropdownObject_WebElement the dropdownObject is used to represent an HTML element
		 * @param dropdownVal _ It takes a parameter of String which is one of the values of Select element
		 * @throws Exception
		 */
		public static void selectValueByVisibleText(WebElement dropdownObject, String dropdownVal) throws Exception {
			System.out.println(" Inside selectValueByvisibleText");
			dropdownObject.click();
			Select dropdown = new Select(dropdownObject);
			try {
				dropdown.selectByVisibleText(dropdownVal);

			} catch (Exception e) {
				failTest("Expected Value " + dropdownVal + " is not displayed.");
			}

		}
		/**
		 * Method Name:click Purpose: click on an UI element
		 * 
		 * @param WebDriver the driver is used to instantiate web browser
		 * @param ele_it is used to represent an HTML element
		 * @throws Exception
		 */
		public static void click(WebElement ele) throws Exception {
			System.out.println("inside click");
			try {
				ele.click();
				System.out.println("out of click");
			} catch (Exception e) {
				failTest("Exception while clicking on element" + ele);
				}
			}
		
		
		
        // Wait for the element to be visible
		public static void waitForElementToBeVisible(WebElement element) {
			 WebDriver driver=DriverFactory.getDriver();
			WebDriverWait wait = new WebDriverWait(driver,20);
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }
		
		
		
	

}
