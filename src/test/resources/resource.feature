Feature: Get resource

  Scenario Outline: Get resource
    When get resource by id
      |  id  |
      | <id> |
    Then resource data is displayed
    Examples:
      |id  |
      |2   |

  Scenario Outline: Resource not found
    When get resource by id
      | id   |
      | <id> |
    Then resource is not found
    Examples:
      | id |
      | 23  |

  Scenario: Get resource list
    When get resource list
    Then resource list is not empty


