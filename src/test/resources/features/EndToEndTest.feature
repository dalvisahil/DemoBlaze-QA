Feature: i AM ABLE TO PURCHASE A PRODUCT


  Scenario: Check out and buy product
    Given  I visit "https://www.demoblaze.com/index.html" site
    When click on a product having name "Samsung galaxy s6"
    Then click on add to cart button
    And open cart page
    Then product with name "Samsung galaxy s6" should be displayed in cart table
    And click on place order button
    And I fill name as "John"
    And I fill country as "India"
    And I fill card as "12345678"
    And I fill city as "Mumbai"
    And I fill month as "11"
    And I fill year as "24"
    Then I click submit
    Then I should have order details name as "John", country as "India", card number as "12345678", city as  "Mumbai", month as "11", year as  "24"
