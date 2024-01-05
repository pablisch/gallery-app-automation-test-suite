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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long) 0.2));
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
    @DisplayName("Test presence of navbar elements")
    @ParameterizedTest(name = "Test presence of {0} navbar element to be {1}")
    @CsvSource({
            "navLogo, true",
            "settingsIcon, true",
            "loginBtn, false",
            "signupBtn, true",
            "logoutBtn, false",
            "uploadImageBtn, false",
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

    @DisplayName("Test text of navbar elements")
    @ParameterizedTest(name = "Test text of {0} is {1}")
    @CsvSource({
            "navTitle, Gallery",
            "signupBtn, Sign up"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = navbar.getNavElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test text of login page elements")
    @ParameterizedTest(name = "Test text of {0}")
    @CsvSource({
            "loginTitle, Log in to Gallery",
            "loginUsernameLabel, Username",
            "loginPasswordLabel, Password",
            "loginSubmitBtn, Log in"
    })
    void testLoginPageElementText(String identifier, String expectedText) {
        // Arrange
        String element = loginPage.getLoginPageElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test placeholder text of login page elements")
    @ParameterizedTest(name = "Test text of {0}")
    @CsvSource({
            "loginUsernameInputField, Username",
            "loginPasswordInputField, Password"
    })
    void testLoginPageElementPlaceholderText(String identifier, String expectedText) {
        // Arrange
        String element = loginPage.getLoginPageElementPlaceholderText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test navigate to main feed after login")
    @ParameterizedTest(name = "Test username {0} and password {1}")
    @CsvSource({
            "Billy, Password123",
            "Martha, Password123",
            "Sabina, Password123",
    })
    void testSuccessfulLogin(String username, String password) {
        // Act
        loginPage.completeLoginFormAndSubmit(username, password);
        String actualPageTitle = loginPage.getPageTitleAfterLogin();
        // Assert
        assertEquals("Gallery", actualPageTitle);
    }
    @DisplayName("Test error message after failed login")
    @ParameterizedTest(name = "Test username {0}, password {1} and expect {2}")
    @CsvSource({
            "Emma, Password123, That username is not in use",
            "Billy, Password124, Username and password do not match",
            "Billy, P1, Username and password do not match",
    })
    void testFailedLogin(String username, String password, String expectedErrorMessage) {
        // Act
        loginPage.completeLoginFormAndSubmit(username, password);
        String actualErrorMessage = loginPage.getErrorAfterFailedLogin();
        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
    @DisplayName("Test login button disabled until all fields filled")
    @ParameterizedTest(name = "Test username {0} and password {1}")
    @CsvSource({
            ", Password123",
            "Billy, ",
            ", ",
    })
    void testBlankFieldsWhenLoggingIn(String username, String password) {
        // Act
        loginPage.completeLoginFormAndSubmit(username, password);
        Boolean loginBtnIsDisabled = loginPage.checkLoginBtnIsDisabled();
        // Assert
        assertEquals(true, loginBtnIsDisabled);
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
