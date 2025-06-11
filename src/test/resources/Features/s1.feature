Feature: User performing swipe left/right actions
  Scenario: Check if user completed swiping
    Given user is on Home Page to Clicks on Swipe Button
    When user perform swipe left
    And user perform swipe right
    Then user completed the swipe actions