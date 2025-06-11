package Utils;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class Locators {
    public static final By SWIPE_4 = AppiumBy.androidUIAutomator("new UiSelector().text(\"Swipe\")");
    public static final By COMPATIBLE = AppiumBy.androidUIAutomator("new UiSelector().text(\"COMPATIBLE\")");
    public static final By FULLY_OPEN_SOURCE = AppiumBy.androidUIAutomator("new UiSelector().text(\"FULLY OPEN SOURCE\")");

    //=========================== task 4 Locators S2=========================
    public static final By DRAG_BUTTON = AppiumBy.androidUIAutomator("new UiSelector().text(\"Drag\")");

}
