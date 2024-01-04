package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GalleryHomeTest {
    private static ChromeDriver driver;
    private static GalleryHomePage page;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new GalleryHomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeEach
    void loadGalleryHomePage() {
        page.navigate();
    }

    @Test
    void testPageTitle() {
        // Arrange
        String pageTitle = page.getPageTitle();
        // Assert
        assertEquals("Gallery", pageTitle);
    }
    @ParameterizedTest(name = "Test presence of {0} navbar element")
    @CsvSource({
            "navLogo",
            "settingsIcon"
    })
    void testNavElementPresence(String identifier) {
        assertTrue(page.checkNavElementPresence(identifier));
    }
    @ParameterizedTest(name = "Test text of {0} is {1}")
    @CsvSource({
            "navTitle, Gallery",
            "loginButton, Log in",
            "signupButton, Sign up"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = page.getNavElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @ParameterizedTest(name = "Test presence of {0} image")
    @CsvSource({
            "indianTruck",
            "girlAndGoat",
            "legTattoos",
            "IndianPublicBin",
            "NoviceBuddhistMonks"
    })
    void testImagePresence(String identifier) {
        assertTrue(page.checkImagePresence(identifier));
    }



//    @DisplayName("Create new todo for plain text, single char, accented chars and emojis")
//    @ParameterizedTest(name = "Creates new todo with text {0}")
//    @CsvSource({
//            "Make tests",
//            "X",
//            "Héłlö",
//            "⭐"
//    })
//    void testNewTodos(String text) {
//        // Arrange
//        // Act
//        // Assert
//    }


    @AfterEach
    void clearStorage() {
        driver.navigate().refresh();
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
