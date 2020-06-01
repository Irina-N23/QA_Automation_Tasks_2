package automation.webdriver.hardcore.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(emailEstimateButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", emailEstimateButton);
        return new EmailForm(driver);
    }

    public String getTotalEstimatedMonthlyCostFromCalculatorPage() {
        return totalEstimatedCost.getText();
    }
}