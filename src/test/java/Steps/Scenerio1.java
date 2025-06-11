package Steps;

import Utils.CommonFunctions;
import Utils.Locators;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;

public class Scenerio1 {
    public static AndroidDriver driver;
    CommonFunctions common;
    SwipActions swipActions;

    @Before
    public void setUp() throws MalformedURLException {
        DriverFactory.launchApp();
        driver = DriverFactory.getDriver();

        // Initialize utility class after driver is available
        swipActions = new SwipActions(driver);
        common = new CommonFunctions(driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitApp();
    }

    @Given("user is on Home Page to Clicks on Swipe Button")
    public void user_is_on_home_page(){
        swipActions.clickSwipeButton();
        System.out.println("Home screen opened.");
    }

    @When("user perform swipe left")
    public void perform_swipe_left(){
        swipActions.swipeUntilElementVisible(Locators.COMPATIBLE,"left",6);
        common.waitForSeconds(2);
        System.out.println("Swipe left until element is found");
    }

    @And("user perform swipe right")
    public void perform_swipe_right(){
        swipActions.swipeUntilElementVisible(Locators.FULLY_OPEN_SOURCE,"right",6);
        common.waitForSeconds(2);
        System.out.println("Swipe right until element is found");
    }

    @Then("user completed the swipe actions")
    public void completed_text(){
        System.out.println("Congratulations text appears.");
        common.waitForSeconds(5);
    }
}
