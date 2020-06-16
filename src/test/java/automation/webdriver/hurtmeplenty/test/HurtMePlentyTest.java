package automation.webdriver.hurtmeplenty.test;

import automation.webdriver.hurtmeplenty.page.EstimateResultPage;
import automation.webdriver.hurtmeplenty.page.GoogleCloudPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HurtMePlentyTest {
    private WebDriver driver;
    private EstimateResultPage estimationResultPage;

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
        boolean isVirtualMachineMatched = estimationResultPage.getEstimatedVirtualMachineClass()
                                            .contains(REQUIRED_VIRTUAL_MACHINE_CLASS.toLowerCase());
        assertTrue(isVirtualMachineMatched,
                "Estimated virtual machine class doesn't correspond to required!");
    }

    @Test
    public void estimatedInstanceTypeCorrespondsToRequired() {
        boolean isInstanceTypeMatched = estimationResultPage.getEstimatedInstanceType()
                                            .contains(REQUIRED_INSTANCE_TYPE.toLowerCase());
        assertTrue(isInstanceTypeMatched, "Estimated instance type doesn't correspond to required!");
    }

    @Test
    public void estimatedRegionCorrespondsToRequired() {
        boolean isRegionMatched = estimationResultPage.getEstimatedRegion()
                                      .contains(REQUIRED_REGION.toLowerCase());
        assertTrue(isRegionMatched, "Estimated region doesn't correspond to required!");
    }

    @Test
    public void estimatedLocalSSDCorrespondsToRequired() {
        boolean isLocalSSDMatched = estimationResultPage.getEstimatedLocalSSD()
                                        .contains(REQUIRED_LOCAL_SSD.toLowerCase());
        assertTrue(isLocalSSDMatched, "Estimated local SSD doesn't correspond to required!");
    }

    @Test
    public void estimatedCommitmentTermCorrespondsToRequired() {
        boolean isCommitmentTermMatched = estimationResultPage.getEstimatedCommitmentTerm()
                                              .contains(REQUIRED_COMMITMENT_TERM.toLowerCase());
        assertTrue(isCommitmentTermMatched, "Estimated commitment term doesn't correspond"
                   + " to required!");
    }

    @Test
    public void totalEstimatedCostCorrespondsToExpectedValue() {
        boolean isTotalEstimatedCostMatched = estimationResultPage.getTotalEstimatedCost()
                                             .contains(EXPECTED_TOTAL_ESTIMATED_COST.toLowerCase());
        assertTrue(isTotalEstimatedCostMatched,"Total estimated cost doesn't correspond"
                   + "to expected!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}