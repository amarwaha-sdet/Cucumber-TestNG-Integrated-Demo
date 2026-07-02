Feature: Data Tables .net website validations

  Background:
    Given the user navigates to data tables page

  @smoke @validationWithSelenium
  Scenario: Miscellaneous validations using Selenium
    When user selects 25 entries per page using selenium
    Then verify 25 entries are shown in data table


  @smoke @validationWithJavaScript
  Scenario: Miscellaneous validations using JavaScript
    Then user performs various operations using JavaScript






