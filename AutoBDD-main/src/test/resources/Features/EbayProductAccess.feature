Feature: Accessing a Product via Category and Search
  
  Background: 
  Given User lauches Ebay application 
  #above step is common in both scenario
  
  @test
  Scenario Outline: Access a product after applying multiple filters
    When User Clicks on "Shop by category" dropdown
    And Select "Cell phones & accessories" under "Electronics" category
    And User clicks on "Cell Phones & Smartphones" in the left-hand navigation section
    And User clicks on "All Filters"
    And User applies the Filter Type with Filter Value to access a product
      | Filter Type   | Filter Value |
      | Condition     | New          |
      | Price         | $200-$400    |
      | Item Location | US Only      |
    Then Verify that user should see "<Filter Applied Details>" are applied
    

    Examples: 
      | Filter Applied Details                                    |
      | Condition:New;Price:$200.00-$400.00;Item Location:US Only |

  @test
  Scenario: Access a Product via Search
    When User enter "MacBook" in the search bar
    And User change Search category to "Computers/Tablets & Networking" and click "Search"
    Then verify that the page loads completely
    Then verify that first result name matches with search string "MacBook"
