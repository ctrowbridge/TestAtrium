# Author:  Cindy Trowbridge

Feature:  Add User
# Test to add a user

  Scenario:  Add User
  	Given I open a browser
		When I open Login page
		And I login as admin user
		And I open Admin page
		Then I see the Admin page
		