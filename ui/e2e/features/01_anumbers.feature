Feature: Create range

  Scenario: Create new range
    Given I open web app
    And I go to admin tab
    And input first number as "61660000"
    And input last number as "61660050"
    And click "Submit" button
    Then I see message "A-Numbers created"
