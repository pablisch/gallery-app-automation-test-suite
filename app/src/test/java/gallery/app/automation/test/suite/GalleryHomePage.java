package gallery.app.automation.test.suite;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;

public class GalleryHomePage {
    protected WebDriver driver;

    private final By indianTruckImageBy = By.cssSelector("img[id='image/v1703781615/octm7jk8lutkexrnmm65.jpg']");
    private final By girlAndGoatImageBy = By.cssSelector("img[id='image/v1703781467/polmlpk8iugb6lrfndue.jpg']");
    private final By legTattoosImageBy = By.cssSelector("img[id='image/v1703781461/s7w8zritrmj5qzj6yays.jpg']");
    private final By dustbinImageBy = By.cssSelector("img[id='image/v1703781451/lb2jtrormhzlrhdpdhe7.jpg']");
    private final By noviceMonksImageBy = By.cssSelector("img[id='image/v1703781443/moadwvgvunbnhtappmm8.jpg']");
    private final By signUpTitleBy = By.cssSelector("h1[id='signup-title']");
    private final By signUpNameInputFieldBy = By.cssSelector("input[id='signup-name-input']");
    private final By signUpUsernameInputFieldBy = By.cssSelector("input[id='signup-username-input']");
    private final By signUpEmailInputFieldBy = By.cssSelector("input[id='signup-email-input']");
    private final By signUpPasswordInputFieldBy = By.cssSelector("input[id='signup-password-input']");
    private final By signUpAvatarButtonBy = By.cssSelector("button[id='avatar-image-upload-select']");
    private final By signUpSubmitButtonBy = By.cssSelector("button[id='sign-up-submit-button']");
    private final By imageUploadTitleBy = By.cssSelector("h1[id='image-upload-title']");
    private final By imageUploadSubmitButtonBy = By.cssSelector("button[id='image-upload-submit-btn']");

    public GalleryHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Format By for indexed todo item (label text)
//    By getTodoLabelSelector(int index) {
//        String todoLabelSelector = String.format(".todo-list li:nth-child(%d) label", index );
//        By todoLabelSelectorBy;
//        todoLabelSelectorBy = By.cssSelector(todoLabelSelector);
//        return todoLabelSelectorBy;
//    }

//    public void takeScreenshot(String desiredPath) throws Exception {
//        TakesScreenshot screenshot = ((TakesScreenshot)this.driver);
//        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
//        File targetFile = new File(desiredPath);
//        FileUtils.copyFile(screenshotFile, targetFile);
//    }

    public void navigate() {
        driver.get("http://localhost:5173");
    }
    public By getImageBy(String identifier) {
        return switch (identifier) {
            case "indianTruck" -> indianTruckImageBy;
            case "girlAndGoat" -> girlAndGoatImageBy;
            case "legTattoos" -> legTattoosImageBy;
            case "IndianPublicBin" -> dustbinImageBy;
            case "NoviceBuddhistMonks" -> noviceMonksImageBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public Boolean checkImagePresence(String identifier) {
        return driver.findElement(getImageBy(identifier)).isDisplayed();
    }



    public String getPageTitle() {
        return driver.getTitle();
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
