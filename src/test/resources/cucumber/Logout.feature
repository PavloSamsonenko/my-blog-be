@Logout
Feature: Logout
  As a Looegd in User of the application
  I want to Logout successfully

  Background: User is logged in and is on Homepage
    Given I am a logged in user

  @SuccessfulLogout
  Scenario: Successful Logout
    When I click on "Logout" button on the "Home" page
    Then I should be successfully logged out
    And I should land on the "Login" page