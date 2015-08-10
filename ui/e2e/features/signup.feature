Feature: Signup

  Scenario: Signup new customer
    Given I open web app
    And I see 3 packages
    Then I select first package
    And see the list of 10 numbers
    And choose the first number
    And click "Next" button

