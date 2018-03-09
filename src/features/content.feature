# Author:  Cindy Trowbridge

Feature: Content

  Background: Login page is open
    Given I open a browser
    When I open Login page

  Scenario: Check Content page
    When I login as admin user
    And I open the Content Page
    Then I see the Content Page
    And the Content page has a <tab> tab
      | tab      |
      | Content  |
      | Comments |
      | Files    |
      | Messages |

  Scenario: Add content
    When I login as admin user
    And I open the Content Page
    And I select Add content from the Content page
    Then I see the Add Content page
    When I select Group from the Add Content page
    Then I see the Create Group page
