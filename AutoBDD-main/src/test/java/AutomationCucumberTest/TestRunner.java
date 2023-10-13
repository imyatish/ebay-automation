package AutomationCucumberTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import CommonStepsDefinitions.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.testng.annotations.Test;



@CucumberOptions(features="classpath:Features" ,glue={"AutomationStepDefinitions","CommonStepsDefinitions"}
               ,tags="@test", monochrome=false ,plugin= {"pretty","html:target/HtmlReports/report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
	 WebDriver driver;
	 @BeforeMethod(alwaysRun = true)
	 public void launchBrowser() {
	     driver = DriverFactory.getDriver();
	 }

	 @AfterMethod(alwaysRun = true)
	 public void closeBrowser() {
	     if (driver != null) {
	         DriverFactory.quitDriver();
	     }
	 }


}





	
	


	
	
	


