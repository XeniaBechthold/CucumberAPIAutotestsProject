Feature: User register
  Register via API

  Scenario Outline: Register with valid credentials
    When register new user with email and password
      |  email  | password  |
      | <email> | <password>|
    Then new user is successfully registered
    Examples:
      | email          | password     |
      |eve.holt@reqres.in | pistol   |



  Scenario Outline: Register with wrong credentials
    When register new user with email and without password
      |  email  |
      | <email> |
    Then error is displayed
    Examples:
      | email          |
      | test1test.com  |
      | test2@test.com |