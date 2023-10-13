package AutomationStepDefinitions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationCucumberTest.TestRunner;
import CommonStepsDefinitions.CommonAction;
import CommonStepsDefinitions.DriverFactory;

public class EbayProduct {
	
    WebDriver driver=DriverFactory.getDriver();
	static String buttonXpath="//button[contains(text(), '%btnName')]";
	static String selectOptionXpath="//table[@id='gh-sbc']/tbody/tr/td/h3/a[contains(text(),'%section')]//parent::h3/following-sibling::ul/li/a[contains(text(),'%option')]";
	static String selectLeftPanelOptionXpath="//a[contains(text(),'%option')]";
	static LinkedHashMap<String,String> expMaps=new LinkedHashMap<String,String>(); 
	static LinkedHashMap<String,String> actUIMaps=new LinkedHashMap<String,String>(); 
	static  String searchInput="";

	
	 

	
	@Given("User lauches Ebay application")  //launch url 
	public void userLauchesApplication() throws Exception{
		//WebDriver driver=DriverFactory.initDriver();
		System.out.println("lauch Ebay Site>>>>>>");
		String expectedURL="https://www.ebay.com/";
	    driver.get(expectedURL);
		driver.manage().timeouts().pageLoadTimeout(40,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		 String currentUrl=driver.getCurrentUrl();
		if(!currentUrl.equalsIgnoreCase(expectedURL)) 
	    	CommonAction.failTest("The current URL does not match the expected URL.");
	}
	@And("User clicks on \"([^\"]*)\"$")
	@When("User Clicks on \"([^\"]*)\" dropdown$") 
	public void clicksOnButton(String btnName) throws Exception {
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath",buttonXpath.replace("%btnName",btnName)));
	}

	@And("Select \"([^\"]*)\" under \"([^\"]*)\" category$")
	public void selectOptionsUnderCategory(String option, String category) throws Exception {
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath",selectOptionXpath.replace("%section",category).replace("%option",option)));
	}

	@And("User clicks on \"([^\"]*)\" in the left-hand navigation section$")
	public void navigateToLeftNavOption(String option) throws Exception { 
		String headerXpath="//h1/span[contains(text(),'%header')]";
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath",selectLeftPanelOptionXpath.replace("%option",option)));
		if(!CommonAction.getWebElement("xpath",headerXpath.replace("%header",option)).isDisplayed())
			CommonAction.failTest("user on different page");
	}

	@When("User applies the Filter Type with Filter Value to access a product")
	public void addTagsToAccessProduct(DataTable fields) throws Exception {
		System.out.println("Inside product search>>>");
		Thread.sleep(5000);
		String filterTag="",tagValue="";
		List<Map<String,String>> keyValueMap=fields.asMaps(String.class,String.class);
		System.out.println("keyValueMap"+keyValueMap);
		for(Map<String,String> eachSet:keyValueMap) {
			filterTag=eachSet.get("Filter Type");
			tagValue=eachSet.get("Filter Value");
			filterByCriteria(filterTag,tagValue);
		}
		
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//div[@class='x-overlay-footer__apply']/button"));
		Thread.sleep(3000);
	}
	public  void filterByCriteria(String filterTag, String value) throws Exception{
		System.out.println("applying filter>>>>>>>>>>>>>");
		switch(filterTag.toLowerCase()) {
		case "condition":
			setCondition(filterTag,value);
			break;
		case "price":
			addPriceRange(filterTag,value);
			break; 
		case "item location":
			setLocation(filterTag,value);
			break;	
		}
	}

	public static void setCondition(String tag,String value) throws Exception{
		System.out.println("inside add condition");
		String conditionValueXpath="//span[@class='cbx x-refine__multi-select-cbx' and contains(text(),'%val')]//parent::label//parent::span/span";
		CommonAction.waitForElementToBeVisible((CommonAction.getWebElement("xpath","//*[@id=\"c3-mainPanel-condition\"]/span")));
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//*[@id=\"c3-mainPanel-condition\"]/span"));
		Thread.sleep(3000);
		CommonAction.waitForElementToBeVisible((CommonAction.getWebElement("xpath",conditionValueXpath.replace("%val",value))));
		CommonAction.click(CommonAction.getWebElement("xpath",conditionValueXpath.replace("%val",value)));

	}

	public  void addPriceRange(String tag,String value) throws Exception{
		 WebDriver driver=DriverFactory.getDriver();
		String textBoxesXpath="(//div[@class='x-textrange__block']/input)[%i]";
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//*[@id=\"c3-mainPanel-price\"]"));
		Thread.sleep(2000);
		List<WebElement> textBoxes=driver.findElements(By.xpath("//div[@class='x-textrange__block']/input"));
		for(int i=1;i<=textBoxes.size();i++) {
			String[] priceParts = value.replace("$", "").split("-");
			CommonAction.javaScriptEnterValue(CommonAction.getWebElement("xpath",textBoxesXpath.replace("%i",String.valueOf(i))),priceParts[i-1].trim());
			CommonAction.getWebElement("xpath",textBoxesXpath.replace("%i",String.valueOf(i))).sendKeys(Keys.TAB);
		}	
	}

	public static void setLocation(String tag,String value) throws Exception{
		System.out.println("inside adding location>>>>>>");
		String radioLocationXpath="//span[@class='cbx x-refine__multi-select-cbx' and contains(text(),'%val')]/parent::label//parent::span/span";
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//*[@id=\"c3-mainPanel-location\"]/span"));
		Thread.sleep(2000);
		CommonAction.click(CommonAction.getWebElement("xpath",radioLocationXpath.replace("%val",value)));
	}

	@Then("Verify that user should see \"([^\"]*)\" are applied$")
	public void checkIfFilterIsApplied(String expDetails) throws Exception {
		 WebDriver driver=DriverFactory.getDriver();
		System.out.println("Inside validation");
		// Click on the "filters applied" button
		CommonAction.click(CommonAction.getWebElement("xpath", "//button/span[contains(text(),'filters applied')]/parent::button"));
		// Find all the filter elements and extract their details
		List<WebElement> listOfFilter = driver.findElements(By.xpath("//button/span[contains(text(),'filters applied')]/parent::button/following-sibling::div/ul/li"));
		for (WebElement ele : listOfFilter) {
			String filterName = ele.getText().replace("filter applied", "").trim();
			String[] filterPart = filterName.split(":");
			String key = filterPart[0].trim();
			String value = filterPart[1].trim();

			if (key.equalsIgnoreCase("Price")) {
				value = value.replace(" ", "").replace("to", "-");
			}

			actUIMaps.put(key, value);
		}
		System.out.println("expDetails>>>" + expDetails);

		String[] expDetailsArray = expDetails.split(";");
		for (String each : expDetailsArray) {
			String[] keyValue = each.split(":");
			String key = keyValue[0].trim();
			String value = keyValue[1].trim();
			expMaps.put(key, value);
		}
		System.out.println("expMaps>>" + expMaps);
		System.out.println("actUIMaps>>" + actUIMaps);

		// Compare the expected and actual filter details
		compareMaps(expMaps, actUIMaps);
		System.out.println("Validation done >>>>>>");
	}
	// Compare two maps and print the differences

	private void compareMaps(Map<String, String> expected, Map<String, String> actual) {
		for (Map.Entry<String, String> entry : expected.entrySet()) {
			String key = entry.getKey();
			String expectedValue = entry.getValue();
			String actualValue = actual.get(key);
			if (!expectedValue.equals(actualValue)) {
				CommonAction.failTest("Mismatch for key: " + key+"Expected: " + expectedValue+"Actual: " + actualValue);
			}
		}
	}

	public static void verifyText(String ExpectedText,WebElement ActualText) throws  Exception {
		String actuaclResult = ActualText.getText().trim();
		if(!actuaclResult.contains(ExpectedText)) {
			CommonAction.failTest("Mismatch in text.Actual:"+ActualText.getText().trim()+".Expected Text:\"+ExpectedText");	
		}
	}

	//scenarioSecond

	@When("User enter \"([^\"]*)\" in the search bar$")
	public void enterDatainSearchBar(String input)throws Exception {
		System.out.println("inside enter data");
		waitForPageToLoad();
		String textAreaXpath="//input[@id='gh-ac']";
		searchInput=input; // stored search string to use in other method for assertion
		CommonAction.waitForElementToBeVisible(CommonAction.getWebElement("xpath",textAreaXpath));
		CommonAction.javaScriptEnterValue(CommonAction.getWebElement("xpath",textAreaXpath), input);


	}
	@And("User change Search category to \"([^\"]*)\" and click \"([^\"]*)\"$")
	public void selectCategoryfromDropDown(String category, String searchBtn) throws Exception { 
		System.out.println("inside changing category");
		String headerResultXpath="//h1[contains(text(),'results for')]/span[contains(text(),'%input')]";
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//select[@id='gh-cat']"));
		CommonAction.selectValueByVisibleText(CommonAction.getWebElement("xpath","//select[@id='gh-cat']"), category);
		CommonAction.javaScriptClick(CommonAction.getWebElement("xpath","//input[@type='submit']"));

		String result=CommonAction.getWebElement("xpath",headerResultXpath.replace("%input",searchInput)).getText();
		if(!result.equalsIgnoreCase(searchInput)) //matching result header with search string 
			CommonAction.failTest("Products are displayed  for"+result+"not for this"+searchInput );
	}

	@Then("verify that the page loads completely")
	public  void waitForPageToLoad() {
		 WebDriver driver=DriverFactory.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
		System.out.println("Page loaded completely.");
	}

	@Then("verify that first result name matches with search string \"([^\"]*)\"$")
	public void verifyFirstResultNameContainsSearchString(String str) throws Exception {
		System.out.println("Inside verification>>");
		str=str.toLowerCase();
		String firstResult=getnthProduct(2);
		if(!firstResult.toLowerCase().contains(str)) //checking result is related to search string only 
			CommonAction.failTest("Displayed product is not related to" +str);
	
	}
   // below method for getting product details 
	public static String getnthProduct(int itemNumber) throws Exception{
		String productXpath = "//div[@id='srp-river-results']/ul/li[%d]";
		String nthProduct =CommonAction.getWebElement("xpath",productXpath.replace("%d",String.valueOf(itemNumber))).getText();
		System.out.println("Product details >>"+ nthProduct);
		return nthProduct;
	}

	
	

    	
    }
	





