Feature: Signup

  Scenario: Signup new customer
    Given I open web app
    And I see 3 packages
    Then I select first package
    And see the list of 10 numbers
    And choose the first number
    And click "Next" button
    And input first name "Vladimir"
    And input last name "Putin"
    And input address "Red Square 1"
    And input city "Moscow"
    And input zip "00000"
    And click "Next" button
    And click "Finish" button
    Then I see message "Signup successful!"

