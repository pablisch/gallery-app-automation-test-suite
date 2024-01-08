package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GalleryImageUploadTest {
    private static ChromeDriver driver;
    private static GalleryImageUploadPage uploadPage;
    private static GalleryLoginPage loginPage;
    private static GalleryNavbar navbar;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        uploadPage = new GalleryImageUploadPage(driver);
        loginPage = new GalleryLoginPage(driver);
        navbar = new GalleryNavbar(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        navbar.resetDbDataAndNavigateToHomePage();
        navbar.logOut();
    }

    @BeforeEach
    void loadGalleryLoginPage() {
        loginPage.navigate();
    }

    @Test
    void testLoginPageTitle() {
        // Arrange
        loginPage.completeLoginFormAndSubmit("Billy", "Password123");
        navbar.clickNavLink("uploadImageBtn");
        String pageTitle = uploadPage.getPageTitle();
        // Assert
        assertEquals("Gallery Image Upload", pageTitle);
    }
    @Test
    void testUploadAnImage() throws InterruptedException {
        // Arrange
        loginPage.completeLoginFormAndSubmit("Billy", "Password123");
        navbar.clickNavLink("uploadImageBtn");
        uploadPage.selectFile("/Users/pablojoyce/Desktop/diy_scientist.png");
        uploadPage.selectAndTypeInElement("imageUploadDescriptionInput", "diy inventor");
        Thread.sleep(2000);
        uploadPage.clickElement("imageUploadSubmitBtn");
        Thread.sleep(2000);
        // Assert
    }

    @AfterEach
    void clearStorage() {
        driver.navigate().refresh();
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
