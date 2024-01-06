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
    private final By hoverInfoLikesNumBy = By.cssSelector("p[id='hover-info-likes-num']");
    private final By hoverInfoLikesIconBy = By.cssSelector("svg[id='hover-info-likes-icon']");
    private final By hoverInfoLikesOutlineIconBy = By.cssSelector("svg[id='hover-info-likes-outline-icon']");


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
            case "deleteIcon" -> deleteIconBy;
            case "deleteBtnContainer" -> deleteBtnContainer;
            case "cancelDeleteImageBtn" -> cancelDeleteImageBtn;
            case "confirmDeleteImageBtn" -> confirmDeleteImageBtn;
            case "hoverInfoContainer" -> hoverInfoContainerBy;
            case "hoverInfoLeftSide" -> hoverInfoLeftSideBy;
            case "hoverInfoRightSide" -> hoverInfoRightSideBy;
            case "hoverInfoAvatarImage" -> hoverInfoAvatarImageBy;
            case "hoverInfoAvatarLetterContainer" -> hoverInfoAvatarLetterContainerBy;
            case "hoverInfoAvatarLetter" -> hoverInfoAvatarLetterBy;
            case "hoverInfoUsername" -> hoverInfoUsernameBy;
            case "hoverInfoCommentsNum" -> hoverInfoCommentsNumBy;
            case "hoverInfoCommentsIcon" -> hoverInfoCommentsIconBy;
            case "hoverInfoLikesNum" -> hoverInfoLikesNumBy;
            case "hoverInfoLikesIcon" -> hoverInfoLikesIconBy;
            case "hoverInfoLikesOutlineIcon" -> hoverInfoLikesOutlineIconBy;
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
