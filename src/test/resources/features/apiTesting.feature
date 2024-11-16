Feature: API Testing for Candidate Management

  @api
  Scenario Outline: Add and delete a candidate via API
    Given I have the candidate details with first name "<firstName>", middle name "<middleName>", last name "<lastName>", email "<email>", contact number "<contactNumber>", keywords "<keywords>", comment "<comment>", date of application "<dateOfApplication>"
    When I add the candidate using API
    Then I verify the candidate is added successfully
    When I delete the candidate using API
    Then I verify the candidate is deleted successfully

    Examples:
      | firstName | middleName | lastName | email             | contactNumber | keywords    | comment   | dateOfApplication |
      | mohamed   | ahmed      | mostafa  | m.r@test.com      | 01095012599     | SE, 2024  | ddnote    | 2024-11-16        |
