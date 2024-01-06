package gallery.app.automation.test.suite;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GallerySingleImagePage {
    protected WebDriver driver;

    private final By indianTruckImageBy = By.cssSelector("img[id='single-image/v1703781615/octm7jk8lutkexrnmm65.jpg']");
    private final By girlAndGoatImageBy = By.cssSelector("img[id='single-image/v1703781467/polmlpk8iugb6lrfndue.jpg']");
    private final By legTattoosImageBy = By.cssSelector("img[id='single-image/v1703781461/s7w8zritrmj5qzj6yays.jpg']");
    private final By dustbinImageBy = By.cssSelector("img[id='single-image/v1703781451/lb2jtrormhzlrhdpdhe7.jpg']");
    private final By noviceMonksImageBy = By.cssSelector("img[id='single-image/v1703781443/moadwvgvunbnhtappmm8.jpg']");
    private final By imageCommentsNumBy = By.cssSelector("p[id='selected-image-comments-num']");
    private final By imageCommentsIconBy = By.cssSelector("svg[id='selected-image-comments-icon']");
    private final By imageLikesNumBy = By.cssSelector("p[id='selected-image-likes-num']");
    private final By imageLikesIconBy = By.cssSelector("svg[id='selected-image-likes-icon']");
    private final By previousImageArrowBy = By.cssSelector("div[id='left-arrow']");
    private final By nextImageArrowBy = By.cssSelector("div[id='right-arrow']");
    private final By imageOwnerAvatarImageBy = By.cssSelector("img[id='image-owner-avatar-image']");
    private final By imageOwnerAvatarLetterContainerBy = By.cssSelector("div[id='image-owner-avatar-letter-container']");
    private final By imageOwnerAvatarLetterBy = By.cssSelector("h1[id='image-owner-avatar-letter']");
    private final By imageInfoBy = By.cssSelector("p[id='image-info']");
    private final By commentLikeBtnContainerBy = By.cssSelector("p[id='comment-like-btn-container']");
    private final By addCommentBtnBy = By.cssSelector("button[id='add-comment-btn']");
    private final By likeBtnBy = By.cssSelector("button[id='like-btn']");
    private final By addCommentTextareaBy = By.cssSelector("textarea[id='add-comment-textarea']");
    private final By addCommentTextareaLabelBy = By.cssSelector("label[id='add-comment-textarea-label']");
    private final By confirmAddCommentBtnBy = By.cssSelector("button[id='confirm-add-comment-btn']");
    private final By cancelAddCommentBtnBy = By.cssSelector("button[id='cancel-add-comment-btn']");
    private final By commentBoxBy = By.cssSelector("div[id='comment-box']");
    private final By commentsTitleBy = By.cssSelector("h3[id='comments-title']");
    private final By commentsListBy = By.cssSelector("ul[id='comments-list']");
    private final By monksCommentNum1By = By.cssSelector("div[id='comment-1703851886278-36']");
    private final By monksCommentNum2By = By.cssSelector("div[id='comment-1703851869598-33']");
    private final By monksCommentNum1AvatarImageBy = By.cssSelector("div[id='comment-1703851886278-36-avatar-image']");
    private final By monksCommentNum2AvatarImageBy = By.cssSelector("div[id='comment-1703851869598-33-avatar-image']");
    private final By monksCommentNum1AvatarLetterBy = By.cssSelector("div[id='comment-1703851886278-36-avatar-letter']");
    private final By monksCommentNum2AvatarLetterBy = By.cssSelector("div[id='comment-1703851869598-33-avatar-letter']");
    private final By monksCommentNum1TextBy = By.cssSelector("div[id='comment-1703851886278-36-text']");
    private final By monksCommentNum2TextBy = By.cssSelector("div[id='comment-1703851869598-33-text']");

    public GallerySingleImagePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("http://localhost:5173");
    }
    public String getPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.titleIs("Gallery"));
        return driver.getTitle();
    }
    public By getElementBy(String identifier) {
        return switch (identifier) {
            case "indianTruck" -> indianTruckImageBy;
            case "girlAndGoat" -> girlAndGoatImageBy;
            case "legTattoos" -> legTattoosImageBy;
            case "indianPublicBin" -> dustbinImageBy;
            case "noviceBuddhistMonks" -> noviceMonksImageBy;
            case "imageCommentsNum" -> imageCommentsNumBy;
            case "imageCommentsIcon" -> imageCommentsIconBy;
            case "imageLikesNum" -> imageLikesNumBy;
            case "imageLikesIcon" -> imageLikesIconBy;
            case "previousImageArrow" -> previousImageArrowBy;
            case "nextImageArrow" -> nextImageArrowBy;
            case "imageOwnerAvatarImage" -> imageOwnerAvatarImageBy;
            case "imageOwnerAvatarLetterContainer" -> imageOwnerAvatarLetterContainerBy;
            case "imageOwnerAvatarLetter" -> imageOwnerAvatarLetterBy;
            case "imageInfo" -> imageInfoBy;
            case "commentLikeBtnContainer" -> commentLikeBtnContainerBy;
            case "addCommentBtn" -> addCommentBtnBy;
            case "likeBtn" -> likeBtnBy;
            case "addCommentTextarea" -> addCommentTextareaBy;
            case "addCommentTextareaLabel" -> addCommentTextareaLabelBy;
            case "confirmAddCommentBtn" -> confirmAddCommentBtnBy;
            case "cancelAddCommentBtn" -> cancelAddCommentBtnBy;
            case "commentBox" -> commentBoxBy;
            case "commentsTitle" -> commentsTitleBy;
            case "commentsList" -> commentsListBy;
            case "monksCommentNum1" -> monksCommentNum1By;
            case "monksCommentNum2" -> monksCommentNum2By;
            case "monksCommentNum1AvatarImage" -> monksCommentNum1AvatarImageBy;
            case "monksCommentNum2AvatarImage" -> monksCommentNum2AvatarImageBy;
            case "monksCommentNum1AvatarLetter" -> monksCommentNum1AvatarLetterBy;
            case "monksCommentNum2AvatarLetter" -> monksCommentNum2AvatarLetterBy;
            case "monksCommentNum1Text" -> monksCommentNum1TextBy;
            case "monksCommentNum2Text" -> monksCommentNum2TextBy;
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
}
