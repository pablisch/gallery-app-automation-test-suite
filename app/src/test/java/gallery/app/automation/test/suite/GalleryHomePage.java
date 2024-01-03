package gallery.app.automation.test.suite;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;

public class GalleryHomePage {
    protected WebDriver driver;
    private final By navLogo = By.cssSelector("img[id='nav-logo']");
    private final By navTitle = By.cssSelector("h1[id='nav-title-text']");
    private final By logInButton = By.cssSelector("a[id='login-navlink']");
    private final By signUpButton = By.cssSelector("a[id='signup-navlink']");
    private final By logOutButton = By.cssSelector("a[id='logout-navlink']");
    private final By uploadImageButton = By.cssSelector("a[id='add-image-navlink']");
    private final By settingsButton = By.cssSelector("a[id='settings-navlink']");
    private final By userSettingsButton = By.cssSelector("a[id='user-settings-navlink']");
    private final By indianTruckImage = By.cssSelector("img[id=" +
            "'https://res.cloudinary.com/ddinmpzrr/image/upload/w_400/v1703781615/octm7jk8lutkexrnmm65.jpg']");
    private final By girlAndGoatImage = By.cssSelector("img[id=" +
            "'https://res.cloudinary.com/ddinmpzrr/image/upload/w_400/v1703781467/polmlpk8iugb6lrfndue.jpg']");
    private final By legTattoosImage = By.cssSelector("img[id=" +
            "'https://res.cloudinary.com/ddinmpzrr/image/upload/w_400/v1703781461/s7w8zritrmj5qzj6yays.jpg']");
    private final By dustbinImage = By.cssSelector("img[id=" +
            "'https://res.cloudinary.com/ddinmpzrr/image/upload/w_400/v1703781451/lb2jtrormhzlrhdpdhe7.jpg']");
    private final By noviceMonksImage = By.cssSelector("img[id=" +
            "'https://res.cloudinary.com/ddinmpzrr/image/upload/w_400/v1703781443/moadwvgvunbnhtappmm8.jpg']");
    private final By logInTitle = By.cssSelector("h1[id='login-title']");
    private final By logInUsernameInputField = By.cssSelector("input[id='login-username-input']");
    private final By logInPasswordInputField = By.cssSelector("input[id='login-password-input']");
    private final By logInSubmitButton = By.cssSelector("button[id='login-submit-button']");
    private final By signUpTitle = By.cssSelector("h1[id='signup-title']");
    private final By signUpNameInputField = By.cssSelector("input[id='signup-name-input']");
    private final By signUpUsernameInputField = By.cssSelector("input[id='signup-username-input']");
    private final By signUpEmailInputField = By.cssSelector("input[id='signup-email-input']");
    private final By signUpPasswordInputField = By.cssSelector("input[id='signup-password-input']");
    private final By signUpAvatarButton = By.cssSelector("button[id='avatar-image-upload-select']");
    private final By signUpSubmitButton = By.cssSelector("button[id='sign-up-submit-button']");
    private final By imageUploadTitle = By.cssSelector("h1[id='image-upload-title']");
    private final By imageUploadSubmitButton = By.cssSelector("button[id='image-upload-submit-btn']");

    public GalleryHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Format By for indexed todo item (label text)
    By getTodoLabelSelector(int index) {
        String todoLabelSelector = String.format(".todo-list li:nth-child(%d) label", index );
        By todoLabelSelectorBy;
        todoLabelSelectorBy = By.cssSelector(todoLabelSelector);
        return todoLabelSelectorBy;
    }

    public void takeScreenshot(String desiredPath) throws Exception {
        TakesScreenshot screenshot = ((TakesScreenshot)this.driver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

    public void navigate() {
        driver.get("http://localhost:5173");
    }

//    public void addNewTodoItem(String todoValue) {
//        WebElement newTodoInput = driver.findElement(newTodoInputBy);
//        newTodoInput.sendKeys(todoValue);
//        newTodoInput.sendKeys(Keys.ENTER);
//    }

    public String getTodoText(int index) {
        WebElement todo = driver.findElement(getTodoLabelSelector(index));
        return todo.getText();
    }
    public String getNavTitleText() {
        WebElement todo = driver.findElement(navTitle);
        return todo.getText();
    }
    public String getLogInButtonText() {
        WebElement todo = driver.findElement(navTitle);
        return todo.getText();
    }


}
