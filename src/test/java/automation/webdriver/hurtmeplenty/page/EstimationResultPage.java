package automation.webdriver.hurtmeplenty.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EstimationResultPage {
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

    public EstimationResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getEstimatedVirtualMachineClass() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(virtualMachineClassField));
        return virtualMachineClassField.getText().toLowerCase();
    }

    public String getEstimatedInstanceType() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(instanceTypeField));
        return instanceTypeField.getText().toLowerCase();
    }

    public String getEstimatedRegion() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(regionField));
        return regionField.getText().toLowerCase();
    }

    public String getEstimatedLocalSSD() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(localSSDField));
        return localSSDField.getText().toLowerCase();
    }

    public String getEstimatedCommitmentTerm() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(commitmentTermField));
        return commitmentTermField.getText().toLowerCase();
    }

    public String getTotalEstimatedCost() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(totalEstimatedCost));
        return totalEstimatedCost.getText().toLowerCase();
    }
}