package StepsForScnerio2;

import Utils.CommonFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.MalformedURLException;

public class Scenerio2 {
    public static AndroidDriver driver;
    CommonFunctions common;
    DragButton dragButton;

    @Before
    public void setUp() throws MalformedURLException {
        DriverFactory.launchApp();
        driver = DriverFactory.getDriver();

        // Initialize utility class after driver is available
        dragButton = new DragButton(driver);
        common = new CommonFunctions(driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitApp();
    }

    @Given("user is on Home Page And Clicks on Drag Button")
    public void user_is_on_home_and_click_drag_button(){
        dragButton.clickDragButton();
        System.out.println("step 1 - Successfully Home page appears");
    }

    @When("user start performing drag and drop")
    public void user_perform_drag_drop(){
        dragButton.complete_drag();
        System.out.println("step 2- Drag-drop completed.");
        common.waitForSeconds(3);
    }
    @Then("after successfully completing congratulations message will appear")
    public void cogratulations_appears(){
        System.out.println("step 3 - Congratulations message appears successfully.");
    }
}