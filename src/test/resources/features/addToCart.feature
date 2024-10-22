<<<<<<< Updated upstream
Feature: Add Product to Cart

  Scenario: User adds a product to the cart on Daraz
    Given the user is on the Daraz homepage
    When the user searches for a product "Laptop"
    And the user selects the first product from the search results
    And the user adds the product to the cart
    Then the product should be added to the cart
    And the cart icon should show the number of items as 1
=======
Feature: Add Item to Cart

  Scenario: User adds a product to the cart
    Given the user is on the product details page
    When the user clicks on the "Add to Cart" button
    Then the product should be added to the cart


>>>>>>> Stashed changes
