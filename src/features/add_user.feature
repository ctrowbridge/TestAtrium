# Author:  Cindy Trowbridge

Feature: Add User

  # Test to add a user
  Background: 
    Given I open a browser
    When I open Login page

  Scenario: Add Non-admin User
    When I login as admin user
    And I open Admin page
    Then I see the Admin page
    When I select the People task from the Admin page
    Then I see the People page
    When I select Add User
    Then I see the Add New User page
    When I enter a new non-admin user
    And I select to Create new Account
    Then new non-admin user is created

  Scenario: Delete Non-admin User
    When I login as admin user
    And I open Admin page
    When I select the People task from the Admin page
    And I delete the non-admin user
    Then The non-admin user is deleted
