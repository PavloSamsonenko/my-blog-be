@Registration
Feature: Registration
  As an Un-registered User of the application
  I want to validate the Registration functionality
  In order to check if it works as desired

  Background: User navigates to Registration page
    Given I navigate to the "Registration" page

  @SuccessfulRegistration
  Scenario Outline: Successful Registration using valid credentials
    When I fill in "username" with "<username>"
    And I fill in "email" with "<email>"
    And I fill in "password" with "<password>"
    And I fill in "confirmPassword" with "<confirmPassword>"
    And I click on  the "Sign Up" button
    Then I should be successfully registered
    Examples:
      | username | email     | password | confirmPassword |
      | test     | test@test | test     | test            |


  @DisabledRegistration
  Scenario Outline: Disabled Registration when one of the required fields is left blank
    When I fill in "username" with "<username>"
    And I fill in "email" with "<email>"
    And I fill in "password" with "<password>"
    And I fill in "confirmPassword" with "<confirmPassword>"
    And I click on  the "Sign Up" button
    Then I should see "<form error>" message for "<input field>" field on "Registration" page
    Examples:
      | username | email                 | password  | confirmPassword | form error               | input field     |
      |          | asdf.asdf@example.com | Asdf@1234 | Asdf@1234       | Username cannot be empty | username        |
      | asdf     |                       | Asdf@1234 | Asdf@1234       | Email cannot be empty    | email           |
      | asdf     | asdf                  | Asdf@1234 | Asdf@1234       | Email is not valid       | email           |
      | asdf     | asdf.asdf@example.com |           | Asdf@1234       | Password cannot be empty | password        |
      | asdf     | asdf.asdf@example.com |           | Asdf@1234       | Passwords don't match    | confirmPassword |