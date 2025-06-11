Feature: feature to perform drag and drop to complete puzzle
  Scenario: check if the drag and drop performing well with updated indexes
    Given user is on Home Page And Clicks on Drag Button
    When user start performing drag and drop
    Then after successfully completing congratulations message will appear