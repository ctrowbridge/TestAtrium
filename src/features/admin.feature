# Author:  Cindy Trowbridge

Feature:  Test Admin Page
# Tests for the Administration page

  Scenario:  Admin page not authorized
		Given I open a browser
		When I open Admin page while user isn't logged in
		Then I see Not Authorized message
		
  Scenario:  Admin page check
  	Given I open a browser
		When  I open Login page
		And   I login as admin user
		And   I open Admin page
		Then  I see the Admin page
		And   the Admin page has a <tab> tab
		| tab   |
		| Tasks |
		| Index |
		And the Admin Page has a <task> task
		| task          |
		| Apps          |
		| Content       |
		| Panopoly      |
		| Structure     |
		| Appearance    |
		| People        |
		| Modules       |
		| Configuration |
		| Open Atrium   |
		| Reports       |
		When I select the Reports task from the Admin page
		Then I see the Reports page
		And  the Reports page as a <task> task
		| task              |
		| Status report     |
		| Available updates |
		| Feeds log         |
		| Field list        |
		| Views plugins     |
		When I open Admin page
		And  I select the People task from the Admin page
		Then I see the People page
		When I set the Username filter name to "xxxxxx"
		Then I see no Users available
		When I select the Execute button from the People page
		Then I see no operation selected