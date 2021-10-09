Feature: Test

  Scenario Outline: Allowing and redirecting user according to its access
    Given The unauthenticated GoHeavy User is in the Login view
    When User insert email "<emails>" and password "<passwords>"
    And User clicks on the "Sign in" button
    Then The system allows the user access to the system
    And Sytem redirects to "<views>" view
    And System Logs Out

    Examples: 
      | emails             | passwords | views             |
      | novanick@gmail.com | @User123  | Dashboard         |
      | evakings@gmail.com | @User123  | Drivers List      |
      | piper95@gmail.com  | @User123  | Fleet Owners List |
      
  Scenario Outline: At least one mandatory data is not inserted AND clicks on the "Sign in" button.
	  Given The unauthenticated GoHeavy User is in the Login view
	  When User insert email "<emails>" and password "<passwords>"
	  And User clicks on the "Sign in" button
	  Then The system displays an error "<message>" below each field

	  Examples:
	    | emails             | passwords | message                 |
	    |                    |           |	This field is required |
	    | novanick@gmail.com |           |	This field is required |
	    |                    | @User123  |	This field is required |
	    
  Scenario Outline: Inserts an email address or a password that is not registered in the system.
	  Given The unauthenticated GoHeavy User is in the Login view
	  When User insert email "<emails>" and password "<passwords>"
	  And User clicks on the "Sign in" button
	  Then The system displays the following error "<message>" in a popup window

	  Examples:
	    | emails          | passwords | message                                                                                              |
	    | fake1@gmail.com | fake1     |	The email or password you entered is incorrect. If you are not registered please create an account. |
	    | fake2@gmail.com | fake2     |	The email or password you entered is incorrect. If you are not registered please create an account. |
	    | fake3@gmail.com | fake3     |	The email or password you entered is incorrect. If you are not registered please create an account. |
