package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GalleryNavbar {
     protected WebDriver driver;
     private final By navLogoBy = By.cssSelector("img[id='nav-logo']");
     private final By navTitleBy = By.cssSelector("h1[id='nav-title-text']");
     private final By loginBtnBy = By.cssSelector("a[id='login-navlink']");
     private final By signupBtnBy = By.cssSelector("a[id='signup-navlink']");
     private final By logoutBtnBy = By.cssSelector("a[id='logout-navlink']");
     private final By uploadImageBtnBy = By.cssSelector("a[id='add-image-navlink']");
     private final By settingsIconBy = By.cssSelector("a[id='settings-navlink']");
     private final By userSettingsAvatarImageBy = By.cssSelector("a[id='user-settings-avatar-image-container']");
      private final By userSettingsAvatarLetterBy = By.cssSelector("a[id='user-settings-avatar-letter-container']");
     private final By dataResetBy = By.cssSelector("pre[style^='word-wrap']");
     private final By noviceMonksImageBy = By.cssSelector("img[id='image/v1703781443/moadwvgvunbnhtappmm8.jpg']");

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
               case "userSettingsAvatarImage" -> userSettingsAvatarImageBy;
               case "userSettingsAvatarLetter" -> userSettingsAvatarLetterBy;
               case "noviceMonksImage" -> noviceMonksImageBy;
               default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
          };
     }
     public void resetDbDataAndNavigateToHomePage() {
          driver.get("http://localhost:8080/api/v1.0/testData/reset");
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
          wait.until(ExpectedConditions.presenceOfElementLocated(dataResetBy));
          driver.get("http://localhost:5173");
          wait.until(ExpectedConditions.presenceOfElementLocated(noviceMonksImageBy));
     }
     public void hoverOverElement(String identifier) {
          WebElement image = driver.findElement(getElementBy(identifier));
          Actions actions = new Actions(driver);
          actions.moveToElement(image).build().perform();
     }
     public Boolean checkNavElementPresence(String identifier) {
          return driver.findElement(getElementBy(identifier)).isDisplayed();
     }
     public boolean checkPresenceOfElement(String identifier) {
          try {
               WebElement element = driver.findElement(getElementBy(identifier));
               return element.isDisplayed();
          } catch (NoSuchElementException e) {
               return false;
          }
     }
     public String getNavElementText(String identifier) {
          WebElement element = driver.findElement(getElementBy(identifier));
          return element.getText();
     }
     public void clickNavLink(String identifier) {
          driver.findElement(getElementBy(identifier)).click();
     }
     public void logOut() {
          if (checkPresenceOfElement("logoutBtn")) {
               driver.findElement(logoutBtnBy).click();
          }
     }

}
