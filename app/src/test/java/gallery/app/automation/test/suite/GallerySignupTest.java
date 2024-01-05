package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GallerySignupTest {
    private static ChromeDriver driver;
    private static GallerySignupPage signupPage;
    private static GalleryNavbar navbar;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        signupPage = new GallerySignupPage(driver);
        navbar = new GalleryNavbar(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long) 0.2));
    }

    @BeforeEach
    void loadGallerySignupPage() {
        signupPage.navigate();
    }

    @Test
    void testSignupPageTitle() {
        // Arrange
        String signupPageTitle = signupPage.getPageTitle();
        // Assert
        assertEquals("Gallery Sign Up", signupPageTitle);
    }
    @DisplayName("Test presence of navbar elements")
    @ParameterizedTest(name = "Test presence of {0} navbar element to be {1}")
    @CsvSource({
            "navLogo, true",
            "settingsIcon, true",
            "loginBtn, true",
            "signupBtn, false",
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
            "loginBtn, Log in"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = navbar.getNavElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test text of signup page elements")
    @ParameterizedTest(name = "Test text of {0}")
    @CsvSource({
            "signupTitle, Create a new Gallery account",
            "signupNameLabel, Name",
            "signupUsernameLabel, Username",
            "signupEmailLabel, Email",
            "signupPasswordLabel, Password",
            "signupAvatarBtnLabel, Avatar image (optional)",
            "signupAvatarBtn, Choose file",
            "signupSubmitBtn, Sign Up"
    })
    void testSignupPageElementText(String identifier, String expectedText) {
        // Arrange
        String element = signupPage.getSignupPageElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test placeholder text of signup page elements")
    @ParameterizedTest(name = "Test text of {0}")
    @CsvSource({
            "signupNameInputField, Name",
            "signupUsernameInputField, Username",
            "signupEmailInputField, Email",
            "signupPasswordInputField, Password"
    })
    void testSignupPageElementPlaceholderText(String identifier, String expectedText) {
        // Arrange
        String element = signupPage.getSignupPageElementPlaceholderText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test navigate to main feed after signup")
    @ParameterizedTest(name = "Test name {0}, username {1}, email {2} and password {3}")
    @CsvSource({
            "Mi, Minna, minna@example.com, A1aaaaaa",
            "MinnaMinnaMinnaMinnaMinna, Minna, minna@example.com, A1aaaaaa",
            "Minna, Min, minna@example.com, A1aaaaaa",
            "Minna, MinnaMinnaMi, minna@example.com, A1aaaaaa",
            "Minna, MinnaMinnaM1, minna@example.com, A1aaaaaa",
            "Minna, Minna, 1@e.co, A1aaaaaa",
            "Minna, Minna, minna@example.com, A1aaaaaa",
            "Minna, Minna, minna@example.com, AAAAAA1a",
            "Minna, Minna, minna@example.com, A111111a",
    })
    void testSuccessfulSignup(String name, String username, String email, String password) {
        // Act
        signupPage.resetDbData();
        signupPage.navigate();
        signupPage.completeSignupFormAndSubmit(name, username, email, password);
        String actualPageTitle = signupPage.getPageTitleAfterSignup();
        // Assert
        assertEquals("Gallery", actualPageTitle);
    }
    @DisplayName("Test error message after failed signup")
    @ParameterizedTest(name = "Test name {0}, username {1}, email {2}, password {3} and expect {4}")
    @CsvSource({
            "AnikaAnikaAnikaAnikaAnikaA, Anika, anika@example.com, Password123, Name can be a maximum of 25 characters",
            "Anika, An, anika@example.com, Password123, Username must be between 3 and 12 characters",
            "Anika, AnikaAnikaAni, anika@example.com, Password123, Username must be between 3 and 12 characters",
            "Anika, Anika!, anika@example.com, Password123, Username must be alphanumeric",
            "Anika, @nika, anika@example.com, Password123, Username must be alphanumeric",
            "Anika, An?ka, anika@example.com, Password123, Username must be alphanumeric",
            "Anika, An-ka, anika@example.com, Password123, Username must be alphanumeric",
            "Anika, Anika, @example.com, Password123, Please enter a valid email",
            "Anika, Anika, a@ex.c, Password123, Please enter a valid email",
            "Anika, Anika, a@ex..co, Password123, Please enter a valid email",
            "Anika, Anika, a@@ex.co, Password123, Please enter a valid email",
            "Anika, Anika, anika@example.com, Password123@, Password may not contain symbols except ! and ?",
            "Anika, Anika, anika@example.com, Password123£, Password may not contain symbols except ! and ?",
            "Anika, Anika, anika@example.com, Password-123, Password may not contain symbols except ! and ?",
            "Anika, Anika, anika@example.com, Pass123, Password must be at least 8 characters= 1 uppercase= 1 lowercase= and 1 number",
            "Anika, Anika, anika@example.com, paaaaaa1, Password must be at least 8 characters= 1 uppercase= 1 lowercase= and 1 number",
            "Anika, Anika, anika@example.com, Paaaaaaa, Password must be at least 8 characters= 1 uppercase= 1 lowercase= and 1 number",
            "Anika, Anika, anika@example.com, PAAAAAA1, Password must be at least 8 characters= 1 uppercase= 1 lowercase= and 1 number",
            "Anika, Anika, anika@example.com, P1231231, Password must be at least 8 characters= 1 uppercase= 1 lowercase= and 1 number",
    })
    void testFailedSignup(String name, String username, String email, String password, String expectedErrorMessage) {
        // Act
        signupPage.completeSignupFormAndSubmit(name, username, email, password);
        String actualErrorMessage = signupPage.getErrorAfterFailedSignup();
        expectedErrorMessage = expectedErrorMessage.replaceAll("=", ",");
        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @DisplayName("Test signup button disabled until all fields filled")
    @ParameterizedTest(name = "Test name {0}, username {1}, email {2} and password {3}")
    @CsvSource({
            ", Anika, anika@example.com, Password123",
            "Anika, , anika@example.com, Password123",
            "Anika, Anika, , Password123",
            "Anika, Anika, anika@example.com, ",
    })
    void testBlankFieldsWhenLoggingIn(String name, String username, String email, String password) {
        // Act
        signupPage.completeSignupFormAndSubmit(name, username, email, password);
        Boolean signupBtnIsDisabled = signupPage.checkSignupBtnIsDisabled();
        // Assert
        assertEquals(true, signupBtnIsDisabled);
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
