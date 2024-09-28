Feature: Verify todos completion percentage for FanCode city users

  Scenario: All users of the city FanCode should have more than half of their todos tasks completed
    Given User has the todo tasks
    And User belongs to the city FanCode
    Then User Completed task percentage should be greater than 50%
    