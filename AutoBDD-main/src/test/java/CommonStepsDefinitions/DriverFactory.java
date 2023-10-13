package CommonStepsDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class DriverFactory {

    static WebDriver driver;

    public static WebDriver initDriver() {
    	
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
        ChromeOptions coptions = new ChromeOptions();
        coptions.addArguments("--log-level=WARNING");
        coptions.addArguments("--start-maximized");
        coptions.addArguments("--disable-infobars");//

        Map<String, Object> cloud_chromeprefs = new HashMap<String, Object>();
        cloud_chromeprefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + "downloadFiles");

        coptions.setExperimentalOption("prefs", cloud_chromeprefs);
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("goog:chromeOptions", coptions);
        driver = new ChromeDriver(capability);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        
        return driver;
     }
	//private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void demoinitDriver() {
        String projectPath = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver.exe");
        ChromeOptions coptions = new ChromeOptions();
        coptions.addArguments("--log-level=WARNING");
        coptions.addArguments("--start-maximized");
        driver = new ChromeDriver(coptions);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    }




