package automation.webdriver.hardcore.test;

import automation.webdriver.hardcore.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HardcoreTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        EmailForm emailForm = new GoogleCloudPage(driver)
                .openGoogleCloudPage()
                .searchForPricingCalculator()
                .goToPricingCalculatorPage()
                .specifyOptionsForEstimation()
                .estimate()
                .clickEmailEstimateButton()
                .sendEmail();
    }

    @Test
    public void totalEstimatedMonthlyCostsAreEqualInCalculatorAndInEmail() {
        String totalCostFromCalculatorPage = new EstimateResultPage(driver)
                                                 .getTotalEstimatedMonthlyCostFromCalculatorPage();
        String totalCostFromReceivedEmail = new TenMinuteMailPage(driver)
                                                .getTotalEstimatedMonthlyCostFromReceivedEmail();
        boolean isTotalCostMatched = totalCostFromCalculatorPage.contains(totalCostFromReceivedEmail);
        assertTrue(isTotalCostMatched,
                "Total estimated monthly costs are not equal in calculator and in received email!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}