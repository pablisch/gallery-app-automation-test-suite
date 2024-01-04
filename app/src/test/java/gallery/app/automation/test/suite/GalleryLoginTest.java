package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryLoginTest {
    private static ChromeDriver driver;
    private static GalleryLoginPage loginPage;
    private static GalleryNavbar navbar;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new GalleryLoginPage(driver);
        navbar = new GalleryNavbar(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeEach
    void loadGalleryHomePage() {
        loginPage.navigate();
    }

    @Test
    void testLoginPageTitle() throws InterruptedException {
        // Arrange
        String loginPageTitle = loginPage.getPageTitle();
        Thread.sleep(3000);
        // Assert
        assertEquals("Gallery Login", loginPageTitle);
    }
    @ParameterizedTest(name = "Test presence of {0} navbar element to be {1}")
    @CsvSource({
            "navLogo, true",
            "settingsIcon, true",
            "loginButton, false",
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
            "signupButton, Sign up"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = navbar.getNavElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
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
