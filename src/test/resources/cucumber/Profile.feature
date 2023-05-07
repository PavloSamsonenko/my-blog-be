@Profile
Feature: View/Edit Profile
  As a Registered User of the application
  I want to view and update my profile

  Background: User is logged in and is on Homepage
    Given I am a logged in user

  @ViewProfile
  Scenario: Successful View Profile
    When I click "My Profile" link
    Then I should land on the  "My Profile" page

  @EdiProfile
  Scenario Outline: Successful Edit Profile
    When I click "My Profile" link
    And I click on "Change Data"  button
    And I fill in email as "<email>"
    And I fill in username as "<username>"
    And I click on the "Submit changes" button
    Then I should land on the "My Profile" page
    Examples:
      | email     | username |
      | test@test | test     |