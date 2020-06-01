package automation.webdriver.hardcore.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

public class TenMinuteMailPage {
    private WebDriver driver;
    private static final String TEN_MINUTE_MAIL_PAGE_URL = "https://10minutemail.com";

    @FindBy(id = "mail_address")
    private WebElement mailAddressField;

    @FindBy(xpath = "//section[@id='mail_messages']"
                    + "//span[contains(text(),'Google Cloud Platform Price Estimate')]")
    private WebElement emailWithEstimate;

    @FindBy(xpath = "//h3[contains(text(),'USD')]")
    private WebElement totalEstimatedMonthlyCost;

    public TenMinuteMailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TenMinuteMailPage openTenMinuteMailPage() {
        driver.get(TEN_MINUTE_MAIL_PAGE_URL);
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(mailAddressField));
        return this;
    }

    public TenMinuteMailPage copyNewEmail() {
        mailAddressField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        return this;
    }

    public String getTotalEstimatedMonthlyCostFromReceivedEmail() {
        ArrayList<String> newWindowsSet = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));

        new WebDriverWait(driver, 120)
                .until(ExpectedConditions.elementToBeClickable(emailWithEstimate));
        emailWithEstimate.click();

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(totalEstimatedMonthlyCost));
        return totalEstimatedMonthlyCost.getText();
    }
}