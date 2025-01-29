package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private Driver(){

    }
    public static WebDriver getDriver() {
        if(driver == null) {

            String browser = ConfigReader.getPropertiesValue("digitalbank.browser");
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if(browser.equalsIgnoreCase("Edge")) {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            else if(browser.equalsIgnoreCase("safari")){
                driver = new SafariDriver();
            }
            else if (browser.equalsIgnoreCase("headless")){
                ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                ChromeOptions options = new ChromeOptions();

                options.addArguments("--window-size=1920,1080");
                options.addArguments("disable-extensions");
                options.setExperimentalOption("useAutomationExtension", false);
                options.addArguments("--proxy-server='direct://'");
                options.addArguments("--proxy-bypass-list=*");
                options.addArguments("--start-maximized");
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
    public static void takeScreenShot(Scenario scenario) {
        if(scenario.isFailed()) {
            //taking a screenshot
            final byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            // adding the screenshot to the report
            scenario.attach(screenshot,"image/png","screenshot");

        }
    }


    public static void closeDriver(){
        if(driver!= null) {
            driver.quit();
            driver=null;
        }
    }
}
