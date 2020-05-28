package automation.webdriver.hurtmeplenty.test;

import automation.webdriver.hurtmeplenty.page.EstimationResultPage;
import automation.webdriver.hurtmeplenty.page.GoogleCloudPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HurtMePlentyTest {
    private WebDriver driver;
    private EstimationResultPage estimationResultPage;

    private static final String REQUIRED_VIRTUAL_MACHINE_CLASS = "regular";
    private static final String REQUIRED_INSTANCE_TYPE = "n1-standard-8";
    private static final String REQUIRED_REGION = "Frankfurt";
    private static final String REQUIRED_LOCAL_SSD = "2x375 GiB";
    private static final String REQUIRED_COMMITMENT_TERM = "1 Year";
    private static final String EXPECTED_TOTAL_ESTIMATED_COST = "USD 1,082.77";

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        estimationResultPage = new GoogleCloudPage(driver)
                .openGoogleCloudPage()
                .searchForPricingCalculator()
                .goToPricingCalculatorPage()
                .specifyOptionsForEstimation()
                .estimate();
    }

    @Test
    public void estimatedVirtualMachineClassCorrespondsToRequired() {
        Assert.assertTrue(estimationResultPage.getEstimatedVirtualMachineClass()
                .contains(REQUIRED_VIRTUAL_MACHINE_CLASS.toLowerCase()),
                "Estimated virtual machine class doesn't correspond to required!");
    }

    @Test
    public void estimatedInstanceTypeCorrespondsToRequired() {
        Assert.assertTrue(estimationResultPage.getEstimatedInstanceType()
                        .contains(REQUIRED_INSTANCE_TYPE.toLowerCase()),
                "Estimated instance type doesn't correspond to required!");
    }

    @Test
    public void estimatedRegionCorrespondsToRequired() {
        Assert.assertTrue(estimationResultPage.getEstimatedRegion()
                        .contains(REQUIRED_REGION.toLowerCase()),
                "Estimated region doesn't correspond to required!");
    }

    @Test
    public void estimatedLocalSSDCorrespondsToRequired() {
        Assert.assertTrue(estimationResultPage.getEstimatedLocalSSD()
                        .contains(REQUIRED_LOCAL_SSD.toLowerCase()),
                "Estimated local SSD doesn't correspond to required!");
    }

    @Test
    public void estimatedCommitmentTermCorrespondsToRequired() {
        Assert.assertTrue(estimationResultPage.getEstimatedCommitmentTerm()
                        .contains(REQUIRED_COMMITMENT_TERM.toLowerCase()),
                "Estimated commitment term doesn't correspond to required!");
    }

    @Test
    public void totalEstimatedCostCorrespondsToExpectedValue() {
        Assert.assertTrue(estimationResultPage.getTotalEstimatedCost()
                        .contains(EXPECTED_TOTAL_ESTIMATED_COST.toLowerCase()),
                "Total estimated cost doesn't correspond to expected!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}