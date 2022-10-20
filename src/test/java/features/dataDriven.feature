Feature: data driven
 Register the number of registrations and verify the validation station of uses
@datadriven
  Scenario Outline: Facebook Registration using invalid data for user name "<First Name>"
    Given User on the new user registration page
    When User enter invalid data as "<First Name>", "<Last Name>", "<Email Address>", "<Re-enter Email Address>" and "<Password>"
    And user click on submit Button
    Then user registration should be unsuccessful
  Examples:
    | First Name             | Last Name               | Email Address         | Re-enter Email Address | Password              |
    | Tom                    | Kenny                   | someone@someone.com   |someone@someone.com     |Password1              |
    | Tom                    | Kenny                   | someone@someone.com   |someone@someone.com     |Password1              |
    | Tom                    | Kenny                   | someone@someone.com   |someone@someone.com     |Password1              |
    | Tom                    | Kenny                   | someone@someone.com   |someone@someone.com     |Password1              |
    | Tom                    | Kenny                   | someone@someone.com   |someone@someone.com     |Password1              |

  @datadriven
  Scenario Outline: Fill the registration form for user "<Name>"
    Given user on Lambda Test registration page
    When user entere data as "<Name>", "<Email>", "<Password>", "<company>", "<Website>", "<City>", "<Address1>", "<Address2>", "<State>" and "<Zip>"
    And user click on submit button
    Then validate success message
    Examples:
      | Name                | Email              | Password          | company      |Website             |City        |Address1        | Address2    |   State         |   Zip      |
      | Sanjay              | sanjay12@gmail.com | Shiv@123          | TechM        |www.tech.com        |Pune        |Srinagar        | Pune        |   Maharashtra   |   413 001  |
      | Sanjay              | sanjay12@gmail.com | Shiv@123          | TechM        |www.tech.com        |Pune        |Srinagar        | Pune        |   Maharashtra   |   413 001  |
      | Sanjay              | sanjay12@gmail.com | Shiv@123          | TechM        |www.tech.com        |Pune        |Srinagar        | Pune        |   Maharashtra   |   413 001  |
      | Sanjay              | sanjay12@gmail.com | Shiv@123          | TechM        |www.tech.com        |Pune        |Srinagar        | Pune        |   Maharashtra   |   413 001  |
      | Sanjay              | sanjay12@gmail.com | Shiv@123          | TechM        |www.tech.com        |Pune        |Srinagar        | Pune        |   Maharashtra   |   413 001  |

  @datadriven
Scenario Outline: Register lambda test dummy form for "<FirstName>"
  Given User on lambdatest dummy registration page
  When User click on continue button without filling required data
  Then validate field error messages
  When User enter valid data as "<FirstName>", "<LastName>", "<Phone>", "<Password>", "<ConfirmPass>"
  Then validate the success message after submit the form
    Examples:
      | FirstName | LastName | Phone         | Password | ConfirmPass |
      | Sanjay    | Waware   | +919096387258 | Shiv@123 | Shiv@123    |
      | Sanjay    | Waware   | +919096387258 | Shiv@123 | Shiv@123    |
      | Sanjay    | Waware   | +919096387258 | Shiv@123 | Shiv@123    |
      | Sanjay    | Waware   | +919096387258 | Shiv@123 | Shiv@123    |
      | Sanjay    | Waware   | +919096387258 | Shiv@123 | Shiv@123    |
