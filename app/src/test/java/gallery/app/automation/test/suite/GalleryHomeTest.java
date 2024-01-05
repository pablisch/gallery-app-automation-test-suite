package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryHomeTest {
    private static ChromeDriver driver;
    private static GalleryHomePage homePage;
    private static GalleryNavbar navbar;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new GalleryHomePage(driver);
        navbar = new GalleryNavbar(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeEach
    void loadGalleryHomePage() {
        homePage.navigate();
    }

    @Test
    void testPageTitle() {
        // Arrange
        String homePageTitle = homePage.getPageTitle();
        // Assert
        assertEquals("Gallery", homePageTitle);
    }
    @ParameterizedTest(name = "Test presence of {0} navbar element to be {1}")
    @CsvSource({
            "navLogo, true",
            "settingsIcon, true",
            "loginButton, true",
            "signupButton, true",
            "logoutButton, false",
            "uploadImageButton, false",
            "userSettingsIcon, false",
    })
    void testNavElementPresence(String identifier, boolean expectedPresence) {
        try {
            // If element is found then it is compared against the expectation
            boolean actualPresence = navbar.checkNavElementPresence(identifier);
            assertEquals(expectedPresence, actualPresence);
        } catch (NoSuchElementException e) {
            // If NoSuchElementException is caught, it means the element was not found and the expectation
            // is compared against false
            assertFalse(expectedPresence);
        }
    }
    @ParameterizedTest(name = "Test text of {0} is {1}")
    @CsvSource({
            "navTitle, Gallery",
            "loginButton, Log in",
            "signupButton, Sign up"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = navbar.getNavElementText(identifier);
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
        assertTrue(homePage.checkImagePresence(identifier));
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
