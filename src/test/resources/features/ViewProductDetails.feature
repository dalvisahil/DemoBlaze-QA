Feature: I AM ABLE TO PURCHASE A PRODUCT

  Scenario: I am able to view product details
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a product having name "HTC One M9"
    Then I am able to see the product name "HTC One M9"
    And I am able to see the product description and price
    Then  I am able to see the product image or not
    
  Scenario: I am able to sort the products category wise
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a category laptop category "Laptops"
    Then click on a product having name "MacBook air"
    And I am able to see the product name "MacBook air"
    Then I visit "https://www.demoblaze.com/index.html" site
    And click on a category laptop category "Laptops"
    Then click on a product having name "2017 Dell 15.6 Inch"
    And I am able to see the product name "2017 Dell 15.6 Inch"

    Then  I visit "https://www.demoblaze.com/index.html" site
    And click on a category monitors category "Monitors"
    Then click on a product having name "Apple monitor 24"
    And I am able to see the product name "Apple monitor 24"
    Then I visit "https://www.demoblaze.com/index.html" site
    And click on a category monitors category "Monitors"
    Then click on a product having name "ASUS Full HD"
    And I am able to see the product name "ASUS Full HD"

    Then  I visit "https://www.demoblaze.com/index.html" site
    And click on a category phone category "Phones"
    Then click on a product having name "Iphone 6 32gb"
    And I am able to see the product name "Iphone 6 32gb"
    Then I visit "https://www.demoblaze.com/index.html" site
    And click on a category phone category "Phones"
    Then click on a product having name "HTC One M9"
    And I am able to see the product name "HTC One M9"