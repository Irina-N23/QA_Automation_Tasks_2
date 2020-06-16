package automation.webdriver.hardcore.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        CustomConditions.waitForVisibilityOf(mailAddressField, driver);
        return this;
    }

    public TenMinuteMailPage copyNewEmail() {
        mailAddressField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        return this;
    }

    public String getTotalEstimatedMonthlyCostFromReceivedEmail() {
        ArrayList<String> newWindowsSet = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(newWindowsSet.get(1));

        CustomConditions.clickOnClickableElement(emailWithEstimate, driver);
        CustomConditions.waitForVisibilityOf(totalEstimatedMonthlyCost, driver);
        return totalEstimatedMonthlyCost.getText();
    }
}