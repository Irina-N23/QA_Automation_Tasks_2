package automation.webdriver.icanwin.page;

import automation.webdriver.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
    private static final String PASTEBIN_HOMEPAGE_URL = "https://pastebin.com";
    private static final String NEW_PASTE_CODE = "Hello from WebDriver";
    private static final String PASTE_NAME = "helloweb";
    private WebDriver driver;

    @FindBy(id="paste_code")
    private WebElement newPasteInput;

    @FindBy(xpath = "//span[text()='Never']")
    private WebElement pasteExpirationMenu;

    @FindBy(xpath = "//li[contains(text(),'10 Minutes')]")
    private WebElement pasteExpirationChoice;

    @FindBy(xpath = "//input[@name='paste_name']")
    private WebElement pasteNameInput;

    @FindBy(xpath = "//input[@id='submit']")
    private WebElement createNewPasteButton;

    public PastebinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinHomePage openPage() {
        driver.get(PASTEBIN_HOMEPAGE_URL);
        new WebDriverWait(driver, 10)
                .until(CustomConditions.jQueryAJAXsCompleted());
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("paste_code")));
        return this;
    }

    public PastebinHomePage addCodeToNewPaste() {
        newPasteInput.sendKeys(NEW_PASTE_CODE);
        return this;
    }

    public PastebinHomePage selectPasteExpiration() {
        pasteExpirationMenu.click();
        pasteExpirationChoice.click();
        return this;
    }

    public PastebinHomePage inputPasteName() {
        pasteNameInput.sendKeys(PASTE_NAME);
        return this;
    }

    public NewPastePage clickOnCreateNewPasteButton() {
        createNewPasteButton.click();
        return new NewPastePage(driver);
    }
}