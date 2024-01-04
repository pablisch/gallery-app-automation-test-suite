package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GalleryNavbar {
     protected WebDriver driver;
     private final By navLogoBy = By.cssSelector("img[id='nav-logo']");
     private final By navTitleBy = By.cssSelector("h1[id='nav-title-text']");
     private final By loginButtonBy = By.cssSelector("a[id='login-navlink']");
     private final By signupButtonBy = By.cssSelector("a[id='signup-navlink']");
     private final By logoutButtonBy = By.cssSelector("a[id='logout-navlink']");
     private final By uploadImageButtonBy = By.cssSelector("a[id='add-image-navlink']");
     private final By settingsIconBy = By.cssSelector("a[id='settings-navlink']");
     private final By userSettingsIconBy = By.cssSelector("a[id='user-settings-navlink']");

     public GalleryNavbar(WebDriver driver) {
          this.driver = driver;
     }

     public By getNavElementBy(String identifier) {
          return switch (identifier) {
               case "navLogo" -> navLogoBy;
               case "navTitle" -> navTitleBy;
               case "loginButton" -> loginButtonBy;
               case "signupButton" -> signupButtonBy;
               case "logOutButton" -> logoutButtonBy;
               case "uploadImageButton" -> uploadImageButtonBy;
               case "settingsIcon" -> settingsIconBy;
               case "userSettingsIcon" -> userSettingsIconBy;
               default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
          };
     }
     public Boolean checkNavElementPresence(String identifier) {
          return driver.findElement(getNavElementBy(identifier)).isDisplayed();
     }
     public String getNavElementText(String identifier) {
          WebElement element = driver.findElement(getNavElementBy(identifier));
          return element.getText();
     }
}
