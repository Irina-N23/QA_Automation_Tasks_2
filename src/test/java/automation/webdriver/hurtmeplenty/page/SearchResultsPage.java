package automation.webdriver.hurtmeplenty.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='gs-title']//b[text()='Google Cloud Platform Pricing Calculator']")
    private WebElement pricingCalculatorLink;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PricingCalculatorPage goToPricingCalculatorPage() {
        CustomConditions.waitForVisibilityOf(pricingCalculatorLink, driver);
        pricingCalculatorLink.click();
        return new PricingCalculatorPage(driver);
    }
}