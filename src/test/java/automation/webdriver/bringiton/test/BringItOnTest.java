package automation.webdriver.bringiton.test;

import automation.webdriver.bringiton.page.CreatedPastePage;
import automation.webdriver.bringiton.page.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        String actualPasteName = createdPastePage.getCreatedPastePageTitle();
        String expectedPasteName = PastebinHomePage.PASTE_NAME;
        assertEquals(actualPasteName, expectedPasteName,
           "Created paste name is not equal to inputted title!");
    }

    @Test
    public void createdPasteSyntaxHighlightingIsEqualToSelectedSyntaxHighlighting() {
        String actualSyntaxHighlighting = createdPastePage.getSelectedSyntaxHighlightingStyle();
        String expectedSyntaxHighlighting = PastebinHomePage.SYNTAX_HIGHLIGHTING;
        assertEquals(actualSyntaxHighlighting, expectedSyntaxHighlighting, "Syntax Highlighting "
                     + "style is not equal to " + expectedSyntaxHighlighting + "!");
    }

    @Test
    public void createdPasteCodeIsEqualToInputtedCode() {
        boolean isNewPasteCodeEqualToInputtedCode = createdPastePage
                                                         .isCreatedPasteCodeEqualToInputtedCode();
        assertTrue(isNewPasteCodeEqualToInputtedCode,
                   "Created paste code is not equal to inputted code!");
    }

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}