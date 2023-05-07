@Comment
Feature: Add Comment on a Blog/Post
  As a Looegd in User of the application
  I want to write a comment to a Blog/Post successfully

  Background: User sees a blog on Homepage and clicks on it
    Given I am a logged in user

  @SuccessfulAddComment
  Scenario Outline: Successful Add Comment to a Blog/Post
    When I click on the "View post" link
    Then I should  land on the "Post Details" page
    And I fill in "commentary" with  "<message>"
    And I click on the "Comment" button
    Examples:
      | message |
      | asdf    |
