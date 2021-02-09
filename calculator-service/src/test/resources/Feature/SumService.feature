Feature: Testing sum API
  User should be able to call sum calculator API endpoint

Scenario: Call sum endpoint
  When User calls endpoint /v1/sum with queryparams number1 equal to "4" and number2 equal to "6"
  Then User receives http status code respond of 200
  And response contains operation "sum" and result 10


