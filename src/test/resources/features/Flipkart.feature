Feature: Perform operations on Flipkart page

  Background:
    Given user navigates to flipkart home page


  @flipkart
  Scenario: Miscellaneous actions on Flipkart Page
    When user performs various actions on flipkart page
    And user clicks on travel button


  @windowAndTabOpening
  Scenario: Windows and Tab opening using Selenium 4 and Legacy
    When user opens new window and tab
