Feature: Testing sum API
  User should be able to call sum calculator API endpoint

Background:
  Given two numbers

Scenario: Call sum endpoint
  When User call sum endpoint
  Then http status respond is 200

