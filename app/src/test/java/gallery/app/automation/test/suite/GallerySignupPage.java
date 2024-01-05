package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GallerySignupPage {
    protected WebDriver driver;

    private final By errorMessageBy = By.cssSelector("p[id='error-message']");
    private final By signupTitleBy = By.cssSelector("h1[id='signup-title']");
    private final By signupNameLabelBy = By.cssSelector("label[for='signup-name-input']");
    private final By signupNameInputFieldBy = By.cssSelector("input[id='signup-name-input']");
    private final By signupUsernameLabelBy = By.cssSelector("label[for='signup-username-input']");
    private final By signupUsernameInputFieldBy = By.cssSelector("input[id='signup-username-input']");
    private final By signupEmailLabelBy = By.cssSelector("label[for='signup-email-input']");
    private final By signupEmailInputFieldBy = By.cssSelector("input[id='signup-email-input']");
    private final By signupPasswordLabelBy = By.cssSelector("label[for='signup-password-input']");
    private final By signupPasswordInputFieldBy = By.cssSelector("input[id='signup-password-input']");
    private final By signupAvatarBtnLabelBy = By.cssSelector("label[for='file-input']");
    private final By signupAvatarBtnBy = By.cssSelector("button[id='avatar-image-upload-select']");
    private final By signupSubmitBtnBy = By.cssSelector("button[id='signup-submit-button']");
    private final By dataResetBy = By.cssSelector("pre[style^='word-wrap']");


    public GallerySignupPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() { driver.get("http://localhost:5173/signup"); }
    public void resetDbData() {
        driver.get("http://localhost:8080/api/v1.0/testData/reset");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(dataResetBy));
    }
    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.titleIs("Gallery Sign Up"));
        return driver.getTitle();
    }
    public By getSignupPageElementBy(String identifier) {
        return switch (identifier) {
            case "signupTitle" -> signupTitleBy;
            case "signupNameLabel" -> signupNameLabelBy;
            case "signupNameInputField" -> signupNameInputFieldBy;
            case "signupUsernameLabel" -> signupUsernameLabelBy;
            case "signupUsernameInputField" -> signupUsernameInputFieldBy;
            case "signupEmailLabel" -> signupEmailLabelBy;
            case "signupEmailInputField" -> signupEmailInputFieldBy;
            case "signupPasswordLabel" -> signupPasswordLabelBy;
            case "signupPasswordInputField" -> signupPasswordInputFieldBy;
            case "signupAvatarBtnLabel" -> signupAvatarBtnLabelBy;
            case "signupAvatarBtn" -> signupAvatarBtnBy;
            case "signupSubmitBtn" -> signupSubmitBtnBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public Boolean checkSignupPageElementPresence(String identifier) {
        return driver.findElement(getSignupPageElementBy(identifier)).isDisplayed();
    }
    public String getSignupPageElementText(String identifier) {
        WebElement element = driver.findElement(getSignupPageElementBy(identifier));
        return element.getText();
    }
    public String getSignupPageElementPlaceholderText(String identifier) {
        WebElement element = driver.findElement(getSignupPageElementBy(identifier));
        return element.getAttribute("placeholder");
    }
    public void completeSignupFormAndSubmit(String name, String username, String email, String password) {
        if (name != null) {
            WebElement nameField = driver.findElement(signupNameInputFieldBy);
            nameField.click();
            nameField.sendKeys(name);
        }
        if (username != null) {
            WebElement usernameField = driver.findElement(signupUsernameInputFieldBy);
            usernameField.click();
            usernameField.sendKeys(username);
        }
        if (email != null) {
            WebElement emailField = driver.findElement(signupEmailInputFieldBy);
            emailField.click();
            emailField.sendKeys(email);
        }
        if (password != null) {
            WebElement passwordField = driver.findElement(signupPasswordInputFieldBy);
            passwordField.click();
            passwordField.sendKeys(password);
        }
        WebElement signupBtn = driver.findElement(signupSubmitBtnBy);
        signupBtn.click();
    }
    public String getPageTitleAfterSignup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.titleIs("Gallery"));
        return driver.getTitle();
    }
    public String getErrorAfterFailedSignup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(errorMessageBy));
        WebElement errorMessage = driver.findElement(errorMessageBy);
        return errorMessage.getText();
    }
    public Boolean checkSignupBtnIsDisabled() {
        WebElement signupBtn = driver.findElement(signupSubmitBtnBy);
        return !signupBtn.isEnabled();
    }

}
