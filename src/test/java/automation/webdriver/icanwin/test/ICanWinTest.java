package automation.webdriver.icanwin.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import automation.webdriver.icanwin.page.NewPastePage;
import automation.webdriver.icanwin.page.PastebinHomePage;

public class ICanWinTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void createNewPaste() {
        NewPastePage newPastePage = new PastebinHomePage(driver)
                .openPage()
                .addCodeToNewPaste()
                .selectPasteExpiration()
                .inputPasteName()
                .clickOnCreateNewPasteButton();
        Assert.assertTrue(newPastePage.isPasteCreated(), "New paste creation has been failed!");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
        driver = null;
    }
}