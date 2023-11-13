Feature: As a User I should be able to get a list of all books available

  Background: User generates token for Authorization
    Given I am an authorized user
  @AllBooks @Regression
  Scenario: I should be able to get a list fo all books available
    When I search for all books
    Then I receive a list of all books