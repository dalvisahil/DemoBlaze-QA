Feature: Check out and buy product

  Scenario: Check out and buy product - Negative
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a product having name "Samsung galaxy s6"
    Then click on add to cart button
    And open cart page
    Then product with name "Samsung galaxy s6" should be displayed in cart table
    Then check for price whether it is 360 or not
    And add another product to the cart named "Nexus 6"
    And open cart page
    Then check for price whether it is 1010 or not
    And click on place order button
    And check submitting blank form
    Then check submitting with name only

  Scenario: Check out and buy product - positive
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a product having name "Sony xperia z5"
    Then click on add to cart button
    And open cart page
    Then product with name "Sony xperia z5" should be displayed in cart table
    And click on place order button
    And I fill name as "John"
    And I fill country as "India"
    And I fill card as "12345678"
    And I fill city as "Mumbai"
    And I fill month as "11"
    And I fill year as "24"
    Then I click submit
    Then I should have order details name as "John", country as "India", card number as "12345678", city as  "Mumbai", month as "11", year as  "24"
