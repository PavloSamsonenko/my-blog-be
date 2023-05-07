@Login
Feature: Login
  As a Registered User of the application
  I want to validate the Login functionality
  In order to check if it works as desired

  Background: A Registered User navigates to Login page
    Given I am a registered user
    And I navigate to the "Login" page

  @SuccessfulLogin
  Scenario Outline: Successful login using valid credentials
    When I fill in "email" with "<email>"
    And I fill in "password" with "<password>"
    And I click on the "Log In" button
    Then I should be successfully logged in
    Examples:
      | email     | password |
      | test@test | test     |

  @failedLogin
  Scenario Outline: Failed login using wrong credentials
    When I fill in "email" with "<email>"
    And I fill in "password" with "<password>"
    And I click on the "Log In" button
    Then I should see "error" message
    Examples:
      | email | password |
      | araj  | !23      |
      | ARAJ  | !23      |
      | aRaJ  | !23      |
      | Test  | !23      |

  @DisabledLogin
  Scenario Outline: Disabled Login when one of the required fields is left blank
    When I fill in "email" with "<email>"
    And I fill in "password" with "<password>"
    And I click on the "Log In" button
    Then I should see "<form error>" message for "<input field>" field on "Login" page
    Examples:
      | email   | password  | form error               | input field |
      |         | Asdf@1234 | Email cannot be empty    | email       |
      | araj@sa |           | Password cannot be empty | password    |
