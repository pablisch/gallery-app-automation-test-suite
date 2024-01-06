package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryHomeLoggedInTest {
    private static ChromeDriver driver;
    private static GalleryHomePage homePage;
    private static GalleryNavbar navbar;
    private static GalleryLoginPage loginPage;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new GalleryHomePage(driver);
        navbar = new GalleryNavbar(driver);
        loginPage = new GalleryLoginPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeEach
    void loadGalleryHomePageAndLogOut() {
        navbar.resetDbDataAndNavigateToHomePage();
        navbar.logOut();
    }

    @Test
    void testNavElementPresenceWhenLoggedInWithAvatarImage() {
        // Log in as Billy
        navbar.clickNavLink("loginBtn");
        loginPage.completeLoginFormAndSubmit("Billy", "Password123");
        assertTrue(navbar.checkPresenceOfElement("navLogo"));
        assertFalse(navbar.checkPresenceOfElement("loginBtn"));
        assertFalse(navbar.checkPresenceOfElement("signupBtn"));
        assertTrue(navbar.checkPresenceOfElement("logoutBtn"));
        assertTrue(navbar.checkPresenceOfElement("uploadImageBtn"));
        assertFalse(navbar.checkPresenceOfElement("settingsIcon"));
        assertTrue(navbar.checkPresenceOfElement("userSettingsAvatarImage"));
        assertFalse(navbar.checkPresenceOfElement("userSettingsAvatarLetter"));
    }
    @Test
    void testNavElementPresenceWhenLoggedInWithoutAvatarImage() {
        // Log in as Martha
        navbar.clickNavLink("loginBtn");
        loginPage.completeLoginFormAndSubmit("Martha", "Password123");
        assertTrue(navbar.checkPresenceOfElement("navLogo"));
        assertFalse(navbar.checkPresenceOfElement("loginBtn"));
        assertFalse(navbar.checkPresenceOfElement("signupBtn"));
        assertTrue(navbar.checkPresenceOfElement("logoutBtn"));
        assertTrue(navbar.checkPresenceOfElement("uploadImageBtn"));
        assertFalse(navbar.checkPresenceOfElement("settingsIcon"));
        assertTrue(navbar.checkPresenceOfElement("userSettingsAvatarLetter"));
        assertFalse(navbar.checkPresenceOfElement("userSettingsAvatarImage"));
    }
    @Test
    void testDeletingTruckImageWhenLoggedIn() {
        // Log in as Martha
        navbar.clickNavLink("loginBtn");
        loginPage.completeLoginFormAndSubmit("Martha", "Password123");
        // Assert that delete is absent from other user's images
        homePage.hoverOverElement("noviceBuddhistMonks");
        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
        assertFalse(homePage.checkPresenceOfElement("deleteIcon"));
        homePage.hoverOverElement("legTattoos");
        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
        assertFalse(homePage.checkPresenceOfElement("deleteIcon"));
        // Assert that delete is present on own images, cancel delete and then confirm delete
        homePage.hoverOverElement("indianTruck");
        assertTrue(homePage.checkPresenceOfElement("deleteIcon"));
        homePage.clickElement("deleteIcon");
        assertTrue(homePage.checkPresenceOfElement("deleteBtnContainer"));
        homePage.clickElement("cancelDeleteImageBtn");
        assertFalse(homePage.checkPresenceOfElement("deleteBtnContainer"));
        homePage.clickElement("deleteIcon");
        assertTrue(homePage.checkPresenceOfElement("deleteBtnContainer"));
        homePage.clickElement("confirmDeleteImageBtn");
        // Assert absence of deleted image
        homePage.waitForElementToNotBePresent("indianTruck");
        assertFalse(homePage.checkPresenceOfElement("indianTruck"));
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
