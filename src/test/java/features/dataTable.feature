Feature: Data table
  Verify that the new user registration successfully or unsuccessful using data table concept
  @datatable
  Scenario: Register using invalid data
    Given I am on the new user registration page
    When I enter invalid data on the page
      | Fields                 | Values              |
      | First Name             | Tom                 |
      | Last Name              | Kenny               |
      | Email Address          | someone@someone.com |
      | Re-enter Email Address | someone@someone.com |
      | Password               | Password1           |
    Then the user registration should be unsuccessful

  @datatable
  Scenario: Fill the registration form
    Given I am on lambda test user registration page
    When I enter valid data in the form
      | Fields                 | Values              |
      | Name                   | Sanjay              |
      | Email                  | sanjay12@gmail.com  |
      | Password               | Shiv@123            |
      | company                | TechM               |
      | Website                | www.tech.com        |
      | City                   | Pune                |
      | Address1               | Srinagar            |
      | Address2               | Pune                |
      | State                  | Maharashtra         |
      | Zip                    | 413 001             |
    And user submit the form
    Then validate the message

@datatable
Scenario: Register lambda test dummy form
  Given I am on lambdatest dummy registration page
  When I click on continue button without filling required data
  Then validate error messages
  When I enter required data in the form and submit the form
    | Fields                 | Values              |
    | FirstName              | Sanjay              |
    | LastName               | Waware              |
   # | Email                  | sanjay12@gmail.com  |
    | Phone                  | +919096387258       |
    | Password               | Shiv@123            |
    | ConfirmPass            | Shiv@123            |
  Then validate the success message
