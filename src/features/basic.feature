# Author:  Cindy Trowbridge

Feature: Basic tests

  # Tests which can be performed without the user being logged in
  Background: Login page is open
    Given I open a browser
    When I open Login page

  Scenario: Basic Test
    Then User is not logged in
    And Documentation URL is valid

  Scenario: Search
    And I search for "hello"
    Then I see the Search Results page

  Scenario: Site Map
    And I select Site Map
    Then I see the Site Map page
