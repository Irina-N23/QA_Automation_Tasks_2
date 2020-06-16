package automation.webdriver.hardcore.page;

import automation.webdriver.utilities.JavaScriptUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EstimateResultPage {
    private WebDriver driver;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost:')]")
    private WebElement totalEstimatedCost;

    @FindBy(id = "email_quote")
    private WebElement emailEstimateButton;

    public EstimateResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public EmailForm clickEmailEstimateButton() {
        JavaScriptUtilities.clickOnVisibleElement(emailEstimateButton, driver);
        return new EmailForm(driver);
    }

    public String getTotalEstimatedMonthlyCostFromCalculatorPage() {
        return totalEstimatedCost.getText();
    }
}