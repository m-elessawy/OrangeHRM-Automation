Feature: User Management in OrangeHRM

  Background:
    Given I am on the OrangeHRM login page
  @web
  Scenario Outline: Add and delete a new user
    Given I am on the login page
    When I enter the username "<username>" and password "<password>"
    And I click on the login button
    And I navigate to the Admin page
    Then I note the current number of users
    And I capture the first employee name
    When I click on the add button
    And I fill in the new user details with username "<newUsername>", password "<newPassword>", user role "<userRole>", and status "<status>"
    And I save the new user
    Then I should see the user count increased by one
    When I search for username "<newUsername>"
    And I delete the user
    Then I should see the user count decreased by one
    Examples:
      | username | password  | newUsername   | newPassword     | userRole | status  |
      | Admin    | admin123  | m.elessawy@22 | P@$$w0rd@123    | ESS      | Enabled |
