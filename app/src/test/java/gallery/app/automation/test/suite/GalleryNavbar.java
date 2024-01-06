package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GalleryNavbar {
     protected WebDriver driver;
     private final By navLogoBy = By.cssSelector("img[id='nav-logo']");
     private final By navTitleBy = By.cssSelector("h1[id='nav-title-text']");
     private final By loginBtnBy = By.cssSelector("a[id='login-navlink']");
     private final By signupBtnBy = By.cssSelector("a[id='signup-navlink']");
     private final By logoutBtnBy = By.cssSelector("a[id='logout-navlink']");
     private final By uploadImageBtnBy = By.cssSelector("a[id='add-image-navlink']");
     private final By settingsIconBy = By.cssSelector("a[id='settings-navlink']");
     private final By userSettingsIconBy = By.cssSelector("a[id='user-settings-navlink']");

     public GalleryNavbar(WebDriver driver) {
          this.driver = driver;
     }

     public By getElementBy(String identifier) {
          return switch (identifier) {
               case "navLogo" -> navLogoBy;
               case "navTitle" -> navTitleBy;
               case "loginBtn" -> loginBtnBy;
               case "signupBtn" -> signupBtnBy;
               case "logoutBtn" -> logoutBtnBy;
               case "uploadImageBtn" -> uploadImageBtnBy;
               case "settingsIcon" -> settingsIconBy;
               case "userSettingsIcon" -> userSettingsIconBy;
               default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
          };
     }
     public void hoverOverElement(String identifier) {
          WebElement image = driver.findElement(getElementBy(identifier));
          Actions actions = new Actions(driver);
          actions.moveToElement(image).build().perform();
     }
     public Boolean checkNavElementPresence(String identifier) {
          return driver.findElement(getElementBy(identifier)).isDisplayed();
     }
     public String getNavElementText(String identifier) {
          WebElement element = driver.findElement(getElementBy(identifier));
          return element.getText();
     }
     public void clickNavLink(String identifier) {
          driver.findElement(getElementBy(identifier)).click();
     }
}
