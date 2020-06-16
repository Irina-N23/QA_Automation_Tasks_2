package automation.webdriver.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomConditions {
    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return (window.jQuery != null) && (jQuery.active == 0); ");
    }

    public static void waitForVisibilityOf(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
    }

    public static void clickOnClickableElement(WebElement element, WebDriver driver) {
        new WebDriverWait(driver, 120)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void clickOnClickableElement(WebElement element, long timeOutInSeconds,
                                               WebDriver driver) {
        new WebDriverWait(driver, timeOutInSeconds)
                .until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public static void switchToInnerFrame(WebElement firstFrame, WebElement secondFrame,
                                          WebDriver driver) {
        switchToAvailableFrame(firstFrame, driver);
        switchToAvailableFrame(secondFrame, driver);
    }

    private static void switchToAvailableFrame(WebElement frame, WebDriver driver) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
}