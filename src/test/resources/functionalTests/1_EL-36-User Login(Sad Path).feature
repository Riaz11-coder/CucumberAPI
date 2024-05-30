Feature: User Login - Sad Path

	#User should not be able to log in when providing incorrect credentials.
	@TEST_EL-36
	Scenario: User Login - Sad Path
		When I provide invalid login details (username and password),
		Then I should receive an unsuccessful response.
		
