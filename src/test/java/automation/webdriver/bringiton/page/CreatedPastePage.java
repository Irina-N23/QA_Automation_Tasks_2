package automation.webdriver.bringiton.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatedPastePage {
    private WebDriver driver;

    @FindBy(id = "success")
    private WebElement successfulPasteCreationMessage;

    @FindBy(className = "paste_box_line1")
    private WebElement createdPastePageTitle;

    @FindBy(xpath = "//span[@class='h_640']/a[@class='buttonsm']")
    private WebElement syntaxHighlightingButton;

    @FindBy(id = "selectable")
    private WebElement createdPasteCode;

    public CreatedPastePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCreatedPastePageTitle() {
        new WebDriverWait(driver, 10)
                .until(CustomConditions.jQueryAJAXsCompleted());
        CustomConditions.waitForVisibilityOf(successfulPasteCreationMessage, driver);
        return createdPastePageTitle.getText();
    }

    public String getSelectedSyntaxHighlightingStyle() {
        return syntaxHighlightingButton.getText();
    }

    public Boolean isCreatedPasteCodeEqualToInputtedCode() {
        return createdPasteCode.getText().equals(PastebinHomePage.CODE_FOR_NEW_PASTE);
    }
}