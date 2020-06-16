package automation.webdriver.icanwin.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewPastePage {
    private WebDriver driver;

    @FindBy(id = "success")
    private WebElement successfulPasteCreationMessage;

    public NewPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean isPasteCreated() {
        CustomConditions.waitForVisibilityOf(successfulPasteCreationMessage, driver);
        return successfulPasteCreationMessage.isDisplayed();
    }
}