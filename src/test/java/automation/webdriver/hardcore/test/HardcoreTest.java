package automation.webdriver.hardcore.test;

import automation.webdriver.hardcore.page.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        Assert.assertTrue(new EstimateResultPage(driver)
                                  .getTotalEstimatedMonthlyCostFromCalculatorPage()
                        .contains(new TenMinuteMailPage(driver)
                                          .getTotalEstimatedMonthlyCostFromReceivedEmail()),
                "Total estimated monthly costs are not equal in calculator and in received email!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}