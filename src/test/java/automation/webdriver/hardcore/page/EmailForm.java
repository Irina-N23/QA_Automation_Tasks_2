package automation.webdriver.hardcore.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class EmailForm {
    private WebDriver driver;

    @FindBy(xpath = "//devsite-iframe/iframe")
    private WebElement firstCalculatorFrame;

    @FindBy(id = "myFrame")
    private WebElement secondCalculatorFrame;

    @FindBy(xpath = "//md-input-container//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    private WebElement sendEmailButton;

    public EmailForm(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EmailForm sendEmail() {
        copyGeneratedEmailFromTenMinuteMailTab();
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(Keys.chord(Keys.CONTROL, "v") + Keys.ENTER);

        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(sendEmailButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", sendEmailButton);
        return this;
    }

    private void copyGeneratedEmailFromTenMinuteMailTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");

        ArrayList<String> newWindowsSet = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));
        new TenMinuteMailPage(driver).openTenMinuteMailPage().copyNewEmail();

        driver.switchTo().window(newWindowsSet.get(0));
        new WebDriverWait(driver, 15).until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(firstCalculatorFrame));
        new WebDriverWait(driver, 15).until(ExpectedConditions
                .frameToBeAvailableAndSwitchToIt(secondCalculatorFrame));
    }
}