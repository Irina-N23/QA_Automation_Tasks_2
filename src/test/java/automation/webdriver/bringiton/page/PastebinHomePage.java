package automation.webdriver.bringiton.page;

import automation.webdriver.utilities.CustomConditions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private WebDriver driver;
    private static final String PASTEBIN_HOMEPAGE_URL = "https://pastebin.com";
    public static final String CODE_FOR_NEW_PASTE = "git config --global user.name \"New Sheriff in"
                         + " Town\"\ngit reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
                         + "git push origin master –force";
    public static final String PASTE_NAME = "how to gain dominance among developers";
    public static final String SYNTAX_HIGHLIGHTING = "Bash";

    @FindBy(id = "paste_code")
    private WebElement newPasteField;

    @FindBy(xpath = "//span[text()='None']")
    private WebElement syntaxHighlightingDropDownList;

    @FindBy(className = "select2-search__field")
    private WebElement syntaxHighlightingField;

    @FindBy(xpath = "//span[text()='Never']")
    private WebElement pasteExpirationDropDownList;

    @FindBy(xpath = "//li[contains(text(),'10 Minutes')]")
    private WebElement specifiedPasteExpiration;

    @FindBy(name = "paste_name")
    private WebElement pasteNameOrTitleField;

    @FindBy(id = "submit")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(PASTEBIN_HOMEPAGE_URL);
        new WebDriverWait(driver, 15)
                .until(CustomConditions.jQueryAJAXsCompleted());
        CustomConditions.waitForVisibilityOf(newPasteField, driver);
        return this;
    }

    public PastebinHomePage addCodeToNewPaste() {
        newPasteField.sendKeys(CODE_FOR_NEW_PASTE);
        return this;
    }

    public PastebinHomePage selectBashSyntaxHighLightingStyle() {
        syntaxHighlightingDropDownList.click();
        syntaxHighlightingField.sendKeys(SYNTAX_HIGHLIGHTING + Keys.ENTER);
        return this;
    }

    public PastebinHomePage selectPasteExpiration() {
        pasteExpirationDropDownList.click();
        specifiedPasteExpiration.click();
        return this;
    }

    public PastebinHomePage inputPasteName() {
        pasteNameOrTitleField.sendKeys(PASTE_NAME);
        return this;
    }

    public CreatedPastePage clickOnCreateNewPasteButton() {
        createNewPasteButton.click();
        return new CreatedPastePage(driver);
    }
}