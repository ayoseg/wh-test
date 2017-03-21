Feature: Place bet

  As a WH Customer
  I want the ability to place bet on an English Premier League event

  Scenario Outline: Place bet
    Given that I am on sports.williamhill.com/sr-admin-set-white-list-cookie.html
    And I navigate to a Premiership football event
    When I select the event and place a "<amount>" bet for the home team to Win
    Then I should see the odds and returns offered

    Examples:
    |amount|
    |0.05  |