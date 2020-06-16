package automation.webdriver.hardcore.page;

import automation.webdriver.utilities.CustomConditions;
import automation.webdriver.utilities.JavaScriptUtilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;

public class EmailForm {
    private WebDriver driver;

    @FindBy(xpath = "//devsite-iframe/iframe")
    private WebElement firstCalculatorFrame;

    @FindBy(id = "myFrame")
    private WebElement secondCalculatorFrame;

    @FindBy(id = "input_404")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailButton;

    public EmailForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EmailForm sendEmail() {
        copyGeneratedEmailFromTenMinuteMailTab();
        CustomConditions.waitForVisibilityOf(emailField, driver);
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "v") + Keys.ENTER);
        JavaScriptUtilities.clickOnVisibleElement(sendEmailButton, driver);
        return this;
    }

    private void copyGeneratedEmailFromTenMinuteMailTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");

        ArrayList<String> newWindowsSet = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));
        new TenMinuteMailPage(driver).openTenMinuteMailPage().copyNewEmail();

        driver.switchTo().window(newWindowsSet.get(0));
        CustomConditions.switchToInnerFrame(firstCalculatorFrame, secondCalculatorFrame, driver);
    }
}