package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GalleryLoginPage {
    protected WebDriver driver;

    private final By loginTitleBy = By.cssSelector("h1[id='login-title']");
    private final By loginUsernameLabelBy = By.cssSelector("label[for='login-username-input']");
    private final By loginUsernameInputFieldBy = By.cssSelector("input[id='login-username-input']");
    private final By loginPasswordLabelBy = By.cssSelector("label[for='login-password-input']");
    private final By loginPasswordInputFieldBy = By.cssSelector("input[id='login-password-input']");
    private final By loginSubmitBtnBy = By.cssSelector("button[id='login-submit-btn']");
    private final By errorMessageBy = By.cssSelector("p[id='error-message']");
    private final By imageUploadTitleBy = By.cssSelector("h1[id='image-upload-title']");
    private final By imageUploadSubmitBtnBy = By.cssSelector("button[id='image-upload-submit-btn']");

    public GalleryLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() { driver.get("http://localhost:5173/login"); }
    public String getPageTitle() { return driver.getTitle(); }
    public By getLoginPageElementBy(String identifier) {
        return switch (identifier) {
            case "loginTitle" -> loginTitleBy;
            case "loginUsernameLabel" -> loginUsernameLabelBy;
            case "loginUsernameInputField" -> loginUsernameInputFieldBy;
            case "loginPasswordLabel" -> loginPasswordLabelBy;
            case "loginPasswordInputField" -> loginPasswordInputFieldBy;
            case "loginSubmitBtn" -> loginSubmitBtnBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public Boolean checkLoginPageElementPresence(String identifier) {
        return driver.findElement(getLoginPageElementBy(identifier)).isDisplayed();
    }
    public String getLoginPageElementText(String identifier) {
        WebElement element = driver.findElement(getLoginPageElementBy(identifier));
        return element.getText();
    }
    public String getLoginPageElementPlaceholderText(String identifier) {
        WebElement element = driver.findElement(getLoginPageElementBy(identifier));
        return element.getAttribute("placeholder");
    }
    public void completeLoginFormAndSubmit(String username, String password) {
        if (username != null) {
            WebElement usernameField = driver.findElement(loginUsernameInputFieldBy);
            usernameField.click();
            usernameField.sendKeys(username);
        }
        if (password != null) {
            WebElement passwordField = driver.findElement(loginPasswordInputFieldBy);
            passwordField.click();
            passwordField.sendKeys(password);
        }
        WebElement loginBtn = driver.findElement(loginSubmitBtnBy);
        loginBtn.click();
    }
    public String getPageTitleAfterLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("Gallery"));
        return driver.getTitle();
    }
    public String getErrorAfterFailedLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageBy));
        WebElement errorMessage = driver.findElement(errorMessageBy);
        return errorMessage.getText();
    }
    public Boolean checkLoginBtnIsDisabled() {
        WebElement loginBtn = driver.findElement(loginSubmitBtnBy);
        return !loginBtn.isEnabled();
    }

}
