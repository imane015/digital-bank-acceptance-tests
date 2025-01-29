package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BrowserHelper {

    //wait until the element is visible
    public static WebElement waitForVisibilityOfElement(WebDriver driver, WebElement element, int timeToWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait until the element is clickable and click on it
    public static WebElement waitUntilElementIsClickable(WebDriver driver, WebElement element, int timeToWaitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSeconds);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();

        return clickableElement;
    }

    public static void scrollIntoView(WebDriver driver,WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",element);

    }


}
