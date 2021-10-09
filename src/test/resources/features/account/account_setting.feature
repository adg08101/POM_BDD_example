Feature: Account Settings
  As a: GoHeavy Admin / Fleet Owner / Dispatcher / Document Approver/Retailer Admin/Store Admin/ Store User/Customer				
  I Want To: edit profile information				
  So That: I can update my user profile.

  Background: 
    Given Any user is logged

  #Scenario: Update Settings
  #  Given The user is in  "Account Settings" view
  #  When User insert valid data
  #  And Clicks on the "Update" button
  #  Then The system saves the user profile information
  #  And The system displays popup with success message: "Your profile was successfully updated"
    
  #Scenario Outline: User Deletes the data of some mandatory field AND clicks on the "Update" button.
	#	Given The user is in  "Account Settings" view
	#	When The user clears a mandatory "<field>" on "<input_type>"
	#	And Clicks on the "Update" button
	#	Then The system displays an error message "<message>".
		
	#	Examples:
	#    | field             | input_type | message								|
	#    | firstName         | input			 | This field is required |
	#    | lastName          | input			 | This field is required |
	#    | mobilePhone				| input			 | This field is required |
	#    | email             | input			 | This field is required |
	#    | address					  | textarea   | This field is required |
	#    | addressCity       | input      | This field is required |
	#    | addressZipCode    | input      | This field is required |
	    
  Scenario Outline: Edits the preloaded info with no valid data in some fields.
		Given The user is in  "Account Settings" view
		When The user sets invalid data to "<field>" on "<input_type>"
		Then The system displays an error message "<message>".
		
		Examples:
	    | field             | input_type | message																																			 |
	    | firstName         | input			 | Only letters and special characters (' -) are allowed. 50 characters maximum  |
	    | lastName          | input			 | Only letters and special characters (' -) are allowed. 50 characters maximum  |
	    | mobilePhone				| input			 | Please, enter a valid mobile number																					 |
	    | email             | input			 | Please, enter a valid email address																				   |
	    | address					  | textarea   | The only special characters allowed are (. - , '). 50 characters maximum      |
	    | addressCity       | input      | Only letters and special characters (' -) are allowed. 50 characters maximum	 |
	    | addressZipCode    | input      | Please, enter a valid ZIP code																								 |