package gallery.app.automation.test.suite;

import org.openqa.selenium.By;

public interface GalleryNavbar {
     final By navLogo = By.cssSelector("img[id='nav-logo']");
     final By navTitle = By.cssSelector("h1[id='nav-title-text']");
     final By logInButton = By.cssSelector("a[id='login-navlink']");
     final By signUpButton = By.cssSelector("a[id='signup-navlink']");
     final By logOutButton = By.cssSelector("a[id='logout-navlink']");
     final By uploadImageButton = By.cssSelector("a[id='add-image-navlink']");
     final By settingsButton = By.cssSelector("a[id='settings-navlink']");
     final By userSettingsButton = By.cssSelector("a[id='user-settings-navlink']");
}
