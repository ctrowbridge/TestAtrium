Feature: Basic tests

	Scenario:  Basic Test
		Given I open a browser
		When I open Login page
		Then User is not logged in
		And Documentation URL is valid
		
	Scenario:  Search
	  Given I open a browser
		When I open Login page
		And I search for "hello"
		Then I see the Search Results page

@wip	
		Scenario:  Site Map
		Given I open a browser
		When I open Login page
		And I select Site Map
		Then I see the Site Map page

	Scenario:  Admin Page
		Given I open a browser
		When I open Admin page while user isn't logged in
		Then I see Not Authorized message
		
	