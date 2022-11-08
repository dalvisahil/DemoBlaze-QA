Feature: View list of the product

  Scenario: I am able to view list of products
    Given I visit "https://www.demoblaze.com/index.html" site
    Then page title should be "STORE"
    And I should be able to view list of products