package automation.webdriver.hurtmeplenty.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EstimateResultPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[contains(text(),'VM class:')]")
    private WebElement virtualMachineClassField;

    @FindBy(xpath = "//div[contains(text(),'Instance type:')]")
    private WebElement instanceTypeField;

    @FindBy(xpath = "//div[contains(text(),'Region:')]")
    private WebElement regionField;

    @FindBy(xpath = "//div[contains(text(),'local SSD')]")
    private WebElement localSSDField;

    @FindBy(xpath = "//div[contains(text(),'Commitment term:')]")
    private WebElement commitmentTermField;

    @FindBy(xpath = "//b[contains(text(),'Total Estimated Cost:')]")
    private WebElement totalEstimatedCost;

    public EstimateResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getEstimatedVirtualMachineClass() {
        CustomConditions.waitForVisibilityOf(virtualMachineClassField, driver);
        return virtualMachineClassField.getText().toLowerCase();
    }

    public String getEstimatedInstanceType() {
        CustomConditions.waitForVisibilityOf(instanceTypeField, driver);
        return instanceTypeField.getText().toLowerCase();
    }

    public String getEstimatedRegion() {
        CustomConditions.waitForVisibilityOf(regionField, driver);
        return regionField.getText().toLowerCase();
    }

    public String getEstimatedLocalSSD() {
        CustomConditions.waitForVisibilityOf(localSSDField, driver);
        return localSSDField.getText().toLowerCase();
    }

    public String getEstimatedCommitmentTerm() {
        CustomConditions.waitForVisibilityOf(commitmentTermField, driver);
        return commitmentTermField.getText().toLowerCase();
    }

    public String getTotalEstimatedCost() {
        CustomConditions.waitForVisibilityOf(totalEstimatedCost, driver);
        return totalEstimatedCost.getText().toLowerCase();
    }
}