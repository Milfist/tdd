Feature: Testing pow API
  User should be able to call pow calculator API endpoint

Scenario: Call pow endpoint
  When User calls endpoint "/v1/pow" with body:
  """

  {
    "a": 5,
    "b": 2
  }

  """

  Then the response status code should be 200
  And the response should be empty


