
package Steps;

import Utils.CommonFunctions;
import Utils.Locators;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;

public class SwipActions {
    private final AndroidDriver driver;
    private final CommonFunctions common;

    public SwipActions(AndroidDriver driver) {
        this.driver = driver;
        this.common = new CommonFunctions(driver);
    }

    public void waitForAppLaunch() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.SWIPE_4));  // Adjust with actual locator
        System.out.println("App has launched successfully.");
    }

    public void clickSwipeButton() {
        // Click the swipe button to navigate to the swipe screen
        common.clickElement(Locators.SWIPE_4);  // Update with the actual swipe button locator
        common.waitForSeconds(2);  // Wait for the swipe screen to load

    }

    public void openSwipeScreen() {
        // Assuming swipe screen is already open
        common.waitForSeconds(2);
    }

    public void swipeUntilElementVisible(By target, String direction, int maxSwipes) {
        int attempts = 0;
        boolean found = false;

        while (attempts < maxSwipes) {
            try {
                WebElement el = driver.findElement(target);
                if (el.isDisplayed()) {
                    System.out.println("✅ Found element: " + el.getText());
                    found = true;
                    break;
                }
            } catch (Exception e) {
                // not found, will attempt swipe
            }

            if ("left".equalsIgnoreCase(direction)) {
                performSwipe(0.8, 0.2);
            } else if ("right".equalsIgnoreCase(direction)) {
                performSwipe(0.2, 0.8);
            } else {
                throw new IllegalArgumentException("Direction must be 'left' or 'right'");
            }

            // Wait for UI to settle after swipe
            new WebDriverWait(driver, Duration.ofSeconds(1))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.ProgressBar")));

            attempts++;
        }

        if (!found) {
            Assert.fail("❌ Element " + target + " not found after " + maxSwipes + " swipes in " + direction);
        }
    }

    private void performSwipe(double startXPercent, double endXPercent) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;
        int startX = (int)(w * startXPercent);
        int endX   = (int)(w * endXPercent);
        int y      = h / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500),
                PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }
}