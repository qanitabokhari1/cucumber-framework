package Utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AssertionHelper {

    // Asserts if an element is visible on the screen
    public static void assertElementVisible(WebElement element, String elementName) {
        // Assert.assertNotNull(element, elementName + " → element was null");
        Assert.assertTrue(element.isDisplayed(), elementName + " → element not displayed");
    }

    // Generic true condition assertion
    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    // Force test failure with a message
    public static void fail(String message) {
        Assert.fail(message);
    }

    // Asserts equality between two strings
    public static void assertEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    // Optional: Asserts equality between two generic types
    public static <T> void assertEqualsGeneric(T actual, T expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
