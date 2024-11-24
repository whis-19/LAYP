Feature: Add Item to Cart

  Scenario: User adds a product to the cart
    Given the user is on the product details page
    When the user clicks on the "Add to Cart" button
    Then the product should be added to the cart


