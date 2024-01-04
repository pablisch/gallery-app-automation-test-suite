package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testLoginPageTitle() {
        // Arrange
        String loginPageTitle = loginPage.getPageTitle();
        // Assert
        assertEquals("Gallery Login", loginPageTitle);
    }
    @ParameterizedTest(name = "Test presence of {0} navbar element")
    @CsvSource({
            "navLogo",
            "settingsIcon"
    })
    void testNavElementPresence(String identifier) {
        assertTrue(navbar.checkNavElementPresence(identifier));
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
