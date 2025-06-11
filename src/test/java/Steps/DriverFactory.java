package Steps;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static AndroidDriver driver;

    public static void launchApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Basic capabilities
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "BYKVEUZXOZWSMVLF"); // Use actual device name from `adb devices`
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // App package and activity
        capabilities.setCapability("appPackage", "com.wdiodemoapp");
        capabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

        // Do NOT include 'app' capability if using appPackage/appActivity directly
        // This assumes the app is already installed on the device

        // Create driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);
    }

    public static void quitApp() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static AndroidDriver getDriver() {
        return driver;
    }
}
