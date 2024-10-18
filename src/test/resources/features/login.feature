Feature: User Login

  Scenario: Successful login Test
    Given the user is on the login page
    When the user enters valid credentials
    Then the user should be logged in
