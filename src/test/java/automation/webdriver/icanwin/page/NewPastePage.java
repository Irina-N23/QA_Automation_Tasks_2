package automation.webdriver.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewPastePage {
    private WebDriver driver;
    private static final String SUCCESSFUL_PASTE_CREATION_MESSAGE_XPATH = "//div[@id='success']";

    @FindBy(xpath = SUCCESSFUL_PASTE_CREATION_MESSAGE_XPATH)
    private WebElement successfulPasteCreationMessage;

    public NewPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean isPasteCreated() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By
                            .xpath(SUCCESSFUL_PASTE_CREATION_MESSAGE_XPATH)));
        return successfulPasteCreationMessage.isDisplayed();
    }
}