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
        homePage.navigate();
        navbar.logOut();
    }


    @Test
    void testDeletingTruckImageWhenLoggedIn() {
        navbar.clickNavLink("loginBtn");
        loginPage.completeLoginFormAndSubmit("Martha", "Password123");
        homePage.hoverOverElement("noviceBuddhistMonks");
        assertTrue(homePage.checkPresenceOfElement("hoverInfoAvatarImage"));
        assertFalse(homePage.checkPresenceOfElement("deleteIcon"));
        homePage.hoverOverElement("indianTruck");
        assertTrue(homePage.checkPresenceOfElement("deleteIcon"));
        homePage.clickElement("deleteIcon");
        assertTrue(homePage.checkPresenceOfElement("deleteIcon"));

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
