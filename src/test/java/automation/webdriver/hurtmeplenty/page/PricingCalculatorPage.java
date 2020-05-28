package automation.webdriver.hurtmeplenty.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricingCalculatorPage {
    private WebDriver driver;
    private static final String REQUIRED_NUMBER_OF_INSTANCES = "4";

    @FindBy(xpath = "//devsite-iframe/iframe")
    private WebElement firstCalculatorFrame;

    @FindBy(id = "myFrame")
    private WebElement secondCalculatorFrame;

    @FindBy(xpath = "//md-tab-item//div[text()='Compute Engine']")
    private WebElement computeEngineCarouselIcon;

    @FindBy(id = "input_58")
    private WebElement numberOfInstancesField;

    @FindBy(id = "select_value_label_51")
    private WebElement operatingSystemOrSoftwareDropDownList;

    @FindBy(id = "select_option_60")
    private WebElement specifiedOperatingSystemOrSoftware;

    @FindBy(id = "select_value_label_52")
    private WebElement virtualMachineClassDropDownList;

    @FindBy(id = "select_option_72")
    private WebElement specifiedVirtualMachineClass;

    @FindBy(id = "select_value_label_55")
    private WebElement machineTypeDropDownList;

    @FindBy(id = "select_option_212")
    private WebElement specifiedMachineType;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUsCheckbox;

    @FindBy(id = "select_value_label_332")
    private WebElement numberOfGPUsDropDownList;

    @FindBy(id = "select_option_339")
    private WebElement specifiedNumberOfGPUs;

    @FindBy(id = "select_value_label_333")
    private WebElement GPUTypeDropDownList;

    @FindBy(xpath = "//div[contains(text(),'NVIDIA Tesla V100')]")
    private WebElement specifiedGPUType;

    @FindBy(id = "select_value_label_169")
    private WebElement localSSDDropDownList;

    @FindBy(xpath = "//div[contains(text(),'2x375 GB')]")
    private WebElement specifiedLocalSSD;

    @FindBy(id = "select_value_label_56")
    private WebElement dataCenterLocationDropDownList;

    @FindBy(id = "select_option_181")
    private WebElement specifiedDataCenterLocation;

    @FindBy(id = "select_value_label_57")
    private WebElement committedUsageDropDownList;

    @FindBy(id = "select_option_90")
    private WebElement specifiedCommittedUsage;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(text(),'Add to Estimate')]")
    private WebElement addToEstimateButton;

    public PricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PricingCalculatorPage specifyOptionsForEstimation() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOf(firstCalculatorFrame));
        driver.switchTo().frame(firstCalculatorFrame).switchTo().frame(secondCalculatorFrame);

        chooseRequiredProduct();
        inputNumberOfInstances();
        specifyOperatingSystemOrSoftware();
        specifyVirtualMachineClass();
        specifyMachineType();
        addGPUs();
        specifyLocalSSD();
        specifyDataCenterLocation();
        specifyCommittedUsage();
        return this;
    }

    public EstimationResultPage estimate() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(addToEstimateButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToEstimateButton);
        return new EstimationResultPage(driver);
    }

    private void chooseRequiredProduct() {
        computeEngineCarouselIcon.click();
    }

    private void inputNumberOfInstances() {
        numberOfInstancesField.click();
        numberOfInstancesField.sendKeys(REQUIRED_NUMBER_OF_INSTANCES);
    }

    private void specifyOperatingSystemOrSoftware() {
        specifyOptionFromDropDownList(operatingSystemOrSoftwareDropDownList,
                                      specifiedOperatingSystemOrSoftware);
    }

    private void specifyVirtualMachineClass() {
        specifyOptionFromDropDownList(virtualMachineClassDropDownList, specifiedVirtualMachineClass);
    }

    private void specifyMachineType() {
        specifyOptionFromDropDownList(machineTypeDropDownList, specifiedMachineType);
    }

    private void addGPUs() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(addGPUsCheckbox));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addGPUsCheckbox);

        specifyOptionFromDropDownList(numberOfGPUsDropDownList, specifiedNumberOfGPUs);
        specifyOptionFromDropDownList(GPUTypeDropDownList, specifiedGPUType);
    }

    private void specifyLocalSSD() {
        specifyOptionFromDropDownList(localSSDDropDownList, specifiedLocalSSD);
    }

    private void specifyDataCenterLocation() {
        specifyOptionFromDropDownList(dataCenterLocationDropDownList, specifiedDataCenterLocation);
    }

    private void specifyCommittedUsage() {
        specifyOptionFromDropDownList(committedUsageDropDownList, specifiedCommittedUsage);
    }

    private void specifyOptionFromDropDownList(WebElement dropDownList, WebElement specifiedOption) {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(dropDownList));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropDownList);

        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(specifiedOption));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", specifiedOption);
    }
}