package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GallerySingleImageLoggedOutTest {
    private static ChromeDriver driver;
    private static GalleryHomePage homePage;
    private static GallerySingleImagePage imagePage;
    private static GalleryNavbar navbar;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new GalleryHomePage(driver);
        imagePage = new GallerySingleImagePage(driver);
        navbar = new GalleryNavbar(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        navbar.resetDbDataAndNavigateToHomePage();
        navbar.logOut();
    }

    @BeforeEach
    void loadGalleryHomePage() {
        homePage.navigate();
    }

    @DisplayName("Test title of single image page when accessed from main feed")
    @ParameterizedTest(name = "Test title of {0} image page to be {1}")
    @CsvSource({
            "indianTruck, Gallery - A Tata truck",
            "girlAndGoat, Gallery - Mariana and a goat",
            "legTattoos, Gallery - Tree of life leg tattoos",
            "indianPublicBin, Gallery - Public bin in McLeod Ganj% India",
            "noviceBuddhistMonks, Gallery - Novice monks in Ladakh% India",
    })
    void testPageTitleFromFeedPage(String identifier, String expectedTitle) {
        // Arrange
        homePage.clickElement(identifier);
        String pageTitle = imagePage.getPageTitle(identifier);
        expectedTitle = expectedTitle.replaceAll("%", ",");
        // Assert
        assertEquals(expectedTitle, pageTitle);
    }
    @DisplayName("Test title of single image page when accessed by URL")
    @ParameterizedTest(name = "Test title of {0} image page to be {1}")
    @CsvSource({
            "indianTruck, Gallery - A Tata truck",
            "girlAndGoat, Gallery - Mariana and a goat",
            "legTattoos, Gallery - Tree of life leg tattoos",
            "indianPublicBin, Gallery - Public bin in McLeod Ganj% India",
            "noviceBuddhistMonks, Gallery - Novice monks in Ladakh% India",
    })
    void testPageTitleFromUrlNavigation(String identifier, String expectedTitle) {
        // Arrange
        imagePage.navigateToImagePage(identifier, expectedTitle);
        String pageTitle = imagePage.getPageTitle(identifier);
        expectedTitle = expectedTitle.replaceAll("%", ",");
        // Assert
        assertEquals(expectedTitle, pageTitle);
    }

//    @DisplayName("Test presence of image elements")
//    @ParameterizedTest(name = "Test presence of {0} image")
//    @CsvSource({
//            "indianTruck",
//            "girlAndGoat",
//            "legTattoos",
//            "indianPublicBin",
//            "noviceBuddhistMonks"
//    })
//    void testImagePresence(String identifier) {
//        assertTrue(homePage.checkPresenceOfElement(identifier));
//    }
//    @Test
//    void testHoveringOverTruckImage() {
//        navbar.hoverOverElement("navTitle");
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
//        homePage.hoverOverElement("indianTruck");
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarLetter"));
//        assertEquals("Martha", homePage.getElementText("hoverInfoUsername"));
//        assertEquals("0", homePage.getElementText("hoverInfoCommentsNum"));
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoCommentsIcon"));
//        assertEquals("0", homePage.getElementText("hoverInfoLikesNum"));
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoLikesIcon"));
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoLikesOutlineIcon"));
//        navbar.hoverOverElement("navTitle");
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
//    }
//    @Test
//    void testHoveringOverMonksImage() {
//        navbar.hoverOverElement("navTitle");
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
//        homePage.hoverOverElement("noviceBuddhistMonks");
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoAvatarLetter"));
//        assertEquals("Pablito", homePage.getElementText("hoverInfoUsername"));
//        assertEquals("2", homePage.getElementText("hoverInfoCommentsNum"));
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoCommentsIcon"));
//        assertEquals("1", homePage.getElementText("hoverInfoLikesNum"));
//        assertTrue(homePage.checkPresenceOfElement("hoverInfoLikesIcon"));
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoLikesOutlineIcon"));
//        navbar.hoverOverElement("navTitle");
//        assertFalse(homePage.checkPresenceOfElement("hoverInfoContainer"));
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
