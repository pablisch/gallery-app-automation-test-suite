package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryHomeLoggedOutTest {
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
        navbar.resetDbDataAndNavigateToHomePage();
        navbar.logOut();
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
    @DisplayName("Test presence of navbar elements")
    @ParameterizedTest(name = "Test presence of {0} navbar element to be {1}")
    @CsvSource({
            "navLogo, true",
            "settingsIcon, true",
            "loginBtn, true",
            "signupBtn, true",
            "logoutBtn, false",
            "uploadImageBtn, false",
            "userSettingsAvatarImage, false",
            "userSettingsAvatarLetter, false",
    })
    void testNavElementPresence(String identifier, boolean expectedPresence) {
        assertEquals(expectedPresence, navbar.checkPresenceOfElement(identifier));
    }
    @DisplayName("Test text of navbar elements")
    @ParameterizedTest(name = "Test text of {0} is {1}")
    @CsvSource({
            "navTitle, Gallery",
            "loginBtn, Log in",
            "signupBtn, Sign up"
    })
    void testNavElementText(String identifier, String expectedText) {
        // Arrange
        String element = navbar.getNavElementText(identifier);
        // Assert
        assertEquals(expectedText, element);
    }
    @DisplayName("Test presence of image elements")
    @ParameterizedTest(name = "Test presence of {0} image")
    @CsvSource({
            "indianTruck",
            "girlAndGoat",
            "legTattoos",
            "indianPublicBin",
            "noviceBuddhistMonks"
    })
    void testImagePresence(String identifier) {
        assertTrue(homePage.checkPresenceOfElement(identifier));
    }
    @Test
    void testHoveringOverTruckImage() {
        navbar.hoverOverElement("navTitle");
        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
        homePage.hoverOverElement("indianTruck");
        assertFalse(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarLetter"));
        assertEquals("Martha", homePage.getElementText("hoverInfoUsername"));
        assertEquals("0", homePage.getElementText("hoverInfoCommentsNum"));
        assertTrue(homePage.checkPresenceOfElement("hoverInfoCommentsIcon"));
        assertEquals("0", homePage.getElementText("hoverInfoLikesNum"));
        assertFalse(homePage.checkPresenceOfElement("hoverInfoLikesIcon"));
        assertTrue(homePage.checkPresenceOfElement("hoverInfoLikesOutlineIcon"));
        navbar.hoverOverElement("navTitle");
        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
    }
    @Test
    void testHoveringOverMonksImage() {
        navbar.hoverOverElement("navTitle");
        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
        homePage.hoverOverElement("noviceBuddhistMonks");
        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
        assertFalse(homePage.checkPresenceOfElement("hoverInfoAvatarLetter"));
        assertEquals("Pablito", homePage.getElementText("hoverInfoUsername"));
        assertEquals("2", homePage.getElementText("hoverInfoCommentsNum"));
        assertTrue(homePage.checkPresenceOfElement("hoverInfoCommentsIcon"));
        assertEquals("1", homePage.getElementText("hoverInfoLikesNum"));
        assertTrue(homePage.checkPresenceOfElement("hoverInfoLikesIcon"));
        assertFalse(homePage.checkPresenceOfElement("hoverInfoLikesOutlineIcon"));
        navbar.hoverOverElement("navTitle");
        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
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
