Feature: Visit Demo Inventory and Open First Product

  Scenario: User visits demo page and opens product
    Given the user is on the demo page
    When the user clicks on the product
    Then the user should see the product details page
