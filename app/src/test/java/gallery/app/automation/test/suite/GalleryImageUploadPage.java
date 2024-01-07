package gallery.app.automation.test.suite;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class GalleryImageUploadPage {
    protected WebDriver driver;

    private final By imageUploadFormBy = By.cssSelector("form[id='image-upload-form']");
    private final By imageUploadFormTitleBy = By.cssSelector("h1[id='image-upload-title']");
    private final By imageUploadSelectBtnBy = By.cssSelector("button[id='image-upload-select-btn']");
    private final By fileInputBy = By.cssSelector("input[id='file-input']");
    private final By imageUploadDescriptionLabelBy = By.cssSelector("label[for='image-upload-description-input']");
    private final By imageUploadDescriptionInputBy = By.cssSelector("input[id='image-upload-description-input']");
    private final By imageUploadSubmitBtnBy = By.cssSelector("button[id='image-upload-submit-btn']");
    private final By descriptionNoteBy = By.cssSelector("p[id='why-description-note']");


    public GalleryImageUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("http://localhost:5173/upload");
    }
    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.titleIs("Gallery Image Upload"));
        return driver.getTitle();
    }
    public By getElementBy(String identifier) {
        return switch (identifier) {
            case "imageUploadForm" -> imageUploadFormBy;
            case "imageUploadFormTitle" -> imageUploadFormTitleBy;
            case "imageUploadSelectBtn" -> imageUploadSelectBtnBy;
            case "fileInput" -> fileInputBy;
            case "imageUploadDescriptionLabel" -> imageUploadDescriptionLabelBy;
            case "imageUploadDescriptionInput" -> imageUploadDescriptionInputBy;
            case "imageUploadSubmitBtn" -> imageUploadSubmitBtnBy;
            case "descriptionNote" -> descriptionNoteBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public void hoverOverElement(String identifier) {
        WebElement image = driver.findElement(getElementBy(identifier));
        Actions actions = new Actions(driver);
        actions.moveToElement(image).build().perform();
    }
    public boolean checkPresenceOfElement(String identifier) {
        try {
            WebElement element = driver.findElement(getElementBy(identifier));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String getElementText(String identifier) {
        WebElement element = driver.findElement(getElementBy(identifier));
        return element.getText();
    }
    public void clickElement(String identifier) {
        driver.findElement(getElementBy(identifier)).click();
    }

    public void waitForElementToNotBePresent(String identifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(getElementBy(identifier)));
    }
    public void waitForElementToBePresent(String identifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.presenceOfElementLocated(getElementBy(identifier)));
    }
    public void waitForElementNotToBeClickable(String identifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getElementBy(identifier))));
    }
    public void typeInElement(String identifier, String text) {
        WebElement element = driver.findElement(getElementBy(identifier));
        element.sendKeys(text);
    }
    public void selectFile(String path) {
        WebElement fileInput = driver.findElement(fileInputBy);
        fileInput.sendKeys(path);
    }
    public void typeStringWithRobot(String text) {
        try {
            Robot robot = new Robot();
            typeString(robot, text);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
        }
    }
    public void uploadFileUsingRobot(String filePath) {
        // Click the file input element to open the system dialogue box
        driver.findElement(imageUploadSelectBtnBy).click();

        try {
            // Wait for a short time to ensure the system dialogue box is opened
            Thread.sleep(1000);

            // Set the clipboard with the file path
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            // Use Robot to perform keyboard shortcuts (Cmd + V for macOS, Ctrl + V for Windows)
            Robot robot = new Robot();
            if (isMacOS()) {
                robot.keyPress(KeyEvent.VK_META);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_META);
            } else {
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }

            // Press Enter to confirm the file selection
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isMacOS() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

}
