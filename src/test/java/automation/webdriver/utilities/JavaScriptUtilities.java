package automation.webdriver.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtilities {
    public static void clickOnVisibleElement(WebElement element, WebDriver driver) {
        CustomConditions.waitForVisibilityOf(element, driver);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public static void specifyOptionFromDropDownList(WebElement dropDownList, WebElement specifiedOption,
                                                     WebDriver driver) {
        clickOnVisibleElement(dropDownList, driver);
        clickOnVisibleElement(specifiedOption, driver);
    }
}