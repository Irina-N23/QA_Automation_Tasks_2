package automation.webdriver.hurtmeplenty.page;

import automation.webdriver.utilities.CustomConditions;
import automation.webdriver.utilities.JavaScriptUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(id = "select_option_213")
    private WebElement specifiedMachineType;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']")
    private WebElement addGPUsCheckbox;

    @FindBy(id = "select_value_label_335")
    private WebElement numberOfGPUsDropDownList;

    @FindBy(id = "select_option_342")
    private WebElement specifiedNumberOfGPUs;

    @FindBy(id = "select_value_label_336")
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
        CustomConditions.switchToInnerFrame(firstCalculatorFrame, secondCalculatorFrame, driver);
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

    public EstimateResultPage estimate() {
        JavaScriptUtilities.clickOnVisibleElement(addToEstimateButton, driver);
        return new EstimateResultPage(driver);
    }

    private void chooseRequiredProduct() {
        computeEngineCarouselIcon.click();
    }

    private void inputNumberOfInstances() {
        numberOfInstancesField.click();
        numberOfInstancesField.sendKeys(REQUIRED_NUMBER_OF_INSTANCES);
    }

    private void specifyOperatingSystemOrSoftware() {
        JavaScriptUtilities.specifyOptionFromDropDownList(operatingSystemOrSoftwareDropDownList,
                                                        specifiedOperatingSystemOrSoftware, driver);
    }

    private void specifyVirtualMachineClass() {
        JavaScriptUtilities.specifyOptionFromDropDownList(virtualMachineClassDropDownList,
                                                          specifiedVirtualMachineClass, driver);
    }

    private void specifyMachineType() {
        JavaScriptUtilities.specifyOptionFromDropDownList(machineTypeDropDownList,
                                                          specifiedMachineType, driver);
    }

    private void addGPUs() {
        JavaScriptUtilities.clickOnVisibleElement(addGPUsCheckbox, driver);
        JavaScriptUtilities.specifyOptionFromDropDownList(numberOfGPUsDropDownList,
                                                          specifiedNumberOfGPUs, driver);
        JavaScriptUtilities.specifyOptionFromDropDownList(GPUTypeDropDownList, specifiedGPUType,
                                                          driver);
    }

    private void specifyLocalSSD() {
        JavaScriptUtilities.specifyOptionFromDropDownList(localSSDDropDownList, specifiedLocalSSD,
                                                          driver);
    }

    private void specifyDataCenterLocation() {
        JavaScriptUtilities.specifyOptionFromDropDownList(dataCenterLocationDropDownList,
                                                          specifiedDataCenterLocation, driver);
    }

    private void specifyCommittedUsage() {
        JavaScriptUtilities.specifyOptionFromDropDownList(committedUsageDropDownList,
                                                          specifiedCommittedUsage, driver);
    }
}