package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GalleryLoginPage {
    protected WebDriver driver;

    private final By loginTitleBy = By.cssSelector("h1[id='login-title']");
    private final By loginUsernameLabelBy = By.cssSelector("label[for='login-username-input']");
    private final By loginUsernameInputFieldBy = By.cssSelector("input[id='login-username-input']");
    private final By loginPasswordLabelBy = By.cssSelector("label[for='login-password-input']");
    private final By loginPasswordInputFieldBy = By.cssSelector("input[id='login-password-input']");
    private final By loginSubmitButtonBy = By.cssSelector("button[id='login-submit-button']");
    private final By signUpTitleBy = By.cssSelector("h1[id='signup-title']");
    private final By signUpNameInputFieldBy = By.cssSelector("input[id='signup-name-input']");
    private final By signUpUsernameInputFieldBy = By.cssSelector("input[id='signup-username-input']");
    private final By signUpEmailInputFieldBy = By.cssSelector("input[id='signup-email-input']");
    private final By signUpPasswordInputFieldBy = By.cssSelector("input[id='signup-password-input']");
    private final By signUpAvatarButtonBy = By.cssSelector("button[id='avatar-image-upload-select']");
    private final By signUpSubmitButtonBy = By.cssSelector("button[id='sign-up-submit-button']");
    private final By imageUploadTitleBy = By.cssSelector("h1[id='image-upload-title']");
    private final By imageUploadSubmitButtonBy = By.cssSelector("button[id='image-upload-submit-btn']");

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
            case "loginSubmitButton" -> loginSubmitButtonBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public Boolean checkLoginElementPresence(String identifier) {
        return driver.findElement(getLoginPageElementBy(identifier)).isDisplayed();
    }
    public String getLoginElementText(String identifier) {
        WebElement element = driver.findElement(getLoginPageElementBy(identifier));
        return element.getText();
    }












//    public void addNewTodoItem(String todoValue) {
//        WebElement newTodoInput = driver.findElement(newTodoInputBy);
//        newTodoInput.sendKeys(todoValue);
//        newTodoInput.sendKeys(Keys.ENTER);
//    }

    //    public String getTodoText(int index) {
//        WebElement todo = driver.findElement(getTodoLabelSelector(index));
//        return todo.getText();
//    }



}
