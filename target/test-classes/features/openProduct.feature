Feature: Open Product Page

  Scenario: User opens the first product listed on Daraz
    Given the user is on the Daraz homepage
    When the user searches for a product "Laptop"
    And the user selects the first product from the search results
    Then the product page should be displayed
    And the product title should contain "Laptop"
    And the product price should be visible
    And the add to cart button should be enabled