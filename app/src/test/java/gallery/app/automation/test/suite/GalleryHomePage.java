package gallery.app.automation.test.suite;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GalleryHomePage {
    protected WebDriver driver;

    private final By indianTruckImageBy = By.cssSelector("img[id='image/v1703781615/octm7jk8lutkexrnmm65.jpg']");
    private final By girlAndGoatImageBy = By.cssSelector("img[id='image/v1703781467/polmlpk8iugb6lrfndue.jpg']");
    private final By legTattoosImageBy = By.cssSelector("img[id='image/v1703781461/s7w8zritrmj5qzj6yays.jpg']");
    private final By dustbinImageBy = By.cssSelector("img[id='image/v1703781451/lb2jtrormhzlrhdpdhe7.jpg']");
    private final By noviceMonksImageBy = By.cssSelector("img[id='image/v1703781443/moadwvgvunbnhtappmm8.jpg']");
    private final By deleteIconContainerBy = By.cssSelector("div[id='delete-icon-container']");
    private final By deleteIconBy = By.cssSelector("svg[id='delete-icon']");
    private final By hoverInfoContainerBy = By.cssSelector("div[id='hover-info-container']");
    private final By hoverInfoLeftSideBy = By.cssSelector("div[id='hover-info-left-side']");
    private final By hoverInfoRightSideBy = By.cssSelector("div[id='hover-info-right-side']");
    private final By hoverInfoAvatarImageBy = By.cssSelector("img[id='hover-info-avatar-image']");
    private final By hoverInfoAvatarLetterContainerBy = By.cssSelector("div[id='hover-info-avatar-letter-container']");
    private final By hoverInfoAvatarLetterBy = By.cssSelector("h1[id='hover-info-avatar-letter']");
    private final By hoverInfoUsernameBy = By.cssSelector("p[id='hover-info-username']");
    private final By hoverInfoCommentsNumBy = By.cssSelector("p[id='hover-info-comments-num']");
    private final By hoverInfoCommentsIconBy = By.cssSelector("svg[id='hover-info-comments-icon']");
    private final By hoverInfoLikesNumBy = By.cssSelector("p[id='hover-info-likes-num']");
    private final By hoverInfoLikeIconBy = By.cssSelector("svg[id='hover-info-likes-icon']");


    public GalleryHomePage(WebDriver driver) {
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
    public By getImageBy(String identifier) {
        return switch (identifier) {
            case "indianTruck" -> indianTruckImageBy;
            case "girlAndGoat" -> girlAndGoatImageBy;
            case "legTattoos" -> legTattoosImageBy;
            case "indianPublicBin" -> dustbinImageBy;
            case "noviceBuddhistMonks" -> noviceMonksImageBy;
            case "deleteIconContainer" -> deleteIconContainerBy;
            case "deleteIcon" -> deleteIconBy;
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
            case "hoverInfoLikeIcon" -> hoverInfoLikeIconBy;
            default -> throw new IllegalArgumentException("Invalid identifier: " + identifier);
        };
    }
    public Boolean checkImagePresence(String identifier) {
        return driver.findElement(getImageBy(identifier)).isDisplayed();
    }
    public void hoverOverImage(String identifier) {
        WebElement image = driver.findElement(getImageBy(identifier));
        Actions actions = new Actions(driver);
        actions.moveToElement(image).build().perform();
//        Thread.sleep(1000);
    }

}
