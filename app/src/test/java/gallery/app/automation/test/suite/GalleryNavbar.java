package gallery.app.automation.test.suite;

import org.openqa.selenium.By;

public interface GalleryNavbar {
     final By navLogoBy = By.cssSelector("img[id='nav-logo']");
     final By navTitleBy = By.cssSelector("h1[id='nav-title-text']");
     final By loginButtonBy = By.cssSelector("a[id='login-navlink']");
     final By signupButtonBy = By.cssSelector("a[id='signup-navlink']");
     final By logoutButtonBy = By.cssSelector("a[id='logout-navlink']");
     final By uploadImageButtonBy = By.cssSelector("a[id='add-image-navlink']");
     final By settingsIconBy = By.cssSelector("a[id='settings-navlink']");
     final By userSettingsIconBy = By.cssSelector("a[id='user-settings-navlink']");
}
