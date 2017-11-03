# Author:  Cindy Trowbridge

Feature: User Tests
	Test for user settings and other options available from the user menu

 Background: Login page is open
    Given I open a browser
		When I open Login page
		
	Scenario:  Settings
	  Verify that the admin user can set notification settings and they persist after logging off then on again.
		Given I login as admin user
		When I select Settings from the user menu
		Then I see the Settings page
		And the email preference is "HTML"
		And the digest preference is "One digest"
		
		When I set mail preference to "Plain text"
		And I set digest preference to "Combined digest"
		And I save my configuration from the Settings page
		And I logoff Settings page
		And I login as admin user
		
		# Check persistance
		
		And I select Settings from the user menu
		Then the email preference is "Plain text"
		And the digest preference is "Combined digest"
		
		# Reset settings
		
		When I set mail preference to "HTML"
		And I set digest preference to "One digest"
		
	Scenario:  Dashboard
		Given I login as admin user
		When I select Dashboard from the user menu
		Then I see the Dashboard page
		
		