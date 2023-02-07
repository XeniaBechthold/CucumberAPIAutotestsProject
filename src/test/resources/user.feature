Feature: User CRUD operations
  CRUD via API

  Scenario Outline: Create user
    When create user with name and job
      |  name  | job  |
      | <name> | <job>|
    Then user is successfully created
    Examples:
      | name         | job     |
      |John Doe      | developer   |
      |Jane Doe      | designer   |
      |John Snow     | CTO         |

  Scenario Outline: Update user
    When update user with job
      | job  |
     | <job>|
    Then user is successfully updated with job
      | job  |
      | <job>|
    Examples:
       | job     |
       | architect   |
      | PM   |
     | CTO         |


  Scenario: Delete user
    When delete user by id
    Then user is successfully deleted

