Feature:Search Functionality

  Scenario: User searches for a product on Daraz.pk
    Given the user is on the Daraz homepage
    When the user searches for "asus tuf a15"
    Then results for "asus tuf a15" should be displayed