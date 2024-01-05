package gallery.app.automation.test.suite;

import org.openqa.selenium.*;

public class GalleryHomePage {
    protected WebDriver driver;

    private final By indianTruckImageBy = By.cssSelector("img[id='image/v1703781615/octm7jk8lutkexrnmm65.jpg']");
    private final By girlAndGoatImageBy = By.cssSelector("img[id='image/v1703781467/polmlpk8iugb6lrfndue.jpg']");
    private final By legTattoosImageBy = By.cssSelector("img[id='image/v1703781461/s7w8zritrmj5qzj6yays.jpg']");
    private final By dustbinImageBy = By.cssSelector("img[id='image/v1703781451/lb2jtrormhzlrhdpdhe7.jpg']");
    private final By noviceMonksImageBy = By.cssSelector("img[id='image/v1703781443/moadwvgvunbnhtappmm8.jpg']");

    public GalleryHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("http://localhost:5173");
    }
    public String getPageTitle() {
        return driver.getTitle();
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

}
