Feature: User login
  Login via API

  Scenario Outline: Login with valid credentials
    When login user with email and password
      |  email  | password  |
      | <email> | <password>|
    Then user is successfully authorized
    Examples:
      | email          | password     |
      |eve.holt@reqres.in | cityslicka   |



  Scenario Outline: Login with wrong credentials
    When login user with email and without password
      |  email  |
      | <email> |
    Then login error is displayed
    Examples:
      | email          |
      | test2@test.com |