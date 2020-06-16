package automation.webdriver.hurtmeplenty.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPage {
    private WebDriver driver;
    private static final String GOOGLE_CLOUD_PAGE_URL = "https://cloud.google.com";

    @FindBy(xpath = "//div[@class='devsite-search-container']")
    private WebElement searchButton;

    @FindBy(name = "q")
    private WebElement searchBox;

    public GoogleCloudPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPage openGoogleCloudPage() {
        driver.get(GOOGLE_CLOUD_PAGE_URL);
        CustomConditions.waitForVisibilityOf(searchButton, driver);
        return this;
    }

    public SearchResultsPage searchForPricingCalculator() {
        searchButton.click();
        searchBox.sendKeys("Google Cloud Platform Pricing Calculator" + Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}