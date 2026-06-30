Feature: Sauce Labs Demo Website

  Background:
    Given the user navigates to sauce labs home page

  @smoke @login
  Scenario Outline: Login functionality test
    When the user enters credentials "<loginName>" and "<loginPassword>"
    And the user clicks on login button
    Then the user verifies login result as "<result>"

    Examples:
      | loginName       | loginPassword     | result        |
      | standard_user   | secret_sauce      | successful    |
      | locked_out_user | secret_sauce      | unsuccessful  |
      | invalid_user    | invalid_password  | unsuccessful  |


  @smoke @inventoryVerification
  Scenario: Inventory verification test
    When the user enters credentials "standard_user" and "secret_sauce"
    And the user clicks on login button
    Then the user verifies following products and their prices
    | productName                       | price  |
    | Sauce Labs Backpack               | $29.99 |
    | Sauce Labs Bike Light             | $9.99  |
    | Sauce Labs Bolt T-Shirt           | $15.99 |
    | Sauce Labs Fleece Jacket          | $49.99 |
    | Sauce Labs Onesie                 | $7.99  |
    | Test.allTheThings() T-Shirt (Red) | $15.99 |


