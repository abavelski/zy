Feature: Signup

  Scenario Outline: Signup new customer
    Given I open web app
    And I see 3 packages
    Then I select first package
    And see the list of 10 numbers
    And choose the first number
    And click "Next" button
    And input first name <firstName>
    And input last name <lastName>
    And input address <address>
    And input city <city>
    And input zip <zip>
    And click "Next" button
    And click "Finish" button
    Then I see message "Signup successful!"
    Examples:
    | firstName  | lastName | address       | city         | zip     |
    | "Vladimir" | "Putin"  | "Red Square"  | "Moscow"     | "00000" |
    | "Barack"   | "Obama"  | "White House" | "Washington" | "10000" |

