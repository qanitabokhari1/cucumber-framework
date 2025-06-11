package StepsForScnerio2;

import Utils.CommonFunctions;
import Utils.Locators;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;

public class DragButton {
    private final AndroidDriver driver;
    private final CommonFunctions common;

    public DragButton(AndroidDriver driver) {
        this.driver = driver;
        this.common = new CommonFunctions(driver);
    }

    public void clickDragButton(){
        common.waitForSeconds(2);
        // Click the drag menu
        common.clickElement(Locators.DRAG_BUTTON);
        common.waitForSeconds(2);
    }

    public void complete_drag() {
        // Perform all drag and drop actions
        performDragAndDrop("android.widget.ImageView", 8, "android.view.ViewGroup", 7);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 3, "android.view.ViewGroup", 8);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 3, "android.view.ViewGroup", 7);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 5, "android.view.ViewGroup", 11);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 4, "android.view.ViewGroup", 13);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 1, "android.view.ViewGroup", 7);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 3, "android.view.ViewGroup", 7);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 2, "android.view.ViewGroup", 7);
        common.waitForSeconds(2);
        performDragAndDrop("android.widget.ImageView", 1, "android.view.ViewGroup", 7);
    }

    private void performDragAndDrop(String sourceClass, int sourceInstance, String targetClass, int targetInstance) {
        try {
            WebElement source = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"" + sourceClass + "\").instance(" + sourceInstance + ")"));

            WebElement target = driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().className(\"" + targetClass + "\").instance(" + targetInstance + ")"));

            // Ensure source and target are interactable
            if (!isElementInteractable(source) || !isElementInteractable(target)) {
                System.out.println("❌ Source or target element is not interactable!");
                return;
            }

            // Get the coordinates for the drag and drop action
            int startX = source.getRect().getX() + source.getRect().getWidth() / 2;
            int startY = source.getRect().getY() + source.getRect().getHeight() / 2;
            int endX = target.getRect().getX() + target.getRect().getWidth() / 2;
            int endY = target.getRect().getY() + target.getRect().getHeight() / 2;

            // Log the drag positions for debugging
            System.out.println("Start position: [" + startX + "," + startY + "] -> End position: [" + endX + "," + endY + "]");

            // Prepare the touch action sequence
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence drag = new Sequence(finger, 1);

            // Move finger to start position (add a small delay for smooth motion)
            drag.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, startY));

            // Press down on the source element
            drag.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

            // Simulate short hold at the source (optional for visual feedback)
            drag.addAction(finger.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(), startX, startY));

            // Drag to destination position
            drag.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY));

            // Release at the target element (drop)
            drag.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            // Perform the sequence
            driver.perform(Arrays.asList(drag));

            // Log success
            System.out.println("✅ Dragged from [" + sourceInstance + "] to [" + targetInstance + "]");

            // Optional: Wait for the drop to be registered properly
            common.waitForSeconds(2);

            // After each drag-and-drop, ensure the target element is interactable before the next action
            waitForElementToBeVisible(target);

        } catch (Exception e) {
            System.out.println("❌ Drag-and-drop failed for source " + sourceInstance + " to target " + targetInstance);
            e.printStackTrace();
        }
    }
    // Check if an element is interactable (visible and enabled)
    public boolean isElementInteractable(WebElement element) {
        return element != null && element.isDisplayed() && element.isEnabled();
    }

    // Wait for an element to be visible before performing actions
    public void waitForElementToBeVisible(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(element));
    }
}