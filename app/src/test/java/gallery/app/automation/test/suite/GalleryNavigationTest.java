package gallery.app.automation.test.suite;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GalleryNavigationTest {
    private static ChromeDriver driver;
    private static GalleryHomePage homePage;
    private static GalleryNavbar navbar;
    private static GalleryLoginPage loginPage;
    private static GallerySignupPage signupPage;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new GalleryHomePage(driver);
        navbar = new GalleryNavbar(driver);
        loginPage = new GalleryLoginPage(driver);
        signupPage = new GallerySignupPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @BeforeEach
    void loadGalleryHomePage() {
        homePage.navigate();
    }

    @Test
    void testNavigationUsingNavLinks() {
        assertEquals("Gallery", homePage.getPageTitle());
        navbar.clickNavLink("loginBtn");
        assertEquals("Gallery Login", loginPage.getPageTitle());
        navbar.clickNavLink("signupBtn");
        driver.navigate().back();
        assertEquals("Gallery Login", loginPage.getPageTitle());
        driver.navigate().forward();
        assertEquals("Gallery Sign Up", signupPage.getPageTitle());
        navbar.clickNavLink("navLogo");
        assertEquals("Gallery", homePage.getPageTitle());
        driver.navigate().back();
        assertEquals("Gallery Sign Up", signupPage.getPageTitle());
        navbar.clickNavLink("navTitle");
        assertEquals("Gallery", homePage.getPageTitle());
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
