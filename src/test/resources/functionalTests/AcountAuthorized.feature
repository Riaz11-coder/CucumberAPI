Feature: Verifies if the user is Authorized
  Description: User is returned a boolean true/false response when a POST request is
  made sending username & password in the request body.


  Scenario: I should receive verification of Authorization
    When  I send positive credentials in POST request
    Then  I should receive response body object verifying my authorization