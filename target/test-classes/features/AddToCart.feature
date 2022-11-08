Feature: Add to cart

  Scenario: I am able to add product to cart
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a product having name "Nexus 6"
    Then click on add to cart button