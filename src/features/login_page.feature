Feature:  Login Page

 Background: Login page is open
    Given I open a browser
		When I open Login page
		
@wip
Scenario: 
	Then User is not logged in
	And the title is "Cindy's Site"
	And the document URL on the Login page is a valid link
	And the menu is present on the Login page
	When I select the Log In button from the Login Page
	Then I see the error message "Username field is required."
	And I see the error message "Password field is required."
	When I set the Username in the login screen to "dummy"
	And  I select the Log In button from the Login Page
	Then I see the error message "Password field is required."
	When I set the Username in the login screen to ""
	And I set the password to "password"
	And  I select the Log In button from the Login Page
	Then I see the error message "Username field is required."