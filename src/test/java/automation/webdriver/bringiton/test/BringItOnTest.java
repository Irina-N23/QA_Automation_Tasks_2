package automation.webdriver.bringiton.test;

import automation.webdriver.bringiton.page.CreatedPastePage;
import automation.webdriver.bringiton.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BringItOnTest {
    private WebDriver driver;
    private CreatedPastePage createdPastePage;

    @BeforeClass(alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        createdPastePage = new PastebinHomePage(driver)
                .openPage()
                .addCodeToNewPaste()
                .selectBashSyntaxHighLightingStyle()
                .selectPasteExpiration()
                .inputPasteName()
                .clickOnCreateNewPasteButton();
    }

    @Test
    public void createdPasteNameIsEqualToInputtedTitle() {
        Assert.assertEquals(createdPastePage.getCreatedPastePageTitle(),
                            PastebinHomePage.PASTE_NAME,
                   "Created paste name is not equal to inputted title!");
    }

    @Test
    public void createdPasteSyntaxHighlightingIsEqualToSelectedSyntaxHighlighting() {
        Assert.assertEquals(createdPastePage.getSelectedSyntaxHighlightingStyle(),
                            PastebinHomePage.SYNTAX_HIGHLIGHTING,
                   "Syntax Highlighting style is not equal to "
                            + PastebinHomePage.SYNTAX_HIGHLIGHTING + "!");
    }

    @Test
    public void createdPasteCodeIsEqualToInputtedCode() {
        Assert.assertTrue(createdPastePage.isCreatedPastePageCodeEqualToInputtedCode(),
                         "Created paste code is not equal to inputted code!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}